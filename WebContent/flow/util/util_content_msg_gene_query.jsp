<!--通用意见查询-->
<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page session="false"%>
<%@ page import="java.util.*"%>
<%@ page import="icbc.cmis.base.*" %>
<%@ taglib uri="cmisTags" prefix="cmis" %>
<%@ include file="../../util/header.jsp" %>


<cmis:muidef def="icbc.cmis.flow.FLOW_UTILE">
<html>

<%
KeyedDataCollection context = (KeyedDataCollection)request.getAttribute("operationData");
String out_msg = (String)context.getValueAt("out_msg");
String out_flag = (String)context.getValueAt("out_flag");
if(out_msg==null){
	out_msg = "";
}

%>

<html>
<head>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/data_check.js"></script>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/tools.js"></script>
<script src="<%=baseWebPath%>/flow/<cmis:muipub item="JSPATH"/>/checkSyntax.js"></script>
<script Language="JavaScript">

function docancel(){
	window.returnValue = null;
	window.close();
}
</script>


<title><cmis:muiitem item="C000054" exp="意见查询"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="stylesheet" href="<%=baseWebPath%>/<cmis:muipub item="CSSPATH"/>/main.css" type="text/css">
</head>

<body>
<form name="form1" method="post" action="<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet">

<cmis:framework align="center">
<cmis:tabpage width="450">
<cmis:tabpagebar titleid="C000054" url="" onclick="" selected="true" /><!--意见查询-->
<cmis:tabpagecontent info="" align="center" valign="top">
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
<tr>
<td>
<%if(out_flag.equals("0")){%>
<TEXTAREA rows="10" cols="80" name="inputmsg" readonly><%=out_msg%></TEXTAREA>
<%}else{%>
<%=out_msg%>,<cmis:muiitem item="C000056" exp="请返回！"/>
<%}%>
</td>
</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td class=td1 align="right"  valign="bottom" height=35>
		<IMG border="0" src="<%=baseWebPath%>/<cmis:muipub item="IMAGEPATH"/>/return.gif" class="hand" onclick="javascript:docancel();">
		</td>
	</tr>
</table>

</cmis:tabpagecontent>
</cmis:tabpage>
</cmis:framework>
</form>

</body>
</html>
	</cmis:muidef>


