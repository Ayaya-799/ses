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

import icbc.cmis.base.KeyedDataCollection;

/**
 * 
 * <b>��������: </b> 2004-10-21<br>
 * <b>����: </b><br>
 * <b>������: </b><br>
 * ���״������ӿ�<br>
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

public interface Handler {
	
			
	/**
	 * <b>��������: </b><br>
	 * <p>���ݽ��������Ĵ����� </p>
	 * @param channelContext
	 *  
	 */
	public void processRequest(ChannelContext channelContext);
	
	/**
	 * <b>��������: </b><br>
	 * <p>���ݽ��������Ĵ����׷��� </p>
	 * @param channelContext
	 *  
	 */
	public void processReply(ChannelContext channelContext);
	
	/**
	 * <b>��������: </b><br>
	 * <p>���ݽ��������Ĵ������쳣 </p>
	 * @param channelContext
	 * @param e
	 *  
	 */
	public void handleException(ChannelContext channelContext,Exception e);
}