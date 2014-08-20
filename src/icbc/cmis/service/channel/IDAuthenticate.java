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

/*************************************************************
 * 
 * <b>��������: </b> 2004-9-21<br>
 * <b>����: </b>��ϵͳ�����֤�ӿ�<br>
 * <b>������: </b><br>
 * 	�ýӿ���������CM2002��Ӧ��ϵͳ����ʱ��������������֤��<br>
 * <p>Copyright: Copyright (c)2004</p>
 * <p>Company: </p>
 * 
 * @author YangGuangrun
 * 
 * @version 
 * 
 * @since 
 * 
 * @see 
 * 
 *************************************************************/

public interface IDAuthenticate {
	/**
	 * <b>��������: </b><br>
	 * <p>�����֤�ӿڷ������÷����������û���ʶ����֤����֤�û�����Ƿ���Ч
	 *  �����Ч����true,���򷵻�false</p>
	 * @param ID
	 * @param verifyStr
	 * @return
	 * @throws TranFailException
	 *  
	 */
	public boolean isAuthenticated(Object ID,Object verifyStr) throws TranFailException;
}
