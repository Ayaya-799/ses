//չ�������޸�У��
function checkspecial(pinfo){
  if(!zhanqicheck())return false;
  else if(!dfcheck())return false;
  else return true;
}

//�ж�������Ȩ
function zhAuthorize(oper){
  if(oper=='updateTa')
    if(!patchCheck(form1.ta270053002.value,form1.ta270053001.value,form1.Apply_kind.value,'02',form1.ta270053004.value))
      return false;
    else return true;
  else if(oper=='deleteTa')
    if(!patchCheck(form1.ta270053002.value,form1.ta270053001.value,form1.Apply_kind.value,'03',form1.ta270053004.value))
      return false;
    else return true;
  else return true;
}

//���̿���
function checkflow(){
  return true;
}

//ɾ���ж�����֮һ
function deletetz(){
  if(form1.ta270053022.value=='1'){
    alert("����չ��״̬Ϊ��ȷ�ϣ�����ɾ��");
    return false;
  }else return true;
}
//-----------------------չ��̨��У��---------------------------
//ta270053009:չ�ڽ��
//ta270051020:�������
//ta270053011:չ����ʼ��
//ta270053012:չ�ڵ�����
//ta270051023:����ʵ�ʵ�����
function zhanqicheck(){
  if(trimNum(form1.ta270053009.value)/1>trimNum(form1.ta270051020.value)/1){
    alert("չ�ڽ��ܴ��ڴ������");
    form1.ta270053009.focus();
    return false;
  }else if(trimNum(form1.ta270053009.value)/1<=0){
    alert("չ�ڽ��Ӧ����0");
    form1.ta270053009.focus();
    return false;
  }else if(form1.ta270053011.value>form1.ta270051023.value){
    alert("չ����ʼ�ղ��ܴ��ڴ���ʵ�ʵ�����("+form1.ta270051023.value+")");
    form1.ta270053011.focus();
    return false;
  //}else if(form1.ta270053011.value<form1.cmisdate.value){
  //  alert("չ����ʼ�ղ���С��ϵͳ����("+form1.cmisdate.value+")");
  //  form1.ta270053011.focus();
  //  return false;
  }else if(form1.ta270053011.value<form1.ta270051022.value){
    alert("չ����ʼ�ղ���С��ԭʵ�ʴ�������("+form1.ta270051022.value+")");
    form1.ta270053011.focus();
    return false;
  }else if(form1.ta270053012.value<=form1.ta270051023.value){
    alert("չ�ں����������Ӧ�ô��ڴ���ʵ�ʵ�����("+form1.ta270051023.value+")");
    form1.ta270053012.focus();
    return false;
  }else if(form1.ta270053005.value>form1.cmisdate.value){
    alert("չ�������ղ��ܴ���ϵͳ����("+form1.cmisdate.value+")");
    form1.ta270053005.focus();
    return false;
  }else if(trimNum(form1.ta270053013.value)/1<0){
    alert("չ�ں������Ϣ��������С��0");
    form1.ta270053013.focus();
    return false;
  }else if(trimNum(form1.ta270053014.value)/1<0){
    alert("չ�ں�������Ϣ��������С��0");
    form1.ta270053014.focus();
    return false;
  }
  if(!isEmpty(form1.ta270053015.value)){
    if(trimNum(form1.ta270053015.value)/1<=0){
      alert("չ�ں��������Ӧ����0");
      form1.ta270053015.focus();
      return false;
    }
  }
  if(!isEmpty(form1.ta270053016.value)){
    if(trimNum(form1.ta270053016.value)/1<=0){
      alert("չ�ں���������Ӧ����0");
      form1.ta270053016.focus();
      return false;
    }
  }
  if(!isEmpty(form1.ta270053017.value)){
    if(trimNum(form1.ta270053017.value)/1<=0){
      alert("չ�ں������ϢӦ����0");
      form1.ta270053017.focus();
      return false;
    }
  } 
  
  //���չ��̨��Ϊ��ȷ��ʱ��ʵ��չ�ڽ��Ϊ����
  if(form1.ta270053022.value=='1'){
    if(isEmpty(form1.ta270053010.value)){
      alert("������ʵ��չ�ڽ��");
      form1.ta270053010.focus();
      return false;
    }
  }
  if(!isEmpty(form1.ta270053010.value)){
    if(trimNum(form1.ta270053010.value)/1<=0){
      alert("ʵ��չ�ڽ��Ӧ����0");
      form1.ta270053010.focus();
      return false;
    }
    if(trimNum(form1.ta270053010.value)/1>trimNum(form1.ta270051020.value)/1){
      alert("ʵ��չ�ڽ��ܴ��ڴ������");
      form1.ta270053010.focus();
      return false;
    }
  }
  return true;
}


//ta270053012:չ�ں����������
//ta270051021:������
//ta270051016:����֤��������
//ta270053011:չ����ʼ��
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
  if(calculatedays(form1.ta270053011.value,form1.ta270053012.value)>pa){
    alert("չ�ں���������գ�չ����ʼ��<="+pa+"��");
    form1.ta270053011.focus();
    return false;
  }
  if(form1.ta270051006.value=='1'){
    if(calculatedays(form1.ta270051022.value,form1.ta270053012.value)+form1.ta270051016.value/1>pb){
      alert("չ�ں���������գ������գ�����֤��������<="+pb+"��");
      form1.ta270053012.focus();
      return false;
    }
  }else{
     if(calculatedays(form1.ta270051022.value,form1.ta270053012.value)>pb){    
       alert("չ�ں���������գ�������<="+pb+"��");
       form1.ta270053012.focus();
       return false;
     }
  }
  return true;
}


//ѡ��������
function chooserate(){
  if(isEmpty(form1.ta270053011.value)){
    alert("������չ����ʼ����");
    form1.ta270053011.focus();
    return;
  }else return true;

}

//����·��
function return2(){
  window.location=form1.return2Url.value;
}

//˫����alert
function alertday(fdate){
  isweekday(eval("form1."+fdate).value);
}

//
function alertstatus(){
  if(form1.ta270053022.value=='0'||form1.ta270053022.value=='2')
    alert("չ��״̬�޸�Ϊδȷ�ϻ����Ѿܾ����뼰ʱ�޸Ĵ���̨���еĴ���ʵ�ʵ�����");
    //return false;
  
}