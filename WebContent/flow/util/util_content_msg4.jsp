<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page import="icbc.cmis.base.*"%>
<%@ page import="java.util.*"%>
<%@ page session="false"%>
<%@ page import="java.util.Enumeration" %>
<%@ taglib uri="cmisTags" prefix="cmis" %>
<!--����importҳ����Ҫ���õ��࣬��Ҫ��ҳ���ϵ�op���dao�࣬Ҫ���Լ������װ-->
<%@ page import="icbc.cmis.flow.util.*"%>
<%@ include file="../../util/header.jsp" %>

<%
//�̶�����Ĳ���������get
//entcode,		���ͻ���
//tradecode		��ҵ�������
//tradetype		����������
//flowtype		����������
//ordernum		����ǰ�������
//ordercode		����ǰ���ڴ���
//employeecode	����ǰ��Ա
//empareacode	����ǰ����
//busitype		��ҵ�����ʣ�0����Ӫ��1��ί��

//~~~~ʹ���Լ���ǰ׺��������Ƭ�����ظ�

String msg4_entcode = request.getParameter("entcode");
String msg4_tradecode = request.getParameter("tradecode");
String msg4_ordernum = request.getParameter("ordernum");
String msg4_ordercode = request.getParameter("ordercode");
String msg4_tradetype = request.getParameter("tradetype");
String msg4_flowtype = request.getParameter("flowtype");
String msg4_employeecode = request.getParameter("employeecode");
String msg4_empareacode = request.getParameter("empareacode");

%>

<script src="<%=baseWebPath%>/flow/jslib/checkappstat.js"></script>
<script language="JavaScript">

var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
var parser=new ActiveXObject("microsoft.xmldom");
parser.async="false";


function showadvice(){
	var url = "<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.util_content_msg4&opDataUnclear=true&opAction=10001"
			+"&msg4_entcode=<%=msg4_entcode%>"
			+"&msg4_tradecode=<%=msg4_tradecode%>"
			+"&msg4_ordernum=<%=msg4_ordernum%>"
			+"&msg4_tradetype=<%=msg4_tradetype%>"
			+"&msg4_flowtype=<%=msg4_flowtype%>"
			+"&msg4_ordercode=<%=msg4_ordercode%>"
			+"&msg4_employeecode=<%=msg4_employeecode%>"
			+"&msg4_empareacode=<%=msg4_empareacode%>"
			+"&time="+(new Date);
	var ts = window.showModalDialog(url,window,"dialogWidth:610px;dialogHeight:450px;center:yes;help:no;status:no;resizable:no");
	
	if(ts!=null){
		var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
		var parser=new ActiveXObject("microsoft.xmldom");
		parser.async="false";
		
		var url = "<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet";
		objHTTP.Open('POST',encodeURL(url),false);
		objHTTP.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		var parameter = "operationName=icbc.cmis.flow.util.util_content_msg4&opDataUnclear=true&opAction=20001"
					  +"&msg4_entcode=<%=msg4_entcode%>"
					  +"&msg4_tradecode=<%=msg4_tradecode%>"
					  +"&msg4_ordernum=<%=msg4_ordernum%>"
					  +"&ta340041013="+ts
					  +"&msg4_tradetype=<%=msg4_tradetype%>"
					  +"&msg4_flowtype=<%=msg4_flowtype%>"
					  +"&msg4_employeecode=<%=msg4_employeecode%>"
					  +"&msg4_empareacode=<%=msg4_empareacode%>"
					  +"&time=" + (new Date);
		objHTTP.Send(encodeURL(parameter));
		
		if(!parser.loadXML(objHTTP.responseText)){
			alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
			return;
		}
		error = parser.getElementsByTagName("error");
		if(error.length > 0) {
			alert(error.item(0).text);
			return;
		}
		alert("�������˵���ɹ�");	
	}
}
</script>




<table width="100%" border="1" cellspacing="" cellpadding="1">
<tr>
	<th colspan=2>�������</th>
</tr>
<tr>
	<td class="td1" width = "350">���˵��(���������ť,�ڶԻ���������)</td><td><a href="javascript:showadvice();"> ����</a></td>
</tr>
</table>