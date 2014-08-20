<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page session="false"%>
<%@ page import="java.util.*" %>
<%@ page import="icbc.cmis.base.*" %>
<%@ taglib uri="cmisTags" prefix="cmis" %>
<%@ include file="../../util/header.jsp" %>

<cmis:sessionCheck>

<%
KeyedDataCollection context = (KeyedDataCollection)request.getAttribute("operationData");
ArrayList grantlist1 = (ArrayList)context.getValueAt("grantlist1");
String grantreal1 = (String)context.getValueAt("grantreal1");
%>

<html>
<head>
<script src="<%=baseWebPath%>/jslib/data_check.js"></script>
<script src="<%=baseWebPath%>/jslib/liball.js"></script>
<script Language="JavaScript">
var ts1 = "";
var money1 = "<%=grantreal1%>";

function f_choose(){
	var ts = new Array(ts1,money1);
	window.returnValue = ts;
	window.close();
	return;
}


function f_detail(str1,str2,str3,str4,str5){
	alert("����Ȩ��;��"+str1+"\n����Ȩǩ��������"+
        str2+"\n��Ȩ������"+str3+"\n�������ڣ�"+
        str4+"\n��ֹ���ڣ�"+str5+"\n");
}

function f_cancel() {
  window.returnValue = null;
  window.close();
}

function f_choose1(str){
	ts1 = str;
}


</script>

<title>ʹ���ر���Ȩ</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="stylesheet" href="<%=baseWebPath%>/css/main.css" type="text/css">
</head>

<body>
<form name="form1" method="post" action="<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet">
<input type="hidden" name="operationName" value="icbc.cmis.flow.util.util_content_appextraOp">
<input type="hidden" name="opAction" value="">
<input type="hidden" name="opDataUnclear" value="true">


<cmis:framework align="center">
<cmis:tabpage width="550">
<cmis:tabpagebar title="��֤���ر���Ȩ�б�" url="" onclick="" selected="true" />
<cmis:tabpagecontent info="" align="center" valign="top">

<table width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
<tr>

<td align="center" class="td1">&nbsp;
</td>
<td align="center" class="td1">���
</td>
<td align="center" class="td1">�ر���Ȩ��
</td>
<td align="center" class="td1">��Ȩ���
</td>
<td align="center" class="td1">��Ȩ����
</td>
<td align="center" class="td1">��Ȩ����
</td>
<td align="center" class="td1">&nbsp;
</td>
</tr>
<%for(int i=0; i<grantlist1.size(); i++) {
	HashMap hmap = (HashMap)grantlist1.get(i); 
	String grant014 = (String)hmap.get("grant014");
	String grant010 = (String)hmap.get("grant010");
	String grant009 = (String)hmap.get("grant009");
	String grant005 = (String)hmap.get("grant005");
	String grant006 = (String)hmap.get("grant006");
	%>
    <tr>
    <td><input type="radio" name="grant_seq" onclick = "f_choose1('<%=(String)hmap.get("grant900")%>');" ></td>
    <td><%=(String)hmap.get("grant900")%></td>
    <td><%=(String)hmap.get("grant015")%></td>
    <td><%=(((String)hmap.get("grant013")).equals("0"))?"�ͻ�":"����"%></td>
    <td><%=(((String)hmap.get("grant012")).equals("01"))?"�����":"�����������Ԫ"%></td>
    <td><%=(String)hmap.get("grant004")%></td>
    <td align="center"><a href="#" onclick="f_detail('<%=grant014%>','<%=grant010%>','<%=grant009%>','<%=grant005%>','<%=grant006%>')">��ϸ</a></td></tr>
<%}%>



</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
<tr>
<td class=td1 align="right"  valign="bottom" height=35>
 <% if (grantlist1.size()!=0){%><img src="<%=baseWebPath%>/images/confirm.gif" width=48 height=24 border=0 style="cursor:hand" onclick="javascript:f_choose()"><%}%><img src="<%=baseWebPath%>/images/return.gif" width=48 height=24 border=0 style="cursor:hand" onclick="javascript:f_cancel()">
</td>
</tr>
</table>

</cmis:tabpagecontent>
</cmis:tabpage>
</cmis:framework>
</form>

<jsp:include page="/util/footer.jsp" flush="true" />
</body>
</html>
</cmis:sessionCheck>


