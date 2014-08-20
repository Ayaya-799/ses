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
 * @author ֣�ڱ�
 * ����-��ʾ���ֶ���Ϣ
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_content_msg extends icbc.cmis.operation.CmisOperation {
	public util_content_msg() {
			super();
	}
	private String webBasePath =
				(String) CmisConstance.getParameterSettings().get("webBasePath");
	util_content_msgDao dao = null;
	public void execute() throws Exception, icbc.cmis.base.TranFailException {
		String action = getStringAt("opAction");
		try {

			//��ѯ�������˵��
			 if (action.equals("20001")) {
				queryshowadvice();
					}
					//���汾�����˵��
			else if (action.equals("10001")) {
				saveadvice();
			};
			
		}

		catch (Exception ex) {
			setOperationDataToSession();
			throw new TranFailException("util_content_msg", "EBAlreadyApprove.execute()", ex.getMessage(), "���˵��");
		}
		
	}
	

/**
	* <b>��������: </b><br>
	* <p>��ѯ�������˵��</p>
	* @throws TranFailException
	*  
	*/
	private void queryshowadvice() throws TranFailException {
	
		String entcode =getStringAt("entcode");   //�ͻ�����
		String tradecode =getStringAt("tradecode");//ҵ�������
		String ordernum =getStringAt("ordernum");  //���
		String ordercode =getStringAt("ordercode"); //����(��Ҫ���������Ƿ��ǵ�����)

		dao = new util_content_msgDao(this);
		Vector contentlist = new Vector();

		try {
			contentlist = dao.getadvicetxt(entcode,tradecode,ordernum,ordercode);
			this.setFieldValue("contentlist", contentlist);
			this.setFieldValue("entcode", entcode);
			this.setFieldValue("tradecode", tradecode);
			this.setFieldValue("ordernum", ordernum);
			this.setFieldValue("ordercode", ordercode);
			
			 
			 setReplyPage("/icbc/cmis/flow/util/util_selfshowcontent_msg.jsp");
			 
			this.setOperationDataToSession();
		}
		catch (TranFailException e) {
			throw e;
		}
		catch (Exception e) {
			throw new TranFailException("util_content_msg", "EBAlreadyApprove.queryshowadvice", e.getMessage());
		}
	}
	
/**
	* <b>��������: </b><br>
	* <p>����ǵ��������</p>
	* @throws TranFailException
	*  
	*/
	private void saveadvice() throws TranFailException {
		String entcode =getStringAt("entcode");   //�ͻ�����
		String tradecode =getStringAt("tradecode");//ҵ�������
		String ordernum =getStringAt("ordernum");  //���
		String ordercode =getStringAt("ordercode"); //����(��Ҫ���������Ƿ��ǵ�����)
		String process012 =getStringAt("process012"); //�������˵��
		try {
		DBProcedureParamsDef defcs = null;
		this.setFieldValue("in_entcode", entcode);
		this.setFieldValue("in_tradecode", tradecode);
		this.setFieldValue("in_ordernum", ordernum);
		this.setFieldValue("in_ordercode", ordercode);
		this.setFieldValue("in_process012", process012);

		defcs = new DBProcedureParamsDef("pack_flow_unit.proc_saveselfadvice");
		defcs.addInParam("in_entcode");
		defcs.addInParam("in_tradecode");
		defcs.addInParam("in_ordernum");
		defcs.addInParam("in_ordercode");	 
		defcs.addInParam("in_process012");
		defcs.addOutParam("ret_flag");	 
		defcs.addOutParam("ret_mess");
		DBProcedureAccessService dbProcService = new DBProcedureAccessService(this);
		int returncode = dbProcService.executeProcedure(getOperationData(), defcs);
		dbProcService = null;
		String retCode = getStringAt("ret_flag");
		String retText = getStringAt("ret_mess");


		if (!retCode.equals("0")) {
			throw new TranFailException(
				"pack_flow_unit.proc_saveselfadvice",
				"SE_pes_EvalueQueryOp.dk_pj()",
				retText,
				"�������˵���������");
		} ;
		if (returncode != 0)
		{
		 String e = this.getStringAt("ret_mess");

		 throw new icbc.cmis.base.TranFailException("pack_flow_unit.proc_saveselfadvice", "util_showcontent_flowOp.send_sqfs()", "!", e);
		}

	}
	catch (TranFailException e) {
		throw e;
	}
	catch (Exception e) {
		throw new TranFailException("util_content_msg", "EBAlreadyApprove.saveadvice", e.getMessage());
	}
		dao = new util_content_msgDao(this);
		String returnURL = "";
		Vector contentlist = new Vector();

		try {
			contentlist = dao.getadvicetxt( entcode, tradecode,ordernum,ordercode);
			this.setFieldValue("contentlist", contentlist);

			
			 setReplyPage("/icbc/cmis/flow/util/util_content_msg.jsp");
			
			this.setOperationDataToSession();
		}
		catch (TranFailException e) {
			throw e;
		}
		catch (Exception e) {
			throw new TranFailException("util_content_msg", "EBAlreadyApprove.saveadvice", e.getMessage());
		}
	}
	

}
