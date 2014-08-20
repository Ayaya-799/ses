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
 * ���������Ľӿڣ��������˴����������Ļ�ȡ������������󡢷��ض���
 * �������ݡ�Session���ݵĽӿڷ���<br>
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

public interface ChannelContext {

	/**
	 * ȡ���״���������
	 */
	public abstract java.lang.Object getChannelDriver();

	/**
	 * ȡ��������
	 */
	public abstract java.lang.Object getChannelRequest();

	/**
	 * ȡ���������������Ӧ����
	 */
	public abstract java.lang.Object getChannelResponse();

	/**
	 * ȡ������������Ự
	 */
	public abstract java.lang.Object getChannelSession();

	/**
	 * ȡ������������
	 */
	public abstract java.lang.String getDeviceType();

	/**
	 * ȡ��������������
	 */
	public abstract KeyedDataCollection getRequestData() throws Exception;

	/**
	 * ���ý��״���������
	 */
	public abstract void setChannelDriver(java.lang.Object channelDriver);

	/**
	 * ����������������Ự
	 */
	public abstract void setChannelSession(java.lang.Object channelSession);

	/**
	 * ����������������
	 */
	public abstract void setDeviceType(java.lang.String type);

	/**
	 * ������������������
	 */
	public abstract void setRequestData(KeyedDataCollection kColl);
	
}
