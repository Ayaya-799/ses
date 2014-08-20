package icbc.cmis.util;

import icbc.cmis.operation.*;
import icbc.cmis.base.TranFailException;
import icbc.missign.Employee;
import java.util.Vector;
import java.util.Hashtable;


public class ChooseEnp2 extends CmisOperation {
  private static final int PAGE_LINES = 15;

  public ChooseEnp2() {
  }
  public void execute() throws TranFailException {
    try{
      String flag = this.getStringAt("flag");
      String page = this.getStringAt("page");
      //�жϱ�־�����ݱ�־ת��ͬ����
      if(flag.equals("newQuery")) {
        newQuery();
      } else if(flag.equals("query")) {
        query(page);
      } else if(flag.equals("done")) {
        clear();
      }
    }
    catch (TranFailException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new TranFailException("cmisUTIL800","",ex.getMessage(),"������ȡʧ�ܣ�" + ex.getMessage());
    }
  }

  public void newQuery() throws TranFailException {
    //�²�ѯ��ȡ����ѯ�������������ݲ�ѯģ��õ���������������SESSION
    //���ؽ�����ĵ�һҳ
    try {
      //ȡ����
      Employee employee = (Employee)this.getSessionData("Employee");
      String TA200011001 = this.getStringAt("TA200011001");
      String TA200011003 = this.getStringAt("TA200011003");
      String TA200011005 = this.getStringAt("TA200011005");
      String TA200011010 = this.getStringAt("TA200011010");
      String TA200011011 = this.getStringAt("TA200011011");
      String TA200011012 = this.getStringAt("TA200011012");
      String TA200011014 = this.getStringAt("TA200011014");
      String TA200011016 = this.getStringAt("TA200011016");
      String TA200011031 = this.getStringAt("TA200011031");
      String queryType = this.getStringAt("queryType");

      Hashtable paras = new Hashtable();
      Vector pnames = this.getElementNames();
      for (int i = 0; i < pnames.size(); i++) {
        String tname = (String)pnames.get(i);
        try {
          paras.put(tname,this.getStringAt(tname));
        }
        catch (Exception ex) {
        }
      }

      //�������ݲ�ѯģ��õ���������������SESSION
      ChooseEnpDAO dao = new ChooseEnpDAO(this);
      Vector datas = dao.getEnterprises(queryType,employee,TA200011001,TA200011003,TA200011005,TA200011010,TA200011011,TA200011012,TA200011014,TA200011016,TA200011031,paras);
      this.setFieldValue("enterprise3GB2U",datas);
      this.setOperationDataToSession();
      //���ؽ�����ĵ�һҳ
      query("1");
    } catch(TranFailException ex) {
      throw ex;
    } catch(Exception ex) {
      throw new TranFailException("cmisUTIL801","",ex.getMessage(),ex.getMessage());
    }

  }

  public void query(String spage) throws TranFailException{
    //��SESSION��ȡ�ؽ����������ҳ�ŷ��ؽ��
    try {
      //��SESSION��ȡ�ؽ����
      Vector datas = (Vector)this.getObjectAt("enterprise3GB2U");
      String warning = (String)datas.get(0);
      //����Ӧȡ�صļ�¼��ʼ����ֹ���
      int page = Integer.valueOf(spage).intValue();
      int begin = (page - 1) * PAGE_LINES + 1;
      int end   = (page) * PAGE_LINES;
      //ȡ��¼����С
      int last = datas.size() - 1;
      if(datas.isEmpty()) {
        last = 0;
      }
      int pages = (last - 1)/ PAGE_LINES + 1;
      //���ؽ��
      String ret = "<?xml version=\"1.0\" encoding=\"GBK\"?>";
      ret += ("<datas page='" + page + "' pages='" + pages + "' lines='" + last + "' warning='" + warning + "'>");
      for(int i = begin; i <= end && i <= last; i++) {
        String[] row = (String[])datas.get(i);
        ret += ("<en o='" + i + "' c='" + row[0] + "' n='" + row[1] + "' j='" + row[2] + "' d='" + row[3] + "' />");
      }
      ret += "</datas>";
      this.setOperationDataToSession();
      this.setReplyPage("DirectOutput" + ret);
    } catch(Exception ex) {
      throw new TranFailException("cmisUTIL802","",ex.getMessage(),"������ȡʧ�ܣ�" + ex.getMessage());
    }

  }

  public void clear() {
    //���SESSION�е��������
    try {
      this.removeDataField("enterprise3GB2U");
      String ret = "<?xml version=\"1.0\" encoding=\"GBK\"?><ok />";
      this.setOperationDataToSession();
      this.setReplyPage("DirectOutput" + ret);
    }
    catch (Exception ex) {};
  }
}