package icbc.cmis.flow.util;

import java.util.*;
import oracle.jdbc.*;
import icbc.cmis.service.CmisDao;
import icbc.cmis.base.*;
import icbc.cmis.util.DBTools;


/**
 * ��ѯ���������б�
 * 
 * @author zjfh-zhangyz 2006-2-17 / 8:53:19
 * 
 * 2007-04-28 �������ѯ�б���е��Ŀ���
 */

public class util_content_flowDao extends CmisDao {
	public util_content_flowDao(icbc.cmis.operation.CmisOperation op) {
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
	 * ��������: ��ѯ����Ϊ���黷�ڵĴ����˴���
	 * 
	 * @param entcode
	 *            //�ͻ�����
	 * @param tradecode
	 *            //ҵ�������
	 * @return
	 * @throws
	 */
	public Vector getstep(String entcode, String tradecode)
			throws TranFailException {
		String queryStr = "";

		queryStr = "select process008 " + "from mprocess_new "
				+ "where process001=? and process002=? and process005='1'";

		try {
			DBTools srv = new DBTools(this.getOperation());
			Vector param = new Vector(2);
			param.add(entcode);
			param.add(tradecode);
			Vector vOut = new Vector();
			vOut.add("process008"); //�����˴���

			Vector result = srv.executeSQLResult(queryStr, param, vOut);
			return result;

		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_flowDao",
					"util_content_flowDao.getstep", e.getMessage());
		}
	}

	/**
	 * ��������: ��ѯ����������Ϣ
	 * 
	 * @param entcode
	 *            //�ͻ�����
	 * @param tradecode
	 *            //ҵ�������
	 * @return
	 * @throws
	 */
	public Vector getqueryhistory(String entcode, String tradecode)
			throws TranFailException {
		String queryStr = "";

		queryStr = "select process005,process007,process008,employee_name,process006,process011,process012,process020,process019 "
				+ "from mprocess_new,mag_employee "
				+ "where process001=? and process002=? "
				+ "and mprocess_new.process008 = mag_employee.employee_code order by process005  ";

		try {
			DBTools srv = new DBTools(this.getOperation());
			Vector param = new Vector(2);
			param.add(entcode);
			param.add(tradecode);
			Vector vOut = new Vector();
			vOut.add("process005"); //���
			vOut.add("process007"); //�����˵���
			vOut.add("process008"); //�����˴���
			vOut.add("employee_name"); //����������
			vOut.add("process006"); //�����˻���
			vOut.add("process011"); //���
			vOut.add("process012"); //�ǵ�����Ϊ���˵��
			vOut.add("process020"); //�����������
			vOut.add("process019"); // ������ʾ���
			//vOut.add("process021");//��������������������
			Vector result = srv.executeSQLResult(queryStr, param, vOut);
			return result;

		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_flowDao",
					"util_content_flowDao.getqueryhistory", e.getMessage());
		}
	}

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @param entcode
	 * @param tradecode
	 * @return
	 * @throws TranFailException
	 */
	public ArrayList queryhistory(String entcode, String tradecode,
			String flowtype, String employeecode, String empareacode)
			throws TranFailException {
		ArrayList vret = new ArrayList();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		java.sql.CallableStatement stmt_call = null;
		String sql = null;
		try {
			//�ȵõ�һ�Ŷ�Ӧ��
			HashMap flowmap = new HashMap();
			flowmap = makeflowdict(flowtype);

			//�õ���������Ϣ
			HashMap firstmap = new HashMap();
			firstmap = getfirstflow(entcode, tradecode);
			String first_process006 = (String) firstmap.get("first_process006");
			String first_process007 = (String) firstmap.get("first_process007");
			String first_process008 = (String) firstmap.get("first_process008");

			//�õ�������Ϣ�б�
			getConnection("cmis3");
			sql = "select process005, "
					+ "process006, "
					+ "process007,(select area_name from mag_area where area_code = process007) process007_name, "
					+ "process008,(select employee_name from mag_employee where employee_code = process008) process008_name, "
					+ "process009, " + "process010, " + "process011, "
					+ "process021, "
					+ "to_date(process022, 'YYYYMMDDHH24MISS') process022 "
					+ "from mprocess_new " + "where process001 = ? "
					+ "and process002 = ? order by process005 ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, entcode);
			pstmt.setString(2, tradecode);
			rs = pstmt.executeQuery();

			//����һ��hashmap���������Ѿ��õ����ƽ���ļ�¼
			HashMap hmapControl = new HashMap();

			while (rs.next()) {
				HashMap hmap = new HashMap();
				hmap.put("process005", killnull(rs.getString("process005")));
				hmap.put("process006", killnull(rs.getString("process006")));
				hmap.put("process006_name", flowmap.get(killnull(rs
						.getString("process006"))));
				hmap.put("process007", killnull(rs.getString("process007")));
				hmap.put("process007_name", killnull(rs
						.getString("process007_name")));
				hmap.put("process008", killnull(rs.getString("process008")));
				hmap.put("process008_name", killnull(rs
						.getString("process008_name")));
				hmap.put("process009", killnull(rs.getString("process009")));
				hmap.put("process010", killnull(rs.getString("process010")));
				hmap.put("process011", killnull(rs.getString("process011")));
				String isprocess021 = killnull(rs.getString("process021"));
				if (isprocess021.equals("")) {
					hmap.put("isprocess021", "0");
				} else {
					hmap.put("isprocess021", "1");
				}
				hmap.put("process022", killnull(rs.getString("process022")));
				if (first_process006.equals(rs.getString("process006"))
						&& first_process007.equals(rs.getString("process007"))
						&& first_process008.equals(rs.getString("process008"))) {
					hmap.put("isfirst", "1");
				} else {
					hmap.put("isfirst", "0");
				}
				//=====���Ʋ���=====
				String adjustflag = "0";
				String targetareacode = killnull(rs.getString("process007"));
				if (hmapControl.containsKey(targetareacode)) {
					adjustflag = (String) hmapControl.get(targetareacode);
				} else {
					/*stmt_call = conn
							.prepareCall("{call pack_flow_show.proc_flowshow_adjust(?,?,?,?,?,?,?,?)}");
					stmt_call.setString(1, employeecode); //��ǰ��Ա��
					stmt_call.setString(2, empareacode); //��ǰ��Ա���ڵ���
					stmt_call.setString(3, targetareacode); //Ŀ�����
					stmt_call.setString(4, entcode); //�ͻ���
					stmt_call.setString(5, tradecode); //�����
					stmt_call.setString(6, "0"); //0����
					stmt_call.registerOutParameter(7, OracleTypes.VARCHAR); //���,0չ�֣�1��չ�֣�-1����
					stmt_call.registerOutParameter(8, OracleTypes.VARCHAR); //������Ϣ
					stmt_call.execute();
					String str_flag = stmt_call.getString(7);
					String str_msg = stmt_call.getString(8);
					adjustflag = str_flag;
					hmapControl.put(targetareacode, str_flag);*/
				}
				//=================
				hmap.put("adjustflag", adjustflag);
				vret.add(hmap);
			}

		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_flowhistoryDao.queryhistory()", e.getMessage(), e
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
		return vret;
	}

	/**
	 * ����һ�Ż��ڴ���->�������ƵĶ�Ӧ��
	 * 
	 * @param flowtype
	 * @return
	 * @throws TranFailException
	 */
	public HashMap makeflowdict(String flowtype) throws TranFailException {
		HashMap hmap = new HashMap();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		String sql = null;
		try {
			getConnection("cmis3");
			sql = "select role_code,role_name "
					+ "from imag_role_major_new "
					+ "where role_type = (select kind_type from imag_kind where kind_code = ? and lang_code='zh_CN')  and lang_code='zh_CN'"
					+ "order by role_order";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, flowtype);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				hmap.put(killnull(rs.getString("role_code")), killnull(rs
						.getString("role_name")));
			}
			//���û�ж����������̱���Ӧ����ʾ����
			if (hmap.size() == 0) {

			}
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_flowhistoryDao.queryhistory()", e.getMessage(), e
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
		return hmap;
	}

	/**
	 * ȡ��ҵ�����˵Ĵ��룬����������
	 * 
	 * @param entcode
	 * @param tradecode
	 * @return
	 * @throws TranFailException
	 */
	public HashMap getfirstflow(String entcode, String tradecode)
			throws TranFailException {
		HashMap hmap = new HashMap();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		String sql = null;
		try {
			getConnection("cmis3");
			sql = "select process006,process007,process008 "
					+ "from mprocess_new "
					+ "where process001 = ? and process002 = ? and process005='1'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, entcode);
			pstmt.setString(2, tradecode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				hmap.put("first_process006", killnull(rs
						.getString("process006")));
				hmap.put("first_process007", killnull(rs
						.getString("process007")));
				hmap.put("first_process008", killnull(rs
						.getString("process008")));
			} else {
				hmap.put("first_process006", "");
				hmap.put("first_process007", "");
				hmap.put("first_process008", "");
			}
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_flowhistoryDao.queryhistory()", e.getMessage(), e
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
		return hmap;
	}

	/**
	 * ��������:
	 * 
	 * @param formflag
	 *            ��ѯ���־ 1,���˵�� 2,������������������������ 3.��������
	 * @param entcode
	 *            //�ͻ�����
	 * @param tradecode
	 *            //ҵ�������
	 * @param flowtype
	 *            //��������
	 * @param xh
	 *            //���
	 * @param step
	 *            //����(��Ҫ���������Ƿ��ǵ�����)
	 * @return
	 * @throws
	 */
	public Vector getcontenttxt(String formflag, String entcode,
			String tradecode, String xh, String step) throws TranFailException {
		String queryStr = "";
		if (formflag.equals("1")) {
			queryStr = " select process021"
					+ " from mprocess_new   "
					+ " WHERE process001=?  AND process002=?  AND process005=? ";
		}
		if (formflag.equals("3")) {
			queryStr = " select process020"
					+ " from mprocess_new   "
					+ " WHERE process001=?  AND process002=?   AND process005=? ";
		}
		if ((formflag.equals("2")) && (step.equals("1"))) {
			queryStr = " select process012,process013,process014,process015,process016,process027,process028"
					+ " from mprocess_new   "
					+ " WHERE process001=?  AND process002=?   AND process005=? ";
		}
		if ((formflag.equals("2")) && (!step.equals("1"))) {
			queryStr = " select process012"
					+ " from mprocess_new   "
					+ " WHERE process001=?  AND process002=?   AND process005=? ";
		}
		try {
			DBTools srv = new DBTools(this.getOperation());
			Vector param = new Vector(3);
			param.add(entcode);
			param.add(tradecode);
			param.add(xh);
			Vector v_result = srv.executeSQLResult(queryStr, param);
			return v_result;
		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_flowDao",
					"util_content_flowDao.getcontenttxt", e.getMessage());
		}
	}

	/**
	 * ��������: ��advice�в�ѯ�����������
	 * 
	 * @param entcode
	 *            //�ͻ�����
	 * @param tradecode
	 *            //ҵ�������
	 * @param xh
	 *            //���
	 * @return
	 * @throws
	 */
	public Vector getadvicelist(String entcode, String tradecode, String xh)
			throws TranFailException {
		String queryStr = "";

		queryStr = " select advice004,(select area_name from mag_area where area_code = advice004) as advice004_name,"
				+ "advice005,(select employee_name from mag_employee where employee_code = advice005) as advice005_name,"
				+ "advice006,advice007,advice008"
				+ " from madvice_ass   "
				+ " WHERE  advice001=? AND advice002=?  AND advice003=?";

		try {
			DBTools srv = new DBTools(this.getOperation());
			Vector param = new Vector(3);
			param.add(entcode);
			param.add(tradecode);
			param.add(xh);
			Vector vout = new Vector();
			vout.add("advice004");
			vout.add("advice004_name");
			vout.add("advice005");
			vout.add("advice005_name");
			vout.add("advice006");
			vout.add("advice007");
			vout.add("advice008");
			Vector v_result = srv.executeSQLResult(queryStr, param, vout);
			return v_result;
		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_flowDao",
					"util_content_flowDao.getadvicelist", e.getMessage());
		}
	}

	/**
	 * ��ѯ�б�֧������
	 * 
	 * @param entcode
	 * @param tradecode
	 * @param flowtype
	 * @param employeecode
	 * @param empareacode
	 * @param langcode
	 * @return
	 * @throws TranFailException
	 */
	public ArrayList queryhistory2(String entcode, String tradecode,
			String flowtype, String employeecode, String empareacode,
			String langcode) throws TranFailException {
		ArrayList vret = new ArrayList();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		java.sql.CallableStatement stmt_call = null;
		String sql = null;
		try {
			//�ȵõ�һ�Ŷ�Ӧ��
			HashMap flowmap = new HashMap();
			flowmap = makeflowdict2(flowtype, langcode);

			//�õ���������Ϣ
			HashMap firstmap = new HashMap();
			firstmap = getfirstflow(entcode, tradecode);
			String first_process006 = (String) firstmap.get("first_process006");
			String first_process007 = (String) firstmap.get("first_process007");
			String first_process008 = (String) firstmap.get("first_process008");

			//�õ�������Ϣ�б�
			getConnection("cmis3");
			sql = "select process005, "
					+ "process006, "
					+ "process007,(select area_name from mag_area where area_code = process007) process007_name, "
					+ "process008,(select employee_name from mag_employee where employee_code = process008) process008_name, "
					+ "process009, " + "process010, " + "process011, "
					+ "process021, "
					+ "to_date(process022, 'YYYYMMDDHH24MISS') process022 "
					+ "from mprocess_new " + "where process001 = ? "
					+ "and process002 = ? order by process005 ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, entcode);
			pstmt.setString(2, tradecode);
			rs = pstmt.executeQuery();

			//����һ��hashmap���������Ѿ��õ����ƽ���ļ�¼
			HashMap hmapControl = new HashMap();

			while (rs.next()) {
				HashMap hmap = new HashMap();
				hmap.put("process005", killnull(rs.getString("process005")));
				hmap.put("process006", killnull(rs.getString("process006")));
				hmap.put("process006_name", flowmap.get(killnull(rs
						.getString("process006"))));
				hmap.put("process007", killnull(rs.getString("process007")));
				hmap.put("process007_name", killnull(rs
						.getString("process007_name")));
				hmap.put("process008", killnull(rs.getString("process008")));
				hmap.put("process008_name", killnull(rs
						.getString("process008_name")));
				hmap.put("process009", killnull(rs.getString("process009")));
				hmap.put("process010", killnull(rs.getString("process010")));
				hmap.put("process011", killnull(rs.getString("process011")));
				String isprocess021 = killnull(rs.getString("process021"));
				if (isprocess021.equals("")) {
					hmap.put("isprocess021", "0");
				} else {
					hmap.put("isprocess021", "1");
				}
				hmap.put("process022", killnull(rs.getString("process022")));
				if (first_process006.equals(rs.getString("process006"))
						&& first_process007.equals(rs.getString("process007"))
						&& first_process008.equals(rs.getString("process008"))) {
					hmap.put("isfirst", "1");
				} else {
					hmap.put("isfirst", "0");
				}
				//=====���Ʋ���=====
				String adjustflag = "0";
				String targetareacode = killnull(rs.getString("process007"));
				if (hmapControl.containsKey(targetareacode)) {
					adjustflag = (String) hmapControl.get(targetareacode);
				} else {
					stmt_call = conn
							.prepareCall("{call pack_flow_show.proc_flowshow_adjust(?,?,?,?,?,?,?,?)}");
					stmt_call.setString(1, employeecode); //��ǰ��Ա��
					stmt_call.setString(2, empareacode); //��ǰ��Ա���ڵ���
					stmt_call.setString(3, targetareacode); //Ŀ�����
					stmt_call.setString(4, entcode); //�ͻ���
					stmt_call.setString(5, tradecode); //�����
					stmt_call.setString(6, "0"); //0����
					stmt_call.registerOutParameter(7, OracleTypes.VARCHAR); //���,0չ�֣�1��չ�֣�-1����
					stmt_call.registerOutParameter(8, OracleTypes.VARCHAR); //������Ϣ
					stmt_call.execute();
					String str_flag = stmt_call.getString(7);
					String str_msg = stmt_call.getString(8);
					adjustflag = str_flag;
					hmapControl.put(targetareacode, str_flag);
				}
				//=================
				hmap.put("adjustflag", adjustflag);
				vret.add(hmap);
			}

		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_flowhistoryDao.queryhistory()", e.getMessage(), e
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
		return vret;
	}

	public HashMap makeflowdict2(String flowtype, String langcode)
			throws TranFailException {
		HashMap hmap = new HashMap();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		String sql = null;
		try {
			getConnection("cmis3");
			sql = "select role_code,role_name "
					+ "from imag_role_major_new "
					+ "where role_type = (select kind_type from mag_kind where kind_code = ?) "
					+ "and  LANG_CODE=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, flowtype);
			pstmt.setString(2, langcode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				hmap.put(killnull(rs.getString("role_code")), killnull(rs
						.getString("role_name")));
			}
			//���û�ж����������̱���Ӧ����ʾ����
			if (hmap.size() == 0) {

			}
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_flowhistoryDao.queryhistory()", e.getMessage(), e
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
		return hmap;
	}
}