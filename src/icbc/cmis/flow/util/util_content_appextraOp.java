package icbc.cmis.flow.util;

import java.util.*;
import java.sql.*;
import icbc.cmis.base.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;

/**
 * ��֤���ر���Ȩ
 * 
 * @author zjfh-zhangyz
 * 
 * ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_content_appextraOp extends CmisOperation {
	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	private SqlTool sqltool = null;

	public util_content_appextraOp() {
		super();
	}

	private void init() throws Exception {
		sqltool = new SqlTool(this);
	}

	public void execute() throws java.lang.Exception, TranFailException {
		try {
			String opAction = this.getStringAt("opAction");
			if (opAction.equals("querygrant")) { //��ѯ�ر���Ȩ
				querygrant();
			}
			if (opAction.equals("savegrant")) {
				savegrant();
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

	public void executeReturnNext() throws TranFailException {
		try {
			String returnPage = this.getStringAt("returnpage");
			this.setOperationDataToSession();
			this.setReplyPage(returnPage);
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("FF0001",
					"icbc.cmis.flow.util.util_content_appextraOp", e
							.getMessage());
		}

	}

	/**
	 * ȡ���ر���Ȩ�б�
	 * 
	 * @throws Exception
	 */
	public void querygrant() throws Exception {
		try {
			String employeecode = this.getStringAt("employeecode"); //��ǰ��Ա��
			String empareacode = this.getStringAt("empareacode"); //��ǰ��Ա��������
			String entcode = this.getStringAt("entcode"); //�ͻ���
			String tradecode = this.getStringAt("tradecode"); //�����
			String tradetype = this.getStringAt("tradetype"); //��������

			String grant003 = "";
			if (tradetype.equals("04")) {
				grant003 = "A1";
			}
			if (tradetype.equals("06") || tradetype.equals("20")) {
				grant003 = "A2";
			}
			if (tradetype.equals("15") || tradetype.equals("16")) {
				grant003 = "A3";
			}

			util_content_appextraDao dao = new util_content_appextraDao(this);
			HashMap happinfo = dao.getappinfo(entcode, tradecode, tradetype);

			String grant002 = (String) happinfo.get("out_areacode"); //�ͻ�������

			String grant012 = (String) happinfo.get("out_sqbz"); //��Ȩ����
			String grant004 = (String) happinfo.get("out_bzjbl"); //��֤����
			String grant016 = (String) happinfo.get("out_ywxs"); //ҵ����ʽ

			String cmisdate = CmisConstance.getWorkDate("yyyyMMdd"); //ϵͳʱ��

			StringTokenizer token012 = new StringTokenizer(grant012, "|");
			if (token012.hasMoreTokens()) {
				grant012 = token012.nextToken();
			}

			ArrayList grantlist1 = new ArrayList();
			grantlist1 = dao.getgrantlist(entcode, grant002, grant003,
					cmisdate, empareacode, grant012, grant004, grant016);
			this.setFieldValue("grantlist1", grantlist1);
			this.setFieldValue("grantreal1", grant004);

			this.setReplyPage(webBasePath
					+ "/flow/util/util_appextra_bailgrant.jsp");
			this.setOperationDataToSession();
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_appextraOp.querygrant()", e.getMessage(), e
							.getMessage());
		}
	}

	/**
	 * �����ر���Ȩʹ�ü�¼
	 * 
	 * @throws Exception
	 */
	public void savegrant() throws Exception {
		try {
			String grantnum = this.getStringAt("grantnum");
			String entcode = this.getStringAt("entcode");
			String tradecode = this.getStringAt("tradecode");
			String money = this.getStringAt("money");
			String grant006 = CmisConstance.getWorkDate("yyyyMMdd");
			util_content_appextraDao dao = new util_content_appextraDao(this);
			HashMap hmap = dao.savegrant(grantnum, entcode, tradecode, money,
					grant006);
			String result = (String) hmap.get("result");
			String reinfo = (String) hmap.get("reinfo");
			if (result.equals("0")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>ok</info>");
			} else {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
						+ reinfo + "</error>");
			}
			this.setOperationDataToSession();
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
					+ e.getMessage() + "</error>");
		}
	}

}