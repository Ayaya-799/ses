package icbc.cmis.second.pkg;

import java.util.*;
/**
 * Insert the type's description here.
 * Creation date: (2002-10-25 15:33:08)
 * @author: Administrator
 */
public class DBProcedureParamsDef {
	private String procedureName = null;
	private String dbUserName = null;
	private String dbUserPass = null;
	private String dbUserPassVerify = null;
	private Vector inParam = new Vector(10);
	private Vector outParam = new Vector(20);
	private Vector vCursorParam = new Vector(2);
	private Vector vIResultName = new Vector(2);
	
	/*begin 2006-06-03 �»ۿ����ӣ�����Զ�commit��־������Ĭ��ֵΪ���Զ�*/
	private boolean bAutoCommit = false;
	/*end 2006-06-03*/

/**
 * ProcedureParamsDef constructor comment.
 */
public DBProcedureParamsDef(String procedureName) {
	super();
	this.procedureName = procedureName;
}
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public void addCursorOutParams(Vector  vCursorOutParamNames,String iResultName)throws Exception {
	
	if(vCursorOutParamNames == null || vCursorOutParamNames.isEmpty()){
		throw new Exception("���ô洢���̽�������ز���������");
	}
	if(iResultName == null || iResultName.trim().length() == 0){
		throw new Exception("���ô洢���̽�����������Ʋ�����");
	}	
	vCursorParam.add(vCursorOutParamNames);
	vIResultName.add(iResultName);
}
 
public void addInParam(String inParamName)throws Exception{
	
	 if(inParamName == null || inParamName.trim().length() == 0)
	 
	 	throw new Exception("�洢��������������ò�������������Ϊ��");
	 	
	 
	 inParam.add(inParamName.trim());
	 
 }
 
public void addOutParam(String outParamName)throws Exception{
	
	 if(outParamName == null || outParamName.trim().length() == 0)
	 
	 	throw new Exception("�洢��������������ò�������������Ϊ��");
	 	
	
	 outParam.add(outParamName.trim());
	 
 }
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public Vector getCursorOutParams() {
	return vCursorParam;
}
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public Vector getCursorResultNames() {
	return vIResultName;
}
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public String getDBUserName() {
	return dbUserName;
}
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public String getDBUserPass() {
	return dbUserPass;
}
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public String getDBUserPassVerify() {
	return dbUserPassVerify;
}
 
public Vector getInParam(){
	
	return inParam;
	 
 }
 
public Vector getOutParam(){
	
	return  outParam;
	 
 }
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public String getProcedureName() {
	return procedureName;
}
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public void reset() throws Exception{
	try{
		dbUserName = null;
		dbUserPassVerify = null;
		procedureName = null;
		if(inParam != null && !inParam.isEmpty()){
			inParam.removeAllElements();
		}
		if(outParam != null && !outParam.isEmpty()){
			outParam.removeAllElements();
		}
		if(vCursorParam != null && !vCursorParam.isEmpty()){
			vCursorParam.removeAllElements();
		}
		if(vIResultName != null && !vIResultName.isEmpty()){
			vIResultName.removeAllElements();
		}
		
	}catch(Exception e){
		throw new Exception("��ʼ���洢���̲�������������ϢΪ��"+e.getMessage());
	}
}
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public void setDBUserName(String dbUserName) {
	this.dbUserName = dbUserName;
}
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public void setDBUserPass(String dbUserPass) {
	this.dbUserPass = dbUserPass;
}
/**
 * Insert the method's description here.
 * Creation date: (2002-10-25 15:49:08)
 */
public void setDBUserPassVerify(String dbUserPassVerify) {
	this.dbUserPassVerify = dbUserPassVerify;
}

/**
 *<b>��������: </b> 2006-06-03<br>
 *<b>����: ��bAutoCommit��־��ֵ</b><br>
 *<br>���Ƿ��Զ�commit��־bAutoCommit��ֵ
 *<p>Copyright: Copyright (c)2006</p>
 *<p>Company: ICBC</p>
 *@author �»ۿ�
 *-------------------------------------------------------------
 *�޸��ˣ�
 *�޸�ԭ��
 *�޸�ʱ�䣺
**/
public void setAutoCommit(boolean bAutoCommit) {
	this.bAutoCommit = bAutoCommit;
}

/**
 *<b>��������: </b> 2006-06-03<br>
 *<b>����: ȡ��bAutoCommit��־��ֵ</b><br>
 *<br>ȡ���Ƿ��Զ�commit��־bAutoCommit��ֵ
 *<p>Copyright: Copyright (c)2006</p>
 *<p>Company: ICBC</p>
 *@author �»ۿ�
 *-------------------------------------------------------------
 *�޸��ˣ�
 *�޸�ԭ��
 *�޸�ʱ�䣺
**/
public boolean getAutoCommit() {
	return bAutoCommit;
}

}
