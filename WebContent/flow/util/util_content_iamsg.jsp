<!--1.11	��ʾֻ���������-->
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
String iamsg_entcode = request.getParameter("entcode");
String iamsg_tradecode = request.getParameter("tradecode");
String iamsg_flowtype = request.getParameter("flowtype");
String iamsg_tradetype = request.getParameter("tradetype");
String iamsg_ordercode = request.getParameter("ordercode");
String iamsg_employeecode = request.getParameter("employeecode");
String iamsg_empareacode = request.getParameter("empareacode");
util_content_iamsg advicehistory = new util_content_iamsg();

ArrayList jipingadvice = advicehistory.queryiamsg(iamsg_entcode,iamsg_tradecode,iamsg_empareacode,iamsg_employeecode,iamsg_ordercode,iamsg_flowtype,iamsg_tradetype);
String o_ret1="";
String o_ret2="";
if(jipingadvice.size()==0)
{
o_ret1="��";
o_ret2="��";
}
if(jipingadvice.size()!=0)
{
for(int i=0; i<jipingadvice.size(); i++) {
	HashMap hmap = (HashMap)jipingadvice.get(i);

	 o_ret1 = (String)hmap.get("o_ret1");
	 o_ret2 = (String)hmap.get("o_ret2");
	}
}
%>
<input type="hidden" name="iamsg2process017" value="0">
<input type="hidden" name="o_stoparea" value="">

<table width="100%" border="1" cellspacing="" cellpadding="1">
<tr><th colspan=2><cmis:muiitem item="C000062"/><!-- ϵͳ��ʾ��Ϣ --></th></tr>
<tr>
<td class=td1 align="center" width="50%"><cmis:muiitem item="C000064"/><!-- ���Կ�����Ϣ�� --></td>
<td class=td1 align="center" width="50%"><cmis:muiitem item="C000065"/><!-- ��ʾ����Ϣ�� --></td>
</table>
<table width="100%" border="0" cellspacing="" cellpadding="0">

<tr>
<td><TEXTAREA NAME="iamsg2gxadvice" ROWS="5" COLS="65"  readonly ><%=o_ret1%></TEXTAREA></td>  
<td><TEXTAREA NAME="iamsg2rxadvice" ROWS="5" COLS="65"  readonly ><%=o_ret2%></TEXTAREA></td> 
</tr>
</table>
</cmis:muidef>

