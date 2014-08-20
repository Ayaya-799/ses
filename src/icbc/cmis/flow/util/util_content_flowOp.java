/*
 * �������� 2006-3-7
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */

package icbc.cmis.flow.util;

import java.util.*;
import icbc.cmis.base.*;

/**
 * @author ֣�ڱ� ����-������ʷ�б��ѯ ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_content_flowOp extends icbc.cmis.operation.CmisOperation {
	public util_content_flowOp() {
		super();
	}

	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	util_content_flowDao dao = null;

	public void execute() throws Exception, icbc.cmis.base.TranFailException {
		String action = getStringAt("opAction");
		try {

			//�鿴��ʷ�����б�
			if (action.equals("20001")) {
				queryhistory();
			} else if (action.equals("20002")) {
				queryshowcontent();
			}
			setOperationDataToSession();
		} catch (Exception ex) {
			setOperationDataToSession();
			throw new TranFailException("util_content_flow",
					"EBAlreadyApprove.execute()", ex.getMessage(), "������ʷ�б�");
		}

	}

	/**
	 * <b>��������: </b> <br>
	 * <p>
	 * ��ѯ������ʷ�б�
	 * </p>
	 * 
	 * @throws TranFailException
	 *  
	 */
	private void queryhistory() throws TranFailException {

		String entcode = getStringAt("entcode"); //�ͻ�����
		String tradecode = getStringAt("tradecode"); //ҵ�������
		String rtag = "";

		String process005 = ""; //���
		String process007 = ""; //�����˵���
		String process008 = ""; //�����˴���
		String employee_name = ""; //����������
		String process006 = ""; //�����˻���
		String process011 = ""; //���
		String process012 = ""; //�ǵ�����Ϊ���˵��
		String process020 = ""; //�����������
		String process019 = ""; // ������ʾ���
		String process021 = ""; //��������������������
		String stepprocess008 = ""; //Ϊ���黷�ڴ����˴���

		dao = new util_content_flowDao(this);
		Vector contentlist = new Vector();
		Vector steplist = new Vector();
		try {
			contentlist = dao.getqueryhistory(entcode, tradecode);
			steplist = dao.getstep(entcode, tradecode);
			this.setFieldValue("contentlist", contentlist);
			this.setFieldValue("entcode", entcode);

			for (int i = 0; i < contentlist.size(); i++) {
				Hashtable temp = (Hashtable) contentlist.get(i);
				String step = ""; //�Ƿ���黷�ڱ�־
				//recordnum=(String)temp.get(String.valueOf(i+1));
				for (int j = 0; j < steplist.size(); j++) {
					Hashtable ht = (Hashtable) steplist.get(j);
					stepprocess008 = (String) ht.get("process008");
				}
				process005 = (String) temp.get("process005");
				process007 = (String) temp.get("process007");
				process008 = (String) temp.get("process008");
				employee_name = (String) temp.get("employee_name");
				process006 = (String) temp.get("process006");
				process011 = (String) temp.get("process011");
				process012 = (String) temp.get("process012");
				process020 = (String) temp.get("process020");
				process019 = (String) temp.get("process019");
				if ((process008).equals(stepprocess008)) {
					step = "dc";
				}
				rtag += "<rec process005='" + process005 + "' process007='"
						+ process007 + "' process008='" + process008
						+ "' employee_name='" + employee_name
						+ "' process006='" + process006 + "' process011='"
						+ process011 + "' step='" + step + "'   />";

			}

			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>"
					+ rtag + "</info>");

			//this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("util_content_flow",
					"EBAlreadyApprove.queryhistory", e.getMessage());
		}
	}

	/**
	 * <b>��������: </b> <br>
	 * <p>
	 * ��ѯ��������
	 * </p>
	 * 
	 * @throws TranFailException
	 *  
	 */
	private void queryshowcontent() throws TranFailException {

		String formflag = getStringAt("formflag"); //��ѯ���־ 1,���˵��
		// 2,������������������������ 3.��������
		String entcode = getStringAt("entcode"); //�ͻ�����
		String tradecode = getStringAt("tradecode"); //ҵ�������
		String xh = getStringAt("xh"); //���
		String step = getStringAt("step"); //����(��Ҫ���������Ƿ��ǵ�����)

		dao = new util_content_flowDao(this);
		String returnURL = "";
		Vector contentlist = new Vector();
		Vector advicelist = null;
		try {
			contentlist = dao.getcontenttxt(formflag, entcode, tradecode, xh,
					step);
			if (formflag.equals("3")) {
				advicelist = dao.getadvicelist(entcode, tradecode, xh);
			}
			this.setFieldValue("contentlist", contentlist);
			this.setFieldValue("advicelist", advicelist);
			if ((formflag.equals("2")) && (step.equals("1"))) {
				setReplyPage("/icbc/cmis/flow/util/util_showcontentforsearcher_flow.jsp");
			} else {
				this.setFieldValue("formflag", formflag);
				this.setFieldValue("step", step);
				setReplyPage("/icbc/cmis/flow/util/util_showcontent_flow.jsp");
			}
			this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("util_content_flow",
					"EBAlreadyApprove.queryshowcontent", e.getMessage());
		}
	}

}