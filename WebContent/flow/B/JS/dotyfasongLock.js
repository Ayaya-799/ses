function dotyfasongLock(){

	if(form1.iamsg2process017.value==""){
		alert("������ϵͳ��ʾ��Ϣ");
		return;
	} 
	if(form1.iamsg2process017.value=="1"){
		if(!confirm("ϵͳ��ʾ��ϢΪ�и��Կ��ƣ��Ƿ����")){
			return;
		}
		//200706ȥ��
		//alert("ϵͳ��ʾ��ϢΪ�и��Կ��ƣ����ܽ���ͬ�ⷢ��");
		//return;
	}
	if(form1.iamsg2process017.value=="-1"){
		if(!confirm("ϵͳ��ʾ��Ϣ�쳣���Ƿ����")){
			return;
		}
		//200706ȥ��
		//alert("ϵͳ��ʾ��Ϣ�쳣�����ܽ���ͬ�ⷢ��");
		//return;
	}

	var tmp_nextflow = form1.nextflow.value.split('|');
	form2.nextflow.value = tmp_nextflow[0];
	form2.nextflowname.value = tmp_nextflow[1];
	
	var temp_nextemp = form1.nextareacode.value.split('|');
	form2.nextareacode.value = temp_nextemp[0];
	form2.nextareaname.value = temp_nextemp[1];


	if(isEmpty(form1.nextflow.value)){
		alert("��ѡ����һ����");
		return;
	}


	if(form2.nextflow.value != form2.approve_ordercode.value){
		alert("��ǰ��"+form2.approve_ordername.value+ "���ڣ���ֻ�ܷ��͵�"+form2.approve_ordername.value+"����");
		return;
	}
	if(isEmpty(form1.nextareacode.value)){
		alert("��ѡ����һ����");
		return;
	}
	
	var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
	var parser=new ActiveXObject("microsoft.xmldom");
	parser.async="false";
	var xmlurl = form2.baseWebPath.value+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.flow.BC.BC_inputcheckOp&opDataUnclear=true&opAction=10001";
	xmlurl += "&chk_entcode=" + form2.approve_entcode.value;
	xmlurl += "&chk_tradecode=" + form2.approve_tradecode.value;
	xmlurl += "&chk_ordernum=" + form2.approve_ordernum.value;
	xmlurl += "&chk_isfirst=" + form2.approve_isfirst.value;
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



	
	form2.nextemployeecode.value = form1.nextemployeecode.value;
	form2.nextemployeename.value = form1.nextemployeename.value;

	form2.operationName.value = "icbc.cmis.flow.BTN.fasongOp";
	form2.opAction.value = "10001";
	document.all.mainframe.style.display="none";
	document.all.waitframe.style.display="";
	form2.submit();

}