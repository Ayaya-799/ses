// TCApply.js

function query(){
if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
      else if(isEmpty(form1.LetterKind.value)){
        alert("��ѡ������֤���ࡣ");
        return;
        form1.LetterKind.focus();
      }
      else if(isEmpty(form1.Apply_contractID.value)){
        alert("��ѡ������š�");
        return;
      }else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      else{
        form1.oper.value="detailForm";
        form1.submit();
      }
}

   
   
function add(){
   if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
      else if(isEmpty(form1.LetterKind.value)){
        alert("��ѡ������֤���ࡣ");
        form1.LetterKind.focus();
        return;
      }else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      else{
        form1.Apply_contractID.value ="";
        form1.oper.value="newForm";
        form1.submit();
        
        }
}

function chooseContractID(){
     if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
       else if(isEmpty(form1.LetterKind.value)){
        alert("��ѡ������֤���ࡣ");
        form1.LetterKind.focus();
        return;
      }else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      else{
        var ts = window.showModalDialog(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.TF.operation.LCApplyQuery&enpcode="+form1.Apply_customerCode.value+"&letterkind="+form1.LetterKind.value+"&area="+form1.Apply_sponsorBank.value+"&height=400&width=300&hasDetailLink=true&time="+new Date(),window,"dialogWidth:330px;dialogHeight:450px;center:yes;help:no;status:no;resizable:no");
        if(ts!=null){
          form1.Apply_contractID.value = ts[1];
        }
      }
   }
   
   function f_changelckind(){
      form1.Apply_contractID.value = "";
    }