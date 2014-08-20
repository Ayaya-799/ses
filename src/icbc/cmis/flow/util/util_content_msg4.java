package icbc.cmis.flow.util;

import java.util.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;
import icbc.cmis.base.*;

/**
 * �����������Ƭ��
 * 
 * @author zjfh-zhangyz
 * 
 * ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_content_msg4 extends icbc.cmis.operation.CmisOperation {
	public util_content_msg4() {
		super();
	}

	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	public void execute() throws Exception, icbc.cmis.base.TranFailException {
		String action = getStringAt("opAction");
		try {
			if (action.equals("10001")) {
				querymsg();
			} else if (action.equals("20001")) {
				savemsg();
			}
			setOperationDataToSession();
		} catch (Exception ex) {
			setOperationDataToSession();
			throw new TranFailException("util_content_msg4", "execute()", ex
					.getMessage(), "�����������");
		}
	}

	public void querymsg() throws TranFailException {
		try {
			//���ò�ѯ
			DBProcedureParamsDef def = new DBProcedureParamsDef(
					"pack_approvesx.query_sxmsg");
			def.addInParam("msg4_entcode"); //�ͻ���
			def.addInParam("msg4_tradecode"); //�����
			def.addInParam("msg4_ordernum"); //���
			def.addInParam("msg4_employeecode"); //��Ա
			def.addOutParam("out_flag");
			def.addOutParam("out_msg");
			DBProcedureAccessService proceSrv = new DBProcedureAccessService(
					this);
			proceSrv.executeProcedure(this.getOperationData(), def);
			proceSrv = null;
			String out_flag = this.getStringAt("out_flag");
			String out_msg = this.getStringAt("out_msg");
			this.setReplyPage(webBasePath
					+ "/flow/util/util_content_msg4input.jsp");
			this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_msg4.querymsg", e.getMessage());
		}
	}

	public void savemsg() throws TranFailException {
		try {
			//���ò�ѯ
			DBProcedureParamsDef def = new DBProcedureParamsDef(
					"pack_approvesx.update_sxmsg");
			def.addInParam("msg4_entcode"); //�ͻ���
			def.addInParam("msg4_tradecode"); //�����
			def.addInParam("msg4_flowtype"); //��������
			def.addInParam("msg4_tradetype"); //��������
			def.addInParam("msg4_ordernum"); //���
			def.addInParam("msg4_ordercode"); //����
			def.addInParam("msg4_empareacode"); //����
			def.addInParam("msg4_employeecode"); //��Ա
			def.addInParam("ta340041013"); //���
			def.addOutParam("out_flag");
			def.addOutParam("out_msg");
			DBProcedureAccessService proceSrv = new DBProcedureAccessService(
					this);
			proceSrv.executeProcedure(this.getOperationData(), def);
			proceSrv = null;
			String out_flag = this.getStringAt("out_flag");
			String out_msg = this.getStringAt("out_msg");
			if (out_flag.equals("0")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>ok</info>");
			} else {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
						+ out_msg + "</error>");
			}
			this.setOperationDataToSession();
		} catch (TranFailException e) {
			this.setOperationDataToSession();
			throw e;
		} catch (Exception e) {
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
					+ e.getMessage() + "</error>");
		}
	}

}