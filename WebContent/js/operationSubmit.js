//**************************************************************
//  StruComboBox���Js����
//	Creation Date: (2004-05-22)	
//	@Author: ECC - renfl
//	@Version: 1.0
//**************************************************************

var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
var parser=new ActiveXObject("microsoft.xmldom");
parser.async = "false";

function DealOperHTTP(act){
    act = act + "&now=" + new Date();
	objHTTP.Open('Get', act, false);
	objHTTP.Send();
	// ��ѯ���׵����Ƿ������
	if (parser.documentElement.tagName == "error") {
	  alert(parser.documentElement.text);
	  return false;
	}else{
	  if(objHTTP.responseText.indexOf('ok')==-1){
	    alert("ɾ����������");
	    return false; 
	  }
	}
	return true;
}

function DealXMLHTTP(act) {
  act = act + "&now=" + new Date();
	objHTTP.Open('Get', act, false);
	objHTTP.Send();
	// ��ѯ��ӦXML���س����� 
	if(!parser.loadXML(objHTTP.responseText)) {
		alert(parser.parseError.reason + "\n" + parser.parseError.line + "\n" + parser.parseError.srcText + "\n");
	  return false;
	}
	// ��ѯ���׵����Ƿ������
	if (parser.documentElement.tagName == "error") {
	  alert(parser.documentElement.text);
	  return false;
	}
	return true;
}	