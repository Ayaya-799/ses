package icbc.cmis.flow.BA;

import java.util.*;
import java.sql.*;
import icbc.cmis.base.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;

/**
 * ��������
 * @author zjfh-zhangyz
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class BA_approveOp extends CmisOperation {
	private String webBasePath = (String)CmisConstance.getParameterSettings().get("webBasePath");
	private SqlTool sqltool = null;

	public BA_approveOp() {
		super();
	}

	private void init() throws Exception {
		sqltool = new SqlTool(this);
	}

	public void execute() throws java.lang.Exception, TranFailException {
		try {
			String opAction = this.getStringAt("opAction");

			if (opAction.equals("getmylist")) { //��ѯ�����˴����б�
				getmylist();
			} else if (opAction.equals("getourlist")) { //��ѯ�����д����б�
				getourlist();
			} else if (opAction.equals("showinfo")) { //����������Ϣҳ��
				showinfo();
			} else if (opAction.equals("showcreditlike")) { //̨����Ϣҳ��
				showcreditlike();
			} else if (opAction.equals("showdeal")) { //����ҳ��
				showdeal();
			} else if (opAction.equals("showextra")) { //��չҳ��

			}

		} catch (TranFailException e) {
			setErrorCode(e.getErrorCode());
			setErrorCode(((TranFailException)e).getErrorCode());
			setErrorDispMsg(e.getDisplayMessage(e.getErrorCode()));
			setErrorLocation(e.getErrorLocation());
			setErrorMessage(e.getErrorMsg());
			this.setOperationDataToSession();
			setReplyPage(webBasePath + "/error.jsp");
		} catch (Exception ee) {
			throw ee;
		}
	}

	public void getmylist() throws Exception {
		try {
			String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա��
			String empareacode = (String)this.getSessionData("AreaCode"); //��Ա��������

			this.setReplyPage(webBasePath + "/flow/B/BA/BA_approvelist_me.jsp");
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.CCGE.EA", "EA_basicinfoOp.saveinfo()", e.getMessage(), e.getMessage());
		}
	}

	public void getourlist() throws Exception {
		try {
			String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա��
			String empareacode = (String)this.getSessionData("AreaCode"); //��Ա��������

			this.setReplyPage(webBasePath + "/flow/B/BA/BA_approvelist_us.jsp");
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.CCGE.EA", "EA_basicinfoOp.saveinfo()", e.getMessage(), e.getMessage());
		}
	}

	public void showinfo() throws Exception {
		try {
			String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա��
			String empareacode = (String)this.getSessionData("AreaCode"); //��Ա��������

			this.setReplyPage(webBasePath + "/flow/B/BA/BA_approve_infoframe.jsp");
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.CCGE.EA", "EA_basicinfoOp.saveinfo()", e.getMessage(), e.getMessage());
		}
	}

	public void showcreditlike() throws Exception {
		try {
			String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա��
			String empareacode = (String)this.getSessionData("AreaCode"); //��Ա��������

			this.setReplyPage(webBasePath + "/flow/B/BA/BA_approve_creditlink.jsp");
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.CCGE.EA", "EA_basicinfoOp.saveinfo()", e.getMessage(), e.getMessage());
		}
	}

	public void showdeal() throws Exception {
		try {
			String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա��
			String empareacode = (String)this.getSessionData("AreaCode"); //��Ա��������

			this.setReplyPage(webBasePath + "/flow/B/BA/BA_approve_dealmain.jsp");
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.CCGE.EA", "EA_basicinfoOp.saveinfo()", e.getMessage(), e.getMessage());
		}
	}

}