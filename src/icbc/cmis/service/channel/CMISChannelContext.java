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
 * CM2002����ӿڣ���������������ʵ����<br>
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

public class CMISChannelContext implements ChannelContext {
	
	private Object channelRequest=null;
	private Object channelResponse=null;
	private String channelType=null;
	private Object session=null;
	
	private KeyedDataCollection requestData=null;
	
	private Object channelDriver=null;

	public CMISChannelContext(Object channelRequest,Object channelResponse,Object channelDriver){
		this.channelRequest=channelRequest;
		this.channelResponse=channelResponse;
		this.channelDriver=channelDriver;
	}
	
	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#getChannelDriver()
	 * @return
	 * 
	 */
	public Object getChannelDriver() {
		return channelDriver;
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#getChannelRequest()
	 * @return
	 * 
	 */
	public Object getChannelRequest() {
		return channelRequest;
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#getChannelResponse()
	 * @return
	 * 
	 */
	public Object getChannelResponse() {
		return channelResponse;
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#getChannelSession()
	 * @return
	 * 
	 */
	public Object getChannelSession() {
		return session;
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#getDeviceType()
	 * @return
	 * 
	 */
	public String getDeviceType() {
		return channelType;
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#getRequestData()
	 * @return
	 * 
	 */
	public KeyedDataCollection getRequestData() throws Exception {
		return requestData;
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#setChannelDriver(java.lang.Object)
	 * @param channelDriver
	 * 
	 */
	public void setChannelDriver(Object channelDriver) {
		this.channelDriver=channelDriver;
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#setChannelSession(java.lang.Object)
	 * @param channelSession
	 * 
	 */
	public void setChannelSession(Object channelSession) {
		this.session=channelSession;
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#setDeviceType(java.lang.String)
	 * @param type
	 * 
	 */
	public void setDeviceType(String type) {
		this.channelType=type;
	}

	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see icbc.cmis.service.channel.ChannelContext#setRequestData(icbc.cmis.base.KeyedDataCollection)
	 * @param arg0
	 * 
	 */
	public void setRequestData(KeyedDataCollection kColl) {
		this.requestData=kColl;
	}

}
