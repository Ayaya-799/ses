/*
 * �������� 2006-3-2
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
public class util_content_msg2Op extends icbc.cmis.operation.CmisOperation {
	public util_content_msg2Op() {
		super();
	}

	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	util_content_msg2Dao dao = null;

	public void execute() throws Exception, icbc.cmis.base.TranFailException {
		String action = getStringAt("opAction");
		try {

			//��ѯ�������˵��
			if (action.equals("20002")) {
				queryshowadvice();
			}
			//������������
			else if (action.equals("10001")) {
				saveadviceforsearcher();
			}

			setOperationDataToSession();
		} catch (Exception ex) {
			setOperationDataToSession();
			throw new TranFailException("util_content_msg2",
					"EBAlreadyApprove.execute()", ex.getMessage(), "����������");
		}

	}

	/**
	 * <b>��������: </b> <br>
	 * <p>
	 * ��ѯ�������˵��
	 * </p>
	 * 
	 * @throws TranFailException
	 *  
	 */
	private void queryshowadvice() throws TranFailException {
		String entcode = getStringAt("msg2_entcode"); //�ͻ�����
		String tradecode = getStringAt("msg2_tradecode"); //ҵ�������
		String ordernum = getStringAt("msg2_ordernum"); //���
		String ordercode = getStringAt("msg2_ordercode"); //����(��Ҫ���������Ƿ��ǵ�����)
		String firstflag = getStringAt("firstflag");
		dao = new util_content_msg2Dao(this);
		Vector contentlist = new Vector();

		try {
			if (firstflag.equals("1")) {
				contentlist = dao.getadvicetxt(entcode, tradecode, ordernum,
						ordercode);
			}
			if (firstflag.equals("0")) {
				contentlist = dao.getselfadvicetxt(entcode, tradecode,
						ordernum, ordercode);
			}

			this.setFieldValue("contentlist", contentlist);
			this.setFieldValue("entcode", entcode);
			this.setFieldValue("tradecode", tradecode);
			this.setFieldValue("ordernum", ordernum);
			this.setFieldValue("ordercode", ordercode);
			this.setFieldValue("firstflag", firstflag);
			if (firstflag.equals("1")) {
				setReplyPage("/icbc/cmis/flow/util/util_selfshowmsg2_flow.jsp");
			}
			if (firstflag.equals("0")) {
				setReplyPage("/icbc/cmis/flow/util/util_selfshowmsg_flow.jsp");
			}

			this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("util_content_msg2",
					"EBAlreadyApprove.queryshowadvice", e.getMessage());
		}
	}

	/**
	 * <b>��������: </b> <br>
	 * <p>
	 * ������������
	 * </p>
	 * 
	 * @throws TranFailException
	 *  
	 */
	private void saveadviceforsearcher() throws TranFailException {
		String numflag = getStringAt("numflag"); //�ͻ�����
		String entcode = getStringAt("msg2_entcode"); //�ͻ�����
		String tradecode = getStringAt("msg2_tradecode"); //ҵ�������
		String tradetype = getStringAt("msg2_tradetype"); //��������
		String flowtype = getStringAt("msg2_flowtype"); //��������
		String ordernum = getStringAt("msg2_ordernum"); //���
		String ordercode = getStringAt("msg2_ordercode"); //����(��Ҫ���������Ƿ��ǵ�����)
		String firstflag = getStringAt("firstflag");
		String areacode = (String) this.getSessionData("AreaCode");
		String employeecode = (String) this.getSessionData("EmployeeCode");
		this.setFieldValue("firstflag", firstflag);
		String process012 = ""; /* ������Ϊ�������������������� /�������˵�� */
		String process013 = ""; /* �ͻ��ֽ�����������Ԥ�� */
		String process014 = ""; /* ����������� */
		String process015 = ""; /* ���շ��� */
		String process016 = ""; /* ������� */
		String process027 = ""; /* ���շ��� */
		String process028 = ""; /* ������� */
		if (firstflag.equals("1")) {
			process012 = getStringAt("msg2_process012"); /* ������Ϊ�������������������� */
			process013 = getStringAt("msg2_process013"); /* �ͻ��ֽ�����������Ԥ�� */
			process014 = getStringAt("msg2_process014"); /* ����������� */
			process015 = getStringAt("msg2_process015"); /* ���շ��� */
			process016 = getStringAt("msg2_process016"); /* ������� */
			process027 = getStringAt("msg2_process027"); /* ���շ��� */
			process028 = getStringAt("msg2_process028"); /* ���շ��� */

		}
		if (firstflag.equals("0")) {
			process012 = getStringAt("msgprocess012"); //�������˵��
		}

		String rtag = "";

		try {
			DBProcedureParamsDef defcs = null;
			this.setFieldValue("in_numflag", numflag);
			this.setFieldValue("in_entcode", entcode);
			this.setFieldValue("in_tradecode", tradecode);
			this.setFieldValue("in_tradetype", tradetype);
			this.setFieldValue("in_flowtype", flowtype);
			this.setFieldValue("in_ordernum", ordernum);
			this.setFieldValue("in_ordercode", ordercode);
			this.setFieldValue("in_areacode", areacode);
			this.setFieldValue("in_employeecode", employeecode);
			if (firstflag.equals("1")) {
				this.setFieldValue("in_process013", process013);
				this.setFieldValue("in_process014", process014);
				this.setFieldValue("in_process015", process015);
				this.setFieldValue("in_process016", process016);
				this.setFieldValue("in_process012", process012);
				this.setFieldValue("in_process027", process027);
				this.setFieldValue("in_process028", process028);
			}
			if (firstflag.equals("0")) {

				this.setFieldValue("in_process012", process012);
			}
			if (firstflag.equals("1")) {
				defcs = new DBProcedureParamsDef(
						"pack_flow_unit.proc_saveselfadviceforsearcher");
				defcs.addInParam("in_numflag");
				defcs.addInParam("in_entcode");
				defcs.addInParam("in_tradecode");
				defcs.addInParam("in_flowtype");
				defcs.addInParam("in_tradetype");
				defcs.addInParam("in_ordernum");
				defcs.addInParam("in_ordercode");
				defcs.addInParam("in_areacode");
				defcs.addInParam("in_employeecode");
				defcs.addInParam("in_process013");
				defcs.addInParam("in_process014");
				defcs.addInParam("in_process015");
				defcs.addInParam("in_process016");
				defcs.addInParam("in_process012");
				defcs.addInParam("in_process027");
				defcs.addInParam("in_process028");
				defcs.addOutParam("ret_flag");
				defcs.addOutParam("ret_mess");
			}
			if (firstflag.equals("0")) {

				defcs = new DBProcedureParamsDef(
						"pack_flow_unit.proc_saveselfadvice");
				this.setFieldValue("in_formflag", "1");
				defcs.addInParam("in_numflag");
				defcs.addInParam("in_formflag");
				defcs.addInParam("in_entcode");
				defcs.addInParam("in_tradecode");
				defcs.addInParam("in_flowtype");
				defcs.addInParam("in_tradetype");
				defcs.addInParam("in_ordernum");
				defcs.addInParam("in_ordercode");
				defcs.addInParam("in_areacode");
				defcs.addInParam("in_employeecode");
				defcs.addInParam("in_process012");
				defcs.addOutParam("ret_flag");
				defcs.addOutParam("ret_mess");
			}

			DBProcedureAccessService dbProcService = new DBProcedureAccessService(
					this);
			dbProcService.executeProcedure(getOperationData(), defcs);
			dbProcService = null;
			String retCode = getStringAt("ret_flag");
			String retText = getStringAt("ret_mess");

			if (!retCode.equals("0")) {
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
						+ retText + "</error>");
			} else {
				rtag += "<rec/>";
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>"
						+ rtag + "</info>");
			}
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
					+ e.getMessage() + "</error>");
			//throw new TranFailException("util_content_msg2Op",
			// "EBAlreadyApprove.saveadviceforsearcher", e.getMessage());
		}

	}
}