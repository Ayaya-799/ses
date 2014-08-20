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

package icbc.cmis.service.channel.mq;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.mq.MQMessage;

import icbc.cmis.base.CTEInvalidRequestException;
import icbc.cmis.base.CTEObjectExistingException;
import icbc.cmis.base.KeyedDataCollection;
import icbc.cmis.base.TranFailException;
import icbc.cmis.base.XMLReader;
import icbc.cmis.service.MQConnectionPoolService;
import icbc.cmis.service.channel.CM2002Service;
import icbc.cmis.service.channel.CMISChannelContext;
import icbc.cmis.service.channel.ChannelContext;
import icbc.cmis.service.channel.ChannelDriver;
import icbc.cmis.service.channel.Handler;


/**
 * 
 * <b>��������: </b> 2004-10-22<br>
 * <b>����: </b><br>
 * <b>������: </b><br>
 * MQ�������״��������������������ɶ������̸߳���������������<br>
 * <p>Copyright: Copyright (c)2004</p>
 * <p>Company: </p>
 * 
 * @author Administrator
 * 
 * @version 
 * 
 * @since 
 * 
 * @see 
 * 
 */

public class MQChannelDriver implements Runnable, ChannelDriver {
	
	/**���յ���MQ��Ϣ*/
	MQMessage message=null;
	/**��Ϣ����Դ*/
	String mqResource=null;
	/**��Ϣ��Դ�ļ�����*/
	MQChannelListener listener=null;
	
	public MQChannelDriver(MQMessage message,String mqResource,MQChannelListener listener){
		this.message=message;
		this.mqResource=mqResource;
		this.listener=listener;
	}
	/** 
	 * <b>��������: </b><br>
	 * <p>�߳̽ӿ�ʵ�ַ���	</p>
	 * @see java.lang.Runnable#run()
	 * 
	 * 
	 */
	public void run() {
		Handler requestHandler=null;
		ChannelContext channelContext=null;
		try {
			channelContext=creatChannelContext(message,mqResource,this);
			requestHandler=CM2002Service.createHanderler(channelContext);
			requestHandler.processRequest(channelContext);
		} catch (Exception e) {
			requestHandler.handleException(channelContext,e);
		}finally{
			listener.subChannelHandlers();
		}
		
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>���������������ݷ�����ʵ��Driver�ӿڷ���</p>
	 * @see icbc.cmis.service.channel.ChannelDriver#parseRequestData(java.lang.Object)
	 * @param channelRequest
	 * @return
	 * @throws Exception 
	 * 
	 */
	public KeyedDataCollection parseRequestData(ChannelContext channelContext) throws TranFailException {
		MQMessage message=(MQMessage)channelContext.getChannelRequest();
		KeyedDataCollection reqData=null;
		int len;
		byte[] recMsg=null;
		try {
			len = message.getMessageLength();
			if (len <= 0){
				throw new TranFailException("", "MQChannelDriver.parseRequestData(ChannelContext channelContext)", 
					"","���յ����������ݰ���Ч!");
			}
			recMsg = new byte[len];
			message.readFully(recMsg);
		} catch (IOException e) {
			throw new TranFailException("","MQChannelDriver.parseRequestData(ChannelContext channelContext)",
						e.getMessage(),"��������������Ϣ�ж�ȡ��ȡ����ʧ��!");
		}
		
		String transNo=null;
		transNo = getTransNo(new String(recMsg));
		try{
			String fmtDefName=CM2002Service.getOperationReqFormat(transNo);
			reqData=CM2002Service.unformat(new String(recMsg),fmtDefName);
		}catch (TranFailException e){
			throw new TranFailException("","MQChannelDriver.parseRequestData(ChannelContext channelContext)",
				e.getDisplayMessage(),"���������ݰ��н�������ʱ�����쳣");
		}
		try {
			reqData.addElement("ServerAddr",java.net.InetAddress.getLocalHost().getHostAddress());
			reqData.addElement("RemoteAddr","");
			reqData.addElement("operationName",CM2002Service.getOperationName(transNo));
			reqData.addElement("TransNo",transNo);
		} catch (CTEInvalidRequestException e) {
			throw new TranFailException("","MQChannelDriver.parseRequestData(ChannelContext channelContext)",
							e.getMessage(),"���������ݰ��н�������ʱ�����쳣��"+e.getErrorMsgDesc());
		} catch (CTEObjectExistingException e) {
			throw new TranFailException("","MQChannelDriver.parseRequestData(ChannelContext channelContext)",
				e.getMessage(),"���������ݰ��н�������ʱ�����쳣��"+e.getErrorMsgDesc());
		} catch (UnknownHostException e) {
		}
		return reqData;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�������������еĽ��״��� </p>
	 * @param reqMessage
	 * @return
	 * @throws Exception
	 *  
	 */
	private String getTransNo(String reqMessage) throws TranFailException{
		XMLReader xmlReader=new XMLReader();
		String transNo=null;
		try{
			xmlReader.loadXMLContentNew(reqMessage);
			Node node=xmlReader.getNode("CM2002");
			if(node!=null){
				NodeList list = node.getChildNodes();
				for (int i = 0; i < list.getLength(); i++) {
					Node tmpNode = list.item(i);
					if (isElementNode(tmpNode)) {
						if(tmpNode.getNodeName().equals("pub")){
							NodeList pubList=tmpNode.getChildNodes();
							for(int j=0;j<pubList.getLength();j++){
								Node anode=pubList.item(j);
								if(isElementNode(anode)){
									if(anode.getNodeName().equals("TransNo")){
										NodeList anodeList=anode.getChildNodes();
										transNo=anodeList.item(0).getNodeValue();
										break;
									}
								}
							}
							break;
						}
					}
				}
			}else{
				throw new TranFailException("00001","MQChannelDriver.getTransNo","","�Ƿ���CM2002�������ݰ�");
			}
		} catch (Exception e) {
			throw new TranFailException("","MQChannelDriver.getTransNo",e.getMessage(),"����MQǿ�����ݰ��еĽ��׺���Ϣ����");
		}finally{
			return transNo;
		}
		
		
		
		
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param aNode
	 * @return
	 *  
	 */
	private boolean isElementNode(Node aNode){
		if (aNode.getNodeType() == Node.ELEMENT_NODE)
			return true;
		else
			return false;
	}
	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelDriver#creatChannelContext(java.lang.Object, java.lang.Object, java.lang.Object)
	 * @param req
	 * @param resp
	 * @param driver
	 * @return
	 * @throws Exception
	 * 
	 */
	public ChannelContext creatChannelContext(Object req, Object resp, Object driver) throws Exception {
		ChannelContext channelContext= new CMISChannelContext(req,resp,driver);
		channelContext.setDeviceType("MQ");
		return channelContext;
	}
}
