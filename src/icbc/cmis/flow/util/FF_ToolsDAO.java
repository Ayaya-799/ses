package icbc.cmis.flow.util;

import java.sql.*;
import icbc.cmis.service.CmisDao;
import icbc.cmis.base.*;
import java.util.*;

public class FF_ToolsDAO extends CmisDao {
	public FF_ToolsDAO(icbc.cmis.operation.CmisOperation op) throws SQLException {
		super(op);
	}

	/**
	 * func:ȡ��ҵȫ�ƺͼ��
	 * @param str_entCode  ��ҵ����
	 * @return
	 * @throws TranFailException
	 * @throws Exception
	 */
	public Hashtable getEnterpriseInfo(String str_entCode) throws TranFailException {
		String queryStr = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Hashtable h_tmp = new Hashtable();

		try {
			queryStr = "select ta200011003,ta200011004 from ta200011 where ta200011001 =? ";
			getConnection();
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, str_entCode);
			rs = stmt.executeQuery();
			if (rs.next()) {
				h_tmp.put("TA200011003", FF_Tools.getRsString(rs, "TA200011003"));
				h_tmp.put("TA200011004", FF_Tools.getRsString(rs, "TA200011004"));
			} else {
				throw new TranFailException("FF0001", "icbc.cmis.FF.DAO.FF_ToolsDAO", "��ҵ��" + str_entCode + " �����ڣ�");
			}
			rs.close();
			stmt.close();
			return h_tmp;
		} catch (SQLException ex) {
			throw new TranFailException("FF0002", "icbc.cmis.FF.DAO.FF_ToolsDAO", queryStr + ex.getSQLState() + ex.getErrorCode());
		} catch (Exception ex) {
			throw new TranFailException("FF0003", "icbc.cmis.FF.DAO.FF_ToolsDAO", ex.getMessage());
		} finally {
			closeConnection();
		}
	}

	/**
	 * ȡ��������
	 * @param areaCode
	 * @return
	 * @throws TranFailException
	 */
	public String getAreaName(String areaCode) throws TranFailException {
		String queryStr = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String areaName = "";

		try {
			queryStr = "select area_name from mag_area where area_code = ?";
			getConnection();
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, areaCode);
			rs = stmt.executeQuery();
			if (rs.next()) {
				areaName = rs.getString(1).trim();
			} else {
				throw new TranFailException("FF0001", "icbc.cmis.FF.DAO.FF_ToolsDAO", "�������룺" + areaCode + " �����ڣ�");
			}
			rs.close();
			stmt.close();
			return areaName;
		} catch (SQLException ex) {
			throw new TranFailException("FF0002", "icbc.cmis.FF.DAO.FF_ToolsDAO", queryStr + ex.getSQLState() + ex.getErrorCode());
		} catch (Exception ex) {
			throw new TranFailException("FF0003", "icbc.cmis.FF.DAO.FF_ToolsDAO", ex.getMessage());
		} finally {
			closeConnection();
		}
	}

	/**
	 * ȡ��Ա����
	 * @param areaCode
	 * @param empCode
	 * @return
	 * @throws TranFailException
	 */
	public String getEmpName(String areaCode, String empCode) throws TranFailException {
		String queryStr = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String empName = "";

		try {
			/*
			 * �޸�ʱ�䣺20040603
			 * �޸��ˣ����M
			 * �޸����ݣ�ԭ������ȡ��Ա����ʱ�����˵������룬����ù�Ա����ת�ƣ��޷���ѯ����Ա����
			 */
			queryStr = "select employee_name from mag_employee where employee_code = ? ";
			getConnection();
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, empCode);
			//stmt.setString(2,areaCode);
			rs = stmt.executeQuery();
			if (rs.next()) {
				empName = rs.getString(1).trim();
			} else {
				empName = "";
			}
			rs.close();
			stmt.close();
			return empName;
		} catch (SQLException ex) {
			throw new TranFailException("FF0002", "icbc.cmis.FF.DAO.FF_ToolsDAO", queryStr + ex.getSQLState() + ex.getErrorCode());
		} catch (Exception ex) {
			throw new TranFailException("FF0003", "icbc.cmis.FF.DAO.FF_ToolsDAO", ex.getMessage());
		} finally {
			closeConnection();
		}
	}

	/**
	 * ���ݵ����š�רҵ�š�ҵ������ȡ�������̶���
	 * @param areaCode
	 * @param major
	 * @param magKind
	 * @return
	 * @throws TranFailException
	 */
	public String getMprocessStep(String areaCode, String major, String magKind) throws TranFailException {
		String queryStr = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String step = "";
		String major_name = "";
		String kind_name = "";
		try {
			getConnection();
			queryStr = "select major_name from mag_major where major_code = ?";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, major);
			rs = stmt.executeQuery();
			if (rs.next()) {
				major_name = (String)rs.getString(1);
			} else {
				major_name = major;
			}

			queryStr = "select kind_name from mag_kind where kind_code = ?";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, magKind);
			rs = stmt.executeQuery();
			if (rs.next()) {
				kind_name = (String)rs.getString(1);
			} else {
				kind_name = magKind;
			}

			queryStr = "select step004 from mprocess_step where step001 = ? and step003 = ?";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, areaCode);
			stmt.setString(2, magKind);
			rs = stmt.executeQuery();
			if (rs.next()) {
				step = (String)rs.getString(1);
			} else {
				throw new TranFailException(
					"FF0001",
					"icbc.cmis.FF.DAO.FF_CreditApproveToolsDAO",
					"�������̻��ڶ����δ����! ������:" + areaCode + "רҵ:" + major_name + "ҵ������:" + kind_name,
					"");
			}
			rs.close();
			stmt.close();
			return step;
		} catch (SQLException ex) {
			throw new TranFailException(
				"FF0002",
				"icbc.cmis.FF.DAO.FF_CreditApproveToolsDAO",
				queryStr + ex.getSQLState() + ex.getErrorCode());
		} catch (Exception ex) {
			throw new TranFailException("FF0003", "icbc.cmis.FF.DAO.FF_CreditApproveToolsDAO", ex.getMessage());
		} finally {
			closeConnection();
		}
	}

}