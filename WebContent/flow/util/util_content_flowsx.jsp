<!--�������̼�¼-->
<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page import="icbc.cmis.base.*"%>
<%@ page import="java.util.*"%>
<%@ page session="false"%>
<%@ page import="java.util.Enumeration" %>
<%@ taglib uri="cmisTags" prefix="cmis" %>

<!--����importҳ����Ҫ���õ��࣬��Ҫ��ҳ���ϵ�op���dao�࣬Ҫ���Լ������װ-->
<%@ page import="icbc.cmis.flow.util.*"%>
<%@ include file="../../util/header.jsp" %>

<cmis:muidef def="icbc.cmis.flow.FLOW_UTILE">
<%
//��ʵ����
icbc.cmis.tags.PropertyResourceReader propertyResourceReader=new icbc.cmis.tags.PropertyResourceReader(pageContext,"icbc.cmis.flow.FLOW_UTILE");
//�̶�����Ĳ���������get
//entcode,		���ͻ���
//tradecode		��ҵ�������
//tradetype		����������
//flowtype		����������
//ordernum		����ǰ�������
//ordercode		����ǰ���ڴ���
//employeecode	����ǰ��Ա
//empareacode	����ǰ����
//empareaname	����������
//busitype		��ҵ�����ʣ�0����Ӫ��1��ί��

//~~~~ʹ���Լ���ǰ׺��������Ƭ�����ظ�
String flow_entcode = request.getParameter("entcode");
String flow_tradecode = request.getParameter("tradecode");
String flow_flowtype = request.getParameter("flowtype");

String flow_ordernum = request.getParameter("ordernum");

String flow_employeecode = request.getParameter("employeecode");
String flow_empareacode = request.getParameter("empareacode");

util_content_flow flowhistory = new util_content_flow();
ArrayList flow_ret = flowhistory.queryhistory(flow_entcode,flow_tradecode,flow_flowtype,flow_employeecode,flow_empareacode);

%>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/data_check.js"></script>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/liball.js"></script>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/tools.js"></script>
<script Language="JavaScript">
function showquery(str){
	var url = "<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.GB.GB_CreditLimitApprove2Op&opDataUnclear=true&opAction=10001"
			+"&entcode=<%=flow_entcode%>"
			+"&appcode=<%=flow_tradecode%>"
			+"&flownum=" + str
			+"&time="+(new Date);
	var ts = window.showModalDialog(url,window,"dialogWidth:610px;dialogHeight:450px;center:yes;help:no;status:no;resizable:no");


}
</script>
<table width="100%" border="1" cellspacing="" cellpadding="1">
<tr>
<th colspan=9 ><cmis:muiitem item="C000039" exp="��������"/></th>
</tr>
<tr>
<td align="center" class="td1"><cmis:muipub item="P000019" exp="���"/></td>
<td align="center" class="td1"><cmis:muiitem item="C000040" exp="����"/></td>
<td align="center" class="td1"><cmis:muipub item="P000029" exp="����"/></td>
<td align="center" class="td1"><cmis:muiitem item="C000041" exp="����"/></td>
<td align="center" class="td1"><cmis:muipub item="P000044" exp="���"/></td>
<td align="center" class="td1"><cmis:muiitem item="C000051" exp="˵������˵������Ȳ�ѯ"/></td>
<td align="center" class="td1"><cmis:muipub item="P000045" exp="����ʱ��"/></td>
</tr>
<%if(flow_ret.size()==0){%>
	<tr><td colspan=9><cmis:muiitem item="C000043" exp="û���������̼�¼"/></td></tr>
<%}%>
<%for(int i=0; i<flow_ret.size(); i++) {
	HashMap hmap = (HashMap)flow_ret.get(i);
	String process005 = (String)hmap.get("process005");
	String process006 = (String)hmap.get("process006");
	String process006_name = (String)hmap.get("process006_name");
	String process007 = (String)hmap.get("process007");
	String process007_name = (String)hmap.get("process007_name");
	String process008 = (String)hmap.get("process008");
	String process008_name = (String)hmap.get("process008_name");
	String process009 = (String)hmap.get("process009");
	String process010 = (String)hmap.get("process010");
	String process011 = (String)hmap.get("process011");
	String process011_name = "";
	if(process011.equals("0")) process011_name = propertyResourceReader.getPrivateStr("C000037");//"ͬ��";
	if(process011.equals("1")) process011_name = propertyResourceReader.getPublicStr("P000024");//"���";
	if(process011.equals("2")) process011_name = propertyResourceReader.getPublicStr("P000025");//"�˻�";
	if(process011.equals("3")) process011_name = propertyResourceReader.getPrivateStr("C000045");//"�������";
	String isprocess021 = (String)hmap.get("isprocess021");
	String process022 = (String)hmap.get("process022");
	String isfirst = (String)hmap.get("isfirst");
	%>
	<tr <%if(i%2==0){%>bgcolor="ffffff"<%}else{%>bgcolor="f1f1f1"<%}%>>
	<td align="center"><%=process005%></td>
	<td align="center"><%=process007_name%></td>
	<td align="center"><%=process008_name%>&nbsp;</td>
	<td align="center"><%=process006_name%></td>
	
	<%if(flow_ordernum.equals(process005)){%>
	<td colspan=5><%if(process009.equals("1")){%>...<cmis:muipub item="P000171" exp="���ڴ���"/>...<%}else if(process009.equals("2")){%>...<cmis:muiitem item="C000052" exp="�������"/>...<%}else{%>...<cmis:muiitem item="C000044" exp="������"/>...<%}%></td>
	<%}else{%>
	<td align="center"><%=process011_name%></td>
	<td align="center"><a href='javascript:showquery("<%=process005%>");'><cmis:muiitem item="C000047" exp="�鿴"/></a></td>
	<td align="center"><%=process022.substring(0,process022.length()>16?16:process022.length())%></td>	
	<%}%>
	</tr>
<%}%>
</table>
	</cmis:muidef>

