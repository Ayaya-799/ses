package icbc.cmis.flow.util;

import java.util.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;
import icbc.cmis.base.*;

/**
 * @author ����-�������ɻ��������Ϣ ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 * 
 * ����ǿ����������ɻ�������Ļ���Ƭ�� ����Op 1������ҳ��ʱ�޻������
 * 
 * 2����������ɡ�ʱ��ʹ��objecthttp��ʽȥ��̨���ɻ�����������,(����ǰ��Ҫ����У�����״̬����) Ȼ��xml���ػ���������ʾ
 * 
 *  
 */
public class util_content_iamsg2 extends CmisOperation {
	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	public util_content_iamsg2() {
		super();
	}

	private void init() throws Exception {
	}

	public void execute() throws java.lang.Exception, TranFailException {
		try {
			String opAction = this.getStringAt("opAction");

			if (opAction.equals("20001")) {
				createiamsg();
			}

			this.setOperationDataToSession();
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
	 * <b>��������: </b> <br>
	 * <p>
	 * ��ѯ������ʷ�б�
	 * </p>
	 * 
	 * @throws TranFailException
	 *  
	 */
	private void createiamsg() throws TranFailException {

		String entcode = getStringAt("entcode"); //�ͻ�����
		String tradecode = getStringAt("tradecode"); //ҵ�������
		String tradetype = getStringAt("tradetype");
		String ordernum = getStringAt("ordernum");
		String flowtype = getStringAt("flowtype");
		String ordercode = getStringAt("ordercode");
		String employeecode = getStringAt("employeecode");
		String empareacode = getStringAt("empareacode");
		String errtag = ""; //������Ϣ
		String o_ret1 = "";
		String o_ret2 = "";
		String rtag = "";
		this.setFieldValue("in_entcode", entcode);
		this.setFieldValue("in_tradecode", tradecode);
		this.setFieldValue("in_tradetype", tradetype);
		this.setFieldValue("in_ordernum", ordernum);
		this.setFieldValue("in_ordercode", ordercode);
		this.setFieldValue("in_flowtype", flowtype);
		this.setFieldValue("in_employeecode", employeecode);
		this.setFieldValue("in_empareacode", empareacode);
		try {
			//ȡ�û������
			DBProcedureParamsDef def = null;
			def = new DBProcedureParamsDef("pack_ctrl_public.proc_ctrl_public");
			def.addInParam("in_entcode");
			def.addInParam("in_tradecode");

			def.addInParam("in_empareacode");
			def.addInParam("in_employeecode");
			def.addInParam("in_ordercode");
			def.addInParam("in_tradetype");
			def.addOutParam("o_opinion");
			def.addOutParam("o_firmctl");
			def.addOutParam("o_softctl");
			def.addOutParam("o_firmold");
			def.addOutParam("o_softold");
			def.addOutParam("o_stoparea");
			DBProcedureAccessService dbProcService = new DBProcedureAccessService(
					this);
			dbProcService.executeProcedure(getOperationData(), def);
			dbProcService = null;
			String o_opinion = getStringAt("o_opinion");
			String o_firmctl = getStringAt("o_firmctl");
			String o_softctl = getStringAt("o_softctl");
			String o_firmold = getStringAt("o_firmold");
			String o_softold = getStringAt("o_softold");
			String o_stoparea = getStringAt("o_stoparea");
			this.setFieldValue("o_opinion", o_opinion);
			this.setFieldValue("o_stoparea", o_stoparea);
			this.setFieldValue("in_o_firmctl", o_firmctl);
			this.setFieldValue("in_o_softctl", o_softctl);
			this.setFieldValue("in_o_firmold", o_firmold);
			this.setFieldValue("in_o_softold", o_softold);
			//����������
			DBProcedureParamsDef defcs = null;
			defcs = new DBProcedureParamsDef("pack_flow_unit.proc_savejpadvice");
			defcs.addInParam("in_entcode");
			defcs.addInParam("in_tradecode");
			defcs.addInParam("in_flowtype");
			defcs.addInParam("in_tradetype");
			defcs.addInParam("in_ordernum");
			defcs.addInParam("in_ordercode");
			defcs.addInParam("in_empareacode");
			defcs.addInParam("in_employeecode");
			defcs.addInParam("o_opinion"); //�������0������1����2��ʾ
			defcs.addInParam("in_o_firmctl"); //���Կ���˵��process018
			defcs.addInParam("in_o_softctl"); //������Ϣ˵��process019
			defcs.addInParam("in_o_firmold"); //���Կ���˵��(���ô�����)--�ϵ�process025
			defcs.addInParam("in_o_softold"); //������Ϣ˵��(���ô�����)--�ϵ�process026
			defcs.addOutParam("ret_flag");
			defcs.addOutParam("ret_mess");
			DBProcedureAccessService dbProcServicecs = new DBProcedureAccessService(
					this);
			dbProcServicecs.executeProcedure(getOperationData(), defcs);
			dbProcServicecs = null;
			String retCode = getStringAt("ret_flag");
			String retText = getStringAt("ret_mess");

			if (!retCode.equals("0")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
						+ retText + "</error>");
				return;
			} else {
				Vector vOut1 = new Vector();
				vOut1.add("o_ret1"); //�����α�(��)
				Vector vOut2 = new Vector();
				vOut2.add("o_ret2");
				//�����α�(��)
				DBProcedureParamsDef def1 = null;
				//���л���������
				def1 = new DBProcedureParamsDef("pack_explaincode.proc_analyze");
				def1.addInParam("in_o_firmctl");
				def1.addInParam("in_o_softctl");
				def1.addInParam("in_o_firmold");
				def1.addInParam("in_o_softold");

				def1.addOutParam("o_flag");
				def1.addOutParam("o_msg");
				def1.addCursorOutParams(vOut1, "iQueryResult1"); //�����α�(��)
				def1.addCursorOutParams(vOut2, "iQueryResult2"); //�����α�(��)

				DBProcedureAccessService dbProcService1 = new DBProcedureAccessService(
						this);
				dbProcService1.executeProcedure(getOperationData(), def1);
				dbProcService1 = null;
				String o_flag = getStringAt("o_flag");
				String o_msg = getStringAt("o_msg");
				IndexedDataCollection gx = getIndexedDataCollection("iQueryResult1");
				for (int i = 0; i < gx.getSize(); i++) {
					KeyedDataCollection gxelement = (KeyedDataCollection) gx
							.getElement(i);
					if (!((String) gxelement.getValueAt("o_ret1"))
							.equals("null")) {

						o_ret1 = o_ret1 + (i + 1) + "."
								+ (String) gxelement.getValueAt("o_ret1")
								+ "\n"; //����ȷ���Ƿ�Code
					}
				}
				IndexedDataCollection rx = getIndexedDataCollection("iQueryResult2");
				for (int i = 0; i < rx.getSize(); i++) {
					KeyedDataCollection rxelement = (KeyedDataCollection) rx
							.getElement(i);
					if (!((String) rxelement.getValueAt("o_ret2"))
							.equals("null")) {
						o_ret2 = o_ret2 + (i + 1) + "."
								+ (String) rxelement.getValueAt("o_ret2")
								+ "\n"; //����ȷ���Ƿ�Code
					}
				}
				if (o_flag.equals("-1")) {
					errtag += "���ɻ��������������쳣";

				}

				rtag += "<rec iamsg2process017='" + o_opinion
						+ "' o_stoparea='" + o_stoparea + "' o_ret1='" + o_ret1
						+ "' o_ret2='" + o_ret2 + "'   />";
			}
			//}

			if (!errtag.equals("")) {
				rtag += "<rec iamsg2process017='" + o_opinion
						+ "' o_stoparea='" + o_stoparea + "' o_ret1='" + o_ret1
						+ "' o_ret2='" + o_ret2 + "'   />";
			}
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>"
					+ rtag + "</info>");
		} catch (TranFailException ee) {
			throw ee;
		} catch (Exception e) {
			if (!errtag.equals("")) {
				rtag += "<error>" + "���ɻ��������������쳣" + "</error>";
			}
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
					+ e.getMessage() + "</error>");
			//throw new TranFailException("icbc.cmis.flow.util",
			// "util_content_iamsg.queryass()", e.getMessage(), e.getMessage());
		}
	}
}