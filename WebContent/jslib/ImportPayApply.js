//���ڴ�������У��
function checkspecial(pinfo){
  var pri=pinfo.split("|");
  if(pri[0]=='24'&&pri[1]=='0'){
    if(!applycheck1())return false;
    else return true;
  }else if(pri[0]=='25'&&pri[1]=='0'){
    if(!applycheck2())return false;
    else return true; 
  }else return true;
}

//�ж�������Ȩ
function zhAuthorize(oper){
  return true;
}

//�ж��Ƿ��������
function checkflow(){
  if(!checkappstat(form1.Apply_customerCode.value,form1.Apply_contractID.value,form1.Apply_kind.value)){
	return false;
  }else return true;
}

//ɾ������
function deletetz(){
  return true;
}
//-----------------------����֤���½��ڴ�������У��---------------------------
//ta270050018������������
//ta270050017: Լ����������
//ta270050014: ��������
//ta270050016: �������
function applycheck1(){
  //����������-Լ����������+��������<=����A
  if(calculatedays(form1.ta270050017.value,form1.ta270050018.value)+form1.ta270050014.value/1>form1.controlA.value){
    alert("���������գ�Լ����������+��������<="+form1.controlA.value+"��");
    form1.ta270050017.focus();
    return false;
  }
  //����������-Լ����������<=����B
  else if(calculatedays(form1.ta270050017.value,form1.ta270050018.value)>form1.controlB.value){
    alert("���������գ�Լ����������<="+form1.controlB.value+"��");
    form1.ta270050017.focus();
    return false;
  }
  else if(form1.ta270050017.value>=form1.ta270050018.value){
    alert("����������Ӧ����Լ����������");
    form1.ta270050018.focus();
    return false;
  }
  else if(form1.ta270050905.value>form1.cmisdate.value){
    alert("�������ڲ��ܴ���ϵͳ����("+form1.cmisdate.value+")");
    form1.ta270050905.focus();
    return false;
  }
  else if(form1.ta270050017.value<form1.cmisdate.value){
    alert("Լ���������ڲ���С��ϵͳ����("+form1.cmisdate.value+")");
    form1.ta270050017.focus();
    return false;
  }
  else if(trimNum(form1.ta270050016.value)/1>trimNum(form1.ta270050013.value)/1){
    alert("�������ܴ��ڽ�����");
    form1.ta270050016.focus();
    return false;
  }
  else if(trimNum(form1.ta270050016.value)/1<=0){
    alert("�������Ӧ����0");
    form1.ta270050016.focus();
    return false;
  }
  else if(trimNum(form1.ta270050009.value)/1<0){
    alert("��֤�����С��0");
    form1.ta270050009.focus();
    return false;
  }
  else if(trimNum(form1.ta270050019.value)/1<0){
    alert("������Ϣ��������С��0");
    form1.ta270050019.focus();
    return false;
  }else if(trimNum(form1.ta270050020.value)/1<0){
    alert("������Ϣ��������С��0");
    form1.ta270050020.focus();
    return false;
  //}else if(form1.ta270050006.value=='20'){
  //  if(isEmpty(form1.ta270050023.value)){
  //    alert("��ѡ�񵣱���ʽ");
  //    form1.ta270050023.focus();
  //    return false;
  //  }else return true;
  }else if(!checkExpDate(form1.ta270050022.value)){
    alert("�����ձ�ʶ��ʽ����ȷ������������");
    form1.ta270050022.focus();
    return false;
  }
  //}else if(form1.ta270050006.value!='20'){
  //  if(!isEmpty(form1.ta270050023.value)){
  //    alert("������ʽ�Ǳ�֤���������뵣����ʽ");
  //    form1.ta270050023.focus();
  //    return false;
  //  }else return true;
  //}  else return true;
  if(form1.ta270050006.value=='20'){
    if(isEmpty(form1.ta270050023.value)){
      alert("��ѡ�񵣱���ʽ");
      form1.ta270050023.focus();
      return false;
    }else return true;
  }else if(form1.ta270050006.value!='20'){
    if(!isEmpty(form1.ta270050023.value)){
      alert("������ʽ�Ǳ�֤���������뵣����ʽ");
      form1.ta270050023.focus();
      return false;
    }else return true;
  }else return true;
}

//-----------------------������֤���½��ڴ�������У��---------------------------
//ta270050018������������
//ta270050017: Լ����������
//ta270050016: �������
function applycheck2(){
  //����������-Լ����������<=����B
  if(calculatedays(form1.ta270050017.value,form1.ta270050018.value)>form1.controlE.value){
    alert("���������գ�Լ����������<="+form1.controlE.value+"��");
    form1.ta270050017.focus();
    return false;
  }
  else if(form1.ta270050017.value>=form1.ta270050018.value){
    alert("����������Ӧ����Լ����������");
    form1.ta270050018.focus();
    return false;
  }
  else if(form1.ta270050905.value>form1.cmisdate.value){
    alert("�������ڲ��ܴ���ϵͳ����("+form1.cmisdate.value+")");
    form1.ta270050905.focus();
    return false;
  }
  else if(trimNum(form1.ta270050016.value)/1<=0){
    alert("�������Ӧ����0");
    form1.ta270050016.focus();
    return false;
  }
  else if(trimNum(form1.ta270050009.value)/1<0){
    alert("��֤�����С��0");
    form1.ta270050009.focus();
    return false;
  }
  else if(trimNum(form1.ta270050013.value)/1<=0){
    alert("������Ӧ����0");
    form1.ta270050013.focus();
    return false;
  }

  else if(form1.ta270050017.value<form1.cmisdate.value){
    alert("Լ���������ڲ���С��ϵͳ����("+form1.cmisdate.value+")");
    form1.ta270050017.focus();
    return false;
  }
  else if(trimNum(form1.ta270050016.value)/1>trimNum(form1.ta270050013.value)/1){
    alert("�������ܴ��ڽ�����");
    form1.ta270050016.focus();
    return false;
  }else if(trimNum(form1.ta270050019.value)/1<0){
    alert("������Ϣ��������С��0");
    form1.ta270050019.focus();
    return false;
  }else if(trimNum(form1.ta270050020.value)/1<0){
    alert("������Ϣ��������С��0");
    form1.ta270050020.focus();
    return false;
  }
  
  if(form1.ta270050003.value=='2'){
    if(isEmpty(form1.ta270050011.value)){
      alert("���������ҵ����");
      form1.ta270050011.focus();
      return false;
    }
    if(isEmpty(form1.ta270050022.value)){
      alert("�����뵽���ձ�ʶ");
      form1.ta270050022.focus();
      return false;
    }else{
      if(!checkExpDate(form1.ta270050022.value)){
        alert("�����ձ�ʶ��ʽ����ȷ������������");
        form1.ta270050022.focus();
        return false;
      }
    }
    if(isEmpty(form1.ta270050014.value)){
      alert("���������ҵ�񸶿�����");
      form1.ta270050014.focus();
      return false;
    }
  }else if(form1.ta270050003.value=='3'){
    if(!isEmpty(form1.ta270050022.value)){
      alert("���㷽ʽΪT/T���,�������뵽���ձ�ʶ");
      form1.ta270050022.focus();
      return false;
    }
  }
  
  if(!isEmpty(form1.ta270050014.value)){
    if(form1.ta270050014.value/1<0){
      alert("���ҵ�񸶿����޲���С��0");
      form1.ta270050014.focus();
      return false;
    }  
  }
  
  if(form1.ta270050006.value=='20'){
    if(isEmpty(form1.ta270050023.value)){
      alert("��ѡ�񵣱���ʽ");
      form1.ta270050023.focus();
      return false;
    }else return true;
  }else if(form1.ta270050006.value!='20'){
    if(!isEmpty(form1.ta270050023.value)){
      alert("������ʽ�Ǳ�֤���������뵣����ʽ");
      form1.ta270050023.focus();
      return false;
    }else return true;
  }else return true;
}
//�ı��������
function changecurr1(){
  form1.ta270050012.value=form1.ta270050015.value;
  if(!isEmpty(form1.ta270050021.value)){
    form1.lab_ta270050021.value="";
    form1.ta270050021.value="";
    alert("������ѡ����������");
    return;
  }
}

//�ı�������
function changecurr2(){
  form1.ta270050015.value=form1.ta270050012.value;
  if(!isEmpty(form1.ta270050021.value)){
    form1.lab_ta270050021.value="";
    form1.ta270050021.value="";
    alert("������ѡ����������");
    return;
  }
}
//---------------------���ڴ�������ͨ��-------------------------------------
//ѡ��������
function chooserate(){
  var pinfo=form1.primaryInfo.value;
  if(isEmpty(form1.ta270050015.value)){
    var pri=pinfo.split("|");
    if(pri[0]=='24')
      alert("��ѡ�����ҵ����");
    else{
      alert("��ѡ���������");
      form1.ta270050015.focus();
    }
    return;
  }else if(isEmpty(form1.ta270050017.value)){
    alert("������Լ����������");
    form1.ta270050017.focus();
    return;
  }else return true;

}

//�䶯��������
function changecurr(){
  if(!isEmpty(form1.ta270050021.value)){
    form1.lab_ta270050021.value="";
    form1.ta270050021.value="";
    alert("������ѡ����������");
    return;
  }
}


//�䶯���ʽ
function changedkfs(){
  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
  var parser=new ActiveXObject("microsoft.xmldom");
  parser.async="false";
  var tmp = "enpcode="+form1.ta270050001.value+"&dkfs="+form1.ta270050006.value+"&oper=querydkfs&time="+new Date();
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
  var seleobj=document.all.ta270050007;
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

//��鵽���ձ�ʶ�Ƿ�ΪRGF����YYYY.MM.DD��ʽ�����ڡ�
function checkExpDate(vexp){
	var v_temp;
	var v_YYYYMMDD;

	if(isEmpty(vexp)){return false;}
	v_temp=vexp;
	if(v_temp=="RGF"){return true;}
	if(v_temp.length!=10){return false;}
	if(v_temp.indexOf('.')!=4){return false;}
	if(v_temp.lastIndexOf('.')!=7){return false;}
	v_YYYYMMDD=v_temp.substring(0,4)+v_temp.substring(5,7)+v_temp.substring(8,10);
	if(isDateValid1(v_YYYYMMDD)){return true;}
	return false;
}

//˫����alert
function alertday(fdate){
  isweekday(eval("form1."+fdate).value);
}
/*
//��غ�ͬ
function assure(){  
  var flag;//0�����޸ģ�1�����޸�
  if(form1.pageMode.value=='showInsertView'||form1.pageMode.value=='showModifyView'){
    flag='1';
  }else
    flag='0';
  if(form1.pageMode.value!='showQueryView')
    submitparameter("form1",form1.formCode.value);
  var returnLink = basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.GeneralContentOp&opDataUnclear=true';//���ص�url
  returnLink+="&oper="+form1.pageMode.value;
  assureTab(flag,returnLink);
}

//ҵ������Ϣ
function append(){
  var flag;//0�����޸ģ�1�����޸�
  if(form1.pageMode.value=='showInsertView'||form1.pageMode.value=='showModifyView'){
    flag='1';
  }else
    flag='0';
  if(form1.pageMode.value!='showQueryView')
    submitparameter("form1",form1.formCode.value);
  var returnurl=basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.GeneralContentOp&opDataUnclear=true';
  returnurl+="&oper="+form1.pageMode.value;
  appendTab(flag,returnurl,'');
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
*/


