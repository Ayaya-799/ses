package icbc.cmis.util;

import java.sql.*;

import oracle.jdbc.driver.*;
import icbc.cmis.base.*;
import icbc.cmis.operation.CmisOperation;
import java.util.*;

import icbc.cmis.service.CmisDao;

/*************************************************************
 * 
 * <b>��������: </b> 2004-8-31><br>
 * <b>����: </b>���ݷ��ʹ�����<br>
 * <b>������: </b>��Ҫ������ݿ����ӵò����ͷŵ����⡣ԭ��Ҫ�ӿڼ򵥡�<br>
 * <br>
 * <p>Copyright: Copyright (c)2004</p>
 * <p>Company: </p>
 * 
 * @author zhouxj
 * 
 * @version 1.25
 * 
 * @since 
 * 
 * @see 
 * 
 *************************************************************/

public class DBTools extends CmisDao {
	private String OpName="";	//���õ�Op��	
	private boolean b_DBUtilLog=true;		//�Ƿ�Ҫ����DBUtilLog��ͳ����Ϣ
	private String s_DBStartTime="";		//����DBUtil��ʼʱ��
	private String s_DBEndTime="";			//����DBUtil����ʱ��
	private Vector result; //���صĽ�������״̬
	private String s_out_Msg; //���ش洢������Ϣ,һ�����������Ϣ
	private String s_out_sign; //���ش洢������Ϣ,һ��洢���ر�־
	private Hashtable H_table; //���ص�һ����������
	private String dbUser;
	/**
	 * <b>���캯��</b><br>
	 * @param arg1
	 * @throws java.io.IOException
	 */
	public DBTools(CmisOperation arg1) throws java.io.IOException {
		super(arg1);
		OpName=arg1.getOperationName();
		this.dbUser = "cmis3";
	}

	public DBTools(CmisOperation arg1, String dbUser) throws java.io.IOException {
		super(arg1);
		OpName=arg1.getOperationName();
		this.dbUser = dbUser;
	}
	/**
	 * <b>��������: </b>�ṩִ�к����ʹ洢���̵ļ򵥽ӿ�<br>
	 * <p>�������ֻ֧��STRING,��������ں���.��ֻ���غ�������ֵ,ֻ���������,֧�������������.�����ʹ洢���̷���ֵΪVARCHAR2��
	 * o_flag       OUT   VARCHAR2,               --���سɹ���Ϣ,�����������ر�־ֵ,0 �ɹ� ��0��ʾʧ��
	 * o_mesg    	OUT   VARCHAR2,               --�ڶ���������Ϣ�Ĳ���
 	 * �����������:{?=call pack_templet.func_get_result( ?, ?, ?, ?, ?)}</p>
	 * 
 	 * @param Sqlstate	���ô洢���̵����
	 * @param inParamValue �������ֵ�б�
	 * @return �洢���̷���ֵ
	 * @throws Exception
	 *  
	 */
	public String executeFunctionMsg(String Sqlstate, Vector inParamValue)
		throws TranFailException {

		CallableStatement call = null;
		Hashtable ht;
		try {
            long D_start = System.currentTimeMillis();	//ȡ��ʼʱ��
			if (inParamValue == null)
				throw new Exception("���ṩ�洢���̵��õ����������Ϣ");

			getConnection(this.dbUser);

			StringBuffer param = new StringBuffer(Sqlstate);

			call = conn.prepareCall(param.toString());
			
			int outIdx = inParamValue.size() +2;

			call.registerOutParameter(1, Types.VARCHAR);
			
			//now set the procedure input paramaters    
			for (int i = 0; i < inParamValue.size(); i++) {
				call.setString(2 + i, (String) inParamValue.get(i));
			}
	
			call.registerOutParameter(outIdx, Types.VARCHAR);
			call.registerOutParameter(outIdx+1, Types.VARCHAR);

			call.execute();

			String s_out_ProcSign = call.getString(1);
			this.s_out_sign= call.getString(outIdx);
			this.s_out_Msg = call.getString(outIdx+1);	 //���ش洢������Ϣ,һ�����������Ϣ

			this.H_table = null; //����һ����¼����H_table��
			this.result = null;

			call.close();
			conn.commit();
			closeConnection();
			long D_end = System.currentTimeMillis();	//ȡ����ʱ��
			//writelog(Sqlstate,D_start,D_end);	//��¼ʱ����Ϣ��
			return s_out_ProcSign;
		} catch (TranFailException e) {

			util_logTools.error(e);
			throw e;
		} catch (SQLException sqlex) {
			util_logTools.error(sqlex);
			throw new TranFailException("DBUtil067", //������룬ʹ���߿�
			getClass().getName() + ".executeFunction()", //����λ��,�����߿�
			sqlex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate);
		} catch (Exception ex) {

			util_logTools.error(ex);
			throw new TranFailException("DBUtil068", //������룬ʹ���߿�
			getClass().getName() + ".executeFunction()", //����λ��,�����߿�
			ex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate); //������������ʹ���߿�
		} finally {
			try {
				conn.rollback();
				if (call != null)
					call.close();
			} catch (Exception ee) {
			};
			closeConnection();
		}

	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>���sql����ͳ����Ϣ </p>
	 * @param Sqlstate SQL�����ַ���
	 * @param D_start ��ʼʱ��
	 * @param D_end ����ʱ��
	 *  
	 */
/*
	private void writelog(String Sqlstate, long D_start, long D_end) throws SQLException, TranFailException {
		PreparedStatement stmt = null;
		try {
			if (this.b_DBUtilLog) {
				String s_start= DateTools.getDateTime(D_start,"yyyy-MM-dd HH:mm");
				String s_end= DateTools.getDateTime(D_end,"yyyy-MM-dd HH:mm");
				
				long l_time = DateTools.TimeBetween(D_start, D_end);
				
				System.out.print("����Op��"+this.OpName+"\nSQL���" + Sqlstate +"\n��ʼʱ��:" +s_start+"\n����ʱ�䣺"+s_end+"\n��ʱ����:"+l_time);
				
				getConnection(this.dbUser);*/

//create table cmis3.ta440901 (		/*���ݿ���Դ��ر�*/
//	OPNAME	varchar2(50),        	/*����Op��*/
//	SQLSTATE	varchar2(1000),		/*SQL�����ߴ洢��������*/
//	STARTTIME	varchar2(16) ,	   	/*��ʼʱ��*/
//	ENDTIME	varchar2(16),	   	/*����ʱ��*/
//	ELAPSE		number(16)		/*��ʱ����*/
//);                
/*	
				String s_sql ="insert into ta440901(OPNAME,SQLSTATE,STARTTIME,ENDTIME,ELAPSE) values(?,?,?,?,?)";
				stmt = conn.prepareStatement(s_sql);
				stmt.setString(1, this.OpName);
				stmt.setString(2, Sqlstate);
				stmt.setString(3, s_start);
				stmt.setString(4, s_end);
				stmt.setLong(5, l_time);
				
				int i_ret=stmt.executeUpdate();
				
				conn.commit();
				stmt.close();
				closeConnection();
					
			}
		} catch (SQLException sqlex) {
			conn.rollback();
			util_logTools.error(sqlex);
			throw new TranFailException("DBUtil077", //������룬ʹ���߿�
			getClass().getName() + ".writelog()", //����λ��,�����߿�
			sqlex.getMessage(), //�������ݣ������߿�
			"дlog������������Ƿ���ȷ��" );
		} catch (Exception ex) {
			conn.rollback();
			util_logTools.error(ex);
			throw new TranFailException("DBUtil078", //������룬ʹ���߿�
			getClass().getName() + ".writelog()", //����λ��,�����߿�
			ex.getMessage(), //�������ݣ������߿�
			"дlog������������Ƿ���ȷ��" ); //������������ʹ���߿�
		} finally {
			try {
		
			if (stmt != null)
				stmt.close();
			} catch (Exception ee) {
			};
			closeConnection();
		}
			
	}*/


	/**
	 * <b>��������: </b>�ṩִ�к����ʹ洢���̵ļ򵥽ӿ�<br>
	 * <p>�������ֻ֧��STRING,��������ں���.��ֻ���غ�������ֵ,ֻ���������,û���������.�����ʹ洢���̷���ֵΪVARCHAR2��
	 * �����������:{?=call pack_templet.func_get_result( ?, ?, ?, ?, ?)}</p>
	 * 
	 * @param Sqlstate	���ô洢���̵����
	 * @param inParamValue �������ֵ�б�
	 * @return �洢���̷��صĵڶ����������ڶ���������Ϣ�Ĳ���
	 * @throws Exception
	 *  
	 */
	public String executeFunction(String Sqlstate, Vector inParamValue)
		throws TranFailException {

		CallableStatement call = null;
		Hashtable ht;
		try {
			long D_start=System.currentTimeMillis();	//ȡ��ʼʱ��

			if (inParamValue == null)
				throw new Exception("���ṩ�洢���̵��õ����������Ϣ");

			getConnection(this.dbUser);

			StringBuffer param = new StringBuffer(Sqlstate);

			call = conn.prepareCall(param.toString());

			call.registerOutParameter(1, Types.VARCHAR);
			
			//now set the procedure input paramaters    
			for (int i = 0; i < inParamValue.size(); i++) {
				call.setString(2 + i, (String) inParamValue.get(i));
			}
	
			call.execute();

			String s_out_ProcSign = call.getString(1);

			this.H_table = null; //����һ����¼����H_table��
			this.result = null;

			call.close();
			conn.commit();
			closeConnection();
			this.s_out_sign = s_out_ProcSign; //���ش洢������Ϣ,һ�����������Ϣ
			this.s_out_Msg = s_out_ProcSign;

			long D_end=System.currentTimeMillis();	//ȡ����ʱ��
			//writelog(Sqlstate,D_start,D_end);	//��¼ʱ����Ϣ��

			return s_out_ProcSign;
		} catch (TranFailException e) {
			util_logTools.error(e);
			throw e;
		} catch (SQLException sqlex) {

			util_logTools.error(sqlex);
			throw new TranFailException("DBUtil067", //������룬ʹ���߿�
			getClass().getName() + ".executeFunction()", //����λ��,�����߿�
			sqlex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate);
		} catch (Exception ex) {
			util_logTools.error(ex);
			throw new TranFailException("DBUtil068", //������룬ʹ���߿�
			getClass().getName() + ".executeFunction()", //����λ��,�����߿�
			ex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate); //������������ʹ���߿�
		} finally {
			try {
				conn.rollback();
				if (call != null)
					call.close();
			} catch (Exception ee) {
			};
			closeConnection();
		}

	}
	
	/**
	 * <b>��������: </b>�ṩִ�д洢���̵ļ򵥽ӿ�<br>
	 * <p>�������ֻ֧��STRING,��������ں���.�������αֻ꣬���سɹ���Ϣ�������ı�־λ</p>
	 * o_flag       OUT   VARCHAR2,               --���سɹ���Ϣ,�����������ر�־ֵ,0 �ɹ� ��0��ʾʧ��
	 * o_msg		OUT   VARCHAR2,               --�ڶ���������Ϣ�Ĳ���
	 * 
	 * @param Sqlstate	���ô洢���̵����
	 * @param inParamValue �������ֵ�б�
	 * @return �洢���̷��صĵڶ����������ڶ���������Ϣ�Ĳ���
	 * @throws Exception
	 *  
	 */
	public String executeProcedure(String Sqlstate, Vector inParamValue)
		throws TranFailException{

		CallableStatement call = null;
		Hashtable ht;
		try {
			long D_start=System.currentTimeMillis();	//ȡ��ʼʱ��

			if (inParamValue == null)
				throw new Exception("���ṩ�洢���̵��õĲ�����Ϣ");

			getConnection(this.dbUser);

			StringBuffer param = new StringBuffer(Sqlstate);

			call = conn.prepareCall(param.toString());

			int outIdx = inParamValue.size() + 1;

			String outType = "";
			//now set the procedure input paramaters    
			for (int i = 0; i < inParamValue.size(); i++) {

				call.setString(1 + i, (String) inParamValue.get(i));

			}

			call.registerOutParameter(outIdx, Types.VARCHAR);
			call.registerOutParameter(outIdx + 1, Types.VARCHAR);

			call.execute();

			this.s_out_sign = call.getString(outIdx);
			this.s_out_Msg = call.getString(outIdx + 1);

			this.H_table = null; //����һ����¼����H_table��
			result = null;

			call.close();
			conn.commit();
			closeConnection();

			long D_end=System.currentTimeMillis();	//ȡ����ʱ��
			//writelog(Sqlstate,D_start,D_end);	//��¼ʱ����Ϣ��

			return s_out_Msg;
		} catch (TranFailException e) {

			util_logTools.error(e);
			throw e;
		} catch (SQLException sqlex) {

			util_logTools.error(sqlex);
			throw new TranFailException("DBUtil057", //������룬ʹ���߿�
			getClass().getName() + ".executeProcedure()", //����λ��,�����߿�
			sqlex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate);
		} catch (Exception ex) {

			util_logTools.error(ex);
			throw new TranFailException("DBUtil058", //������룬ʹ���߿�
			getClass().getName() + ".executeProcedure()", //����λ��,�����߿�
			ex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate); //������������ʹ���߿�
		} finally {
			try {
				conn.rollback();
				if (call != null)
					call.close();
			} catch (Exception ee) {
			};
			closeConnection();
		}

	}

	/**
	 * <b>��������: </b>�ṩִ�д洢���̵Ľӿ�<br>
	 * <p>�������ֻ֧��STRING,��������ں���,���һ������ΪCURSOR����,��Ž���� .</p>
	 * o_mesg       OUT   VARCHAR2,               --������ϸ��Ϣ,,�����������ر�־ֵ,0 �ɹ� ��0��ʾʧ��
	 * o_retcount   OUT   VARCHAR2,               --���ؼ�¼��
	 * o_result   OUT   ref_dbtest                --���ؽ������
	 * 
	 * 
	 * @param Sqlstate	���ô洢���̵����
	 * @param inParamValue �������ֵ�б�
	 * @param outParamType	�������
	 * @return �洢���̷��صĵڶ���������һ����ָ��¼������
	 * @throws Exception
	 *  
	 */
	public int executeProcedure(
		String Sqlstate,
		Vector inParamValue,
		Vector outParamName)
		throws TranFailException {

		CallableStatement call = null;
		Hashtable ht;
		try {
			long D_start=System.currentTimeMillis();	//ȡ��ʼʱ��

			if (inParamValue == null)
				throw new Exception("���ṩ�洢���̵��õĲ�����Ϣ");

			getConnection(this.dbUser);

			StringBuffer param = new StringBuffer(Sqlstate);

			call = conn.prepareCall(param.toString());

			int outIdx = inParamValue.size() + 1;

			String outType = "";
			//now set the procedure input paramaters    
			for (int i = 0; i < inParamValue.size(); i++) {

				call.setString(1 + i, (String) inParamValue.get(i));

			}

			call.registerOutParameter(outIdx, Types.VARCHAR);
			call.registerOutParameter(outIdx + 1, Types.VARCHAR);
			call.registerOutParameter(outIdx + 2, OracleTypes.CURSOR);

			call.execute();
			result = new Vector();
			ResultSet rset = null;

			String s_out_ProcSign = call.getString(outIdx);
			String s_out_rowCount = call.getString(outIdx + 1);

			try {
				rset = (ResultSet) call.getObject(outIdx + 2);

				int i_count = 0;
				int maxRow = getOperation().getMaxRowNum(); // ȡ�ļ�¼�������ֵ
				while (rset.next()) {
					i_count++;
					ht = new Hashtable();
					for (int i = 0; i < outParamName.size(); i++) {
						ht.put(
							(String) outParamName.get(i),
							this.replaceNullString(rset.getString(1 + i)));
					}
					if (i_count == 1) {
						this.H_table = ht; //����һ����¼����H_table��
					}
					result.add(ht);

					if (maxRow != -1 && i_count >= maxRow) {
						util_logTools.warn(
							"���ݼ�¼����maxRow:" + maxRow + "������!�����������С���ҷ�Χ!");
						break;
					}
				}
			} catch (SQLException esql) {
				throw esql;
			}

			call.close();
			conn.commit();
			closeConnection();
			s_out_sign = s_out_ProcSign;
			s_out_Msg = s_out_ProcSign;

			long D_end=System.currentTimeMillis();	//ȡ����ʱ��
			//writelog(Sqlstate,D_start,D_end);	//��¼ʱ����Ϣ��

			return Integer.valueOf(s_out_rowCount).intValue();
		} catch (TranFailException e) {

			util_logTools.error(e);
			throw e;
		} catch (SQLException sqlex) {

			util_logTools.error(sqlex);
			throw new TranFailException("DBUtil047", //������룬ʹ���߿�
			getClass().getName() + ".executeProcedure()", //����λ��,�����߿�
			sqlex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate);
		} catch (Exception ex) {

			util_logTools.error(ex);
			throw new TranFailException("DBUtil048", //������룬ʹ���߿�
			getClass().getName() + ".executeProcedure()", //����λ��,�����߿�
			ex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate); //������������ʹ���߿�
		} finally {
			try {
				conn.rollback();
				if (call != null)
					call.close();
			} catch (Exception ee) {
			};
			closeConnection();
		}

	}

	/**
	 * <b>��������: </b>�ṩִ�д洢���̵Ľӿ�<br>
	 * <p>�������ֻ֧��STRING,��������ں���,���һ������ΪCURSOR����,��Ž���� .</p>
	 * out_ProcSign	out varchar2,	�����������ر�־ֵ,0 �ɹ� ��0��ʾʧ��
	 * out_rowCount	out varchar2,
	 * out_ProcMsg  out varchar2,
	 * ret_dbtest 	out ref_dbtest,
	 * 
	 * 
	 * @param Sqlstate	���ô洢���̵����
	 * @param inParamValue �������ֵ�б�
	
	 * @param outParamType	�������
	 * @return �洢���̷��صĵڶ���������һ����ָ��¼������
	 * @throws Exception
	 *  
	 */
	public int executeProcedureMsg(
		String Sqlstate,
		Vector inParamValue,
		Vector outParamName)
		throws TranFailException{

		CallableStatement call = null;
		Hashtable ht;
		try {
			long D_start=System.currentTimeMillis();	//ȡ��ʼʱ��

			if (inParamValue == null)
				throw new Exception("���ṩ�洢���̵��õĲ�����Ϣ");

			getConnection(this.dbUser);

			StringBuffer param = new StringBuffer(Sqlstate);

			call = conn.prepareCall(param.toString());

			int outIdx = inParamValue.size() + 1;

			String outType = "";
			//now set the procedure input paramaters    
			for (int i = 0; i < inParamValue.size(); i++) {

				call.setString(1 + i, (String) inParamValue.get(i));

			}

			call.registerOutParameter(outIdx, Types.VARCHAR);
			call.registerOutParameter(outIdx + 1, Types.VARCHAR);
			call.registerOutParameter(outIdx + 2, Types.VARCHAR);
			call.registerOutParameter(outIdx + 3, OracleTypes.CURSOR);

			call.execute();
			result = new Vector();
			ResultSet rset = null;

			String s_out_ProcSign = call.getString(outIdx);
			String s_out_rowCount = call.getString(outIdx + 1);
			String s_out_ProcMsg = call.getString(outIdx + 2);

			try {
				rset = (ResultSet) call.getObject(outIdx + 3);

				int i_count = 0;
				int maxRow = getOperation().getMaxRowNum(); // ȡ�ļ�¼�������ֵ
				while (rset.next()) {
					i_count++;
					ht = new Hashtable();
					for (int i = 0; i < outParamName.size(); i++) {
						ht.put(
							(String) outParamName.get(i),
							this.replaceNullString(rset.getString(1 + i)));
					}
					if (i_count == 1) {
						this.H_table = ht; //����һ����¼����H_table��
					}
					result.add(ht);

					if (maxRow != -1 && i_count >= maxRow) {
						util_logTools.warn(
							"���ݼ�¼����maxRow:" + maxRow + "������!�����������С���ҷ�Χ!");
						break;
					}
				}
			} catch (SQLException esql) {
				throw esql;
			}

			call.close();
			conn.commit();
			closeConnection();
			s_out_Msg = s_out_ProcMsg;
			s_out_sign = s_out_ProcSign;

			long D_end=System.currentTimeMillis();	//ȡ����ʱ��
			//writelog(Sqlstate,D_start,D_end);	//��¼ʱ����Ϣ��

			return Integer.valueOf(s_out_rowCount).intValue();
		} catch (TranFailException e) {
		
			util_logTools.error(e);
			throw e;
		} catch (SQLException sqlex) {
			util_logTools.error(sqlex);
			throw new TranFailException("DBUtil037", //������룬ʹ���߿�
			getClass().getName() + ".executeProcedureMsg()", //����λ��,�����߿�
			sqlex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate);
		} catch (Exception ex) {

			util_logTools.error(ex);
			throw new TranFailException("DBUtil038", //������룬ʹ���߿�
			getClass().getName() + ".executeProcedureMsg()", //����λ��,�����߿�
			ex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + Sqlstate); //������������ʹ���߿�
		} finally {
			try {
				conn.rollback();
				if (call != null)
					call.close();
			} catch (Exception ee) {
			};
			closeConnection();
		}

	}

	/**
	 * <b>��������: </b><br>
	 * <p> ȡ�������</p>
	 * @return
	 *  
	 */
	public Vector getResult() {
		return result;
	}

	/**
	 * <b>��������: </b><br>
	 * <p>���ý������ </p>
	 * @param vector
	 *  
	 */
	private void setResult(Vector vector) {
		result = vector;
	}

	/**
	 * <b>��������: </b><br>
	 * <p>ִ�е�һSQL���Ľӿڣ�ֻ֧�ּ򵥲�ѯ��SQL��ֻ֧��STRING���͵Ĳ�����
	 * ֱ�ӷ��ص�һֵ�����,���Դ�result��ȡ�󼸸��ֶε�ֵ. </p>
	 * @param SQL �󶨱�����SQL
	 * @param vParam ����ֵ 
	 * @return ��һ�ֶε�ֵ
	 *  
	 */
	public String executeSQL(String SQL, Vector vParam)
		throws TranFailException {
		String s_ret = "";
		//		String s_sql = SQL.toUpperCase();
		String s_sql = SQL;
		Vector v_field;
		PreparedStatement stmt = null;
		Hashtable ht;
		try {
			long D_start=System.currentTimeMillis();	//ȡ��ʼʱ��

			v_field = findField(s_sql);

			if (v_field == null) {
				util_logTools.error("�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL);
				throw new TranFailException("DBUtil005", //������룬ʹ���߿�
				getClass().getName() + ".executeSQL()", //����λ��,�����߿�
				"�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL, //�������ݣ������߿�
				"�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL);
			}

			getConnection(this.dbUser);

			ResultSet rs = null;
			stmt = conn.prepareStatement(s_sql);

			for (int i = 0; i < vParam.size(); i++) {
				String s_param = (String) vParam.get(i);
				stmt.setString(1 + i, s_param);
			}

			rs = stmt.executeQuery();
			result = new Vector();
			this.H_table = new Hashtable();
			int i_count = 0;
			while (rs.next()) {
				i_count++;
				s_ret = rs.getString(1);
				ht = new Hashtable();
				for (int i = 0; i < v_field.size(); i++) {

					ht.put(
						(String) v_field.get(i),
						this.replaceNullString(rs.getString(1 + i)));
				}
				if (i_count == 1) {
					this.H_table = ht; //����һ����¼����H_table��
				}
				result.add(ht);

				break; //ֻ���һ����¼
			}
			rs.close();
			stmt.close();
			closeConnection();
	
			long D_end=System.currentTimeMillis();	//ȡ����ʱ��
			//writelog(SQL,D_start,D_end);	//��¼ʱ����Ϣ��

			return s_ret; //���ص�һ�ֶε�ֵ
		} catch (TranFailException e) {
			util_logTools.error(e);
			throw e;
		} catch (SQLException sqlex) {
			util_logTools.error(sqlex);
			throw new TranFailException("DBUtil007", //������룬ʹ���߿�
			getClass().getName() + ".executeSQL()", //����λ��,�����߿�
			sqlex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + SQL);
		} catch (Exception ex) {
			util_logTools.error(ex);
			throw new TranFailException("DBUtil008", //������룬ʹ���߿�
			getClass().getName() + ".executeSQL()", //����λ��,�����߿�
			ex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + SQL); //������������ʹ���߿�
		} finally {
			try {
//				conn.rollback();
				if (stmt != null)
					stmt.close();
			} catch (Exception ee) {
			};
			closeConnection();
		}

	}

	/**
	 * <b>��������: </b><br>
	 * <p>ִ�е�һSQL���Ľӿڣ�ֻ֧�ּ򵥲�ѯ��SQL��ֻ֧��STRING���͵Ĳ�����
	 * ����һ��������� .</p>
	 * @param SQL	�󶨱�����SQL
	 * @param vParam 
	 * @return ���صĽ����
	 * @throws TranFailException
	 *  
	 */
	public Vector executeSQLResult(String SQL, Vector vParam)
		throws TranFailException {
		String s_ret = "";
		//		String s_sql = SQL.toUpperCase();
		String s_sql = SQL;
		Vector v_field;
		PreparedStatement stmt = null;
		Hashtable ht;
		try {
			long D_start=System.currentTimeMillis();	//ȡ��ʼʱ��
			
			v_field = findField(s_sql);

			if (v_field == null) {
				util_logTools.error("�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL);
				throw new TranFailException("DBUtil015", //������룬ʹ���߿�
				getClass().getName() + ".executeSQLResult()", //����λ��,�����߿�
				"�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL, //�������ݣ������߿�
				"�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL);
			}

			getConnection(this.dbUser);

			ResultSet rs = null;
			stmt = conn.prepareStatement(s_sql);

			for (int i = 0; i < vParam.size(); i++) {
				String s_param = (String) vParam.get(i);
				stmt.setString(1 + i, s_param);
			}

			rs = stmt.executeQuery();
			result = new Vector();
			int maxRow = getOperation().getMaxRowNum();
			int i_count = 0;
			while (rs.next()) {
				i_count++;
				ht = new Hashtable();
				for (int i = 0; i < v_field.size(); i++) {
					ht.put(
						(String) v_field.get(i),
						this.replaceNullString(rs.getString(1 + i)));
				}
				if (i_count == 1) {
					this.H_table = ht; //����һ����¼����H_table��
				}
				result.add(ht);
				if (i_count > maxRow) {
					util_logTools.warn("���ݼ�¼����maxRow������!");
					break;
				}
			}

			rs.close();
			stmt.close();
			closeConnection();

			long D_end=System.currentTimeMillis();	//ȡ����ʱ��
			//writelog(SQL,D_start,D_end);	//��¼ʱ����Ϣ��

			return result;
		} catch (TranFailException e) {
			util_logTools.error(e);
			throw e;
		} catch (SQLException sqlex) {
			util_logTools.error(sqlex);
			throw new TranFailException("DBUtil017", //������룬ʹ���߿�
			getClass().getName() + ".executeSQLResult()", //����λ��,�����߿�
			sqlex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + SQL);
		} catch (Exception ex) {
			util_logTools.error(ex);
			throw new TranFailException("DBUtil018", //������룬ʹ���߿�
			getClass().getName() + ".executeSQLResult()", //����λ��,�����߿�
			ex.getMessage(), //�������ݣ������߿�
			"����������SQL�Ƿ���ȷ��" + SQL); //������������ʹ���߿�
		} finally {
			try {
//				conn.rollback();
				if (stmt != null)
					stmt.close();
			} catch (Exception ee) {
			};
			closeConnection();
		}

	}

	/**
		 * <b>��������: </b><br>
		 * <p>ִ�е�һSQL���Ľӿڣ�ֻ֧�ּ򵥲�ѯ��SQL��ֻ֧��STRING���͵Ĳ�����
		 * ����һ��������� .</p>
		 * @param SQL	�󶨱�����SQL
		 * @param vParam 
		 * @return ���صĽ����
		 * @throws TranFailException
		 *  
		 */
		public Vector executeSQLResultNoLimit(String SQL, Vector vParam)
			throws TranFailException {
			String s_ret = "";
			//		String s_sql = SQL.toUpperCase();
			String s_sql = SQL;
			Vector v_field;
			PreparedStatement stmt = null;
			Hashtable ht;
			try {
				long D_start=System.currentTimeMillis();	//ȡ��ʼʱ��
			
				v_field = findField(s_sql);

				if (v_field == null) {
					util_logTools.error("�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL);
					throw new TranFailException("DBUtil015", //������룬ʹ���߿�
					getClass().getName() + ".executeSQLResult()", //����λ��,�����߿�
					"�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL, //�������ݣ������߿�
					"�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL);
				}

				getConnection(this.dbUser);

				ResultSet rs = null;
				stmt = conn.prepareStatement(s_sql);

				for (int i = 0; i < vParam.size(); i++) {
					String s_param = (String) vParam.get(i);
					stmt.setString(1 + i, s_param);
				}

				rs = stmt.executeQuery();
				result = new Vector();
				int maxRow = 500;
				int i_count = 0;
				while (rs.next()) {
					i_count++;
					ht = new Hashtable();
					for (int i = 0; i < v_field.size(); i++) {
						ht.put(
							(String) v_field.get(i),
							this.replaceNullString(rs.getString(1 + i)));
					}
					if (i_count == 1) {
						this.H_table = ht; //����һ����¼����H_table��
					}
					result.add(ht);
					if (i_count > maxRow) {
						util_logTools.warn("���ݼ�¼����maxRow������!");
						break;
					}
				}

				rs.close();
				stmt.close();
				closeConnection();

				long D_end=System.currentTimeMillis();	//ȡ����ʱ��
				//writelog(SQL,D_start,D_end);	//��¼ʱ����Ϣ��

				return result;
			} catch (TranFailException e) {
				util_logTools.error(e);
				throw e;
			} catch (SQLException sqlex) {
				util_logTools.error(sqlex);
				throw new TranFailException("DBUtil017", //������룬ʹ���߿�
				getClass().getName() + ".executeSQLResult()", //����λ��,�����߿�
				sqlex.getMessage(), //�������ݣ������߿�
				"����������SQL�Ƿ���ȷ��" + SQL);
			} catch (Exception ex) {
				util_logTools.error(ex);
				throw new TranFailException("DBUtil018", //������룬ʹ���߿�
				getClass().getName() + ".executeSQLResult()", //����λ��,�����߿�
				ex.getMessage(), //�������ݣ������߿�
				"����������SQL�Ƿ���ȷ��" + SQL); //������������ʹ���߿�
			} finally {
				try {
//					conn.rollback();
					if (stmt != null)
						stmt.close();
				} catch (Exception ee) {
				};
				closeConnection();
			}

		}
		/**
		 * <b>��������: </b><br>
		 * <p>ִ��SQL���Ľӿڣ�֧�ֶ�select��ѯ��SQL��ֻ֧��STRING���͵Ĳ�����
		 * ����һ��������� .</p>
		 * @param SQL	�󶨱�����SQL
		 * @param vParam,vselect
		 * @return ���صĽ����
		 * @throws TranFailException
		 *  
		 */
		public Vector executeSQLResult(String SQL, Vector vParam,Vector vselect)
			throws TranFailException {
			String s_ret = "";
			String s_sql = SQL;
			Vector v_field;
			PreparedStatement stmt = null;
			Hashtable ht;
			try {
				long D_start=System.currentTimeMillis();	//ȡ��ʼʱ��
			
				v_field = vselect;

				if (v_field == null) {
					util_logTools.error("�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL);
					throw new TranFailException("DBUtil015", //������룬ʹ���߿�
					getClass().getName() + ".executeSQLResult()", //����λ��,�����߿�
					"�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL, //�������ݣ������߿�
					"�Ҳ�����ȷ���ֶμ��ϣ�����SQL�Ƿ���ȷ��" + SQL);
				}

				getConnection(this.dbUser);

				ResultSet rs = null;
				stmt = conn.prepareStatement(s_sql);

				for (int i = 0; i < vParam.size(); i++) {
					String s_param = (String) vParam.get(i);
					stmt.setString(1 + i, s_param);
				}

				rs = stmt.executeQuery();
				result = new Vector();
				int maxRow = getOperation().getMaxRowNum();
				int i_count = 0;
				while (rs.next()) {
					i_count++;
					ht = new Hashtable();
					for (int i = 0; i < v_field.size(); i++) {
						ht.put(
							(String) v_field.get(i),
							this.replaceNullString(rs.getString(1 + i)));
					}
					if (i_count == 1) {
						this.H_table = ht; //����һ����¼����H_table��
					}
					result.add(ht);
					if (i_count > maxRow) {
						util_logTools.warn("���ݼ�¼����maxRow������!");
						break;
					}
				}

				rs.close();
				stmt.close();
				closeConnection();

				long D_end=System.currentTimeMillis();	//ȡ����ʱ��
				//writelog(SQL,D_start,D_end);	//��¼ʱ����Ϣ��

				return result;
			} catch (TranFailException e) {
				util_logTools.error(e);
				throw e;
			} catch (SQLException sqlex) {
				util_logTools.error(sqlex);
				throw new TranFailException("DBUtil017", //������룬ʹ���߿�
				getClass().getName() + ".executeSQLResult()", //����λ��,�����߿�
				sqlex.getMessage(), //�������ݣ������߿�
				"����������SQL�Ƿ���ȷ��" + SQL);
			} catch (Exception ex) {
				util_logTools.error(ex);
				throw new TranFailException("DBUtil018", //������룬ʹ���߿�
				getClass().getName() + ".executeSQLResult()", //����λ��,�����߿�
				ex.getMessage(), //�������ݣ������߿�
				"����������SQL�Ƿ���ȷ��" + SQL); //������������ʹ���߿�
			} finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (Exception ee) {
				};
				closeConnection();
			}

		}
	/**
	 * <b>��������: </b><br>
	 * <p>����SQL�е���SELECT FROM֮����ֶΣ�Ȼ���ŵ������з��� </p>
	 * @param s_sql
	 * @return
	 *  
	 */
	private Vector findField(String s_sql) throws Exception {
		Vector V_ret = new Vector();
		int i_sp = s_sql.toUpperCase().indexOf("SELECT");
		int i_wp = s_sql.toUpperCase().indexOf("FROM");
		if (i_sp == -1 || i_wp == -1)
			return null;
		String s_field = s_sql.substring(i_sp + 7, i_wp);

		int i_cp = -2;

		i_cp = s_field.indexOf(",");
		while (i_cp != -1) {

			String s_temp = s_field.substring(0, i_cp);
			V_ret.add(s_temp.trim());
			s_field = s_field.substring(i_cp + 1);
			i_cp = s_field.indexOf(",");
		}
		V_ret.add(s_field.trim());

		if (V_ret.size() == 0) {
			return null;
		} else {
			return V_ret;
		}

	}
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public String getS_out_sign() {
		return s_out_sign;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param string
	 *  
	 */
	private void setS_out_sign(String string) {
		s_out_sign = string;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public Hashtable getH_table() {
		return H_table;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param hashtable
	 *  
	 */
	public void setH_table(Hashtable hashtable) {
		H_table = hashtable;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public String getS_out_Msg() {
		return s_out_Msg;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param string
	 *  
	 */
	private void setS_out_Msg(String string) {
		s_out_Msg = string;
	}

    /**
     * <b>��������: </b><br>
     * <p>���ַ���Ϊ�գ��滻Ϊ"" </p>
     * @param tmpStr
     * @return
     *  
     */
    
    private String replaceNullString(String tmpStr) {
        try {
            String a = (tmpStr == null) ? "" : tmpStr;
            return a;
        } catch (Exception ex) {
            return "";
        }
    }
}
