<%@ page import="com.icbc.cte.base.*" %>
<%@ taglib uri="CTPTLD" prefix="ctp" %>
<%
String sysid = "0";
String path=CTEConstance.getAppFullWebPathBeforeLogon(sysid);
boolean isTimeout = false;
%>
<jsp:useBean id="utb" scope="page" class="com.icbc.cte.cs.html.CTEJspContextServices">
<%
   try{
		utb.initialize(request);
   }catch(Exception ex){
   System.out.println("*****utb init error");
   isTimeout = true;
		//response.sendRedirect("../sesserror.jsp");
		//return;
   }
%>
</jsp:useBean>

<%
	String sessionId = null;
	///////////////////////////////////////////////////////////////////
	/*
	 * �޸�ԭ������Context��Ķ������icbc.cmis.base.Context��
	 *			 com.icbc.cte.base.Context���ֶ��壬Ϊ�˷�ֹ��ҳ���в���
	 			 �����ԣ����ڴ˽���������������
	 * �޸����ڣ�2004-07-08
	 * �޸��ˣ������
	 */
	//Context sessionCtx = null;
	//Context context = null;
	com.icbc.cte.base.Context sessionCtx = null;
	com.icbc.cte.base.Context context=null;
	///////////////////////////////////////////////////////////////////
	String basePath = null;
	String exPathInfo = null;
	String fullPath = null;
	String internation = null;
if(!isTimeout){
	sessionId = utb.getSessionId();
	sessionCtx = utb.getSessionContext();
 	if(sessionId == null || sessionCtx == null){
		isTimeout = true;
	}
}
if(!isTimeout){
	context = utb.getContext();
	basePath = utb.getBaseWebPath();

	exPathInfo = utb.getExtraPathInfo();
	fullPath = utb.getAppFullWebPath(sessionId);
	internation = (String)sessionCtx.getValueAt("Language");
}
%>
<%
if(isTimeout){
%>
 <form NAME="timeoutfmt" METHOD="post" action="<%=path%>sesserror.jsp"  target="_top">
 </form>
<%
out.println("<script>");
out.println("timeoutfmt.submit();");
out.println("</script>");
return;
}
%>
