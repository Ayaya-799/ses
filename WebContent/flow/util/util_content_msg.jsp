<!--���˵��-->
<%@ page contentType="text/html;charset=GBK" %>
<%@ page import="icbc.cmis.base.*"%>
<%@ page import="java.util.*"%>

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
String flow_entcode = request.getParameter("entcode");
String flow_tradecode = request.getParameter("tradecode");
String flow_ordernum = request.getParameter("ordernum");
String flow_ordercode = request.getParameter("ordercode");
//util_content_flow flowhistory = new util_content_flow();
//Vector flow_ret = flowhistory.queryhistory(flow_entcode,flow_tradecode);

%>

<script language="JavaScript">
function showadvice(entcode,tradecode,ordernum,ordercode)//formflag:1,���˵�� 2��������
{

 window.showModalDialog("<%=baseWebPath%>/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.util_content_msg&opAction="20001"&entcode="+entcode+"&tradecode="+tradecode+"&ordernum="+ordernum+"&ordercode="+ordercode+"&time="+new Date(),window,"dialogWidth:500px;dialogHeight:320px;center:yes;help:no;status:no;resizable:no");
}
</script>

<table width="100%" border="1" cellspacing="" cellpadding="1">
<tr>
<td  class="td1">�������</td>
</tr>
<tr>
</tr>

<tr>
	
	<td>���˵��(���������ť,�ڶԻ���������)<a href="javascript:showadvice('<%=flow_entcode%>','<%=flow_tradecode%>','<%=flow_ordernum%>','<%=flow_ordercode%>');"> ��� </a></td>
</tr>

</table>