/*
 * �������� 2006-3-3
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */

package icbc.cmis.flow.util;

import icbc.cmis.service.CmisDao;
import icbc.cmis.operation.*;
import java.util.*;
import icbc.cmis.base.TranFailException;

/**
 * Title: Description: ��ѯ��һ�����б�,��һ��������б� Copyright: Copyright (c) 2005
 * Company:icbcsdc
 * 
 * @author��֣�ڱ�
 * @version 1.0
 */
public class util_content_choosenextDao extends CmisDao {

	public util_content_choosenextDao(CmisOperation op) {
		super(op);

	}

	private String killnull(String strin) {
		if (strin == null) {
			return "";
		} else {
			return strin;
		}
	}

	/**
	 * ȡ�û����б�
	 * 
	 * @param flowtype
	 * @param busi_type
	 * @param lang_code
	 * @return
	 * @throws TranFailException
	 */
	public ArrayList getflowlist(String flowtype, String busi_type,
			String lang_code) throws TranFailException {
		ArrayList alist = new ArrayList();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		String sql = null;
		try {
			getConnection("cmis3");
			sql = " select role_code,role_name"
					+ " from imag_role_major_new "
					+ " where lang_code = ? "
					+ "and  role_type= (select kind_type from imag_kind where kind_code=? and lang_code='"+lang_code+"') "
					+ "and role_flag2='1' order by role_code ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lang_code);
			pstmt.setString(2, flowtype);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap hmap = new HashMap();
				hmap.put("role_code", killnull(rs.getString("role_code")) + "|"
						+ killnull(rs.getString("role_name")));
				hmap.put("role_name", killnull(rs.getString("role_name")));
				alist.add(hmap);
			}
		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_choosenextDao",
					"util_content_choosenextDao.getflowlist", e.getMessage());
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
		return alist;
	}

	/**
	 * ȡ���Է��͵ĵ���
	 * 
	 * @param areacode
	 *            ������
	 * @param busi_type
	 *            ҵ�����ʣ�0����Ӫ��1��ί��
	 * @param entcode
	 *            �ͻ�����
	 * @return
	 * @throws TranFailException
	 */
	public ArrayList getnextarea(String areacode, String busi_type,
			String entcode) throws TranFailException {
		ArrayList alist = new ArrayList();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		String sql = null;
		try {
			getConnection("cmis3");

			//ȡ�������Լ�ֱ���ϼ����Լ�����Ŀ��Է��͵���
			sql = "select area_code,area_name "
					+ "from mag_area "
					+ "where area_code = ? or area_code=(select belong_bank from mag_area where area_code=?) "
					;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, areacode);
			pstmt.setString(2, areacode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap hmap = new HashMap();
				hmap.put("area_code", killnull(rs.getString("area_code")) + "|"
						+ killnull(rs.getString("area_name")));
				hmap.put("area_name", killnull(rs.getString("area_name")));
				alist.add(hmap);
			}
		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_choosenextDao",
					"util_content_choosenextDao.getnextarea", e.getMessage());
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
		return alist;
	}
}