<!--1.11	�������ɻ������ -->
<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page import="icbc.cmis.base.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Vector" %>

<!--����importҳ����Ҫ���õ��࣬��Ҫ��ҳ���ϵ�op���dao�࣬Ҫ���Լ������װ-->
<%@ page import="icbc.cmis.flow.util.*"%>
<%@ include file="../../util/header.jsp" %>

<%@ taglib uri="cmisTags" prefix="cmis" %>
<cmis:muidef def="icbc.cmis.flow.BA.flow_BA">

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

String iamsg2_entcode = request.getParameter("entcode");
String iamsg2_tradecode = request.getParameter("tradecode");
String iamsg2_ordernum = request.getParameter("ordernum");
String iamsg2_flowtype = request.getParameter("flowtype");
String iamsg2_tradetype = request.getParameter("tradetype");
String iamsg2_ordercode = request.getParameter("ordercode");
String iamsg2_employeecode = request.getParameter("employeecode");
String iamsg2_empareacode = request.getParameter("empareacode");
%>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/data_check.js"></script>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/liball.js"></script>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/tools.js"></script>
<script language="JavaScript">

function secondiamsg() {
	var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
	var parser=new ActiveXObject("microsoft.xmldom");
	parser.async="false";
	var url = "<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.util_content_iamsg2&opDataUnclear=true&opAction=20001&entcode=<%=iamsg2_entcode%>&tradecode=<%=iamsg2_tradecode%>&tradetype=<%=iamsg2_tradetype%>&ordercode=<%=iamsg2_ordercode%>&employeecode=<%=iamsg2_employeecode%>&empareacode=<%=iamsg2_empareacode%>&ordernum=<%=iamsg2_ordernum%>&flowtype=<%=iamsg2_flowtype%>";
	url+= "&newtime=" + new Date();
	objHTTP.Open('GET',encodeURL(url),false);
	objHTTP.Send();
	
	if(!parser.loadXML(objHTTP.responseText)){
		//alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
	    return;
	}
	error = parser.getElementsByTagName("error");
	if(error.length > 0) {
		alert(error.item(0).text);
		return;
	}	

	info = parser.getElementsByTagName("info");
	if(info.length > 0){
		var nodes = parser.documentElement.childNodes;
		var ts=new Array(6);
		if(nodes.length > 0){
			for(i = 0; i < nodes.length ; i ++ ) {
				var node = nodes.item(i);
				ts[0]=node.getAttribute("iamsg2process017");//�õ��������
				ts[1]=node.getAttribute("o_stoparea");//ͣ��������
				ts[2]=node.getAttribute("o_ret1");//�õ����Ի������
				ts[3]=node.getAttribute("o_ret2");//�õ����Ի������
				document.all.iamsg2process017.value=ts[0];
				document.all.o_stoparea.value=ts[1];
				document.all.iamsg2gxadvice.value =ts[2];
				document.all.iamsg2rxadvice.value =ts[3];
							
			}
		}
	}
	
}

</script>


<input type="hidden" name="iamsg2process017" value="">
<input type="hidden" name="o_stoparea" value="">

<table width="100%" border="1" cellspacing="" cellpadding="1">
<tr><th colspan=2><cmis:muiitem item="C000062"/><!-- ϵͳ��ʾ��Ϣ --><a href="javascript:secondiamsg();"><cmis:muiitem item="C000063"/><!-- ���� --></a></th></tr>
<tr>
<td class="td1" align="center" width="50%"><cmis:muiitem item="C000064"/><!-- ���Կ�����Ϣ�� --></td>
<td class="td1" align="center" width="50%"><cmis:muiitem item="C000065"/><!-- ��ʾ����Ϣ�� --></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="" cellpadding="0">
<tr>
<td><TEXTAREA NAME="iamsg2gxadvice" ROWS="5" COLS="65"  readonly ><cmis:muiitem item="C000066"/></TEXTAREA></td><!-- �������� -->
<td><TEXTAREA NAME="iamsg2rxadvice" ROWS="5" COLS="65"  readonly ><cmis:muiitem item="C000066"/></TEXTAREA></td><!-- �������� -->
</tr>
</table>

</cmis:muidef>