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

import icbc.cmis.base.TranFailException;

import java.util.*;
import javax.servlet.http.*;

/**
 * 
 * <b>��������: </b> 2004-9-17><br>
 * <b>����: </b><br>
 * <b>������: </b><br>
 * �Ự�����������ڴ洢�Ựʵ�壬���Իػ�����Ч�Խ���У��<br>
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

public class SessionManager {
	private static Hashtable sEntries = new Hashtable(); //sessionʵ�弯��
	private SessionEntry sEntry = null; //sessionʵ��
	private ChannelContext channelContext = null; 

	/**
	 * <b>���캯��</b><br>
	 * @param request
	 */
	public SessionManager(ChannelContext channelContext) {
		this.channelContext = channelContext;
	}

	/**
	 * <b>��������: </b><br>
	 * <p>�жϵ�ǰ�û��Ự�Ƿ���Ч</p>
	 * @param authenticater ID��֤У����
	 * @return boolean �Ự�Ƿ���Ч
	 *  
	 */
	public boolean isValid(IDAuthenticate authenticater) throws TranFailException  {
		if(authenticater==null)
			return true;
		Hashtable sessionData=(Hashtable)channelContext.getChannelSession();
		String cur_authId =(String)sessionData.get("AuthId");
		String cur_verifyStr =(String)sessionData.get("VerifyStr");
		String cur_type = (String)sessionData.get("SysID");
		
		long sys_timeStamp = System.currentTimeMillis();
		
		Long cur_timeStamp = new Long(sys_timeStamp);
		if (cur_authId == null ){
			return false;
		}
		
		if (sEntries.containsKey(cur_authId)) {
			sEntry = (SessionEntry) sEntries.get(cur_authId);
			Long timeStamp = sEntry.getTimeStamp();
			if (cur_timeStamp.longValue() - timeStamp.longValue()
				> getMaxTimeInterval()) {
				if (authenticater.isAuthenticated(cur_authId,cur_verifyStr)) {
					sEntry.setTimeStamp(cur_timeStamp);
					return true;
				} else {
					sEntries.remove(cur_authId);
					return false;
				}
			} else {
				return true;
			}
		} else {
			if (authenticater.isAuthenticated(cur_authId,cur_verifyStr)) {
				sEntry =
					new SessionEntry(cur_type, cur_timeStamp, cur_authId,cur_verifyStr);
				sEntries.put(cur_authId, sEntry);
				return true;
			} else {
				return false;
			}
		}
		
	}

	/**
	 * <b>��������: </b><br>
	 * <p>��ȡϵͳ�ĻỰ��ʱ��������ʱ���� </p>
	 * @return
	 *  
	 */
	private long getMaxTimeInterval() {
		return Long.valueOf((String)CM2002Service.getSettings().get("SessionMaxTimeInterval")).longValue();
	}

}
