////////////////////////////////////////////////////////////////////////////
//Copyright (C) 2006 ICBC
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

package icbc.cmis.service.channel.ipcgw;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import icbc.cmis.base.*;
/*************************************************************
 * 
 * <b>��������: </b> 2006-7-14<br>
 * <b>����: </b><br>
 * <b>������: </b><br>
 * <br>
 * <p>Copyright: Copyright (c)2006</p>
 * <p>Company: </p>
 * 
 * @author ZJFH-wanglx
 * 
 * @version 
 * 
 * @since 
 * 
 * @see 
 * 
 *************************************************************/

public class IPCGWClientService {

  private static WorkThread workThreads[];
  public static Hashtable ht_trades = new Hashtable(); //�����ֶζ����
  public static String trace_or_not; //�Ƿ����ü��,false����أ�true����쳣��all�������
  /**
   * <b>���캯��</b><br>
   * 
   */
  public IPCGWClientService() {
    workThreads = null;
  }
  /**
   * <b>��������: </b><br>
   * <p> </p>
   * @param node
   * @throws Exception
   *  
   */
  public static void startService() throws Exception {
    int conNum = 1;
    long retryTime = 20L;
    int socketTimeout = 3000;
    int maxHandlers = 10; //����߳���
    ArrayList hostList = new ArrayList();
    String handlerClass = null;
    String receiverClass = null;
    String senderClass = null;
    Node rnode = null;
    Node snode = null;
    Node hnode = null;
    try {
      trace_or_not = (String)CmisConstance.getParameterSettings().get("IPCGWServiceTrace");
    }
    catch (Exception e) {
      trace_or_not = "false";
    }

    //���ȳ�ʼ����������
    //		////////////////////////////
    XMLReader xmlReader = new XMLReader();
    //���ȳ�ʼ��Ӧ�����������ļ�
    String rootPath = (String)CmisConstance.getParameterSettings().get("fileRootPath");
    String IPCGWServiceTrans = (String)CmisConstance.getParameterSettings().get("IPCGWServiceTrans");
    if (IPCGWServiceTrans == null || IPCGWServiceTrans.trim().length() == 0) {
      throw new Exception("��ȷ��DSR XML �ļ����������Ƿ���ȷ");
    }
    if (rootPath.endsWith(System.getProperty("file.separator"))) {
      IPCGWServiceTrans = rootPath + IPCGWServiceTrans;
    }
    else {
      IPCGWServiceTrans = rootPath + System.getProperty("file.separator") + IPCGWServiceTrans;
    }
    xmlReader.loadXMLFile(IPCGWServiceTrans);
    Node node0 = xmlReader.getNode("TRADES");
    NodeList childNodes0 = node0.getChildNodes();
    for (int i = 0; i < childNodes0.getLength(); i++) {
      Node nextNode = childNodes0.item(i);
      if (nextNode != null && nextNode.getNodeType() == Node.ELEMENT_NODE) {
        String tradeNo = nextNode.getAttributes().getNamedItem("id").getNodeValue().trim();
        NodeList nextChildNodes = nextNode.getChildNodes();
        Vector v_trade = new Vector();

        for (int j = 0; j < nextChildNodes.getLength(); j++) {
          Node nextChildNodeNextNode = nextChildNodes.item(j);
          if (nextChildNodeNextNode != null && nextChildNodeNextNode.getNodeType() == Node.ELEMENT_NODE) {
            String[] strTmp = new String[3];
            strTmp[0] = nextChildNodeNextNode.getAttributes().getNamedItem("tagName").getNodeValue().trim();
            strTmp[1] = nextChildNodeNextNode.getAttributes().getNamedItem("len").getNodeValue().trim();
			strTmp[2] = nextChildNodeNextNode.getAttributes().getNamedItem("type").getNodeValue().trim();
            v_trade.add(strTmp);
          }
        }
        ht_trades.put(tradeNo, v_trade);
      }
    }
    //Ȼ�����ʵʱ����ϵͳ�����ļ���ʼ��ip����ʵʱ��������ϵͳ
    String IPCGWService = (String)CmisConstance.getParameterSettings().get("IPCGWService");
    if (IPCGWService == null || IPCGWService.trim().length() == 0) {
      throw new Exception("��ȷ��DSR XML �ļ����������Ƿ���ȷ");
    }
    if (rootPath.endsWith(System.getProperty("file.separator"))) {
      IPCGWService = rootPath + IPCGWService;
    }
    else {
      IPCGWService = rootPath + System.getProperty("file.separator") + IPCGWService;
    }
    xmlReader.loadXMLFile(IPCGWService);
    Node node = xmlReader.getNode("GatewayClientService");

    //////////////////////////////////
    NamedNodeMap attrMap = node.getAttributes();
    try {
      socketTimeout = Integer.parseInt(attrMap.getNamedItem("socketTimeout").getNodeValue().trim());
    }
    catch (Exception e) {
      System.out.println("can't find socketTimeout in the config file!");
    }
    try {
      maxHandlers = Integer.parseInt(attrMap.getNamedItem("maxHandlers").getNodeValue().trim());
    }
    catch (Exception e) {
      System.out.println("can't find maxHandlers in the config file!");
    }
    try {
      retryTime = Integer.parseInt(attrMap.getNamedItem("retryTime").getNodeValue().trim());
    }
    catch (Exception e) {
      System.out.println("can't find retryTime in the config file!");
    }
    try {
      conNum = Integer.parseInt(attrMap.getNamedItem("conNum").getNodeValue().trim());
    }
    catch (Exception e) {
      System.out.println("can't find conNum in the config file!");
    }
    try {
      handlerClass = attrMap.getNamedItem("handlerClass").getNodeValue().trim();
    }
    catch (Exception e) {
      System.out.println("can't find handlerClass in the config file!");
      throw new Exception("can't find handlerClass in the config file!");
    }
    try {
      receiverClass = attrMap.getNamedItem("receiverClass").getNodeValue().trim();
    }
    catch (Exception e) {
      System.out.println("can't find receiverClass in the config file!");
      throw new Exception("can't find receiverClass in the config file!");
    }
    try {
      senderClass = attrMap.getNamedItem("senderClass").getNodeValue().trim();
    }
    catch (Exception e) {
      System.out.println("can't find senderClass in the config file!");
      throw new Exception("can't find senderClass in the config file!");
    }
    NodeList childNodes = node.getChildNodes();

    for (int i = 0; i < childNodes.getLength(); i++) {
      Node nextNode = childNodes.item(i);
      if (nextNode.getNodeType() == Node.ELEMENT_NODE) {
        if (nextNode != null && nextNode.getNodeName().trim().equals("Receiver")) {
          rnode = nextNode;
        }
        if (nextNode != null && nextNode.getNodeName().trim().equals("Sender")) {
          snode = nextNode;
        }
        if (nextNode != null && nextNode.getNodeName().trim().equals("host")) {
          NamedNodeMap hostMap = nextNode.getAttributes();
          try {
            String tmphost[] = new String[2];
            tmphost[0] = hostMap.getNamedItem("value").getNodeValue().trim();
            tmphost[1] = "0"; //0��ʾ������δ��������1��ʾ�������Ѿ�������
            hostList.add(tmphost);
          }
          catch (Exception e) {}
        }
      }
    }
		//Ȼ�����ʵʱ����ϵͳ�����ļ���ʼ��ip����ʵʱ��������ϵͳ��handler����
		String IPCGWServiceHandler = (String)CmisConstance.getParameterSettings().get("IPCGWServiceHandler");
		if (IPCGWServiceHandler == null || IPCGWServiceHandler.trim().length() == 0) {
			throw new Exception("��ȷ��DSR XML �ļ����������Ƿ���ȷ");
		}
		if (rootPath.endsWith(System.getProperty("file.separator"))) {
			IPCGWServiceHandler = rootPath + IPCGWServiceHandler;
		}
		else {
			IPCGWServiceHandler = rootPath + System.getProperty("file.separator") + IPCGWServiceHandler;
		}
		xmlReader.loadXMLFile(IPCGWServiceHandler);
		Node node2 = xmlReader.getNode("GatewayClientService");
		NamedNodeMap attrMap2 = node.getAttributes();
		NodeList childNodes2 = node2.getChildNodes();
		for (int i = 0; i < childNodes2.getLength(); i++) {
			Node nextNode = childNodes2.item(i);
			if (nextNode.getNodeType() == Node.ELEMENT_NODE) {				
				if (nextNode != null && nextNode.getNodeName().trim().equals("Handler")) {
					hnode = nextNode;
				}
			}
		}
    //�����ǳ�ʼ���������ݣ����濪ʼ�����̴߳����������
    workThreads = new WorkThread[conNum];
    for (int i = 0; i < conNum; i++) {
      workThreads[i] = new WorkThread();
      workThreads[i].setName("IPCGWClientWorkThread[" + i + "]");
      try {
        Receiver receiver = (Receiver)Class.forName(receiverClass).newInstance();
        Handler handler = (Handler)Class.forName(handlerClass).newInstance();
        Sender sender = (Sender)Class.forName(senderClass).newInstance();
        receiver.initForm(rnode);
        handler.initForm(hnode);
        sender.initForm(snode);
        workThreads[i].setReceiver(receiver);
        workThreads[i].setSender(sender);
        workThreads[i].setHandler(handler);
      }
      catch (Exception e) {
        throw new Exception("IPCGWClientWorkThread[" + i + "]" + "����IPͨ�����ط���ʧ��");
      }
      workThreads[i].setRetryTime(retryTime);
      workThreads[i].setMaxHandlers(maxHandlers);
      workThreads[i].setSocketTimeOut(socketTimeout);
      //���濪ʼ��ÿ�������߳�ȷ������������
      String host = "";
      String tmpstr[] = new String[2];
      for (int j = 0; j < hostList.size(); j++) {
        tmpstr = (String[])hostList.get(j);
        if (tmpstr[1].equals("1")) {
          continue;
        }
        else {
          host = tmpstr[0];
          tmpstr[1] = "1";
          hostList.remove(j);
          hostList.add(j, tmpstr);
        }
      }
      if (host.equals("")) { //���ȫ�������ˣ����ͷ��ʼ����
        tmpstr = (String[])hostList.get(i % hostList.size());
        host = tmpstr[0];
      }
      String hostName = host.substring(0, host.indexOf(":"));
      String hostPort = host.substring(host.indexOf(":") + 1);
      workThreads[i].setHostList(hostName, hostPort);
      workThreads[i].setThreadNo(i);
      workThreads[i].start();
      System.out.println("�����߳�" + i + " ������ϣ�");
    }
    System.out.println("IPͨ�����ط���������ϣ�");
  }
  /**
   * <b>��������: </b><br>
   * <p> </p>
   * @throws Exception
   *  
   */
  public void terminate() throws Exception {
    for (int i = 0; i < workThreads.length; i++) {
      workThreads[i].shutdown();
    }
    workThreads = null;
  }
}
