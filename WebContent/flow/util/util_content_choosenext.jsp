<!--ѡ����һ����--> 
<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page import="icbc.cmis.base.*"%> <%@ page import="java.util.*"%>
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
CMisSessionMgr sm = new CMisSessionMgr(request);
//--------------------------------------------------------\
String LangCode = (String)sm.getSessionData("LangCode");
if(LangCode == null || LangCode.equals("")) LangCode = "zh_CN";
icbc.cmis.tags.muiStr muistr = new icbc.cmis.tags.muiStr("icbc.cmis.flow.BA.flow_BA",LangCode);
//--------------------------------------------------------/

String chsn_AreaCode = request.getParameter("empareacode");
String chsn_entcode = request.getParameter("entcode");
String chsn_flowtype = request.getParameter("flowtype");
String chsn_busitype = request.getParameter("busitype");
String chsn_tradecode = request.getParameter("tradecode");
String chsn_tradetype = request.getParameter("tradetype");

util_content_choosenext c_nextarea = new util_content_choosenext();
util_content_choosenext1 c_nextarea1 = new util_content_choosenext1();
ArrayList chsn_flowlist = c_nextarea.getflowlist(chsn_flowtype,chsn_busitype,LangCode);
ArrayList chsn_arealist = null;

if (null != chsn_tradetype && chsn_tradecode!=null && (chsn_tradetype.substring(0,2)).equals("qt"))
{
chsn_arealist = c_nextarea1.getnextarea(chsn_AreaCode,chsn_busitype,chsn_entcode,chsn_tradecode,chsn_tradetype);
}
else{
 chsn_arealist = c_nextarea.getnextarea(chsn_AreaCode,chsn_busitype,chsn_entcode);
}
CMisSessionMgr smTemp = new CMisSessionMgr(request);//
String s_Major = (String) smTemp.getSessionData("Major"); //��ǰ��Աҵ��רҵ��

%>
<script language="JavaScript">
function choosenextemployeecode()
{
	if(isEmpty(form1.nextareacode.value)){
		alert(<cmis:muiitem item="C000056" mark="\""/>);//"��ѡ����һ�������"
		return;
	}
	var temparea = form1.nextareacode.value.split('|');
	var ts = null;

	ts = window.showModalDialog("<%=baseWebPath%>/util/util_ChooseEmp.jsp?queryType=QueryNormalEnp&ask=false&time=" + (new Date) + "&area_code="+temparea[0]+"&sub_bank=0&major_code=<%=s_Major%>&class_code=",window,"dialogWidth:600px;dialogHeight:400px;center:yes;help:no;status:no;resizable:no");
	if(ts != null){
		form1.nextemployeecode.value = ts[0];
		form1.nextemployeename.value = ts[1];
	} 
}
function cleannext(){
	form1.nextemployeecode.value = "";
	form1.nextemployeename.value = "";
}
</script> 
<table width="100%" border="1" cellspacing="" cellpadding="1">
	<tr align="left">
		<td width="140" class="td1"><cmis:muiitem item="C000057"/><!-- ѡ����һ���� --></td>
		<td width="208">
		<select name="nextflow">
		<option value=""></option>
		<%
		for(int i=0; i<chsn_flowlist.size(); i++) {		
			HashMap hmap = (HashMap)chsn_flowlist.get(i);%>
		<option value ="<%=(String)hmap.get("role_code")%>"><%=(String)hmap.get("role_name")%></option>
		<%}%>
		</select>
		</td>
		<td width="140" class="td1"><cmis:muiitem item="C000058"/><!-- ��һ������� --></td>
		<td width="208">
		<select name="nextareacode" onchange="javascript:cleannext();">
		<option value=""></option>
		<%for(int i=0; i<chsn_arealist.size(); i++) {		
			HashMap hmap = (HashMap)chsn_arealist.get(i);%>
		<option value ="<%=(String)hmap.get("area_code")%>"><%=(String)hmap.get("area_name")%></option>
		<%}%>		
		</select>
		</td>
	</tr>
	<tr align="left">
		<td width="140" class="td1"><cmis:muiitem item="C000059"/><!-- ��һ�����˴��� --><a href="javascript:cleannext();"><cmis:muiitem item="C000060"/></a></td>
		<td width="208" valign="bottom"><input name="nextemployeecode" size="22" maxlength="15" readonly>
			<a href="javascript:choosenextemployeecode();" title="ѡ����һ�����˴���"><img src="<%=baseWebPath%>/images/choose.gif" border="0"></a>
		</td>
		<td width="140" class="td1"><cmis:muiitem item="C000061"/><!--��һ����������--></td>
		<td width="208" valign="bottom"><input type="text"
			name="nextemployeename" size="22" maxlength="60" readonly></td>
	</tr>
</table>
</cmis:muidef>