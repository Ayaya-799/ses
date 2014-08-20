/*
 * �������� 2006-3-7
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */

package icbc.cmis.flow.util;

import java.util.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;
import icbc.cmis.base.*;

/**
 * @author ֣�ڱ� ����-��ʾ���ֶ���Ϣ ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_content_assistantOp extends icbc.cmis.operation.CmisOperation {
	public util_content_assistantOp() {
		super();
	}

	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	util_content_assistantDao dao = null;

	public void execute() throws Exception, icbc.cmis.base.TranFailException {
		String action = getStringAt("opAction");
		try {
			if (action.equals("20001")) { //�鿴�������
				queryassistant();
			} else if (action.equals("10001")) { //

			} else if (action.equals("10002")) { //���͸��������Ա�͸����������˵��
				savdinput();
			} else if (action.equals("10003")) { //

			}
			setOperationDataToSession();
		} catch (Exception ex) {
			setOperationDataToSession();
			throw new TranFailException("util_content_assistant",
					"EBAlreadyApprove.execute()", ex.getMessage(), "�������");
		}

	}

	/**
	 * ��ѯ�������������Ϣ
	 * 
	 * @throws TranFailException
	 */
	private void queryassistant() throws TranFailException {
		try {
			String entcode = getStringAt("assistant_entcode"); //�ͻ�����
			String tradecode = getStringAt("assistant_tradecode"); //ҵ�������
			String ordernum = getStringAt("assistant_ordernum"); //���
			String rtag = "";
			ArrayList alist = null;
			String assinfo = "";
			util_content_assistantDao dao = new util_content_assistantDao(this);
			alist = dao.getassistant(entcode, tradecode, ordernum);
			assinfo = dao.getmyassinfo(entcode, tradecode, ordernum);
			for (int i = 0; i < alist.size(); i++) {
				HashMap hmap = new HashMap();
				hmap = (HashMap) alist.get(i);
				rtag += "<rec " + "rnum = '" + (String) hmap.get("rnum") + "' "
						+ "advice005 = '" + (String) hmap.get("advice005")
						+ "' " + "advice005_name = '"
						+ (String) hmap.get("advice005_name") + "' "
						+ "advice006 = '" + (String) hmap.get("advice006")
						+ "' " + "advice007 = '"
						+ (String) hmap.get("advice007") + "' "
						+ "advice008 = '" + (String) hmap.get("advice008")
						+ "' " + "/>";
			}

			rtag += "<myinfo>" + assinfo + "</myinfo>";
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>"
					+ rtag + "</info>");
			this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_assistantOp.queryassistant", e.getMessage());
		}
	}

	public void savdinput() throws TranFailException {
		try {
			String entcode = this.getStringAt("assistant_entcode"); //�ͻ�����
			String tradecode = this.getStringAt("assistant_tradecode"); //ҵ�������
			String tradetype = this.getStringAt("assistant_tradetype");
			String flowtype = this.getStringAt("assistant_flowtype");
			String ordernum = this.getStringAt("assistant_ordernum"); //���
			String ordercode = this.getStringAt("assistant_ordercode");
			String empareacode = this.getStringAt("assistant_empareacode");
			String employeecode = this.getStringAt("assistant_employeecode"); //��Ա��
			String assinfo = this.getStringAt("assistant_assinfo");
			String asslist = this.getStringAt("assistant_asslist"); //

			util_content_assistantDao dao = new util_content_assistantDao(this);
			HashMap hamp = dao.savemyassinfo(entcode, tradecode, tradetype,
					flowtype, ordernum, ordercode, empareacode, employeecode,
					assinfo, asslist);
			String result = (String) hamp.get("result");
			String reinfo = (String) hamp.get("reinfo");
			if (result.equals("0")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>ok</info>");
			} else {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
						+ reinfo + "</error>");
			}

		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
					+ e.getMessage() + "</error>");
		}
	}

}