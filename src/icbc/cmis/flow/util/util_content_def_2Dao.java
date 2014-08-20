package icbc.cmis.flow.util;

import oracle.jdbc.driver.*;
import icbc.cmis.service.CmisDao;
import icbc.cmis.operation.*;

import java.util.*;
import icbc.cmis.base.TranFailException;

/**
 * ֧�ֵͷ���ҵ�����������ҵ�����ж�
 * 
 * @author zjfh-zhangyz
 * 
 * ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */

public class util_content_def_2Dao extends CmisDao {

	public util_content_def_2Dao(CmisOperation op) {
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
	 * ��ѯ���̶��壬֧�ֵͷ����жϣ���ʱ��Ӫ��ʹ��
	 * 
	 * @param entcode
	 *            �ͻ���
	 * @param tradecode
	 *            �����
	 * @param flowtype
	 *            ��������
	 * @param empareacode
	 *            ��Ա����
	 * @param tradetype
	 *            ��������
	 * @param LangCode
	 *            ���Ա�־
	 * @return
	 * @throws TranFailException
	 */
	public HashMap getflowdefconten(String entcode, String tradecode,
			String flowtype, String empareacode, String tradetype,
			String langcode) throws TranFailException {
		HashMap hmap = new HashMap();
		java.sql.ResultSet rs = null;
		java.sql.CallableStatement stmt_call = null;

		String defconten = "";
		String notdefconten = "";

		try {
			//�Ѷ��������
			getConnection("cmis3");
			String sInfo = "";
			String Sqlstate = "";
			stmt_call = conn
					.prepareCall("{ call pack_controlparameter.proc_ywcode_2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt_call.setString(1, entcode);
			stmt_call.setString(2, tradecode);
			stmt_call.setString(3, "1");
			stmt_call.setString(4, tradetype);
			stmt_call.registerOutParameter(5, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(6, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(7, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(8, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(10, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(11, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(12, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(13, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(14, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(15, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(16, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(17, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(18, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(19, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(20, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(21, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(22, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(23, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(24, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(25, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(26, OracleTypes.VARCHAR);
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
			String slowrisk = killnull(stmt_call.getString(5)); //�ͷ��ձ�־��0Ϊ�ͷ���
			String sNewFlowtype = killnull(stmt_call.getString(28));
			sNewFlowtype = "28";
			if (slowrisk.equals("0")) {
				sInfo = "0";//�ͷ���
			} else {
				sNewFlowtype = flowtype;
				sInfo = "1";//�ǵͷ���
			}

			stmt_call = conn
					.prepareCall("{ call pack_approvepub.proc_ctrl_spflow(?,?,?,?,?,?,?,?,?)}");
			stmt_call.setString(1, entcode);
			stmt_call.setString(2, tradecode);
			stmt_call.setString(3, sNewFlowtype);
			stmt_call.setString(4, empareacode);
			stmt_call.setString(5, langcode);
			stmt_call.registerOutParameter(6, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(7, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(8, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt_call.execute();
			defconten = killnull(stmt_call.getString(8));
			notdefconten = killnull(stmt_call.getString(9));
			hmap.put("lowriskinfo", sInfo);
			hmap.put("defconten", defconten);
			hmap.put("notdefconten", notdefconten);
			return hmap;
		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_def_2Dao",
					"util_content_def2_Dao.getflowdefconten", e.getMessage());
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
	}
}