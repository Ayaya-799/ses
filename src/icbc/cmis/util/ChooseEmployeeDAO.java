package icbc.cmis.util;

import icbc.cmis.service.CmisDao;
import icbc.cmis.base.MuiTranFailException;
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

public class ChooseEmployeeDAO extends CmisDao {

  public ChooseEmployeeDAO(icbc.cmis.operation.CmisOperation op) {
  super(op);
  }

  /**
   * ���ݴ���Ĳ�������mag_employee_major����ѡ���Ա�����ع�Ա�б�H
   * @param eCode ��Ա��a
   * @param area �����ţ���������ö��ŷָ�?
   * @param major רҵ�����רҵ�ö��ŷָ�?
   * @param eClasses ��ѡ��Ա��ɫ�������ɫ�ö��ŷָ�?
   * @param includeSelf �Ƿ�����Լ���true|false
   * @return Vector��Ա�б�?
   * @param boolean exludeInsideCode �Ƿ��ų��ڲ���Ա 
   * @throws TranFailException
   */
  public Vector getEmployee(String eCode,String area,String major,String eClasses,
                            boolean includeSelf,String distinctIgnor,boolean exludeInsideCode) throws TranFailException{
    String where = "";
    if(major.length() > 0) {
      StringBufferR majors = new StringBufferR("'"+major+"'");
      majors.replace(",","','");
      where = " and employee_major in (" + majors.toString() + ") ";
    }
    if(area.length() > 0) {
      StringBufferR areas = new StringBufferR("'"+area+"'");
      areas.replace(",","','");
      where += (" and b.mdb_sid in (" + areas.toString() + ") ");
    }
    if(!includeSelf) {
      StringBufferR eCodes = new StringBufferR("'"+eCode+"'");
      eCodes.replace(",","','");
      where += (" and b.employee_code not in (" + eCodes.toString() + ") ");
    }
    if(eClasses.length() > 0) {
      where += (" and employee_class in (" + eClasses + ") ");
    }
    if(where.length() > 0) {
      where = where.substring(4) + " and ";
    }
    //��ѯmag_employee��t
    String sql = "select distinct b.employee_code||' '||rpad(rtrim(b.employee_name),9,'.')||'..'";
    String select = ",b.employee_code,b.employee_name";

    if (distinctIgnor.charAt(0) == '1') {//ignor major
      sql = sql + "||'.............'";
      select = select + ",null,null";
    }
    else {
      sql = sql + "||rpad(major_name,19,'.')";
      select = select + ",employee_major,major_name";
    }

    if (distinctIgnor.charAt(1) == '1') {//ignor class
      select = select + ",null,null";
    }
    else {
      sql = sql + "||class_name";
      select = select + ",employee_class,class_name";
    }
    sql = sql + select + " from mag_employee_major a,mag_employee_class,mag_major,mag_employee b where "
              + where + " b.employee_delete_flag(+) = 0  and "
              + " employee_class = class_code(+) and employee_major = mag_employee_class.major_code(+) and employee_major = mag_major.major_code(+) ";
              
              
     //20070623--------
     
     sql+=" and mag_employee_class.lang_code='"+(String)super.getOperation().getSessionData("LangCode")+"' ";
     sql+=" and  mag_major.lang_code = mag_employee_class.lang_code  ";
     
     //---------
     
    //�޸�ԭ������һ���Ƿ��ų��ڲ���Ա�Ĳ���
    //�޸�����:	2004-5-20 
    //�޸��ˣ������
    if(exludeInsideCode)
    	sql=sql+" and (b.employee_code not in ( select inside_employee_code from missign.mag_employee_comparison )) ";
    sql=sql+" and a.employee_code(+) = b.employee_code order by b.employee_code ";
    Statement stmt = null;
    ResultSet rs = null;
    Vector retVector = new Vector();
    try {
      this.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        String[] row = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
        retVector.add(row);
      }
    }
    catch (TranFailException ex) {
      throw ex;
    }
    catch (Exception ex) {
//      throw new TranFailException("cmisutil200","icbc.cmis.util.ChooseEmployeeDAO",ex.getMessage() + sql,"������Ա�б����");
			throw new MuiTranFailException("099995", "ChooseEmployeeDAO.getEmployee()",(String)this.getOperation().getSessionData("LangCode"));
    }
    finally {
      if(rs != null) try {rs.close();} catch (Exception ex) {};
      if(stmt != null) try {stmt.close();} catch (Exception ex) {};
      this.closeConnection();
    }
    return retVector;

  }
}