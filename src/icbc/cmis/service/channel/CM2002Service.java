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

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import icbc.cmis.base.CTEInvalidRequestException;
import icbc.cmis.base.CTEObjectExistingException;
import icbc.cmis.base.CTEObjectNotFoundException;
import icbc.cmis.base.CTEUncausedException;
import icbc.cmis.base.CmisConstance;
import icbc.cmis.base.FormatElement;
import icbc.cmis.base.KeyedDataCollection;
import icbc.cmis.base.TranFailException;
import icbc.cmis.base.XMLReader;
import icbc.cmis.service.MQConnectionPoolService;
import icbc.cmis.service.MQResource;
import icbc.cmis.service.QManagerConnectionManager;
import icbc.cmis.service.channel.http.CmisServiceHttpReqHandler;
import icbc.cmis.service.channel.mq.CmisServiceMQReqHandler;
import icbc.cmis.service.channel.mq.MQChannelListener;

/**
 * 
 * <b>��������: </b> 2004-10-21<br>
 * <b>����: </b><br>
 * <b>������: </b><br>
 * ������CM2002�������ӿڵ�ʵ���࣬��Ҫ����洢����ӿڵ�������Ϣ���ṩ<br>
 * ���߷�����������ͣ�����÷���ķ�����
 * 
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

public class CM2002Service {
	
	/**���׸�ʽ�����ļ���ȡ��*/
	private static XMLReader xmlReader=null;
	
	/**CM2002����ӿ��Ƿ�����*/
	private static boolean serviceEnabled=false;
	
	/**MQ���ӻ���ط���*/
	private static MQConnectionPoolService mqConnectionPoolService=null;
	
	/**ͨ������*/
	private static Hashtable settings=new Hashtable();
	
	/**�����������׶�Ӧ��ϵ*/
	private static Hashtable transMap=new Hashtable();
	
	/**�����������׸�ʽ���õĶ�Ӧ��ϵ*/
	private static Hashtable transFmtMap=new Hashtable();
	
	/**Ӧ�÷�����MQ�������ͬʱ������*/
	private static int maxMQServieceThread=0;
	
	/**���������б�*/
	private static Hashtable channelListeners=new Hashtable();
	
	private CM2002Service(){
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>CM2002����ӿڣ��÷�����KeyedDataCollection ��Ԥ�ȶ���ĸ�ʽ��<br>
	 * ��ʽ��ΪXML���ݰ� </p>
	 * @param data
	 * @param fmtDefName
	 * @return
	 * @throws Exception
	 *  
	 */
	public static String format(KeyedDataCollection data ,String fmtDefName) 
			throws TranFailException{
		FormatElement fmtElmt;
		String formatedData=null;
		try {
			fmtElmt = getFormatElement(fmtDefName);
		} catch (Exception e) {
			throw new TranFailException("","CM2002Service.format()",e.getMessage(),
				"��ȡ��ʶΪ��"+fmtDefName+" �����ݸ�ʽ�������");
		}
		try {
			formatedData=fmtElmt.format(data);
		} catch (CTEUncausedException e) {
			throw new TranFailException("","CM2002Service.format()",e.getErrorMsgDesc(),e.getErrorMsgDesc());
		} catch (CTEObjectExistingException e) {
			throw new TranFailException("","CM2002Service.format()",e.getErrorMsgDesc(),e.getErrorMsgDesc());
		} catch (CTEObjectNotFoundException e) {
			throw new TranFailException("","CM2002Service.format()",e.getErrorMsgDesc(),e.getErrorMsgDesc());
		} catch (CTEInvalidRequestException e) {
			throw new TranFailException("","CM2002Service.format()",e.getErrorMsgDesc(),e.getErrorMsgDesc());
		} finally {
			return formatedData;
		}
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>CM2002����ӿڣ���XML���ݰ����з���ʽ������ </p>
	 * @param fmtDefName
	 * @param formatedString
	 * @return
	 * @throws Exception
	 *  
	 */
	public static KeyedDataCollection unformat(String data,String fmtDefName)
			throws TranFailException{
		FormatElement fmtElement=null;
		try {
			fmtElement = getFormatElement(fmtDefName);
		} catch (Exception e1) {
			new TranFailException("","CM2002Service.unformat()",e1.getMessage(),
				"��ȡ��ʶΪ��"+fmtDefName+" �����ݸ�ʽ�������");
		}
		KeyedDataCollection kCol=new KeyedDataCollection();
		try {
			kCol=(KeyedDataCollection) fmtElement.unformat(data,kCol);
		} catch (CTEUncausedException e) {
			throw new TranFailException("","CM2002Service.unformat()",e.getErrorMsgDesc(),e.getErrorMsgDesc());
		} catch (CTEObjectExistingException e) {
			
		} catch (CTEObjectNotFoundException e) {
			throw new TranFailException("","CM2002Service.unformat()",e.getErrorMsgDesc(),e.getErrorMsgDesc());
		} catch (CTEInvalidRequestException e) {
			throw new TranFailException("","CM2002Service.unformat()",e.getErrorMsgDesc(),e.getErrorMsgDesc());
		} finally {
			return kCol;
		}
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�÷�����Ҫ����CM2002����ĳ�ʼ���������洢����ӿڵ�������Ϣ </p>
	 * @param aNode
	 * @throws Exception
	 *  
	 */
	public static void initCM2002Service(Node aNode)throws Exception{
		NodeList list = aNode.getChildNodes();
		int maxCon = 10;
		for (int i = 0; i < list.getLength(); i++) {
			Node tmpNode = list.item(i);
			if (isElementNode(tmpNode)) {
				if (tmpNode.getNodeName().equals("CM2002MQService")) {
					manageMQ(tmpNode);
				}else{
					if (isElementNode(tmpNode)) {
						NamedNodeMap map = tmpNode.getAttributes();
						CM2002Service.getSettings().put(
							map.getNamedItem("id").getNodeValue(),
							map.getNamedItem("value").getNodeValue());
					}
				}
			}
		}
		xmlReader=new XMLReader();
		String fmtDefFile =
			(String) CM2002Service.getSettings().get("TranscfgFile");
		String rootPath =
			(String) CmisConstance.getParameterSettings().get("fileRootPath");
		if (rootPath.endsWith(System.getProperty("file.separator")))
			fmtDefFile = rootPath + fmtDefFile;
		else
			fmtDefFile =rootPath
				+ System.getProperty("file.separator")
				+ fmtDefFile;
		xmlReader.loadXMLFile(fmtDefFile);
		initTransMap();
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�÷������CM2002����ӿڣ�MQ����,MQ���ӻ���صĳ�ʼ��</p>
	 * @param aNode
	 *  
	 */
	private static void manageMQ(Node aNode){
		MQConnectionPoolService mqPool = new MQConnectionPoolService();
		NamedNodeMap nameMap=aNode.getAttributes();
		CM2002Service.maxMQServieceThread=Integer.valueOf(
						(String)nameMap.getNamedItem("maxServiceThread").getNodeValue()).intValue();		
		NodeList list = aNode.getChildNodes();
		int maxCon = 10;
		for (int i = 0; i < list.getLength(); i++) {
			Node tmpNode = list.item(i);
			if (isElementNode(tmpNode)) {
				if (tmpNode.getNodeName().equals("QManager")) {
					QManagerConnectionManager qMgr =
						new QManagerConnectionManager();
					NamedNodeMap map = tmpNode.getAttributes();
					qMgr.qmanagerName =
						(String) map
							.getNamedItem("QMgrName")
							.getNodeValue();
					qMgr.channelName =
						(String) map.getNamedItem("channel").getNodeValue();
					qMgr.charSet =
						Integer
							.valueOf(
								map.getNamedItem("charSet").getNodeValue())
							.intValue();
					qMgr.hostName =
						(String) map
							.getNamedItem("hostName")
							.getNodeValue();
					qMgr.port =
						Integer
							.valueOf(
								map.getNamedItem("port").getNodeValue())
							.intValue();
					maxCon =
						Integer
							.valueOf(
								map
									.getNamedItem("maxConnections")
									.getNodeValue())
							.intValue();
					qMgr.maxConnections = maxCon;
					mqPool.addQManager(qMgr);
				} else if (tmpNode.getNodeName().equals("MQResource")) {
					MQResource rs = new MQResource();
					NamedNodeMap map = tmpNode.getAttributes();
					rs.setQManagerName(
						map.getNamedItem("QMgrName").getNodeValue());
					rs.setSendToQ(
						map.getNamedItem("sendToQ").getNodeValue());
					rs.setReplyToQ(
						map.getNamedItem("replyToQ").getNodeValue());
					rs.setPutMessageOptions(
						map
							.getNamedItem("PutMessageOptions")
							.getNodeValue());
					rs.setGetMessageOptions(
						map
							.getNamedItem("getMessageOptions")
							.getNodeValue());
					rs.setSendToQOpenOptions(
						map
							.getNamedItem("sendToQOpenOptions")
							.getNodeValue());
					rs.setReplyToQOpenOptions(
						map
							.getNamedItem("replyToQOpenOptions")
							.getNodeValue());
					rs.setTimeOut(
						map.getNamedItem("timeout").getNodeValue());
					rs.setResourceID(
						map.getNamedItem("resourceID").getNodeValue());
					rs.setMaxConnections(maxCon);
					mqPool.addMQResource(rs);
				}
			}
		}
		mqConnectionPoolService=mqPool;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�÷������CM2002�������ӿڣ����׺�ӳ��������Ϣ�ĳ�ʼ��������<br>
	 * �����뵽�������ӳ��ͽ����뵽�������ݸ�ʽ����ӳ�� </p>
	 * @throws Exception
	 *  
	 */
	private static void initTransMap() throws Exception{
		XMLReader xmlReader=new XMLReader();
		String fmtDefFile =
			(String) CM2002Service.getSettings().get("TransMapFile");
		String rootPath =
			(String) CmisConstance.getParameterSettings().get("fileRootPath");
		if (rootPath.endsWith(System.getProperty("file.separator")))
			fmtDefFile = rootPath + fmtDefFile;
		else
			fmtDefFile =rootPath
				+ System.getProperty("file.separator")
				+ fmtDefFile;
		xmlReader.loadXMLFile(fmtDefFile);
		Node node = xmlReader.getNode("TransCfg");
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node tmpNode = list.item(i);
			if (isElementNode(tmpNode)) {
				if(tmpNode.getNodeName().equals("TransMap")){
					readTransMapSettings(tmpNode,transMap);
				}else{
					if(tmpNode.getNodeName().equals("TransFmtMap"))
						readTransMapSettings(tmpNode,transFmtMap);
				}
			}
		}
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�÷�����ɽ���ӳ��������Ϣ�Ķ�ȡ </p>
	 * @param node
	 * @param mapContainer
	 *  
	 */
	private static void readTransMapSettings(Node node,Hashtable mapContainer ){
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node tmpNode = list.item(i);
			if (isElementNode(tmpNode)) {
				NamedNodeMap map = tmpNode.getAttributes();
				mapContainer.put(
					map.getNamedItem("id").getNodeValue(),
					map.getNamedItem("value").getNodeValue());
			}
		}
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param tmpNode
	 * @return
	 *  
	 */
	private static boolean isElementNode(Node aNode) {
		if (aNode.getNodeType() == Node.ELEMENT_NODE)
			return true;
		else
			return false;
	}

	/**
	 * <b>��������: </b><br>
	 * <p>�÷�����������CM2002����ӿ�</p>
	 * 
	 *  
	 */
	public static boolean startService(){
		
		if(((String)CM2002Service.getSettings().get("MQEnabled")).equalsIgnoreCase("true")){
			Enumeration enumeration=CM2002Service.mqConnectionPoolService.getMqResources().keys();
			for(;enumeration.hasMoreElements();){
				String mqResourceName=(String)enumeration.nextElement();
				Thread mqListener=new MQChannelListener(mqResourceName,CM2002Service.mqConnectionPoolService);
				CM2002Service.channelListeners.put(mqResourceName,mqListener);
				mqListener.start();
			}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�÷�������ֹͣCM2002����ӿ� </p>
	 * 
	 *  
	 */
	public static boolean stopService(){
		
		if(((String)CM2002Service.getSettings().get("MQEnabled")).equalsIgnoreCase("true")){
			Enumeration mqResourceNames=CM2002Service.channelListeners.keys();
			for(;mqResourceNames.hasMoreElements();){
				String resourceName=(String)mqResourceNames.nextElement();
				MQChannelListener mqListener=(MQChannelListener)CM2002Service.channelListeners.get(resourceName);
				mqListener.stopListener();
				CM2002Service.channelListeners.remove(resourceName);
			}
		}
		return true;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�÷����������÷��� </p>
	 * 
	 *  
	 */
	public static boolean resetService(){
		if(stopService()){
			channelListeners=new Hashtable(); 
			if(startService())
				return true;
			else
				return false;
		}else
			return false;
	}
	/**
	 * <b>��������: </b><br>
	 * <p>��ȡCM2002����ӿ����ò����� </p>
	 * @return
	 *  
	 */
	public static Hashtable getSettings() {
		return settings;
	}
	

	/**
	 * <b>��������: </b><br>
	 * <p>���ո�ʽ�����ʶ����ȡ��ʽ������ </p>
	 * @param fmtDefName
	 * @return
	 * @throws Exception
	 *  
	 */
	private static FormatElement getFormatElement(String fmtDefName)
		throws Exception {
		return (FormatElement) xmlReader.readFormatElement(fmtDefName);
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�÷��������������ʹ��������Ĵ�����</p>
	 * @param channelContext
	 * @return
	 *  
	 */
	public static Handler createHanderler(ChannelContext channelContext)throws TranFailException{
		String channelDeviceType=null;
		try{
			channelDeviceType=channelContext.getDeviceType();
			if(channelDeviceType.equalsIgnoreCase("HTTP")){
				return (Handler)Class.forName((String)CM2002Service.getSettings().get("HttpHandler")).newInstance();
			}else{
				if(channelDeviceType.equalsIgnoreCase("MQ")){
					return (Handler)Class.forName((String)CM2002Service.getSettings().get("MQHandler")).newInstance();
				}
			}
		} catch (InstantiationException e) {
			throw new TranFailException("","CM2002Service.createHanderler",e.getMessage(),
						"����"+channelDeviceType+"����������ʧ��!");
		} catch (IllegalAccessException e) {
			throw new TranFailException("","CM2002Service.createHanderler",e.getMessage(),
						"����"+channelDeviceType+"����������ʧ��!");
		} catch (ClassNotFoundException e) {
			throw new TranFailException("","CM2002Service.createHanderler",e.getMessage(),
						"����"+channelDeviceType+"����������ʧ��!");
		}
		return null;
	}
	/**
	 * <b>��������: </b><br>
	 * <p>��ȡ���׺ŵ����׵�ӳ��� </p>
	 * @return
	 *  
	 */
	public static Hashtable getTransMap() {
		return transMap;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public static int getMaxMQServieceThread() {
		return maxMQServieceThread;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public static boolean isEnabled(){
		return serviceEnabled;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param option
	 *  
	 */
	public static void setEnabled(boolean option){
		serviceEnabled=option;
	}
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public static MQConnectionPoolService getMqConnectionPoolService() {
		return mqConnectionPoolService;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�Դ�����Ϣ����Exception���д����� </p>
	 * @param e
	 * @return
	 *  
	 */
	public static String handleException2(String reqSerialNo,String transCode,Exception e){
		String errCode=null;
		String errMsg=null;
		
		if(e instanceof TranFailException){
			errCode=((TranFailException)e).getErrorCode();
			errMsg=((TranFailException)e).getDisplayMessage(errCode);
		}else{
			errCode="δ֪������";
			errMsg="�ô�����Ϣδ��ָ����������CM2002ϵͳ����Ա��ϵ";
		}
		StringBuffer strBuffer=new StringBuffer();
		strBuffer.append("<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?>");
		strBuffer.append("<CM2002Err><SerialNo>"+reqSerialNo+"</SerialNo><TransNo>"+transCode+"</TransNo>");
		strBuffer.append("<Error><ErrNo>"+errCode+"</ErrNo><ErrMsg>"+errMsg+"</ErrMsg></Error></CM2002Err>");
		return strBuffer.toString();
		
	}
	/**
	 * <b>��������: </b><br>
	 * <p>�Դ�����Ϣ����Exception���д����� </p>
	 * @param e
	 * @return
	 *  
	 */
	public static String handleException(String reqSerialNo,String transCode,Exception e,String reqSeq){
		String errCode=null;
		String errMsg=null;
		if(e instanceof TranFailException){
			errCode=((TranFailException)e).getErrorCode();
			errMsg=((TranFailException)e).getDisplayMessage(errCode);
		}else{
			errCode="δ֪������";
			errMsg="�ô�����Ϣδ��ָ����������CM2002ϵͳ����Ա��ϵ";
		}
		//20041125 chenj	add for record log;
		Hashtable  upData = new Hashtable();
		upData.put("reqSeq",reqSeq);
		upData.put("errCode",errCode);
		upData.put("errMsg",errMsg);
		try{
			ServiceHandlerDAO dao = new ServiceHandlerDAO(new icbc.cmis.operation.DummyOperation());
			dao.logInServerInfoUpdate(upData);
		}
		catch(Exception e2){
		}
		//add end
		
		
		StringBuffer strBuffer=new StringBuffer();
		strBuffer.append("<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?>");
		strBuffer.append("<CM2002><pub><TransNo>"+transCode+"</TransNo>");
		strBuffer.append("<SN>"+reqSerialNo+"</SN><TN>"+transCode+"</TN><TS>0</TS><BR>0</BR><ER>0</ER><RC>0</RC><ErrCode>"+errCode+"</ErrCode><ErrMsg>"+errMsg+"</ErrMsg></pub><out></out></CM2002>");
		//<SN>1</SN><TN>37001</TN><TS>1</TS><BR>1</BR><ER>2</ER><RC>6</RC><ErrCode>00000000</ErrCode><ErrMsg>ok</ErrMsg>
		return strBuffer.toString();

		
	}
	

	
	
	/**
	 * <b>��������: </b><br>
	 * <p>��ȡ��ҳ������󷵻ؼ�¼�� </p>
	 * @return
	 *  
	 */
	public static int getTurnPageMax(){
		String turnPageMax=(String)CM2002Service.getSettings().get("TurnPageMax");
		return Integer.valueOf(turnPageMax).intValue();
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>���ݽ��׺Ż�ȡ�������� </p>
	 * @param transNo
	 * @return
	 *  
	 */
	public static String getOperationName(String transNo){
		return (String)transMap.get(transNo);
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>���ݽ��׺Ż�ȡ�����������ݰ��ĸ�ʽ�������� </p>
	 * @param transNo
	 * @return
	 *  
	 */
	public static String getOperationReqFormat(String transNo){
		return (String)transFmtMap.get(transNo+"IN");
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>���ݽ��׺Ż�ȡ���׷������ݰ��ĸ�ʽ���� </p>
	 * @param transNo
	 * @return
	 *  
	 */
	public static String getOperationRepFormat(String transNo){
		return (String)transFmtMap.get(transNo+"OUT");
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>��ȡCM2002����ӿ���֤�ӿ� </p>
	 * @return
	 * @throws TranFailException
	 *  
	 */
	public static IDAuthenticate getIDAuthenticate() throws TranFailException{
		
		IDAuthenticate IDAuthenticateInterface=null;
		try {
			String IDAuthenticateName=(String)CM2002Service.getSettings().get("IDAuthenticate");
			if(IDAuthenticateInterface!=null && !IDAuthenticateInterface.equals(""))
				return (IDAuthenticate)Class.forName(IDAuthenticateName).newInstance();
		} catch (InstantiationException e) {
			throw new TranFailException("","CM2002Service.getIDAuthenticate",e.getMessage(),
						"�������ػ���֤�ӿ�ʧ��!");
		} catch (IllegalAccessException e) {
			throw new TranFailException("","CM2002Service.getIDAuthenticate",e.getMessage(),
						"�������ػ���֤�ӿ�ʧ��!");
		} catch (ClassNotFoundException e) {
			throw new TranFailException("","CM2002Service.getIDAuthenticate",e.getMessage(),
						"�������ػ���֤�ӿ�ʧ��!");
		}
		return IDAuthenticateInterface;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param e
	 * @return
	 *  
	 */
	private String getExceptionStackMsg(Exception e) {
		try {
			if (e == null)
				return "";
			java.io.StringWriter stringWriter = new java.io.StringWriter();
			java.io.PrintWriter print = new java.io.PrintWriter(stringWriter);
			e.printStackTrace(print);
			return new String(stringWriter.getBuffer());
		} catch (Exception ee) {
			return e.toString();
		}
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param e
	 * @return
	 *  
	 */
	private String getThrowableStackMsg(Throwable e) {
		try {
			if (e == null)
				return "";
			java.io.StringWriter stringWriter = new java.io.StringWriter();
			java.io.PrintWriter print = new java.io.PrintWriter(stringWriter);
			e.printStackTrace(print);
			return new String(stringWriter.getBuffer());
		} catch (Exception ee) {
			return e.toString();
		}
	}

}
