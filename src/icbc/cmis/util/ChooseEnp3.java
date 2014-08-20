package icbc.cmis.util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import icbc.cmis.base.*;
import icbc.missign.Employee;
import icbc.cmis.util.Decode;

public class ChooseEnp3 extends HttpServlet {
  private static final int PAGE_LINES = 15;
  private static final String CONTENT_TYPE = "text/xml; charset=GBK";
//  private static final String CONTENT_TYPE = "text/xml";
  /**Initialize global variables*/
  public void init() throws ServletException {
  }
  /**Process the HTTP Get request*/
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
 }

  /**Clean up resources*/
  public void destroy() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    CMisSessionMgr session = new CMisSessionMgr(request);

    try {
      if(!CMisSessionMgr.checkSession(request)){
        outTime(out,"  ��ǰ�Ự��ʧЧ�������µ�¼");
        return;
      }

      String flag = request.getParameter("flag");
      String page = request.getParameter("page");

      //�жϱ�־�����ݱ�־ת��ͬ����
      if(flag.equals("newQuery")) {
        newQuery(request,session,out);
      } else if(flag.equals("query")) {
        query(session,out,page);
      } else if(flag.equals("done")) {
        String ccode = Decode.decode(request.getParameter("ccode"));
        String cname = Decode.decode(request.getParameter("cname"));
        clear(session,out,ccode,cname);
      }

    }
    catch (TranFailException ex) {
        outTime(out,"������ȡʧ�ܣ�" + ex.getErrorCode()+"" + ex.getErrorLocation()+"" + ex.getDisplayMessage()+"" + ex.getErrorMsg()+"");
    }
    catch (Exception ex) {
        outTime(out,"������ȡʧ�ܣ�" + ex.getMessage());
    }

  }

  public void newQuery(HttpServletRequest request,CMisSessionMgr session,PrintWriter out) throws TranFailException {
    //�²�ѯ��ȡ����ѯ�������������ݲ�ѯģ��õ���������������SESSION
    //���ؽ�����ĵ�һҳ
    try {
      //ȡ����
      Employee employee = (Employee)session.getSessionData("Employee");
      String TA200011001 = request.getParameter("TA200011001");
      if(TA200011001 == null) TA200011001 = "";
      String TA200011003 = request.getParameter("TA200011003");
      if(TA200011003 == null) TA200011003 = "";
      TA200011003 = Decode.decode(TA200011003);
      String TA200011005 = request.getParameter("TA200011005");
      if(TA200011005 == null) TA200011005 = "";
      String TA200011010 = request.getParameter("TA200011010");
      if(TA200011010 == null) TA200011010 = "";
      String TA200011011 = request.getParameter("TA200011011");
      if(TA200011011 == null) TA200011011 = "";
      String TA200011012 = request.getParameter("TA200011012");
      if(TA200011012 == null) TA200011012 = "";
      String TA200011014 = request.getParameter("TA200011014");
      if(TA200011014 == null) TA200011014 = "";
      String TA200011016 = request.getParameter("TA200011016");
      if(TA200011016 == null) TA200011016 = "";
      String TA200011031 = request.getParameter("TA200011031");
      if(TA200011031 == null) TA200011031 = "";
      String queryType = request.getParameter("queryType");

      Hashtable paras = new Hashtable();
      Enumeration pnames = request.getParameterNames();
      while (pnames.hasMoreElements()) {
        String tname = (String)pnames.nextElement();
        paras.put(tname,request.getParameter(tname));
      }

      //�������ݲ�ѯģ��õ���������������SESSION
      ChooseEnp3DAO dao = new ChooseEnp3DAO(new icbc.cmis.operation.DummyOperation());
      String sql[] = dao.genSQL(queryType,employee,TA200011001,TA200011003,TA200011005,TA200011010,TA200011011,TA200011012,TA200011014,TA200011016,TA200011031,paras);
      session.updateSessionData("enterprise3GB2U",sql);
      int rowCount = dao.getRecordCount(sql[0]);
      session.updateSessionData("recordCount3GB2U",String.valueOf(rowCount));
      //���ؽ�����ĵ�һҳ
      query(session,out,"1");
    } catch(TranFailException ex) {
      throw ex;
    } catch(Exception ex) {
      throw new TranFailException("cmisUTIL801","",ex.getMessage(),ex.getMessage());
    }

  }

  public void query(CMisSessionMgr session,PrintWriter out,String spage) throws TranFailException{
    //��SESSION��ȡ�ؽ����������ҳ�ŷ��ؽ��
    try {
      //��SESSION��ȡ��sql
      String sql[] = (String[])session.getSessionData("enterprise3GB2U");
      int rowCount = Integer.parseInt((String)session.getSessionData("recordCount3GB2U"));
      String warning = "";//"  �� " + rowCount + " ����¼";

      //����Ӧȡ�صļ�¼��ʼ����ֹ���
      int page = Integer.parseInt(spage);
      int begin = (page - 1) * PAGE_LINES + 1;
      int end   = (page) * PAGE_LINES;

      //ȡ��¼����С
      int last = rowCount;
      int pages = (last - 1)/ PAGE_LINES + 1;
      ChooseEnp3DAO dao = new ChooseEnp3DAO(new icbc.cmis.operation.DummyOperation());
      Vector datas = dao.query(sql[1],begin,end);

      //���ؽ��
      out.println("<?xml version=\"1.0\" encoding=\"GBK\"?>");
      out.print("<datas page='" + page + "' pages='" + pages + "' lines='" + last + "' warning='" + warning + "'>");
      int j = 0;
      if (!datas.isEmpty()) {
        j = datas.size();
      }

      for(int i = 0; i <j; i++) {
        String[] row = (String[])datas.get(i);
        out.print("<en o='" + row[4] + "' c='" + row[0] + "' n='" + row[1] + "' j='" + row[2] + "' d='" + row[3] + "' />");
      }
      out.print("</datas>");
    } catch(Exception ex) {
      throw new TranFailException("","",ex.getMessage(),ex.getMessage());
    }

  }

  public void clear(CMisSessionMgr session,PrintWriter out,String ccode,String cname) {
    //���SESSION�е��������
    try {
      //System.out.println("clear enterprise3GB2U!!!");
      session.removeSessionData("enterprise3GB2U");
      session.removeSessionData("recordCount3GB2U");
      session.addSessionData("CustomerCodeInSession",ccode);
      session.addSessionData("CustomerNameInSession",cname);
    }
    catch (Exception ex) {};
    out.println("<?xml version=\"1.0\" encoding=\"GBK\"?>");
    out.println("<ok />");
  }

  public void outTime(PrintWriter out,String info) {
    out.println("<?xml version=\"1.0\"?>");
    out.println("<error>");
    out.println(info);
    out.println("</error>");
  }
}