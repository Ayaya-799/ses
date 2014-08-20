package icbc.cmis.util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import icbc.cmis.base.*;
import icbc.missign.Employee;
import icbc.cmis.util.Decode;

public class ParaAdjustView extends HttpServlet {
  private static final int PAGE_LINES = 15;
  private static final int BUFFER_LINES = 10 * PAGE_LINES;
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
        newQuery(request,session,out,page);
      } else if(flag.equals("query")) {
        query(session,out,page,request);
      } else if(flag.equals("done")) {
        //String ccode = Decode.decode(request.getParameter("ccode"));
        //String cname = Decode.decode(request.getParameter("cname"));
        clear(session,out);
      }

    }
    catch (TranFailException ex) {
        outTime(out,"������ȡʧ�ܣ�" + ex.getErrorCode()+"" + ex.getErrorLocation()+"" + ex.getDisplayMessage()+"" + ex.getErrorMsg()+"");
    }
    catch (Exception ex) {
        outTime(out,"������ȡʧ�ܣ�" + ex.getMessage());
    }

  }

  public void newQuery(HttpServletRequest request,CMisSessionMgr session,PrintWriter out, String page) throws TranFailException {
    //�²�ѯ��ȡ����ѯ�������������ݲ�ѯģ��õ���������������SESSION
    //���ؽ�����ĵ�һҳ
    try {
      //ȡ����
      String tableCode = (String)session.getSessionData("paraTableCode");
//      String tableName = (String)session.getSessionData("paraTableName");
//      Vector colomnHead = (Vector)session.getSessionData("colomnHead");
//      Vector colomnHeadDisplay = (Vector)session.getSessionData("colomnHeadDisplay");
//      Vector colomnType = (Vector)session.getSessionData("colomnType");
      
      //�������ݲ�ѯģ��õ���������������SESSION
      ParaAdjustViewDAO dao = new ParaAdjustViewDAO(new icbc.cmis.operation.DummyOperation());
//      String sql[] = dao.genSQL2(queryType,employee,TA200011001,TA200011003,TA200011005,TA200011010,TA200011011,TA200011012,TA200011014,TA200011016,TA200011031,paras);
      
//      Vector vparas = dao.genPara(queryType,employee,TA200011001,TA200011003,TA200011005,TA200011010,TA200011011,TA200011012,TA200011014,TA200011016,TA200011031,paras);
      
      
//      session.updateSessionData("sql3GB2U",sql);
      int rowCount = dao.getCount(tableCode);
      
//      session.updateSessionData("paraTableCode",tableCode);
//      session.updateSessionData("paraTableName",tableName);
//      session.updateSessionData("colomnHead",colomnHead);
//      session.updateSessionData("colomnHeadDisplay",colomnHeadDisplay);
//      session.updateSessionData("colomnType",colomnType);
      
      session.updateSessionData("recordCount3GB2U",String.valueOf(rowCount));
      session.updateSessionData("bufferBegin3GB2U",null);
      session.updateSessionData("bufferEnd3GB2U",null);
      session.updateSessionData("data3GB2U",null);

      //���ؽ�����ĵ�һҳ
      query(session,out,page,request);
    } catch(TranFailException ex) {
      throw ex;
    } catch(Exception ex) {
      throw new TranFailException("ParaAdjustView","ParaAdjustView",ex.getMessage(),ex.getMessage());
    }

  }

  public void query(CMisSessionMgr session,PrintWriter out,String spage,HttpServletRequest request) throws TranFailException{
    
    String tableCode = null;
    Vector colomnInfo = null;
    //��SESSION��ȡ�ؽ����������ҳ�ŷ��ؽ��
    try {
      //��SESSION��ȡ������
 
      int rowCount = Integer.parseInt((String)session.getSessionData("recordCount3GB2U"));
      int bufferBegin = 0;
      int bufferEnd = 0;
      Vector datas = null;

      tableCode = (String)session.getSessionData("paraTableCode");
      //colomnHead = (Vector)session.getSessionData("colomnHead");
      colomnInfo = (Vector)session.getSessionData("colomnInfo");
      
      try {
        bufferBegin = Integer.parseInt((String)session.getSessionData("bufferBegin3GB2U"));
      }
      catch (Exception ex) {
      }
      try {
        bufferEnd = Integer.parseInt((String)session.getSessionData("bufferEnd3GB2U"));
      }
      catch (Exception ex) {
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
          bufferBegin = begin / ParaAdjustView.BUFFER_LINES * ParaAdjustView.BUFFER_LINES + 1;
          bufferEnd = bufferBegin + ParaAdjustView.BUFFER_LINES - 1;
        }else{
          bufferEnd = ((end - 1)/ ParaAdjustView.BUFFER_LINES + 1) * ParaAdjustView.BUFFER_LINES;
          bufferBegin = bufferEnd - ParaAdjustView.BUFFER_LINES + 1;
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

        
        
        ParaAdjustViewDAO dao = new ParaAdjustViewDAO(new icbc.cmis.operation.DummyOperation());
        datas = dao.getData(tableCode,colomnInfo,bufferBegin,bufferEnd);
        session.updateSessionData("bufferBegin3GB2U",String.valueOf(bufferBegin));
        session.updateSessionData("bufferEnd3GB2U",String.valueOf(bufferEnd));
        session.updateSessionData("data3GB2U",datas);
        

        //System.out.println("new buffer:" + bufferBegin + " -> " + bufferEnd);
      }else{
        try {
          datas = (Vector)session.getSessionData("data3GB2U");
        }
        catch (Exception ex) {
          throw new TranFailException("query","icbc.cmis.util.ParaAdjustView",ex.getMessage(),"ȡ�������ݳ���");
        }
      }


      //���ؽ��
      StringBuffer xmlBuff = new StringBuffer();
      Hashtable tableContainer=CmisConstance.getDictParam();
      
      xmlBuff.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
      
      xmlBuff.append("<datas page='" + page + "' pages='" + pages + "' lines='" + last + "' warning='" + warning + "'>");
      if(datas != null && !datas.isEmpty()) {
        for(int i = 0; i <= end - begin; i++) {
          String[] row = (String[])datas.get(begin - bufferBegin + i);
          xmlBuff.append("<en o='" + row[0] + "' ");
          for(int j = 0; j<colomnInfo.size();j++){
            Hashtable htable = (Hashtable)colomnInfo.get(j);
            xmlBuff.append((String)htable.get("col") + "='" + row[j+1] + "' ");
            if(((String)htable.get("type")).equals("sel")){
              if(((String)htable.get("seltype")).equals("01")){
                DictBean dbean = (DictBean)tableContainer.get(((String)htable.get("dict")).toLowerCase());
                String value = dbean.getValue(row[j+1]);
				if(value==null)
				  value = "";
                xmlBuff.append((String)htable.get("col") + "_disp_name='" + value + "' ");
              }
              else if(((String)htable.get("seltype")).equals("99")){
                Hashtable ht = (Hashtable)htable.get("value");
                String value = (String)ht.get(row[j+1]);
                if(value==null)
                  value = "";
                xmlBuff.append((String)htable.get("col") + "_disp_name='" + value + "' ");
                
              }
            }
          }
          xmlBuff.append("/>");
        }
      }
      xmlBuff.append("</datas>");
      
      
      out.print(xmlBuff.toString());
    } catch (TranFailException ex) {
      throw ex;
    } catch(Exception ex) {
      throw new TranFailException("query","icbc.cmis.util.ParaAdjustView",ex.getMessage(),"ȡ���ݳ���");
    }

  }

  public void clear(CMisSessionMgr session,PrintWriter out) {
    //���SESSION�е��������
    try {
      //System.out.println("clear enterprise3GB2U!!!");
//      session.removeSessionData("sql3GB2U");
      session.removeSessionData("recordCount3GB2U");
      session.removeSessionData("bufferBegin3GB2U");
      session.removeSessionData("bufferEnd3GB2U");
      session.removeSessionData("data3GB2U");

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