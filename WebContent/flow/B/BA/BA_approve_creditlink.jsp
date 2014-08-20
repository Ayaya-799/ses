<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page import="icbc.cmis.base.*"%>
<%@ page session="false"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="cmisTags" prefix="cmis" %>
<%@ include file="../../../util/header.jsp"%>

<cmis:muidef def="icbc.cmis.flow.BA.flow_BA">
<%
KeyedDataCollection context = (KeyedDataCollection)request.getAttribute("operationData");
String approve_entcode = (String)context.getValueAt("approve_entcode");
String approve_entname = (String)context.getValueAt("approve_entname");
String approve_tradecode = (String)context.getValueAt("approve_tradecode");
String approve_tradetype = (String)context.getValueAt("approve_tradetype");
String approve_tradetypename = (String)context.getValueAt("approve_tradetypename");
String approve_flowtype = (String)context.getValueAt("approve_flowtype");
String approve_flowtypename = (String)context.getValueAt("approve_flowtypename");
String approve_ordernum = (String)context.getValueAt("approve_ordernum");
String approve_ordercode = (String)context.getValueAt("approve_ordercode");
String approve_busitype = (String)context.getValueAt("approve_busitype");
String approve_spflag = (String)context.getValueAt("approve_spflag");
String approve_returnurl = (String)context.getValueAt("approve_returnurl");
String approve_ordername = (String)context.getValueAt("approve_ordername");
String approve_isfirst = (String)context.getValueAt("approve_isfirst");

ArrayList alist = (ArrayList)context.getValueAt("linklist");

ArrayList exttablist = (ArrayList)context.getValueAt("exttablist");
String infotabname = (String)context.getValueAt("infotabname");

String tabarray = "";
for(int i=0; i<exttablist.size(); i++){
	HashMap hmap = (HashMap)exttablist.get(i);
	tabarray = tabarray +  "'" + (String)hmap.get("taburl") + "',";
}
if(!tabarray.equals("")){
	tabarray = tabarray.substring(0,tabarray.length()-1);
}
%>

<html>
<head>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/data_check.js"></script>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/liball.js"></script>
<script Language="JavaScript">
var taburlarray = new Array (<%=tabarray%>);

function showinfo(){
	form1.opAction.value = "showinfo";
	form1.submit();
}
function showcreditlike(){
	form1.opAction.value = "showcreditlike";
	form1.submit();
}
function showdeal(){
	form1.opAction.value = "showdeal";
	form1.submit();
}
function showextratab(str1,str2){
	form1.extrataborder.value = str1;
	form1.extrataburl.value = taburlarray[str2];
	form1.opAction.value = "showextra";
	form1.submit();
}
</script>




<title>̨����Ϣ</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="stylesheet" href="<%=baseWebPath%>/<cmis:muipub item="CSSPATH"/>/main.css" type="text/css">
</head>

<body>
<form name="form1" method="post" action="<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet">
<input type="hidden" name="operationName" value="icbc.cmis.flow.BA.BA_approvedoOp">
<input type="hidden" name="opAction" value="">
<input type="hidden" name="opDataUnclear" value="true">

<input type="hidden" name="extrataborder" value="">
<input type="hidden" name="extrataburl" value="">

<input type="hidden" name="approve_entcode" value="<%=approve_entcode%>">
<input type="hidden" name="approve_entname" value="<%=approve_entname%>">
<input type="hidden" name="approve_tradecode" value="<%=approve_tradecode%>">
<input type="hidden" name="approve_tradetype" value="<%=approve_tradetype%>">
<input type="hidden" name="approve_tradetypename" value="<%=approve_tradetypename%>">
<input type="hidden" name="approve_flowtype" value="<%=approve_flowtype%>">
<input type="hidden" name="approve_flowtypename" value="<%=approve_flowtypename%>">
<input type="hidden" name="approve_ordernum" value="<%=approve_ordernum%>">
<input type="hidden" name="approve_ordercode" value="<%=approve_ordercode%>">
<input type="hidden" name="approve_busitype" value="<%=approve_busitype%>">
<input type="hidden" name="approve_spflag" value="<%=approve_spflag%>">
<input type="hidden" name="approve_returnurl" value="<%=approve_returnurl%>">
<input type="hidden" name="approve_ordername" value="<%=approve_ordername%>">
<input type="hidden" name="approve_isfirst" value="<%=approve_isfirst%>">


<table width="750" border="1" cellspacing="0" cellpadding="1" align="center">
	<tr>
		<td class="td1"><cmis:muipub item="P000004"/><!--�ͻ�����--></td><td><%=approve_entcode%></td><td class="td1"><cmis:muipub item="P000006"/><!--�ͻ�����--></td><td colspan=4><%=approve_entname%></td>
	</tr>
	<tr>
		<td class="td1"><cmis:muipub item="P000027"/><!--�����--></td><td><%=approve_tradecode%></td><td class="td1"><cmis:muipub item="P000170"/><!--��������--></td><td><%=approve_tradetypename%></td><td class="td1"><cmis:muiitem item="C000005"/><!--��������--></td><td><%=approve_flowtypename%></td>
	</tr>
</table>

<cmis:framework align="center">
<cmis:tabpage width="850">
<cmis:tabpagebar title="<%=infotabname%>" url="javascript:showinfo();" onclick="" selected="false" />
<%for(int i=0; i<exttablist.size(); i++){
	HashMap hmap = (HashMap)exttablist.get(i);
	String tabname = (String)hmap.get("tabname");
	String taborder = (String)hmap.get("taborder");
	String javasc = "javascript:showextratab('" + taborder + "','" + i + "');";%>
<cmis:tabpagebar title="<%=tabname%>" url="<%=javasc%>" onclick="" selected="false" />
<%}%>
<cmis:tabpagebar titleid="C000020" url="javascript:showcreditlike();" onclick="" selected="true" /><!-- ̨����Ϣ -->
<cmis:tabpagebar titleid="C000021" url="javascript:showdeal();" onclick="" selected="false" /><!-- �������� -->
<cmis:tabpagecontent info="" align="center" valign="top">
<%if(alist.size()==0){%>
	<cmis:muiitem item="C000025"/><!-- ��ǰҵ���ṩ̨�ʲ�ѯ -->
<%}%>

<%for(int i=0; i<alist.size(); i++) {
	HashMap hmap = (HashMap)alist.get(i);
	String url_name = (String)hmap.get("url_name");
	String url_link = (String)hmap.get("url_link");
%>
<a href="<%=url_link%>"><%=url_name%></a><%if((i+1)%3==0){%><br><%}%>
<%}%>

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td class=td1 align="right"  valign="bottom" height=35>
			<a href="<%=approve_returnurl%>"><IMG border="0" src="<%=baseWebPath%>/<cmis:muipub item="IMAGEPATH"/>/return.gif"  class="hand" height="24"></a>
		</td>
	</tr>
</table>
</cmis:tabpagecontent>
</cmis:tabpage>
</cmis:framework>
</form>

<form name="form2" method="post" action="<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet">
<input type="hidden" name="operationName" value="">
<input type="hidden" name="opAction" value="">
<input type="hidden" name="opDataUnclear" value="true">

</form>

</body>
</html>
</cmis:muidef>

