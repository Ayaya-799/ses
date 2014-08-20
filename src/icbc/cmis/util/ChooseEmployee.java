package icbc.cmis.util;

import icbc.cmis.base.*;
import icbc.cmis.operation.*;
import java.util.Vector;

/**
 * <p>Title: ��Աѡ��</p>
 * <p>Description:��ģ���ṩ��������ѡ�񵥸�������Ա�Ĺ��ܡ� </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company:icbc </p>
 * @author Ҷ��ͦ
 * @version 1.0
 */

public class ChooseEmployee extends CmisOperation {

  public ChooseEmployee() {
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
  public void execute() throws TranFailException{
    String eCode = this.getStringAt("eCodeWsA342d");
    String area = this.getStringAt("areaWsA342d");
    String major = this.getStringAt("majorWsA342d");
    String eClass = this.getStringAt("eClassWsA342d");
    String includeSelf = this.getStringAt("includeSelfWsA342d");
    String multiSelect = this.getStringAt("multiSelectWsA342d");
	//�޸�ԭ������һ���Ƿ��ų��ڲ���Ա�Ĳ��� exludeInsideCode342d
	//�޸�����: 2004-5-20 
	//�޸��ˣ������
    boolean exludeInsideCode = false;
    if(isElementExist("exludeInsideCode342d"))
		exludeInsideCode=new  Boolean( this.getStringAt("exludeInsideCode342d")).booleanValue();
    String distinctIgnor = "00";
    try {
      distinctIgnor = this.getStringAt("distinctIgnoreWsA342d");
    }
    catch (Exception ex) {
    }


    try {
      ChooseEmployeeDAO dao = new ChooseEmployeeDAO(this);
      Vector employee = dao.getEmployee(eCode,area,major,eClass,Boolean.valueOf(includeSelf).booleanValue(),distinctIgnor,exludeInsideCode);
      this.setFieldValue("cmisChooseEmployeeDataWsA342d",employee);
      this.setFieldValue("cmisChooseEmployeeMultiSelectWsA342d",multiSelect);
      this.setOperationDataToSession();
      this.setReplyPage("/icbc/cmis/util/util_ChooseEmployee.jsp");
    } catch (TranFailException ex) {
      throw ex;
    } catch (Exception ex) {
//      throw new TranFailException("cmisutil201","icbc.cmis.util.ChooseEmployee",ex.getMessage(),"���ɹ�Ա�б����");
			throw new MuiTranFailException("099995", "ChooseEmployee.execute()",(String)this.getSessionData("LangCode"));
    }
  }
}