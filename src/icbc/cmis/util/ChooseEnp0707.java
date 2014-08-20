package icbc.cmis.util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import icbc.cmis.base.*;
import icbc.missign.Employee;
import icbc.cmis.util.Decode;

/**
 * �����ͻ��ϲ���ϸ��ѯģ�� 200707����
 * @author ZhangDM
 */
public class ChooseEnp0707 extends HttpServlet {
  private static final int PAGE_LINES = 15;
  private static final int BUFFER_LINES = 10 * PAGE_LINES;
  private static final String CONTENT_TYPE = "text/xml; charset=GBK";
 
  public void init() throws ServletException {
  }
 
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
  }
  
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

	  String nedCcode = request.getParameter("nedCcode");
	  if(nedCcode == null) nedCcode = "ture";

      //�жϱ�־�����ݱ�־ת��ͬ����
      if(flag.equals("newQuery")) {	
         newQuery(request,session,out);
      } else if(flag.equals("query")) {		
         query(request,session,out,page);
      } else if(flag.equals("done")) {
        String ccode = Decode.decode(request.getParameter("ccode"));
        String cname = Decode.decode(request.getParameter("cname"));
        clear(session,out,ccode,cname,nedCcode);
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
	
      //�������ݲ�ѯģ��õ���������������SESSION
      ChooseEnp0707DAO dao = new ChooseEnp0707DAO(new icbc.cmis.operation.DummyOperation());   
      String sql[] = dao.genSQL(employee,TA200011001);
	  session.updateSessionData("sql3GB2U",sql);
	  int rowCount = dao.getRecordCount(sql[0]);
	  	 
      session.updateSessionData("recordCount3GB2U",String.valueOf(rowCount));
      session.updateSessionData("bufferBegin3GB2U",null);
      session.updateSessionData("bufferEnd3GB2U",null);
      session.updateSessionData("data3GB2U",null);

      //���ؽ�����ĵ�һҳ
      query(request,session,out,"1");
    } catch(TranFailException ex) {
      throw ex;
    } catch(Exception ex) {
      throw new TranFailException("cmisUTIL801","",ex.getMessage(),ex.getMessage());
    }
  }

  public void query(HttpServletRequest request,CMisSessionMgr session,PrintWriter out,String spage) throws TranFailException{
    //��SESSION��ȡ�ؽ����������ҳ�ŷ��ؽ��
    try {
      //��SESSION��ȡ������
      String sql[] = (String[])session.getSessionData("sql3GB2U");
      int rowCount = Integer.parseInt((String)session.getSessionData("recordCount3GB2U"));
      int bufferBegin = 0;
      int bufferEnd = 0;
      Vector datas = null;

      try {
        bufferBegin = Integer.parseInt((String)session.getSessionData("bufferBegin3GB2U"));
      }catch (Exception ex) {
      }
      try {
        bufferEnd = Integer.parseInt((String)session.getSessionData("bufferEnd3GB2U"));
      }catch (Exception ex) {
      }

      String warning = "";

      //ȡ��¼����С
      int last = rowCount;
      int pages = (last - 1)/ PAGE_LINES + 1;

      //����Ӧȡ�صļ�¼��ʼ����ֹ���
      int page = Integer.parseInt(spage);
      int begin = (page - 1) * PAGE_LINES + 1;
      int end   = (page) * PAGE_LINES;
      if (end > last) {
        end = last;
      }

      //����Ƿ��ѻ���
      if(begin < bufferBegin || end > bufferEnd) {
        if(begin < bufferBegin) {
          bufferBegin = begin / BUFFER_LINES * BUFFER_LINES + 1;
          bufferEnd = bufferBegin + BUFFER_LINES - 1;
        }else{
          bufferEnd = ((end - 1)/ BUFFER_LINES + 1) * BUFFER_LINES;
          bufferBegin = bufferEnd - BUFFER_LINES + 1;
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
        
		ChooseEnp0707DAO dao = new ChooseEnp0707DAO(new icbc.cmis.operation.DummyOperation());	
		String newString = new String(sql[1]);	
		newString += " ROWNUM <= "+ bufferEnd +" ) WHERE rnum >= "+ bufferBegin ; 			   
		datas = dao.query(newString,bufferBegin,bufferEnd);
		
        session.updateSessionData("bufferBegin3GB2U",String.valueOf(bufferBegin));
        session.updateSessionData("bufferEnd3GB2U",String.valueOf(bufferEnd));
        session.updateSessionData("data3GB2U",datas);
       
      }else{
        try {
          datas = (Vector)session.getSessionData("data3GB2U");
        }
        catch (Exception ex) {
          throw new TranFailException("cmisUTIL803","icbc.cmis.util.ChooseEnp0707",ex.getMessage(),"ȡ�������ݳ���");
        }
      }
	 
      //���ؽ��
      out.println("<?xml version=\"1.0\" encoding=\"GBK\"?>");
      out.print("<datas page='" + page + "' pages='" + pages + "' lines='" + last + "' warning='" + warning + "'>");    
	 
      if(datas != null && !datas.isEmpty()) {		
        // for(int i = 0; i <= end - begin; i++) {        
        //  String[] row = (String[])datas.get(begin - bufferBegin + i);
		String[] row = (String[])datas.get(0);
        out.print("<en o='" + row[4] + "' c='" + row[0] + "' n='" + row[1] + "' j='" + row[2] + "' d='" + row[3] + "' />");
        // }
      }         
      out.print("</datas>");
    } catch (TranFailException ex) {
      throw ex;
    } catch(Exception ex) {
      throw new TranFailException("cmisUTIL801","icbc.cmis.util.ChooseEnp0707",ex.getMessage(),"ȡ���ݳ���");
    }
  }

  public void clear(CMisSessionMgr session,PrintWriter out,String ccode,String cname,String nedCcode) {
    //���SESSION�е��������
    try {     
      session.removeSessionData("sql3GB2U");
      session.removeSessionData("recordCount3GB2U");
      session.removeSessionData("bufferBegin3GB2U");
      session.removeSessionData("bufferEnd3GB2U");
      session.removeSessionData("data3GB2U");      
      if(ccode.equals("")){
      }
      else{
      	if(nedCcode.equals("ture")){
	      	session.addSessionData("CustomerCodeInSession",ccode);
	        session.addSessionData("CustomerNameInSession",cname);
      	}
      }
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