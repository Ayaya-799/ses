function sxqianpi2(){
	//�������У��
	var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
	var parser=new ActiveXObject("microsoft.xmldom");
	parser.async="false";
	var xmlurl = form2.baseWebPath.value+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.BC.BC_inputcheckOp&opDataUnclear=true&opAction=10005";
	xmlurl += "&check_ent=" + form2.approve_entcode.value;
	xmlurl += "&check_app=" + form2.approve_tradecode.value;
	xmlurl += "&check_num=" + form2.approve_ordernum.value;
	xmlurl += "&newtime=" + new Date();
	objHTTP.Open('GET',encodeURL(xmlurl),false);
	objHTTP.Send();
	if(!parser.loadXML(objHTTP.responseText)){
		alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
		return;
	}
	error = parser.getElementsByTagName("error");
	if(error.length > 0) {
		alert(error.item(0).text);
		return;
	}
	
	//���������Ƿ�����
	xmlurl = form2.baseWebPath.value+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.BC.BC_inputcheckOp&opDataUnclear=true&opAction=10006";
	xmlurl += "&check_ent=" + form2.approve_entcode.value;
	xmlurl += "&check_app=" + form2.approve_tradecode.value;
	xmlurl += "&check_flow=" + form2.approve_flowtype.value;
	xmlurl += "&check_area=" + form2.empareacode.value;
	xmlurl += "&newtime=" + new Date();
	objHTTP.Open('GET',encodeURL(xmlurl),false);
	objHTTP.Send();
	if(!parser.loadXML(objHTTP.responseText)){
		alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
		return;
	}
	error = parser.getElementsByTagName("error");
	if(error.length > 0) {
		alert("����ǩ����"+error.item(0).text);
	return;
	}
	
  //2006-07-19 �����ҳ����жϵ�ǰ�ͻ��������־ ����
  if (form2.approve_tradetype.value == "sx03") {
    alert("�ÿͻ����Լ����ӹ�˾��ݽ�������Ŀͻ�,���ܵ�������ǩ����");
    return false;
  }
  
	//��ǩ��
	form2.qptype.value = "1";  //���ǩ��
	form2.operationName.value = "icbc.cmis.GB.GB_CreditApproveMainOp";
	form2.oper.value = "pass";
	document.all.mainframe.style.display="none";
	document.all.waitframe.style.display="";
	form2.submit();
}