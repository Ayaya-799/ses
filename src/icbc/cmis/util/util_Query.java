package icbc.cmis.util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import icbc.cmis.base.*;
import icbc.missign.Employee;
import icbc.cmis.util.Decode;

public class util_Query extends HttpServlet {
  private static final String CONTENT_TYPE = "text/xml; charset=GBK";
//  private static final String CONTENT_TYPE = "text/xml";
  /**
   *Initialize global variables
   * @throws ServletException
   */
  public void init() throws ServletException {
  }
  /**
   * Process the HTTP Get request
   * @param request request
   * @param response response
   * @throws ServletException
   * @throws IOException
   */
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
	  
	  session.updateSessionData("currentPage_3dt2d",page);
      //�жϱ�־�����ݱ�־ת��ͬ����
      if(flag.equals("newQuery")) {
        newQuery(request,session,out);
      } else if(flag.equals("query")) {
        query(session,out,page,request);
      } else if(flag.equals("done")) {
        clear(session,out);
      }

    }
    catch (TranFailException ex) {
        outTime(out,/*"������ȡʧ�ܣ�" + */ex.getErrorCode()+"" + ex.getErrorLocation()+"" + ex.getDisplayMessage()+"" + ex.getErrorMsg()+"");
    }
    catch (Exception ex) {
        outTime(out,/*"������ȡʧ�ܣ�" + */ex.getMessage());
    }

  }

  public void newQuery(HttpServletRequest request,CMisSessionMgr session,PrintWriter out) throws TranFailException {
    //�²�ѯ��ȡ����ѯ�������������ݲ�ѯģ��õ���������������SESSION
    //���ؽ�����ĵ�һҳ
    try {
      //ȡ����
      Employee employee = (Employee)session.getSessionData("Employee");
      String queryType = request.getParameter("queryType");

      Hashtable paras = new Hashtable();
      Enumeration pnames = request.getParameterNames();
      while (pnames.hasMoreElements()) {
        String tname = (String)pnames.nextElement();
//        paras.put(tname,request.getParameter(tname));
        paras.put(tname,icbc.cmis.util.Decode.decode(request.getParameter(tname)));
      }
	 

		//�������ݲ�ѯģ��õ���������������SESSION
		util_QueryDAO dao = (util_QueryDAO)Class.forName(queryType).newInstance();
		dao.genParameters(employee,paras);
		session.updateSessionData("query_3dt2d",dao);

		//���ؽ�����ĵ�һҳ
		query(session,out,"1",request);




      
      
    } catch(TranFailException ex) {
      throw ex;
    } catch(Exception ex) {
      throw new TranFailException("cmisUTIL801","",ex.getMessage(),ex.getMessage());
    }

  }

  public void query(CMisSessionMgr session,PrintWriter out,String spage,HttpServletRequest request) throws TranFailException{

	util_QueryDAO dao = (util_QueryDAO)session.getSessionData("query_3dt2d");
	String daoName = dao.getClass().getName();
    //��SESSION��ȡ�ؽ����������ҳ�ŷ��ؽ��
	//��SESSION��ȡ������
    try {
		//�����Խ��ײ����������жϡ�
	  CmisConstance.isValidRuningStatus(daoName,request);
      
	    try{
			String dbuser = null;
      
			dbuser = request.getParameter("dbuser");
      
			if(dbuser==null || dbuser.equals(""))      
			  dbuser = "cmis3";
	      
	      int rowCount = dao.getRecordCount(dbuser);
	
	      String data = dao.queryPage(Integer.parseInt(spage),dbuser);
	      //���ؽ��
	      out.println("<?xml version=\"1.0\" encoding=\"GBK\"?>");
	      int fnumber = dao.getFieldNumber();
	      int[] fwidths = dao.getFieldsWidth();
	      int[] askFields = dao.getAskFields();
	      int[] fAlign = dao.getFieldsAlign();
	      String[] fNames = dao.getFieldNames();
	      out.println("<Result><description num='" + fnumber + "'>");
	      for(int i=0;i<fnumber;i++) {
	        out.println("<field" + i + " n='" + fNames[i] + "' w='" + fwidths[i] + "' a='" + askFields[i]+"' t='" + fAlign[i]+ "' />");
	      }
	      out.println("</description>");
	      out.print("<datas page='" + spage + "' pages='" + dao.getPages() + "' lines='" + dao.getRecordCount(dbuser) + "'>");
	      out.print(data);
	      out.print("</datas></Result>");
	    }catch(Exception e)
	    {
	    	throw e;
	    }
		finally {
			CmisConstance.resetRuningStatus(daoName,request);
		}	    
    } catch (TranFailException ex) {
      throw ex;
    } catch(Exception ex) {
      throw new icbc.cmis.base.MuiTranFailException ("099993","icbc.cmis.util.util_Query",(String)session.getSessionData("LangCode"));
/*200706_weihb throw new TranFailException(cmisUTIL801","icbc.cmis.util.util_Query",ex.getMessage(),"ȡ���ݳ���");*/
    }

  }

  public void clear(CMisSessionMgr session,PrintWriter out) {
    //���SESSION�е��������
    try {
      session.removeSessionData("datas_3dt2d");
      session.removeSessionData("query_3dt2d");
      session.removeSessionData("currentPage_3dt2d");
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