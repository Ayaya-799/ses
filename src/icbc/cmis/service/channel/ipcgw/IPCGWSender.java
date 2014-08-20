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

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.w3c.dom.Node;

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

public class IPCGWSender implements Runnable, Sender {
  private Socket socket;
  private byte[] repMsg;
  private WorkThread workth;
  /**
   * 
   */
  public IPCGWSender() {}
  /**
   * @param socket1
   * @param repMsg1
   * @throws Exception
   */
  public void set(Socket socket1, byte repMsg1[], WorkThread workth1) throws Exception {
    repMsg = repMsg1;
    socket = socket1;
	workth = workth1;
  }
  /**
   * 
   */
  public void run() {
    try {
      if (socket != null) {
        send(socket, repMsg);
      }
    }
    catch (Exception e) {
      //�����Ǽ�ز��֣���Ӱ��ҵ������
      if (!IPCGWClientService.trace_or_not.equals("false")) { //���ü�أ���¼�쳣��
        ToolsDAO tools = new ToolsDAO(new DummyOperation());
        try {
          tools.trace(InetAddress.getLocalHost().getHostAddress(), String.valueOf(workth.threadNo), new String(repMsg), String.valueOf(workth.threadNo),  workth.remoteHost+":"+String.valueOf(workth.remotePort), e.getMessage().substring(0,200), "1", "1");
        }
        catch (Exception ee) {}
      }
      //�����Ǽ�ز��֣���Ӱ��ҵ������
    }
  }
  /** 
   * <b>��������: </b><br>
   * <p>	���ͣ������Ƕ���������ͨѶ��������</p>
   * @see com.icbc.cmis.services.ipcgw.Sender#send(java.net.Socket, byte[])
   * @param socket
   * @param repMsg
   * @throws Exception
   * 
   */
  public void send(Socket socket, byte repMsg[]) throws Exception {
    OutputStream out = socket.getOutputStream();
    String strHead = repMsg.length + "00000000";
    strHead = "0000".substring(0, 12 - strHead.length()) + strHead;
    out.write((strHead + new String(repMsg)).getBytes());
  }
  /** 
   * <b>��������: </b><br>
   * <p>	</p>
   * @see com.icbc.cmis.services.ipcgw.Sender#initForm(org.w3c.dom.Node)
   * @param node
   * @throws Exception
   * 
   */
  public void initForm(Node node) throws Exception {}
}
