// ImportDaiFu.js

function query(){
    
    if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
   }
   if(isEmpty(form1.Apply_sponsorBank.value)){
	    alert("��ѡ��ҵ��������");
	    return;
   }
   if(form1.Apply_kind.value=="24"){
	    if(isEmpty(form1.LCCode.value)){
	    alert("��ѡ������֤�š�");
	    form1.LCCode.focus();
	    return;
      }
   }else{
        if(isEmpty(form1.ta270050003.value)){
        alert("��ѡ����㷽ʽ��");
        form1.ta270050003.focus();
        return;
      }
   }
   if(isEmpty(form1.Apply_contractID.value)){
	    alert("��ѡ��������ϣ�");
	    return;
   }
    clearSessionData();  
    combineParameters();
    form1.oper.value="showQueryView";
    form1.Isquery.value='1';
    form1.submit();
     
}

   
   
function add(){
   combineParameters();
   if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
   }
   if(isEmpty(form1.Apply_sponsorBank.value)){
	    alert("��ѡ��ҵ��������");
	    return;
   }
   if(form1.Apply_kind.value=="24"){
	    if(isEmpty(form1.LCCode.value)){
	    alert("��ѡ������֤�š�");
	    form1.LCCode.focus();
	    return;
      }
   }else{
        if(isEmpty(form1.ta270050003.value)){
        alert("��ѡ����㷽ʽ��");
        form1.ta270050003.focus();
        return;
      }
   }
    clearSessionData();  
    form1.Isquery.value='0';
    form1.Apply_contractID.value ="";
    form1.oper.value="showInsertView";
    form1.submit();
        
        
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
        if(form1.Apply_kind.value=="24"){
        if(isEmpty(form1.LCCode.value)){
        alert("��ѡ������֤�š�");
        form1.LCCode.focus();
        return;
      }
        ts = window.showModalDialog(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.tfms.CA_util.RFApplyQuery&enpcode="+form1.Apply_customerCode.value+"&LCCode="+encode(form1.LCCode.value)+"&area="+form1.Apply_sponsorBank.value+"&applykind="+form1.Apply_kind.value+"&height=400&width=500&hasDetailLink=true&time="+new Date(),window,"dialogWidth:500px;dialogHeight:450px;center:yes;help:no;status:no;resizable:no");
       
       }
        else{
        if(isEmpty(form1.ta270050003.value)){
        alert("��ѡ����㷽ʽ��");
        form1.ta270050003.focus();
        return;
      }
        ts=window.showModalDialog(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.tfms.CA_util.RFApplyQuery&enpcode="+form1.Apply_customerCode.value+"&LCCode="+encode(form1.ta270050003.value)+"&area="+form1.Apply_sponsorBank.value+"&applykind="+form1.Apply_kind.value+"&height=400&width=500&hasDetailLink=true&time="+new Date(),window,"dialogWidth:500px;dialogHeight:450px;center:yes;help:no;status:no;resizable:no");
        
        }
        if(ts!=null){
          form1.Apply_contractID.value = ts[2];
        }
      }
     
   }
   
   function f_changelccode(){
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
	     form1.Apply_contractID.value="";
	   }
     }
    
  }
  
  function changeCheckOut(){
   
    if(form1.ta270050003.value==1){
      alert("������֤���½��ڴ�������ѡ������֤��ʽ��");
      form1.ta270050003.value="";
      return;
    }
   
   form1.Apply_contractID.value="";
  }