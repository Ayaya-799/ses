package icbc.cmis.util;

import icbc.cmis.service.CmisDao;
import icbc.cmis.base.TranFailException;
import java.sql.*;
/**
 * @author Administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class IDAndCryptoConfigDAO extends CmisDao{
  public IDAndCryptoConfigDAO(icbc.cmis.operation.CmisOperation op) {
  super(op);
  }

  public void updateIDAndCrypto(String accountCode,String id,String idType, String crypto) throws TranFailException {
    PreparedStatement pstmt = null;
    String sql = "update mag_employee set id=?,id_type=? where employee_code = ?";
    String insert_sql ="insert into MAG_EMPLOYEE_PASS (EMPLOYEE_CODE,EMPLOYEE_PASSWD) values(?,?)";
    int n = 0;
    try{
      this.getConnection("missign");
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,id);
      pstmt.setString(2,idType);
      pstmt.setString(3,accountCode);
      try{
        n = pstmt.executeUpdate();
      }
      catch(SQLException se){
        if(se.getErrorCode() == 1)
          throw new TranFailException(
            "util.IDAndTypeConfigDAO",
            "IDAndTypeConfigDAO.updateIDAndCrypto();",
            "��֤�����������ݿ����Ѿ����ڣ�һ��֤������ֻ�ܵǼ�һ�Ρ�","��֤�����������ݿ����Ѿ����ڡ�");
        else
          throw se;
      }
      if(n!=1)
        throw new TranFailException(
            "util.IDAndTypeConfigDAO",
            "IDAndTypeConfigDAO.updateIDAndCrypto();",
            "����֤�����ͺ����֤������ʧ��","����֤�����ͺ����֤������ʧ��");
      pstmt.close();
      pstmt = conn.prepareStatement(insert_sql);
      pstmt.setString(1,accountCode);
      pstmt.setString(2,crypto);
      n = pstmt.executeUpdate();
      if(n!=1)
        throw new TranFailException(
            "util.IDAndTypeConfigDAO",
            "IDAndTypeConfigDAO.updateIDAndCrypto();",
            "������������ʧ��","������������ʧ��");
      conn.commit();
      
    }
    catch(TranFailException te){
      try{
        conn.rollback();
      }
      catch(Exception e){}
      throw te;  
    }
    
    catch(Exception e){
      try{
        conn.rollback();
      }
      catch(Exception ee){}
      throw new TranFailException(
            "util.IDAndTypeConfigDAO",
            "IDAndTypeConfigDAO.updateIDAndCrypto();",
            "����֤�����ͺ����֤���������������ʧ��","����֤�����ͺ����֤���������������ʧ��");  
    }
    finally {
      
      try { if(pstmt != null) pstmt.close(); } catch (Exception ex) {}
      this.closeConnection();
    }
  }
}
