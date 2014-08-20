/*
 * �������� 2006-9-28
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package icbc.cmis.service.channel.ipcgw;

import java.sql.*;
import java.util.Hashtable;
import java.util.Vector;

import icbc.cmis.base.TranFailException;
import icbc.cmis.operation.CmisOperation;
import icbc.cmis.service.CmisDao;

/**
 * @author ZJFH-wanglx
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class ToolsDAO extends CmisDao {
  /**
   * @param op
   */
  public ToolsDAO(CmisOperation op) {
    super(op);
  }
  /**��¼�����Ϣ
   * @param viewname
   * @param debtAssetCode
   * @throws TranFailException
   */
  public void trace(
    String i_ip,
    String i_workthread,
    String i_data,
    String i_threadnum,
    String i_addressport,
    String i_exception,
    String i_state,
    String i_sendRec) {
    String sql = null;
    PreparedStatement pstmt = null;
    int rs;
    try {
      this.getConnection("missign");
      sql =
        "insert into missign.LOG_FOR_ICPGW(times,host,workthread,no,data,threadnum,addressport,exceptionss,state,sendRec)"
          + " values(to_char(cmisdate,'yyyymmddhh24miss'),?,?,(select lpad(to_char(to_number(nvl(max(no),'0'))+1),10,'0') from missign.LOG_FOR_ICPGW where times=to_char(cmisdate,'yyyymmddhh24miss') and host=? and workthread=?),?,?,?,?,?,?) ";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, i_ip);
      pstmt.setString(2, i_workthread.equals("")?"000":i_workthread);
      pstmt.setString(3, i_ip);
      pstmt.setString(4, i_workthread.equals("")?"000":i_workthread);
      pstmt.setString(5, i_data);
      pstmt.setString(6, i_threadnum);
      pstmt.setString(7, i_addressport);
      pstmt.setString(8, i_exception);
      pstmt.setString(9, i_state);
      pstmt.setString(10, i_sendRec);
      rs = pstmt.executeUpdate();
      conn.commit(); //�ύ
    }
    catch (Exception ex) {
      System.out.println("ToolsDAO.trace" + ex.getMessage());
      try {
        conn.rollback();
      }
      catch (Exception ee) {}
    }
    finally {
      if (pstmt != null)
        try {
          pstmt.close();
        }
        catch (Exception ex) {};
      this.closeConnection();
    }

  }
  /**��������Ϣ
   * @param viewname
   * @param debtAssetCode
   * @throws TranFailException
   */
  public void del_trace(String i_ip, String i_datetime) {
    String sql = null;
    PreparedStatement pstmt = null;
    int rs;
    try {
      this.getConnection("missign");
      sql = "delete from missign.LOG_FOR_ICPGW where host=?  and substr(times,1,length(?))=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, i_ip);
	  pstmt.setString(2, i_datetime);
      pstmt.setString(3, i_datetime);
      rs = pstmt.executeUpdate();
      conn.commit(); //�ύ
    }
    catch (Exception ex) {
      System.out.println("ToolsDAO.del_trace" + ex.getMessage());
      try {
        conn.rollback();
      }
      catch (Exception ee) {}
    }
    finally {
      if (pstmt != null)
        try {
          pstmt.close();
        }
        catch (Exception ex) {};
      this.closeConnection();
    }
  }
  /**��ѯ�����Ϣ
   * @param inParamValue
   * @return
   * @throws TranFailException
   */
  public Hashtable query_trace(Vector inParamValue) throws TranFailException {

    CallableStatement call = null;
    Hashtable ht;
    try {
      if (inParamValue == null)
        throw new Exception("���ṩ�洢���̵��õ����������Ϣ");
      getConnection("missign");
      call = conn.prepareCall("{CALL missign.pack_ipcgw_public.query_info(?,?,?,?,?,?,?,?,?)}");
      int outIdx = inParamValue.size() + 1;
      for (int i = 0; i < inParamValue.size(); i++) {
        call.setString(1 + i, (String)inParamValue.get(i));
      }
      call.registerOutParameter(outIdx, Types.VARCHAR);
      call.registerOutParameter(outIdx + 1, Types.VARCHAR);
      call.registerOutParameter(outIdx + 2, Types.VARCHAR);
      call.registerOutParameter(outIdx + 3, Types.VARCHAR);
      call.registerOutParameter(outIdx + 4, Types.VARCHAR);
      call.registerOutParameter(outIdx + 5, Types.VARCHAR);
      call.registerOutParameter(outIdx + 6, Types.VARCHAR);
      call.execute();
      String o_maxThread = call.getString(outIdx);
      String o_normalget = call.getString(outIdx + 1);
      String o_abnormalget = call.getString(outIdx + 2);
      String o_normalput = call.getString(outIdx + 3);
      String o_abnormalgput = call.getString(outIdx + 4);
      String out_flag = call.getString(outIdx + 5);
      String out_msg = call.getString(outIdx + 6);
      if (out_flag == null || !out_flag.equals("0")) {
        throw new TranFailException("000", //������룬ʹ���߿�
        getClass().getName() + ".query_trace()", //����λ��,�����߿�
        "�洢����ִ�д���" + out_msg);
      }
      Hashtable h_table = new Hashtable();
      h_table.put("o_maxThread", o_maxThread == null ? "" : o_maxThread);
      h_table.put("o_normalget", o_normalget == null ? "" : o_normalget);
      h_table.put("o_abnormalget", o_abnormalget == null ? "" : o_abnormalget);
      h_table.put("o_normalput", o_normalput == null ? "" : o_normalput);
      h_table.put("o_abnormalgput", o_abnormalgput == null ? "" : o_abnormalgput);
      call.close();
      conn.commit();
      closeConnection();
      return h_table;
    }
    catch (TranFailException e) {
      throw e;
    }
    catch (SQLException sqlex) {
      throw new TranFailException("000", //������룬ʹ���߿�
      getClass().getName() + ".query_trace()", //����λ��,�����߿�
      sqlex.getMessage()); //�������ݣ������߿�);
    }
    catch (Exception ex) {
      throw new TranFailException("000", //������룬ʹ���߿�
      getClass().getName() + ".query_trace()", //����λ��,�����߿�
      ex.getMessage()); //�������ݣ������߿�); 
    }
    finally {
      try {
        conn.rollback();
        if (call != null)
          call.close();
      }
      catch (Exception ee) {};
      closeConnection();
    }

  }
}