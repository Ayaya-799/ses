function dofenfa(){
	//У�鲿�ִ�
	if(isEmpty(form1.nextflow.value)){
		alert("��ѡ����һ����");
		return;
	}
	if(isEmpty(form1.nextareacode.value)){
		alert("��ѡ����һ����");
		return;
	}
	//���ڷַ���ֻ�ܷ��͵�ͬһ���ڣ�ͬһ����
	if(form1.nextflow.value != form2.approve_ordercode.value){
		alert("�ַ�ʱ��ֻ�ܷ��͵���ǰ���ڣ�"+form2.approve_ordername.value);
		return;
	}
	if(form1.nextareacode.value != form2.empareacode.value) {
		alert("�ַ�ʱ��ֻ�ܷ��͵���ǰ��Ա�����У�"+form2.empareaname.value);
		return;
	}
	if(isEmpty(form1.nextemployeecode.value)) {
		alert("��ѡ����Ҫ���͵Ĺ�Ա");
		return;
	}
	
	form2.nextflow.value = form1.nextflow.value;
	form2.nextflowname.value = form1.nextflow.value;
	form2.nextareacode.value = form1.nextareacode.value;
	form2.nextareaname.value = form1.nextareaname.value;
	form2.nextemployeecode.value = form1.nextemployeecode.value;
	form2.nextemployeename.value = form1.nextemployeename.value;

	form2.operationName.value = "icbc.cmis.flow.BTN.fenfaOp";
	form2.opAction.value = "10001";
	document.all.mainframe.style.display="none";
	document.all.waitframe.style.display="";
	form2.submit();
}
