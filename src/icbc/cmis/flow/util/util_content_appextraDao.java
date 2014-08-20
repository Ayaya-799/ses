package icbc.cmis.flow.util;

import java.sql.*;
import java.util.*;
import oracle.jdbc.*;
import icbc.cmis.service.CmisDao;
import icbc.missign.Employee;
import icbc.cmis.base.*;

/**
 * ��֤���ر���Ȩ�������ݿ����
 * 
 * @author zjfh-zhangyz
 * 
 * ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_content_appextraDao extends CmisDao {

	public util_content_appextraDao(icbc.cmis.operation.CmisOperation op) {
		super(op);
	}

	private String killnull(String strin) {
		if (strin == null) {
			return "";
		} else {
			return strin;
		}
	}

	public int findStr(StringBuffer strb, String str, int begin) {
		int strlen = str.length();
		int ret = -1;
		for (int i = begin; i <= strb.length() - strlen; i++) {
			if (strb.substring(i, i + strlen).equals(str)) {
				ret = i;
				break;
			}
		}
		return ret;
	}

	public String replace(String strb, String src, String des) {
		if (src.equals(des)) {
			return strb;
		}
		int i, j;
		int srclen = src.length();
		StringBuffer strb2 = new StringBuffer(strb);
		j = 0;
		while ((i = this.findStr(strb2, src, j)) != -1) {
			strb2.delete(i, i + srclen);
			strb2.insert(i, des);
			j = i + des.length();
		}
		return strb2.toString();
	}

	/**
	 * ����ر���Ȩʹ�ñ�
	 * 
	 * @param grant001
	 *            ��Ȩ���
	 * @param entcode
	 *            ��ҵ����
	 * @param tradecode
	 *            ʹ����Ȩ�ĺ�ͬ��
	 * @param money
	 *            ����ʹ����Ȩ���
	 * @param grant006
	 *            ����
	 * @throws TranFailException
	 */
	public HashMap savegrant(String grant001, String entcode, String tradecode,
			String money, String grant006) throws TranFailException,
			SQLException {
		HashMap hmap = new HashMap();
		String sql = "";
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			try {
				this.getConnection("cmis3");
				sql = "insert into mag_special_grant_use (grant001,grant002,grant003,grant004,grant005,grant006) values (?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, grant001);
				pstmt.setString(2, entcode);
				pstmt.setString(3, tradecode);
				pstmt.setString(4, money);
				pstmt.setString(5, "1");
				pstmt.setString(6, grant006);
				pstmt.executeUpdate();
				hmap.put("result", "0"); //�ɹ���־
				hmap.put("reinfo", ""); //�ɹ���Ϣ
				conn.commit();
			} catch (SQLException sqexp) {
				if (sqexp.getErrorCode() == 1) {
					conn.rollback();
					hmap.put("result", "1");
					hmap.put("reinfo", "��ǰҵ���Ѿ�ʹ����" + grant001 + "����ر���Ȩ");
				} else {
					throw sqexp;
				}
			}
		} catch (TranFailException e) {
			conn.rollback();
			throw e;
		} catch (Exception e) {
			hmap.put("result", "1");
			hmap.put("reinfo", e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			closeConnection();
		}
		return hmap;
	}

	/**
	 * ��ѯ�ñ��������ص���Ϣ
	 * 
	 * @param entcode
	 * @param tradecode
	 * @param tradetype
	 * @return
	 * @throws TranFailException
	 */
	public HashMap getappinfo(String entcode, String tradecode, String tradetype)
			throws TranFailException {
		HashMap hmap = new HashMap();
		java.sql.ResultSet rs = null;
		java.sql.CallableStatement stmt_call = null;
		try {
			this.getConnection("cmis3");
			stmt_call = conn
					.prepareCall("{call pack_controlparameter.proc_ywcode"
							+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt_call.setString(1, entcode); //�ͻ���
			stmt_call.setString(2, tradecode); //ҵ���
			stmt_call.setString(3, "1");
			stmt_call.setString(4, tradetype); //��������
			stmt_call.registerOutParameter(5, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(6, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(7, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(8, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(10, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(11, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(12, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(13, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(14, OracleTypes.NUMBER);
			stmt_call.registerOutParameter(15, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(16, OracleTypes.NUMBER);
			stmt_call.registerOutParameter(17, OracleTypes.NUMBER);
			stmt_call.registerOutParameter(18, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(19, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(20, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(21, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(22, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(23, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(24, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(25, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(26, OracleTypes.NUMBER);
			stmt_call.registerOutParameter(27, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(28, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(29, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(30, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(31, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(32, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(33, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(34, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(35, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(36, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(37, OracleTypes.VARCHAR);
			stmt_call.execute();
			hmap.put("out_lowrisk", killnull(stmt_call.getString(5))); //�ͷ��ձ�־
			hmap.put("out_ywpz", killnull(stmt_call.getString(6))); //ҵ��Ʒ��
			hmap.put("out_bz", killnull(stmt_call.getString(7))); //����
			hmap.put("out_ywje", killnull(stmt_call.getString(8))); //ҵ����
			hmap.put("out_jtdkfs", killnull(stmt_call.getString(9))); //������ʽ
			hmap.put("out_dkxs", killnull(stmt_call.getString(10))); //������ʽ
			hmap.put("out_areacode", killnull(stmt_call.getString(11))); //ҵ��������
			hmap.put("out_begindate", killnull(stmt_call.getString(12))); //ҵ��ʼʱ��
			hmap.put("out_enddate", killnull(stmt_call.getString(13))); //ҵ�����ʱ��
			hmap.put("out_bzj", killnull(stmt_call.getString(14))); //��֤����
			hmap.put("out_bzjbz", killnull(stmt_call.getString(15))); //��֤�����
			hmap.put("out_bzjbl", killnull(stmt_call.getString(16))); //��֤�����
			hmap.put("out_zcfxd", killnull(stmt_call.getString(17))); //�ʲ����ն�
			hmap.put("out_llfdz", killnull(stmt_call.getString(18))); //���ʸ���ֵ
			hmap.put("out_llzl", killnull(stmt_call.getString(19))); //��������
			hmap.put("out_dkfl", killnull(stmt_call.getString(20))); //�������
			hmap.put("out_rztx", killnull(stmt_call.getString(21))); //����Ͷ��
			hmap.put("out_oywcode", killnull(stmt_call.getString(22))); //ԭҵ�����
			hmap.put("out_sxkh", killnull(stmt_call.getString(23))); //���ſͻ�
			hmap.put("out_ywxs", killnull(stmt_call.getString(24))); //ҵ����ʽ
			hmap.put("out_sqxm", killnull(stmt_call.getString(25))); //��Ȩ��Ŀ
			hmap.put("out_fmoney", killnull(stmt_call.getString(26))); //���ս��
			hmap.put("out_fbz", killnull(stmt_call.getString(27))); //���ձ���
			hmap.put("out_lczl", killnull(stmt_call.getString(28))); //��������
			hmap.put("out_sxdkxs", killnull(stmt_call.getString(29))); //���Ŵ�����ʽ
			hmap.put("out_ifxmrz", killnull(stmt_call.getString(30))); //�Ƿ���Ŀ���ʱ�ʶ
			hmap.put("out_table", killnull(stmt_call.getString(31))); //����
			hmap.put("out_ywlx", killnull(stmt_call.getString(32))); //ҵ������
			hmap.put("out_sqbz", killnull(stmt_call.getString(33))); //��Ȩ����
			hmap.put("out_sqmoney", killnull(stmt_call.getString(34))); //��Ȩ���
			hmap.put("out_dkfs", killnull(stmt_call.getString(35))); //���ʽ

			hmap.put("out_ret", killnull(stmt_call.getString(36))); //���ر�־
			hmap.put("out_msg", killnull(stmt_call.getString(37))); //������Ϣ
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_appextraDao.getappinfo()", e.getMessage(), e
							.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (stmt_call != null)
				try {
					stmt_call.close();
				} catch (Exception ex) {
				}
			closeConnection();
		}
		return hmap;
	}

	/**
	 * ȡ���ر���Ȩ�б�
	 * 
	 * @param entcode//�ͻ���
	 * @param grant002//ҵ��������
	 * @param grant003//��Ȩ��Ŀ
	 * @param cmisdate
	 *            //ʱ��
	 * @param empareacode//��ǰ��
	 * @param grant012
	 *            //��Ȩ����
	 * @param grant004//��Ȩ���
	 * @param grant016
	 *            //ҵ����ʽ
	 * @return
	 * @throws TranFailException
	 */
	public ArrayList getgrantlist(String entcode, String grant002,
			String grant003, String cmisdate, String empareacode,
			String grant012, String grant004, String grant016)
			throws TranFailException {
		ArrayList vret = new ArrayList();
		String sql = "";
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			this.getConnection("cmis3");

			StringBuffer sbf = new StringBuffer();
			sbf
					.append(" select grant900 ,grant015 ,grant013 ,grant012 ,grant004 , grant014,(select area_name from mag_area where area_code = grant010) grant010,(select area_name from mag_area where area_code = grant009) grant009,grant005,grant006 ");
			sbf.append(" from mag_special_grant ");
			sbf
					.append(" where grant001=? and grant002 = ? and grant003=? and grant004<=? and grant005<=? and grant006>?  and grant013='0'  ");
			//if (!stoparea.equals("")) {
			//	sbf.append(
			//		" AND grant009 IN (SELECT area_code FROM mag_area START WITH
			// area_code = ? CONNECT BY PRIOR belong_bank = area_code) ");
			//}
			sbf.append(" UNION ");
			sbf
					.append(" select a.grant900 , a.grant015 , a.grant013 , a.grant012 , a.grant004 , grant014,(select area_name from mag_area where area_code = grant010) grant010,(select area_name from mag_area where area_code = grant009) grant009,grant005,grant006 ");
			sbf.append(" from mag_special_grant a ");
			sbf
					.append(" where a.grant001=? and a.grant002 = ? and a.grant003=? and a.grant004<=? and  not exists (SELECT * FROM mag_special_grant_use b where b.grant001=a.grant900) and a.grant005<=? and a.grant006>?  and a.grant013='1'  ");

			sql = sbf.toString();

			int i = 1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, entcode);
			pstmt.setString(i++, grant002);
			pstmt.setString(i++, grant003);
			pstmt.setString(i++, grant004);
			pstmt.setString(i++, cmisdate);
			pstmt.setString(i++, cmisdate);

			pstmt.setString(i++, entcode);
			pstmt.setString(i++, grant002);
			pstmt.setString(i++, grant003);
			pstmt.setString(i++, grant004);
			pstmt.setString(i++, cmisdate);
			pstmt.setString(i++, cmisdate);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap hmap = new HashMap();
				String temp = replace(rs.getString("grant014"), "\r\n", " ");
				hmap.put("grant900", killnull(rs.getString("grant900")));
				hmap.put("grant015", killnull(rs.getString("grant015")));
				hmap.put("grant013", killnull(rs.getString("grant013")));
				hmap.put("grant012", killnull(rs.getString("grant012")));
				hmap.put("grant004", killnull(rs.getString("grant004")));
				hmap.put("grant014", temp);
				hmap.put("grant010", killnull(rs.getString("grant010")));
				hmap.put("grant009", killnull(rs.getString("grant009")));
				hmap.put("grant005", killnull(rs.getString("grant005")));
				hmap.put("grant006", killnull(rs.getString("grant006")));
				vret.add(hmap);
			}

		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_appextraDao.getgrantlist()", e.getMessage(),
					e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			closeConnection();
		}
		return vret;
	}

	public String spellbtn(String entcode, String tradecode, String tradetype)
			throws TranFailException {
		String btnurl = "";
		String sql = "";
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			this.getConnection("cmis3");
			sql = "select adjust_url,cust_code,apply_code,adjust001 from mag_approve_adjust_apply where apply_kind=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tradetype);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String s_adjust_url = killnull(rs.getString("adjust_url"));
				String s_cust_code = killnull(rs.getString("cust_code"));
				String s_apply_code = killnull(rs.getString("apply_code"));
				String s_adjust001 = killnull(rs.getString("adjust001"));
				if (!s_adjust_url.equals("") && !s_cust_code.equals("")
						&& !s_apply_code.equals("") && !s_adjust001.equals("")) {
					btnurl = s_adjust_url + "&" + s_cust_code + "=" + entcode
							+ "&" + s_apply_code + "=" + tradecode
							+ s_adjust001;
				} else {
					btnurl = "";
				}
				return btnurl;
			} else {
				return "";
			}
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_appextraDao.spellbtn()", e.getMessage(), e
							.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ex) {
				}
			closeConnection();
		}

	}

}