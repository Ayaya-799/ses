<%@ page contentType="text/html;charset=GB2312" isErrorPage="true" %>  
<%@ page import="java.io.PrintWriter" %>
<html>
<head>
<title>ϵͳ�������---����ϵ����Ա!!!!!!!</title>
</head>
<body>
<h2>errorPage</h2>
<p>���������<I><%= exception %></I></p><br>
<pre>
�������£�<% exception.printStackTrace(new PrintWriter(out)); %> //��������ԭ��
</pre>
</body>
</html>
