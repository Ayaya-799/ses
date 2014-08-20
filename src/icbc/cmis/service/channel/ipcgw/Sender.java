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

import java.net.Socket;

import org.w3c.dom.Node;

/*************************************************************
 * 
 * <b>��������: </b> 2006-7-12<br>
 * <b>����: cm2002IP����ͨѶ</b><br>
 * <b>������: �������ݰ��ӿ�</b><br>
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

public interface Sender {
	/**
	 * <b>��������: </b><br>
	 * <p>���� </p>
	 * @param socket
	 * @param repMsg
	 * @throws Exception
	 *  
	 */
	public abstract void send(Socket socket, byte repMsg[]) throws Exception;
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param node
	 * @throws Exception
	 *  
	 */
	public abstract void initForm(Node node) throws Exception;
}
