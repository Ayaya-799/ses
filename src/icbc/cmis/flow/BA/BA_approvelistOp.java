package icbc.cmis.flow.BA;

import java.util.*;

import com.mk.framework.context.*;

import icbc.cmis.base.*;
import icbc.cmis.operation.*;
import icbc.cmis.tags.muiStr;

/**
 * �����Ĵ������б��ѯ
 * 
 * @author zjfh-zhangyz
 * @version 20060307
 * 
 * ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class BA_approvelistOp extends CmisOperation {
	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	private SqlTool sqltool = null;

	public BA_approvelistOp() {
		super();
	}

	private void init() throws Exception {
		sqltool = new SqlTool(this);
	}

	private String getLangCode() throws Exception {
		String LangCode = "zh_CN";
		return LangCode;
	}

	public void execute() throws java.lang.Exception, TranFailException {
		try {
			String opAction = this.getStringAt("opAction");

			if (opAction.equals("getmylist")) { //��ѯ�����˴����б�
				getmylist();
			} else if (opAction.equals("qotherpageme")) { //���˴����б�ҳ
				qotherpageme();
			} else if (opAction.equals("getourlist")) { //��ѯ�����д����б�
				getourlist();
			} else if (opAction.equals("qotherpageus")) { //���д����б�ҳ
				qotherpageus();
			} else if (opAction.equals("checkapply")) { //��������������У��
				checkapply();
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
	 * �õ������˴���������б�
	 * 
	 * @throws Exception
	 */
	public void getmylist() throws Exception {
		try {
			UserContext ucontext = ContextFacade.getUserContext();
			String employeecode =ucontext.getUserId(); //��Ա��
			String empareacode = ucontext.getCompanyid(); //��Ա��������
			
			String ordercode = this.getStringAt("ordercode"); //��ǰ���ڴ���
			String busitype = this.getStringAt("busitype"); //ҵ�����ʣ�0��Ӫ��1ί�У�2����
			String runproc = ""; //�Ƿ���ô洢���̲�ѯδ������ҵ��0��1��
			try {
				runproc = this.getStringAt("runproc");
			} catch (Exception eee) {
				runproc = "0"; //Ĭ�ϲ����ô洢����
			}
			this.setFieldValue("runproc", runproc);

			String spflag = ""; //�ر��ǣ�ϸ�ֻ���
			try {
				spflag = this.getStringAt("spflag");
			} catch (Exception eee) {
				spflag = "1";
			}
			this.setFieldValue("spflag", spflag);

			String now_page_me = ""; //ҳ��
			try {
				now_page_me = this.getStringAt("now_page_me");
			} catch (Exception eee) {
				now_page_me = "1";
			}
			this.setFieldValue("now_page_me", now_page_me);

			BA_approvelistDao dao = new BA_approvelistDao(this);
			ArrayList alist = dao.querylist_me(employeecode, empareacode,
					ordercode, runproc, busitype);
			this.setFieldValue("alist", alist);

			String max_num = alist.size() + "";
			this.setFieldValue("max_num", max_num); //����

			if (busitype.equals("0")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvelist_me.jsp");
			} else if (busitype.equals("1")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvewt_me.jsp");
			} else if (busitype.equals("2")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvesx_me.jsp");
			} else if (busitype.equals("4")) { //����
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvebb_me.jsp");
			} else {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvelist_me.jsp");
			}

			//this.setOperationDataToSession();
		} catch (TranFailException ee) {
			//this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approvelistOp.saveinfo()", e.getMessage(), e
							.getMessage());
		}
	}

	/**
	 * ���˴����б�ҳ
	 * 
	 * @throws Exception
	 */
	public void qotherpageme() throws Exception {
		try {
			String now_page_me = this.getStringAt("now_page_me"); //ҳ��
			String busitype = this.getStringAt("busitype"); //ҵ�����ʣ�0��Ӫ��1ί�У�2����
			this.setFieldValue("now_page_me", now_page_me);
			if (busitype.equals("0")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvelist_me.jsp");
			} else if (busitype.equals("1")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvewt_me.jsp");
			} else if (busitype.equals("2")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvesx_me.jsp");
			} else if (busitype.equals("4")) { //����
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvebb_me.jsp");
			} else {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvelist_me.jsp");
			}
			this.setOperationDataToSession();
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approvelistOp.saveinfo()", e.getMessage(), e
							.getMessage());
		}
	}

	/**
	 * �õ������д���������б�
	 * 
	 * @throws Exception
	 */
	public void getourlist() throws Exception {
		try {
			String employeecode = (String) this.getSessionData("EmployeeCode"); //��Ա��
			String empareacode = (String) this.getSessionData("AreaCode"); //��Ա��������
			String ordercode = this.getStringAt("ordercode"); //��ǰ���ڴ���
			String busitype = this.getStringAt("busitype"); //ҵ�����ʣ�0��Ӫ��1ί�У�2����
			String runproc = ""; //�Ƿ���ô洢���̲�ѯδ������ҵ��0��1��
			try {
				runproc = this.getStringAt("runproc");
			} catch (Exception eee) {
				runproc = "0"; //Ĭ�ϲ����ô洢����
			}
			this.setFieldValue("runproc", runproc);

			String spflag = ""; //�ر��ǣ�ϸ�ֻ���
			try {
				spflag = this.getStringAt("spflag");
			} catch (Exception eee) {
				spflag = "1";
			}
			this.setFieldValue("spflag", spflag);

			String now_page_us = ""; //ҳ��
			try {
				now_page_us = this.getStringAt("now_page_us");
			} catch (Exception eee) {
				now_page_us = "1";
			}
			this.setFieldValue("now_page_us", now_page_us);

			BA_approvelistDao dao = new BA_approvelistDao(this);
			ArrayList alist = dao.querylist_us(empareacode, employeecode,
					ordercode, runproc, busitype);

			this.setFieldValue("alist", alist);
			String max_num = alist.size() + "";
			this.setFieldValue("max_num", max_num); //����

			if (busitype.equals("0")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvelist_us.jsp");
			} else if (busitype.equals("1")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvewt_us.jsp");
			} else if (busitype.equals("2")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvesx_us.jsp");
			} else if (busitype.equals("4")) { //����
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvebb_us.jsp");
			} else {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvelist_us.jsp");
			}

			this.setOperationDataToSession();
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approvelistOp.saveinfo()", e.getMessage(), e
							.getMessage());
		}
	}

	/**
	 * ���д����б�ҳ
	 * 
	 * @throws Exception
	 */
	public void qotherpageus() throws Exception {
		try {
			String now_page_us = this.getStringAt("now_page_us"); //ҳ��
			String busitype = this.getStringAt("busitype"); //ҵ�����ʣ�0��Ӫ��1ί�У�2����
			this.setFieldValue("now_page_us", now_page_us);
			if (busitype.equals("0")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvelist_us.jsp");
			} else if (busitype.equals("1")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvewt_us.jsp");
			} else if (busitype.equals("2")) {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvesx_us.jsp");
			} else if (busitype.equals("4")) { //����
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvebb_us.jsp");
			} else {
				this.setReplyPage(webBasePath
						+ "/flow/B/BA/BA_approvelist_us.jsp");
			}
			this.setOperationDataToSession();
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approvelistOp.saveinfo()", e.getMessage(), e
							.getMessage());
		}
	}

	/**
	 * ������У��
	 * 
	 * @throws Exception
	 */
	public void checkapply() throws Exception {
		try {
			String employeecode = (String) this.getSessionData("EmployeeCode"); //��Ա��
			String empareacode = (String) this.getSessionData("AreaCode"); //��Ա��������
			String chk_entcode = this.getStringAt("chk_entcode"); //�ͻ���
			String chk_tradecode = this.getStringAt("chk_tradecode"); //�����
			String chk_tradetype = this.getStringAt("chk_tradetype"); //��������
			String chk_ordercode = this.getStringAt("chk_ordercode"); //��������

			BA_approvelistDao dao = new BA_approvelistDao(this);

			HashMap hmap = dao.checkapply(chk_entcode, chk_tradecode,
					empareacode, employeecode, chk_ordercode, chk_tradetype);

			String o_opinion = (String) hmap.get("o_opinion");
			String o_message = (String) hmap.get("o_message");

			muiStr muistr = new muiStr("icbc.cmis.flow.BA.flow_BA", this
					.getLangCode());

			if (o_opinion.equals("-1")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
						+ muistr.getStr("C000019")
						+ "��"
						+ o_message
						+ "</error>");
			}
			if (o_opinion.equals("1")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
						+ muistr.getStr("C000019")
						+ "��"
						+ o_message
						+ "</error>");
			}
			if (o_opinion.equals("2")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><hint>"
						+ muistr.getStr("C000018")
						+ "��"
						+ o_message
						+ "</hint>");
			}
			if (o_opinion.equals("0")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>"
						+ o_message + "</info>");
			}
			this.setOperationDataToSession();
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approvelistOp.saveinfo()", e.getMessage(), e
							.getMessage());
		}
	}

}