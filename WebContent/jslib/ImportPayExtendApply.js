//չ������У��
function checkspecial(pinfo){
  if(!zhanqicheck())return false;
  else if(!dfcheck())return false;
  else return true;
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
//-----------------------չ������У��---------------------------
//ta270052005:չ�ڽ��
//ta270051020:�������
//ta270052006:չ����ʼ��
//ta270052007:չ�ڵ�����
//ta270051023:����ʵ�ʵ�����
function zhanqicheck(){
  if(trimNum(form1.ta270052005.value)/1>trimNum(form1.ta270051020.value)/1){
    alert("չ�ڽ��ܴ��ڴ������");
    form1.ta270052005.focus();
    return false;
  }else if(trimNum(form1.ta270052005.value)/1<=0){
    alert("չ�ڽ��Ӧ����0");
    form1.ta270052005.focus();
    return false;
  }else if(form1.ta270052006.value>form1.ta270051023.value){
    alert("չ����ʼ�ղ��ܴ���ԭ����ʵ�ʵ�����("+form1.ta270051023.value+")");
    form1.ta270052006.focus();
    return false;
  }else if(form1.ta270052006.value<form1.cmisdate.value){
    alert("չ����ʼ�ղ���С��ϵͳ����("+form1.cmisdate.value+")");
    form1.ta270052006.focus();
    return false;
  }else if(form1.ta270052006.value<form1.ta270051022.value){
    alert("չ����ʼ�ղ���С��ԭʵ�ʴ�������("+form1.ta270051022.value+")");
    form1.ta270052006.focus();
    return false;
  }else if(form1.ta270052007.value<=form1.ta270051023.value){
    alert("չ�ں����������Ӧ����ԭ����ʵ�ʵ�����("+form1.ta270051023.value+")");
    form1.ta270052007.focus();
    return false;
  }else if(form1.ta270052905.value>form1.cmisdate.value){
    alert("�������ڲ��ܴ���ϵͳ����("+form1.cmisdate.value+")");
    form1.ta270052905.focus();
    return false;
  }else if(trimNum(form1.ta270052008.value)/1<0){
    alert("չ�ں������Ϣ��������С��0");
    form1.ta270052008.focus();
    return false;
  }else if(trimNum(form1.ta270052009.value)/1<0){
    alert("չ�ں�������Ϣ��������С��0");
    form1.ta270052009.focus();
    return false;
  }
  else return true;
}


//ta270052007:չ�ں����������
//ta270051021:������
//ta270051016:����֤��������
//ta270052006:չ����ʼ��
//pa:����C(����֤����)/����F(������֤����)
//pb:����D/����G
function dfcheck(){
  var pa,pb;
  if(form1.ta270051006.value=='1'){
    pa=form1.controlC.value;
    pb=form1.controlD.value;
  }else{
    pa=form1.controlF.value;
    pb=form1.controlG.value;
  }
  if(calculatedays(form1.ta270052006.value,form1.ta270052007.value)>pa){
    alert("չ�ں���������գ�չ����ʼ��<="+pa+"��");
    form1.ta270052006.focus();
    return false;
  }
  if(form1.ta270051006.value=='1'){
    if(calculatedays(form1.ta270051022.value,form1.ta270052007.value)+form1.ta270051016.value/1>pb){
       alert("չ�ں���������գ������գ�����֤��������<="+pb+"��");
       form1.ta270052007.focus();
       return false;
    }
  }else{
    if(calculatedays(form1.ta270051022.value,form1.ta270052007.value)>pb){
      alert("չ�ں���������գ�������<="+pb+"��");
      form1.ta270052007.focus();
      return false;
    }
  }
  return true;
}


//ѡ��������
function chooserate(){
  if(isEmpty(form1.ta270052006.value)){
    alert("������չ����ʼ����");
    form1.ta270052006.focus();
    return;
  }else return true;

}

//˫����alert
function alertday(fdate){
  isweekday(eval("form1."+fdate).value);
}
