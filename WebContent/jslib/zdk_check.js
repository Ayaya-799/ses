function check_protocl_subspe(theStr){
/*	theStr			У���ר����Э����
	���ز�����false --ΪУ�鲻ͨ��  true --ΪУ��ͨ��
*/	
	checkLength(theStr);
	if(theStr.value.length!=21){
		alert('ר����Э���ű���Ϊ21λ');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(0,1),'S')){
		alert('ר����Э���ŵ�����ĸ����ΪS');
		//theStr.focus();
		return false;
	}
	if(!isAlpha(theStr.value.substring(1,2),'M')&&!isAlpha(theStr.value.substring(1,2),'R')&&!isAlpha(theStr.value.substring(1,2),'T')&&!isAlpha(theStr.value.substring(1,2),'S')&&!isAlpha(theStr.value.substring(1,2),'Z')){
		alert('ר����Э���ŵĵڶ�����ĸ����ΪM,R,S,Z��T');    /*zyh����S,Z�ַ� 20030425*/
		//theStr.focus();
		return false;
	}
	if(!isAlpha('ABCDEFGHIJKLMNOPQRSTUVWXYZ',theStr.value.substring(20,21))){      /*zyh�޸�20030425*/
		alert('ר����Э���ŵ�ĩλ����Ϊ��ĸ');
		//theStr.focus();
		return false;
	}
	if(!isInt(theStr.value.substring(2,20))){
		alert('ר����Э���ŵ�3-20λ����Ϊ����');
		//theStr.focus();
		return false;
	}
	return(true);
}

function tranauth_check(agree_type,agree_no,employee_id,modify_date){
/*	agree_type   		Э������
	agree_no	 	Э�����
	employee_id		��Ա�ţ��޸�Э�飩
	modify_date		�޸�����
	���ز�����false --Ϊ��Ȩ�޸�  true --Ϊ��Ȩ�޸�
*/
	var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
	var parser=new ActiveXObject("microsoft.xmldom");
	parser.async="false";
        
	var tmp = "&TA450401001="+agree_type+"&TA450401002="+agree_no+"&TA450401004="+employee_id+"&InDate="+modify_date+"&time="+(new Date);
	objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.OU.OU_tranAuthQuery&opDataUnclear=true'+tmp,false);
	objHTTP.send();
	var xml = objHTTP.responseText;
	if(!parser.loadXML(xml)) {
		return false;
	}
	error = parser.getElementsByTagName("error");
	if(error.length > 0) {
		alert(error.item(0).text);
		return false;
	}
	var out_flag = parser.documentElement.getAttribute("out_flag");
	var out_mesg = parser.documentElement.getAttribute("out_mesg");
	if(out_flag == "1"){//�쳣
		alert(out_mesg);
		return false;
	}else if(out_flag == "0"){//��Ȩ�޸�
		return true;
	}else{//��Ȩ�޸�
		//alert(out_mesg);
		return false;
	}
}

function project_agreement_check(TA220011001,TA220011002){
/*	TA220011001   		��ҵ����
	TA220011002	 	��Ŀ����
	���ز�����0 --Ϊ��Ŀ�޶�ӦЭ��  1 --Ϊ��Ŀ�ж�ӦЭ��  2 --����
*/	
	var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
	var parser = new ActiveXObject("microsoft.xmldom");
	parser.async="false";
	var ret = 2;

    if(TA220011001 == ""){
		alert("��ҵ���벻��Ϊ�գ�");
		return ret;
    }
    if(TA220011002 == ""){
		alert("��Ŀ���벻��Ϊ�գ�");
		return ret;
    }

	var tmp = "TA220011001="+TA220011001+"&TA220011002="+TA220011002;
	objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.OG.TransLoanExecOp&xmlOutput=true&opDataUnclear=true&act=checkagreement&'+tmp,false);
	objHTTP.send();
	var xml = objHTTP.responseText;
	if(!parser.loadXML(xml)) { 
		alert(parser.parseError.reason + "\n" + parser.parseError.line + "\n" + parser.parseError.srcText + "\n");    
		return ret;
	}

	if(parser.documentElement.tagName == "error") {
		var errorCode = parser.getElementsByTagName("errorCode").item(0).childNodes.item(0).text;
		var errorDispMsg = parser.getElementsByTagName("errorDispMsg").item(0).childNodes.item(0).text;		
		alert("�������:"+errorCode+"\n"+errorDispMsg);
		return ret;
	}
	var nodes = parser.documentElement.childNodes;
	if(nodes.length > 0){
		var node = nodes.item(0);    
		var flag = node.getAttribute("flag");
		if (flag == "0")
			ret = 0;
		else if (flag == "1")
			ret = 1;
		else
			ret = 2;
	}else{
		ret = 2;
	}
	return ret;
}