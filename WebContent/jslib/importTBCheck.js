var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
var parser=new ActiveXObject("microsoft.xmldom");
	parser.async="false";

function xgxx(){
      var ts = window.showModalDialog(basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.GA.GA_InterrelatedInfoOp&opAction=EnterPage&CustomerId="+form1.Apply_customerCode.value+"&opDataUnclear=true&time=" + (new Date), window,"dialogWidth:650px;dialogHeight:450px;center:yes;help:no;status:no;resizable:no;z-lock:yes;moveable:no;copyhistory:yes");
     
    }

//�ж��Ƿ����ѡ��֤������
function changekzbz(){
 if(form1.ta270019010.value=="1"){
 alert("��ؽ�������Ϊ����֤������ѡ��֤���֣�");
 return false;
 }
}
function return2(){
  window.location=form1.return2Url.value;
}
function scanpre()
{
 
  form1.operationName.value="icbc.cmis.IA.IAImagePreDefOp";
  form1.doWhat.value="OpClassfy";
  form1.qydm.value = form1.Apply_customerCode.value;
  
  form1.spzt.value  ="0";
  
  form1.ywbh.value =form1.Apply_contractID.value;
  if(form1.ta270019010.value=='1'){
   if(form1.ta270019011.value=='0')//����  
     form1.ywzl.value = "2020";
   else
     form1.ywzl.value = "8045"; 
  }
  if(form1.ta270019010.value=='2'){
     form1.ywzl.value = "8046";
  } 
   if(form1.ta270019011.value=='3'){
     form1.ywzl.value = "8047";
  }
  form1.dbfs.value=  form1.ta270019006.value;
        
    form1.OnlyAdd.value = "0";
    form1.returnUrl.value=basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.GeneralContentOp&opDataUnclear=true&isSessionClear=1&oper="+form1.pageMode.value;
 
    form1.submit();
}

//�ж��Ƿ�����޸Ļ�ɾ��
function checkflow(){	
	var applyState = form1.ta270019016.value;//������״̬
	var ywfqh  = form1.ta270019033.value;//ҵ������
	var curDq = form1.dqdm.value;
	form1._querypermute.value = "1";
	if(applyState=='1'||applyState=='2'||applyState=='4'){
		if(ywfqh==curDq){
			return true;
		}else{
			form1._querypermute.value = "0";
			alert("�������ҵ�����в��Ǳ��У�����ɾ�����޸ģ�");
			return false;
		}
	}else{
		form1._querypermute.value = "0";
		alert("�����벻�����롢������˻ص���״̬������ɾ�����޸ģ�");
		return false;
	}
	return true;	
}

 function f_v_image()//Ӱ��ɨ��
    {
      form1.enpcode.value =form1.Apply_customerCode.value;
      form1.applycode.value=form1.Apply_contractID.value;
      form1.oper.value = "query";
      form1.operationName.value = "icbc.cmis.ID.Image_display";
      form1.returnUrl.value=basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.GeneralContentOp&opDataUnclear=true&isSessionClear=1&oper="+form1.pageMode.value;

      form1.submit();
    }
    
    //�������Ϣ
    function goDJ(){
   if(form1.pageMode.value=="showInsertView"){
     alert("���ڲ�ѯģʽ�µ����ѯ�������Ϣ��");
     return;
   }else{
     
    form1.opDataUnclear.value="true";
    form1.Dj_ReturnUrl.value=basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.GeneralContentOp&opDataUnclear=true&isSessionClear=1&oper="+form1.pageMode.value;//���ص�url
    form1.operationName.value="icbc.cmis.FE.FE_LPIInterfaceOp";
    form1.opAction.value='20003';    
    form1.submit();
   }
 }
function zhAuthorize(str){//�ж�������Ȩ
	return true;
}

function deletetz(){//����У��
	return true;
}

function checkMoney(){
	var totalDueMoney = form1.totalDueMoney.value;
	if(trim(totalDueMoney)=="" || totalDueMoney==null||totalDueMoney=="null"){
		totalDueMoney = 0;
	}
	if(trimNum(form1.ta270019015.value)/1<trimNum(totalDueMoney)/1){
		alert("��ͬ����Ѻ����С�ڽ�ݽ���ܶ��ǰ��ݽ���ܶ�Ϊ��"+totalDueMoney);
		form1.ta270019015.focus();
		return false;
	}
	return true;
}
//У����������
function checkApplyDate()
{
 if (form1.ta270019004.value >form1.cmisdate.value)
   {
   alert("�������ڲ��ô���ϵͳ����:"+form1.cmisdate.value);
   form1.ta270019004.value="";
   return false;
 }
 return true;
}
//���ݴ��ʽ��þ�����ʽ
 function changejtdkfs()
 {
  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
  var parser=new ActiveXObject("microsoft.xmldom");
  parser.async="false";
  var tmp = "enpcode="+form1.ta270019001.value+"&dkfs="+form1.ta270019006.value+"&oper=querydkfs&time="+new Date();
  objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&xmlOutput=true&opDataUnclear=true&'+tmp,false);
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
  var seleobj=document.all.ta270019007;
  seleobj.length = 0;
  seleobj.options[0] = new Option("    ","");
  if(nodes.length > 0){
    for(i = 0; i < nodes.length ; i ++ ) {
      var node = nodes.item(i);
      seleobj.options[i+1] = new Option(node.getAttribute("dict_name"),node.getAttribute("dict_code"));
    }
  } else{
    seleobj.length = 0;
    seleobj.options[0] = new Option("      ","");
  }
}

 //���ݾ�����ʽ��ô�����ն� 
 function changedkfs(){
  if(isEmpty(form1.ta270019007.value)){
    alert("������ʽΪ��");
    form1.ta270019007.focus();
  }
  else{

        var tmp = "enpcode="+form1.ta270019001.value+"&dkfs="+form1.ta270019007.value+"&oper=creditventure&time="+new Date();
        objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&xmlOutput=true&opDataUnclear=true&'+tmp,false);
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
          form1.ta270019020.value = parser.documentElement.getAttribute("creditveture");
          document.all.plain_ta270019020.innerHTML=parser.documentElement.getAttribute("creditveture");
  }
}
//������ؽ�������,��ʼ����
function changexyzzl(){
  if  (form1.ta270019010.value=="1"){
  	form1.ta270019034.readOnly =true;
  	form1.ta270019034.value="";
  	form1.ta270019035.readOnly =true;
  	form1.ta270019035.value="";
  	form1.ta270019003.readOnly = true;
  	form1.ta270019030.value = "8045";
  	form1.ta270019009.value="0123002";
  }
  if(form1.ta270019010.value=="2") {
  	form1.ta270019034.readOnly =false;
	form1.ta270019035.readOnly=false;
  	form1.ta270019024.value='8046';
  	form1.ta270019003.readOnly = false;
  	form1.ta270019003.value="";
  	document.all.plain_ta270019012.innerHTML="�����";
  	form1.ta270019012.value="01";
  	form1.ta270019013.value = "";
    document.all.plain_ta270019013.innerHTML="";
    form1.ta270019009.value="0123002";    
  }
  if(form1.ta270019010.value=="3"){
   	form1.ta270019034.readOnly =false;
	form1.ta270019035.readOnly=false;
  	form1.ta270019024.value='8047';
  	form1.ta270019003.readOnly = true;
  	form1.ta270019003.value="";
  	document.all.plain_ta270019012.innerHTML="�����";
  	form1.ta270019012.value="01";
  	form1.ta270019013.value = "";
    document.all.plain_ta270019013.innerHTML="";
    form1.ta270019009.value="0123008";   
  } 
   var tmp = "dkkmCode="+form1.ta270019009.value+"&opAction=creditventure&time="+new Date();
   objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.TE.JK_RFQueryOp&xmlOutput=true&opDataUnclear=true&'+tmp,false);
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
   var ta270019009Show = parser.documentElement.getAttribute("creditveture"); 
   if(isEmpty(ta270019009Show)){
   	document.all.plain_ta270019009.innerHTML=form1.ta270019009.value;
   }else{
   	document.all.plain_ta270019009.innerHTML=ta270019009Show;
   }
}


//ѡ�����ҵ����
function ChooseLC()
{
	if(trim(form1.ta270019010.value)=="" || form1.ta270019010.value==null){
      	alert("����ѡ����ؽ�������!");
      	form1.ta270019010.focus();
      	return;
      }
       if(form1.ta270019010.value=='2'){
      	alert("ѡ����ؽ�������Ϊ���ڴ��գ�����ѡ�����ҵ����,���ֹ�����ISEE���ڴ���Ψһ��ʶ��");
      	form1.ta270019003.readOnly=false;
      	return;
      }
	 if(form1.ta270019010.value=='3'){
      	alert("ѡ����ؽ�������ΪTT���ʣ�����ѡ�����ҵ���ţ�");
      	form1.ta270019003.readOnly=true;
      	return;
      }
        var customerCode = form1.ta270019001.value;
        var ts = window.showModalDialog(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.TC.util.queryAllLC&customerCode="+form1.ta270019001.value+"&areacode="+form1.ta270019018.value+"&height=410&hasDetailLink=true&ask=true&domain=international&zhuxiaoflag=0",window,"dialogWidth:645px;dialogHeight:465px;center:yes;help:no;status:no;resizable:no");
        if (ts != null){        
        
        
        if (ts[8]==null||ts[8]=='')
        {
          alert("��ǰ����֤��δ��ʽ����,���ܽ��к���ҵ��Ĵ���.");
          form1.ta270019003.value ="";
          return ;
        }
        
        else if (ts[11]=='1')
        {
          alert("��ǰ����֤����Ŀ���µģ�����ʹ�ã�");
          form1.ta270019003.value ="";
          return ;
        }
        else {
        
          document.all.plain_ta270019012.innerHTML=ts[3];
          form1.ta270019003.value = ts[1];
          form1.ta270019013.value = ts[4];
          document.all.plain_ta270019013.innerHTML=ts[4];
          form1.ta270019012.value = ts[6];
          //form1.TA270019012SHOW.value=ts[3];
          var bar='';
          if(ts[7]==1){
            //bar='<select name="ta270019011" disabled><option value=0 selected>���� </option><option value=1>Զ�� </option>';
            form1.ta270019011.value='1';//Զ��
            document.all.plain_ta270019011.innerHTML='Զ��';          
          		form1.ywzl.value = "8045";
            	form1.ta270019024.value='8045';         
          }
          else{
          // bar='<select name="TA270019011" disabled><option value=0 >���� </option><option value=1 selected>Զ�� </option>'; 
          	form1.ta270019011.value='0';//
          	document.all.plain_ta270019011.innerHTML='����';
          	form1.ywzl.value = "2020";
           	form1.ta270019024.value='2020';  
           }
                
          //form1.TA270019012SHOW.value=ts[6];
        }
      }
      
  }
  
  /*********************************************************************************/
  /*ѡ����������*/
    function chooserate(){
      
      if(isEmpty(form1.ta270019014.value)){
        alert("����ѡ������Ѻ����֣�");
        form1.ta270019014.focus();
        return;
      } 
      if(isEmpty(form1.ta270019004.value)){
        alert("���������������ڣ�");
        form1.ta270019004.focus();
        return;
      }      
      var   MoneyType= form1.ta270019014.value;              //--�������
      var   BorrowType= "10";                           //--�������
      var   RateTypeCode= form1.ta270019026.value;     //--��������
      var   Day_Begin= "";        //--��ʼ��
      var   Day_End= "";          //--������
      var   Day_BeginUse= form1.ta270019004.value;     //--������
      var   flag= "0";                                  //--�����־(0������ 1����ʽ)
	��var ts = window.showModalDialog(basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.Util_ChooseRateTypeOp&opAction=20001&opDataUnclear=true&MoneyType="+MoneyType+"&RateTypeCode="+RateTypeCode+"&Day_BeginUse="+Day_BeginUse+"&flag="+flag+"&time="+(new Date()),window,"dialogWidth:630px;dialogHeight:360px;center:yes;help:no;status:yes;resizable:no");
	  
	  if(ts!=null){
	    form1.lab_ta270019026.value = ts[0]; //������������
	    form1.ta270019027.value = ts[1]; //��׼����
	    document.all.plain_ta270019027.innerHTML=ts[1];
	    form1.ta270019026.value = ts[2]; //��������
	    form1.ta270019032.value = ts[3];//���ʴ���
	    if(ts!=null&&ts[2]=="32"){
   	    form1.ta270019028.value = "1";//Э��̶�����ֻ���Ǹ�������
   	    changefdlx1();
       }
	  }
  }
  
   
  /*�仯����������Ӧ*/
   function changefdlx(){
      if(form1.ta270019028.value=="0"){
        form1.ta270019029.value = "";
        ds.style.display = "none";
        dc.style.display = "";
      }
      else{
        form1.ta270019029.value = "";
        ds.style.display = "";
        dc.style.display = "none";
      }
    }
/*************************************************************************/
function changefdlx1(){
      if(form1.ta270019028.value=='0'){
        document.all.div_140030.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';
        form1.ta270019029.value="";}
       else{
        document.all.div_140030.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';
         form1.ta270019029.value="";}
}
    function changeyqlv(){
      if(form1.ta270019030.value=='0'){
        document.all.div_150030.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';
         form1.ta270019031.value="";}
       else{
        document.all.div_150030.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';
         form1.ta270019031.value="";}
    }   
/************************************************************/
  
  function chooseDDCode()//��ȡ������,���������
  {
  if(form1.ta270019010.value=="1"){ 
	  	var ts = window.showModalDialog(encodeURL(basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.TC.operation.LCDocOp&opDataUnclear=true&doflag=queryDocSeq&LCCode="+form1.ta270019003.value+"&customerCode="+form1.ta270016001.value+"&time="+(new Date)),window,"dialogWidth:400px;dialogHeight:300px;center:yes;help:no;status:no;resizable:no");
	    if (ts != null){
	      	form1.ta270016017.value = ts[1];
	     	form1.ta270016006.value = ts[2];
	     	document.all.plain_ta270016006.innerHTML=ts[2];	
	     	form1.ta270016007.value=ts[3];
	     	document.all.plain_ta270016007.innerHTML=ts[3];	     	
	    }
  }else{
		alert("��ؽ�������Ϊ������֤������ѡ�������ţ�");
	   form1.ta270016017.value = '*';
	   form1.ta270016007.value='0';
	   form1.ta270016005.value='01';
	   form1.ta270016006.value='0';
	   document.all.plain_ta270016005.innerHTML='�����';
	   document.all.plain_ta270016006.innerHTML='0';
	   form1.ta270016007.value='0';	
	   document.all.plain_ta270016007.innerHTML='0';   
  }
 }
  
  function checkJJcount(){
  if (form1.jjcount.value=='0'){
  alert("���������,��������¼��һ����ݲ��ܱ���!");
  return true;
  
   }
  }

//�õ������ʲ����ն�
function changedkfx()
{
  if (trim(form1.ta270019004.value)==""||form1.ta270019004.value==null||isDate(form1.ta270019004.value)==false)
  {
    alert ("����������ȷ��������YYYYmmdd!");
    form1.ta270019004.focus();
    return false;
  }
  else if (checkApplyDate()==false) return false;
         var tmp = "enpcode="+form1.ta270019001.value+"&sqsj="+form1.ta270019004.value+"&oper=capitalventure&time="+new Date();
         objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&xmlOutput=true&opDataUnclear=true&'+tmp,false);
         objHTTP.send();
         var xml = objHTTP.responseText;
        if(!parser.loadXML(xml))
        {
          return false;
        }
        error = parser.getElementsByTagName("error");
       if(error.length > 0)
       {
           alert(error.item(0).text);
         	return;
       }
       
      form1.ta270019021.value = parser.documentElement.getAttribute("capitalventure");
      document.all.plain_ta270019021.innerHTML=parser.documentElement.getAttribute("capitalventure");

}
//�жϳ���
function checkLength_szj(v,length){
 var s = v;
 var l = length;
 var len = 0;
   for(i=0;i<s.length;i++){
     var c = s.substr(i,1);
     var ts = escape(c);
     if(ts.substring(0,2) == "%u") {
      len+=2;
     } else {
      	if(ts.substring(0,3) == "%D7"){
      	     len+=2;
     	}      	
	else{
 	     len+=1;
	}
     }
   }
   if(l>=len){
     return true;
   }
   else{
	var aaa=Math.floor(l/2);
	alert("���ҵ���Ų���ֵ���������"+l+"���ַ���"+aaa+"�����֡�");
	return false;
   }
}
  /*���ҳ����ĸ����ֶε�����*/
  function checkForm_Ht()
  {
    
    if (trim(form1.ta270019004.value)=="" || form1.ta270019004.value==null|| isDate(form1.ta270019004.value)==false)
  	{
	    alert ("����������ȷ��������YYYYmmdd!");
	    form1.ta270019004.focus();
	    return false;
    }
    if(!checkApplyDate()){
    	form1.ta270019004.focus();
    	return false;
    }
    if (trim(form1.ta270019010.value)=="" || form1.ta270019010.value==null )
   	{
	     alert ("����ѡ����ؽ�������!");
	     form1.ta270019010.focus();
	     return false;
   	}
   	if(form1.ta270019010.value!="1"){
   		
		   	 if (isEmpty(form1.ta270019034.value)||isDate(form1.ta270019034.value)==false)
		    {
		        alert("��������ȷ�ĺ�ͬ��Ч��yyyyMMdd!");
		        form1.ta270019034.focus();
		        return false;
		    }
		   if (isEmpty(form1.ta270019035.value)||isDate(form1.ta270019035.value)==false)
		  {
		        alert("��������ȷ�ĺ�ͬ������yyyyMMdd!");
		        form1.ta270019035.focus();
		        return false;
		   }
		   if(form1.ta270019035.value < form1.cmisdate.value)
   			 {
               alert("��ͬ�����ձ�����ڵ�ǰʱ��!"+form1.cmisdate.value);
               form1.ta270019035.focus();
               return false;
   			}
		  if (dateBetween(form1.ta270019035.value,form1.ta270019034.value)<0)
		   {
		        alert("��ͬ�����ղ��ó��ں�ͬ��Ч��!");
		        form1.ta270019035.focus();
		        return false;
		  }
   	}
   	if(form1.ta270019010.value=="1"){
   		 	 if(!isEmpty(form1.ta270019034.value))
		    {
		        alert("��ؽ�������Ϊ����֤�����������ͬ��Ч�գ�");		        
		        form1.ta270019034.value="";
		        form1.ta270019034.readOnly=true;
		        return false;
		    }
		   if (!isEmpty(form1.ta270019035.value))
		  {
		        alert("��ؽ�������Ϊ����֤�����������ͬ�����գ�");
		        form1.ta270019035.value="";
		        form1.ta270019035.readOnly=true;
		        return false;
		   }  	
   	}
    if (trim(form1.ta270019006.value)=="" || form1.ta270019006.value==null )
    {
        alert ("����ѡ����ʽ!");
        form1.ta270019006.focus();
        return false;
    }

 	if (trim(form1.ta270019007.value)==""||form1.ta270019007.value==null)
  	{
    	alert("����д������ʽ!");
    	form1.ta270019007.focus();
    	return false;
  	}
    
    if(isEmpty(form1.lab_ta270019026.value)){
	    alert("��ѡ���������࣡");    
	    form1.lab_ta270019026.focus();
	    return false;
    }
	    if(isEmpty(form1.ta270019028.value)){
	    alert("��ѡ���������ʸ������ͣ�");
	    form1.ta270019028.focus();
	    return false;
	    }
	    if(form1.ta270019026.value=="32"){
	    	if(form1.ta270019028.value=="0"){
	    		alert("��������ΪЭ��̶����ʣ�ֻ��ѡ�񸡶�����");
	    		form1.ta270019028.focus();
	    		return false;
	    	}
	    }
	    if(form1.ta270019028.value=="0"){
	    	 if(isEmpty(form1.ta270019029.value)){
		    alert("��ѡ���������ʸ������Σ�");
		    form1.ta270019029.focus();
		    return false;
		    }
	    }else{
		    if(isEmpty(form1.ta270019029.value)){
		    alert("��ѡ���������ʸ���ֵ��");
		    form1.ta270019029.focus();
		    return false;
		    }
	    }
	
	    if(isEmpty(form1.ta270019030.value)){
	    alert("��ѡ���������ʸ������ͣ�");
	    form1.ta270019030.focus();
	    return false;
	    }
	    if(form1.ta270019030.value=="0"){
	     	if(isEmpty(form1.ta270019031.value)){
		    alert("��ѡ���������ʸ������Σ�");
		    form1.ta270019031.focus();
		    return false;
		    }		    
	    }else{
		    if(isEmpty(form1.ta270019031.value)){
		    alert("��ѡ���������ʸ���ֵ��");
		    form1.ta270019031.focus();
		    return false;
		    }
    	}
        if(form1.ta270019028.value=="1"){
		   if(trimNum(form1.ta270019029.value)<-10||trimNum(form1.ta270019029.value)>10){
		    alert("�������ʵķ�Χ��-10��10,��������");
		    return false;
		   }
		 }
		 
		 if(form1.ta270019028.value=="0"){
		   if(trimNum(form1.ta270019029.value)<=-100||trimNum(form1.ta270019029.value)>=900){
		    alert("�������ʸ������β���С�ڵ���-100,���ڵ���900,��������");
		    return false;
		   }
		 }
		 
		 if(form1.ta270019030.value==0){
			 if(form1.ta270019031.value<=-100||form1.ta270019031.value>=900){
			   alert("���������ʸ�������Ϊ��������ʱ,���ڸ���ֵ����С�ڵ���-100,���ڵ���900��");
			   form1.ta270019031.focus();
			   return false;
			 }
		 }
		 else
		 {
		   if(form1.ta270019031.value<=-100||form1.ta270019031.value>=100){
		   alert("���������ʸ�������Ϊ��������ʱ,���ڸ���ֵ����С�ڵ���-100,���ڵ���100��");
		   form1.ta270019031.focus();
		   return false;
		 }
 	 	}
    
	    //code add by zhengzezhou 2004-6-10 ҳ�������֤���� ���� Զ�� Ҳ��Ϊ������
	      if ((trim(form1.ta270019011.value)==""||form1.ta270019011.value==null)&&trim(form1.ta270019010.value)=='1')
	      {
	        alert("��ѡ������֤����!");
	        form1.ta270019011.focus();
	        return false;
	      }
	    if (trim(form1.ta270019008.value)=="" || form1.ta270019008.value==null )
    {
	    alert ("����ѡ���������!");
	    form1.ta270019008.focus();
	    return false;
    }
		if(form1.ta270019010.value=="1"){
	      if (trim(form1.ta270019003.value)=="" || form1.ta270019003.value==null )
	      {
	        alert ("����ѡ�����ҵ����!");
	        form1.ta270019003.focus();
	        return false;
	      }
	     }
	     if(form1.ta270019010.value=="2"){
	      if (trim(form1.ta270019003.value)=="" || form1.ta270019003.value==null )
	      {
	        alert ("���ҵ���ű�������ISEE���ڴ���Ψһ��ʶ!");
	        form1.ta270019003.focus();
	        return false;
	      }
	     }
	     if(form1.ta270019010.value=="2"){
	     
	     	if (trim(form1.ta270019003.value)!="" && form1.ta270019003.value!=null )
	    	{
		    	if(!checkLength_szj(form1.ta270019003.value,30)){
		    		form1.ta270019003.focus();
		    		return false;
		    	}
	    	}
	     } 
	     if(form1.ta270019010.value=="1"){
	      if (trim(form1.ta270019013.value)==""||form1.ta270019013.value==null)
	      {
	        alert("����д��֤���!");
	        form1.ta270019013.focus();
	        return false;
	      }
	      else  if  ((msg=isReal(trimNum(form1.ta270019013.value),14,2))!="ok")
	      {
	        alert ("��д���뿪֤������:"+msg);
	        form1.ta270019013.focus();
	        return false;
	      }
	      }
	      if (trim(form1.ta270019015.value)=="" || form1.ta270019015.value==null )
	      {
	        alert("������д����Ѻ����!");
	        form1.ta270019015.focus();
	        return false;
	      }
	      else  if  ((msg=isReal(trimNum(form1.ta270019015.value),14,2))!="ok")
	      {
	        alert ("��д����Ѻ�������:"+msg);
	        form1.ta270019015.focus();
	        return false;
	      }
	      if(!checkMoney()){
  			return false; 
  		  }
    	 if(trim(form1.ta270019015.value)!="" && form1.ta270019015.value!=null){
	      	
	      }	
    
    //code modify by zhengzezhou 2004-6-10 �������δ���롰����Ѻ���ʱ���ύ����ϵͳ��ʾ��������дѺ���
      if (trim(form1.ta270019013.value)!="" && form1.ta270019013.value!=null )
      {
        if  ((msg=isReal(trimNum(form1.ta270019013.value),14,2))!="ok")
        {
          alert ("��д��֤������:"+msg);
          form1.ta270019013.focus();
          return false;
        }
      }
      if (form1.ta270019006.value=="20" && isEmpty(form1.ta270019019.value)){
        alert ("��֤���ʽ��,��ѡ�񵣱���ʽ.")
            form1.ta270019019.focus();
        return false;
      }
      if (form1.ta270019006.value!="20" && !isEmpty(form1.ta270019019.value)){
        alert ("�����ʽ���Ǳ�֤ʱ,����ѡ�񵣱���ʽ.")
            form1.ta270019019.focus();
        return false;
      }
      if (form1.oper.value=="store" || form1.oper.value=="new"){
        //���Կ���
        //if(!call_apply_control()) return false;
      }
      //if(chechstat()==false) return; �ж��Ƿ�������̣���ʱûʵ��
	    form1.ta270019013.value=trimNum(form1.ta270019013.value);
	    form1.ta270019015.value=trimNum(form1.ta270019015.value);        
	    
	    form1.ta270019029.value=trimNum(form1.ta270019029.value);
	    form1.ta270019031.value=trimNum(form1.ta270019031.value);
	                  
      return true;

  }
  //��ʼ������
function permute(){

if(form1.isJieju.value=='0'){	
	if(form1.ta270019028.value=='0'){
        document.all.div_140030.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';
    }else{
        document.all.div_140030.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';
    }
	if(form1.ta270019030.value=='0'){
        document.all.div_150030.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';
    }else{
        document.all.div_150030.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';        
    }
    if(form1.ta270019010.value=='2'){
    	form1.ta270019003.readOnly=false;
    }
    if(form1.ta270019010.value=='1'){
    	form1.ta270019034.readOnly=true;
    	form1.ta270019035.readOnly=true;
    }           
}
if(form1.isJieju.value=='7'){
	if(form1.ta270016010.value=='0'){
        document.all.div_110010.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';        
    }
     else{
        document.all.div_110010.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';        
     }
     if(form1.ta270016019.value=='0'){
        document.all.div_150010.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';
     }
     else{
        document.all.div_150010.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';
     }
}
if(form1.isJieju.value=='4'){
	if(form1.ta270016010.value=='0'){
        document.all.div_110010.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';        
    }
     else{
        document.all.div_110010.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';        
     }
     if(form1.ta270016019.value=='0'){
        document.all.div_150010.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';
     }
     else{
        document.all.div_150010.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';
     }
}

}

/***************************�����ǽ�ݵ�JS**************************************/

/*���ڱȽϴ�С��С�����ʾstr1<str2*/
function dateBetween(str1,str2)
{
  var x = str1.substring(0,4)/1;
  var y = str1.substring(4,6)/1-1;
  var z = str1.substring(6,8);
  var date1 = new Date(x,y,z);
  var x = str2.substring(0,4)/1;
  var y = str2.substring(4,6)/1-1;
  var z = str2.substring(6,8);
  var date2 = new Date(x,y,z);
  return (date1-date2)/(1000*3600*24);
}

 /*ѡ����������*/
    function chooserate2(){
      
      if(isEmpty(form1.ta270019014.value)){
        alert("����ѡ������Ѻ����֣�");
        return;
      } 
      if(isEmpty(form1.ta270019004.value)){
        alert("���������������ڣ�");
        return;
      }
     
      if(form1.ta270016013.value=="0"){
      	alert("��Ϣ����Ϊ����Ϣ������ѡ���������࣡");
      	form1.ta270016013.focus();
      	return;
      }      
      var   MoneyType= form1.ta270019014.value;              //--�������
      var   BorrowType= "10";                           //--�������
      var   RateTypeCode=form1.ta270016014.value;     //--��������
      var   Day_Begin=form1.ta270016008.value;        //--��ʼ��
      var   Day_End= form1.ta270016009.value;          //--������
      var   Day_BeginUse=form1.ta270016008.value;     //--������
      var   flag= "1";                                  //--�����־(0������ 1����ʽ)
	��var ts = window.showModalDialog(basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.Util_ChooseRateTypeOp&opAction=20001&opDataUnclear=true&MoneyType="+MoneyType+"&RateTypeCode="+RateTypeCode+"&Day_BeginUse="+Day_BeginUse+"&flag="+flag,window,"dialogWidth:630px;dialogHeight:360px;center:yes;help:no;status:yes;resizable:no");
	  
	  if(ts!=null){
	    form1.lab_ta270016014.value = ts[0]; //������������
	    form1.ta270016011.value = ts[1]; //��׼����
        document.all.plain_ta270016011.innerHTML=ts[1];
	    form1.ta270016014.value = ts[2]; //��������
	    //form1.ta200251054.value = ts[3];//���ʴ���
	    if(ts!=null&&ts[2]=="32"){
   	    form1.ta270016010.value = "1";//Э��̶�����ֻ���Ǹ�������
   	    changefdlx2();
       }
	  }
  }
  
//ѡ��������������
 function changefdlx2(){
      if(form1.ta270016013.value=="0"&&form1.ta270016010.value=="1"){
        alert("����Ϣʱ��������������ֻ��ѡ�񸡶����Σ�");
        form1.ta270016010.value="0";
        return;
      }
      if(form1.ta270016010.value=='0'){
        document.all.div_110010.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';
        form1.ta270016012.value="";
        }
       else{
        document.all.div_110010.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';
        form1.ta270016012.value="";
        }
    }
    
//ѡ�����ڸ�������
 function changeyqlv2(){
      if(form1.ta270016013.value=="0"&&form1.ta270016019.value=="1"){
        alert("����Ϣʱ�����ڸ�������ֻ��ѡ�񸡶����Σ�");
        form1.ta270016019.value="0";
        return;
      }
      if(form1.ta270016019.value=='0'){
        document.all.div_150010.innerHTML='�������ʸ�������(%)<font color="#FF0000">*</font>';
        form1.ta270016018.value="";
        }
       else{
        document.all.div_150010.innerHTML='�������ʸ���ֵ<font color="#FF0000">*</font>';
        form1.ta270016018.value="";
        }
 }  
   
//��ü�Ϣ����
function changeta200071012(){
      if(form1.ta270016013.value=="0"){
       // document.all.rateChoose.style.display="none";
       	form1.jxzqFlag.value="0";
        form1.ta270016010.value="0";
        form1.ta270016010.readOnly=true;       
        form1.ta270016012.readOnly=true;
       // form1.ta270026014Name.value="��������������";
        form1.lab_ta270016014.value="��������������";
        form1.ta270016011.value="0";
        document.all.plain_ta270016011.innerHTML='0';
        form1.ta270016014.value="30000";
        if(form1.ta270016019.value!="0"){
        form1.ta270016019.value="0";
        changefdlx2();
        }
        form1.ta270016012.value="0";
        form1.ta270016018.value="0";
        form1.ta270016018.readOnly=true;
      }
      else{
      	if(form1.jxzqFlag.value=="0"){
      	form1.jxzqFlag.value="1";
        form1.ta270016010.value="";
        form1.ta270016010.disabled=false;
        form1.ta270016012.value="";
        form1.ta270016012.readOnly=false;
        //form1.ta270026014Name.value="";
        form1.lab_ta270016014.value="";
        form1.ta270016011.value="";
        document.all.plain_ta270016011.innerHTML='';
        form1.ta270016014.value="";
        //form1.ta200071020.value = "";
        form1.ta270016018.readOnly=false;
       } 
      }
    }

/*
���Ϊ���ж�
*/
    function checkForm_Jj()
    {
    
    if (form1.isJieju.value=='3'){
      if (trimNum(form1.ta270016004.value)>(form1.ta270019015.value-form1.sumDueMoney.value) )
 {
 alert ("���Ľ��Ѻ�����ܶ��Ѿ�������ͬѺ����.�ú�ͬ�Ľ��Ѻ�����Ϊ"+(form1.ta270019015.value-form1.sumDueMoney.value));
 
 return false;
 
 }
 }else{
 
 if (trimNum(form1.ta270016004.value)>((form1.ta270019015.value-form1.sumDueMoney.value)*1+(form1.ta270019015_all.value)*1) ){
 
 alert ("���Ľ��Ѻ�����ܶ��Ѿ�������ͬѺ����.�ú�ͬ�Ľ��Ѻ�����Ϊ"+((form1.ta270019015.value-form1.sumDueMoney.value)*1+(form1.ta270019015_all.value)*1));
 
 return false;
 
 }
 
 
 }
 
      
      if (isEmpty(form1.ta270016004.value))
      {
        alert("Ѻ���������Ϊ��!");
        form1.ta270016004.focus();
        return false;
      }
      
      if(trimNum(form1.ta270016004.value)<=0){
        alert("Ѻ���������С�ڵ���0!");
        form1.ta270016004.focus();
        return false;
      }
 //     if(form1.isMo.value=='1'){
 //       if (checkDueBillAmout()==false) return false;
  //    }
      if(isEmpty(form1.ta270016013.value)){
        alert("��Ϣ���ڲ���Ϊ�գ�");
        return false;
      }
      
 //     if (isEmpty(form1.ta270016017.value))
 //     {
  //      alert("Ѻ�㵽����Ų���Ϊ��!");        
  //      return false;
  //    }
      if (isEmpty(form1.ta270016006.value))
      {
        alert("Ѻ�㵥�ݽ���Ϊ�գ�����ѡ�񵽵����!");
        //form1.ta270016006.focus();
        return false;
      }
      if (isEmpty(form1.ta270016008.value)||isDate(form1.ta270016008.value)==false)
      {
        alert("��������ȷ��Ѻ����yyyyMMdd!");
        form1.ta270016008.focus();
        return false;
      }
     if(form1.ta270016008.value < form1.cmisdate.value)
      {
        alert("Ѻ���ձ�����ڵ�ǰʱ��!");
        return false;
     }
      if (isEmpty(form1.ta270016009.value)||isDate(form1.ta270016009.value)==false)
      {
        alert("��������ȷ��Ѻ��黹��yyyyMMdd!");
        form1.ta270016009.focus();
        return false;
      }
      if (dateBetween(form1.ta270016009.value,form1.ta270016008.value)<0)
      {
        alert("Ѻ���ղ��ó���Ѻ��黹��!");
        form1.ta270016009.focus();
        return false;
      }
      
 if (dateBetween(form1.ta270016009.value,form1.ta270016008.value)>form1.controlC.value)
   {
  alert("Ѻ��黹�ղ��ó���Ѻ����"+form1.controlC.value+"��!");
   form1.ta270016009.focus();
   return false;
 }
 if (isEmpty(form1.ta270016011.value))
 {
   alert("Ѻ�����ʲ���Ϊ��!");
   form1.ta270016011.focus();
   return false;
 }
  if (isEmpty(form1.ta270016019.value))
 {
   alert("���ڸ������Ͳ���Ϊ��!");
   form1.ta270016019.focus();
   return false;
 }
 if(form1.ta270016019.value=="0"){
 	if (isEmpty(form1.ta270016018.value))
	 {
	   alert("���ڸ������β���Ϊ��!");
	   form1.ta270016018.focus();
	   return false;
	 }
 }else{
	 if (isEmpty(form1.ta270016018.value))
	 {
	   alert("���ڸ���ֵ����Ϊ��!");
	   form1.ta270016018.focus();
	   return false;
	 }
 }
 form1.ta270016018.value = trimNum(form1.ta270016018.value);
 
 if(form1.ta270016019.value==0){
 if(form1.ta270016018.value<=-100||form1.ta270016018.value>=900){
   alert("���������ʸ�������Ϊ��������ʱ,���ڸ���ֵ����С�ڵ���-100,���ڵ���900��");
   form1.ta270016018.focus();
   return false;
 }
 }else{
   if(form1.ta270016018.value<=-100||form1.ta270016018.value>=100){
   alert("���������ʸ�������Ϊ��������ʱ,���ڸ���ֵ����С�ڵ���-100,���ڵ���100��");
   form1.ta270016018.focus();
   return false;
 }
 }
  if (isEmpty(form1.ta270016010.value))
 {
   alert("�������Ͳ���Ϊ��!");
   form1.ta270016010.focus();
   return false;
 }
 if(form1.ta270016010.value=="0"){
 	if (isEmpty(form1.ta270016012.value))
	 {
	   alert("�������β���Ϊ��!");
	   form1.ta270016012.focus();
	   return false;
	 }
 }else{
	 if (isEmpty(form1.ta270016012.value))
	 {
	   alert("����ֵ����Ϊ��!");
	   form1.ta270016012.focus();
	   return false;
	 }
 }

 
 var a = 0;
 form1.ta270016012.value = trimNum(form1.ta270016012.value);
 a = form1.ta270016012.value/1;
 if(form1.ta270016010.value=="1"){
   if(a<-10||a>10){
    alert("�������ʵķ�Χ��-10��10,��������");
    return false;
   }
 }
 
 if(form1.ta270016010.value=="0"){
   if(a<=-100||a>=900){
    alert("�������ʸ������β���С�ڵ���-100,���ڵ���900,��������");
    return false;
   }
 }
 if (form1.ta270019010.value=="1"){
   if (isEmpty(form1.ta270016007.value))
   {
     alert("������֤���㷽ʽ��,����������ȷ��Ѻ�㵽�������ձ�ʶ!");
     form1.ta270016007.focus();
     return false;
   }
   if (isEmpty(form1.ta270016017.value))
   {
     alert("������֤���㷽ʽ��,����ѡ��Ѻ�㵽�����!");

     return false;
   }
 }
 //code add by zzz 2004-5-8
   if (form1.ta270016011.value <0) {
     alert("Ѻ�����ʲ���С��0��");
     return false;
   }
   if(form1.ta270016014.value=='32'){
     if(form1.ta270016010.value=='0'){
      alert("������������Э��̶�����,ֻ��ѡ�񸡶�����!");
      return false;
     }
     trimNum(form1.ta270016012.value);
     if(form1.ta270016012.value<=0)
     {
       alert("����ֵ����С�ڵ���0��");
       return false;
     }
  }
      return true;
}

    /*
       ���ý����ϸ����ӿ�
    */  
    function calTerm(){
      if(isEmpty(form1.ta270016011.value)){
        alert("��ѡ���������࣡");
        return;
      }
      if(isEmpty(form1.ta270016004.value)){
        alert("������Ѻ�������");
        return;
      }
      if(!isDate(form1.ta270016008.value)){
        alert("��������ȷ�ķſ����ڣ�");
        return;
      }
      if(!isDate(form1.ta270016009.value)){
        alert("��������ȷ�ĵ������ڣ�");
        return;
      }
      var rate =0;
      if(form1.ta270016010.value==0){
      	  rate=Math.round(trimNum(form1.ta270016011.value)*(1+trimNum(form1.ta270016012.value)/100)*1000000)/1000000;
      }else{
      	  rate=trimNum(form1.ta270016011.value)/1+trimNum(form1.ta270016012.value)/1;
      }     
      var ts = window.showModalDialog(basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FG.FG_TrialDuebillAmorDetailOp&opAction=50001&currType="+form1.ta270019014.value+"&amout="+trimNum(form1.ta270016004.value)+"&opDataUnclear=true&grantDate="+form1.ta270016008.value+"&matuDate="+form1.ta270016009.value+"&rate="+rate,window,"dialogWidth:630px;dialogHeight:360px;center:yes;help:no;status:no;resizable:no");
    }

function candeljj(){
	return true;
}
//��ӽ�ݸ�����Ϣ
function append1() {
  var flag;//0�����޸ģ�1�����޸�
  if(form1.isJieju.value == '7'){
    flag='0';
  }else
    flag='1';
  if(form1.pageMode.value!='showQueryView'){
    var items="";
    //ȡ���ڵ�����input�� 
    items = document.getElementsByTagName("input");
    for(ii=0;ii<items.length;ii++){
      if(items[ii].isAmount=='yes')
        items[ii].value=trimNum(items[ii].value);
      else
        items[ii].value=trim(items[ii].value);    
    }
    
    submitparameter("form1","17");
  }
  var returnurl=basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.GeneralContentOp&opDataUnclear=true';
  returnurl+="&oper="+form1.pageMode.value;
  appendTab2(flag,returnurl,form1.ta270016003.value);

}


  function appendTab2(actionMode,returnUrl,loanCode){
    form1.opAction.value = "60001";
    form1.opDataUnclear.value="true";
    form1.operationName.value="icbc.cmis.FG.FG_DirectionOp";
    form1.url.value=returnUrl;
    form1.pretend.value=form1.Apply_pesudoID.value;
    form1.flag.value=actionMode;
    
    form1.magkind.value="01";
    form1.step.value=form1.Apply_stage.value;
    form1.areacode.value=form1.dqdm.value;
    form1.enpcode.value= form1.Apply_customerCode.value;
    form1.enpname.value=form1.Apply_customerName.value;
    if(form1.Apply_contractID.value =="")
    form1.tradecode.value='����';
    else
    form1.tradecode.value=form1.Apply_contractID.value;
    if(loanCode!=""){
    form1.tradecode.value=form1.tradecode.value+'-'+loanCode;
    form1.pretend.value=form1.pretend.value+'-'+loanCode;
    }
    form1.submit();  
  }	








  