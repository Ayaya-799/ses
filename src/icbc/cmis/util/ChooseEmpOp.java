package icbc.cmis.util;

import icbc.cmis.base.*;
import icbc.cmis.operation.*;
import icbc.cmis.service.*;
import java.util.*;
import java.sql.*;
import icbc.cmis.util.*;

public class ChooseEmpOp extends CmisOperation {

	public ChooseEmpOp() {
		super();
	}

	public void execute() throws java.lang.Exception {
		/**@todo: implement this icbc.cmis.operation.CmisOperation abstract method*/
		try {
			String action = getStringAt("opAction");
			if (action.equals("QueryPage")) { //��ѯ��Ա
				querySelectModule();
			}
		} catch (TranFailException te) {
			this.setOperationDataToSession();
			throw te;
		} catch (Exception e) {
			this.setOperationDataToSession();
			throw new TranFailException(
				"099999",
				"icbc.cmis.util.ChooseEmpOp.execute()");
		}
	}

	/**
	 * ��ѯ��Ա
	 * @throws Exception
	 */
	private void querySelectModule() throws Exception {
		IndexedDataCollection v_return = new IndexedDataCollection();
		try {
			String employee_code = this.getStringAt("employee_code"); //��Ա����
			String employee_name = this.getStringAt("employee_name"); //��Ա����
			String area_code = this.getStringAt("area_code"); //������
			String sub_bank = this.getStringAt("sub_bank"); //�Ƿ��ѯ�¼���
			String major_code = this.getStringAt("major_code"); //רҵ��
			String class_code = this.getStringAt("class_code"); //��λ��
			String beginPos = this.getStringAt("beginPos"); //��ʼλ��
			String subemployee = this.getStringAt("subemployee");//��ȥ�Ĺ�Ա
			String a1 = "";
			String a2 = "";
			String a3 = "";
			String a4 = "";
			String a5 = "";
			String a6 = "";
			String a7 = "";
			ChooseEmpDAO dao = new ChooseEmpDAO(this);
			v_return =
				dao.eventQueryDao(
					employee_code,
					employee_name,
					area_code,
					sub_bank,
					major_code,
					class_code,
					beginPos,
					subemployee);
			if (this.isElementExist("query_Result")) {
				this.removeDataField("query_Result");
			}

			String xmlPack = "<?xml version=\"1.0\" encoding=\"GB2312\"?>";

			if (v_return.getSize() > 0) {
				KeyedDataCollection kdresult =
					(KeyedDataCollection) v_return.getElement(0);
				a5 = (String) kdresult.getValueAt("allcount"); //��¼����
				a6 = (String) kdresult.getValueAt("beginPos"); //��¼��ʼλ��
				xmlPack += "<Content pages=\""
					+ a6
					+ "\" itemamount=\""
					+ a5
					+ "\">";
				for (int j = 0; j < v_return.getSize(); j++) {
					KeyedDataCollection kdresult1 =
						(KeyedDataCollection) v_return.getElement(j);
					a1 = (String) kdresult1.getValueAt("employee_code"); //��Ա����
					a2 = (String) kdresult1.getValueAt("employee_name"); //��Ա����
					a3 = (String) kdresult1.getValueAt("area_code"); //������
					a4 = (String) kdresult1.getValueAt("area_name"); //��������

					xmlPack += "<CodeName "
						+ "a7=\""
						+ String.valueOf(j + 1)
						+ "\" a1=\""
						+ a1
						+ "\" a2=\""
						+ a2
						+ "\" a3=\""
						+ a3
						+ "\" a4=\""
						+ a4
						+ "\"/>";
				}
			} else {
				a5 = "0"; //��¼����
				a6 = "0"; //��¼��ʼλ��
				xmlPack += "<Content pages=\""
					+ a6
					+ "\" itemamount=\""
					+ a5
					+ "\">";

				a1 = ""; //��Ա����
				a2 = ""; //��Ա����
				a3 = ""; //������
				a4 = ""; //��������
				a7="";

				xmlPack += "<CodeName "
					+ "a7=\""
					+ a7
					+ "\" a1=\""
					+ a1
					+ "\" a2=\""
					+ a2
					+ "\" a3=\""
					+ a3
					+ "\" a4=\""
					+ a4
					+ "\"/>";

			}
			xmlPack += "</Content>";
			this.setOperationDataToSession();
			this.setReplyPage("DirectOutput" + xmlPack);
//			this.setReplyPage("DirectOutput" + icbc.cmis.util.Func_XMLfiltrate.validXml(xmlPack));
		} catch (TranFailException e) {
			this.setOperationDataToSession();
			throw e;
		} catch (Exception e) {
			this.setOperationDataToSession();
			throw new TranFailException(
				"099999",
				"icbc.cmis.util.ChooseEmpOp.querySelectModule()");
		}
	}

}