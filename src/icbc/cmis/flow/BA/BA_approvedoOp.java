package icbc.cmis.flow.BA;

import java.util.*;

import com.mk.framework.context.ContextFacade;
import com.mk.framework.context.UserContext;

import icbc.cmis.base.*;
import icbc.cmis.operation.*;

/**
 * ��������
 * 
 * @author zjfh-zhangyz
 * 
 * ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class BA_approvedoOp extends CmisOperation {
	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	private SqlTool sqltool = null;

	public BA_approvedoOp() {
		super();
	}

	private void init() throws Exception {
		sqltool = new SqlTool(this);
	}

	public void execute() throws java.lang.Exception, TranFailException {
		try {
			String opAction = this.getStringAt("opAction");

			if (opAction.equals("showinfo")) { //����������Ϣҳ��
				showinfo();
			} else if (opAction.equals("showcreditlike")) { //̨����Ϣҳ��
				showcreditlike();
			} else if (opAction.equals("showdeal")) { //����ҳ��
				showdeal();
			} else if (opAction.equals("showextra")) { //��չҳ��
				showextra();
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
	 * ����������Ϣҳ��
	 * 
	 * @throws Exception
	 */
	public void showinfo() throws Exception {
		try {
			UserContext ucontext = ContextFacade.getUserContext();
			String employeecode =ucontext.getUserId(); //��Ա��
			String empareacode = ucontext.getCompanyid(); //��Ա��������
			
			String approve_entcode = this.getStringAt("approve_entcode"); //�ͻ���
			String approve_entname = this.getStringAt("approve_entname"); //�ͻ�����
			String approve_tradecode = this.getStringAt("approve_tradecode"); //�����
			String approve_tradetype = this.getStringAt("approve_tradetype"); //��������
			String approve_tradetypename = this
					.getStringAt("approve_tradetypename"); //������������
			String approve_flowtype = this.getStringAt("approve_flowtype"); //��������
			String approve_flowtypename = this
					.getStringAt("approve_flowtypename"); //������������
			String approve_ordernum = this.getStringAt("approve_ordernum"); //���ں�
			String approve_ordercode = this.getStringAt("approve_ordercode"); //���ڴ���
			String approve_busitype = this.getStringAt("approve_busitype"); ////ҵ�����ʣ�0����Ӫ��1��ί��
			String approve_spflag = this.getStringAt("approve_spflag"); //ϸ�ֻ��ڱ�־
			String approve_returnurl = this.getStringAt("approve_returnurl"); //���ص�ַ

			//ȡ��������
			BA_approvedoDao dao = new BA_approvedoDao(this);
			String approve_ordername = dao.getordername(approve_flowtype,
					approve_ordercode);
			this.setFieldValue("approve_ordername", approve_ordername); //��������

			//�Ƿ�����
			String approve_isfirst = dao.checkfirst(approve_entcode,
					approve_tradecode, approve_ordercode, empareacode,
					employeecode);
			this.setFieldValue("approve_isfirst", approve_isfirst); //�Ƿ��𻷽�

			//ȡ��������tab
			getinfotabmane();

			//ȡURL
			String infourl = dao.getinfourl(approve_tradetype, approve_entcode,
					approve_entname, approve_tradecode);

			if (!infourl.equals("")) {
				this.setFieldValue("infourl", webBasePath + infourl);
			} else {
				this.setFieldValue("infourl", "");
			}

			//ȡ��չ
			makeextratab();

			//ȡ̨��
			isshowtab2();

			this.setReplyPage(webBasePath
					+ "/flow/B/BA/BA_approve_infoframe.jsp");
			//this.setOperationDataToSession();
		} catch (TranFailException ee) {
			//this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approveOp.showinfo()", e.getMessage(), e.getMessage());
		}
	}

	/**
	 * ����̨��ҳ��
	 * 
	 * @throws Exception
	 */
	public void showcreditlike() throws Exception {
		try {
			UserContext ucontext = ContextFacade.getUserContext();
			String employeecode =ucontext.getUserId(); //��Ա��
			String empareacode = ucontext.getCompanyid(); //��Ա��������

			String approve_entcode = this.getStringAt("approve_entcode"); //�ͻ���
			String approve_entname = this.getStringAt("approve_entname"); //�ͻ�����
			String approve_tradecode = this.getStringAt("approve_tradecode"); //�����
			String approve_tradetype = this.getStringAt("approve_tradetype"); //��������
			String approve_tradetypename = this
					.getStringAt("approve_tradetypename"); //������������
			String approve_flowtype = this.getStringAt("approve_flowtype"); //��������
			String approve_flowtypename = this
					.getStringAt("approve_flowtypename"); //������������
			String approve_ordernum = this.getStringAt("approve_ordernum"); //���ں�
			String approve_ordercode = this.getStringAt("approve_ordercode"); //���ڴ���
			String approve_busitype = this.getStringAt("approve_busitype"); ////ҵ�����ʣ�0����Ӫ��1��ί��
			String approve_spflag = this.getStringAt("approve_spflag"); //ϸ�ֻ��ڱ�־
			String approve_returnurl = this.getStringAt("approve_returnurl"); //���ص�ַ

			String approve_ordername = this.getStringAt("approve_ordername"); //��������
			String approve_isfirst = this.getStringAt("approve_isfirst"); //�Ƿ��𻷽�

			String returnurl = webBasePath
					+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.BA.BA_approvedoOp&opDataUnclear=true&opAction=showcreditlike"
					+ "&approve_entcode=" + approve_entcode
					+ "&approve_entname="
					+ java.net.URLEncoder.encode(approve_entname)
					+ "&approve_tradecode="
					+ java.net.URLEncoder.encode(approve_tradecode)
					+ "&approve_tradetype=" + approve_tradetype
					+ "&approve_tradetypename="
					+ java.net.URLEncoder.encode(approve_tradetypename)
					+ "&approve_flowtype=" + approve_flowtype
					+ "&approve_flowtypename="
					+ java.net.URLEncoder.encode(approve_flowtypename)
					+ "&approve_ordernum=" + approve_ordernum
					+ "&approve_ordercode=" + approve_ordercode
					+ "&approve_busitype=" + approve_busitype
					+ "&approve_spflag=" + approve_spflag
					+ "&approve_returnurl="
					+ java.net.URLEncoder.encode(approve_returnurl)
					+ "&approve_ordername=" + approve_ordername
					+ "&approve_isfirst=" + approve_isfirst;
			BA_approvedoDao dao = new BA_approvedoDao(this);
			ArrayList alist = dao.getcrediturl(approve_tradetype, returnurl,
					webBasePath, approve_entcode, approve_entname);

			this.setFieldValue("linklist", alist);

			//ȡ��������tab
			getinfotabmane();
			//ȡ��չ
			makeextratab();
			//ȡ̨��
			isshowtab2();

			this.setReplyPage(webBasePath
					+ "/flow/B/BA/BA_approve_creditlink.jsp");
			//this.setOperationDataToSession();
		} catch (TranFailException ee) {
			//this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approveOp.showcreditlike()", e.getMessage(), e
							.getMessage());
		}
	}

	/**
	 * ������������ҳ��
	 * 
	 * @throws Exception
	 */
	public void showdeal() throws Exception {
		try {
			UserContext ucontext = ContextFacade.getUserContext();
			String employeecode =ucontext.getUserId(); //��Ա��
			String empareacode = ucontext.getCompanyid(); //��Ա��������

			String approve_tradetype = this.getStringAt("approve_tradetype"); //��������
			String approve_ordercode = this.getStringAt("approve_ordercode"); //���ڴ���
			String approve_spflag = this.getStringAt("approve_spflag"); //ϸ�ֻ��ڱ�־

			BA_approvedoDao dao = new BA_approvedoDao(this);
			ArrayList ALfrag = dao.queryfragment(approve_ordercode,
					approve_spflag, approve_tradetype);
			ArrayList ALbtn = dao.querybutton(approve_ordercode,
					approve_spflag, approve_tradetype);

			this.setFieldValue("ALfrag", ALfrag);
			this.setFieldValue("ALbtn", ALbtn);

			//ȡ��������tab
			getinfotabmane();
			//ȡ��չ
			makeextratab();
			//ȡ̨��
			isshowtab2();

			this.setReplyPage(webBasePath
					+ "/flow/B/BA/BA_approve_dealmain.jsp");
			//this.setOperationDataToSession();
		} catch (TranFailException ee) {
			//this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approveOp.showdeal()", e.getMessage(), e.getMessage());
		}
	}

	public void showextra() throws Exception {
		try {
			String extrataborder = this.getStringAt("extrataborder");
			String extrataburl = this.getStringAt("extrataburl");

			this.setFieldValue("extrataborder", extrataborder);
			this.setFieldValue("extrataburl", extrataburl);

			//ȡ��������tab
			getinfotabmane();
			//ȡ��չ
			makeextratab();
			//ȡ̨��
			isshowtab2();

			this.setReplyPage(webBasePath
					+ "/flow/B/BA/BA_approve_extratab.jsp");
			//this.setOperationDataToSession();
		} catch (TranFailException ee) {
			//this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approveOp.makeextratab()", e.getMessage(), e
							.getMessage());
		}
	}

	/**
	 * ȡ��չ
	 * 
	 * @throws Exception
	 */
	public void makeextratab() throws Exception {
		try {
			String approve_entcode = this.getStringAt("approve_entcode"); //�ͻ���
			String approve_entname = this.getStringAt("approve_entname"); //�ͻ�����
			String approve_tradecode = this.getStringAt("approve_tradecode"); //�����
			String approve_tradetype = this.getStringAt("approve_tradetype"); //��������
			BA_approvedoDao dao = new BA_approvedoDao(this);
			ArrayList exttablist = dao.makeextratab(approve_entcode,
					approve_entname, approve_tradecode, approve_tradetype,
					webBasePath);
			this.setFieldValue("exttablist", exttablist);
			//this.setOperationDataToSession();
		} catch (TranFailException ee) {
			//this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approveOp.makeextratab()", e.getMessage(), e
							.getMessage());
		}
	}

	/**
	 * ȡ��������tab��
	 * 
	 * @throws Exception
	 */
	public void getinfotabmane() throws Exception {
		try {
			String approve_tradetype = this.getStringAt("approve_tradetype"); //��������
			BA_approvedoDao dao = new BA_approvedoDao(this);
			String infotabname = dao.getinfoname(approve_tradetype);
			this.setFieldValue("infotabname", infotabname);
			//this.setOperationDataToSession();
		} catch (TranFailException ee) {
			//this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approveOp.makeextratab()", e.getMessage(), e
							.getMessage());
		}
	}

	/**
	 * �Ƿ���ʾ̨��
	 * 
	 * @throws Exception
	 */
	public void isshowtab2() throws Exception {
		try {
			String approve_tradetype = this.getStringAt("approve_tradetype"); //��������
			BA_approvedoDao dao = new BA_approvedoDao(this);
			String isshowtab2 = dao.isshowtab2(approve_tradetype);
			this.setFieldValue("isshowtab2", isshowtab2);
			//this.setOperationDataToSession();
		} catch (TranFailException ee) {
			//this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approveOp.makeextratab()", e.getMessage(), e
							.getMessage());
		}
	}

}