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
 * <b>����: IPͨ������cm2002�˷����</b><br>
 * <b>������: �������ط�����cm2002������</b><br>
 * <br>
 * <p>Copyright: Copyright (c)2006</p>
 * <p>Company: </p>
 * 
 * @author ZJFH-wanglx
 * 
 * @version 1.0.0
 * 
 * @since 
 * 
 * @see 
 * 
 *************************************************************/

public interface Receiver {
	/**
	 * <b>��������: </b><br>
	 * <p>���캯��</p>
	 * @param socket
	 * @return
	 * @throws Exception
	 *  
	 */
	public abstract byte[] receive(Socket socket) throws Exception;
	/**
	 * <b>��������: </b><br>
	 * <p> ��ȡ�����ļ�</p>
	 * @param node
	 * @throws Exception
	 *  
	 */
	public abstract void initForm(Node node) throws Exception;
}
