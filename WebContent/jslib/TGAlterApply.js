// TGAlterApply.js


function query(){
if(isEmpty(form1.Apply_customerCode.value)){
		alert("��������ͻ��ţ�");
		form1.Apply_customerCode.focus();
		return ;
	}
	 if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return ;
      }
	if(isEmpty(form1.Apply_baseID.value)){
		alert("�������뱣����ţ�");
		form1.Apply_baseID.focus();
		return ;
	}
	if(isEmpty(form1.Apply_contractID.value)){
		alert("�������뱣������ţ�");
		form1.Apply_contractID.focus();
		return ;
	}
	form1.oper.value = "queryDB";
	form1.submit();
}

   
   
function add(){
   if(isEmpty(form1.Apply_customerCode.value)){
		alert("��������ͻ��ţ�");
		form1.Apply_customerCode.focus();
		return ;
	}
	 if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return ;
      }
	if(isEmpty(form1.Apply_baseID.value)){
		alert("�������뱣����ţ�");
		form1.Apply_baseID.focus();
		return ;
	}
	if(isEmpty(form1.lgbgType.value)){
		alert("�������뱣��������࣡");
		form1.lgbgType.focus();
		return ;
	}
	form1.Apply_contractID.value ="";
	form1.oper.value = "showNew";
	form1.submit();
}

function chooseContractID(){
     if(isEmpty(form1.Apply_customerCode.value)){
		alert("��������ͻ��ţ�");
		form1.Apply_customerCode.focus();
		return ;
	} 
	if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return ;
      }	
	var ts=window.showModalDialog(encodeURL(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.TG.util.queryLGNO&ask=true&customerCode="+form1.Apply_customerCode.value+"&areacode="+form1.Apply_sponsorBank.value+"&domain=international&kailiflag=true&height=410&hasDetailLink=true"),window,"dialogWidth:640px;dialogHeight:458px;center:yes;help:no;status:no;resizable:no");
	if(ts!=null){
 		form1.Apply_baseID.value=ts[1];
		form1.ISEELGCode.value=ts[2];
        form1.LGCurrencyType.value=ts[5];
        form1.LGFormerAmt.value=ts[4];
		form1.Apply_contractID.value = ""; //��ֹ��ѡ
 	}
   }
   
 //ѡ����������
function f_SelectLGBG() {
	if(isEmpty(form1.Apply_customerCode.value)){
		alert("��������ͻ��ţ�");
		form1.Apply_customerCode.focus();
		return ;
	}
	if(isEmpty(form1.Apply_sponsorBank.value)){
        alert("��ѡ��ҵ��������");
        return ;
      }
	if(isEmpty(form1.Apply_baseID.value)){
		alert("�������뱣����ţ�");
		form1.Apply_baseID.focus();
		return ;
	}
	 
	if(isEmpty(form1.lgbgType.value)){
		alert("�������뱣��������࣡");
		form1.lgbgType.focus();
		return ;
	}
	var ts=window.showModalDialog(encodeURL(basepath0606+"/util/util_Query.jsp?queryType=icbc.cmis.TG.util.queryLGAlterApplyNum&ask=true&customerCode="+form1.Apply_customerCode.value+"&LGCode="+form1.Apply_baseID.value+"&lgbgType="+form1.lgbgType.value+"&height=410&hasDetailLink=true"),window,"dialogWidth:640px;dialogHeight:458px;center:yes;help:no;status:no;resizable:no");
	if(ts!=null){
 		form1.Apply_contractID.value=ts[1];
 	}
}