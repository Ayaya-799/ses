var webBasePath = "/icbc/bift";
// tools_bift.js
var zero = "000000000000000000000000000000000000000000000000000000000000000";
function gohome(){
	location.href = webBasePath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.servlets.CmisMain";
}

function initArray(){
	this.length = initArray.arguments.length;
	for (var i = 0; i < this.length; i++)
	this[i+1] = initArray.arguments[i];
}
//�����Сд����󣬶��ŷָ�ʵ��;
//����Ĳ���Ϊ��input������name����;
function commaSep(str)
{
	var this1 = eval("document.all."+str);
 	var snum1 = this1.value;
	if(snum1 == "" || snum1 == null){
   		//window.alert("��������");
	   	//this1.focus();
   		return false;
	}
	for(var i = 0;i<snum1.length;i++){
  		if(snum1.charAt(i)!="."&&snum1.charAt(i)!=","){
     			if(isNaN(snum1.charAt(i))){
         			alert("��������������������");
         			this1.focus();
         			return false;
     			}
  		}
	}
	var dot_l = snum1.indexOf(".");
	var snum;
	if(dot_l == -1){
  		snum = snum1;
	}
	else{
   		var dot_l2 = snum1.indexOf(".",dot_l+1);
   		var dot_l3 = snum1.indexOf(",",dot_l+1);
   		if((dot_l2 != -1)||(dot_l3!=-1)){
        		alert("��������������������");
        		this1.focus();
        		return false;
   		}
   		snum= snum1.substring(0,dot_l);
	}
	var dnum="";
	for(var i =0;i< snum.length;i++){
   		if('0'<= snum.charAt(i) && snum.charAt(i)<='9')
     			dnum = dnum + snum.charAt(i);
	}
	var j =0;
	var tnum ="";
	for(var i = dnum.length-1;i>=0;i--){
	   	if((j%3) == 0){
	       		if(j!=0)
	       			tnum = "," + tnum;
	   	}
	   	tnum =  dnum.charAt(i)+tnum;
	   	j++;
	}
	if(dot_l ==-1)
		tnum = tnum +".00";
	else{
  		if(snum1.substring(dot_l).length == 1)
    			tnum = tnum +".00";
  		else if(snum1.substring(dot_l).length == 2)
    			tnum = tnum +"."+snum1.substring(dot_l+1)+"0";
  		else
    			tnum = tnum +"."+snum1.substring(dot_l+1,dot_l+3);
	}
	this1.value = tnum;
	return true;
}
/**
 *���ַ���str�в����ַ�sep,��ȥ������str���е�sep�ַ�
 */
function delsep(str,sep){
	while(str.search(sep)!=-1)
		str=str.replace(sep,"");	
	return str;
}
function amountfocus(name){
	var this1=eval("document.all."+name);
	this1.value = delsep(this1.value,',');
}

//��Сд�������ֵת���ɴ�д��ֵ��
//Ҽ���������������飬½���⣬�ƣ������֣�ʰ���ۣ�Ǫ������
var UpcaseMoney = new initArray("��","Ҽ","��","��","��","��","½","��","��","��");
var Jinwei = new initArray("","ʰ","��","Ǫ","��","��","��");
function ddd(snumInt)
{
	var i=0;
	var out1 = "";
	var out2 = "";
	var out3 = "";		
	var index1 = snumInt.length;
	for (i=0;i<snumInt.length;i++)
	{
		out1 += UpcaseMoney[parseInt(snumInt.charAt(i))+1];
		if (parseInt(snumInt.charAt(i))==0)
		{
			out1 += "";
		}
		else{
			out1 += Jinwei[index1];
		}
		index1--;
	}
	for (i=0;i<out1.length;i++)
	{
		if (out1.charAt(i)!=out1.charAt(i+1))		
			out2 += out1.charAt(i);
	}
	if (out2.charAt(out2.length-1)=="��")
	{
		for (i=0;i<out2.length-1;i++)
		{
			out3 += out2.charAt(i);
		}
	}
	else{
		out3 = out2;
	}
	return out3;
}
/**
 * Last Modified Date  20041208
 * Modified by Zhou Ping Ping
 */

function moneyLowcase2Upcase(sstr,dstr){
	var i;
	var this2 = eval("document.all."+sstr);
	var this3 = eval("document.all."+dstr);
 	var snum2 = this2.value;
	if(snum2 == ""){
   		//alert("��������");
	   	//this2.focus();
		this3.value="";
   		return false;
	}
	for(var i = 0;i<snum2.length;i++){
  		if(snum2.charAt(i)!="."&&snum2.charAt(i)!=","){
     			if(isNaN(snum2.charAt(i))){
         			//alert("��������������������");
         			//this2.focus();
					this3.value="";
         			return false;
     			}
  		}
	}
	var result1 = "";
	var result2 = "";
	var result3 = "";
	var tmpsnum = snum2;
	while(tmpsnum!=tmpsnum.replace(',','')){
		tmpsnum = tmpsnum.replace(',','')
	}	
	var snumFloat = parseFloat(tmpsnum);
	var snumInt = parseInt(tmpsnum);
	//window.alert(snumInt+"float:"+snumFloat);
	//window.alert(snumFloat+"   "+parseInt(snumFloat));
	if(snumInt.toString().length<5)
	{
		result1 = ddd(snumInt.toString());
		result1 += "Ԫ";
		if (snumFloat==snumInt)
		{
			result1 += "��";
		}
		else   //������ʾС�����ֵĺ���eee(snumFloat)
		{
			result1 += eee(snumFloat);
		}
		this3.value = result1;
	}
	else if (snumInt.toString().length<9)
	{
		//window.alert(snumInt.toString().substr(0,snumInt.toString().length-4));
		result2 = ddd(snumInt.toString().substr(0,snumInt.toString().length-4));
		result2 += "��";
		//window.alert(snumInt.toString().substr(snumInt.toString().length-4,snumInt.toString().length-1));
		result1 = ddd(snumInt.toString().substr(snumInt.toString().length-4,snumInt.toString().length-1));
		result1 += "Ԫ";
		if (snumFloat==snumInt)
		{
			result1 += "��";
		}
		else   //������ʾС�����ֵĺ���eee(snumFloat)
		{
			result1 += eee(snumFloat);
		}
		//result1 += "Ԫ��";
		this3.value = result2 + result1;
	}
	else if (snumInt.toString().length<13)
	{
		//window.alert(snumInt.toString().substr(0,snumInt.toString().length-8));
		result3 = ddd(snumInt.toString().substr(0,snumInt.toString().length-8));
		result3 += "��";
		//window.alert(snumInt.toString().substring(snumInt.toString().length-8,snumInt.toString().length-4));
		result2 = ddd(snumInt.toString().substring(snumInt.toString().length-8,snumInt.toString().length-4));
		if (result2!="")
		{
			result2 += "��";
		}
		//window.alert(snumInt.toString());
		//window.alert(snumInt.toString().substr(snumInt.toString().length-4,snumInt.toString().length));
		result1 = ddd(snumInt.toString().substr(snumInt.toString().length-4,snumInt.toString().length));
		result1 += "Ԫ";
		if (snumFloat==snumInt)
		{
			result1 += "��";
		}
		else   //������ʾС�����ֵĺ���eee(snumFloat)
		{
			//window.alert("eee return"+eee(snumFloat));
			result1 += eee(snumFloat);
		}
		//result1 += "Ԫ��";
		this3.value = result3 + result2 + result1;
	}
	else
		window.alert("���̫��");	
		
	//---------------�����ΪС��1��С��ʱ ȥ����һλ"Ԫ"----------------------
	if(snum2<1 && snum2>0){this3.value=this3.value.substring(1);} 	
	//-----------------------------------------------------------------------
	
	//---------------�����ΪС��1��С��ʱ ȥ����һλ"Ԫ"----------------------
	if(snum2==0){this3.value="";} 	
	//-----------------------------------------------------------------------
	
	return true;
}

//��ʾС�����֣�ȡ��Ч������λ��
function eee(snumFloat){
	var tmp1="";
	if (snumFloat==parseInt(snumFloat))
	{
		tmp1 += "��";
		return tmp1;
	}
	else
	{
		var x =  parseInt(snumFloat);
		var xx = snumFloat.toString().substr(x.toString().length+1,2);
		if (xx.length == 1)	
		{
			xx += "0";		//���С�������һλ��˵����ʾ��ʮ�֣�Ӧ���������
		}
		if (xx=="00")
		{
			tmp1 += "��";
			return tmp1;
		}
		else
		{
			//window.alert(xx);
			var tmp2 = parseInt(xx);
			if (tmp2<10)
			{
				tmp1 += "��" + UpcaseMoney[tmp2+1] + "��"; 
				return tmp1;
			}
			else if(tmp2==10)
			{
				tmp1 += "Ҽ��";//tmp1 += "ʰ��";
				return tmp1;
			}
			else if (tmp2%10==0)
			{
				tmp1 += UpcaseMoney[tmp2/10+1] + "����";//"ʰ��";
				return tmp1;
			}
			else 
			{
				tmp1 += UpcaseMoney[(tmp2-tmp2%10)/10+1] + "��" + UpcaseMoney[tmp2%10+1] + "��";
				return tmp1;
			}
		}
	}
}

var alphabet = new Array('A','B','C','D','E','F','G',
						 'H','I','J','K','L','M','N',
						 'O','P','Q','R','S','T',
						 'U','V','W','X','Y','Z');
				  /*Array('a','b','c','d','e','f','g',
						 'h','i','j','k','l','m','n',
						 'o','p','q','r','s','t',
						 'u','v','w','x','y','z');*/
function Asc2Char(chr)
{
	if (chr < 97)
	{
		chr += 32;
	}
	chr -= 97;
	return alphabet[chr];
}
function round(val)
{
	return Math.round(val);
}
function round2(val)
{
	return roundn(val,2);
}
function round4(val)
{
	return roundn(val,4);
}
function roundn(val,decimal) {
	var tmp,part,smalllen;
	if (decimal<=0||decimal==null)//��������λ������������쳣���
		return Math.round(val);
		
	tmp=new String(val);
	part=tmp.split('.');

	if (part.length!=2)
		return val;//����һ��'.'����������ʱ

	smalllen=part[1].length;
	if (smalllen>decimal)
		smalllen=decimal;
	if (part[1].charAt(decimal)<5)
	{//����
		part[1]=part[1].substr(0,decimal);
	}
	else
	{//����
		part[1]=part[1].substr(0,decimal);
		var aa=new String(part[1]);
		part[1]=new String(parseInt(part[1],10)+1);
	}
	
	if (part[1].length<smalllen) //С��λǰ��0
		part[1]=zero.substr(0,smalllen-part[1].length)+part[1];
	else if (part[1].length>smalllen)//����С����λ
	{
		if (part[0].charAt(0)=='-')
			part[0]=parseInt(part[0],10)-1;
		else
			part[0]=parseInt(part[0],10)+1;
		part[1]="0";
	}
	
	return parseFloat(part[0]+'.'+part[1]);
}