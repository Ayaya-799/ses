package icbc.cmis.flow.util;
import java.util.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;
import icbc.cmis.base.*;

/**
 * ͨ�ã���һ�����ѯ����
 * @author zjfh-zhangyz
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_content_msg_geneOp extends icbc.cmis.operation.CmisOperation {
	public util_content_msg_geneOp() {
		super();
	}

	private String webBasePath = (String)CmisConstance.getParameterSettings().get("webBasePath");

	public void execute() throws Exception, icbc.cmis.base.TranFailException {
		String action = getStringAt("opAction");
		try {
			if (action.equals("10001")) { //��ѯ������޸�
				querymsgformodify();
			} else if (action.equals("10002")) { //������ѯ���
				querymsgonly();
			} else if (action.equals("20001")) { //�������
				savemsg();
			} else if (action.equals("30001")) { //У�����
				checkmsg();
			}
			//setOperationDataToSession();
		} catch (Exception ex) {
			//setOperationDataToSession();
			throw new TranFailException("util_content_msg_geneOp", "execute()", ex.getMessage(), ex.getMessage());
		}
	}

	/**
	 * ��ѯ������޸�
	 * @throws TranFailException
	 */
	public void querymsgformodify() throws TranFailException {
		try {
			//���ò�ѯ
			DBProcedureParamsDef def = new DBProcedureParamsDef("pack_approve_msg.query_msgformodify");
			def.addInParam("msg_g_entcode"); //�ͻ���
			def.addInParam("msg_g_tradecode"); //�����
			def.addInParam("msg_g_ordernum"); //���
			def.addInParam("msg_g_employeecode"); //��Ա
			def.addOutParam("out_flag");
			def.addOutParam("out_msg");
			DBProcedureAccessService proceSrv = new DBProcedureAccessService(this);
			proceSrv.executeProcedure(this.getOperationData(), def);
			proceSrv = null;
			String out_flag = this.getStringAt("out_flag");
			String out_msg = this.getStringAt("out_msg");
			this.setReplyPage(webBasePath + "/flow/util/util_content_msg_gene_input.jsp"); //�޸�ҳ��

			//this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util", "util_content_msg4.querymsg", e.getMessage());
		}
	}

	/**
	 * ������ѯ���
	 * @throws TranFailException
	 */
	public void querymsgonly() throws TranFailException {
		try {
			//���ò�ѯ
			DBProcedureParamsDef def = new DBProcedureParamsDef("pack_approve_msg.query_msgonly");
			def.addInParam("msg_g_entcode"); //�ͻ���
			def.addInParam("msg_g_tradecode"); //�����
			def.addInParam("msg_g_ordernum"); //���
			def.addOutParam("out_flag");
			def.addOutParam("out_msg");
			DBProcedureAccessService proceSrv = new DBProcedureAccessService(this);
			proceSrv.executeProcedure(this.getOperationData(), def);
			proceSrv = null;
			String out_flag = this.getStringAt("out_flag");
			String out_msg = this.getStringAt("out_msg");
			this.setReplyPage(webBasePath + "/flow/util/util_content_msg_gene_query.jsp"); //��ѯҳ��

			//this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util", "util_content_msg4.querymsg", e.getMessage());
		}
	}

	/**
	 * �������
	 * @throws TranFailException
	 */
	public void savemsg() throws TranFailException {
		try {
			//���ñ���
			util_content_msg_geneDao dao = new util_content_msg_geneDao(this);
			String msg_g_entcode = this.getStringAt("msg_g_entcode");
			String msg_g_tradecode = this.getStringAt("msg_g_tradecode");
			String msg_g_flowtype = this.getStringAt("msg_g_flowtype");
			String msg_g_tradetype = this.getStringAt("msg_g_tradetype");
			String msg_g_ordernum = this.getStringAt("msg_g_ordernum");
			String msg_g_ordercode = this.getStringAt("msg_g_ordercode");
			String msg_g_empareacode = this.getStringAt("msg_g_empareacode");
			String msg_g_employeecode = this.getStringAt("msg_g_employeecode");
			String msg_g_msginput = this.getStringAt("msg_g_msginput");
			HashMap hmap =
				dao.savemsg(
					msg_g_entcode,
					msg_g_tradecode,
					msg_g_flowtype,
					msg_g_tradetype,
					msg_g_ordernum,
					msg_g_ordercode,
					msg_g_empareacode,
					msg_g_employeecode,
					msg_g_msginput);

			String out_flag = (String)hmap.get("result");
			String out_msg = (String)hmap.get("reinfo");
			if (out_flag.equals("0")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>ok</info>");
			} else {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>" + out_msg + "</error>");
			}
			//this.setOperationDataToSession();
		} catch (TranFailException e) {
			//this.setOperationDataToSession();
			throw e;
		} catch (Exception e) {
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>" + e.getMessage() + "</error>");
		}
	}

	/**
	 * �������У��
	 * @throws Exception
	 */
	public void checkmsg() throws Exception {
		try {
			DBProcedureParamsDef def = new DBProcedureParamsDef("pack_approve_msg.check_msg");
			def.addInParam("check_ent");
			def.addInParam("check_app");
			def.addInParam("check_num");
			def.addOutParam("out_flag");
			def.addOutParam("out_msg");
			DBProcedureAccessService proceSrv = new DBProcedureAccessService(this);
			proceSrv.executeProcedure(this.getOperationData(), def);
			proceSrv = null;
			String out_flag = this.getStringAt("out_flag");
			String out_msg = this.getStringAt("out_msg");
			if (out_flag.equals("0")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>ok</info>");
			} else {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>" + out_msg + "</error>");
			}
			this.setOperationDataToSession();
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			this.setOperationDataToSession();
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>" + e.getMessage() + "</error>");
		}
	}

}