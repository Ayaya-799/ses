// FECreditJGApply.js

function query(){
 if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return ;
      }
      else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return ;
      }
      else if(isEmpty(form1.ta200061041.value)){
        alert("��ѡ������ͬ");
        return ;
      }
      else if(isEmpty(form1.ta200061002.value)){
        alert("��ѡ��������");
        return ;
      }
      else{
        form1.oper.value="detail1";
        form1.submit();
      }
}

   
   
function add(){
   if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return ;
      }
      else if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return ;
      }
      else if(isEmpty(form1.ta200061041.value)){
        alert("��ѡ������ͬ");
        return ;
      }
      else{

           var j = 0;
           j = form1.syje.value/1;
          j = j/1;
          if(j <= 0){
            alert("��������֮���Ѿ���������������������");
            return ;
          }
          else{ 
          form1.ta200061002.value ="";     
          form1.oper.value="add1";
          form1.submit();        
       }
      }
}

function chooseContractID(){
    
     if(!isEmpty(form1.Apply_customerCode.value)){
        if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        }
        tmp = "oper=checkweituo&&time="+(new Date);
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
       var weituo = parser.documentElement.getAttribute("weituo");//ί�д���
         var ts = window.showModalDialog(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.FE.DueApplyQuery&enpcode="+form1.Apply_customerCode.value+"&areacode="+form1.Apply_sponsorBank.value+"&height=400&width=300&hasDetailLink=true&weituo="+weituo+"&time="+new Date(),window,"dialogWidth:330px;dialogHeight:450px;center:yes;help:no;status:no;resizable:no");
        if(ts!=null){
          form1.ta200061041.value = ts[1];
          tmp = "enpcode="+form1.Apply_customerCode.value+"&pactcode="+encode(form1.ta200061041.value)+"&oper=pactback&time="+(new Date);
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
      var syje = parser.documentElement.getAttribute("syje");
      syje = syje/1;
      if(syje==-10000){
        alert("�ú�ͬ��ת����ͬΪ��¼��ͬ���������������");
        form1.ta200061041.value = "";
        return ;
      }
      form1.syje.value = parser.documentElement.getAttribute("syje");
      }
      }
      else{
        alert("��ѡ��ͻ�");
      }
   }
   
   function queryJG(){
      if(isEmpty(form1.Apply_customerCode.value)){
        alert("��ѡ��ͻ�");
        return ;
      }
      else
      if(isEmpty(form1.ta200061041.value)){
        alert("��ѡ������ͬ");
        return ;
      }
      else{
        var ts = window.showModalDialog(basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=FECreditApplyOp&OpDataUnclear=true&ta200061001="+form1.Apply_customerCode.value+"&ta200061041="+encode(form1.ta200061041.value)+"&oper=applyquery&time="+new Date(),window,"dialogWidth:500px;dialogHeight:320px;center:yes;help:no;status:no;resizable:no");
        if(ts!=null){
          form1.ta200061002.value = ts[0][0];
        }
      }
   }