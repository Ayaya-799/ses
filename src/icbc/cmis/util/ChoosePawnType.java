/*
 * �������� 2005-9-5
 * ����ѺƷ����Ĳ�ѯ
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package icbc.cmis.util;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import icbc.cmis.base.*;

public class ChoosePawnType extends HttpServlet {
	private static final String CONTENT_TYPE = "text/xml; charset=GBK";
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
			String pawn_code = request.getParameter("pawn_code");
			ChoosePawnTypeDAO dao = new ChoosePawnTypeDAO(new icbc.cmis.operation.DummyOperation());
			String xml = dao.getPawnType(pawn_code);

			out.println("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		
			out.print(xml);
		}
		catch (TranFailException ex) {
				outTime(out,"������ȡʧ�ܣ�" + ex.getErrorCode()+"" + ex.getErrorLocation()+"" + ex.getDisplayMessage()+"" + ex.getErrorMsg()+"");
		}
		catch (Exception ex) {
				outTime(out,"������ȡʧ�ܣ�" + ex.getMessage());
		}

	}

	public void outTime(PrintWriter out,String info) {
		out.println("<?xml version=\"1.0\"?>");
		out.println("<error>");
		out.println(info);
		out.println("</error>");
	}
}