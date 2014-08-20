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

import icbc.cmis.operation.DummyOperation;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.net.*;
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

public class WorkThread extends Thread {
  private Receiver receiver;
  private Handler handler;
  private Sender sender;
  private boolean running;
  private Socket socket;
  // private ArrayList hostList;
  private long retryTime;
  private long retryTime1;
  private int socketTimeOut;
  private int maxHandlers;
  public String remoteHost;
  public int remotePort;
  private int Handlers = 0; //�������������������߳���
  public int threadNo; //�̺߳�
  /**
   * <b>���캯��</b><br>
   * 
   */
  public WorkThread() {
    receiver = null;
    handler = null;
    sender = null;
    running = false;
    socket = null;
    //hostList = null;
    retryTime = 20L;
    socketTimeOut = 3000;
    remoteHost = "127.0.0.1";
    remotePort = 9999;
  }
  /** 
   * <b>��������: </b><br>
   * <p>	</p>
   * @see java.lang.Runnable#run()
   * 
   * 
   */
  public void run() {
    running = true;
    while (running) {
      try {
        if (socket == null) {
          // genHost(); //������صĵ�ַ�Ͷ˿�
          socket = new Socket(remoteHost, remotePort);
          System.out.println("�߳�" + threadNo + "������Ӧipͨ�����ص�socket���ӳɹ�����ַ�˿ڣ�" + remoteHost + ":" + remotePort);
          retryTime1 = retryTime; //���ô���socket��ʱ˯��ʱ��
          socket.setSoTimeout(socketTimeOut);
        }
        if (Handlers < maxHandlers) { //�߳������ܳ�������߳���
          byte reqMsg[] = receiver.receive(socket);
          handler.set((IPCGWSender)sender, socket, reqMsg, this);
          Thread t_handler = new Thread((IPCGWHandler)handler);
          t_handler.start();
          synchronized (this) {
            Handlers++;
          }
          //�����Ǽ�ز��֣���Ӱ��ҵ������
          if (IPCGWClientService.trace_or_not.equals("all")) { //����ȫ���أ���¼�յ���ÿ����
            ToolsDAO tools = new ToolsDAO(new DummyOperation());
            try {
              tools.trace(
                InetAddress.getLocalHost().getHostAddress(),
                String.valueOf(threadNo),
                new String(reqMsg),
                String.valueOf(Handlers),
                (remoteHost + ":" + remotePort),
                "���յ����ݰ��������̼߳�¼",
                "0",
                "0");

            }
            catch (Exception ee) {}
          }
          //�����Ǽ�ز��֣���Ӱ��ҵ������
        }
        else {
          try {
            Thread.sleep(retryTime); //�߳������꣬˯��һ��ʱ������
          }
          catch (Exception e2) {}
        }
      }
      catch (Exception e) {
        System.out.println("�߳�" + threadNo + "��Ӧipͨ�����ص�socket�����쳣����ַ�˿ڣ�" + remoteHost + ":" + remotePort + "���쳣��Ϣ��" + e.getMessage());
        try {
          socket.close();
		  socket=null;
          try {
            Thread.sleep(retryTime1); //��ʧ�ܣ�˯��һ��ʱ������
            retryTime1 = retryTime1 + retryTime;
            if(retryTime1 >= 10*retryTime){
				retryTime1 = retryTime;
            }
          }
          catch (Exception e2) {}
        }
        catch (Exception e1) {
          socket = null;
          try {
            Thread.sleep(retryTime1); //��ʧ�ܣ�˯��һ��ʱ������
            retryTime1 = retryTime1 + retryTime;
			if(retryTime1 >= 10*retryTime){
				retryTime1 = retryTime;
			}
          }
          catch (Exception e2) {}
        }
      }
    }
  }
  /**
   * <b>��������: </b><br>
   * <p> </p>
   * 
   *  
   */
  public void shutdown() {
    running = false;
    try {
      socket.close();
    }
    catch (Exception e) {}
  }
  /**
   * <b>��������: </b><br>
   * <p> </p>
   * @param handler
   *  
   */
  public void setHandler(Handler handler) {
    this.handler = handler;
  }
  /**
   * <b>��������: </b><br>
   * <p> </p>
   * @param receiver
   *  
   */
  public void setReceiver(Receiver receiver) {
    this.receiver = receiver;
  }
  /**
   * <b>��������: </b><br>
   * <p> </p>
   * @param sender
   *  
   */
  public void setSender(Sender sender) {
    this.sender = sender;
  }
  /**
   * <b>��������: </b><br>
   * <p> �趨����ʱ��</p>
   * @param retryTime
   *  
   */
  public void setRetryTime(long retryTime) {
    this.retryTime = retryTime;
    this.retryTime1 = retryTime;
  }
  /**
   * <b>��������: </b><br>
   * <p> �趨��ʱʱ��</p>
   * @param socketTimeout
   *  
   */
  public void setSocketTimeOut(int socketTimeout) {
    this.socketTimeOut = socketTimeout;
  }
  /**
   * <b>��������: </b><br>
   * <p> ȡ��</p>
   * @param hostList
   *  
   */
  public void setHostList(String host, String port) {
    this.remoteHost = host;
    this.remotePort = Integer.parseInt(port);
  }
  /**
   * <b>��������: </b><br>
   * <p> ȡ��</p>
   * @param hostList
   *  
   */
  public void setMaxHandlers(int maxHandlers) {
    this.maxHandlers = maxHandlers;
  }
  /**
   * 
   */
  public synchronized void subHandlers() {
    Handlers--;
  }
  /**
   * 
   */
  public void setThreadNo(int no) {
    threadNo = no;
  }
}
