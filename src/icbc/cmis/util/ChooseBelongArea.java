package icbc.cmis.util;

import icbc.cmis.base.*;
import icbc.cmis.operation.*;
import java.util.Vector;
import java.sql.*;

/**
 * <p>Title: ��Աѡ��</p>
 * <p>Description:��ģ���ṩ��������ѡ�񵥸�������Ա�Ĺ��ܡ� </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company:icbc </p>
 * @author Ҷ��ͦ
 * @version 1.0
 */

public class ChooseBelongArea extends CmisOperation {

  public ChooseBelongArea() {
  }
  private SqlTool sqltool= null;

  private void init() throws Exception{
	  sqltool = new SqlTool(this);
  }
  /**
   * ���ݴ���Ĳ�������ʾ��Ա�б�
   * ����Ĳ�����<br>
   * eCodeWsA342d����Ա��<br>
   * areaWsA342d�������ţ���������ö��ŷָ�<br>
   * majorWsA342d��רҵ�����רҵ�ö��ŷָ�<br>
   * eClassWsA342d����ѡ��Ա��ɫ�������ɫ�ö��ŷָ�<br>
   * includeSelfWsA342d���Ƿ�����Լ���true|false<br>
   * multiSelectWsA342d����ѡ�� true|false<br>
   *
   * ��ʾ��Ա�б���Ա�š�������רҵ����ɫ
   * @throws TranFailException
   */
  public void execute() throws Exception{
	String action = getStringAt("opAction");
//    String eCode = this.getStringAt("eCodeWsA342d");
//    String area = this.getStringAt("areaWsA342d");
//    String major = this.getStringAt("majorWsA342d");
//    String eClass = this.getStringAt("eClassWsA342d");
//    String includeSelf = this.getStringAt("includeSelfWsA342d");
//    String multiSelect = this.getStringAt("multiSelectWsA342d");
//    String distinctIgnor = "00";
    
	if(action.equals("queryBelongBank")){
				queryBelongBank();
			}
//    try {
//      distinctIgnor = this.getStringAt("distinctIgnoreWsA342d");
//    }
//    catch (Exception ex) {
//    }
//
//
//    try {
//      ChooseBelongEmpDAO dao = new ChooseBelongEmpDAO(this);
//      Vector employee = dao.getEmployee(eCode,area,major,eClass,Boolean.valueOf(includeSelf).booleanValue(),distinctIgnor);
//      this.setFieldValue("cmisChooseEmployeeDataWsA342d",employee);
//      this.setFieldValue("cmisChooseEmployeeMultiSelectWsA342d",multiSelect);
//      this.setOperationDataToSession();
//      this.setReplyPage("/icbc/cmis/util/util_ChooseBelongEmp.jsp");
//    } catch (TranFailException ex) {
//      throw ex;
//    } catch (Exception ex) {
//      throw new TranFailException("cmisutil201","icbc.cmis.util.ChooseEmployee",ex.getMessage(),"���ɹ�Ա�б����");
//    }
  }
  private void queryBelongBank() throws Exception{

		  String loginBankCode;
		  try{
			loginBankCode = this.getStringAt("areaCodeCBA");
		  }
		  catch(Exception e){
			loginBankCode = (String)getSessionData("AreaCode");
		  }
		  
		  IndexedDataCollection iResult = new IndexedDataCollection();
		  iResult.setName("iBelongBankCode");
		  String bankFlag ="";
		  String bankCode ="";
		  String bankName ="";
		  String selectBelongBank=null;
		  try{
						init();
						selectBelongBank = "select AREA_CODE, AREA_NAME, BANK_FLAG, BELONG_BANK from mag_area,minit  where (belong_bank = ? or area_code = ?) and  area_code = local_code(+) and local_code is not null";
						Vector paras_scan = new Vector(2);
						String[] tmp1 = {"s",loginBankCode};
						paras_scan.addElement(tmp1);
						String[] tmp2 = {"s",loginBankCode};
						paras_scan.addElement(tmp2);
						sqltool = new SqlTool(this);
						sqltool.getConn("missign");
						ResultSet rs=sqltool.executeQuery(selectBelongBank,paras_scan);
			  while(rs.next()){
				  bankFlag = rs.getString(3);
				  bankCode = rs.getString(1);
				  bankName = rs.getString(2);

				  KeyedDataCollection kdcoll= new KeyedDataCollection();
				  kdcoll.setName("CodeName1");
				  DataElement dfield1= new DataElement();
				  DataElement dfield2= new DataElement();
				  DataElement dfield3= new DataElement();
				  dfield1.setName("area_code");
				  dfield2.setName("area_name");
				  dfield3.setName("area_flag");
				  kdcoll.addElement(dfield1);
				  kdcoll.addElement(dfield2);
				  kdcoll.addElement(dfield3);
				  kdcoll.setValueAt("area_code", bankCode);
				  kdcoll.setValueAt("area_name", bankName);
				  kdcoll.setValueAt("area_flag", bankFlag);
				  iResult.addElement(kdcoll);
			  }
						  rs.close();;
			  sqltool.closeconn();


			  try{
				  this.removeDataField("iBelongBankCode");
			  }
			  catch(Exception e){}
			  getOperationData().addElement(iResult);
			  this.setReplyPage("/icbc/cmis/util/ChooseBelongArea.jsp");
			  this.setOperationDataToSession();
		  }
		  catch(Exception e){
			  try{
				  sqltool.closeconn();
			  }
			  catch(Exception e3){}
			  throw e;
		  }


	  }

}