function sxback(){
	if(form2.approve_isfirst.value=='1'){
		alert("��ǰ�Ƿ��𻷽ڣ����ܽ��л�ԭ����");
		return;
	}

	if(!confirm("����ǰ����ҵ��ԭ��δ����״̬���Ƿ������")){
		return;
	}
	
	//��ԭ����
	form2.operationName.value = "icbc.cmis.flow.BTN.sxbackOp";
	form2.opAction.value = "10001";
	document.all.mainframe.style.display="none";
	document.all.waitframe.style.display="";
	form2.submit();
}