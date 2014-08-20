package icbc.cmis.flow.util;

import java.util.*;
import java.sql.*;
import icbc.cmis.base.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;

public class util_content_doassistOp extends CmisOperation {
	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	private SqlTool sqltool = null;

	public util_content_doassistOp() {
		super();
	}

	private void init() throws Exception {
		sqltool = new SqlTool(this);
	}

	public void execute() throws java.lang.Exception, TranFailException {
		try {
			String opAction = this.getStringAt("opAction");
			if (opAction.equals("asssave")) {
				asssave();
			}

		} catch (TranFailException e) {
			setErrorCode(e.getErrorCode());
			setErrorCode(((TranFailException) e).getErrorCode());
			setErrorDispMsg(e.getDisplayMessage(e.getErrorCode()));
			setErrorLocation(e.getErrorLocation());
			setErrorMessage(e.getErrorMsg());
			this.setOperationDataToSession();
			setReplyPage(webBasePath + "/error.jsp");
		} catch (Exception ee) {
			throw ee;
		}
	}

	/**
	 * ���渨���������˵��
	 * 
	 * @throws Exception
	 */
	public void asssave() throws Exception {
		String employeecode = (String) this.getSessionData("EmployeeCode"); //��Ա��
		String empareacode = (String) this.getSessionData("AreaCode"); //��Ա��������
		String sysdate = CmisConstance.getWorkDate("yyyyMMdd"); //ʱ��
		String entcode = this.getStringAt("approve_entcode"); //�ͻ���
		String tradecode = this.getStringAt("approve_tradecode"); //�����
		String ordernum = this.getStringAt("approve_ordernum"); //���ں�
		String approve_returnurl = this.getStringAt("approve_returnurl");
		String advice006 = "";
		try {
			advice006 = this.getStringAt("ass_advice006");
		} catch (Exception eee) {
			advice006 = "";
		}
		String advice008 = this.getStringAt("ass_advice008");
		util_content_doassistDao dao = new util_content_doassistDao(this);
		HashMap hmap = dao.saveass(entcode, tradecode, ordernum, employeecode,
				empareacode, advice006, advice008, sysdate);
		String result = (String) hmap.get("result");
		String info = (String) hmap.get("info");
		if (result.equals("0")) {
			this.setFieldValue("okTitle", genMsg.getErrMsg("099997"));
			this.setFieldValue("okMsg", info);
			this.setFieldValue("okReturn", approve_returnurl);
			this.setReplyPage(webBasePath + "/ok.jsp");
		} else {
			this.setFieldValue("infoTitle", genMsg.getErrMsg("099993"));
			this.setFieldValue("infoMsg", info);
			this.setFieldValue("infoReturn", approve_returnurl);
			this.setReplyPage(webBasePath + "/util/util_info.jsp");
		}
		this.setOperationDataToSession();
	}

}