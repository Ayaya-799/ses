<!--�������̼�¼-->
<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page import="icbc.cmis.base.*"%>
<%@ page import="java.util.*"%>
<%@ page session="false"%>
<%@ taglib uri="cmisTags" prefix="cmis" %>

<!--����importҳ����Ҫ���õ��࣬��Ҫ��ҳ���ϵ�op���dao�࣬Ҫ���Լ������װ-->
<%@ page import="icbc.cmis.flow.util.*"%>
<%@ include file="../../util/header.jsp" %>
<cmis:muidef def="icbc.cmis.flow.BA.flow_BA">
<%
CMisSessionMgr sm = new CMisSessionMgr(request);
//--------------------------------------------------------\
String LangCode = (String)sm.getSessionData("LangCode");
if(LangCode == null || LangCode.equals("")) LangCode = "zh_CN";
icbc.cmis.tags.muiStr muistr = new icbc.cmis.tags.muiStr("icbc.cmis.flow.BA.flow_BA",LangCode);
//--------------------------------------------------------/

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
ArrayList flow_ret = flowhistory.queryhistory2(flow_entcode,flow_tradecode,flow_flowtype,flow_employeecode,flow_empareacode,LangCode);

%>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/data_check.js"></script>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/liball.js"></script>
<script src="<%=baseWebPath%>/<cmis:muipub item="JSPATH"/>/tools.js"></script>
<script Language="JavaScript">
function showexplain(formflag,entcode,tradecode,xh,step){
if (formflag=="1"){
window.showModalDialog("<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.util_content_flowOp&opAction=20002&opDataUnclear=true&formflag="+formflag+"&entcode="+entcode+"&tradecode="+encode(tradecode)+"&xh="+xh+"&step="+step+"&time="+new Date(),window,"dialogWidth:550px;dialogHeight:250px;center:yes;help:no;status:no;resizable:no");
}
if (formflag=="3"){
window.showModalDialog("<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.util_content_flowOp&opAction=20002&opDataUnclear=true&formflag="+formflag+"&entcode="+entcode+"&tradecode="+encode(tradecode)+"&xh="+xh+"&step="+step+"&time="+new Date(),window,"dialogWidth:600px;dialogHeight:600px;center:yes;help:no;status:no;resizable:no");
}
if ((formflag=="2") && (step=="1")){
window.showModalDialog("<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.util_content_flowOp&opAction=20002&opDataUnclear=true&formflag="+formflag+"&entcode="+entcode+"&tradecode="+encode(tradecode)+"&xh="+xh+"&step="+step+"&time="+new Date(),window,"dialogWidth:600px;dialogHeight:600px;center:yes;help:no;status:no;resizable:no");
}
if ((formflag=="2") && (step!="1")){
window.showModalDialog("<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util.util_content_flowOp&opAction=20002&opDataUnclear=true&formflag="+formflag+"&entcode="+entcode+"&tradecode="+encode(tradecode)+"&xh="+xh+"&step="+step+"&time="+new Date(),window,"dialogWidth:550px;dialogHeight:250px;center:yes;help:no;status:no;resizable:no");
}
 
}
</script>
<table width="100%" border="1" cellspacing="" cellpadding="1">
<tr>
<th colspan=9 ><cmis:muiitem item="C000021"/></th><!-- �������� -->
</tr>
<tr>
<td align="center" class="td1"><cmis:muiitem item="C000001"/></td><!-- ��� -->
<td align="center" class="td1"><cmis:muiitem item="C000028"/></td><!-- ���� -->
<td align="center" class="td1"><cmis:muiitem item="C000029"/></td><!-- ���� -->
<td align="center" class="td1"><cmis:muiitem item="C000030"/></td><!-- ���� -->
<td align="center" class="td1"><cmis:muiitem item="C000031"/></td><!-- ��� -->
<td align="center" class="td1"><cmis:muiitem item="C000032"/></td><!-- ˵�� -->
<td align="center" class="td1"><cmis:muiitem item="C000033"/></td><!-- �������������������� -->
<td align="center" class="td1"><cmis:muiitem item="C000034"/></td><!-- ������� -->
<td align="center" class="td1"><cmis:muiitem item="C000035"/></td><!-- ����ʱ�� -->
</tr>
<%if(flow_ret.size()==0){%>
	<tr><td colspan=9><cmis:muiitem item="C000046"/><!--û���������̼�¼--></td></tr>
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
	if(process011.equals("0")) process011_name = muistr.getStr("C000036");//"ͬ��";
	if(process011.equals("1")) process011_name = muistr.getStr("C000037");//"���";
	if(process011.equals("2")) process011_name = muistr.getStr("C000038");//"�˻�";
	if(process011.equals("3")) process011_name = muistr.getStr("C000039");//"�������";
	String isprocess021 = (String)hmap.get("isprocess021");
	String process022 = (String)hmap.get("process022");
	String isfirst = (String)hmap.get("isfirst");
	String adjustflag = (String)hmap.get("adjustflag");//Ȩ�ޱ�־
	%>
	<tr <%if(i%2==0){%>bgcolor="ffffff"<%}else{%>bgcolor="f1f1f1"<%}%>>
	<td align="center"><%=process005%></td>
	<td align="center"><%=process007_name%></td>
	<td align="center"><%=adjustflag.equals("0")?process008_name:"<font color=red>***</font>"%>&nbsp;</td>
	<td align="center"><%=process006_name%></td>
	
	<%if(flow_ordernum.equals(process005)){%>
	<td colspan=5><%if(process009.equals("1")){%>...<cmis:muiitem item="C000011"/><!--���ڴ���-->...<%}else{%>...<cmis:muiitem item="C000010"/><!--������-->...<%}%></td>
	<%}else{%>
	<td align="center"><%=process011_name%></td>
	<%if(adjustflag.equals("0")){%>
	<td align="center"><a href="javascript:showexplain('2','<%=flow_entcode%>','<%=flow_tradecode%>','<%=process005%>','<%=isfirst%>');"><%if(isfirst.equals("1")){%><cmis:muiitem item="C000040"/><!--�������--><%}else{%><cmis:muiitem item="C000041"/><!--�鿴--><%}%></a>
						</td>
	<td align="center"><%if(isprocess021.equals("1")){%>
							<a href="javascript:showexplain('1','<%=flow_entcode%>','<%=flow_tradecode%>','<%=process005%>','<%=isfirst%>');"><cmis:muiitem item="C000041"/><!--�鿴--></a>
						<%}else{%>
							&nbsp;
						<%}%></td>
	<td align="center"><%if(process010.equals("1")){%>
							<a href="javascript:showexplain('3','<%=flow_entcode%>','<%=flow_tradecode%>','<%=process005%>','<%=isfirst%>');"><cmis:muiitem item="C000041"/><!--�鿴--></a>
						<% } else {%>
							&nbsp;
						<%}%></td>
	<%}else{%>
		<td colspan=3 align="center"><font color=red>***</font></td>
	<%}%>
	<td align="center"><%=process022.substring(0,process022.length()>16?16:process022.length())%></td>	
	<%}%>
	</tr>
<%}%>
</table>
</cmis:muidef>