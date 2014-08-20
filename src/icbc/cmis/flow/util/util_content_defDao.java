package icbc.cmis.flow.util;

import icbc.cmis.service.CmisDao;
import icbc.cmis.operation.*;
import java.util.*;
import java.sql.*;
import icbc.cmis.base.TranFailException;

/**
 * Title: Description: ���̶��� Copyright: Copyright (c) 2005 Company:icbcsdc
 * 
 * @author��֣�ڱ�
 * @version 1.0
 */
public class util_content_defDao extends CmisDao {

	public util_content_defDao(CmisOperation op) {
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
	 * ��ѯ�����Ѷ��壬ȱ�ٻ���
	 * 
	 * @param entcode
	 *            �ͻ���
	 * @param tradecode
	 *            �����
	 * @param flowtype
	 *            ��������
	 * @param empareacode
	 *            ������
	 * @param busitype
	 *            ҵ������
	 * @return
	 * @throws TranFailException
	 */
	public ArrayList getflowdefconten(String entcode, String tradecode,
			String flowtype, String empareacode, String busitype)
			throws TranFailException {
		ArrayList vret = new ArrayList();
		java.sql.ResultSet rsdefconten = null; //�Ѷ�������̽����
		java.sql.ResultSet rsnotdefconten = null; //δ��������̽����
		java.sql.PreparedStatement pstmt = null;
		Vector isdef = new Vector();
		Vector isnotdef = new Vector();
		Vector resultDeflist = new Vector();
		String defconten = "";
		String notdefconten = "";
		String queryStrdefconten = "";
		String queryStrnotdefconten = "";
		String process007 = "";
		String process008 = "";
		String repeatflag = ""; //���̻����Ƿ���ڱ�־0������1����
		String process006 = ""; //���̻�������
		int processnum = 0; //δ�����˴�
		String step002 = ""; //���̻��ڴ���
		String role_name = ""; //���̻�������
		int step003 = 0; //�����˴�
		int step004 = 0; //�����˴� ������step003�Ƚϴ�С

		try {
			//�Ѷ��������
			getConnection("cmis3");
			String Sqlstate = "";
			if (busitype.equals("1")) { //ί�д��������ر�ĵط�
				Sqlstate = "{ CALL pack_approvewt.proc_ctrl_spflowwt(?,?,?,?,?,?,?,?)}";
			} else {
				Sqlstate = "{ CALL pack_flow_unit.proc_ctrl_spflow(?,?,?,?,?,?,?,?)}";
			}

			CallableStatement call = null;
			Hashtable ht;
			StringBuffer param = new StringBuffer(Sqlstate);

			call = conn.prepareCall(param.toString());

			String outType = "";
			call.setString(1, entcode);
			call.setString(2, tradecode);
			call.setString(3, flowtype);
			call.setString(4, empareacode);
			call.registerOutParameter(5, Types.VARCHAR);
			call.registerOutParameter(6, Types.VARCHAR);
			call.registerOutParameter(7, Types.VARCHAR);
			call.registerOutParameter(8, Types.VARCHAR);

			call.execute();
			Vector result = new Vector();
			ResultSet rset = null;

			defconten = call.getString(7);
			notdefconten = call.getString(8);
			if (defconten == null || defconten.equals("null")) {
				defconten = "";
			}
			if (notdefconten == null || notdefconten.equals("null")) {
				notdefconten = "";
			}
			HashMap hmap = new HashMap();
			hmap.put("defconten", defconten);
			hmap.put("notdefconten", notdefconten);
			vret.add(hmap);
			return vret;
		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_defDao",
					"util_content_defDao.getflowdefconten", e.getMessage());
		} finally {
			if (rsdefconten != null)
				try {
					rsdefconten.close();
				} catch (Exception ex) {
				}
			if (rsnotdefconten != null)
				try {
					rsnotdefconten.close();
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