/*update by chenzhaofang on 2006.12.31*/
/************************* create by yhua on 2002/04/06 **********************/
/*��λ���ںϷ��Լ���*/
function isDateYYYYMM(theStr) {
	  if (theStr.length!=6)   return false;
	  var y = theStr.substring(0,4);
	  var m = theStr.substring(4,6);
	  if (isInt(m)==false || isInt(y) ==false) {return(false);}
	  if (y<1900 ||y>3000)  {return(false);}
	  if ( m>12 )  {return(false);}
	  return(true);
}
/*�Ƚϴ�С*/
function isFstBigthanSec(firstS, secondS) {
	  if( firstS >secondS )
		  return(true);
	  else
		  return false;
}

/**********************create by gj 200704 ����  �ж����������ܷ��޸Ļ�ɾ��*********/
function mod_able(customercode,pj_date,pj_kind,mk_code,key_mk,key,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=mod_able&customercode="+customercode+"&pj_date="+pj_date+"&customer_kind="+pj_kind+"&mk_code="+mk_code+"&key_mk="+key_mk+"&key="+key+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
       var nodes = parser.documentElement.childNodes;
       var node = nodes.item(0);
       var able_str=node.getAttribute("able_str"); 
      return able_str;
}

/**********************create by wz 20041110 С��ҵ����  ���ݿͻ���ȡ�ͻ�����*********/
function get_customer_kind(customercode,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=check_customercode&customercode="+customercode+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
       var nodes = parser.documentElement.childNodes;
       var node = nodes.item(0);
       var customer_kind=node.getAttribute("customer_kind");       
      return customer_kind;
/***  customer_kind��һ�� 10������ 20����ó 50������ 01�� ��ҽ 40�� С��ҵ:60  ������30**********/   
}
/*ȡ��ʷ��ͻ�����*/
function get_ls_kind(customercode,pjdate,jldate,baseWebPath){
     var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=get_ls_kind&customercode="+customercode+"&pjdate="+pjdate+"&jldate="+jldate+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
       var nodes = parser.documentElement.childNodes;
       var node = nodes.item(0);
       var customer_kind=node.getAttribute("customer_kind");       
      return customer_kind;
/***  customer_kind��һ�� 10������ 20����ó 50������ 01�� ��ҽ 40�� С��ҵ:60  ������30**********/      
}

//update by wz 20050414 
/** biaozhun:  10:һ��,����,����,��ó 30:��ҽ 01:���� 60:С��ҵ 70:����    **/
function check_customer_new(customercode,biaozhun,baseWebPath){
     var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=check_customer_new&customercode="+customercode+"&biaozhun="+biaozhun+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
       var nodes = parser.documentElement.childNodes;
       var node = nodes.item(0);
       var ret=node.getAttribute("ret");       
      return ret;
/**** ��һλ���ͻ����ͣ�һ�� 10������ 20����ó 50������ 01�� ��ҽ 40�� С��ҵ:60  ������30      
/***  �ڶ�λ���Ƿ���ȷ��־�� 0:��ȷ  1������
/***  �ӵ���λ��ʼ����Ϣ example : �ÿͻ�Ϊ"+kind_name+"�������ڸ�ҳ�����**/      
}

/* add by chenzhaofang 20061009 
 * ret:  ��������(С��)������
**/      
function get_customer_kind_child(customercode,pjdate,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser  = new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=get_customer_kind_child&customercode="+customercode+"&pjdate="+pjdate+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
      var nodes = parser.documentElement.childNodes;
      var node = nodes.item(0);
      var ret=node.getAttribute("customer_kind");       
      return ret;
}

/* add by chenzhaofang 20061128 
 * ret:  ��������(С��)������ʷ���������
**/      
function get_customer_kind_all(customercode,pjdate,querytype,querydate,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser  = new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=get_customer_kind_all&customercode="+customercode+"&pjdate="+pjdate+"&query_type="+querytype+"&query_date="+querydate+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
      var nodes = parser.documentElement.childNodes;
      var node = nodes.item(0);
      var ret=node.getAttribute("customer_kind");       
      return ret;
}

/* add by chenzhaofang 20070426 
 * ret:  �����ͻ����ͣ��ͻ������Ͳ������е�
**/      
function get_customer_type(customercode,pjdate,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser  = new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=get_customer_type&customercode="+customercode+"&pjdate="+pjdate+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
      var nodes = parser.documentElement.childNodes;
      var node = nodes.item(0);
      var ret=node.getAttribute("customer_kind");       
      return ret;
}

/* add by chenzhaofang 20061016 
 * ret:  �ж�����ר��Ա1-�ǣ�0-��
**/      
function check_administrator(customercode,employeecode,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser  = new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=check_administrator&customercode="+customercode+"&employeecode="+employeecode+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
      var nodes = parser.documentElement.childNodes;
      var node = nodes.item(0);
      var ret=node.getAttribute("ret");       
      return ret;
}

/* add by chenzhaofang 20070625 
 * ret:  �ж������Ŵ�Ա1-�ǣ�0-��
**/      
function check_admin(customercode,employeecode,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser  = new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=check_admin&customercode="+customercode+"&employeecode="+employeecode+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
      var nodes = parser.documentElement.childNodes;
      var node = nodes.item(0);
      var ret=node.getAttribute("ret");       
      return ret;
}

/* add by chenzhaofang 20061031 
 * ret:  �ж��Ƿ���������1-�ǣ�0-��
**/      
function check_cp(customercode,pjdate,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser  = new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=check_cp&customercode="+customercode+"&pjdate="+pjdate+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
      var nodes = parser.documentElement.childNodes;
      var node = nodes.item(0);
      var ret=node.getAttribute("ret");       
      return ret;
}

/* add by chenzhaofang 20061110
 * ret:  �ж��Ƿ��ж��Զ���00��λ
**/      
function check_dxdl(customerkind,modulecode,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser  = new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=check_dxdl&customer_kind="+customerkind+"&module_code="+modulecode+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
      var nodes = parser.documentElement.childNodes;
      var node = nodes.item(0);
      var ret=node.getAttribute("ret");       
      return ret;
}
/* add by chenzhaofang 20061230 
 * ret:  �ж��ܷ��޸����ܡ��й��Ŵ�Ա1���� 0����
**/      
function change_admin(customer_code,employee_code,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser  = new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=change_admin&customer_code="+customer_code+"&employeecode="+employee_code+"&time="+new Date();
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
      var nodes = parser.documentElement.childNodes;
      var node = nodes.item(0);
      var ret=node.getAttribute("ret");       
      return ret;
}

/* add by chenzhaofang 20070702 
 * ret:�жϲ��񱨱��Ƿ�����1-�ǣ�0-��
**/      
function report_locked(customercode,pjdate,reporttype,baseWebPath){
      var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser  = new ActiveXObject("microsoft.xmldom");
      tmp = "opAction=report_locked&customercode="+customercode+"&pjdate="+pjdate+"&report_type="+reporttype+"&time="+new Date();                 
      objHTTP.open('GET',baseWebPath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.eval.EA.EA_mfevl_999_Op&xmlOutput=true&opDataUnclear=true&'+tmp,false);          
      objHTTP.send();         
      var xml = objHTTP.responseText;
      if(!parser.loadXML(xml)) {
        return;
      }
      error = parser.getElementsByTagName("error");
      if(error.length > 0) {
        alert(error.item(0).text);
        return;
      }
      var nodes = parser.documentElement.childNodes;
      var node = nodes.item(0);
      var ret=node.getAttribute("ret");       
      return ret;
}

/*****************************************************************************/
/*ȥ�ո�*/
function trim(str){
    var returnstr="";
    if(str == "")
       return "";
    var i = 0;
    for(i = 0;i<str.length;i++)
    {
        if(str.charAt(i) == ' '){
            continue;
         }
        break;
    }
    str = str.substring(i,str.length);
    if(str =="")
       return "";
    for(i=str.length-1;i>=0;i--)
    {
        if(str.charAt(i)==' '){
            continue;
         }
         break;
     }
     returnstr = str.substring(0,i+1);
     return returnstr;
}
/*һ�����Ƿ���ĳ��������*/
function isBetween (val,lo,hi){
	  if ((val<lo) || (val > hi)) { return(false);}
	  else {return(true);}
}
/*�ж��Ƿ���Ч���ڸ�ʽ��λ*/
function isDate(theStr) {
	 if (theStr.length!=8)   return false;
	  var y = theStr.substring(0,4);
	  var m = theStr.substring(4,6); if(m/1==0||m/1>12) return false;
	  var d = theStr.substring(6,8);
	  var maxDays = 31;
	  if (isInt(m)==false || isInt(d)==false || isInt(y) ==false) {
		  return(false);}
	  else if (y<1900 ||y>3000)  {return(false);}
	  else if (m==4 || m==6 || m==9 || m==11) maxDays = 30;
	  else if (m==2) {
			  if (y%4>0) maxDays= 28;
			  else if (y % 100 ==0 && y % 400 >0) maxDays = 28;
			  else  maxDays = 29;
			}
	 if (isBetween(d,1,maxDays) == false) { return(false);}
	 else {return(true);}
}
/*����ʱ��*/
function isTime(theStr) {
  var colonDex = theStr.indexOf(':');
  if ((colonDex < 1) || (colonDex > 2)) {return(false);}
  else {
	 var hh = theStr.substring(0,colonDex);
	 var ss = theStr.substring(colonDex+1,theStr.length);
	 if ((hh.length<1) || (hh.length>2) || (!isInt(hh))) {return(false);}
	 else if ((ss.length<1) || (ss.length>2) || (!isInt(ss))) {return(false);}
	 else if ((!isBetween(hh,0,23)) || (!isBetween(ss,0,59))) {return(false);}
	 else {return(true);}
  }
}
/*����0-9*/
function isDigit(theNum) {
   var theMask = '0123456789';
   if (isEmpty(theNum)) return(false);
   else if (theMask.indexOf(theNum) == -1) return(false);
   return(true);
}
/*�ж�Ϊ��*/
function isEmpty(str) {
   str = trim(str);
   if ((str==null) || (str.length == 0)) return true;
   else return(false);
}
/*�ж�Ϊ����*/
function isInt(theStr) {
   var flag = true;
   theStr = trim(theStr);
   if (isEmpty(theStr))  flag=true;
   else
	{ if (theStr.substring(0,1)=='-')   //����
		  theStr = theStr.substring(1);
	  for (var i=0;i<theStr.length;i++){
	   if (isDigit(theStr.substring(i,i+1))==false) {
		  flag = false;
		  break;
		  }
	   }
	 }
	 return flag;
 }
 
 /*��������λС��λ��������*/
 function isReal(theStr,intLen,decLen) { //(����ֵ,�������ֳ���,С�����ֳ���)
	 theStr = trim(theStr);
	 //test if chars are acceptable
	 if(theStr.indexOf(" ")>0){
	   return "�����м䲻������ֿո�";
	 }
	 if(isNaN(theStr*1))
	   return "�������벻�Ϸ�";
	 
	 var v_ret=0;
	 var ret_mesg="ok";
	 
	 if (theStr.substring(0,1)=='-')   //����
		  theStr = theStr.substring(1);
//xgl begin 20030407
	  if(theStr.substring(0,1)=='.'){
		theStr = '0'+theStr;
	 }
	  //xgl end

	 if (!isEmpty(theStr)) {
	 var dot1st = theStr.indexOf('.');
	 var dot2nd = theStr.lastIndexOf('.');

	 if (dot1st == -1) {
		 if (!isInt(theStr)) v_ret=1;
		 else if (theStr.length>intLen) v_ret=2;
		 else v_ret=0;
	 }
	 else if (dot1st != dot2nd) v_ret=1;
	 else if (dot1st==0) v_ret=1;
	 else {
		  var intPart = theStr.substring(0,dot1st);
		  var decPart = theStr.substring(dot2nd+1);
		  if (intPart.length > intLen) v_ret=2;
		  else if (decPart.length > decLen) v_ret=3;
		  else if (!isInt(intPart) || !isInt(decPart)) v_ret=1;
		  else if ( isEmpty(decPart)) v_ret=1;
		  else v_ret=0;
	 }
	}
	 if (v_ret==0) ret_mesg="ok";
	 else if (v_ret==1) ret_mesg="�������벻�Ϸ�";
	 else if (v_ret==2) ret_mesg="�������ֳ���(���"+intLen+"λ)";
	 else if (v_ret==3) ret_mesg="С�����ֳ���(���"+decLen+"λ)";
	 else  ret_mesg="������";

	 return ret_mesg;
 }
 /*������*/
 function check_jjj(para_code) {
  var local_code = para_code.substr(0,8);
  var de_code = para_code.substr(8,1);
  var verify_code = para_code.substr(9,1);
  if (para_code.length > 10) {
    return true;
  }
  if (de_code != '-') {
    return "��9λӦΪ�ָ��� '-'��";
  }
  total = 0;
  for (i=0;i<8 ;i++ ) {
    switch(i+1) {
      case 1:
        w = 3;break;
      case 2:
        w = 7;break;
      case 3:
        w = 9;break;
      case 4:
        w = 10;break;
      case 5:
        w = 5;break;
      case 6:
        w = 8;break;
      case 7:
        w = 4;break;
      case 8:
        w = 2;break;
      default:
        w = 0;break;
    }
    s = local_code.substr(i,1);
    if(s >= '0' && s <= '9') {
      n = s*1;
    } else if (s >='A'&& s <='Z') {
      n = s.charCodeAt(0) - 55;
    } else {
      return "����ֱ������岿�����벻��ȷ!";
    }
    total = total + w * n;
  }
  goal = total % 11;
  switch (goal) {
    case 0:
      rst = '0'; break;
    case 1:
      rst = 'X'; break;
    default:
      rst = (11 - goal) + ""; break;
  }
  if(rst==verify_code) {
    return true;
  } else {
    return "����ֱ����У���벿�ֲ���ȷ�����ݵ�ǰ���岿��,У����ӦΪ" + rst;
  }
}

/*ȥ����*/
var space = "                                                                ";
var zero = "000000000000000000000000000000000000000000000000000000000000000";
function trimNum(str){
    var returnstr="";
    str = "" + str;
    for(i = 0;i<str.length;i++)
    {
        if(str.charAt(i) != ' ' && str.charAt(i) != ',')
        {
            returnstr += str.charAt(i);
         }
    }
     return returnstr;
}

/*ֻ����������*/
function inputNumber() {
	if (event.keyCode < 45 || event.keyCode > 57 || event.keyCode == 47) event.returnValue = false;
}
/*��ʽ���������ֺ�С������*/
function exact(val,len,decimal) {
	var nav = 0;

	if(decimal == null) decimal = 2;
	var scale = Math.pow(10, decimal);
	var t = Math.round(parseFloat(val) * scale);
	if(t < 0){ nav = 1; t = 0 - t; }
	var tStr = "" + t;
	if(tStr == "NaN") return "";

	if(tStr.length <= decimal) {
	  tStr = zero.substr(0,decimal - tStr.length + 1) + tStr;
	}

  var str = "";
  if(decimal > 0) {
  	str = "." + tStr.substring(tStr.length - decimal,tStr.length);
  }

	for(var i=tStr.length-decimal-1, j=0 ; i>=0; i--) {
		if(++j > 3) {
			str = "," + str;
			j = 1;
		}
		str = tStr.substring(i, i+1) + str;
	}
	if(nav == 1) str = "-" + str;
	str = space.substr(0,len - str.length + 1) + str;
	return str;
}

function toExact(ob,len,dec) {
	var val = trimNum(ob.value);
	if((ret = isReal(val,len - dec,dec)) != "ok") { alert(ret); ob.focus(); return false; }
	ob.value = exact(val,ob.size,dec);
	return true;
}

function toExact2(ob) {
	var val = trimNum(ob.value);
  var len = ob.dataLen;
  var dec = ob.decLen;
	if((ret = isReal(val,len - dec,dec)) != "ok") {event.cancelBubble=true ;ob.focus(); alert(ret);  return false; }
	ob.value = exact(val,ob.size,dec);
}

function allExact() {
  var allInputs = document.all.H7D4F6Z;
  if(allInputs == null) return;
  if(allInputs.length == null) {
    toExact2(allInputs);
  } else {
    for (var i = 0; i < allInputs.length; i++) {
      toExact2(allInputs(i));
    }

  }
}

function toNumber(ob) {
	var ts = trimNum(ob.value);
	ob.value = ts;
	ob.select();
}

function toNumber2(ob) {
	var ts = trimNum(ob.value);
	ob.value = ts;
}

function allToNumber() {
  var allInputs = document.all.H7D4F6Z;
  if(allInputs == null) return;
  if(allInputs.length == null) {
    toNumber2(allInputs);
  } else {
    for (var i = 0; i < allInputs.length; i++) {
      toNumber2(allInputs(i));
    }

  }
}

function isDateValid1(str){
  if(str.length < 8) return false;
  var sy = str.substring(0,4);
  var iy = sy/1;
  var sm =str.substring(4,6);
  var im = sm/1;
  var sd = str.substring(6,8);
  var id = sd/1;
  var month1 = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
  var month2 = new Array(31,29,31,30,31,30,31,31,30,31,30,31);
  if((iy%4 == 0)||(iy%400 ==0)||(iy%100 == 0)){
    if((id <= month2[im-1])&&(id >=1 ))
   	  return true;
    else
      return false;
  }
  else{
    if((id <= month1[im-1])&&(id >= 1))
   	  return true;
   else
      return false;
  }
}
/***************************************************************/


/*******************�ж������������Ƿ�ȵ�ǰϵͳʱ���**********/
/*                                                             */
/*     �������Ϊ8λ�ַ��� exp:20050606                        */
function isDateValid2(str){
  var now = new Date()
  var year = now.getYear()
  var month = now.getMonth()+1;
  var day = now.getDate()
  var today =""+year;
  today += ((month<10)?"0":"")+month;
  today += ((day<10)?"0":"")+day;

  if (str >= today) return true;
  return false;
}
/***************************************************************/

/*********************�ж��ַ����Ƿ�Ϊ����*********************/
function isNumberCode(str){
  var len;
  len = str.length;
  for(var i=0;i<len;i++)
  {
      if((str.charAt(i) < '0') || (str.charAt(i) > '9'))
      {
        return false;
      }
  }
  return true;
}


/*******************�ж�8λ��6λ����4λ����**********************add by wbn***********/
function isDateValid(str){
  var sy = str.substring(0,4);
  var iy = sy/1;
  var sm = str.substring(4,6);
  var im = sm/1;
  var sd = str.substring(6,8);
  var id = sd/1;
  if(str.length == 8){
     if(isDate(str)){
   	return true;
     }else{
        return false;
     }
  }else if(str.length == 6){
    if (iy<1900 ||iy>3000 ||im==0 ||im>12){
       return false;
    }else{
       return true;
    }
  }else if(str.length == 4){
    if (iy<1900 ||iy>3000){
       return false;
    }else{
       return true;
    }
  }else return false;
}

//������ת��Ϊ��"/"
function toDateShow(ob,len) {
       var val = ob.value;
       if (isEmpty(val)) return true;
       if (len==6) val=val+"01";
       if (!isDate(val)) {
         alert("�������벻�Ϸ�");
         ob.focus();
         return false;
       }
        if (len==6)
           ob.value = val.substring(0,4)+"/"+val.substring(4,6);
	else
            ob.value = val.substring(0,4)+"/"+val.substring(4,6)+"/"+val.substring(6,8);
}

function toDateEdit(ob,len) {
       var val = ob.value;
       if (len==6 && val.length!=7 ) return true;
       if (len==8 && val.length!=10 ) return true;
       ob.value = trimDate(val,len);
       ob.select();
}

function trimDate(str,len) {
       if (len==8 && str.length!=10) return str;
       if (len==6 && str.length!=7) return str;
       if (len==6)
         return str.substring(0,4)+str.substring(5,7);
       else
         return str.substring(0,4)+str.substring(5,7)+str.substring(8,10);
}
/**************�жϱ��������Ƿ�Ϸ�����������,00���ǺϷ���,added by lzq*/
function isReportDateValid(str){
  if(isNaN(str)){
    return false;
  }
  if(!isNumberCode(str)){
    return false;
  }
  var sy = str.substring(0,4);
  var iy = sy/1;
  var sm = str.substring(4,6);
  var im = sm/1;
  if(str.length == 6){
    if (iy<1900 ||iy>3000 ||im>12){
       return false;
    }else{
       return true;
    }
  }else return false;
}

function checkLength(v){
	var s = v.value;
	var l = v.maxLength;
	var len = 0;
	  for(i=0;i<s.length;i++){
	    var c = s.substr(i,1);
	    var ts = escape(c);
	    if(ts.substring(0,2) == "%u") {
	     len+=2;
	    } else {
	     len+=1;
	    }
	  }
	  if(l>=len){
	    return;
	  }
	  else{
	     alert("�����������ֵ���������"+l+"���ַ�");
	    v.focus();
	  }
}


function checkAreaLength(v,l){
	var s= v.value;
	var temlen=0;
	var len = 0;
	for(i=0;i<s.length;i++){
	    var c = s.substr(i,1);
	    var ts = escape(c);

	    if(ts.substring(0,2) == "%u"){
	     len+=2;
	     len+=temlen;
	     temlen=0;
	    }
	    else if(ts.substring(0,3) == "%0D"){
	    	temlen+=1;
	    }
	    else if(ts.substring(0,3) == "%0A"){
	        temlen+=1;
	    }
	    else if(ts.substring(0,3) == "%20"){
	    	temlen+=1;
	    }
	     else{
	     len+=1;
	     len+=temlen;
	     temlen=0;
	    }
	  }
	 if(len>l){
	   alert("����ֵ���������"+l+"���ַ�");
	    v.focus();
	 }
}

