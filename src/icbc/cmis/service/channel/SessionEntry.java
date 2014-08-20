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

import icbc.cmis.base.CTEException;
import icbc.cmis.base.CTEInvalidRequestException;
import icbc.cmis.base.CTEObjectExistingException;
import icbc.cmis.base.CTEObjectNotFoundException;
import icbc.cmis.base.DataElement;
import icbc.cmis.base.KeyedDataCollection;

/*************************************************************
 * 
 * <b>��������: </b> 2004-9-17><br>
 * <b>����: SessionEntry</b><br>
 * <b>������: </b>
 * �Ựʵ���ࡣ���ڴ��ÿһ���ػ�ʵ�壬�����û�ID���û�У���롢��ϵͳ��ʶ<br>
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
 *************************************************************/

public class SessionEntry extends KeyedDataCollection {
	public SessionEntry() {
		super();
	}

	/**
	 * <b>���캯��</b><br>
	 * @param name
	 */
	public SessionEntry(String name) {
		super(name);
	}

	/**
	 * <b>���캯��</b><br>
	 * @param type
	 * @param timeStamp
	 * @param sessionId
	 */
	public SessionEntry(String type, Long timeStamp, String authId,String verifyStr) {
		this();
		setName(authId);
		initialize(type, timeStamp, authId,verifyStr);
		
	}

	/**
	 * <b>���캯��</b><br>
	 * @param type
	 * @param timeStamp
	 * @param sessionId
	 * @param sessionObject
	 */
	public SessionEntry(
		String type,
		Long timeStamp,
		String sessionId,
		String verifyStr,
		Object sessionObject) {
		this();
		setName(sessionId);

		initialize(type, timeStamp, sessionId,verifyStr,sessionObject);

	}

	/**
	 * <b>��������: </b><br>
	 * <p> �õ��Ƿ���ڣ�false:û���ڣ�true:�Ѿ�����</p>
	 * @return
	 * @throws CTEException
	 *  
	 */
	public boolean getExpired() {
		try {
			return ((Boolean) getValueAt("expired")).booleanValue();
		} catch (CTEObjectNotFoundException e) {
			
		} catch (CTEInvalidRequestException e) {
			
		}
		return true;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> ȡ����֤ID</p>
	 * @return
	 * @throws CTEException
	 *  
	 */
	public String getAuthId() {
		try {
			return (String) getValueAt("authid");
		} catch (CTEObjectNotFoundException e) {
			return null;
		} catch (CTEInvalidRequestException e) {
			return null;
		}
	}

	/**
	 * <b>��������: </b><br>
	 * <p>�õ�ʱ��� </p>
	 * @return
	 * @throws CTEException
	 *  
	 */
	public Long getTimeStamp() {
		try {
			return (Long) getValueAt("timeStamp");
		} catch (CTEObjectNotFoundException e) {
			return null;
		} catch (CTEInvalidRequestException e) {
			return null;
		}
	}

	/**
	 * <b>��������: </b><br>
	 * <p>�õ�session����</p>
	 * @return
	 * @throws CTEException
	 *  
	 */
	public String getType() {
		try {
			return (String) getValueAt("type");
		} catch (CTEObjectNotFoundException e) {
			return null;
		} catch (CTEInvalidRequestException e) {
			return null;
		}
	}

	/**
	 * <b>��������: </b><br>
	 * <p>�����Ự </p>
	 * @param type
	 * @param timeStamp
	 * @param sessionId
	 * @throws CTEInvalidRequestException
	 * @throws CTEObjectExistingException
	 *  
	 */
	private void initialize(String type, Long timeStamp, String authId,String veriryStr) {
		try {
			DataElement dataField = new DataElement();
			dataField.setName("type");
			dataField.setValue(type);
			addElement(dataField);

			dataField = new DataElement();
			dataField.setName("timeStamp");
			dataField.setValue(timeStamp);
			addElement(dataField);

			dataField = new DataElement();
			dataField.setName("AuthId");
			dataField.setValue(authId);
			addElement(dataField);
			
			dataField = new DataElement();
			dataField.setName("VerifyStr");
			dataField.setValue(veriryStr);
			addElement(dataField);
			
			dataField = new DataElement();
			dataField.setName("expired");
			dataField.setValue(new Boolean(false));
			addElement(dataField);
		} catch (CTEInvalidRequestException e) {
			
		} catch (CTEObjectExistingException e) {
			
		}
	}

	/**
	 * <b>��������: </b><br>
	 * <p>�����Ự </p>
	 * @param type
	 * @param timeStamp
	 * @param sessionId
	 * @param sessionObject
	 * @throws CTEInvalidRequestException
	 * @throws CTEObjectExistingException
	 *  
	 */
	private void initialize(
		String type,
		Long timeStamp,
		String authId,
		String verifyStr,
		Object sessionObject) {
		try {
			DataElement dataField = new DataElement();
			dataField.setName("type");
			dataField.setValue(type);

			addElement(dataField);

			dataField = new DataElement();
			dataField.setName("timeStamp");
			dataField.setValue(timeStamp);
			addElement(dataField);

			dataField = new DataElement();
			dataField.setName("authid");
			dataField.setValue(authId);
			addElement(dataField);
			
			dataField = new DataElement();
			dataField.setName("verifystr");
			dataField.setValue(verifyStr);
			addElement(dataField);

			dataField = new DataElement();
			dataField.setName("expired");
			dataField.setValue(new Boolean(false));
			addElement(dataField);

			dataField = new DataElement();
			dataField.setName("sessionObj");
			dataField.setValue(sessionObject);
			addElement(dataField);
		} catch (CTEInvalidRequestException e) {
			
		} catch (CTEObjectExistingException e) {
			
		}
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @throws CTEException
	 *  
	 */
	public void remove() throws CTEException {
		setValueAt("authid", null);
	}

	/**
	 * <b>��������: </b><br>
	 * <p>�����Ƿ���� </p>
	 * @param expired
	 * @throws CTEException
	 *  
	 */
	public void setExpired(boolean expired)  {
		try {
			setValueAt("expired", new Boolean(expired));
		} catch (CTEInvalidRequestException e) {
			
		} catch (CTEObjectNotFoundException e) {
			
		}
	}

	/**
	 * <b>��������: </b><br>
	 * <p>����ʱ��� </p>
	 * @param long1
	 * @throws CTEException
	 *  
	 */
	public void setTimeStamp(Long long1) {
		try {
			setValueAt("timeStamp", long1);
		} catch (CTEInvalidRequestException e) {
			
		} catch (CTEObjectNotFoundException e) {
		
		}
	}

	/**
	 * <b>��������: </b><br>
	 * <p>����session���� </p>
	 * @param type
	 * @throws CTEException
	 *  
	 */
	public void setType(String type) {
		try {
			setValueAt("type", type);
		} catch (CTEInvalidRequestException e) {
			
		} catch (CTEObjectNotFoundException e) {
			
		}
	}

}
