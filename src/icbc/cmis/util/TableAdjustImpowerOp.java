/*
 * 
 * �������� 2007-6-22
 *
 * @author ��ǿ
 * 
 */
package icbc.cmis.util;
import icbc.cmis.operation.*;
import java.util.*;
import icbc.cmis.base.*;
import icbc.cmis.base.TranFailException;
import icbc.cmis.second.pkg.*;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Calendar;
import icbc.cmis.util.tools;
/**
 * Title:ҵ�����ϸĶ�Ȩ�޵���Ȩ
 * Description: ҵ�����ϸĶ�Ȩ�޵���Ȩ
 * Company:icbcsdc
 * @author����ǿ
 * @version 1.0
 */
public class TableAdjustImpowerOp extends CmisOperation {

	TableAdjustImpowerDAO dao = null;
	String baseWebPath = (String)CmisConstance.getParameterSettings().get("webBasePath");

	/**
	 * @throws Exception
	 */
	public void execute() throws java.lang.Exception {
		/**@todo: implement this icbc.cmis.operation.CmisOperation abstract method*/
		String action = getStringAt("opAction");

		try {
			//��Ȩ���Listҳ��
			if (action.equals("10001")) {
				tableAdjustImpowerList();
			}
			//��Ȩ����ҳ��
			if (action.equals("10002")) {
				tableAdjustImpowerAddPage();
			}

			//��Ȩ�޸Ĳ���
			if (action.equals("10004")) {
				tableAdjustImpowerModify();
			}
			//��Ȩɾ������
			if (action.equals("10005")) {
				tableAdjustImpowerDelete();
			}

			//��Ȩ����ҳ��
			if (action.equals("10006")) {
				tableAdjustImpowerAdd();
			}
			setOperationDataToSession();

		}
		catch (Exception ex) {
			ex.printStackTrace();
			setOperationDataToSession();
			throw new TranFailException("ҵ�����ϸĶ�Ȩ�޵���Ȩ", "TableAdjustImpowerOP.execute()", ex.getMessage(), " ҵ�����ϸĶ�Ȩ�޵���Ȩ����ʧ��");
		}
	}

	/**
	* <b>��������:ҵ�����ϸĶ�Ȩ�޵���Ȩ</b><br>
	* <p>��Ȩ��ѯListҳ��</p>
	* @throws TranFailException
	*  
	*/
	private void tableAdjustImpowerList() throws TranFailException {

		String areaCode = (String)this.getStringAt("area_code"); //������
		String areaName = (String)this.getStringAt("area_name"); //��������

		dao = new TableAdjustImpowerDAO(this);
		Vector list = null; //��Ȩ��Ŀ�б�
		try {
			list = dao.getQueryImpowerList(areaCode);
			this.setFieldValue("query_area_code", areaCode);
			this.setFieldValue("query_area_name", areaName);
			this.setFieldValue("list", list);
			this.setReplyPage("/util/util_TableAdjustImpowerList.jsp");
			this.setOperationDataToSession();
		}
		catch (TranFailException e) {
			throw e;
		}
		catch (Exception e) {
			throw new TranFailException("ҵ�����ϸĶ�Ȩ�޵���Ȩ", "TableAdjustImpowerOP.tableAdjustImpowerList()", e.getMessage(), "��ѯ��Ȩ�б����");
		}
	}

	/**
		* <b>��������:ҵ�����ϸĶ�Ȩ�޵���Ȩ</b><br>
		* <p>��ѯ��Ȩ����ҳ��</p>
		* @throws TranFailException
		*  
		*/
	private void tableAdjustImpowerAddPage() throws TranFailException {

		String areaCode = (String)this.getStringAt("query_area_code"); //������		
		String areaName = (String)this.getStringAt("query_area_name"); //��������
		String matherAreaCode = "";
		String matherAreaName = "";
		String matherSelect = "true"; //�ϼ����Ƿ��ѡ��־λ
		dao = new TableAdjustImpowerDAO(this);
		try {
			if (!areaCode.equals("*")) {
				String ret = dao.getPriorBank(areaCode);
				if (ret.length() > 0) {
					matherAreaCode = ret.substring(0, ret.indexOf("|"));
					matherAreaName = ret.substring(ret.indexOf("|") + 1);
					matherSelect = "false";
				}
			}
			this.setFieldValue("mather_area_code", matherAreaCode);
			this.setFieldValue("mather_area_name", matherAreaName);
			this.setFieldValue("matherSelect", matherSelect);
			this.setFieldValue("query_area_code", areaCode);
			this.setFieldValue("query_area_name", areaName);
			this.setReplyPage("/util/util_TableAdjustImpowerNew.jsp");
			this.setOperationDataToSession();
		}
		catch (TranFailException e) {
			throw e;
		}
		catch (Exception e) {
			throw new TranFailException("ҵ�����ϸĶ�Ȩ�޵���Ȩ", "TableAdjustImpowerOP.tableAdjustImpowerAddPage()", e.getMessage(), "��Ȩ��������");
		}
	}

	/**
		* <b>��������:ҵ�����ϸĶ�Ȩ�޵���Ȩ</b><br>
		* <p>��ѯ��Ȩ����ҳ��</p>
		* @throws TranFailException
		*  
		*/
	private void tableAdjustImpowerAdd() throws TranFailException {

		String areaCode = (String)this.getStringAt("area_code"); //������
		String operationType = (String)this.getStringAt("operationType"); //ҵ��Ʒ��
		String controlType = (String)this.getStringAt("controlType"); //��������
		String matherAreaCode = (String)this.getStringAt("mather_area_code"); //����Ȩ���ϼ���
		String input_personnel_code = (String)this.getSessionData("EmployeeCode"); //this.getStringAt("input_personnel_code"); //¼���Ա
		String input_area_code = (String)this.getStringAt("input_area_code"); //¼�����

		dao = new TableAdjustImpowerDAO(this);
		try {
			int count = 0;
			count = dao.getRecordCount(areaCode, operationType, controlType, matherAreaCode);
			if (count == 0) {
				if (dao.adjustImpowerAdd(areaCode, operationType, controlType, matherAreaCode, input_personnel_code, input_area_code)) {
					String returnURL =
						baseWebPath
							+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.TableAdjustImpowerOp&opAction=10001"
							+ "&area_code="
							+ areaCode;
					this.setFieldValue("okTitle", "ҵ�����ϸĶ�Ȩ�޵���Ȩ��������");
					this.setFieldValue("okMsg", "ҵ�����ϸĶ�Ȩ�޵���Ȩ���������ɹ���");
					this.setFieldValue("okReturn", returnURL);
					this.setOperationDataToSession();
					this.setReplyPage("/ok.jsp");
					this.setOperationDataToSession();
				}

			}
			else {
				if (!areaCode.equals("*") && !controlType.equals("*")) {
					this.setFieldValue("infoTitle", "ҵ�����ϸĶ�Ȩ�޵���Ȩ��������");
					this.setFieldValue("infoMsg", "Ȩ���Ѵ��ڣ�����������");
					this.setFieldValue("infoReturn", "javascript:window.history.back();");
					this.setOperationDataToSession();
					this.setReplyPage("/util/util_info.jsp");
					this.setOperationDataToSession();
				}
				else {
					dao.deleteExistRecord(areaCode, operationType, controlType, matherAreaCode) ;
					if (dao.adjustImpowerAdd(areaCode, operationType, controlType, matherAreaCode, input_personnel_code, input_area_code)) {
						String returnURL =
							baseWebPath
								+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.TableAdjustImpowerOp&opAction=10001"
								+ "&area_code="
								+ areaCode;
						this.setFieldValue("okTitle", "ҵ�����ϸĶ�Ȩ�޵���Ȩ��������");
						this.setFieldValue("okMsg", "ҵ�����ϸĶ�Ȩ�޵���Ȩ���������ɹ���");
						this.setFieldValue("okReturn", returnURL);
						this.setOperationDataToSession();
						this.setReplyPage("/ok.jsp");
						this.setOperationDataToSession();
					}

				}
			}
		}
		catch (TranFailException e) {
			throw e;
		}
		catch (Exception e) {
			throw new TranFailException("ҵ�����ϸĶ�Ȩ�޵���Ȩ", "AA_FinancingOpTrunImpowerOp.financingOpTrunImpowerAdd()", e.getMessage(), genMsg.getErrMsg("100469"));
		}
	}

	/**
	* <b>��������:ҵ�����ϸĶ�Ȩ�޵���Ȩ</b><br>
	* <p>��ѯ��Ȩ�޸Ĳ���</p>
	* @throws TranFailException
	*  
	*/
	private void tableAdjustImpowerModify() throws TranFailException {
		String oldAreaCode = ""; //������
		String oldOoperationType = ""; //ҵ��Ʒ��
		String oldControlType = ""; //��������
		String oldMatherAreaCode = ""; //����Ȩ���ϼ���
		String newAreaCode = ""; //������
		String newOoperationType = ""; //ҵ��Ʒ��
		String newControlType = ""; //��������
		String newMatherAreaCode = ""; //����Ȩ���ϼ���

		String newValues = (String)this.getStringAt("newValues");
		String oldValues = (String)this.getStringAt("oldValues");
		String employeeCode = (String)this.getSessionData("EmployeeCode");
		String employeeArea = (String)this.getSessionData("AreaCode");

		dao = new TableAdjustImpowerDAO(this);

		//����������
		StringTokenizer st = new StringTokenizer(newValues, "|");
		if (st.hasMoreTokens()) {
			newAreaCode = ((String)st.nextElement()).trim();
			newOoperationType = ((String)st.nextElement()).trim();
			newControlType = ((String)st.nextElement()).trim();
			newMatherAreaCode = ((String)st.nextElement()).trim();
		}

		//����������
		st = new StringTokenizer(oldValues, "|");
		if (st.hasMoreTokens()) {
			oldAreaCode = ((String)st.nextElement()).trim();
			oldOoperationType = ((String)st.nextElement()).trim();
			oldControlType = ((String)st.nextElement()).trim();
			oldMatherAreaCode = ((String)st.nextElement()).trim();
		}

		try {
			if (oldOoperationType.equals(newOoperationType) && oldControlType.equals(newControlType)) {
				this.setFieldValue("infoTitle", "ҵ�����ϸĶ�Ȩ�޵���Ȩ�޸Ĳ���");
				this.setFieldValue("infoMsg", "����û�仯�������޸ģ�");
				this.setFieldValue("infoReturn", "javascript:window.history.back();");
				this.setOperationDataToSession();
				this.setReplyPage("/util/util_info.jsp");
				this.setOperationDataToSession();
				return;
			}
			//���ֻ�ǿ������ʹӾ�����*,����Ҫ���business_control_modify���еļ�¼
			if (!(oldOoperationType.equals(newOoperationType) && newControlType.equals("*"))) {
				dao.busiModiContDel(oldAreaCode, oldOoperationType, oldControlType, oldMatherAreaCode);
			}

			//��������			
			if (dao
				.adjustImpowerUpdate(
					newAreaCode,
					newOoperationType,
					newControlType,
					oldOoperationType,
					oldControlType,
					newMatherAreaCode,
					employeeCode,
					employeeArea)) {
				String returnURL =
					baseWebPath
						+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.TableAdjustImpowerOp&opAction=10001"
						+ "&area_code="
						+ oldAreaCode;
				this.setFieldValue("okTitle", "ҵ�����ϸĶ�Ȩ�޵���Ȩ�޸Ĳ���");
				this.setFieldValue("okMsg", "ҵ�����ϸĶ�Ȩ�޵���Ȩ�޸Ĳ����ɹ���");
				this.setFieldValue("okReturn", returnURL);
				this.setOperationDataToSession();
				this.setReplyPage("/ok.jsp");
				this.setOperationDataToSession();
			}
		}
		catch (TranFailException e) {
			throw e;
		}
		catch (Exception e) {
			throw new TranFailException("ҵ�����ϸĶ�Ȩ�޵���Ȩ", "AA_FinancingOpTrunImpowerOp.financingOpQueryImpowerModify()", e.getMessage(), genMsg.getErrMsg("100469"));
		}
	}

	/**
		* <b>��������:ҵ�����ϸĶ�Ȩ�޵���Ȩ</b><br>
		* <p>��Ȩ��ѯɾ������</p>
		* @throws TranFailException
		*  
		*/
	private void tableAdjustImpowerDelete() throws TranFailException {

		String areaCode = ""; //������
		String operationType = ""; //ҵ��Ʒ��
		String controlType = ""; //��������
		String matherAreaCode = ""; //����Ȩ���ϼ���

		String res = (String)this.getStringAt("oldValues");
		StringTokenizer st = new StringTokenizer(res, "|");
		if (st.hasMoreTokens()) {
			areaCode = ((String)st.nextElement()).trim();
			operationType = ((String)st.nextElement()).trim();
			controlType = ((String)st.nextElement()).trim();
			matherAreaCode = ((String)st.nextElement()).trim();
		}
		dao = new TableAdjustImpowerDAO(this);
		Vector list = null; //��Ȩ��Ŀ�б�
		try {
			if (dao.busiModiContDel(areaCode, operationType, controlType, matherAreaCode)) {
				if (dao.adjustImpowerDelete(areaCode, operationType, controlType, matherAreaCode)) {
					String returnURL =
						baseWebPath
							+ "/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.TableAdjustImpowerOp&opAction=10001"
							+ "&area_code="
							+ areaCode;
					this.setFieldValue("okTitle", "ҵ�����ϸĶ�Ȩ�޵���Ȩɾ������");
					this.setFieldValue("okMsg", "ҵ�����ϸĶ�Ȩ�޵���Ȩɾ�������ɹ���");
					this.setFieldValue("okReturn", returnURL);
					this.setOperationDataToSession();
					this.setReplyPage("/ok.jsp");
					this.setOperationDataToSession();
				}
			}

		}
		catch (TranFailException e) {
			throw e;
		}
		catch (Exception e) {

			throw new TranFailException("ҵ�����ϸĶ�Ȩ�޵���Ȩ", "AA_FinancingOpTrunImpowerOp.financingOpTrunImpowerAddFistWeb()", e.getMessage(), genMsg.getErrMsg("100469"));
		}
	}
}