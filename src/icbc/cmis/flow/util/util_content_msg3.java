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
public class util_content_msg3 extends icbc.cmis.operation.CmisOperation {
	public util_content_msg3() {
		super();
	}

	private String webBasePath = (String) CmisConstance.getParameterSettings()
			.get("webBasePath");

	util_content_msg3Dao dao = null;

	public void execute() throws Exception, icbc.cmis.base.TranFailException {
		String action = getStringAt("opAction");
		try {

			//��ѯ�������˵��
			if (action.equals("20002")) {
				queryshowadvice();
			}
			//����ǵ��������
			else if (action.equals("10001")) {
				saveadvice();
			}
			;
			setOperationDataToSession();
		} catch (Exception ex) {
			setOperationDataToSession();
			throw new TranFailException("util_content_msg3",
					"EBAlreadyApprove.execute()", ex.getMessage(),
					"�ǵ�����Ϊ��������������������");
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
		String entcode = getStringAt("msg3_entcode"); //�ͻ�����
		String tradecode = getStringAt("msg3_tradecode"); //ҵ�������
		String ordernum = getStringAt("msg3_ordernum"); //���
		String ordercode = getStringAt("msg3_ordercode"); //����(��Ҫ���������Ƿ��ǵ�����)

		dao = new util_content_msg3Dao(this);
		Vector contentlist = new Vector();

		try {
			contentlist = dao.getadvicetxt(entcode, tradecode, ordernum,
					ordercode);
			this.setFieldValue("contentlist", contentlist);
			this.setFieldValue("entcode", entcode);
			this.setFieldValue("tradecode", tradecode);
			this.setFieldValue("ordernum", ordernum);
			this.setFieldValue("ordercode", ordercode);

			setReplyPage("/icbc/cmis/flow/util/util_selfshowmsg3_flow.jsp");

			this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("util_content_msg3",
					"EBAlreadyApprove.queryshowadvice", e.getMessage());
		}
	}

	/**
	 * <b>��������: </b> <br>
	 * <p>
	 * ����ǵ��������
	 * </p>
	 * 
	 * @throws TranFailException
	 *  
	 */
	private void saveadvice() throws TranFailException {
		String formflag = "3";
		String numflag = getStringAt("numflag"); //�ͻ�����
		String entcode = getStringAt("msg3_entcode"); //�ͻ�����
		String tradecode = getStringAt("msg3_tradecode"); //ҵ�������
		String tradetype = getStringAt("msg3_tradetype"); //��������
		String flowtype = getStringAt("msg3_flowtype"); //��������
		String ordernum = getStringAt("msg3_ordernum"); //���
		String ordercode = getStringAt("msg3_ordercode"); //����(��Ҫ���������Ƿ��ǵ�����)
		String process021 = getStringAt("msg3process021"); //�������˵��
		String areacode = (String) this.getSessionData("AreaCode");
		String employeecode = (String) this.getSessionData("EmployeeCode");
		String rtag = "";
		try {
			DBProcedureParamsDef defcs = null;
			this.setFieldValue("in_numflag", numflag);
			this.setFieldValue("in_formflag", formflag);
			this.setFieldValue("in_entcode", entcode);
			this.setFieldValue("in_tradecode", tradecode);
			this.setFieldValue("in_tradetype", tradetype);
			this.setFieldValue("in_flowtype", flowtype);
			this.setFieldValue("in_ordernum", ordernum);
			this.setFieldValue("in_ordercode", ordercode);
			this.setFieldValue("in_areacode", areacode);
			this.setFieldValue("in_employeecode", employeecode);
			this.setFieldValue("in_process021", process021);
			defcs = new DBProcedureParamsDef(
					"pack_flow_unit.proc_saveselfadvice");
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
			defcs.addInParam("in_process021");
			defcs.addOutParam("ret_flag");
			defcs.addOutParam("ret_mess");
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
				rtag += "<rec    />";
				setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><info>"
						+ rtag + "</info>");
			}
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?><error>"
					+ e.getMessage() + "</error>");
			//throw new TranFailException("util_content_msg3",
			// "EBAlreadyApprove.saveadvice", e.getMessage());
		}

	}

}