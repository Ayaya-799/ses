
var _status="";

var _status1="";
function query(){
if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
      else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      else if(isEmpty(form1.Apply_baseID.value)){
        alert("��ѡ�����ҵ��š�");
        return;
        
      }
      else if(isEmpty(form1.Apply_contractID.value)){
        alert("��ѡ��������ϡ�");
        return;
      }
      else{
        clearSessionData();  
        combineParameters();
        form1.Isquery.value='1';
        form1.oper.value="showQueryView";
        form1.submit();
      }
}

   
   
function add(){
	
   if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
      else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }else if(isEmpty(form1.Apply_baseID.value)){
        alert("��ѡ�����ҵ��š�");
        
        return;
      }else if(_status!=2&&_status!=3){
      	  alert("����״̬���Ǵ���ȷ�ϡ����������������");
      	  return;
      }
      else if(_status1==1)
      {
        alert("�ô��������������еĴ���չ����������δȷ�ϵĴ���չ��̨�ˣ�����������");
        return;
      }
      else{
        if(form1.addflag.value==1){
        alert("�ô����к���ҵ����������");
        return;
      }
        clearSessionData();  
        combineParameters();
        form1.Isquery.value='0';
        form1.Apply_contractID.value ="";
        form1.oper.value="showInsertView";
        form1.submit();
        
        }
}

function chooseContractID(){
   
    
     if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
       else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      else{
        var ts;        
        ts = window.showModalDialog(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.tfms.CA_util.RFApplyExtendQuery&enpcode="+form1.Apply_customerCode.value+"&LCCode="+encode(form1.LCCode.value)+"&area="+form1.Apply_sponsorBank.value+"&jiesuantype="+form1.ta270050003.value+"&baseID="+form1.Apply_baseID.value+"&height=400&width=300&hasDetailLink=true&time="+new Date(),window,"dialogWidth:330px;dialogHeight:450px;center:yes;help:no;status:no;resizable:no");
              
        if(ts!=null){
          form1.Apply_contractID.value = ts[2];
        }
      }
     
   }
   
   function f_changelccode(){
    if(form1.ta270050003.value!="1")
    {
     alert("������֤��ʽ����ѡ������֤�ţ�")
     form1.Apply_baseID.value="";
     return ;
    }
    if(isEmpty(form1.Apply_customerCode.value)){
    alert(" ������ͻ����룡 ");
    form1.Apply_customerCode.focus();
    return ;
	  }
	  if(isEmpty(form1.Apply_sponsorBank.value)){
	        alert("��ѡ��ҵ��������");
	        return ;
	  }else{
	  
	  var customerCode = form1.Apply_customerCode.value;		//�ͻ����� 
	  var ts = window.showModalDialog(encodeURL(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.TC.util.queryAllLC&ask=true&customerCode="+customerCode+"&areacode="+form1.Apply_sponsorBank.value+"&domain=international&zhuxiaoflag=0&height=410&hasDetailLink=true"),window,"dialogWidth:640px;dialogHeight:458px;center:yes;help:no;status:no;resizable:no");
	
	  if (ts != null){
	    if(ts[8]==""||ts[8]==0){
	       alert("������֤δ�������ѽ��壬����������ҵ��");
	       return;
	     }else
	     form1.LCCode.value = ts[1];
	   }
     }
    
  }
  
  function chooseBaseContractID(){
     if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
       else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      else if(isEmpty(form1.ta270050003.value)){
      	  alert("��ѡ����㷽ʽ��");
      	  return;
      }else if(form1.ta270050003.value=="1"&&isEmpty(form1.LCCode.value)){
      	   alert("��ѡ������֤�ţ�");
      	   return;
      }
      else{
        var ts = window.showModalDialog(encodeURL(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.TC.util.QueryRFNO_Extend&ask=true&customerCode="+form1.Apply_customerCode.value+"&areacode="+form1.Apply_sponsorBank.value+"&xyzcode="+form1.LCCode.value+"&jiesuantype="+form1.ta270050003.value+"&domain=international&zhuxiaoflag=0&width=500&height=410&hasDetailLink=true"),window,"dialogWidth:640px;dialogHeight:458px;center:yes;help:no;status:no;resizable:no");
	
	   if (ts != null){
	     form1.addflag.value=ts[8];
	     form1.Apply_baseID.value = ts[1];
	     form1.Apply_contractID.value="";
	     _status=ts[7];
	     _status1=ts[8];
	   }
      }
  }
  
  function changeCheckOut(){
    form1.LCCode.value="";
    form1.Apply_baseID.value ="";
    
  }