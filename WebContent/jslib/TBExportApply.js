// TBExportApply.js

function query(){
  if(trim(form1.Apply_customerCode.value) == ""||form1.Apply_customerCode.value == null){
        alert("��ѡ��ͻ�����!");
        return;
      }
   if(trim(form1.Apply_contractID.value) == ""||form1.Apply_contractID.value == null){
        alert("��ѡ�����Ѻ�������");
        return;
      }else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      
      else{
    form1.oper.value="querydetail";
    form1.submit();
  }
}

   
   
function add(){
   if(trim(form1.Apply_customerCode.value) == ""||form1.Apply_customerCode.value == null)  {
     alert("��ѡ��ͻ�����!");
     return;
   }else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
   form1.Apply_contractID.value = "";  
   form1.oper.value="new";
   form1.submit();
}

function chooseContractID(){
    if(form1.Apply_customerCode.value == ""||form1.Apply_customerCode.value == null){
    alert("����ѡ��ͻ�����!");
    form1.Apply_customerCode.focus();
    return;
  }
 if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
    }
  var EntCode = form1.Apply_customerCode.value;
  var ts = window.showModalDialog(encodeURL(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.TB.util.queryAppCode&EntCode="+EntCode+"&area="+form1.Apply_sponsorBank.value+"&height=410&hasDetailLink=true&returnType=value"),window,"dialogWidth:630px;dialogHeight:460px;center:yes;help:no;status:no;resizable:yes");
  if (ts != null){
    form1.Apply_contractID.value = ts[1];
  }
}