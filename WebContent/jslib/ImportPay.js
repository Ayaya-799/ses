// ���ڴ���̨�������޸�У��
function checkspecial(pinfo){
  var pri=pinfo.split("|");
  if(pri[0]=='24'&&pri[1]=='1'){
    if(!applycheck1())return false;
  }else if(pri[0]=='25'&&pri[1]=='1'){
    if(!applycheck2())return false;
  }
  if(!check1()) return false;
  else return true;  
}

//�ж�������Ȩ���Ƿ�����޸�ɾ��
function zhAuthorize(oper){
  if(oper=='updateTa')
    if(!patchCheck(form1.ta270051002.value,form1.ta270051001.value,form1.Apply_kind.value,'02',form1.ta270051004.value)){
      return false;
    }else return true;
  else if(oper=='deleteTa')
    if(!patchCheck(form1.ta270051002.value,form1.ta270051001.value,form1.Apply_kind.value,'03',form1.ta270051004.value)){
      return false;
    }else return true;
  else return true;
}

//�����ж�
function checkflow(){
  return true;
}

//ɾ��̨������֮һ
function deletetz(){
  if(form1.ta270051039.value=='2'){
    alert("����״̬Ϊ����ȷ�ϣ�����ɾ��");
    return false;
  }else return true;
}

//-----------------------����֤���½��ڴ���̨��У��---------------------
//ta270051024������������
//ta270051021: Լ����������
//ta270051025: ��������
//ta270051018: �������
function applycheck1(){
  //����������-Լ����������+��������<=����A
  if(calculatedays(form1.ta270051021.value,form1.ta270051024.value)+form1.ta270051016.value/1>form1.controlA.value){
    alert("���������գ�Լ����������+��������<="+form1.controlA.value+"��");
    form1.ta270051021.focus();
    return false;
  }
  //����������-Լ����������<=����B
  else if(calculatedays(form1.ta270051021.value,form1.ta270051024.value)>form1.controlB.value){
    alert("���������գ�Լ����������<="+form1.controlB.value+"��");
    form1.ta270051021.focus();
    return false;
  }
  else if(form1.ta270051021.value>=form1.ta270051024.value){
    alert("����������Ӧ����Լ����������");
    form1.ta270051024.focus();
    return false;
  }
  else if(form1.ta270051005.value>form1.cmisdate.value){
    alert("�������ڲ��ܴ���ϵͳ����("+form1.cmisdate.value+")");
    form1.ta270051005.focus();
    return false;
  }
  //else if(form1.ta270051021.value<form1.cmisdate.value){
  //  alert("Լ���������ڲ���С��ϵͳ����("+form1.cmisdate.value+")");
  //  form1.ta270051021.focus();
  //  return false;
  //}
  else if(trimNum(form1.ta270051018.value)/1<=0){
    alert("�������Ӧ����0");
    form1.ta270051018.focus();
    return false;
  }
  else if(trimNum(form1.ta270051012.value)/1<0){
    alert("��֤�����С��0");
    form1.ta270051012.focus();
    return false;
  }
  else if(trimNum(form1.ta270051018.value)/1>trimNum(form1.ta270051015.value)/1){
    alert("�������ܴ��ڽ�����");
    form1.ta270051018.focus();
    return false;
  }
  else if(!checkExpDate(form1.ta270051041.value)){
    alert("�����ձ�ʶ��ʽ����ȷ������������");
    form1.ta270051041.focus();
    return false;
  }else if(trimNum(form1.ta270051029.value)/1<0){
    alert("������Ϣ��������С��0");
    form1.ta270051029.focus();
    return false;
  }else if(trimNum(form1.ta270051030.value)/1<0){
    alert("������Ϣ��������С��0");
    form1.ta270051030.focus();
    return false;
  }
  if(!isEmpty(form1.ta270051033.value)){
    if(trimNum(form1.ta270051033.value)/1<0){
      alert("�������ִ�����ʲ���С��0");
      form1.ta270051033.focus();
      return false;
    }
  }
  if(!isEmpty(form1.ta270051034.value)){
    if(trimNum(form1.ta270051034.value)/1<0){
      alert("���д���ִ�����ʲ���С��0");
      form1.ta270051034.focus();
      return false;
    }
  }
  
  if(form1.ta270051009.value=='20'){
    if(isEmpty(form1.ta270051042.value)){
      alert("��ѡ�񵣱���ʽ");
      form1.ta270051042.focus();
      return false;
    }else return true;
  }else if(form1.ta270051009.value!='20'){
    if(!isEmpty(form1.ta270051042.value)){
      alert("������ʽ�Ǳ�֤���������뵣����ʽ");
      form1.ta270051042.focus();
      return false;
    }else return true;
  }
  else return true;
}


//-----------------------������֤���½��ڴ���̨��У��--------------------
//ta270051024������������
//ta270051021: Լ����������
//ta270051018: �������
function applycheck2(){
  //����������-Լ����������<=����E
  if(calculatedays(form1.ta270051021.value,form1.ta270051024.value)>form1.controlE.value){
    alert("���������գ�Լ����������<="+form1.controlE.value+"��");
    return false;
  }
  else if(form1.ta270051021.value>=form1.ta270051024.value){
    alert("����������Ӧ����Լ����������");
    form1.ta270051024.focus();
    return false;
  }
  else if(form1.ta270051005.value>form1.cmisdate.value){
    alert("�������ڲ��ܴ���ϵͳ����("+form1.cmisdate.value+")");
    form1.ta270051005.focus();
    return false;
  }
  //else if(form1.ta270051021.value<form1.cmisdate.value){
  //  alert("Լ���������ڲ���С��ϵͳ����("+form1.cmisdate.value+")");
  //  form1.ta270051021.focus();
  //  return false;
  //}
  else if(trimNum(form1.ta270051018.value)/1<=0){
    alert("�������Ӧ����0");
    form1.ta270051018.focus();
    return false;
  }
  else if(trimNum(form1.ta270051012.value)/1<0){
    alert("��֤�����С��0");
    form1.ta270051012.focus();
    return false;
  }
  else if(trimNum(form1.ta270051015.value)/1<=0){
    alert("������Ӧ����0");
    form1.ta270051015.focus();
    return false;
  }
  else if(trimNum(form1.ta270051018.value)/1>trimNum(form1.ta270051015.value)/1){
    alert("�������ܴ��ڽ�����");
    form1.ta270051018.focus();
    return false;
  }
  else if(trimNum(form1.ta270051029.value)/1<0){
    alert("������Ϣ��������С��0");
    form1.ta270051029.focus();
    return false;
  }else if(trimNum(form1.ta270051030.value)/1<0){
    alert("������Ϣ��������С��0");
    form1.ta270051030.focus();
    return false;
  }
  if(!isEmpty(form1.ta270051033.value)){
    if(trimNum(form1.ta270051033.value)/1<0){
      alert("�������ִ�����ʲ���С��0");
      form1.ta270051033.focus();
      return false;
    }
  }
  if(!isEmpty(form1.ta270051034.value)){
    if(trimNum(form1.ta270051034.value)/1<0){
      alert("���д���ִ�����ʲ���С��0");
      form1.ta270051034.focus();
      return false;
    }
  }
  if(form1.ta270051006.value=='2'){
    if(isEmpty(form1.ta270051013.value)){
      alert("���������ҵ����");
      form1.ta270051013.focus();
      return false;
    }
    if(isEmpty(form1.ta270051041.value)){
      alert("�����뵽���ձ�ʶ");
      form1.ta270051041.focus();
      return false;
    }else{
      if(!checkExpDate(form1.ta270051041.value)){
        alert("�����ձ�ʶ��ʽ����ȷ������������");
        form1.ta270051041.focus();
        return false;
      }
    }
    if(isEmpty(form1.ta270051016.value)){
      alert("���������ҵ�񸶿�����");
      form1.ta270051016.focus();
      return false;
    }
  }else if(form1.ta270051006.value=='3'){
    if(!isEmpty(form1.ta270051041.value)){
      alert("���㷽ʽΪT/T���,�������뵽���ձ�ʶ");
      form1.ta270051041.focus();
      return false;
    }
  }
  
  if(!isEmpty(form1.ta270051016.value)){
    if(form1.ta270051016.value/1<0){
      alert("���ҵ�񸶿����޲���С��0");
      form1.ta270051016.focus();
      return false;
    }
  }
  
  if(form1.ta270051009.value=='20'){
    if(isEmpty(form1.ta270051042.value)){
      alert("��ѡ�񵣱���ʽ");
      form1.ta270051042.focus();
      return false;
    }else return true;
  }else if(form1.ta270051009.value!='20'){
    if(!isEmpty(form1.ta270051042.value)){
      alert("������ʽ�Ǳ�֤���������뵣����ʽ");
      form1.ta270051042.focus();
      return false;
    }else return true;
  }else return true;
}

//�ı��������
function changecurr1(){
  form1.ta270051014.value=form1.ta270051017.value;
  form1.lab_ta270051035.value="";
  form1.ta270051035.value="";

}
//�ı�������
function changecurr2(){
  form1.ta270051017.value=form1.ta270051014.value;
  form1.lab_ta270051035.value="";
  form1.ta270051035.value="";

}
//-------------------------���ڴ���̨��ͨ��---------------------------
//�䶯���ʽ
function changedkfs(){
  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
  var parser=new ActiveXObject("microsoft.xmldom");
  parser.async="false";
  var tmp = "enpcode="+form1.ta270051001.value+"&dkfs="+form1.ta270051009.value+"&oper=querydkfs&time="+new Date();
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
  var seleobj=document.all.ta270051010;
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

//ѡ����������
function chooserate(){
  var pinfo=form1.primaryInfo.value;
  if(isEmpty(form1.ta270051017.value)){
    var pri=pinfo.split("|");
    if(pri[0]=='24')
      alert("��ѡ�����ҵ����");
    else{
      alert("��ѡ���������");
      form1.ta270051017.focus();
    }
    return;
  }else if(isEmpty(form1.ta270051021.value)){
    alert("������Լ����������");
    form1.ta270051021.focus();
    return;
  }else return true;
}

//�ı��������
function changecurr(){
  if(!isEmpty(form1.ta270051017.value)){
    form1.lab_ta270051035.value="";
    form1.ta270051035.value="";
    alert("������ѡ����������");
    return;
  }
}

//��̨��״̬����δȷ��ʱ������У��
function check1(){
  if(form1.ta270051039.value!='0'){
    if(isEmpty(form1.ta270051027.value)){
      alert("���������ʵ��ҵ����");
      form1.ta270051027.focus();
      return false;
    }else if(isEmpty(form1.ta270051019.value)){
      alert("������ʵ�ʴ������");
      form1.ta270051019.focus();
      return false; 
    }else if(trimNum(form1.ta270051019.value)/1<=0){
      alert("ʵ�ʴ������Ӧ����0");
      form1.ta270051019.focus();
      return false;
    }else if(trimNum(form1.ta270051019.value)/1>form1.ta270051015.value){
      alert("ʵ�ʴ������ܴ��ڽ�����");
      form1.ta270051019.focus();
      return false;
    }else if(isEmpty(form1.ta270051020.value)){
      alert("������������");
      form1.ta270051020.focus();
      return false;
    }else if(trimNum(form1.ta270051020.value)/1<0){
      alert("��������С��0");
      form1.ta270051020.focus();
      return false;
    }else if(trimNum(form1.ta270051020.value)/1>trimNum(form1.ta270051019.value)/1){
      alert("�������ܴ���ʵ�ʴ������");
      form1.ta270051020.focus();
      return false;
    }
    else if(isEmpty(form1.ta270051022.value)){
      alert("������ʵ�ʴ�������");
      form1.ta270051022.focus();
      return false;
    }else if(isEmpty(form1.ta270051023.value)){
      alert("���������ʵ�ʵ�����");
      form1.ta270051023.focus();
      return false
    }else if(isEmpty(form1.ta270051036.value)){
      alert("���������и�������");
      form1.ta270051036.focus();
      return false;
    }else if(isEmpty(form1.ta270051028.value)){
      alert("�����������");
      form1.ta270051028.focus();
      return false;
    //}else if(form1.ta270051022.value<form1.cmisdate.value){
    //  alert("ʵ�ʴ������ڲ���С��ϵͳ����("+form1.cmisdate.value+")");
    //  form1.ta270051022.focus();
    //  return false;
    }else if(form1.ta270051023.value<form1.ta270051022.value){
      alert("����ʵ�ʵ����ղ���С��ʵ�ʴ�������");
      form1.ta270051023.focus();
      return false;
    }
  }
  if(form1.ta270051006.value=='1'){
    if(calculatedays(form1.ta270051022.value,form1.ta270051023.value)+form1.ta270051016.value/1>form1.controlA.value){
      alert("����ʵ�ʵ����գ�ʵ�ʴ�������+��������<="+form1.controlA.value+"��");
      form1.ta270051023.focus();
      return false;
    }
    //����������-Լ����������<=����B
    else if(calculatedays(form1.ta270051022.value,form1.ta270051023.value)>form1.controlB.value){
      alert("����ʵ�ʵ����գ�ʵ�ʴ�������<="+form1.controlB.value+"��");
      form1.ta270051023.focus();
      return false;
    }
  }else{
    if(calculatedays(form1.ta270051022.value,form1.ta270051023.value)>form1.controlE.value){
      alert("����ʵ�ʵ����գ�ʵ�ʴ�������<="+form1.controlE.value+"��");
      form1.ta270051023.focus();
      return false;
    }
  }
  return true;
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