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
 * �����������ӿڣ������彻��������ʵ�ֽ������ݽ����Ľӿڷ�����<br>
 * ���������������ĵķ����ӿ�<br>
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

public interface ChannelDriver {
	
	/**
	 * <b>��������: </b><br>
	 * <p>����ͬ����������������ݽ���ΪKeyedDataCollection�ṹ������ </p>
	 * @param channelContext
	 * @return
	 * @throws Exception
	 *  
	 */
	public KeyedDataCollection parseRequestData(ChannelContext channelContext ) throws Exception;
	
	/**
	 * <b>��������: </b><br>
	 * <p>���������Ľ��������ġ� </p>
	 * @param req
	 * @param resp
	 * @param driver
	 * @return
	 * @throws Exception
	 *  
	 */
	public ChannelContext creatChannelContext(Object req,Object resp,Object driver) throws Exception;
}
