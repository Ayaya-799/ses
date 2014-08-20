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

public class ChooseBelongEmp extends CmisOperation {

  public ChooseBelongEmp() {
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
    String distinctIgnor = "00";
    try {
      distinctIgnor = this.getStringAt("distinctIgnoreWsA342d");
    }
    catch (Exception ex) {
    }


    try {
      ChooseBelongEmpDAO dao = new ChooseBelongEmpDAO(this);
      Vector employee = dao.getEmployee(eCode,area,major,eClass,Boolean.valueOf(includeSelf).booleanValue(),distinctIgnor);
      this.setFieldValue("cmisChooseEmployeeDataWsA342d",employee);
      this.setFieldValue("cmisChooseEmployeeMultiSelectWsA342d",multiSelect);
      this.setOperationDataToSession();
      this.setReplyPage("/icbc/cmis/util/util_ChooseBelongEmp.jsp");
    } catch (TranFailException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new TranFailException("cmisutil201","icbc.cmis.util.ChooseEmployee",ex.getMessage(),"���ɹ�Ա�б����");
    }
  }
}