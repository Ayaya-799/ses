package icbc.cmis.util;

import icbc.cmis.service.CmisDao;
import icbc.cmis.base.TranFailException;
import java.util.Vector;
import java.sql.*;
/**
 * <p>Title: ������Ա�б�</p>
 * <p>Description: ���ݴ���Ĳ��������ع�Ա�б�</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ChooseBelongEmpDAO extends CmisDao {

  public ChooseBelongEmpDAO(icbc.cmis.operation.CmisOperation op) {
  super(op);
  }

  /**
   * ���ݴ���Ĳ�������mag_employee_major����ѡ���Ա�����ع�Ա�б�
   * @param eCode ��Ա��
   * @param area �����ţ���������ö��ŷָ�
   * @param major רҵ�����רҵ�ö��ŷָ�
   * @param eClasses ��ѡ��Ա��ɫ�������ɫ�ö��ŷָ�
   * @param includeSelf �Ƿ�����Լ���true|false
   * @return Vector��Ա�б�
   * @throws TranFailException
   */
  public Vector getEmployee(String eCode,String area,String major,String eClasses,
                            boolean includeSelf,String distinctIgnor) throws TranFailException{
    String where = "";
    if(major.length() > 0) {
      StringBufferR majors = new StringBufferR("'"+major+"'");
      majors.replace(",","','");
      where = " and employee_major in (" + majors.toString() + ") ";
    }
    if(area.length() > 0) {
      StringBufferR areas = new StringBufferR("'"+area+"'");
      //areas.replace(",","','");
      where += (" and employee_area in ( select AREA_CODE from mag_area  start with area_code = '" +
                      area + "' connect by belong_bank = prior  area_code )"); 
    }
    if(!includeSelf) {
      StringBufferR eCodes = new StringBufferR("'"+eCode+"'");
      eCodes.replace(",","','");
      where += (" and a.employee_code not in (" + eCodes.toString() + ") ");
    }
    if(eClasses.length() > 0) {
      where += (" and employee_class in (" + eClasses + ") ");
    }
    if(where.length() > 0) {
      where = where.substring(4) + " and ";
    }
    //��ѯmag_employee��
    String sql = "select distinct a.employee_code||' '||rpad(rtrim(b.employee_name),9,'.')||'..'";
    String select = ",a.employee_code,b.employee_name";

    if (distinctIgnor.charAt(0) == '1') {//ignor major
      sql = sql + "||'.............'";
      select = select + ",null,null";
    }
    else {
      sql = sql + "||rpad(major_name,13,'.')";
      select = select + ",employee_major,major_name";
    }

    if (distinctIgnor.charAt(1) == '1') {//ignor class
      select = select + ",null,null,area_code,area_name";
    }
    else {
      sql = sql + "||class_name";
      select = select + ",employee_class,class_name,area_code,area_name";
    }

    sql = sql + select + " from mag_employee_major a,mag_employee_class,mag_major,mag_employee b ,mag_area where "
              + where + " a.employee_delete_flag = 0  and employee_area = area_code and "
              + " employee_class = class_code and employee_major = mag_employee_class.major_code and employee_major = mag_major.major_code and"
              + " a.employee_code = b.employee_code order by a.employee_code";

    Statement stmt = null;
    ResultSet rs = null;
    Vector retVector = new Vector();
    try {
      this.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        String[] row = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
        retVector.add(row);
      }
    }
    catch (TranFailException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new TranFailException("cmisutil200","icbc.cmis.util.ChooseEmployeeDAO",ex.getMessage() + sql,"������Ա�б����");
    }
    finally {
      if(rs != null) try {rs.close();} catch (Exception ex) {};
      if(stmt != null) try {stmt.close();} catch (Exception ex) {};
      this.closeConnection();
    }
    return retVector;

  }
}