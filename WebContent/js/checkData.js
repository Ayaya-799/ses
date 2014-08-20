//check number  
function isNumber(e){
  if(isNaN(e)){
    return false;
  }
  if(e<0){
    return false;
  }
  return true;
}

function isNatrual(str)
{	
	
	for(var i=0 ; i<str.length ; i++)
 	{	
 		var oneChar=str.charAt(i);
      	if((i==0)&&(oneChar=='0')){
      		return false;
      	}
      	if(oneChar<'0'||oneChar>'9')
   		{	
   			return false;
  		}
 	}
 	return true;
}


function isNumber1(e){
  if(isNaN(e)){
    return false;
  }
  return true;
}
//check integer
function isInteger(e){ 
  for(var i=0;i<e.length;i++){
    var oneChar=e.charAt(i);
    if((i==0) && (oneChar=='+'||oneChar=='-')){
      continue;
    }      
    if(oneChar<'0'||oneChar>'9'){
      return false;
    }
    }
  return true;
}
//check positive integer
function isPosInteger(e){
  if (isEmpty(e)) {
  	return false;
  }
  for(var i=0;i<e.length;i++){
    var oneChar=e.charAt(i);
    if(oneChar<'0'||oneChar>'9'){
      return false;
    }
  }
  if(e<0){return false;}
  return true;
}

function isPosNumber(e){ 
  for(i=0;i<e.length;i++){
    var oneChar=e.charAt(i);
    if((oneChar<'0'&&oneChar!='.')||oneChar>'9'){
      return false;
    }
    }
  return true;
}
function isLetAndPosInt(e){
  for(i=0;i<e.length;i++){
    var oneChar=e.charAt(i);
    if((oneChar<'a'||oneChar>'z')&&(oneChar<'A'||oneChar>'Z')&&(oneChar<'0'||oneChar>'9')){
      return false;
    }
    }
  return true;
} 
//check phone
function isPhone(e){ 
  for(var i=0;i<e.length;i++){
    var oneChar=e.charAt(i);
    if(oneChar=="-"){
      continue;
    }      
    if(oneChar<'0'||oneChar>'9'){
      return false;
    }
    }
  return true;
}
//check Emial
function isEmail(e){  
 if(e.indexOf('@')!=-1&&e.indexOf('.')!=-1)
 return true;
 else
 return false; 
}
//check isLetter
function isLetter(e){
  for(i=0;i<e.length;i++){
    var oneChar=e.charAt(i);
    if(oneChar<'a'||oneChar>'z'){
      return false;
    }
  }
  return true;
} 
function isLetAndPosInt(e){
  for(i=0;i<e.length;i++){
    var oneChar=e.charAt(i);
    if((oneChar<'a'||oneChar>'z')&&(oneChar<'0'||oneChar>'9')){
      return false;
    }
    }
  return true;
} 
//check is longer
function isLonger(str,length){
 if(str.length<length)
   return false;
 else
   return true;
}            
//check is shorter
function isShorter(str,length){
 if(str.length>length)
   return false;
 else
   return true;
}            
        
//check empty
function isEmpty(e){
 var newString = trim(e);
 if(newString==null||newString=="")
   return true;
 else
   return false;
} 
//check empty
function isEmptyAny(formname,inputnames){
  var inputArray=inputnames.split(","); 
  var inputname,inputvalue; 
  for(i=0;i<inputArray.length;i++){ 
    //alert(inputArray[i]); 
    inputname=eval("document."+formname+"."+inputArray[i]);
    inputvalue=inputname.value;
    if(inputvalue==""){
      alert("�����Ϊ��!"); 
      inputname.focus();
      return true;
    }        
  }
  return false;
}
//check date
function isDate(str) //������YYYYMMDD
{
	if(str.length!=8){
		return false;
	}
	var y = str.substring(0,4);
	var m = str.substring(4,6)-1;
	var d = str.substring(6,8);
	
	var date = new Date(y,m,d);
	if((date.getFullYear()==y) && (date.getMonth()==m) && (date.getDate()==d) ){
		return true;
	}else{
		return false;
	}
}
function getLength(s){
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
   return len;
}

//strǰ��Ŀո��ȥ
function trim(str)
{
    var returnstr="";
    if(str == "")
       return "";
    var i = 0;
    for(i = 0;i<str.length;i++)
    {
        if(str.charAt(i) == ' ')
        {
            continue;
         }
        break;
    }
	//str = "" + str;
    str = str.substring(i,str.length);
    if(str =="")
       return "";
    for(i=str.length-1;i>=0;i--)
    {
        if(str.charAt(i)==' ')
        {
            continue;
         }
         break;
     }
     returnstr = str.substring(0,i+1);
     return returnstr;
}

	//�ж��Ƿ�������
    function isDigit(theNum) {
       var theMask = '0123456789';
       if (isEmpty(theNum)) return(false);
       else if (theMask.indexOf(theNum) == -1) return(false);
       return(true);
    }

	//�ж��Ƿ�������
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

	//�ж��Ƿ�С��
	 function isReal(theStr,intLen,decLen) { //(����ֵ,�������ֳ���,С�����ֳ���)
     	 var v_ret=0;
     	 var ret_mesg="ok";
     	 theStr = trim(theStr);
		 if (theStr.substring(0,1)=='-')   //����
              theStr = theStr.substring(1);

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
function stringCheck(name,e)
{
  var flag,flag1;
 	flag=hasQuotation(e);
 	if(flag==true)
 	  {
 	    alert(name+"���뺬�е����Ż�˫����,���������룡");
 	    return false;
 	  }
 	flag=hasBracket(e);
 	if(flag==true)
 	  {
 	    alert(name+"���뺬������,���������룡");
 	    return false;
 	  }
 	flag=hasSpecial(e);
 	if(flag==true)
 	  {
 	    alert(name+"���뺬�������ַ�@#$%^&*,���������룡");
 	    return false;
 	  }
 	  return true;
 } 
function hasQuotation(str){
	for(var i=0 ; i<str.length ; i++){	
 		var oneChar=str.charAt(i);
      	if(oneChar=='\''||oneChar=='\"'){	
   			return true;
  		}
 	}
 	return false;
}
function hasBracket(str){
	for(var i=0 ; i<str.length ; i++){	
 		var oneChar=str.charAt(i);
      	if(oneChar=='('||oneChar==')'||oneChar=='\<'||oneChar=='\>'){	
   			return true;
  		}
 	}
 	return false;
}
function hasSpecial(str){
	for(var i=0 ; i<str.length ; i++){	
 		var oneChar=str.charAt(i);
      	if(oneChar=='@'||oneChar=='#'||oneChar=='$'||oneChar=='%'||oneChar=='^'||oneChar=='&'||oneChar=='*'){	
   			return true;
  		}
 	}
 	return false;
}


  /**
   *�ж��������Ƿ���ڡ�<>&?���ַ�
   **/
	var re = /^[^'"()@$%^*<>&?]*$/;               // ����������ʽģʽ��
	function isValidInputText(src, fieldName) {
		var msg = fieldName + "�в����С�'������\"������(������)������@������$������%������^������*������<������>������&���͡�?���ַ���";
  		var arr = re.exec(src);
  		if (arr == null) {
  			alert(msg);
  			return false;
  		} else {
  			return true;
  		}
	}  