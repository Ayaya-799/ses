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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.w3c.dom.NamedNodeMap;
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

public class IPCGWReceiver implements Receiver {
	private long checkTime;
	/**
	 * <b>���캯��</b><br>
	 * 
	 */
	public IPCGWReceiver() {
		checkTime = 60000L;
	}
	/** 
	 * <b>��������: </b><br>
	 * <p>�������ݰ������ص��Ƕ���������ͨѶ��������</p>
	 * @see com.icbc.cmis.services.ipcgw.Receiver#receive(java.net.Socket)
	 * @param socket
	 * @return
	 * @throws Exception
	 * 
	 */
	public byte[] receive(Socket socket) throws Exception {
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		String strHead = "";
		byte body[];
		String bodyStr = "";
		do {
			byte head[] = new byte[12];
			int len = 0;
			long begin = System.currentTimeMillis();
			while (len < 12) {
				try {
					len += in.read(head, len, 12 - len);
					if(len == -1){
						len = 0 ;
					}
				} catch (IOException e) {
					if (System.currentTimeMillis() - begin > checkTime) {
						//���հ�ͷ��ʱ
						begin = System.currentTimeMillis();
						out.write("00140000000010030000000000".getBytes());
						//���������
					}
				}
			}
			strHead = new String(head);
			int dataLen = Integer.parseInt(strHead.substring(0, 4));
			len = 0;
			body = new byte[dataLen];
			begin = System.currentTimeMillis();
			while (len < dataLen) {
				try {
					len += in.read(body, len, dataLen - len);
				} catch (IOException e) {
					if (System.currentTimeMillis() - begin > checkTime) {
						//���հ��峬ʱ
						begin = System.currentTimeMillis();
						out.write("00140000000010030000000000".getBytes());
						//���������
					}
				}
			}
			bodyStr = new String(body);
		}
		while ((bodyStr.substring(2, 4)).equals("03")
			|| !(strHead.substring(4, 8)).equals("0000"));
		//����������쳣��������������ȡ�����������ֹͣѭ��
		return body;
	}
	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see com.icbc.cmis.services.ipcgw.Receiver#initForm(org.w3c.dom.Node)
	 * @param node
	 * @throws Exception
	 * 
	 */
	public void initForm(Node node) throws Exception {
		NamedNodeMap attrMap = node.getAttributes();
		try{
			this.checkTime = Long.valueOf(attrMap.getNamedItem("checkTime").getNodeValue().trim()).longValue();
		}catch(Exception e){
			
		}
	}
}
