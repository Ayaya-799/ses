<!--	ǩ����ǩ��ķſ����� ҳ�� -->
<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ page import="icbc.cmis.base.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Vector" %>
<%@ page import="icbc.cmis.flow.util.*"%>
<%@ taglib uri="cmisTags" prefix="cmis" %>
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
String iamsg_entcode = request.getParameter("entcode");
String iamsg_tradecode = request.getParameter("tradecode");
String iamsg_flowtype = request.getParameter("flowtype");
String iamsg_tradetype = request.getParameter("tradetype");
String iamsg_ordercode = request.getParameter("ordercode");
String iamsg_employeecode = request.getParameter("employeecode");
String iamsg_empareacode = request.getParameter("empareacode");
util_content_subscribe subscribe = new util_content_subscribe();

String subscribemsg = subscribe.querysubscribe(iamsg_entcode,iamsg_tradecode,iamsg_empareacode,iamsg_employeecode,iamsg_ordercode,iamsg_flowtype,iamsg_tradetype);
%>
  <table width="100%" border="1" cellspacing="" cellpadding="1">
  <tr><th  class="td1" align="left"><font size="4">ǩ����ǩ��ķſ�����:</font></th></tr>
  <tr>
  <td class="td1">
 <TEXTAREA NAME="process021" ROWS="7" COLS="120"><%=subscribemsg%></TEXTAREA>   
  </td>
  <tr>
  </table>




 
 
