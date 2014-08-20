/*����˵����������ͬ����ģ��һЩ����У���js����*/

/**
 * ������ͬ
 * @create date 2005-8-24 10:03
 * @create by zheng ze zhou
 * @ע����������У��Ⱥ���
 * @modify date 2005-8-31 15:23
 * @modify by zheng ze zhou
 * @ע�������˴������returnLink
 * @modify date 2005-9-16 14:44
 * @modify by zheng ze zhou
 * @ע�������˴�������ߵı����֣��Ա�������ݣ�ȥ�������ù�����ģʽ
 * @modify date 2005-12-14 10:22
 * @modify by zheng ze zhou
 * @ע��get�����ϴ�contractIDʱ������encode��������ֹ������б�������
 * @modify date 2006-1-6 10:36
 * @modify by zheng ze zhou
 * @ע����tools.js����encode���������ļ���ֹ�����߱��Ҳ���encode�����Ĵ���
 */

/****************************************  һЩ���õı���  ***************************************************/
var PRENAME  = '_assure_'; //��OP���ͳһǰ׺��һ���ģ���ֹ�˹���ģ�������������
var DIVNAME  = 'divAssureHidden'; //�����߱���ͳһ����
var ASSUREOP = 'icbc.cmis.tfms.AA.AssureAssociationOP'; //���õ����ӿڶ�Ӧ��OP����
var ASSUREQUERY = 'icbc.cmis.tfms.AA.AssureAssociationQuery'; //���õ����ӿڹ��ò�ѯ����

/****************************************************************************************************************
                              �����ǵ�����ͬģ��Ĺ���У�麯��
*****************************************************************************************************************/
/******************************************************
 *�������������������޸ĵ�����ͬ��ҳ����ô˺����ṩ������ͬ��ά�����ܣ�������ͬ���У��
 *����˵����customerCode = �ͻ���                                                    
 *����˵����customerName = �ͻ���������ҳ����ʾ��                                    
 *����˵����contractID   = ҵ��̨�ʻ�������еĺ�ͬ�ţ�ָta200211002��ta200212002��ֵ��
 *����˵����pesudoID     = ҵ��α������δ����ҵ���ͬ��ʱ���棩                          
 *����˵����baseID       = �޸���ҵ���Ӧ�Ļ���ҵ������֤�޸Ķ�Ӧ������֤�ţ�            
 *����˵����employeeCode = ҵ�񾭰��ˣ��͵�ǰsession��Ա��                           
 *����˵����areaCode     = ҵ�������У��͵�ǰsession�����У�                             
 *����˵����magKind      = ����ҵ���Ӧ��magKind�ֵ���ֵ                                  
 *����˵����assureKind   = ������ҵ�����ࣨda200211010��                               
 *����˵����workdate     = ҵ����ʱ�䣨��cmisdate, yyyymmdd��                         
 *����˵����applyStage   = ҵ�������׶Σ�0 ���� 1 ���� 2 ׼��֤ 3 ̨�ʣ�               
 *����˵����actionMode   = 0 ��ѯģʽ 1 ��дģʽ 2 У��ģʽ 3 ���ù�����
 *����˵����returnLink   = �ڲ�ѯ���дģʽ���ýӿں󷵻ص�url
 *����˵����formName     = ������ҳ��������ƣ�һ��Ϊform1�����ڱ�������ݣ�û��ʱ���� 
 *���ߣ�zhengzezhou
 *�������ڣ�2005-8-25 16:01
 *****************************************************/
function assure_check(customerCode,customerName,contractID,pesudoID,baseID,employeeCode,areaCode,magKind,assureKind,workdate,applyStage,actionMode,returnLink,formName){
  if(actionMode=='1'||actionMode=='3'||actionMode=='0'){
    if(formName==null||formName=='')return false;
    var objForm = eval('document.all.'+formName);
    objForm.action = basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet'; 
    objForm.operationName.value = 'icbc.cmis.tfms.AA.AssureAssociationOP';
    var objDiv = eval('document.all.'+DIVNAME);
    //�ж�����Ԫ���Ƿ���������ظ�
    var obj_opAction = eval(formName+'.opAction');
    if(obj_opAction==null) {
      objDiv.innerHTML += '<input type="hidden" name="opAction" value="showList">';
    }else{
      obj_opAction.value = 'showList';
    }
    var obj_opDataUnclear = eval(formName+'.opDataUnclear');
    if(obj_opDataUnclear==null) {
      objDiv.innerHTML += '<input type="hidden" name="opDataUnclear" value="true">';
    }else{
      obj_opDataUnclear.value = 'true';
    }
    var obj_queryType = eval(formName+'.queryType');
    if(obj_queryType==null) {
      objDiv.innerHTML += '<input type="hidden" name="queryType" value="' + ASSUREQUERY + '">';
    }else{
      obj_queryType.value = ASSUREQUERY;
    }
    var obj_customerCode = eval(formName+'.customerCode');
    if(obj_customerCode==null) {
      objDiv.innerHTML += '<input type="hidden" name="customerCode" value="'+customerCode+'">';
    }else{
      obj_customerCode.value = customerCode;
    }
    var obj_customerName = eval(formName+'.customerName');
    if(obj_customerCode==null) {
      objDiv.innerHTML += '<input type="hidden" name="customerName" value="'+customerName+'">';
    }else{
      obj_customerName.value = customerName;
    }
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'contractID" value="'+contractID+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'pesudoID" value="'+pesudoID+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'baseID" value="'+baseID+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'employeeCode" value="'+employeeCode+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'areaCode" value="'+areaCode+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'magKind" value="'+magKind+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'assureKind" value="'+assureKind+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'workdate" value="'+workdate+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'applyStage" value="'+applyStage+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'actionMode" value="'+actionMode+'">';
    objDiv.innerHTML += '<input type="hidden" name="'+PRENAME+'returnLink" value="'+returnLink+'">';
    objDiv.innerHTML += '<input type="hidden" name="hasDetailLink" value="true">';
    objDiv.innerHTML += '<input type="hidden" name="returnType" value="submitOp">';
    objForm.submit();
    ;
  }else if(actionMode=='2'){ //�����У��ģʽ
	  //define xml object
	  var objHTTP = new ActiveXObject('Microsoft.XMLHTTP');
    var parser=new ActiveXObject('microsoft.xmldom');
    parser.async='false';
    var act = basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName='+ASSUREOP
            + '&opAction=test'
            + '&'+PRENAME+'i_customercode=' + customerCode
            + '&'+PRENAME+'i_areacode=' + areaCode
            + '&'+PRENAME+'i_newcontract=' + encode(contractID)
            + '&'+PRENAME+'i_basecontract=' + encode(baseID)
            + '&'+PRENAME+'i_checktype=' + magKind
            + '&'+PRENAME+'i_applystage=' + applyStage
            + '&xmlOutput=true&opDataUnclear=true&DSF32AS='+new Date();
    //��ѯ
    objHTTP.Open('GET',act,false);
    objHTTP.Send();
    //��ѯ������
    if(!parser.loadXML(objHTTP.responseText)) {
      alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
      return false;
    }
    //���session��ʱ�ͽ���ʧ�ܣ���ʾ������Ϣ
    rootElement = parser.documentElement;
    if (rootElement.tagName == "error") {
      errorCode = "";
      errorMsg = "";
      for (i=0; i<rootElement.childNodes.length; i++) {
          if (rootElement.childNodes.item(i).tagName == "errorCode") {
               errorCode = rootElement.childNodes.item(i).text;
          }
          else if (rootElement.childNodes.item(i).tagName == "errorDispMsg") {
              errorMsg = rootElement.childNodes.item(i).text;
          }
      }
      alert(" ������룺" + errorCode + "\n����������" + errorMsg);
      return false;
    }
    if(rootElement.tagName=="datas")
    {
       var result=rootElement.getAttribute("message");
       if (result == 'OK') {
  	 	   return true;
		   }
		   else {
         alert('����������ʾ��Ϣ��'+result);
         return false;
		   }
    }
  } //У��ģʽ end
}

/****************************************************************************************************************
                              �����ǵ�����ͬҳ���õ�����������Ĺ��ú���
*****************************************************************************************************************/
/******************************************************
 *��������:У�����б�����
 *����˵����
 *���ߣ�zhengzezhou
 *�������ڣ�2005-3-23 14:58
 *****************************************************/
function checkRequired(){
  var inputs = document.all.tags("INPUT");
  for(var i=0;i < inputs.length ; i++) {
        if(inputs[i].required == "true"){
            if(isEmpty(inputs[i].value)){
                alert(inputs[i].hint+"����Ϊ��");
                inputs[i].focus();
                return false;
            }
        }
  }
  var selects = document.all.tags("select");
  for(var i=0;i < selects.length ; i++) {
        if(selects[i].required == "true"){
            if(isEmpty(selects[i].value)){
                alert(selects[i].hint+"����Ϊ��");
                selects[i].focus();
                return false;
            }
        }
  }
  var textareas = document.all.tags("textarea");
  for(var i=0;i < textareas.length ; i++) {
        if(textareas[i].required == "true"){
            if(isEmpty(textareas[i].value)){
                alert(textareas[i].hint+"����Ϊ��");
                textareas[i].focus();
                return false;
            }
        }
  }
  return true;
}
/******************************************************
 *��������:У�������������ʽ
 *����˵�����Զ���input������dateF="true"����dataType="date"
 *���ߣ�zhengzezhou
 *�������ڣ�2005-3-23 14:58
 *****************************************************/
function checkDateFormat(){
  var inputs = document.all.tags("INPUT");
  for(var i=0;i < inputs.length ; i++) {
        if(!isEmpty(inputs[i].value) && (inputs[i].dateF == "true" || inputs[i].dataType == "date")){
            var date = trimDate(inputs[i].value,8);
            if(!isDate(date)){
                alert(inputs[i].hint+"���ڸ�ʽ����");
                inputs[i].focus();
                return false;
            }
        }
  }
  return true;
}
/******************************************************
 *��������:У�����������ʽ
 *����˵�����Զ���input������amountF="true"����dataType="amount"
 *���ߣ�zhengzezhou
 *�������ڣ�2005-3-23 14:58
 *****************************************************/
function checkAmountFormat(){
  var inputs = document.all.tags("INPUT");
  for(var i=0;i < inputs.length ; i++) {
        if(inputs[i].amountF == "true" || inputs[i].dataType == "amount"){
            var amount = trimNum(inputs[i].value);
            var mesg = isReal(amount,16,2);
            if(mesg !='ok'){
                alert(inputs[i].hint+"����ʽ���ԣ�"+mesg);
                inputs[i].focus();
                return false;
            }
        }
  }
  return true;
}

/*����˵����������ͬģ��һЩ���õ�trim��js����*/
/******************************************************
 *��������:�����б���ǳ����Ľ���������ֵtrim
 *����˵�����Զ���input������amountF="true"����dataType="amount"
 amountF="true"���Ϊ���������amountF="false"���ǽ��������Ĭ��
 <form name="form1" method="post" action="">
  <input type="text" name="amount1" amountF="true">
</form>
�ύ��ʱҪ������������value��ʽ��������ȥ�����еĶ��ţ���ǰ�����ֹ�д���м�������ֶξ�Ҫд����trimNumber��䣩�����ڷ���canModifyAll����ʽ�����Զ�����ֻ�����¶���
1 �ڽ����������amountF�Զ�����Ϊtrue
���� <input type="text" name="ĳ���" value="123��456.12" disabled canModify=true amountF=true >
2 ��submit()����ǰ����trimNumAll()����

 *���ߣ�zheng ze zhou
 *�������ڣ�2004-7-20 9:08
 *****************************************************/
function trimNumAll(){
  var inputs = document.all.tags("INPUT");
  for(var i=0;i < inputs.length ; i++) {
    if(inputs[i].amountF=="true" || inputs[i].dataType == "amount"){
      inputs[i].value = delsep(inputs[i].value,',');
      inputs[i].value = trim(inputs[i].value);
    }
  }
}

/******************************************************
 *��������:�����б���ǳ����������������ֵtrim
 *����˵�����Զ���input������dateF="true"����dataType="date"
 dateF="true"���Ϊ����������dateF="false"��������������Ĭ��
 <form name="form1" method="post" action="">
  <input type="text" name="date1" dateF="true">
</form>
�ύ��ʱҪ�������������value��ʽ��������ȥ�����е�'/'����ǰ�����ֹ�д���м��������ֶξ�Ҫд����trim��䣩������ֻ�����¶���
1 ��������������dateF�Զ�����Ϊtrue
���� <input type="text" name="ĳ����" value="2005/02/13" disabled canModify=true dateF=true >
2 ��submit()����ǰ����trimDateAll()����

 *���ߣ�zheng ze zhou
 *�������ڣ�2004-7-20 9:08
 *****************************************************/
function trimDateAll(){
  var inputs = document.all.tags("INPUT");
  for(var i=0;i < inputs.length ; i++) {
    if(inputs[i].dateF=="true" || inputs[i].dataType == "date"){
      inputs[i].value = trimDate(inputs[i].value,8);
    }
  }
}

/******************************************************
 *��������:�ύ��ǰ��������������trim�Ķ���
 *����˵����
 *���ߣ�
 *�������ڣ�2005-3-23 14:59
 *****************************************************/
function trimFormAll(){
  trimNumAll();
  trimDateAll();
}

/******************************************************
 *��������:ҳ������󣬸�ʽ����ʾ����������Ķ���
 *����˵�����Զ���input������amountF="true"����dataType="amount"
 *����˵�����Զ���input������dateF="true"����dataType="date"
 *���ߣ�
 *�������ڣ�2005-3-23 14:59
 *****************************************************/
function formatShowAll(){
  var inputs = document.all.tags("INPUT");
  for(var i=0;i < inputs.length ; i++) {
    if(inputs[i].dateF=="true" || inputs[i].dataType == "date"){
      toDateShow(inputs[i],8);
    }
    if(inputs[i].amountF=="true" || inputs[i].dataType == "amount"){
      if(!isEmpty(inputs[i].value)){
        toExact2(inputs[i]);
      }
    }
  }
}

/******************************************************
 *��������:��������������disabled�Ķ���
 *����˵����
 *���ߣ�zheng ze zhou
 *�������ڣ�2005-3-23 14:59
 *****************************************************/
function disableAll(){

  var inputs = document.all.tags("INPUT");
  for(var i=0;i < inputs.length ; i++) {
    inputs[i].disabled = true;
  }

   
  var selects = document.all.tags("select");
  for(var i=0;i < selects.length ; i++) {
        selects[i].disabled = true;
  }
  
  var textareas = document.all.tags("textarea");
  for(var i=0;i < textareas.length ; i++) {
        textareas[i].disabled = true;
  }
}

/******************************************************
 *��������:������������ȡ��disabled�Ķ���
 *����˵����
 *���ߣ�zheng ze zhou
 *�������ڣ�2005-3-23 14:59
 *****************************************************/
function ableAll(){
  var inputs = document.all.tags("INPUT");
  for(var i=0;i < inputs.length ; i++) {
        inputs[i].disabled = false;
  }
  var selects = document.all.tags("select");
  for(var i=0;i < selects.length ; i++) {
        selects[i].disabled = false;
  }
  var textareas = document.all.tags("textarea");
  for(var i=0;i < textareas.length ; i++) {
        textareas[i].disabled = false;
  }
}

/******************************************************
 *��������:������������İ���canModify��������
 *����˵����
 canModify="true"����Ϊ���Ա༭��canModify="false"Ϊ�����Ա༭
 <form name="form1" method="post" action="">
  <input type="text" name="a" canModify="true">
  <input type="text" name="b" canModify="false">
  <input type="text" name="c" canModify="true">
  <input type="text" name="d" canModify="false">
</form>
 *���ߣ�
 *�������ڣ�
 *****************************************************/
function canModifyAll(){
  var inputs = document.all.tags("INPUT");
  for(var i=0;i < inputs.length ; i++) {
    if(inputs[i].canModify=="true")
        inputs[i].disabled = false;
      if(inputs[i].canModify=="false")
        inputs[i].disabled = true;
  }
  var selects = document.all.tags("select");
  for(var i=0;i < selects.length ; i++) {
    if(selects[i].canModify=="true")
          selects[i].disabled = false;
        if(selects[i].canModify=="false")
          selects[i].disabled = true;
  }
  var textareas = document.all.tags("textarea");
  for(var i=0;i < textareas.length ; i++) {
    if(textareas[i].canModify=="true")
          textareas[i].disabled = false;
        if(textareas[i].canModify=="false")
          textareas[i].disabled = true;
  }
}


/****************************************************************************************************************
                              �����ǵ�����ͬҳ���õ���ժ¼������js��ʵ�ú���
*****************************************************************************************************************/
/*����˵����һЩ���ý������У���js����*/
/******************************************************
 *��������: �����Ƿ�ֻ����������ֵ��0��9 ��.��
 *����˵����
 *���ߣ��ϲ�
 *�������ڣ�
 *****************************************************/
function inputPositiveNum(){
  if (event.keyCode < 46 || event.keyCode > 57 || event.keyCode == 47) event.returnValue = false;
}

/******************************************************
 *��������: ��һ��������ʽ���ɽ����ʽ(###,###,###.##)
 *����˵����
 *���ߣ��ϲ�
 *�������ڣ�
 *****************************************************/
function toExactPositive(ob,len,dec) {
  var val = trimNum(ob.value);
  if((ret = isReal(val,len - dec,dec)) != "ok") { alert(ret); ob.focus(); return false; }
  if(val<0){
    alert("������ֲ���С����");
    ob.focus();
      return false;
  }
  ob.value = exact(val,ob.size,dec);
  return true;
}
/******************************************************
 *��������: ���ַ���str�в����ַ�sep,��ȥ������str���е�sep�ַ�
 *����˵����str Դ�ַ��� sep �����˵��ַ� 
 *���ߣ�
 *�������ڣ�
 *****************************************************/
function delsep(str,sep){
  while(str.search(sep)!=-1)
    str=str.replace(sep,"");  
  return str;
}

/******************************************************
 *��������: ֻ�Ժ��ֽ���unicode���룻
            Ϊ�˷�ֹtools.jsû�������߰������ʰ��������ļ�ʹ��
 *����˵����str Դ�ַ��� ���� ������ַ���
 *���ߣ�
 *�������ڣ�
 *****************************************************/

//ֻ�Ժ��ֽ���unicode����
function encode(s) {
  if(s == null ) return s;
  if(s.length == 0) return s;

  var ret = "";
  for(i=0;i<s.length;i++) {
    var c = s.substr(i,1);
    var ts = escape(c);
    if(ts.substring(0,2) == "%u") {
      ret = ret + ts.replace("%u","@@");
    } else {
      ret = ret + c;
    }
  }
  return ret;
}
