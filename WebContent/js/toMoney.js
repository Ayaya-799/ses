<!--
//**************************************************************
//	��˼������������С�����
//	Creation date: (2003-05-12)	
//	@author: ecc-wangdong
//	@version: 1.0
//	@param��hidMin_money, hidMax_money, txtMin_money, txtMax_money, form
//      @param˵����
//          ������������������ƣ���С�������������ƣ����������������ƣ���С�������������ƣ�������FORM��	
//      @condition��������С���������Ѹ���ֵ
//	@��������
//	ʹ��˵����                                                             	
//	1��	�˲���jsp����������ɱ������ֵ���趨                          	
//	<%  String minAmtTxt ="0";            //Ĭ����С������ֵ              	
//	    String maxAmtTxt ="1000000000000";//Ĭ����������ֵ             	
//	   if (opCtx.getName()!=null){                                         	
//	       //��ǰ�������ݣ���С������ֵ������Լ��������λ��ʾС�����֣�	
//		minAmtTxt = (String)opCtx.getValueAt("minAmount");		       	
//	     	                                                               	
//		//��С����˼����ֵ(����С����)                                       	
//		minAmtTxt = (String)minAmtTxt.substring(0,minAmtTxt.length()-2)+"."+(String)minAmtTxt.substring(minAmtTxt.length()-2);                	
//	   	//��ǰ�������ݣ���������ֵ������Լ��������λ��ʾС�����֣�    
//        	maxAmtTxt = (String)opCtx.getValueAt("maxAmount");                     
//        	//������˼����ֵ(����С����)                                       
//        	maxAmtTxt = (String)maxAmtTxt.substring(0,maxAmtTxt.length()-2)+"."+(String)maxAmtTxt.substring(maxAmtTxt.length()-2);                
//           }                                                                   
//        %>                                                                     
//	2.	����صı�������и�ֵ
// 	<td height="28" colspan="2" bgcolor="e7E7E7"> ������������� 
//   		 <input type="text" name="txtMinAmount" size="15" style="font-size:12px" value="<%=minAmtTxt%>">Ԫ��С�� 
//    		 <input type="text" name="txtMaxAmount" size="17" style="font-size:12px" value="<%=maxAmtTxt%>">
// 	</td>
//	3.  ���÷���
//   	��ҳ��onloadʱ���ô˷���  
//	<body onLoad="iniTxtMoney('minAmount','maxAmount','txtMinAmount','txtMaxAmount','thisform')��">
//**************************************************************
function iniTxtMoney(hidMin_money,hidMax_money,txtMin_money,txtMax_money,form){
    convertToMoney(form,txtMin_money,hidMin_money);
    convertToMoney(form,txtMax_money,hidMax_money);	
}


//**************************************************************
//	�Խ�����ת���������������������
//	Creation date: (2003-05-12)	
//	@author: ecc-wangdong
//	@version: 1.0
//	@param��form, txtmoney, hidmoney
//      @param˵����
//              FORM�������������ƣ�����������
//      @condition���ñ������Ѹ���ֵ	
//**************************************************************


function convertToMoney(form,txtmoney,hidmoney){
    var tonumber;
    var re = /,/g;
    var txt_money = eval("document."+form+"."+txtmoney);
    var hid_money = eval("document."+form+"."+hidmoney);
    tonumber = txt_money.value.replace(re,"");

    txt_money.value = "";
    if (tonumber !="" && tonumber!=null){
   	rep = / /g;
		var amt = tonumber.replace(rep,"");
		
		for(var j = 0; j < amt.length; j++){
			if(isNaN(parseInt(amt.charAt(j),10)) && amt.charAt(j)!="," && amt.charAt(j)!=".") {
				alert("��������ȷ�Ľ��!");
				txt_money.value="";
				txt_money.focus();
				return;
			}
		}
		if(amt.indexOf(".")!=amt.lastIndexOf(".")){
			alert("��������ȷ�Ľ��!");
			txt_money.focus();
			return;
		}
	
		re = /,/g;
		var amt1 = amt.replace(re,"");

		var amt2=parseFloat(amt1);		
		if(amt2<0){
			alert("����Ľ��С����,����������!");
			txt_money.focus();
			return;
		}else{		//����0������;
			if(amt1.indexOf(".")!=-1){				
				var str = amt1.substr(amt1.indexOf(".")+1);				
				if(str.length>2){
					alert("����Ľ��С�����ֻ�ܱ�����λ,����������!");
					txt_money.focus();
					return;
				}else if(str.length==1){
					amt1=amt1 + "0";
				}else if(str.length<1){
					amt1=amt1 + "00";
				}
			}else{
				amt1=amt1 + ".00";
			}
			if(amt1.charAt(0)=='0' && amt1.indexOf(".")!=1){
			alert("��������ȷ�Ľ��!");
			txt_money.focus();
			return;
			}
			hid_money.value=amt1.substring(0,amt1.indexOf(".")) + amt1.substr(amt1.indexOf(".")+1);
			var temp=amt1.substring(0,amt1.indexOf("."));
			if (hid_money.value.length > 18){
			    alert("���̫��");
			    txt_money.focus();
			    return;
			}
			txt_money.value=comma(temp) + amt1.substring(amt1.indexOf("."));
			return;							
		}
    }
}
//**************************************************************
//	������ʽ���Ӷ���
//	Creation date: (2003-05-12)	
//	@author: ecc-wangdong
//	@version: 1.0
//	@param��number
//      @param˵����
//              ��ת����ֵ	
//**************************************************************

function comma(number) {
	number = '' + number;
	if (number.length > 3) {
		var mod = number.length % 3;
		var output = (mod > 0 ? (number.substring(0,mod)) : '');
		for (i=0 ; i < Math.floor(number.length / 3); i++) {
			if ((mod == 0) && (i == 0))
				output += number.substring(mod+ 3 * i, mod + 3 * i + 3);
			else
				output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
		}
		return (output);
	}
	else return number;
}
