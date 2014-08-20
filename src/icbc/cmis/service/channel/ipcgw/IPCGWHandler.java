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

import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import icbc.cmis.base.KeyedDataCollection;
import icbc.cmis.operation.*;
/*************************************************************
 * 
 * <b>��������: </b> 2006-7-12<br>
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

public class IPCGWHandler implements Runnable, Handler {
  private String defaultOperation;
  private HashMap operationMap;
  private IPCGWSender sender;
  private Socket socket;
  private byte reqMsg1[];
  private WorkThread workth;
  /**
   * <b>���캯��</b><br>
   * 
   */
  public IPCGWHandler() {
    defaultOperation = null;
    operationMap = new HashMap();
  }
  /**
   * <b>���캯��</b><br>
   * 
   */
  public IPCGWHandler(HashMap operationMap, String defaultOperation, IPCGWSender sender1, Socket socket1, byte reqMsg[], WorkThread workth1) {
    defaultOperation = null;
    operationMap = new HashMap();
    sender = sender1;
    socket = socket1;
    reqMsg1 = reqMsg;
    workth = workth1;
  }

  public void set(IPCGWSender sender1, Socket socket1, byte reqMsg[], WorkThread workth1) throws Exception {
    sender = sender1;
    socket = socket1;
    reqMsg1 = reqMsg;
    workth = workth1;
  }
  /* ���� Javadoc��
   * @see java.lang.Runnable#run()
   */
  public void run() {
    try {
      if (socket != null) {
        byte repMsg[] = Process(reqMsg1);
        sender.set(socket, repMsg,workth);
        Thread t_sender = new Thread((IPCGWSender)sender);
        t_sender.start();
		workth.subHandlers();
      }
    }
    catch (Exception e) {}
  }
  /**
   * <b>��������: </b><br>
   * <p> ����Ĳ����Ƕ���������ͨѶ��������</p>
   * @param repMsg
   * @return
   * @throws Exception
   *  
   */
  public byte[] Process(byte reqMsg[]) throws Exception {
    String operationName = null;
    byte buf[] = new byte[1];
    System.arraycopy(reqMsg, 38+33, buf, 0, 1); //��������ǰ1���ֽڱ�ʾ��������
    String operationFlag = new String(buf); //ȡ���������ͣ��Ӷ�ȷ�����õ�op
    Vector v_trade_in = (Vector)IPCGWClientService.ht_trades.get(operationFlag + "_in");
    Vector v_trade_out = (Vector)IPCGWClientService.ht_trades.get(operationFlag + "_out");
    byte tmpReqMsg[] = new byte[reqMsg.length - 38]; //��ȡ����ͨѶ������
    System.arraycopy(reqMsg, 38, tmpReqMsg, 0, tmpReqMsg.length);
    byte repMsg[] = new byte[38 + 4]; //�������ݰ������ظ�ͳһ��Ϣƽ̨
    System.arraycopy(reqMsg, 0, repMsg, 0, 38); //��ȡ���հ��Ķ���������������
    //�������ж��յ���������������ļ��Ƿ�һ��
    int allLen = 0;
    for (int k = 0; k < v_trade_in.size(); k++) {
      allLen += Integer.parseInt(((String[])v_trade_in.elementAt(k))[1]);
    }
    if (allLen != tmpReqMsg.length) { //���Ȳ��ȣ��ͷ��ش���1��ʾ�����쳣
      //�����Ǽ�ز��֣���Ӱ��ҵ������
      if (!IPCGWClientService.trace_or_not.equals("false")) { //���ü�أ���¼�쳣��
        ToolsDAO tools = new ToolsDAO(new DummyOperation());
        try {
          tools.trace(InetAddress.getLocalHost().getHostAddress(), String.valueOf(workth.threadNo), new String(reqMsg),String.valueOf(workth.threadNo), workth.remoteHost+":"+String.valueOf(workth.remotePort), "���ݰ����Ⱥ������ļ���һ�£�handler��¼", "1", "0");
        }
        catch (Exception ee) {}
      }
      //�����Ǽ�ز��֣���Ӱ��ҵ������
      System.arraycopy("0001".getBytes(), 0, repMsg, 38, "0001".length()); //��ʾ�����쳣
      return repMsg;//�������ݸ������߳�
    }
    operationName = (String)operationMap.get(String.valueOf(Integer.parseInt(operationFlag)));
    if (operationName == null) {
      //operationName = defaultOperation;
//		�����Ǽ�ز��֣���Ӱ��ҵ������
		if (!IPCGWClientService.trace_or_not.equals("false")) { //���ü�أ���¼�쳣��
			ToolsDAO tools = new ToolsDAO(new DummyOperation());
			try {
				tools.trace(InetAddress.getLocalHost().getHostAddress(), String.valueOf(workth.threadNo), new String(reqMsg), String.valueOf(workth.threadNo),  workth.remoteHost+":"+String.valueOf(workth.remotePort), "û�ж�Ӧ�������ݰ��Ľ��״����࣬handler��¼", "1", "0");
			}catch (Exception ee) {}
		}
		//�����Ǽ�ز��֣���Ӱ��ҵ������
		System.arraycopy("0002".getBytes(), 0, repMsg, 38, "0002".length()); //��ʾ�����쳣
		return repMsg;//�������ݸ������߳�
    }
    try {
      Class c = (Class)Class.forName(operationName);
      CmisOperation operation = (CmisOperation)c.newInstance();
      operation.setOperationName(operationName);
      KeyedDataCollection opData = new KeyedDataCollection();
      operation.setOperationData(opData);
      //�˴���ӽ������

      String fieldName = ""; //�����������
      String fieldLen = ""; //������ĳ���
      String fieldType="";//���ݵ�����
      int idx = 0; //�˱�����־��ǰ�⴮��λ��
      String[] strTmp = new String[5];
      for (int i = 0; i < v_trade_in.size(); i++) {
        strTmp = (String[])v_trade_in.elementAt(i);
        fieldName = strTmp[0];
        fieldLen = strTmp[1];
        byte tmpBuf[] = new byte[Integer.parseInt(fieldLen)];
        System.arraycopy(tmpReqMsg, idx, tmpBuf, 0, Integer.parseInt(fieldLen)); //ȡ���ݵ�ֵ
        idx = idx + Integer.parseInt(fieldLen);
        operation.setFieldValue(fieldName, new String(tmpBuf));
      }
      //����opִ����������������
      operation.execute();
      //ִ����ϰѽ�����أ����������ļ��Խ�����д��	
      //����Ҫ���б�־�ģ�����ݷ������ݸ�ʽ�����û�б�־�ģ�ֱ�ӷ��سɹ�
      if (v_trade_out != null && v_trade_out.size() > 0) {
        String retStr = "";
        for (int i = 0; i < v_trade_out.size(); i++) {
          strTmp = (String[])v_trade_out.elementAt(i);
          fieldName = strTmp[0];
		  fieldLen = strTmp[1];
		  fieldType = strTmp[2];
          String fieldValue = operation.getStringAt(fieldName);
          if (fieldValue.length() <= Integer.parseInt(fieldLen)) { 
          	if(fieldType.equals("NUM")){//��������ǰ�油��0
          		for(;fieldValue.length()<Integer.parseInt(fieldLen);){
					fieldValue = "0"+fieldValue;
          		}
          	}else{//�ַ�����ǰ�油��ո�
				for(;fieldValue.length()<Integer.parseInt(fieldLen);){
					fieldValue = " "+fieldValue;
				}          		
          	}
            retStr +=  fieldValue ;
          }
          else { //���ȳ���3λ������ֶβ���ֵ������0
            throw new Exception(fieldName + "�ֶ����ݳ��ȳ�����");
          }
          if (retStr.length() > 4000) {
            throw new Exception(operationFlag + "���ֶ����ݳ��ȳ�����");
          }
        }
        byte tmpRep[] = new byte[38 + retStr.length()];
        System.arraycopy(repMsg, 0, tmpRep, 0, 38);
        System.arraycopy(retStr.getBytes(), 0, tmpRep, 38, retStr.length());
        return tmpRep;
      }
      else {
        System.arraycopy("0000".getBytes(), 0, repMsg, 38, "0000".length());
        return repMsg;
      }
    }
    catch (Exception e) {
      //�����Ǽ�ز��֣���Ӱ��ҵ������
      if (!IPCGWClientService.trace_or_not.equals("false")) { //���ü�أ���¼�쳣��
        ToolsDAO tools = new ToolsDAO(new DummyOperation());
        try {
          tools.trace(InetAddress.getLocalHost().getHostAddress(), String.valueOf(workth.threadNo), new String(reqMsg), String.valueOf(workth.threadNo),  workth.remoteHost+":"+String.valueOf(workth.remotePort), e.getMessage().length()>200?e.getMessage().substring(0, 200):e.getMessage(), "1", "2");
        }
        catch (Exception ee) {
        System.out.print("");
        }
      }
      //�����Ǽ�ز��֣���Ӱ��ҵ������
      System.arraycopy("0002".getBytes(), 0, repMsg, 38, "0002".length()); //����0002��ʾ�����쳣
      return repMsg;
    }
//    finally {
//      workth.subHandlers();
//    }
  }
  /** 
   * <b>��������: </b><br>
   * <p>	</p>
   * @see com.icbc.cmis.services.ipcgw.Handler#initForm(org.w3c.dom.Node)
   * @param node
   * @throws Exception
   * 
   */
  public void initForm(Node node) throws Exception {
    NamedNodeMap attrMap = node.getAttributes();
    try {
      defaultOperation = attrMap.getNamedItem("defaultOperation").getNodeValue().trim();
    }
    catch (Exception e) {
      throw new Exception("xml�ļ���ʽ����node����Handler&defaultOperation");
    }
    NodeList childNodes = node.getChildNodes();
    for (int i = 0; i < childNodes.getLength(); i++) {
      Node nextNode = childNodes.item(i);
      NamedNodeMap switchMap = nextNode.getAttributes();
      try {
        operationMap.put(switchMap.getNamedItem("case").getNodeValue().trim(), switchMap.getNamedItem("operation").getNodeValue().trim());
      }
      catch (Exception e) {}
    }
  }
}
