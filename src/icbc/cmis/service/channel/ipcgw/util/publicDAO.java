/*
 * �������� 2006-11-2
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package icbc.cmis.service.channel.ipcgw.util;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Hashtable;
import java.util.Vector;

import icbc.cmis.base.TranFailException;
import icbc.cmis.operation.CmisOperation;
import icbc.cmis.service.CmisDao;
import icbc.cmis.util.DBTools;

/**
 * @author ZJFH-wanglx
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class publicDAO extends CmisDao {
  /**
   * @param op
   */
  public publicDAO(CmisOperation op) {
    super(op);
  }
  /**�������ݿ�ö���
   * @param v_param
   * @throws TranFailException
   */
  public void insert_it(Vector inParamValue) throws TranFailException {

    CallableStatement call = null;
    try {
      if (inParamValue == null)
        throw new Exception("���ṩ�洢���̵��õĲ�����Ϣ");
      getConnection("missign");
      String procStr =
        "{CALL missign.pack_ipcgw_public.insert_it(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
      call = conn.prepareCall(procStr);
      String outType = "";
      for (int i = 0; i < 41; i++) {
        call.setString(1 + i, (String)inParamValue.get(i));
      }
      call.registerOutParameter(42, Types.VARCHAR);
      call.registerOutParameter(43, Types.VARCHAR);
      call.execute();
      String s_out_sign = call.getString(42);
      String s_out_Msg = call.getString(43);
      if (!s_out_sign.equals("0")) {
        throw new TranFailException("icbc.cmis.service.channel.ipcgw.util.publicDAO", "publicDAO.inset_it()", s_out_Msg, s_out_Msg);

      }
      conn.commit();
    }
    catch (TranFailException e) {
      throw e;
    }
    catch (Exception ex) {
      for (int j = 0; j < 41; j++) {
        System.out.print("#wlx#"+inParamValue.size()+"#" + (String)inParamValue.get(j));
      }
      try {
        conn.rollback();
      }
      catch (Exception ee) {};
      throw new TranFailException("icbc.cmis.service.channel.ipcgw.util.publicDAO", "publicDAO.inset_it()", ex.getMessage(), ex.getMessage());
    }
    finally {
      if (call != null) {
        try {
          call.close();
        }
        catch (Exception ee) {};
      }
      closeConnection();
    }
  }
}