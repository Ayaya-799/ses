//����ҳ��
function f_init(){
  for(var i=0;i<parent.length;i++){
   if(parent.frames[i].name=="approve_iframeinfo")	       
     hiddenButtons();
  }
  loadTable(form1.formID.value,form1.pageMode.value,form1._primaryInfo.value,form1._querypermute.value);
}

//������¼
function insertTa(){
  
  
  var items="";
  //ȡ���ڵ�����input�� 
  items = document.getElementsByTagName("input");
  for(ii=0;ii<items.length;ii++){
    if(items[ii].isAmount=='yes')
      items[ii].value=trimNum(items[ii].value);
    else
      items[ii].value=trim(items[ii].value);
  }
  
  //�������ݴ浽session��
 
  if (form1.Apply_kind.value=='24'|| form1.Apply_kind.value=='25'||form1.Apply_kind.value.value=='26'){
    if(!checkNeedParams())
    return;  
  if(!checkspecial(form1.primaryInfo.value))
    return;
  
  }else{
  
  if(!checkForm_Ht())  //��ͬ�Ƿ���������
  return;
  if(form1.Apply_kind.value=='171'){
  if(checkJJcount())
  return;
  }
  }
  if (form1.Isquery.value=='0')
  form1.opAction.value="add";
  else if (form1.Isquery.value=='1')
  form1.opAction.value="modify";
  submitparameter("form1",form1.formCode.value);
  form1.submit();
}

//�޸ļ�¼
function modifyTa(){
  if(!checkflow()){//�ж��Ƿ��������
    return;
  }
  if(!zhAuthorize('updateTa'))//�ж��Ƿ���������Ȩ
    return;
  form1.isJieju.value="0";  //���
  form1.operationName.value='icbc.cmis.util.GeneralContentOp';
  form1.oper.value='showInsertView';  //���޸ĺ��������ҳ�棬��Ϊ�������Ĺ���һ��
  form1.submit();
}

//���¼�¼
function updateTa(){
  if(!checkNeedParams())
    return;
  if(!checkspecial(form1.primaryInfo.value))//�ж�ҵ������ݹ�ϵУ��
    return;
   
  var items="";
  //ȡ���ڵ�����input�� 
  items = document.getElementsByTagName("input");
  for(ii=0;ii<items.length;ii++){
    if(items[ii].isAmount=='yes')
      items[ii].value=trimNum(items[ii].value);
    else
      items[ii].value=trim(items[ii].value);
  }
    
  //�������ݴ浽session��
  submitparameter("form1",form1.formCode.value);
  form1.opAction.value='modify';
  form1.submit();
}

//ɾ����¼
function deleteTa(){  
  if(!checkflow()){//�ж��Ƿ��������
   return;
  }
  if(!zhAuthorize('deleteTa'))//�ж�������Ȩ
    return;
  if(!deletetz()){//����У��
    return;
  }
  if(!confirm("�Ƿ�ȷ��Ҫɾ����ҵ���¼?"))
    return;

  form1.opAction.value='delete';
  form1.submit();
}

function returnmainAdd(){
  pinfo=form1._primaryInfo.value;
  var pri=pinfo.split("|");
  form1.operationName.value="icbc.cmis.util.GeneralApplyFirstFormOp";
  form1.Apply_kind.value=pri[0];
  form1.Apply_stage.value=pri[1];
  form1.approveAction.value=0;
  form1.submit();
}

//���ز�ѯҳ
function cancelp(){
  form1.operationName.value="icbc.cmis.util.GeneralContentOp";
  form1.oper.value="showQueryView";
  form1.submit();
}

//������ҳ
function returnmain(){
  pinfo=form1._primaryInfo.value;
  var pri=pinfo.split("|");
  form1.operationName.value="icbc.cmis.util.GeneralApplyFirstFormOp";
  form1.Apply_kind.value=pri[0];
  form1.Apply_stage.value=pri[1];
  form1.submit();
}

//��غ�ͬ
function assure(){  
  var flag;//0�����޸ģ�1�����޸�
  var returnLink;
  if (form1.isJieju.value=='3'){
  alert("���ȵ�ȷ�������ݣ����ߵ㷵��ȡ�����!");
  return;
  }
  if(form1.pageMode.value=='showInsertView'||form1.pageMode.value=='showModifyView'){
    flag='1';
  }else
    flag='0';
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
  
  if (form1.isJieju.value!='2' &&  form1.isJieju.value!='6')
    submitparameter("form1",form1.formCode.value);
  }
  if(form1.isJieju.value=='6'||form1.isJieju.value=='7') 
   form1.pageMode.value='showQueryView';  //����ǲ�ѯ�������ò�ѯ״̬
  //if (form1.isJieju.value=='2'||form1.isJieju.value=='3'||form1.isJieju.value=='4')
  form1.isJieju.value='0'  //���ص���ͬҳ��
   returnLink = basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.GeneralContentOp&opDataUnclear=true&isSessionClear=1';//���ص�url
  returnLink+="&oper="+form1.pageMode.value;
  
  assureTab(flag,returnLink);
}
//����ͬ����ҳ��
function Qyhtsq(){
  form1.isJieju.value="0";  //���
  form1.operationName.value="icbc.cmis.util.GeneralContentOp";
  form1.submit();

}
//���ص���ͬ����ҳ�棨�����޸�ʱ��
function Qyhtsq1(){
  form1.oper.value="showQueryView";
  form1.isJieju.value="0";  //���
  form1.operationName.value="icbc.cmis.util.GeneralContentOp";
  form1.submit();

}
//��ѯ�����޸Ľ�����б�ҳ��
function Qyjjsq(){
  form1.oper.value="showInsertView";
  form1.isJieju.value="6";  //���
  form1.operationName.value="icbc.cmis.util.GeneralContentOp";
  form1.submit();
}

//���������ҳ��
function jjsq(){  

  if(!checkForm_Ht())
  return;
  submitparameter("form1",form1.formCode.value);
  form1.isJieju.value="2";  //���
  form1.operationName.value="icbc.cmis.util.GeneralContentOp";
  form1.submit();
}

//����������������ҳ��
function addjj(){  
  
  form1.isJieju.value="3";  //���
  form1.operationName.value="icbc.cmis.util.GeneralContentOp";
  form1.submit();
}
//��ݵ�������(��������)
function insertjj(){  
  if(!checkForm_Jj())
  return;
  form1.operationName.value="icbc.cmis.util.GeneralViewOp";
  form1.opAction.value="addjjArchive";
  form1.submit();
}



//������ʽ�ݽ����ҳ��
function gotoModjj(jjcode,table_name){
   
  if (form1.isJieju.value=="6"){
  form1.isJieju.value="7"
  form1.oper.value="showInsertView";
  };//���벻���޸ĵĽ����ϸҳ��
  else
  form1.isJieju.value="4";    
  form1.operationName.value="icbc.cmis.util.GeneralContentOp";
  form1.querytj.value=table_name+'#'+jjcode;
  form1.submit();

}
//�����޸�
function modifyJjInfo(){
  if(!checkForm_Jj())
  return;
  form1.operationName.value="icbc.cmis.util.GeneralViewOp";
  form1.opAction.value="modjjArchive";
  form1.submit();

}
//����ɾ��
function deleteJjInfo(){
  if(!candeljj())
  return;
  form1.operationName.value="icbc.cmis.util.GeneralViewOp";
  form1.opAction.value="deljjArchive";
  form1.submit();

}

//����б��еİ�ť����󷵻غ�ͬҳ��
function returnHt(){
    form1.operationName.value="icbc.cmis.util.GeneralContentOp";
    form1.isJieju.value='0';
    form1.submit();
}

//����е�����ذ�ť�����ص�����б�ҳ��
//����б��еİ�ť����󷵻غ�ͬҳ��
function returnJjList(){
    form1.operationName.value="icbc.cmis.util.GeneralContentOp";
    form1.isJieju.value='2';
    form1.submit();
}

//ҵ������Ϣ
function append(){
  var flag;//0�����޸ģ�1�����޸�
  if(form1.pageMode.value=='showInsertView'||form1.pageMode.value=='showModifyView'){
    flag='1';
  }else
    flag='0';
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
    submitparameter("form1",form1.formCode.value);
  }
  var returnurl=basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.GeneralContentOp&opDataUnclear=true';
  returnurl+="&oper="+form1.pageMode.value;
  appendTab(flag,returnurl,'');
}


function hiddenButtons(){
  document.all.image_area.style.display="none";
}


function showRelation(){
  var ts = window.showModalDialog(basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.GA.GA_InterrelatedInfoOp&opAction=EnterPage&CustomerId="+form1.Apply_customerCode.value+"&opDataUnclear=true&time=" + (new Date), window,"dialogWidth:650px;dialogHeight:450px;center:yes;help:no;status:no;resizable:no;z-lock:yes;moveable:no;copyhistory:yes");
}


function isweekday(str){
    if(str.length>0&&isDate(str)){
      var x= str.substring(0,4)/1;
      var y= str.substring(4,6)/1-1;
      var z= str.substring(6,8)/1;
      var date1=new Date(x,y,z);
      if(date1.getDay()== '0'){
        alert("������������");
      }
      else if(date1.getDay()=='6'){
        alert("������������");
      }
    }
 }

