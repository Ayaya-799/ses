// FEGuarantee.js
    var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
    var parser=new ActiveXObject("microsoft.xmldom");
    parser.async="false";
    
function query(){
      if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
      else if(isEmpty(form1.Apply_contractID.value)){
        alert("��ѡ�񱣺���������");
        return;
      }
      if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      else{
        form1.oper.value="detail";
        form1.submit();
      }
   }

    

   function add(){
      if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
      if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      if(isEmpty(form1.ta250051032.value)){
        alert("��ѡ�񱣺����ͣ�");
        return;
      }
      else{ 
      	form1.Apply_contractID.value ="";       
        form1.oper.value="add";
        form1.submit();
     }
   }
   function chooseContractID(){
      if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return;
      }
      if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return;
      }
      else{
        var ts = window.showModalDialog(basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=FEGuarantyOp&OpDataUnclear=true&ta250051001="+form1.Apply_customerCode.value+"&area="+form1.Apply_sponsorBank.value+"&ta250051032="+form1.ta250051032.value+"&oper=query&time="+new Date(),window,"dialogWidth:500px;dialogHeight:320px;center:yes;help:no;status:no;resizable:no");
        if(ts!=null){
          form1.Apply_contractID.value = ts[0][0];
        }
      }
   }