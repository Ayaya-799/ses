////////////////////////////////////////////////////////////////////////////
//Copyright (C) 2004 ICBC
//
//ALL RIGHTS RESERVED BY ICBC CORPORATION, THIS PROGRAM
//MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH IT WAS  
//FURNISHED BY ICBC CORPORATION, NO PART OF THIS PROGRAM
//MAY BE REPRODUCED OR DISCLOSED TO OTHERS, IN ANY FORM
//WITHOUT THE PRIOR WRITTEN PERMISSION OF ICBC CORPORATION.
//USE OF COPYRIGHT NOTICE DOES NOT EVIDENCE PUBLICATION
//OF THE PROGRAM
//
//			ICBC CONFIDENTIAL AND PROPRIETARY
//
////////////////////////////////////////////////////////////////////////////
//

package icbc.cmis.service.channel;

import java.util.Hashtable;
import java.util.StringTokenizer;

import icbc.cmis.base.CTEInvalidRequestException;
import icbc.cmis.base.CTEObjectNotFoundException;
import icbc.cmis.base.CmisConstance;
import icbc.cmis.base.KeyedDataCollection;
import icbc.cmis.base.SignOnException;
import icbc.cmis.base.TranFailException;
import icbc.cmis.operation.CmisOperation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <b>��������: </b> 2004-10-21<br>
 * <b>����: </b><br>
 * <b>������: </b><br>
 * ���״������ĳ���ʵ���ˣ�ʵ���˽��׵Ĵ�������<br>
 * <p>Copyright: Copyright (c)2004</p>
 * <p>Company: </p>
 * 
 * @author YangGuangRun
 * 
 * @version 
 * 
 * @since 
 * 
 * @see 
 * 
 */

public abstract class ServiceHandler implements Handler {

	/** 
	 * <b>��������: </b><br>
	 * <p>�Խ���������д���</p>
	 * @see icbc.cmis.service.channel.Handler#processRequest()
	 * 
	 * 
	 */
	
	public void processRequest(ChannelContext channelContext) {
		String opName = null;
		try {
			
			
			preProcessRequest(channelContext);
			//20041125 chenj	add for record log;
			String retseq =null; 
			try{
				//System.out.print("Begin log 1!");
				retseq = logData(channelContext);	
			}
			catch(Exception e){
				retseq = "000";
			}
			KeyedDataCollection opData=channelContext.getRequestData();
			
			//System.out.print("Begin log retseq"+retseq);
			//add end chenj
			
			//KeyedDataCollection opData=channelContext.getRequestData();
			//20041125 chenj	add for record log;			
			opData.addElement("reqSeq",retseq);
			//add end chenj
			
			SessionManager sMgr = new SessionManager(channelContext);
			IDAuthenticate idAuthenticator=CM2002Service.getIDAuthenticate();

			if (!sMgr.isValid(idAuthenticator)) {
				handleException(channelContext,
					new TranFailException(
						"",
						"ServiceHandler.(ChannelContext channelContext)",
						"�Ự��Ч","�Ự����δͨ��֤���ѳ�ʱ"));
				return;
			}
			
			String TransNo=(String)opData.getValueAt("TransNo");
			if(CmisConstance.isOutPopedom(TransNo))
			{
				throw new TranFailException("", "processRequest()", "��ҵ����Ŀǰʱ���Ѿ������ƣ�����Ȩ���иò���", "��ҵ����Ŀǰʱ���Ѿ������ƣ�����Ȩ���иò���");
			}
			//ServiceHandlerDAO dao = new ServiceHandlerDAO(new icbc.cmis.operation.DummyOperation());
			
			opName = (String) (opData.getValueAt("operationName"));
			Class c = createOperation(opName);
			opName = c.getName().toString();
			CmisConstance.isValidRuningStatus(opName,opData);
			String replayPage = "";
			try {
				CmisOperation operation = (CmisOperation) c.newInstance();
				operation.setOperationName(opName);
				operation.setOperationData(opData);
				operation.setServerAddr((String)opData.getValueAt("ServerAddr"),
						(String)opData.getValueAt("RemoteAddr"));
				//operation.setSessionMgr(mgr);
				operation.run();
				try{
					replayPage = (String) operation.getReplyPage();
				}catch(TranFailException e){
					replayPage="";
				}
				try{
					opData.setValueAt("ReplyPage",replayPage);
				}catch(CTEObjectNotFoundException e){
					opData.addElement("ReplyPage","");
				}
				
			} catch (Exception ex) {
				throw ex;
			} finally {
				CmisConstance.resetRuningStatus(opName,opData);
			}
			processReply(channelContext);
		} catch (Exception e) {
			handleException(channelContext, e);
		}
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>����OpName����Operation���� </p>
	 * @param opName
	 * @return
	 * @throws TranFailException
	 * @throws Exception
	 *  
	 */
	private Class createOperation(String opName)
		throws TranFailException, Exception {

		Class c = null;
		String opPackage = "";
		String tmpOpName = "";
		opName = opName.trim();
		c = (Class) CmisConstance.getOperationClass(opName);

		if (c == null && opName.indexOf(".") == -1) {
			opPackage =(String) CmisConstance.getParameterSettings().get("operationPackages");
			StringTokenizer toke = new StringTokenizer(opPackage, "|");
			while (toke.hasMoreElements()) {
				String tmpStr =
					((String) toke.nextElement()).trim() + "." + opName;
				c = (Class) CmisConstance.getOperationClass(tmpStr);
				if (c == null) {
					continue;
				} else {
					tmpOpName = tmpStr;
					break;
				}
			}
		} else {
			tmpOpName = opName;
		}

		if (c != null) {
			return c;
		} else {
			Exception ecl = null;
			try {
				c = (Class) Class.forName(opName);
				if (c != null)
					tmpOpName = opName;
			} catch (ClassNotFoundException e) {
				ecl = e;
			}
			if ((ecl != null || c == null) && opName.indexOf(".") == -1) {
				c = null;
				opPackage =
					(String) CmisConstance.getParameterSettings().get(
						"operationPackages");
				StringTokenizer toke = new StringTokenizer(opPackage, "|");
				while (c == null && toke.hasMoreTokens()) {
					try {
						String tmpStr =
							((String) toke.nextElement()).trim() + "." + opName;
						c = (Class) Class.forName(tmpStr);
						if (c != null)
							tmpOpName = tmpStr;
					} catch (ClassNotFoundException ee) {
					}
				}
			}
			if (c == null) {
				icbc.cmis.base.Trace.trace("CmisServiceReqHandler",0,0,"","OperatinName:" + opName);
				throw new TranFailException("",
					"ServiceHandler.createOperation(String opName)",
					"δ�ҵ����״���ģ��["
						+ opName
						+ "] in packages ["
						+ opPackage
						+ "]","δ���ҵ����׺Ŷ�Ӧ�Ľ�����");
			}
			try {
				icbc.cmis.base.Trace.trace("CmisServiceReqHandler",0,0,"","OperatinName:" + tmpOpName);
				CmisConstance.addCmisOperation(tmpOpName, c);
				return c;
			} catch (Exception ex) {
				throw new TranFailException(
					"",
					"ServiceHandler.createOperation(String opName)",
					ex.getMessage(),"��������ʧ��");
			}
		}
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�����Ự���� </p>
	 * @param channelContext
	 * @throws TranFailException
	 *  
	 */
	private  void parseSession(ChannelContext channelContext)throws TranFailException{
		Hashtable sessionData=new Hashtable();
		try {
			KeyedDataCollection data=channelContext.getRequestData();
			sessionData.put("AuthId",data.getValueAt("AuthId"));
			sessionData.put("VerifyStr",data.getValueAt("VerifyStr"));
			sessionData.put("SysID",data.getValueAt("SysID"));
			channelContext.setChannelSession(sessionData);
		} catch (CTEObjectNotFoundException e) {
			throw new TranFailException("","ServiceHandler.parseSession",e.getMessage(),"��ȡ�Ự��Ϣ�������´���"+e.getErrorMsgDesc());
		} catch (CTEInvalidRequestException e) {
			throw new TranFailException("","ServiceHandler.parseSession",e.getMessage(),"��ȡ�Ự��Ϣ�������´���"+e.getErrorMsgDesc());
		} catch (Exception e) {
			throw new TranFailException("","ServiceHandler.parseSession",e.getMessage(),"��ȡ�Ự��Ϣ����!");
		}
	}

	/**
	 * <b>��������: </b><br>
	 * <p>��¼��ϵͳ���ýӿڵ���־����20041125</p>
	 * @param channelContext
	 * @throws TranFailException
	 *  
	 */
	private  String logData(ChannelContext channelContext)throws TranFailException{
		Hashtable logData=new Hashtable();
		String retSeq = "";
		try {
//			System.out.print("Begin log 2!");
			HttpServletRequest req=(HttpServletRequest)channelContext.getChannelRequest();
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(req.getRequestURI()+"?");
			
			
			//String url = req.getRequestURI()+"?";
			for (java.util.Enumeration enumeration = req.getParameterNames();
				enumeration.hasMoreElements();) {
				String attrName = (String) enumeration.nextElement();
				String attrValue[] =
					(String[]) req.getParameterValues(attrName);

				if (attrValue == null) {
					attrValue = new String[1];
					attrValue[0] = "";
				}
				for (int i = 0; i < attrValue.length; i++) {
					attrValue[i] = icbc.cmis.util.Decode.decode(attrValue[i]);
					
					strBuffer.append(attrName + "=" +attrValue[i] +"&");
				}
			}			
			KeyedDataCollection data=channelContext.getRequestData();
			
			String channelType = channelContext.getDeviceType();
			String SerialNo = (String)data.getValueAt("SerialNo"); //�������к�
			String TransNo  = (String)data.getValueAt("TransNo");  //����ID��
			String AuthId   = (String)data.getValueAt("AuthId");   //�����Ա��
			String AreaCode = (String)data.getValueAt("AreaCode"); //�����Ա��
			String SysID = (String)data.getValueAt("SysID");       //����Ӧ�ú�
			
			logData.put("SerialNo",SerialNo);
			logData.put("TransNo",TransNo);
			logData.put("AuthId",AuthId);
			logData.put("AreaCode",AreaCode);
			logData.put("SysID",SysID);
			logData.put("RvData",strBuffer.toString());
			logData.put("channelType",channelType);
			
			ServiceHandlerDAO dao = new ServiceHandlerDAO(new icbc.cmis.operation.DummyOperation());
			 retSeq = dao.logInServerInfo(logData);
//			sessionData.put("AuthId",data.getValueAt("AuthId"));
//			sessionData.put("VerifyStr",data.getValueAt("VerifyStr"));
//			sessionData.put("SysID",data.getValueAt("SysID"));
//			channelContext.setChannelSession(sessionData);
			return retSeq;
		} catch (CTEObjectNotFoundException e) {
			throw new TranFailException("","ServiceHandler.parseSession",e.getMessage(),"��ȡ�Ự��Ϣ�������´���"+e.getErrorMsgDesc());
		} catch (CTEInvalidRequestException e) {
			throw new TranFailException("","ServiceHandler.parseSession",e.getMessage(),"��ȡ�Ự��Ϣ�������´���"+e.getErrorMsgDesc());
		} catch (Exception e) {
			throw new TranFailException("","ServiceHandler.parseSession",e.getMessage(),"��ȡ�Ự��Ϣ����!");
		}
	}

	
	/**
	 * <b>��������: </b><br>
	 * <p>�Խ����������Ԥ���� </p>
	 * @param channelContext
	 * @throws TranFailException
	 *  
	 */
	private void preProcessRequest(ChannelContext channelContext)throws TranFailException,Exception{
		
		try {
			ChannelDriver channelDriver=(ChannelDriver)channelContext.getChannelDriver();
			//������������
			KeyedDataCollection reqData=channelDriver.parseRequestData(channelContext);
			
			//����ҳ���ױ�־
			if(reqData.isElementExist("TurnPage")){
				java.text.DecimalFormat fmt=new java.text.DecimalFormat("#");
				String turnPageFlag=(String)reqData.getValueAt("TurnPage");
//				if(turnPageFlag.equals("0")){
//					reqData.setValueAt("BeginRow","1");
//					reqData.setValueAt("EndRow",new Integer(CM2002Service.getTurnPageMax()).toString());
//				}else{
//					long beginRow=Long.valueOf((String)reqData.getValueAt("BeginRow")).longValue();
//					long endRow=Long.valueOf((String)reqData.getValueAt("EndRow")).longValue();
//					if(turnPageFlag.equals("1")){

					reqData.setValueAt("BeginRow",(String)reqData.getValueAt("BeginRow"));
					reqData.setValueAt("EndRow",(String)reqData.getValueAt("EndRow"));

//					}else{
//						if(turnPageFlag.equals("-1")){
//							if(beginRow-CM2002Service.getTurnPageMax()<=0){
//								reqData.setValueAt("BeginRow","1");
//								reqData.setValueAt("EndRow",fmt.format(new Long(CM2002Service.getTurnPageMax()).longValue()));
//							}else{
//								reqData.setValueAt("BeginRow",fmt.format(new Long(beginRow-CM2002Service.getTurnPageMax()+1).longValue()));
//								reqData.setValueAt("EndRow",fmt.format(new Long(beginRow).longValue()));
//							}
//						}
//					}
//				}
				//����Ĭ������ʽ
				if(!reqData.isElementExist("Acs")){
					reqData.addElement("Acs","0");
				}
			}
			channelContext.setRequestData(reqData);
			//�������׻Ự����
			parseSession(channelContext);
		} catch (CTEObjectNotFoundException e) {
			throw new TranFailException("","ServiceHandler.preProcessRequest",e.getMessage(),"������ʱ�������´���"+e.getErrorMsgDesc());
		} catch (CTEInvalidRequestException e) {
			throw new TranFailException("","ServiceHandler.preProcessRequest",e.getMessage(),"������ʱ�������´���"+e.getErrorMsgDesc());
		} finally {
			
		}
	}
	

}
