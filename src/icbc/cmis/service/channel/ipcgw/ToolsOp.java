/*
 * �������� 2006-11-13
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package icbc.cmis.service.channel.ipcgw;

import java.util.Hashtable;
import java.util.Vector;

import icbc.cmis.base.CmisConstance;
import icbc.cmis.base.TranFailException;
import icbc.cmis.operation.CmisOperation;
import icbc.cmis.second.pkg.DBProcedureAccessService;
import icbc.cmis.second.pkg.DBProcedureParamsDef;

/**
 * @author ZJFH-wanglx
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class ToolsOp extends CmisOperation {
  /**
   * 
   */
  public ToolsOp() {
    super();
  }
  public void execute() throws TranFailException {
    try {
      String opAction = this.getStringAt("opAction");
      if (opAction.equals("20001")) {
        query_trace();
      }
      else if (opAction.equals("40001")) {
        del_trace();
      }
    }
    catch (TranFailException ex) {

      throw ex;
    }
    catch (Exception e) {
      throw new TranFailException("ToolsOp",
      //������룬ʹ���߿�
      getClass().getName() + ".execute()", //����λ��,�����߿�
      e.getMessage() //�������ݣ������߿�
      );
    }
  }
  /**
   * @throws TranFailException
   */
  private void query_trace() throws TranFailException {
    try {
      ToolsDAO dao = new ToolsDAO(this);
      Vector v_param = new Vector();
      v_param.add(this.getStringAt("v_queryTime"));
      v_param.add(this.getStringAt("v_hostIP"));
      Hashtable h_table = dao.query_trace(v_param);
      this.setFieldValue("flag", "1");
      this.setFieldValue("h_table", h_table);
      this.setReplyPage("/util/util_ICPGW_Mag.jsp");
    }
    catch (TranFailException ex) {
      throw ex;
    }
    catch (Exception e) {
      throw new TranFailException("099993", //������롣
      "ToolsOp.query_it()", //������ʾ�ĳ���λ�á�
      e.getMessage(), //������������Ϣ��
      e.getMessage() //���������Ĵ�����Ϣ��
      );
    }
  }
  /**
   * @throws TranFailException
   */
  private void del_trace() throws TranFailException {
    try {
      ToolsDAO dao = new ToolsDAO(this);
      dao.del_trace(this.getStringAt("v_hostIP"), this.getStringAt("v_queryTime"));
      this.setFieldValue("okTitle", "�ɹ�");
      this.setFieldValue("okMsg", "ɾ���ɹ�");
      String baseWebPath = (String)CmisConstance.getParameterSettings().get("webBasePath");
      this.setFieldValue("okReturn", baseWebPath + "/util/util_ICPGW_Mag.jsp");
      this.setReplyPage("/ok.jsp");
    }
    catch (TranFailException ex) {
      throw ex;
    }
    catch (Exception e) {
      throw new TranFailException("099993", //������롣
      "ToolsOp.del_trace()", //������ʾ�ĳ���λ�á�
      e.getMessage(), //������������Ϣ��
      e.getMessage() //���������Ĵ�����Ϣ��
      );
    }
  }
}
