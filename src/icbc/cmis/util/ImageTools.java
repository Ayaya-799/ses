/*
 * �������� 2006-7-26
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package icbc.cmis.util;
import icbc.cmis.base.*;
import icbc.missign.*;

/**
 * @author ZJFH-yanb
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class ImageTools {
  public String getImageOutEmp(CMisSessionMgr sm) {
    String ret = "";
    try {
      Employee employee = (Employee)sm.getSessionData("Employee");
      String outEmp = employee.getOutsideEmpCode();
      outEmp = outEmp.substring(4);
      int test = Integer.parseInt(outEmp);
      ret = outEmp;
    }
    catch (Exception e) {
      ret = "00000";
    }
    return ret;
  }

}
