// TCCargoApply.js

function query(){
  if(check()){
    if(isEmpty(form1.Apply_contractID.value)){
      alert(" ����������ţ� ");
      form1.Apply_contractID.focus();
      return ;
    }
    
    form1.oper.value="query";    
    document.form1.submit();
  }
}


/*ѡ����֤��*/
function chooseLC_Code()
{
  if(isEmpty(form1.Apply_customerCode.value)){

    alert(" ������ͻ����룡 ");
    form1.Apply_customerCode.focus();
    return ;
  }
  if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return ;
  }else{
  var customerCode = form1.Apply_customerCode.value;		//�ͻ����� 
  var ts = window.showModalDialog(encodeURL(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.TC.util.queryAllLC&ask=true&customerCode="+customerCode+"&areacode="+form1.Apply_sponsorBank.value+"&domain=international&zhuxiaoflag=0&height=410&hasDetailLink=true"),window,"dialogWidth:640px;dialogHeight:458px;center:yes;help:no;status:no;resizable:no");

  if (ts != null){
     form1.LC_code.value = ts[1];		//����֤��
     form1.LC_Ctype.value=ts[6];		//����֤����
     form1.Balance.value=ts[8];		//����֤ʵ�����
     Lcyxq = ts[12];		//����֤��Ч��
   }
  }
}
 /*����֤ʵ�����Ϊ��ʱ����δ��������֤����ʱ���������������*/
function check()
{
  if(isEmpty(form1.Apply_customerCode.value))
  {
    alert(" ������ͻ����룡 ");
    form1.Apply_customerCode.focus();
    return false;
  }
  if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return false;
      }
  if(isEmpty(form1.LC_code.value)){
    alert(" ����������֤�ţ� ");
    form1.LC_code.focus();
    return false;
  }

  if(form1.Balance.value=='null'||isEmpty(form1.Balance.value)){
    alert(" ��ǰ����֤��δ��ʽ���������ܽ��к���ҵ��Ĵ��� ");
    form1.LC_code.focus();
    return false;
  }
  
  return true;
}  
   
function add(){
   if(check()){ 
   	if(Lcyxq < sys_date){ 
   	  alert(" ��ǰ����֤�Ѿ�ʧЧ�����ܽ���������");
      return ;
    }else{
    	form1.Apply_contractID.value ="";  
      form1.oper.value="addNew";      
      document.form1.submit();
    }
  }
  
}

function chooseContractID(){
    if(isEmpty(form1.Apply_customerCode.value)){

    alert(" ������ͻ����룡 ");
    form1.Apply_customerCode.focus();
    return ;
  }if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return ;
  }
  if(isEmpty(form1.LC_code.value)){

    alert(" ����������֤��ţ� ");
    form1.LC_code.focus();
    return ;
  } else{
    var customerCode = form1.Apply_customerCode.value;		//�ͻ�����
    var LCID=form1.LC_code.value;
    var ts = window.showModalDialog(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.TC.util.queryCargoAppID&ask=true&customerCode="
     +customerCode+"&area="+form1.Apply_sponsorBank.value+"&LC_code="+LCID+"&height=410&hasDetailLink=true",window,"dialogWidth:640px;dialogHeight:458px;center:yes;help:no;status:no;resizable:no");
   if (ts != null){
     form1.Apply_contractID.value = ts[2];			//ѡ��������������
   }
  }
}
   
  