function dozhongshen(){
	if(!confirm("�Ƿ�ȷ�Ͻ������������")){
		return;
	}
	
	form2.operationName.value = "icbc.cmis.flow.BTN.zhongshenOp";
	form2.opAction.value = "10001";
	form2.submit();
   
}