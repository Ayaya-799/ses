package icbc.cmis.util;

import icbc.cmis.service.CmisDao;
import icbc.cmis.base.TranFailException;
import icbc.cmis.base.SSICTool;
import icbc.missign.Employee;
import java.sql.*;
import java.util.Vector;
import icbc.cmis.util.AuthorizationException;

public class AuthorizationDAO extends CmisDao {

  public AuthorizationDAO(icbc.cmis.operation.CmisOperation op) {
    super(op);
  }

  public String authorize(Employee employee, String ecode2, String passwd, String module, String info) throws TranFailException, AuthorizationException {
    String ecode1 = employee.getEmployeeCode();
    String ename1 = employee.getEmployeeName();
    String area = employee.getMdbSID();
    String major = employee.getEmployeeMajor();
    String majorName = employee.getEmployeeMajorName();

    String insideEmpCode2 = null; //ecode2��Ӧ��area�������ڲ���Ա��
    String xml = "<ok>ok</ok>";
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    java.sql.PreparedStatement pstmt = null;
    if (employee.getOutsideEmpCode().equals(ecode2)) {
      return "<error>��Ա�������Լ�!</error>";
    }

    try {
      this.getConnection();

      //ȡ�ø��ⲿ��Ա�µ�ָ��������ڲ���Ա
      sql = "select inside_employee_code from missign.mag_employee_comparison where outside_employee_code = ? and area_code = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, ecode2);
      pstmt.setString(2, area);
      rs = pstmt.executeQuery();
      if (!rs.next()) {
        throw new AuthorizationException("<error>��Ա:" + ecode2 + "�ڱ�����û�е����κν�ɫ!</error>");
      }
      insideEmpCode2 = rs.getString(1);
      pstmt.close();


      //ȡ�ù�Ա��Ϣ
      sql = "select employee_passwd,employee_name from mag_employee where employee_code = ? ";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, insideEmpCode2);

      rs = pstmt.executeQuery();
      if (!rs.next()) {
        throw new AuthorizationException("<error>�޴˹�Ա:" + ecode2 + "!</error>");
      }

      String passwd2 = rs.getString(1);
      String ename2 = rs.getString(2);
      pstmt.close();

      sql =
        "select employee_class from mag_employee_major where employee_code = ? and employee_major in ('210', ? ) and employee_area = ? and employee_class in ('5','6')";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, insideEmpCode2);
      pstmt.setString(2, employee.getEmployeeMajor());
      pstmt.setString(3, area);

      rs = pstmt.executeQuery();

      if (!rs.next()) {
        throw new AuthorizationException("<error>�ⲿ��Ա" + ecode2  + ename2 + "�ڱ�������Ӧ���ڲ���Ա"+insideEmpCode2 +"û���ڱ����������Ŵ���" + employee.getEmployeeMajorName() + "�е�רҵ����Ա���쵼��ɫ��</error>");
      }

      //int eclass = rs.getInt(1);
      //if(eclass != 5 && eclass != 6) {throw new AuthorizationException("<error>��Ա����Ϊרҵ����Ա���쵼</error>");}
      if (!SSICTool.isSSICEnabled()) {
        if (!Encode.checkPassword(ecode2, passwd, passwd2)) {
          throw new AuthorizationException("<error>��Ա" + ecode2 + "�������!</error>");
        }
      }
      pstmt.close();
      sql = "insert into mag_audit values( ? , ? , ? , ? , ? ,to_char(cmisdate,'yyyymmddhhmiss'),?,?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, area);
      pstmt.setString(2, ecode1);
      pstmt.setString(3, ename1);
      pstmt.setString(4, ecode2);
      pstmt.setString(5, ename2);
      pstmt.setString(6, module);
      pstmt.setString(7, info);

      int rows = pstmt.executeUpdate();
      if (rows == 0) {
        throw new AuthorizationException("<error>������Ȩ��(mag_aduit)����!����0����¼��</error>");
      }

      conn.commit();
    }
    catch (AuthorizationException ex) {
      throw ex;
    }
    catch (TranFailException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new TranFailException("cmisutil215", "icbc.cmis.util.AuthorizationDAO", ex.getMessage(), ex.getMessage());
    }
    finally {
      if (rs != null)
        try {
          rs.close();
        }
        catch (Exception ex) {};
      if (pstmt != null)
        try {
          pstmt.close();
        }
        catch (Exception ex) {};
      this.closeConnection();
    }
    return xml;
  }
}