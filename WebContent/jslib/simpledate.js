<!--
function localcanFocus(obj) {
  var tobj = obj;
  while(true) {
    if (tobj.tagName == "BODY") {
      return true;
    } else if (tobj.style.display == "none" || tobj.disabled || tobj.readOnly) {
      return false;
    } else {
      tobj = tobj.parentElement;
    }
  }
}

function isDateValid8(str){
  if(str.length == 8&&isDateValid(str))
   	return true;
  else 
  	return false;
}
function isDateValid(str){
  var sy = str.substring(0,4);
  var iy = sy/1;
  var sm = str.substring(4,6); 
  var im = sm/1;
  var sd = str.substring(6,8);
  var id = sd/1;
  if(str.length == 8){
     if(isDate(str)){
   	return true;
     }else{
        return false;
     }
  }else if(str.length == 6){
    if (iy<1900 ||iy>3000 ||im==0 ||im>12){
       return false;
    }else{
       return true;
    }
  }else if(str.length==4){
    if (iy<1900 ||iy>3000){
       return false;
    }else{
       return true;
    }
  }else return false;
}

function initArray(){
	this.length = initArray.arguments.length
	for (var i = 0; i < this.length; i++)
	this[i+1] = initArray.arguments[i]
}
var MonthArray = new initArray("01","02","03","04","05","06","07","08","09","10","11","12");
var DayArray = new initArray("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
var daysInMonth = new initArray(31,28,31,30,31,30,31,31,30,31,30,31);
function getDays(month, year) {
	//�������δ������жϵ�ǰ�Ƿ��������
	if (month == 2)
		return ((0 == year % 4) && (0 != (year % 100))) || (0 == year % 400) ? 29 : 28;
	else
		return daysInMonth[month];
}
function trimDate(v_date)
{
	if (v_date.length==11)
	{
		var y1=v_date.substr(0,4);
		var m1=v_date.substr(5,2);
		var d1=v_date.substr(8,2);
		return ""+y1+m1+d1;
	}
	else
		return v_date;
}

function decodeThisDate(name)
{
	if (name==null)
		this0=window.event.srcElement;
	else
		this0 = eval("document.all."+name);
		
	var v_date=this0.value;
	if (v_date=="")
		return true;
	if (v_date.length<11)
	{
		if (localcanFocus(this0))
		{
			this0.focus();
			this0.select();
		}
		return false;
	}
	var y1=v_date.substr(0,4);
	var m1=v_date.substr(5,2);
	var d1=v_date.substr(8,2);
	this0.value=""+y1+m1+d1;
	this0.select();

}

function encodeThisDate(name)
{
//alert(1);
	if (name==null)
		this0=window.event.srcElement;
	else
		this0 = eval("document.all."+name);
	var v_date=this0.value;
	if (v_date=="")
		return true;
	if (!isDateValid8(v_date))
	{
		if (localcanFocus(this0))
		{
//		alert("can");
			this0.focus();
			this0.select();
		}
		return false;
	}
	var y1=v_date.substr(0,4);
	var m1=v_date.substr(4,2);
	var d1=v_date.substr(6,2);
	this0.value=y1+"��"+m1+"��"+d1+"��";
}

function decodeDate(name)
{
	this0 = eval("document.all."+name+"temp");
		
	var v_date=this0.value;
	if (v_date=="")
		return true;
	if (v_date.length<11)
	{
		if (localcanFocus(this0))
		{
			this0.focus();
			this0.select();
		}
		return false;
	}
	var y1=v_date.substr(0,4);
	var m1=v_date.substr(5,2);
	var d1=v_date.substr(8,2);
	this0.value=""+y1+m1+d1;
	this0.select();

}

function encodeDate(name)
{
	this0 = eval("document.all."+name+"temp");
	var v_date=this0.value;
	if (v_date=="")
		return true;
	if (!isDateValid8(v_date))
	{
		if (localcanFocus(this0))
		{
			this0.focus();
			this0.select();
		}
		return false;
	}
	var y1=v_date.substr(0,4);
	var m1=v_date.substr(4,2);
	var d1=v_date.substr(6,2);
	this0.value=y1+"��"+m1+"��"+d1+"��";
}

function ShowTodayDate()
{
	var today=new Date(); 
	y1=today.getYear();
	m1=today.getMonth()+1;
	if (m1<10)
	   m1="0"+m1;
	d1=today.getDate();
	document.write(y1+"��"+m1+"��"+d1+"��");
}

function ShowDateSelect(name,v_date){
	var i;
	var y1="";
    var m1="";
    var d1="";
	var today=new Date(); 
if(v_date==""){
	document.write("<input size=14 maxlength=8 type=text name=\""+name+"temp\" value='' onFocus=\"decodeDate('"+name+"')\" onBlur=\"encodeDate('"+name+"')\" class=tablebody>");
	return;
}
else if(v_date==null){
	y1=today.getYear();
	m1=today.getMonth()+1;
	if (m1<10)
	   m1="0"+m1;
	d1=today.getDate();
}
else{
	if (v_date.length==4)
        {
          y1 = v_date;
        }
    else if(v_date.length == 6)
        {
          y1 = v_date.substr(0,4);
	  m1 = v_date.substr(4,2);
        }
    else if(v_date.length == 8)
        {
          y1 = v_date.substr(0,4);		  
          m1 = v_date.substr(4,2);
	  d1 = v_date.substr(6,2);
        }
}
	document.write("<input size=14 maxlength=8 type=text name=\""+name+"temp\" value='"+y1+"��"+m1+"��"+d1+"��"+"' onFocus=\"decodeDate('"+name+"')\" onBlur=\"encodeDate('"+name+"')\" class=tablebody>");
}

function changelocation(name){
	this0 = eval("document.all."+name+"year");
	this1 = eval("document.all."+name+"month");
	this2 = eval("document.all."+name+"day");
	var tmpmonth = this1.selectedIndex+1;
	var tmpyear = parseInt(this0.value);
	//window.alert(tmpyear);
	var daycount = getDays(tmpmonth,tmpyear);
	//var daycount = getDays(9,tmpyear);
	//window.alert("tmpyear:"+tmpyear+" "+"tmpmonth:"+tmpmonth+"  daycount"+daycount);
	this2.length = 0; 
    var i;
    this2.options[0] = new Option('01','01');
    for (i=1;i<daycount; i++)
        {
            this2.options[i] = new Option(DayArray[i+1],DayArray[i+1]);  
        }        
}
function chgYear(name){
	//window.alert("��ݸı���"+name);
	var i = 0;
	this0 = eval("document.all."+name+"year");
	this1 = eval("document.all."+name+"month");
	this2 = eval("document.all."+name+"day");
	//����Ϸ��Ե�У�飻
	if (this0.value=="")
	{
		window.alert("���������!");
		this0.focus();
		return false;
	}
	if (this0.value.length < 4)
	{
		window.alert("������벻��ȷ������������!");
		this0.focus();
		return false;
	}
	for (i;i<4;i++)
	{
		if (isNaN(this0.value.charAt(i)))
		{
			window.alert("������벻��ȷ������������!");
			this0.focus();
			return false;
		}
	}
	//�Ƿ�Ϊ����;
	if (this1.value=="02")
	{
		var year = parseInt(this0.value);
		if (((year%4==0)&&(year%100!=0))||(year%400==0))
		{
			this2.options[0] = new Option('01','01');
			for (i=1;i<29; i++)
			{
				this2.options[i] = new Option(DayArray[i+1],DayArray[i+1]);  
			} 
		}
	}
	return true;
}
//�����ѡ������ڣ�
function getSelectDate(name){
	this0 = eval("document.all."+name+"temp");
/*	String tmp=String(this0.value);
	if (tmp.length<*/
	decodeDate(name);
	var riqi = this0.value;
	return riqi;
}
/**
 *��������
 *�������:��ʼ���ڣ���������
 �����Ϊ:��ʼ���ڵ��������ڰ���������
 */
/*
function calculatedays(startdate,enddate){
	var oneday=24*60*60*1000;  //����һ��ĺ�����=24Сʱ��60���ӡ�60���1000
	if(parseInt(startdate)>parseInt(enddate)){
		var tmp=startdate;
		startdate=enddate;
		enddate=tmp;		
	}
	//��ʼʱ��
	var sy="";
	var sm="";
	var sd="";
	sy = startdate.substr(0,4);		  
	if(startdate.substr(4,1)=="0")
	  sm = startdate.substr(5,1);
	else
	  sm = startdate.substr(4,2);
	if(startdate.substr(6,1)=="0")
	  sd = startdate.substr(7,1);
	else
	  sd = startdate.substr(6,2);
	//����ʱ��
	var ey="";
	var em="";
	var ed="";
	ey = enddate.substr(0,4);		  
	if(enddate.substr(4,1)=="0")
	  em = enddate.substr(5,1);
	else
	  em = enddate.substr(4,2);
	if(enddate.substr(6,1)=="0")
	  ed = enddate.substr(7,1);
	else
	  ed = enddate.substr(6,2);
	//���ÿ�ʼʱ��
	var theSDate = new Date();
	theSDate.setFullYear(parseInt(sy));
	theSDate.setMonth(parseInt(sm)-1);
	theSDate.setDate(parseInt(sd));
	var sdate=Date.parse(theSDate.toGMTString());	
	//���ý���ʱ��
	var theEDate = new Date();
	theEDate.setFullYear(parseInt(ey));
	theEDate.setMonth(parseInt(em)-1);
	theEDate.setDate(parseInt(ed));
	var edate=Date.parse(theEDate.toGMTString());
	
	var sedate=(edate-sdate)/oneday;
	return sedate;
}
*/
function compareDate6month(startdate,enddate){
	if(parseInt(enddate) < parseInt(startdate)){
		alert("��ʼʱ�����С�ڵ��ڽ���ʱ��");
	}
	//��ʼʱ��
	var sy="";
	var sm="";
	var sd="";
	sy = startdate.substr(0,4);		  
	if(startdate.substr(4,1)=="0")
	  sm = startdate.substr(5,1);
	else
	  sm = startdate.substr(4,2);
	if(startdate.substr(6,1)=="0")
	  sd = startdate.substr(7,1);
	else
	  sd = startdate.substr(6,2);
	//����ʱ��
	var ey="";
	var em="";
	var ed="";
	ey = enddate.substr(0,4);		  
	if(enddate.substr(4,1)=="0")
	  em = enddate.substr(5,1);
	else
	  em = enddate.substr(4,2);
	if(enddate.substr(6,1)=="0")
	  ed = enddate.substr(7,1);
	else
	  ed = enddate.substr(6,2);

	var interMonth = (ey*1 - sy*1)*12+em*1 - sm*1;

	if(interMonth > 6)
		return false;
	else if(interMonth < 6)
		return true;
	else{
		if(ed*1>sd*1) return false;
		else return true;
	}
}
function isDate(theStr) {
      var sy = theStr.substring(0,4);
      var iy = sy/1;
      var sm = theStr.substring(4,6);
      var im = sm/1;
      var sd = theStr.substring(6,8);
      var id = sd/1;
      if (theStr.length!=8)   return false;
	  if(im==0 ||im>12)  return false;
         var month1 = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
         var month2 = new Array(31,29,31,30,31,30,31,31,30,31,30,31);
         if((iy%4 == 0)&&(iy%400 ==0)&&(iy%100 == 0)){
             if((id <= month1[im-1])&&(id >=1 ))
   	         return true;
             else
                 return false;
         }else{
            if((id <= month2[im-1])&&(id >= 1))
                return true;
             else
                return false;
         }
}
function formatDate(indate)
{
	if (indate.length!=8)
		return indate;
	var y1=indate.substr(0,4);
	var m1=indate.substr(4,2);
	var d1=indate.substr(6,2);
	return y1+"��"+m1+"��"+d1+"��";

}
//-->