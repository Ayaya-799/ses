package icbc.cmis.util;

import icbc.cmis.service.CmisDao;
import icbc.cmis.base.TranFailException;
import icbc.cmis.base.CmisConstance;

import java.io.IOException;
import java.sql.*;
import java.util.Vector;
import java.util.Hashtable;
import icbc.missign.Employee;


/**
 * �����ͻ��ϲ���ϸ��ѯģ�� 200707����
 * @author ZhangDM
 */
public class ChooseEnp0707DAO extends CmisDao {
	
	public ChooseEnp0707DAO(icbc.cmis.operation.CmisOperation op) {
		super(op);
	}

	public String[] genSQL(Employee employee,String TA200011001) throws TranFailException {	
		String sql[] = {"", ""};
		String areaCode = employee.getMdbSID();			
		sql[0]="select count(*) FROM  ta20001c a,ta200011 b " +
		sql[1]="select * from (select (select ta200011001 from ta200011 " +
		return sql;
	}

	public int getRecordCount(String sql) throws TranFailException {		
		Statement stmt = null;
		ResultSet rs = null;
		int ret = 0;
		try {
			this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			ret = rs.getInt(1);
		} catch (TranFailException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new TranFailException(
				"cmisutil214",
				"icbc.cmis.util.ChooseEnp0707DAO",
				ex.getMessage() + sql,
				"��ѯ��¼��������");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				};
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception ex) {
				};
			this.closeConnection();
		}
		return ret;
	}

	public Vector query(String sql, int begin, int end)throws TranFailException {
		Vector ret = new Vector();		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			this.getConnection();			
			stmt = conn.createStatement();			
			rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				String[] row ={rs.getString(1),
						       rs.getString(2),
						       rs.getString(3),
						       rs.getString(4),
						       rs.getString(5)};				
				ret.add(row);
				i++;
			}
		} catch (TranFailException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new TranFailException(
				"cmisutil215",
				"icbc.cmis.util.ChooseEnp0707DAO",
				ex.getMessage() + sql,
				"�����ͻ��б�����");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				};			
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception ex) {
				};
			this.closeConnection();
		}
		return ret;
	}	
}