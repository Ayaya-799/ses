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
import icbc.cmis.flow.BTN.tuihuiDao;
/**
 * @author ֣�ڱ�
 * ����-�������ѯ
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class unit_content_nopersonbanklist extends icbc.cmis.operation.CmisOperation {	

	icbc.cmis.tags.PropertyResourceReader propertyResourceReader = null;

	String langCode="zh_CN";
	public unit_content_nopersonbanklist() {
		super();
	}
	
	
	private String webBasePath = (String)CmisConstance.getParameterSettings().get("webBasePath");
	unit_content_nopersonbanklistDao dao = null;
	/**
			 * ȡ�û����б�
			 * @param flowtype
			 * @return
			 * @throws Exception
			 */
			public ArrayList getflowlist(String flowtype, String busi_type) throws Exception {
				ArrayList alist = new ArrayList();
				try {
					DummyOperation dummyop = new DummyOperation();
					unit_content_nopersonbanklistDao dao = new unit_content_nopersonbanklistDao(dummyop);
					alist = dao.getflowlist(flowtype, busi_type);
				} catch (TranFailException ee) {
					throw ee;
				} catch (Exception e) {
					throw new TranFailException(
						"icbc.cmis.flow.util",
						"unit_content_nopersonbanklistDao.getflowlist()",
						e.getMessage(),
						e.getMessage());
				}
				return alist;
			}
			
	/**
		 * ȡ�÷��͵���һ����
		 * @param areacode
		 * @param busi_type
		 * @param entcode
		 * @return
		 * @throws Exception
		 */
		public ArrayList getnextarea(String areacode, String busi_type, String entcode) throws Exception {
			ArrayList alist = new ArrayList();
			try {
				DummyOperation dummyop = new DummyOperation();
				unit_content_nopersonbanklistDao dao = new unit_content_nopersonbanklistDao(dummyop);
				alist = dao.getnextarea(areacode, busi_type, entcode);
			} catch (TranFailException ee) {
				throw ee;
			} catch (Exception e) {
				throw new TranFailException(
					"icbc.cmis.flow.util",
					"util_content_choosenext.getflowlist()",
					e.getMessage(),
					e.getMessage());
			}
			return alist;
		}
		
	public void execute() throws Exception, icbc.cmis.base.TranFailException {
			try
			{
				langCode=(String)this.getSessionData("LangCode");
				propertyResourceReader=new icbc.cmis.tags.PropertyResourceReader(langCode,"icbc.cmis.flow.FLOW_UTILE");
			}
			catch (Exception ex)
			{
			}
		String action = getStringAt("opAction");
		try {
			if (action.equals("20001")) { //ҵ��ַ������˴��� ��0��Ӫ��1ί�У�4����ͳһ���ô˷���
				querynoperson();
			} else if (action.equals("20002")) { //ҵ��ַ������д��� 0��Ӫ��1ί�У�4����ͳһ���ô˷���
				querynobank();
			} else if (action.equals("20003")) {//0��Ӫ��1ί�У�4�����ַ�����ͳһ���ô˷���
				fenfa();
			} else if (action.equals("40001")) {//�˻ز���
				tuihui();
			} else if (action.equals("60001")) { //��ѯ�����б�---������
				querysx();
			} else if (action.equals("60002")) { //�ַ�----������
				fenfasx();
			} else if (action.equals("70001")) { //��ѯ�����д����б�---6���ź�
			querysxh();
			} else if (action.equals("70003")) { //��ѯ�����˴����б�---6���ź�
			querypersonsxh();
			} else if (action.equals("70002")) { //�ַ�----6���ź�
			fenfasxh();
			}
		} catch (Exception ex) {
			setOperationDataToSession();
			throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.execute()", ex.getMessage(), "");
		}

	}

	private void querynoperson() throws TranFailException {
		String areacode = (String)this.getSessionData("AreaCode"); //��������
		String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա����
		String busitype = "";
		//busitype="8";//����ҵ��ַ�
		try {
			busitype = (String)this.getStringAt("busitype");
		} catch (Exception e) {
		}
		if (busitype.equals("")) {
			busitype = "0";
		}
		String flowtype = ""; //��������
		try {
			flowtype = (String)this.getStringAt("flowtype");
		} catch (Exception e) {
		}
		if (flowtype.equals("")) {
			flowtype = "01";
		}
		dao = new unit_content_nopersonbanklistDao(this);
		Vector list = new Vector();

		try {
			list = dao.getnopersonlist(areacode, employeecode, busitype);

			this.setFieldValue("list", list);
			this.setFieldValue("busitype", busitype);
			this.setFieldValue("flowtype", flowtype);
			this.setFieldValue("areacode", areacode);
			this.setFieldValue("employeecode", employeecode);
			this.setReplyPage("/flow/util/unit_content_nopersonlist.jsp");

			this.setOperationDataToSession();

		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.querynoperson", e.getMessage());
		}
	}

	private void querynobank() throws TranFailException {
		String areacode = (String)this.getSessionData("AreaCode"); //��������
		String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա����

		dao = new unit_content_nopersonbanklistDao(this);
		Vector list = new Vector();
		String flowtype = ""; //��������
		try {
			flowtype = (String)this.getStringAt("flowtype");
		} catch (Exception e) {
		}
		if (flowtype.equals("")) {
			flowtype = "01";
		}
		String busitype = ""; //ҵ�����ʣ�0��Ӫ��1ί�У�2����,4����,6���ź�
		try {
			busitype = (String)this.getStringAt("busitype");
		} catch (Exception e) {
		}
		if (busitype.equals("")) {
			busitype = "0";
		}
		try {
			list = dao.getnobanklist(areacode, busitype);
			this.setFieldValue("list", list);
			this.setFieldValue("busitype", busitype);
			this.setFieldValue("flowtype", flowtype);
			this.setFieldValue("areacode", areacode);
			this.setFieldValue("employeecode", employeecode);
			this.setReplyPage("/flow/util/unit_content_nobanklist.jsp");
			this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.querynobank", e.getMessage());
		}
	}

	private void fenfa() throws TranFailException {
		String areacode = (String)this.getSessionData("AreaCode"); //��������
		String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա����
		String busitype="";
		try{
			busitype = (String)this.getStringAt("busitype"); //����
		}
		catch (Exception e){
			busitype="";
			
				}
		String fsinfo = this.getStringAt("fsinfo");
		String fornum = "";
		fornum = fsinfo.substring(0, 1);
		fsinfo = fsinfo.substring(1);
		String process012 = this.getStringAt("process012");
		String nextemployeecode = this.getStringAt("nextemployeecode");
		String nextemployeename = this.getStringAt("nextemployeename");
		String nextareacode = this.getStringAt("nextareacode");
		if (!nextareacode.equals("") && !nextareacode.equals("null"))
		{
			nextareacode=nextareacode.substring(0,nextareacode.indexOf("|"));

		}
		this.setFieldValue("in_nextareacode", nextareacode);
		String nextflow="";
		try{
			nextflow = this.getStringAt("nextflow");
			nextflow=nextflow.substring(0,nextflow.indexOf("|"));
		}
		catch (Exception e){
			nextflow="";
			
		}
		
		this.setFieldValue("in_fsinfo", fsinfo);
		this.setFieldValue("in_fornum", fornum);
		this.setFieldValue("in_nextemployeecode", nextemployeecode);
		this.setFieldValue("in_nextemployeename", nextemployeename);
		this.setFieldValue("in_nextflow", nextflow);
		this.setFieldValue("in_process012", process012);

		try {
			DBProcedureParamsDef def = null;
			def = new DBProcedureParamsDef("pack_flow_unit.dofenfa");
			def.addInParam("in_fsinfo");
			def.addInParam("in_fornum");
			def.addInParam("in_nextemployeecode");
			def.addInParam("in_nextemployeename");
			def.addInParam("in_process012");
			def.addInParam("in_nextareacode");
			def.addInParam("in_nextflow");
			def.addOutParam("err_flag");
			def.addOutParam("err_txt");

			DBProcedureAccessService dbProcService = new DBProcedureAccessService(this);
			dbProcService.executeProcedure(getOperationData(), def);
			dbProcService = null;
			String retCode = getStringAt("err_flag");
			String retText = getStringAt("err_txt");
			if (!retCode.equals("0")) {
				throw new TranFailException("icbc.cmis.flow.util", "SE_pes_EvalueQueryOp.dk_pj()", retText, retText);
			} else {
				this.setFieldValue("okMsg", propertyResourceReader.getPrivateStr("C000063"));//�ַ��ɹ�
				this.setFieldValue("okTitle", genMsg.getErrMsgByLang(langCode,"099997"));//"�����ɹ�!"
				this.setFieldValue(
					"okReturn",
					webBasePath
						+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.unit_content_nopersonbanklist&opAction=20001&busitype="
						+ busitype);
				this.setReplyPage("/icbc/cmis/ok.jsp");
			}
			//this.setReplyPage("/flow/B/BB/BB_flow_Cancancleflowinfo.jsp");
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.fenfa", e.getMessage());
		}
	}

	private void tuihui() throws TranFailException {
		try {
			String areacode = (String)this.getSessionData("AreaCode"); //��������
			String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա����
			String busitype = (String)this.getStringAt("busitype"); //����
			String fsinfo = this.getStringAt("fsinfo");
			String fornum = "";
			fornum = fsinfo.substring(0, 1);
			fsinfo = fsinfo.substring(3);
			String process012 = this.getStringAt("process012");
			this.setFieldValue("in_process012", process012);
		
			this.setFieldValue("in_fsinfo", fsinfo);
			this.setFieldValue("empareacode", areacode);
			this.setFieldValue("employeecode", employeecode);
			tuihuiDao dao = new tuihuiDao(this);
			String okMsg = "";
			String errorMsg = "";
//process001+"|"+process002+"|"+process003+"|"+process004+"|"+process005+"|"+process006+"|"+process007+"|"+employeecode
			String process001=fsinfo.substring(0,fsinfo.indexOf(","));
			fsinfo=fsinfo.substring(fsinfo.indexOf(",")+1);
			String process002=fsinfo.substring(0,fsinfo.indexOf(","));
			fsinfo=fsinfo.substring(fsinfo.indexOf(",")+1);
			String process003=fsinfo.substring(0,fsinfo.indexOf(","));
			fsinfo=fsinfo.substring(fsinfo.indexOf(",")+1);
			String process004=fsinfo.substring(0,fsinfo.indexOf(","));
			fsinfo=fsinfo.substring(fsinfo.indexOf(",")+1);
			String process005=fsinfo.substring(0,fsinfo.indexOf(","));
			fsinfo=fsinfo.substring(fsinfo.indexOf(",")+1);
			String process006=fsinfo.substring(0,fsinfo.indexOf(","));
			this.setFieldValue("process001", process001);
			this.setFieldValue("process002", process002);
			this.setFieldValue("process003", process003);
			this.setFieldValue("process004", process004);
			this.setFieldValue("process005", process005);
			this.setFieldValue("process006", process006);
			String approve_backemp = dao.querybackemp(process001,process002);
			this.setFieldValue("approve_backemp",approve_backemp);			
				
				//�����˻ش洢����
					DBProcedureParamsDef def = new DBProcedureParamsDef("pack_approvedeal.dotuihui");
					def.addInParam("process001");
					def.addInParam("process002");
					def.addInParam("process003");
					def.addInParam("process004");
					def.addInParam("process005");
					def.addInParam("process006");
					def.addInParam("empareacode");
					def.addInParam("employeecode");
					def.addInParam("approve_backemp");//�˻صĻ��ں�
					def.addOutParam("out_flag");
					def.addOutParam("out_msg");
					DBProcedureAccessService proceSrv = new DBProcedureAccessService(this);
					proceSrv.executeProcedure(this.getOperationData(), def);
					proceSrv = null;
					String out_flag = this.getStringAt("out_flag");
					String out_msg = this.getStringAt("out_msg");

					if (out_flag.equals("0")) {
						this.setFieldValue("okTitle", genMsg.getErrMsgByLang(langCode,"099997"));
						this.setFieldValue("okMsg", out_msg);
				this.setFieldValue(
					"okReturn",
					webBasePath
						+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.unit_content_nopersonbanklist&opAction=20001");
				this.setReplyPage("/icbc/cmis/ok.jsp");
			} else {
				this.setFieldValue("infoTitle", genMsg.getErrMsgByLang(langCode,"099993"));
				this.setFieldValue("infoMsg", okMsg + errorMsg);
				this.setFieldValue(
					"infoReturn",
					webBasePath
						+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.unit_content_nopersonbanklist&opAction=20001&busitype="
						+ busitype);
				this.setReplyPage(webBasePath + "/util/util_info.jsp");
			}

			this.setOperationDataToSession();
		} catch (TranFailException ee) {
			this.setOperationDataToSession();
			throw ee;
		} catch (Exception e) {
			throw new TranFailException(
				"icbc.cmis.flow.util",
				"unit_content_nopersonbanklist.tuihui",
				e.getMessage(),
				e.getMessage());
		}
	}

	private void querysx() throws TranFailException {
		String areacode = (String)this.getSessionData("AreaCode"); //��������
		String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա����

		dao = new unit_content_nopersonbanklistDao(this);
		Vector list = new Vector();
		try {
			list = dao.getnobanklist(areacode, "2");
			this.setFieldValue("list", list);
			this.setFieldValue("areacode", areacode);
			this.setFieldValue("employeecode", employeecode);
			this.setReplyPage("/flow/util/unit_content_nobanklistsx.jsp");
			this.setOperationDataToSession();
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.querysx", e.getMessage());
		}
	}
	
	private void querysxh() throws TranFailException {
			String areacode = (String)this.getSessionData("AreaCode"); //��������
			String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա����
		String busitype = ""; //ҵ�����ʣ�0��Ӫ��1ί�У�2����,4����,6���ź�
				try {
					busitype = (String)this.getStringAt("busitype");
				} catch (Exception e) {
				}
				if (busitype.equals("")) {
					busitype = "6";
				}
			dao = new unit_content_nopersonbanklistDao(this);
			Vector list = new Vector();
			try {
				list = dao.getnobanklist(areacode, busitype);
				this.setFieldValue("list", list);
				this.setFieldValue("areacode", areacode);
				this.setFieldValue("employeecode", employeecode);
				this.setReplyPage("/flow/util/unit_content_nobanklistsxh.jsp");
				this.setOperationDataToSession();
			} catch (TranFailException e) {
				throw e;
			} catch (Exception e) {
				throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.querysxh", e.getMessage());
			}
		}
		
	private void querypersonsxh() throws TranFailException {
				String areacode = (String)this.getSessionData("AreaCode"); //��������
				String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա����
			String busitype = ""; //ҵ�����ʣ�0��Ӫ��1ί�У�2����,4����,6���ź�
					try {
						busitype = (String)this.getStringAt("busitype");
					} catch (Exception e) {
					}
					if (busitype.equals("")) {
						busitype = "6";
					}
				dao = new unit_content_nopersonbanklistDao(this);
				Vector list = new Vector();
				try {
					list = dao.getnopersonlist(areacode, employeecode, busitype);
					this.setFieldValue("list", list);
					this.setFieldValue("areacode", areacode);
					this.setFieldValue("employeecode", employeecode);
					this.setReplyPage("/flow/util/unit_content_nopersonlistsxh.jsp");
					this.setOperationDataToSession();
				} catch (TranFailException e) {
					throw e;
				} catch (Exception e) {
					throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.querysxh", e.getMessage());
				}
			}

	private void fenfasx() throws TranFailException {
		String areacode = (String)this.getSessionData("AreaCode"); //��������
		String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա����
		String fsinfo = this.getStringAt("fsinfo");
		String fornum = "";
		fornum = fsinfo.substring(0, 1);
		fsinfo = fsinfo.substring(1);
		String nextemployeecode = this.getStringAt("nextemployeecode");
		String nextemployeename = this.getStringAt("nextemployeename");
		this.setFieldValue("in_fsinfo", fsinfo);
		this.setFieldValue("in_fornum", fornum);
		this.setFieldValue("in_nextemployeecode", nextemployeecode);
		this.setFieldValue("in_nextemployeename", nextemployeename);

		try {
			DBProcedureParamsDef def = null;
			def = new DBProcedureParamsDef("pack_approvesx.dofenfa");
			def.addInParam("in_fsinfo");
			def.addInParam("in_fornum");
			def.addInParam("in_nextemployeecode");
			def.addInParam("in_nextemployeename");

			def.addOutParam("err_flag");
			def.addOutParam("err_txt");

			DBProcedureAccessService dbProcService = new DBProcedureAccessService(this);
			dbProcService.executeProcedure(getOperationData(), def);
			dbProcService = null;
			String retCode = getStringAt("err_flag");
			String retText = getStringAt("err_txt");
			if (!retCode.equals("0")) {
				throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.fenfa", retText,retText);
			} else {
				this.setFieldValue("okMsg", retText);
				this.setFieldValue("okTitle", genMsg.getErrMsgByLang(langCode,"099997"));//"�����ɹ�!"
				this.setFieldValue(
					"okReturn",
					webBasePath
						+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.unit_content_nopersonbanklist&opAction=60001");
				this.setReplyPage("/icbc/cmis/ok.jsp");
			}
			//this.setReplyPage("/flow/B/BB/BB_flow_Cancancleflowinfo.jsp");
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.fenfa", e.getMessage());
		}
	}
	
	private void fenfasxh() throws TranFailException {
			String areacode = (String)this.getSessionData("AreaCode"); //��������
			String employeecode = (String)this.getSessionData("EmployeeCode"); //��Ա����
			String fsinfo = this.getStringAt("fsinfo");
			String fornum = "";
			fornum = fsinfo.substring(0, 1);
			fsinfo = fsinfo.substring(1);
			String nextemployeecode = this.getStringAt("nextemployeecode");
			String nextemployeename = this.getStringAt("nextemployeename");
		    String nextareacode = this.getStringAt("nextareacode");
		   
		    if (!nextareacode.equals("") && !nextareacode.equals("null"))
		    {
					nextareacode=nextareacode.substring(0,nextareacode.indexOf("|"));
				
		    }
			this.setFieldValue("in_fsinfo", fsinfo);
			this.setFieldValue("in_fornum", fornum);
		    this.setFieldValue("in_nextareacode", nextareacode);
			this.setFieldValue("in_nextemployeecode", nextemployeecode);
			this.setFieldValue("in_nextemployeename", nextemployeename);
		String nextflow="";
				try{
					nextflow = this.getStringAt("nextflow");
					nextflow=nextflow.substring(0,nextflow.indexOf("|"));
				}
				catch (Exception e){
					nextflow="";
			
				}
		
		this.setFieldValue("in_nextflow", nextflow);
			try {
				DBProcedureParamsDef def = null;
				def = new DBProcedureParamsDef("pack_flow_unit.dofenfasxh");
				def.addInParam("in_fsinfo");
				def.addInParam("in_fornum");
				def.addInParam("in_nextemployeecode");
				def.addInParam("in_nextemployeename");
				def.addInParam("in_nextareacode");
				def.addInParam("in_nextflow");
				def.addOutParam("err_flag");
				def.addOutParam("err_txt");

				DBProcedureAccessService dbProcService = new DBProcedureAccessService(this);
				dbProcService.executeProcedure(getOperationData(), def);
				dbProcService = null;
				String retCode = getStringAt("err_flag");
				String retText = getStringAt("err_txt");
				if (!retCode.equals("0")) {
					throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.fenfaxsh", retText, retText);
				} else {
					this.setFieldValue("okMsg", retText);
					this.setFieldValue("okTitle", genMsg.getErrMsgByLang(langCode,"099997"));//"�����ɹ�!"
					this.setFieldValue(
						"okReturn",
						webBasePath
							+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.unit_content_nopersonbanklist&opAction=70001");
					this.setReplyPage("/icbc/cmis/ok.jsp");
				}
				//this.setReplyPage("/flow/B/BB/BB_flow_Cancancleflowinfo.jsp");
			} catch (TranFailException e) {
				throw e;
			} catch (Exception e) {
				throw new TranFailException("icbc.cmis.flow.util", "unit_content_nopersonbanklist.fenfasxh", e.getMessage());
			}
		}

}
