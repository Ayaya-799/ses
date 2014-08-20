package icbc.cmis.util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import icbc.cmis.base.*;
import icbc.missign.Employee;
import icbc.cmis.util.Decode;

public class ChooseEnpBalance extends HttpServlet {
  private static final int PAGE_LINES = 15;
  private static final int BUFFER_LINES = 10 * PAGE_LINES;
  private static final String CONTENT_TYPE = "text/xml; charset=GBK";
  //  private static final String CONTENT_TYPE = "text/xml";
  /**Initialize global variables*/
  public void init() throws ServletException {}
  /**Process the HTTP Get request*/
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  /**Clean up resources*/
  public void destroy() {}

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    CMisSessionMgr session = new CMisSessionMgr(request);
    String langCode = "";
    try {
      if (!CMisSessionMgr.checkSession(request)) {
        outTime(out, "  ��ǰ�Ự��ʧЧ�������µ�¼<br>Please log on again");
        return;
      }

      langCode = (String)session.getSessionData("LangCode");
      String flag = request.getParameter("flag");
      String page = request.getParameter("page");

      //�жϱ�־�����ݱ�־ת��ͬ����
      if (flag.equals("newQuery")) {
        newQuery(request, session, out);
      }
      else if (flag.equals("query")) {
        query(session, out, page, request);
      }
      else if (flag.equals("done")) {
        String ccode = Decode.decode(request.getParameter("ccode"));
        String cname = Decode.decode(request.getParameter("cname"));
        clear(session, out, ccode, cname);
      }

    }
    catch (TranFailException ex) {
      outTime(out, genMsg.getErrMsgByLang(langCode,"103161"," ") + ex.getErrorCode() + "" + ex.getErrorLocation() + "" + ex.getDisplayMessage() + "" + ex.getErrorMsg() + "");
    }
    catch (Exception ex) {
      outTime(out, genMsg.getErrMsgByLang(langCode,"103161"," ") + ex.getMessage());
    }

  }

  public void newQuery(HttpServletRequest request, CMisSessionMgr session, PrintWriter out) throws TranFailException {
    //�²�ѯ��ȡ����ѯ�������������ݲ�ѯģ��õ���������������SESSION
    //���ؽ�����ĵ�һҳ
    try {
      //ȡ����
      Employee employee = (Employee)session.getSessionData("Employee");
      String TA200361001 = request.getParameter("TA200361001");
      if (TA200361001 == null)
        TA200361001 = "";
      String TA200361008 = request.getParameter("TA200361008");
      if (TA200361008 == null)
        TA200361008 = "";
      String condition = request.getParameter("condition");
      String cond = null;
      if (condition.equals("L"))
        cond = "<";
      else if (condition.equals("LE"))
        cond = "<=";
      else if (condition.equals("E"))
        cond = "=";
      else if (condition.equals("ME"))
        cond = ">=";
      else if (condition.equals("M"))
        cond = ">";
      else if (condition.equals("n")) {
        cond = ">";
        TA200361008 = "0";
      }
      String queryType = request.getParameter("queryType");

      Hashtable paras = new Hashtable();
      Enumeration pnames = request.getParameterNames();
      while (pnames.hasMoreElements()) {
        String tname = (String)pnames.nextElement();
        paras.put(tname, request.getParameter(tname));
      }

      //�������ݲ�ѯģ��õ���������������SESSION
      ChooseEnpBalanceDAO dao = new ChooseEnpBalanceDAO(new icbc.cmis.operation.DummyOperation());
      //      String sql[] = dao.genSQL2(queryType,employee,TA200011001,TA200011003,TA200011005,TA200011010,TA200011011,TA200011012,TA200011014,TA200011016,TA200011031,paras);

      //      Vector vparas = dao.genPara(queryType,employee,TA200011001,TA200011003,TA200011005,TA200011010,TA200011011,TA200011012,TA200011014,TA200011016,TA200011031,paras);

      //      session.updateSessionData("sql3GB2U",sql);
      int rowCount = dao.getCount(queryType, employee, TA200361001, TA200361008, cond, paras);
      session.updateSessionData("recordCount3GB2U", String.valueOf(rowCount));
      session.updateSessionData("bufferBegin3GB2U", null);
      session.updateSessionData("bufferEnd3GB2U", null);
      session.updateSessionData("data3GB2U", null);

      //���ؽ�����ĵ�һҳ
      query(session, out, "1", request);
    }
    catch (TranFailException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new TranFailException("cmisUTIL801", "", ex.getMessage(), ex.getMessage());
    }

  }

  public void query(CMisSessionMgr session, PrintWriter out, String spage, HttpServletRequest request) throws TranFailException {
    //��SESSION��ȡ�ؽ����������ҳ�ŷ��ؽ��
    
	String langCode = (String)session.getSessionData("LangCode");
		
    try {
      //��SESSION��ȡ������

      Employee employee = (Employee)session.getSessionData("Employee");
      String TA200361001 = request.getParameter("TA200361001");
      if (TA200361001 == null)
        TA200361001 = "";
      String TA200361008 = request.getParameter("TA200361008");
      if (TA200361008 == null)
        TA200361008 = "";
      String condition = request.getParameter("condition");
      String cond = null;
      if (condition.equals("L"))
        cond = "<";
      else if (condition.equals("LE"))
        cond = "<=";
      else if (condition.equals("E"))
        cond = "=";
      else if (condition.equals("ME"))
        cond = ">=";
      else if (condition.equals("M"))
        cond = ">";
      else if (condition.equals("n")) {
        cond = ">";
        TA200361008 = "0";
      }
      String queryType = request.getParameter("queryType");

      Hashtable paras = new Hashtable();
      Enumeration pnames = request.getParameterNames();
      while (pnames.hasMoreElements()) {
        String tname = (String)pnames.nextElement();
        paras.put(tname, request.getParameter(tname));
      }

      //      String sql[] = (String[])session.getSessionData("sql3GB2U");
      int rowCount = Integer.parseInt((String)session.getSessionData("recordCount3GB2U"));
      int bufferBegin = 0;
      int bufferEnd = 0;
      Vector datas = null;

      try {
        bufferBegin = Integer.parseInt((String)session.getSessionData("bufferBegin3GB2U"));
      }
      catch (Exception ex) {}
      try {
        bufferEnd = Integer.parseInt((String)session.getSessionData("bufferEnd3GB2U"));
      }
      catch (Exception ex) {}

      String warning = "";

      //ȡ��¼����С
      int last = rowCount;
      int pages = (last - 1) / PAGE_LINES + 1;

      //����Ӧȡ�صļ�¼��ʼ����ֹ���
      int page = Integer.parseInt(spage);
      int begin = (page - 1) * PAGE_LINES + 1;
      int end = (page) * PAGE_LINES;
      if (end > last) {
        end = last;
      }

      //����Ƿ��ѻ���
      if (begin < bufferBegin || end > bufferEnd) {
        if (begin < bufferBegin) {
          bufferBegin = begin / this.BUFFER_LINES * this.BUFFER_LINES + 1;
          bufferEnd = bufferBegin + this.BUFFER_LINES - 1;
        }
        else {
          bufferEnd = ((end - 1) / this.BUFFER_LINES + 1) * this.BUFFER_LINES;
          bufferBegin = bufferEnd - this.BUFFER_LINES + 1;
        }

        if (bufferBegin > begin) {
          bufferBegin = begin;
        }
        if (bufferEnd < end) {
          bufferEnd = end;
        }
        if (bufferEnd > last) {
          bufferEnd = last;
        }
        if (bufferBegin < 1) {
          bufferBegin = 1;
        }
        //        newString += "ROWNUM <= "+ bufferEnd +" ) WHERE rnum >= "+ bufferBegin ;

        ChooseEnpBalanceDAO dao = new ChooseEnpBalanceDAO(new icbc.cmis.operation.DummyOperation());
        datas = dao.getData(queryType, employee, TA200361001, TA200361008, cond, paras, bufferBegin, bufferEnd);
        session.updateSessionData("bufferBegin3GB2U", String.valueOf(bufferBegin));
        session.updateSessionData("bufferEnd3GB2U", String.valueOf(bufferEnd));
        session.updateSessionData("data3GB2U", datas);

        //System.out.println("new buffer:" + bufferBegin + " -> " + bufferEnd);
      }
      else {
        try {
          datas = (Vector)session.getSessionData("data3GB2U");
        }
        catch (Exception ex) {
          throw new TranFailException("cmisUTIL803", "icbc.cmis.util.ChooseEnpBalance", ex.getMessage(), genMsg.getErrMsgByLang(langCode,"099993"));
        }
      }

      //���ؽ��
      out.println("<?xml version=\"1.0\" encoding=\"GBK\"?>");
      out.print("<datas page='" + page + "' pages='" + pages + "' lines='" + last + "' warning='" + warning + "'>");
      if (datas != null && !datas.isEmpty()) {
        for (int i = 0; i <= end - begin; i++) {
          String[] row = (String[])datas.get(begin - bufferBegin + i);
          out.print("<en o='" + row[5] + "' c='" + row[0] + "' n='" + row[1] + "' j='" + row[2] + "' b='" + row[3] + "' d='" + row[4] + "' />");
        }
      }
      out.print("</datas>");
    }
    catch (TranFailException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new TranFailException("cmisUTIL801", "icbc.cmis.util.ChooseEnpBalance", ex.getMessage(), genMsg.getErrMsgByLang(langCode,"099993"));
    }

  }

  public void clear(CMisSessionMgr session, PrintWriter out, String ccode, String cname) {
    //���SESSION�е��������
    try {
      //System.out.println("clear enterprise3GB2U!!!");
      //      session.removeSessionData("sql3GB2U");
      session.removeSessionData("recordCount3GB2U");
      session.removeSessionData("bufferBegin3GB2U");
      session.removeSessionData("bufferEnd3GB2U");
      session.removeSessionData("data3GB2U");
      session.addSessionData("CustomerCodeInSession", ccode);
      session.addSessionData("CustomerNameInSession", cname);
    }
    catch (Exception ex) {};
    out.println("<?xml version=\"1.0\" encoding=\"GBK\"?>");
    out.println("<ok />");
  }

  public void outTime(PrintWriter out, String info) {
    out.println("<?xml version=\"1.0\"?>");
    out.println("<error>");
    out.println(info);
    out.println("</error>");
  }
}