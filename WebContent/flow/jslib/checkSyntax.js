function checkSyntax(target){
	if(target.value.indexOf("<")  >=0 ||
		target.value.indexOf(">") >=0 ||
		target.value.indexOf("&") >=0 ||
		target.value.indexOf("\"")>=0 ||
		target.value.indexOf("'") >=0 ||
		target.value.indexOf("%") >=0 ||
		target.value.indexOf("+") >=0){
		alert("�������в��ܰ��������ַ���\n< ��> ��& ��\" ��'��%��+");
		target.focus();
		return false;
	}
	return true;
}