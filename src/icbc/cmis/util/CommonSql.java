package icbc.cmis.util;
import icbc.cmis.operation.CmisOperation;
import icbc.cmis.base.TranFailException;
import java.util.*;
import icbc.cmis.second.pkg.*;
import icbc.cmis.base.*;
/**
 * Title:
 * Description: common sql query for general use
 * Copyright:   Copyright (c) 2001
 * Company:
 * @author      wu
 * @version 1.0
 *
 * update date: 2003-06-08
 * update content:�޸İ󶨱���
 * updated by WuQQ
 *
 * update date: 2002-09-12
 * update content: remove print message
 * updated by WuQQ
 */

public class CommonSql extends CmisOperation {

	public CommonSql() {
		super();
	}

	public void execute(){
	}

	/**
	 * Common date query �ǰ󶨱�����ʽ��ѯ
	 * �������ڣ�(2002-5-17 15:16:30)
	 * @param String sql
	 * @return ArrayList query result
	 * @exception java.lang.Exception �쳣˵����
	 */
	public java.util.ArrayList getDate(String sql) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		java.util.ArrayList aArrayList = null;
		try {
			tool.getConn();
			rs = tool.executeQuery(sql);
			if (rs != null){
				aArrayList = new java.util.ArrayList();
				//int iColumnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					aArrayList.add(rs.getString(1));
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtzutil???", "icbc.cmis.util.CommonSql.getDate()", "��ѯʱ��ʧ�ܣ�",e.getMessage());
		}
		return aArrayList;
	}
/**
	 * Common date query �󶨱�����ʽ��ѯ
	 * �������ڣ�(2002-5-17 15:16:30)
	 * @param String sql
	 * @return ArrayList query result
	 * @exception java.lang.Exception �쳣˵����
	 */
	public java.util.ArrayList getDate(String sql,java.util.Vector vValueData) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		java.util.ArrayList aArrayList = null;
		try {
			tool.getConn();
			rs = tool.executeQuery(sql,vValueData);
			if (rs != null){
				aArrayList = new java.util.ArrayList();
				//int iColumnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					aArrayList.add(rs.getString(1));
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtzutil???", "icbc.cmis.util.CommonSql.getDate()", "��ѯʱ��ʧ�ܣ�",e.getMessage());
		}
		return aArrayList;
	}
	/**
	 * �ǰ󶨱�����ʽ��ѯ
	 * �������ڣ�(2002-1-15 15:16:30)
	 * @param String sql
	 * @return int query count
	 * @exception java.lang.Exception �쳣˵����
	 */
	public  int checkData(String sql) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		int dataAmount = 0;
		try {
			tool.getConn();
			rs = tool.executeQuery(sql);
			if (rs != null){
				while (rs.next()) {
					dataAmount = rs.getInt(1);
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtzutil???", "icbc.cmis.util.CommonSql.checkAmount()","��ѯ��¼����ʧ�ܣ�",e.getMessage());
		}
		return dataAmount;
	}
/**
	 * �󶨱�����ʽ��ѯ
	 * �������ڣ�(2002-1-15 15:16:30)
	 * @param String sql
	 * @return int query count
	 * @exception java.lang.Exception �쳣˵����
	 */
	public  int checkData(String sql,java.util.Vector vValueData) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		int dataAmount = 0;
		try {
			tool.getConn();
			rs = tool.executeQuery(sql,vValueData);
			if (rs != null){
				while (rs.next()) {
					dataAmount = rs.getInt(1);
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtzutil???", "icbc.cmis.util.CommonSql.checkAmount()","��ѯ��¼����ʧ�ܣ�",e.getMessage());
		}
		return dataAmount;
	}
	/**
	 * ��ѯ����¼(һ���ֶ�)
	 * �������ڣ�(2002-1-15 15:16:30)
	 * @param String sql
	 * @return String query result
	 * @exception java.lang.Exception �쳣˵����
	 */
	public   java.lang.String getSingleResult(String sql) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		//java.util.ArrayList aArrayList = null;
		String aStr = null;

		try {
			tool.getConn();
			rs = tool.executeQuery(sql);
			if (rs != null){
				//aArrayList = new java.util.ArrayList();
				//int iColumnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					//aArrayList.add("first element");
					//for(int i=1;i<iColumnCount+1;i++){
						//aArrayList.add(rs.getString(i));
					//}
					aStr = rs.getString(1);
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtz0FFK804", "icbc.cmis.util.CommonSql.getResult()", "��ѯ��¼ʧ�ܣ�",e.getMessage());
		}
		return aStr;
	}
	/**
	 * ��ѯ����¼(һ���ֶ�)
	 * �������ڣ�(2002-1-15 15:16:30)
	 * @param String sql
	 * @return String query result
	 * @exception java.lang.Exception �쳣˵����
	 */
	public   java.lang.String getSingleResult(String sql,java.util.Vector vValueData) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		//java.util.ArrayList aArrayList = null;
		String aStr = null;

		try {
			tool.getConn();
			rs = tool.executeQuery(sql,vValueData);
			if (rs != null){
				//aArrayList = new java.util.ArrayList();
				//int iColumnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					//aArrayList.add("first element");
					//for(int i=1;i<iColumnCount+1;i++){
						//aArrayList.add(rs.getString(i));
					//}
					aStr = rs.getString(1);
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtz0FFK804", "icbc.cmis.util.CommonSql.getResult()", "��ѯ��¼ʧ�ܣ�",e.getMessage());
		}
		return aStr;
	}
	/**
	 * ��ѯ����¼(���ֶ�)
	 * �������ڣ�(2002-1-15 15:16:30)
	 * @param String sql
	 * @return ArrayList query result
	 * @exception java.lang.Exception �쳣˵����
	 */
	public  java.util.ArrayList getSingleListResult(String sql) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		java.util.ArrayList aArrayList = null;
		try {
			tool.getConn();
			rs = tool.executeQuery(sql);
			if (rs != null){
				aArrayList = new java.util.ArrayList();
				int iColumnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					//aArrayList.add("first element");
					for(int i=1;i<iColumnCount+1;i++){
						aArrayList.add(rs.getString(i));
					}
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtzUtil000", "icbc.cmis.util.CommonSql.getSingleResult()", "��ѯ��¼ʧ�ܣ�",e.getMessage());
		}
		return aArrayList;
	}
/**
	 * ��ѯ����¼(���ֶ�)
	 * �������ڣ�(2002-1-15 15:16:30)
	 * @param String sql
	 * @return ArrayList query result
	 * @exception java.lang.Exception �쳣˵����
	 */
	public  java.util.ArrayList getSingleListResult(String sql,java.util.Vector vValueData) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		java.util.ArrayList aArrayList = null;
		try {
			tool.getConn();
			rs = tool.executeQuery(sql,vValueData);
			if (rs != null){
				aArrayList = new java.util.ArrayList();
				int iColumnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					//aArrayList.add("first element");
					for(int i=1;i<iColumnCount+1;i++){
						aArrayList.add(rs.getString(i));
					}
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtzUtil000", "icbc.cmis.util.CommonSql.getSingleResult()", "��ѯ��¼ʧ�ܣ�",e.getMessage());
		}
		return aArrayList;
	}
		/**
	 * ��ѯ���¼(���ֶ�)
	 * �������ڣ�(2002-1-15 15:16:30)
	 * @param String sql
	 * @return ArrayList query result
	 * @exception java.lang.Exception �쳣˵����
	 */
	public   java.util.ArrayList getMultiListResult(String sql) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		java.util.ArrayList aArrayList = null;
		java.util.ArrayList bArrayList = new ArrayList();

		try {
			tool.getConn();
			rs = tool.executeQuery(sql);
			if (rs != null) {
				int iColumnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					aArrayList = new ArrayList();
					for (int j=0;j<iColumnCount;j++){
						aArrayList.add(rs.getString(j+1));
					}
					bArrayList.add(aArrayList);
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtzUtil000", "icbc.cmis.util.CommonSql.getMultiListResult()", "��ѯ��¼ʧ�ܣ�",e.getMessage());
		}
		return bArrayList;
	}
		/**
	 * ��ѯ���¼(���ֶ�)�󶨱�����ʽ��ѯ
	 * �������ڣ�(2002-1-15 15:16:30)
	 * @param String sql
	 * @return ArrayList query result
	 * @exception java.lang.Exception �쳣˵����
	 */
	public   java.util.ArrayList getMultiListResult(String sql,java.util.Vector vValueData) throws java.lang.Exception {
		icbc.cmis.FJ.CommonTools.cmisPrintln(sql);
		icbc.cmis.operation.SqlTool tool = new icbc.cmis.operation.SqlTool(this);
		java.sql.ResultSet rs = null;
		java.util.ArrayList aArrayList = null;
		java.util.ArrayList bArrayList = new ArrayList();

		try {
			tool.getConn();
			rs = tool.executeQuery(sql,vValueData);
			if (rs != null) {
				int iColumnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					aArrayList = new ArrayList();
					for (int j=0;j<iColumnCount;j++){
						aArrayList.add(rs.getString(j+1));
					}
					bArrayList.add(aArrayList);
				}
			}
			rs.close();
			tool.closeconn();
		}catch (Exception e) {
			tool.closeconn();
			throw new TranFailException("xdtzUtil000", "icbc.cmis.util.CommonSql.getMultiListResult()", "��ѯ��¼ʧ�ܣ�",e.getMessage());
		}
		return bArrayList;
	}
	/**
	 * �˴����뷽��˵����
	 * �������ڣ�(2003-01-06 15:16:30)
	 * @param
	 * @return
	 * @exception java.lang.Exception �쳣˵����
	 */

	public String delTable(ArrayList inDataValue,ArrayList inDataName,String procName) throws java.lang.Exception {
	String sRet = "OK";
	String sErrMessageFlag = "sErrMessage";
	try {

		DBProcedureParamsDef def = new DBProcedureParamsDef(procName);
		//���볡����
		for (int i=0;i<inDataValue.size();i++){
			this.setFieldValue((String)inDataName.get(i),inDataValue.get(i));
			def.addInParam((String)inDataName.get(i));
		}
		//���������
		def.addOutParam(sErrMessageFlag);

		/*
		//Ϊ���ô洢����׼������Ҫ������
		//setFieldValue("rowNum","10");//�洢�������볡�������ֵ
		//���볡����
		//def.addInParam("rowNum");

		//���������
		//def.addOutParam("returnFlag");//�洢���̷����룬��ʶ�洢��������״̬
		//def.addOutParam("rowCount");

		//����洢���̽������cursor�����ز���
		//�������
		Vector vCursor1 = new Vector(2);
		vCursor1.add("id1");//����������ֶ�
		vCursor1.add("value1");//����������ֶ�
		def.addCursorOutParams(vCursor1,"iResultSet1");//ָ��������洢�ڵ�ǰ���׼����е�һ����iResultSet1������IndexedDataCollection��
		//�������
		Vector vCursor2 = new Vector(2);
		vCursor2.add("id2");//����������ֶ�
		vCursor2.add("value2");//����������ֶ�
		def.addCursorOutParams(vCursor2,"iResultSet2");//ָ��������洢�ڵ�ǰ���׼����е�һ����iResultSet��������IndexedDataCollection��
		*/

		//���ô洢����
			DBProcedureAccessService proceSrv = new DBProcedureAccessService(this);
			int returnCode = proceSrv.executeProcedure(this.getOperationData(),def);
			proceSrv = null;

		//ȡ�洢���̽����Ϣ

		//����returnCodeΪ0��ʾ�洢���̵��óɹ�
		if(returnCode != 0){
			//����returnCode�Ĳ�ͬ���ʹ���洢���̷��ص���ϢreturnMessage��������Ĵ洢���̶����˵Ļ�����ҵ������
			//setReplyPage("/icbc/cmis/testlogin.jsp");	//����������ҳ��
			//return;
			sRet = this.getStringAt(sErrMessageFlag);
			return sRet;
		}

		/*
		System.out.println(getStringAt("returnFlag"));
		System.out.println(getStringAt("rowCount"));

		//ȡ�������Ϣ
			//�������
			IndexedDataCollection iResult1 = getIndexedDataCollection("iResultSet1");

			for(int i = 0;i<iResult1.getSize();i++){	//����������е�ÿ����¼
				KeyedDataCollection aKColl = (KeyedDataCollection)iResult1.getElement(i);
				System.out.println((String)aKColl.getValueAt("id1"));
				System.out.println((String)aKColl.getValueAt("value1"));
			}

			//�������
			IndexedDataCollection iResult2 = getIndexedDataCollection("iResultSet2");

			for(int i = 0;i<iResult2.getSize();i++){	//����������е�ÿ����¼
				KeyedDataCollection aKColl = (KeyedDataCollection)iResult2.getElement(i);
				System.out.println((String)aKColl.getValueAt("id2"));
				System.out.println((String)aKColl.getValueAt("value2"));
			}
			setReplyPage("/icbc/cmis/testlogin.jsp");
			*/
		return sRet;
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			//handler your exception
			throw e;
		}
	}
	/**
	 * ������ɾ�ĵĴ洢����
	 * �������ڣ�(2003-01-06 15:16:30)
	 * @param ��ڲ���ֵ,��ڲ�������,���õĴ洢��������
	 * @return  ��ȷ�����󷵻�"OK",���򷵻ش�����Ϣ
	 * @exception java.lang.Exception �쳣˵����
	 */

	public String callUpdateProc(ArrayList inDataName,ArrayList inDataValue,String procName) throws java.lang.Exception {
	String sRet = "OK";
	String sErrMessageFlag = "sErrMessage";
	KeyedDataCollection data = new KeyedDataCollection();
	try {

		DBProcedureParamsDef def = new DBProcedureParamsDef(procName);
		//���볡����
		for (int i=0;i<inDataValue.size();i++){
			data.addElement((String)inDataName.get(i),(String)inDataValue.get(i));
			def.addInParam((String)inDataName.get(i));
		}
		//���������
		def.addOutParam("sRet");
		def.addOutParam(sErrMessageFlag);

		/*
		//Ϊ���ô洢����׼������Ҫ������
		//setFieldValue("rowNum","10");//�洢�������볡�������ֵ
		//���볡����
		//def.addInParam("rowNum");

		//���������
		//def.addOutParam("returnFlag");//�洢���̷����룬��ʶ�洢��������״̬
		//def.addOutParam("rowCount");

		//����洢���̽������cursor�����ز���
		//�������
		Vector vCursor1 = new Vector(2);
		vCursor1.add("id1");//����������ֶ�
		vCursor1.add("value1");//����������ֶ�
		def.addCursorOutParams(vCursor1,"iResultSet1");//ָ��������洢�ڵ�ǰ���׼����е�һ����iResultSet1������IndexedDataCollection��
		//�������
		Vector vCursor2 = new Vector(2);
		vCursor2.add("id2");//����������ֶ�
		vCursor2.add("value2");//����������ֶ�
		def.addCursorOutParams(vCursor2,"iResultSet2");//ָ��������洢�ڵ�ǰ���׼����е�һ����iResultSet��������IndexedDataCollection��
		*/

		//���ô洢����
			DBProcedureAccessService proceSrv = new DBProcedureAccessService(this);
			//updated by WuQQ
			//int returnCode = proceSrv.executeProcedure(this.getOperationData(),def);
			int returnCode = proceSrv.executeProcedure(data,def);
			proceSrv = null;

		//ȡ�洢���̽����Ϣ

		//����returnCodeΪ0��ʾ�洢���̵��óɹ�
		if(returnCode != 0){
			//����returnCode�Ĳ�ͬ���ʹ���洢���̷��ص���ϢreturnMessage��������Ĵ洢���̶����˵Ļ�����ҵ������
			//setReplyPage("/icbc/cmis/testlogin.jsp");	//����������ҳ��
			//return;
			//updated by WuQQ
			//sRet = this.getStringAt(sErrMessageFlag);
			sRet = (String)data.getValueAt(sErrMessageFlag);
			return sRet;
		}

		/*
		System.out.println(getStringAt("returnFlag"));
		System.out.println(getStringAt("rowCount"));

		//ȡ�������Ϣ
			//�������
			IndexedDataCollection iResult1 = getIndexedDataCollection("iResultSet1");

			for(int i = 0;i<iResult1.getSize();i++){	//����������е�ÿ����¼
				KeyedDataCollection aKColl = (KeyedDataCollection)iResult1.getElement(i);
				System.out.println((String)aKColl.getValueAt("id1"));
				System.out.println((String)aKColl.getValueAt("value1"));
			}

			//�������
			IndexedDataCollection iResult2 = getIndexedDataCollection("iResultSet2");

			for(int i = 0;i<iResult2.getSize();i++){	//����������е�ÿ����¼
				KeyedDataCollection aKColl = (KeyedDataCollection)iResult2.getElement(i);
				System.out.println((String)aKColl.getValueAt("id2"));
				System.out.println((String)aKColl.getValueAt("value2"));
			}
			setReplyPage("/icbc/cmis/testlogin.jsp");
			*/
		return sRet;
		//} catch (TranFailException e) {
			//throw e;
		} catch (Exception e) {
			//handler your exception
			throw new TranFailException("CommonSql000", "icbc.cmis.util.CommonSql.callUpdateProc() ", e.toString(),"��ѯ��Ŀ������ʧ�ܣ�");
		}
	}
	/**
	 * ������ɾ�ĵĴ洢����
	 * �������ڣ�(2003-01-06 15:16:30)
	 * @param ��ڲ���ֵ,��ڲ�������,���õĴ洢��������
	 * @return  ��ȷ�����󷵻�"OK",���򷵻ش�����Ϣ
	 * @exception java.lang.Exception �쳣˵����
	 */

	public String callInsertProc(ArrayList inDataName,ArrayList inDataValue,String procName) throws java.lang.Exception {
	String sRet = "OK";
	String sErrMessageFlag = "sErrMessage";
	KeyedDataCollection data = new KeyedDataCollection();
	try {

		DBProcedureParamsDef def = new DBProcedureParamsDef(procName);
		//���볡����
		for (int i=0;i<inDataValue.size();i++){
			data.addElement((String)inDataName.get(i),(String)inDataValue.get(i));
			def.addInParam((String)inDataName.get(i));
		}
		//���������
		def.addOutParam("sRet");
		def.addOutParam(sErrMessageFlag);

		/*
		//Ϊ���ô洢����׼������Ҫ������
		//setFieldValue("rowNum","10");//�洢�������볡�������ֵ
		//���볡����
		//def.addInParam("rowNum");

		//���������
		//def.addOutParam("returnFlag");//�洢���̷����룬��ʶ�洢��������״̬
		//def.addOutParam("rowCount");

		//����洢���̽������cursor�����ز���
		//�������
		Vector vCursor1 = new Vector(2);
		vCursor1.add("id1");//����������ֶ�
		vCursor1.add("value1");//����������ֶ�
		def.addCursorOutParams(vCursor1,"iResultSet1");//ָ��������洢�ڵ�ǰ���׼����е�һ����iResultSet1������IndexedDataCollection��
		//�������
		Vector vCursor2 = new Vector(2);
		vCursor2.add("id2");//����������ֶ�
		vCursor2.add("value2");//����������ֶ�
		def.addCursorOutParams(vCursor2,"iResultSet2");//ָ��������洢�ڵ�ǰ���׼����е�һ����iResultSet��������IndexedDataCollection��
		*/

		//���ô洢����
			DBProcedureAccessService proceSrv = new DBProcedureAccessService(this);
			//updated by WuQQ
			//int returnCode = proceSrv.executeProcedure(this.getOperationData(),def);
			int returnCode = proceSrv.executeProcedure(data,def);
			proceSrv = null;

		//ȡ�洢���̽����Ϣ
		sErrMessageFlag = (String)data.getValueAt(sErrMessageFlag);
		//����returnCodeΪ0��ʾ�洢���̵��óɹ�
		if(returnCode != 0){
			//����returnCode�Ĳ�ͬ���ʹ���洢���̷��ص���ϢreturnMessage��������Ĵ洢���̶����˵Ļ�����ҵ������
			//setReplyPage("/icbc/cmis/testlogin.jsp");	//����������ҳ��
			//return;
			//updated by WuQQ
			//sRet = this.getStringAt(sErrMessageFlag);

			sRet = sErrMessageFlag;
			return sRet;
		}

		/*
		System.out.println(getStringAt("returnFlag"));
		System.out.println(getStringAt("rowCount"));

		//ȡ�������Ϣ
			//�������
			IndexedDataCollection iResult1 = getIndexedDataCollection("iResultSet1");

			for(int i = 0;i<iResult1.getSize();i++){	//����������е�ÿ����¼
				KeyedDataCollection aKColl = (KeyedDataCollection)iResult1.getElement(i);
				System.out.println((String)aKColl.getValueAt("id1"));
				System.out.println((String)aKColl.getValueAt("value1"));
			}

			//�������
			IndexedDataCollection iResult2 = getIndexedDataCollection("iResultSet2");

			for(int i = 0;i<iResult2.getSize();i++){	//����������е�ÿ����¼
				KeyedDataCollection aKColl = (KeyedDataCollection)iResult2.getElement(i);
				System.out.println((String)aKColl.getValueAt("id2"));
				System.out.println((String)aKColl.getValueAt("value2"));
			}
			setReplyPage("/icbc/cmis/testlogin.jsp");
			*/
		//��ȷ��������"OK"+��ʾ��Ϣ
		return (sRet+sErrMessageFlag);
		//} catch (TranFailException e) {
			//throw e;
		} catch (Exception e) {
			//handler your exception
			throw new TranFailException("CommonSql000", "icbc.cmis.util.CommonSql.callUpdateProc() ", e.toString(),"��ѯ��Ŀ������ʧ�ܣ�");
		}
	}
	/**
	 * ������ɾ�ĵĴ洢����
	 * �������ڣ�(2004-02-03 15:16:30)
	 * @param ��ڲ���ֵ,��ڲ�������,���õĴ洢��������
	 *        outDataName �д�Ŵ洢����out����
	 * @return  ��ȷ�����󷵻�"OK",���򷵻ش�����Ϣ
	 * @exception java.lang.Exception �쳣˵����
	 */

	public ArrayList getProcResult(ArrayList inDataName,ArrayList inDataValue,ArrayList outDataName,String procName) throws java.lang.Exception {
//	String sRet = "OK";
//	String sErrMessageFlag = "sErrMessage";
//	String sRetValue = "sRetValue";
	KeyedDataCollection data = new KeyedDataCollection();
	int i=0;
	try {

		DBProcedureParamsDef def = new DBProcedureParamsDef(procName);
		//���볡����
		for (i=0;i<inDataValue.size();i++){
			data.addElement((String)inDataName.get(i),(String)inDataValue.get(i));
			def.addInParam((String)inDataName.get(i));
		}
		//���������
		for (i=0;i<outDataName.size();i++){
			def.addOutParam((String)outDataName.get(i));
		}
		//���ô洢����
			DBProcedureAccessService proceSrv = new DBProcedureAccessService(this);
			int returnCode = proceSrv.executeProcedure(data,def);
			proceSrv = null;

		//ȡ�洢���̽����Ϣ
		ArrayList ret = new ArrayList(outDataName.size());
		//����returnCodeΪ0��ʾ�洢���̵��óɹ�
		//���۵����Ƿ�ɹ����������ý�������ϼ��������ϼ���������ret�е�һ�������Ƿ�Ϊ0�жϵ����Ƿ�ɹ���
//		if(returnCode != 0){
//			sRet = (String)data.getValueAt(sErrMessageFlag);
			for (i=0;i<outDataName.size();i++){
				ret.add(data.getValueAt((String)outDataName.get(i)));
			}
			return ret;
//		}
//		return ;
		} catch (Exception e) {
			//handler your exception
			throw new TranFailException("CommonSql000", "icbc.cmis.util.CommonSql.callUpdateProc() ", e.toString(),"��ѯ��Ŀ������ʧ�ܣ�");
		}
	}
}