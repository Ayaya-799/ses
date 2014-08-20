var webBasePath = "/icbc/bift";

// choose_bift.js

function formatDate(indate)//copy from simple_date.js
{
	if (indate.length!=8)
		return indate;
	var y1=indate.substr(0,4);
	var m1=indate.substr(4,2);
	var d1=indate.substr(6,2);
	return y1+"��"+m1+"��"+d1+"��";

}
/** ��齻������(8λ)�Ƿ�ڼ���
  * ���÷�������:util_check_settle_date('20040229')
  */
function util_check_settle_date(thisday)
{
	var workday=util_get_next_workdate(thisday);
	if (workday==null)//ȡ��һ�����ճ���
		return false;
	if (thisday!=workday)//���ָ�������ǽڼ���
		return confirm("������"+formatDate(thisday)+"�ǽڼ��գ���һ������Ϊ:"+formatDate(workday)+"\n�Ƿ������");
	else//���ָ�����ڲ��ǽڼ���
		return true;
}
/** ����ָ������(8λ)����һ��������,null��ʾ����
  * ���÷�������:get_next_workdate('20040229')
  */
function util_get_next_workdate(thisday) {
  //��֯��ѯ����
  var qstr = "method=getNextWorkdate&thisday=" + thisday;

  //��ѯ
  objHTTP.Open('GET',webBasePath+'/servlet/icbc.cmis.bift.util.CUtilServlet?'+qstr,false);
  objHTTP.Send();

  //��ѯ������
  var xml = objHTTP.responseText;
  //alert(xml);
  if(!parser.loadXML(xml)) {
//    alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
    return null;
  }
  error = parser.getElementsByTagName("error");
  if(error.length > 0) {
    alert(error.item(0).text);
    return null;
  }
  //��ѯ��ȷ���������
  //��ȡ����ֵ
  var para_value = parser.documentElement.getAttribute("value");

  return para_value;
}

/** ��Ȩ
  * ���÷�������:var ts=util_authorize("�������","�޸Ŀͻ���Ϣ"+form1.ta390050001.value)
  * alert(ts);//��Ȩ��Ա��
  */
function util_authorize(module,info) {
  return window.showModalDialog(webBasePath+"/bift/util/util_Authorize.jsp",new Array(module,info),"dialogWidth:295px;dialogHeight:190px;center:yes;help:no;status:no;resizable:yes");
}

/** ѡ�����
  * ���÷�������:var ts=util_choose_area(s_start_area_code)//�磺util_choose_area(<%=sm.getSessionData("AreaCode")%>)
  * s_start_area_code://��s_start_area_codeָ���Ļ�����ʼѡ��(һ��ȡ�ñ���Ա���ڻ�������)
  * alert(ts.substring(1,1));//��������
  * alert(ts.substring(1,9));//������
  * alert(ts.substring(10,ts.length));//������
  */
function util_choose_area(s_start_area_code,s_range)
{
  if (s_range==null)
  	s_range="01110";
  //����,һ���У�׼һ���������У�֧��
  //��������ѡ��һ���С�׼һ���С�������

  return window.showModalDialog(webBasePath+"/util/util_ChooseAreaNominit.jsp?area="+s_start_area_code+"&range="+s_range+"&time=" + (new Date)+"&opDataUnclear=true",window,"dialogWidth:380px;dialogHeight:280px;center:yes;help:no;status:no;resizable:no");
}

/** ѡ��ծȯ
  * ���÷�������:var ts=util_choose_bond()
  *              var ts=util_choose_bond(bondtype)//ָ��ծȯ��2�ͣ�ta390001006��
  *	alert(ts[1]);//��Ӧta390001001
  *	alert(ts[2]);//��Ӧta390001002
  *	alert(ts[3]);//��Ӧta390001003
  *	alert(ts[4]);//��Ӧta390001004
  *	alert(ts[5]);//��Ӧta390001005
  * ...
  *	alert(ts[48]);//��Ӧta390001048
  */
function util_choose_bond(type,status)
{
  if (type==null)
  	type="";
  if (status==null)
    status="01";
  var ret=["�벻Ҫȡts[0]"];
  var ts=window.showModalDialog(webBasePath+"/bift/util/util_Query.jsp?ta390001006="+type+"&ta390001048="+status+"&queryType=icbc.cmis.bift.util.CChooseBondDAO&QueryPage=/bift/util/bift_util_choose_bond_input.jsp&width=700&height=400&hasDetailLink=new"+"&opDataUnclear=true",window,"dialogWidth:788px;dialogHeight:526px;center:yes;help:no;status:no;resizable:yes");
  if (ts==null)
  	return ts;
  else 
    return ret.concat(ts);
}

/** ѡ��ծȯ
  * ���÷�������:var ts=util_choose_bond()
  *              var ts=util_choose_bond(bondtype)//ָ��ծȯ��2�ͣ�ta390001006��
  *	alert(ts[1]);//��Ӧta390001001
  *	alert(ts[2]);//��Ӧta390001002
  *	alert(ts[3]);//��Ӧta390001003
  *	alert(ts[4]);//��Ӧta390001004
  *	alert(ts[5]);//��Ӧta390001005
  * ...
  *	alert(ts[48]);//��Ӧta390001048
  */
function util_choose_all_bond(type)
{
  if (type==null)
  	type="";
    return util_choose_bond(type,'');
}

 /** ѡ���Ա
   * ���÷�������:var ts=util_choose_employee(area,major,eClass)
   * area�������ţ���������ö��ŷָ�(�硱12020010,12020020��)
   * major��רҵ�����רҵ�ö��ŷָ�(�硱099,210��)��Ӧ�ֵ������mag_major ��
   * eClass����ѡ��Ա��ɫ�������ɫ�ö��ŷָ����硱5,6������Ӧ�ֵ������mag_employee_class ��
   *		 ծȯ��ɫ�����������ĵ�Ϊ׼����6-�쵼 7-�������Ա 8-���չ���Ա 9-ծȯ����Ա 10-����Ա
   * eCode�������Ա�ö��ŷָ�(�硱120200199,120200200��)
   * includeSelf���Ƿ�����ֶ�eCode�еĹ�Ա��true|false<br>
   * multiSelect����ѡ�� true|false<br>
   * alert(ts[0][1]);//��Ա��
   * alert(ts[0][2]);//����
   * alert(ts[0][3]);//רҵ��
   * alert(ts[0][4]);//רҵ����
   * alert(ts[0][5]);//��ɫ
   * alert(ts[0][6]);//��ɫ����
   */
function util_choose_employee(area,major,eClass,eCode,includeSelf,multiSelect)
{
  if (eCode==null)
  	eCode="none";
  if (includeSelf==null)
  	includeSelf=true;
  if (multiSelect==null)
  	multiSelect=false;
  	
  var qstr = webBasePath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=ChooseEmployee&eCodeWsA342d=" + eCode + "&areaWsA342d=" + area + "&majorWsA342d=" + major + "&eClassWsA342d=" + eClass + "&includeSelfWsA342d=" + includeSelf + "&multiSelectWsA342d=" + multiSelect + "&time=" + new(Date)+"&opDataUnclear=true";
  return window.showModalDialog(qstr,window,"dialogWidth:370px;dialogHeight:350px;center:yes;help:no;status:no;resizable:yes");
}

 /** ��ѡ���Ա������������������ѡ���Ա��
   * ���÷�������:var ts=util_choose_employee_new(area,major,eClass)
   * area����������(�硱00000001��)���û����������������Ĺ�Ա������ѡ��
   * major��רҵ�����רҵ�ö��ŷָ�(�硱099,210��)��Ӧ�ֵ������mag_major ��
   * eClass����ѡ��Ա��ɫ�������ɫ�ö��ŷָ����硱5,6������Ӧ�ֵ������mag_employee_class ��
   *		 ծȯ��ɫ�����������ĵ�Ϊ׼����6-�쵼 7-�������Ա 8-���չ���Ա 9-ծȯ����Ա 10-����Ա
   * eCode�������Ա�ö��ŷָ�(�硱120200199,120200200��)
   * includeSelf���Ƿ�����ֶ�eCode�еĹ�Ա��true|false<br>
   * multiSelect����ѡ�� true|false<br>
   * alert(ts[0][1]);//��Ա��
   * alert(ts[0][2]);//����
   * alert(ts[0][3]);//רҵ��
   * alert(ts[0][4]);//רҵ����
   * alert(ts[0][5]);//��ɫ
   * alert(ts[0][6]);//��ɫ����
   * alert(ts[0][7]);//����������
   * alert(ts[0][8]);//����������
   */
function util_choose_employee_new(area,major,eClass,eCode,includeSelf,multiSelect)
{
  area='00000000';
  if (eCode==null)
  	eCode="none";
  if (includeSelf==null)
  	includeSelf=true;
  if (multiSelect==null)
  	multiSelect=false;
  	  
  var qstr = webBasePath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.ChooseBelongEmp&eCodeWsA342d=" + eCode + "&areaWsA342d=" + area + "&majorWsA342d=" + major + "&eClassWsA342d=" + eClass + "&includeSelfWsA342d=" + includeSelf + "&multiSelectWsA342d=" + multiSelect + "&time=" + new(Date)+"&opDataUnclear=true";
  return window.showModalDialog(qstr,window,"dialogWidth:370px;dialogHeight:350px;center:yes;help:no;status:no;resizable:yes");
}


/** ѡ��ͻ�
  * ���÷�������:var ts=util_choose_client()//ѡ��״̬������ȫ���ͻ�
  * ���÷�������:var ts=util_choose_client('02')//ѡ��״̬�����Ĵ���ͻ�
  *	alert(ts[1]);//��Ӧta390050001
  *	alert(ts[2]);//��Ӧta390050002
  *	alert(ts[3]);//��Ӧta390050003
  *	alert(ts[4]);//��Ӧta390050004
  *	alert(ts[5]);//��Ӧta390050005
  *	alert(ts[6]);//��Ӧta390050006
  *	alert(ts[7]);//��Ӧta390050007
  *	alert(ts[15]);//��Ӧta390050015
  */
function util_choose_client(ta390050005,ta390050028)
{
  var ret=["�벻Ҫȡts[0]"];
  var para="";
  if (ta390050005==null)
  	ta390050005="";
  if (ta390050028==null)
    ta390050028="01";
  para=para+"&ta390050005="+ta390050005+"&ta390050028="+ta390050028;
    
  var ts=window.showModalDialog(webBasePath+"/bift/util/util_Query.jsp?queryType=icbc.cmis.bift.util.CChooseClientDAO&QueryPage=/bift/util/bift_util_choose_client_input.jsp"+para+"&width=700&height=480&hasDetailLink=new"+"&opDataUnclear=true",window,"dialogWidth:800px;dialogHeight:570px;center:yes;help:no;status:no;resizable:yes");
  if (ts==null)
  	return ts;
  else 
    return ret.concat(ts);
}

/** ��Ʊ�������޸Ľ�����Ϊ��ת�����ʱѡ��ͻ�
  * �����ͬ��major��ѡ�ͻ�ʱ��������Ҫ�����������
  * by zly 2004.08
  */
function util_choose_client_major(ta390050005,ta390050028,major)
{
  var ret=["�벻Ҫȡts[0]"];
  var para="";
  if (ta390050005==null)
  	ta390050005="";
  if (ta390050028==null)
    ta390050028="01";
  //Ĭ�������ȡ��Ʊ  
  if (major==null)
    major="914";  
  para=para+"&ta390050005="+ta390050005+"&ta390050028="+ta390050028+"&major="+major;
    
  var ts=window.showModalDialog(webBasePath+"/bift/util/util_Query.jsp?queryType=icbc.cmis.bift.util.CChooseClientMajorDAO&QueryPage=/bift/util/bift_util_choose_client_input.jsp"+para+"&width=700&height=480&hasDetailLink=new"+"&opDataUnclear=true",window,"dialogWidth:800px;dialogHeight:570px;center:yes;help:no;status:no;resizable:yes");
  if (ts==null)
  	return ts;
  else 
    return ret.concat(ts);
}

/** ѡ��ͻ�
  * ���÷�������:var ts=util_choose_all_client()//ѡ��ȫ���ͻ�
  * ���÷�������:var ts=util_choose_all_client('02')//ѡ��ȫ������ͻ�
  *	alert(ts[1]);//��Ӧta390050001
  *	alert(ts[2]);//��Ӧta390050002
  *	alert(ts[3]);//��Ӧta390050003
  *	alert(ts[4]);//��Ӧta390050004
  *	alert(ts[5]);//��Ӧta390050005
  *	alert(ts[6]);//��Ӧta390050006
  *	alert(ts[7]);//��Ӧta390050007
  *	alert(ts[15]);//��Ӧta390050015
  */
function util_choose_all_client(ta390050005)
{
  if (ta390050005==null)
  	ta390050005="";
  return util_choose_client(ta390050005,'');
}

var objHTTP = new ActiveXObject("Microsoft.XMLHTTP")  
var parser=new ActiveXObject("microsoft.xmldom")
parser.async="false"

function util_get_genpara(paratype,paracode) {
  //��֯��ѯ����
  var qstr = "method=getGenPara&paratype=" + paratype + "&paracode=" + paracode;

  //��ѯ
  objHTTP.Open('GET',webBasePath+'/servlet/icbc.cmis.bift.util.CUtilServlet?'+qstr,false);
  objHTTP.Send();

  //��ѯ������
  var xml = objHTTP.responseText;
  //alert(xml);
  if(!parser.loadXML(xml)) {
//    alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
    return null;
  }
  error = parser.getElementsByTagName("error");
  if(error.length > 0) {
    alert(error.item(0).text);
    return null;
  }
  //��ѯ��ȷ���������
  //��ȡ����ֵ
  var para_value = parser.documentElement.getAttribute("value");

  return para_value;
}

/** ѡ����ҵ
  * ���÷�������:var ts=util_choose_enp(s_start_area_code)
  * //alert(ts[0]);//��ҵ����
  * //alert(ts[1]);//��ҵȫ��
  * //alert(ts[2]);//��ҵ���
  * //alert(ts[3]);//��ҵ����
  * //alert(ts[4]);//��ҵ����
  */
function util_choose_enp()
{

  return window.showModalDialog(webBasePath+"/util/util_ChooseEnpBift.jsp?queryType=QueryAssurerWJFL&time=" + (new Date)+"&opDataUnclear=true",window,"dialogWidth:690px;dialogHeight:360px;center:yes;help:no;status:no;resizable:no");
}

/** ѡ����ڻ���(�ͻ�)
  * ���÷�������:var ts=util_choose_bank()
  * //alert(ts[0]);//���ڻ�������
  * //alert(ts[1]);//���ڻ������
  * //alert(ts[2]);//������������
  * //alert(ts[3]);//���ڻ���ȫ��
  * //alert(ts[4]);//����ʡ�ݴ���
  * //alert(ts[5]);//������������
  * //alert(ts[6]);//����ʡ������
  * //alert(ts[7]);//��ҵ����
  */
function util_choose_bank()
{

  return window.showModalDialog(webBasePath+"/util/util_ChooseBank.jsp?ask=true&queryType=QueryGroup&time="+new Date()+"&opDataUnclear=true",window,"dialogWidth:650px;dialogHeight:400px;center:yes;help:no;status:no;resizable:no");
}
/** ѡ��ͻ�
  * ���÷�������:var ts=util_choose_client()
  * ���÷�������:var ts=util_choose_client('02')//ѡ�����ͻ�
  *	alert(ts[1]);//��Ӧta390050001
  *	alert(ts[2]);//��Ӧta390050002
  *	alert(ts[3]);//��Ӧta390050003
  *	alert(ts[4]);//��Ӧta390050004
  *	alert(ts[5]);//��Ӧta390050005
  *	alert(ts[6]);//��Ӧta390050006
  *	alert(ts[7]);//��Ӧta390050007
  */
function util_choose_client_new(ta390050005)
{
  var ret=["�벻Ҫȡts[0]"];
  var para="";
  if (ta390050005!=null)
  	para=para+"&ta390050005="+ta390050005;
  var ts=window.showModalDialog(webBasePath+"/bift/util/util_Query.jsp?queryType=icbc.cmis.bift.util.CChooseClientDAO"+para+"&width=700&height=432&hasDetailLink=new&isTotal=true"+"&opDataUnclear=true",window,"dialogWidth:760px;dialogHeight:526px;center:yes;help:no;status:no;resizable:yes");
  if (ts==null)
  	return ts;
  else 
    return ret.concat(ts);
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

function initArray(){
	this.length = initArray.arguments.length
	for (var i = 0; i < this.length; i++)
	this[i+1] = initArray.arguments[i]
}
var daysInMonth = new initArray(31,28,31,30,31,30,31,31,30,31,30,31);
function getDays(month, year) {
	//�������δ������жϵ�ǰ�Ƿ��������
	if (month == 2)
		return ((0 == year % 4) && (0 != (year % 100))) || (0 == year % 400) ? 29 : 28;
	else
		return daysInMonth[month];
}
function add_months(basedate,factor)
{
     //Ŀǰֻ������factorΪ�������
     if (factor<0)
     	return 'error';
     var year=parseInt(basedate.substring(0,4),10);
     var month=parseInt(basedate.substring(4,6),10)+factor;
     var days=basedate.substring(6,8);
     if (month>12)
     {
        year=year+Math.floor(month/12);
        month=month % 12;
        
        if (month==0){//��month+factor��12��������ʱ���ᷢ��month=0�����
	     	year=year-1;
	     	month="12";
     	}
     }
     
     //���Ǵ�С�µ��������
     if (days>getDays(month,year))
     	days=getDays(month,year);
     if (month<10)
     	month="0"+month;
     	
     return ""+year+month+days;
}
//���ݴ�����ʽ𽻸��ա��״���Ϣ�ա���Ϣ��ʽ��������һ��Ϣ��
//���ص����ַ���
function util_get_last_interest_date(settledate,startdate,interesttype)
{
  var result_this;
  var result_last;

  if (settledate==null||startdate==null||interesttype==null)
    return null;

  //������״���Ϣǰ����򷵻��ʽ𽻸��գ���֤��ϢΪ0
  if (settledate<startdate)
  	return settledate;

  //��Ϣȯ��������Ϣ�վ����ʽ𽻸��գ���֤��ϢΪ0
  if (interesttype ==4)
     return settledate;
  	
  //���ڸ�Ϣ��������Ϣ�վ����״ε�����
  if (interesttype ==3)
     return startdate;

  //һ�긶Ϣ��������Ϣ�ջ����Ǳ���ĸ�Ϣ�գ�������ȥ��ĸ�Ϣ��
  if (interesttype ==1)
  {
     //ȡ�ñ���ȸ�Ϣ��
     result_this= settledate.substring(0,4)+startdate.substring(startdate,4,8);
     //ȡ������ȸ�Ϣ��
     result_last= ""+(parseInt(settledate.substring(0,4),10)-1)+startdate.substring(startdate,4,8);
     if (result_this<=settledate)
        return result_this;
     else
        return result_last;
  }
  //���긶Ϣ���ӱ�����Ϣ���԰���Ϊ���ں��ƣ�ֱ���ҵ���ӽ�settledate�����һ����Ϣ��
  if (interesttype ==2)
  {
     //���״���Ϣ�տ�ʼ
     result_this=startdate;
     times=times+1;
     //ѭ�����ƣ�ֱ��result_this����settledate,��result_last��Ϊ�����
     while (result_this<=settledate)
     {
     	times=times+1;
     	result_last=result_this;
     	result_this=add_months(startdate,6*times);
     }
     return result_last;
  }
}
//���ݴ��������Ƿ�����
//���ص��ǻ��
function is_leap(year_in)
{
	var year=parseInt(year_in,10);
	return ((0 == year % 4) && (0 != (year % 100))) || (0 == year % 400) ? true : false;
}
//���ݴ������ʼ���ںͽ������ڣ��ж�֮���Ƿ����2��29�գ���Ϣ�գ�
//�����ڼ�����Ϣ�յ�����
function sum_dead_interest_date(startdate,enddate)
{
  var year,dead_interest_date,day_count;
  day_count=0;

  year=startdate.substring(0,4);

  //����ʼ���ڿ�ʼ���������ڣ���鵱���Ƿ������Ϣ��(����������ֹ����������)
  while (year<=enddate.substring(0,4))
  {
    if (is_leap(year)){
       dead_interest_date=year+'0229';
       if (dead_interest_date>=startdate && dead_interest_date<=enddate)
          day_count=day_count+1;
    }
    year=""+(parseInt(year,10)+1);
  }

  return day_count;
}
/**
 *��������
 *�������:��ʼ���ڣ���������
 *���Ϊ:��ʼ���ڵ��������ڰ���������
 */
function calculatedays(startdate,enddate){
/*	var oneday=24*60*60*1000;  //����һ��ĺ�����=24Сʱ��60���ӡ�60���1000
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
	return sedate;*/
	
	date1 = new Date();
	date2 = new Date();
	diff  = new Date();
	
	date1temp = new Date(startdate.substr(4,2) + "/" + startdate.substr(6,2) + "/" + startdate.substr(0,4) + " " + "01:00:00 AM");
	date1.setTime(date1temp.getTime());
	
	date2temp = new Date(enddate.substr(4,2) + "/" + enddate.substr(6,2) + "/" + enddate.substr(0,4) + " " + "01:00:00 AM");
	date2.setTime(date2temp.getTime());

	// sets difference date to difference of first date and second date
	diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
	timediff = diff.getTime();

	days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
	
	return days;	
}
//���ݴ������ʼ���ںͽ������ڣ��ж����е���Ч��Ϣ����
//��������
function interest_days_between(startdate,enddate)
{
  return calculatedays(startdate,enddate)-sum_dead_interest_date(startdate,enddate);
}
//���ݴ�����ʽ𽻸��ա��״���Ϣ�ա���Ϣ��ʽ��ծȯ��ֵ��ʵ�����ʡ����ޡ�������ʼ�ա���Ϣ��־������Ӧ����Ϣ
//���ص���Ӧ����Ϣ���
function compute_interest_old(settledate,startdate,interesttype,base_amount,rate,year,enddate,payflag)
{
  var result_this,result_last,last_interest_date,next_interest_date;
  var times,factor;

  //��ʼ��ֵ
  factor=1;
  if (year==null)
  	year=0;
  if (enddate==null)
  	enddate="20991231";
  if (payflag==null)
  	payflag=0;
  
  if (settledate==null||startdate==null||interesttype==null||base_amount==null||rate==null)
    return null;

  //������ڸ�Ϣ��������δ֪�򷵻�null
  if (interesttype==3 && (year==0 || enddate=='20991231'))
    return null;

  //������״���Ϣǰ����򷵻�0
  if (settledate<startdate)
  	return 0;

  //����ʽ𽻸������ڵ�����ʼ�գ����ʽ𽻸�������Ϊ������ʼ��
  if (settledate >enddate)
     settledate=enddate;

  //��Ϣȯ��������Ϣ�վ����ʽ𽻸��գ���ϢΪ0
  if (interesttype ==4)
     return 0;
  	
  //���ڸ�Ϣ��������Ϣ�վ����״ε�����
  if (interesttype ==3)
  {
     if (payflag==0)
            factor=roundn(1.0*interest_days_between(startdate,settledate)/interest_days_between(startdate,enddate),14);
     return roundn(roundn(1.0*base_amount*roundn(1.0*rate*year,14),14)*factor,14);
  }

  //һ�긶Ϣ��������Ϣ�ջ����Ǳ���ĸ�Ϣ�գ�������ȥ��ĸ�Ϣ��
  if (interesttype ==1)
  {
     //ȡ�ñ���ȸ�Ϣ��
     result_this= settledate.substring(0,4)+startdate.substring(startdate,4,8);
     //ȡ������ȸ�Ϣ��
     result_last= ""+(parseInt(settledate.substring(0,4),10)-1)+startdate.substring(startdate,4,8);
     if (result_this<=settledate)
        last_interest_date = result_this;
     else
        last_interest_date = result_last;
     //��һ��Ϣ�ռ������,����Ӧ����Ϣ
     if (payflag==0)
	factor=roundn(1.0*interest_days_between(last_interest_date,settledate)/365,14);
     return roundn(roundn(1.0*base_amount*rate,14)*factor,14);
  }
  //���긶Ϣ���ӱ�����Ϣ���԰���Ϊ���ں��ƣ�ֱ���ҵ���ӽ�settledate�����һ����Ϣ��
  if (interesttype ==2)
  {
     //���״���Ϣ�տ�ʼ
     result_this=startdate;
     times=0;
     //ѭ�����ƣ�ֱ��result_this����settledate,��result_last��Ϊ�����
     while (result_this<=settledate)
     {
     	times=times+1;
     	result_last=result_this;
     	result_this=add_months(startdate,6*times);
     }
     last_interest_date= result_last;
     next_interest_date= result_this;
     //��һ��Ϣ������һ��Ϣ�ռ�����ϣ�����Ӧ����Ϣ
     if (payflag==0)
	factor=roundn(1.0*interest_days_between(last_interest_date,settledate)/interest_days_between(last_interest_date,next_interest_date),14);
     return roundn(roundn(1.0*base_amount*roundn(1.0*rate/2,14),14)*factor,14);
  }
}

//���ݴ�����ʽ𽻸��ա��״���Ϣ�ա���Ϣ��ʽ��ծȯ��ֵ��ʵ�����ʡ����ޡ�������ʼ�ա���Ϣ��־����Ϣ���͡����м۸�workdate������Ӧ����Ϣ
//���ص���Ӧ����Ϣ���
//Ӧ����Ϣ����workdate�ж��Ƿ�������㷨����Ϣ�����㷽������
function compute_interest(settledate, startdate, interesttype, base_amount, rate, year, enddate, payflag, interestmode, payprice, workdate) {
  var result_this, result_last, last_interest_date, next_interest_date;
  var times, factor;
  var genparadate;

      /*
      alert("�ʽ𽻸���(" + settledate + ")\n" +
            "�״���Ϣ��(" + startdate + ")\n" +
            "��Ϣ��ʽ(" + interesttype + ")\n" +
            "ծȯ��ֵ(" + base_amount + ")\n" +
            "ʵ������(" + rate + ")\n" +
            "����(" + year + ")\n" +
            "������ʼ��(" + enddate + ")\n" +
            "��Ϣ��־(" + payflag + ")\n" +
            "��Ϣ����(" + interestmode + ")\n" +
            "���м۸�(" + payprice + ")\n" +
            "workdate(" + workdate + ")"
      );
      */

  //��ʼ��ֵ
  factor = 1;
  if (year == null)
    year = 0;
  if (enddate == null)
    enddate = "20991231";
  if (payflag == null)
    payflag = 0;

  if (settledate == null || startdate == null || interesttype == null || base_amount == null || rate == null)
    return null;
  if (interestmode == null || payprice == null || workdate == null)
    return null;

  if (payflag == 1)
    return compute_interest_old(settledate, startdate, interesttype, base_amount, rate, year, enddate, payflag);
  genparadate = util_get_genpara('77', '01');
  if (genparadate == null)
    //����������
    genparadate = "20991231";
  if (settledate < genparadate)
    //���������㷨
    return compute_interest_old(settledate, startdate, interesttype, base_amount, rate, year, enddate, payflag);

  //������ڸ�Ϣ��������δ֪�򷵻�null
  if (interesttype == 3 && (year == 0 || enddate == '20991231'))
    return null;

  //������״���Ϣǰ����򷵻�0
  if (settledate < startdate)
    return 0;

  //����ʽ𽻸������ڵ�����ʼ�գ����ʽ𽻸�������Ϊ������ʼ��
  if (settledate > enddate)
    settledate = enddate;

  //�㷨����
  //��Ϣȯ��������Ϣ�վ����ʽ𽻸��գ���ϢΪ0
  if (interestmode == 4)
    if (payflag == 0) {

      /*
      alert("�ʽ𽻸���(" + settledate + ")\n" +
            "�״���Ϣ��(" + startdate + ")\n" +
            "��Ϣ��ʽ(" + interesttype + ")\n" +
            "ծȯ��ֵ(" + base_amount + ")\n" +
            "ʵ������(" + rate + ")\n" +
            "����(" + year + ")\n" +
            "������ʼ��(" + enddate + ")\n" +
            "��Ϣ��־(" + payflag + ")\n" +
            "��Ϣ����(" + interestmode + ")\n" +
            "���м۸�(" + payprice + ")\n" +
            "workdate(" + workdate + ")\n" +
            "��Ϣծȯ\n" +
            "P = " + payprice + "\n" +
            "t = " + interest_days_between(startdate, settledate) + "\n" +
            "T = " + interest_days_between(startdate, enddate) + "\n" +
            "100 - P = " + (100 * 10000 - payprice * 10000) / 10000 + "\n" +
            "(100 - P) * t * ծȯ��ֵ / 100 = " + roundn((100 * 10000 - payprice * 10000) / 10000 * interest_days_between(startdate, settledate) * base_amount * 10 / 1000, 16) + "\n" +
            "((100 - P) * t * ծȯ��ֵ / 100) / T = " + roundn(roundn((100 * 10000 - payprice * 10000) / 10000 * interest_days_between(startdate, settledate) * base_amount * 10 / 1000, 16) / interest_days_between(startdate, enddate), 16)
      );
      */

      return roundn(roundn((100 * 10000 - payprice * 10000) / 10000 * (interest_days_between(startdate, settledate) + sum_dead_interest_date(startdate, settledate)) * base_amount * 10 / 1000, 16) / (interest_days_between(startdate, enddate) + sum_dead_interest_date(startdate, enddate)), 16);
    } else
      return roundn((100 * 10000 - payprice * 10000) / 10000 * base_amount * 10 / 1000, 16);

  //���ڸ�Ϣ��������Ϣ�վ����״ε�����
  if (interesttype == 3) {
    //������һ���۸�Ϣ��
    //ȡ�ñ���ȸ�Ϣ��
    result_this = settledate.substring(0, 4) + enddate.substring(4, 8);
    //ȡ������ȸ�Ϣ��
    result_last = "" + (parseInt(settledate.substring(0, 4), 10) - 1) + enddate.substring(4, 8);
    if (result_this <= settledate)
      last_interest_date = result_this;
    else
      last_interest_date = result_last;
    //��Ϣ�ջ���һ���۸�Ϣ��ȡ�ϴ�ֵ
    if (startdate > last_interest_date)
      last_interest_date = startdate;
    if (payflag == 0) {

      /*
      alert("�ʽ𽻸���(" + settledate + ")\n" +
            "�״���Ϣ��(" + startdate + ")\n" +
            "��Ϣ��ʽ(" + interesttype + ")\n" +
            "ծȯ��ֵ(" + base_amount + ")\n" +
            "ʵ������(" + rate + ")\n" +
            "����(" + year + ")\n" +
            "������ʼ��(" + enddate + ")\n" +
            "��Ϣ��־(" + payflag + ")\n" +
            "��Ϣ����(" + interestmode + ")\n" +
            "���м۸�(" + payprice + ")\n" +
            "workdate(" + workdate + ")\n" +
            "����һ�λ�����Ϣծȯ\n" +
            "��Ϣ�ջ���һ���۸�Ϣ�� = " + last_interest_date + "\n" +
            "K = " + (interest_days_between(startdate, settledate) - interest_days_between(startdate, settledate) % 365) / 365 + "\n" +
            "C * ծȯ��ֵ / 100 = " + base_amount * rate + "\n" +
            "K * C * ծȯ��ֵ / 100 = " + roundn(((interest_days_between(startdate, settledate) - interest_days_between(startdate, settledate) % 365) / 365) * base_amount * rate, 16) + "\n" +
            "t = " + interest_days_between(last_interest_date, settledate) + "\n" +
            "(C * ծȯ��ֵ / 100) * t = " + roundn(interest_days_between(last_interest_date, settledate) * base_amount * rate, 16) + "\n" +
            "(C * ծȯ��ֵ / 100) * t / 365 = " + roundn(roundn(interest_days_between(last_interest_date, settledate) * base_amount * rate, 16) / 365, 16) + "\n" +
            "(K * C * ծȯ��ֵ / 100) + ((C * ծȯ��ֵ / 100) * t / 365) = " + (roundn(((interest_days_between(startdate, settledate) - interest_days_between(startdate, settledate) % 365) / 365) * base_amount * rate, 16) + roundn(roundn(interest_days_between(last_interest_date, settledate) * base_amount * rate, 16) / 365, 16))
      );
      */

      return roundn((((interest_days_between(startdate, settledate) + sum_dead_interest_date(startdate, settledate)) - (interest_days_between(startdate, settledate) + sum_dead_interest_date(startdate, settledate)) % 365) / 365) * base_amount * rate, 16) + roundn(roundn((interest_days_between(last_interest_date, settledate) + sum_dead_interest_date(last_interest_date, settledate)) * base_amount * rate, 16) / 365, 16);
    } else
      return roundn(base_amount * roundn(rate * year, 16), 16);
  }

  //һ�긶Ϣ��������Ϣ�ջ����Ǳ���ĸ�Ϣ�գ�������ȥ��ĸ�Ϣ��
  if (interesttype == 1) {
    //ȡ�ñ���ȸ�Ϣ��
    result_this = settledate.substring(0, 4) + startdate.substring(4, 8);
    //ȡ������ȸ�Ϣ��
    result_last = "" + (parseInt(settledate.substring(0, 4), 10) - 1) + startdate.substring(4, 8);
    if (result_this <= settledate)
      last_interest_date = result_this;
    else
      last_interest_date = result_last;
    if (startdate > last_interest_date)
      last_interest_date = startdate;
    if (payflag == 0) {

      /*
      alert("�ʽ𽻸���(" + settledate + ")\n" +
            "�״���Ϣ��(" + startdate + ")\n" +
            "��Ϣ��ʽ(" + interesttype + ")\n" +
            "ծȯ��ֵ(" + base_amount + ")\n" +
            "ʵ������(" + rate + ")\n" +
            "����(" + year + ")\n" +
            "������ʼ��(" + enddate + ")\n" +
            "��Ϣ��־(" + payflag + ")\n" +
            "��Ϣ����(" + interestmode + ")\n" +
            "���м۸�(" + payprice + ")\n" +
            "workdate(" + workdate + ")\n" +
            "һ�긶Ϣһ��\n" +
            "��Ϣ�ջ���һ��Ϣ�� = " + last_interest_date + "\n" +
            "C * ծȯ��ֵ / 100 = " + base_amount * rate + "\n" +
            "t = " + interest_days_between(last_interest_date, settledate) + "\n" +
            "(C * ծȯ��ֵ / 100) * t / 365 = " + roundn(roundn(base_amount * rate, 16) * interest_days_between(last_interest_date, settledate) / 365, 16) + "\n" +
            roundn(roundn(base_amount * rate, 16) * (interest_days_between(last_interest_date, settledate) + sum_dead_interest_date(last_interest_date, settledate)) / 365, 16)
      );
      */

      return roundn(roundn(base_amount * rate, 16) * (interest_days_between(last_interest_date, settledate) + sum_dead_interest_date(last_interest_date, settledate)) / 365, 16);
    } else
      return roundn(base_amount * rate, 16);
  }

  //���긶Ϣ���ӱ�����Ϣ���԰���Ϊ���ں��ƣ�ֱ���ҵ���ӽ�settledate�����һ����Ϣ��
  if (interesttype == 2) {
    //���״���Ϣ�տ�ʼ
    result_this = startdate;
    times = 0;
    //ѭ�����ƣ�ֱ��result_this����settledate��result_last��Ϊ�����
    while (result_this <= settledate) {
      times = times + 1;
      result_last = result_this;
      result_this = add_months(startdate, 6 * times);
    };
    last_interest_date = result_last;
    next_interest_date = result_this;
    if (startdate > last_interest_date)
      last_interest_date = startdate;
    if (payflag == 0) {

      /*
      alert("�ʽ𽻸���(" + settledate + ")\n" +
            "�״���Ϣ��(" + startdate + ")\n" +
            "��Ϣ��ʽ(" + interesttype + ")\n" +
            "ծȯ��ֵ(" + base_amount + ")\n" +
            "ʵ������(" + rate + ")\n" +
            "����(" + year + ")\n" +
            "������ʼ��(" + enddate + ")\n" +
            "��Ϣ��־(" + payflag + ")\n" +
            "��Ϣ����(" + interestmode + ")\n" +
            "���м۸�(" + payprice + ")\n" +
            "workdate(" + workdate + ")\n" +
            "���긶Ϣһ��\n" +
            "��Ϣ�ջ���һ��Ϣ�� = " + last_interest_date + "\n" +
            "C * ծȯ��ֵ / 100 = " + base_amount * rate + "\n" +
            "t = " + interest_days_between(last_interest_date, settledate) + "\n" +
            "(C * ծȯ��ֵ / 100) * t / 365 = " + roundn(roundn(base_amount * rate, 16) * interest_days_between(last_interest_date, settledate) / 365, 16)
      );
      */

      return roundn(roundn(base_amount * rate, 16) * (interest_days_between(last_interest_date, settledate) + sum_dead_interest_date(last_interest_date, settledate)) / 365, 16);
    } else
      return roundn(roundn(base_amount * rate, 16) / 2, 16);
  }
}

//���ô洢���̼���Ӧ����Ϣ
//���ݴ�����ʽ𽻸��ա�ծȯ��š�ծȯ��ֵ��ʵ�����ʡ���Ϣ��־������Ӧ����Ϣ
function util_get_interest(settledate, bond_serial, base_amount, rate, payflag) {

  /*
  alert("�ʽ𽻸���(" + settledate + ")\n" +
        "ծȯ���(" + bond_serial + ")\n" +
        "ծȯ��ֵ(" + base_amount + ")\n" +
        "ʵ������(" + rate + ")");
        */

  //����������Ϊ��
  if (settledate == null || bond_serial == null || base_amount == null || rate == null || payflag == null)
    return null;

  //��֯��ѯ����
  var qstr = "method=getInterest&settledate=" + settledate + "&bond_serial=" + bond_serial + "&base_amount=" + base_amount + "&rate=" + rate + "&payflag=" + payflag;

  //��ѯ
  objHTTP.Open('GET',webBasePath+'/servlet/icbc.cmis.bift.util.CUtilServlet?'+qstr,false);
  objHTTP.Send();

  //��ѯ������
  var xml = objHTTP.responseText;
  //alert(xml);
  if(!parser.loadXML(xml)) {
//    alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
    return null;
  }
  error = parser.getElementsByTagName("error");
  if(error.length > 0) {
    alert(error.item(0).text);
    return null;
  }
  //��ѯ��ȷ���������
  //��ȡ����ֵ
  var para_value = parser.documentElement.getAttribute("value");

  return para_value;
}

//����������ѡ��
function util_choose_area_compare(s_start_area_code, s_range) {
  if (s_range == null)
    s_range = "1111";
  //���С�һ���С�׼һ���С�������
  //��������ѡ�����С�һ���С�׼һ���С�������

  return window.showModalDialog(webBasePath+"/bift/util/bift_util_choose_area_compare.jsp?area="+s_start_area_code+"&range="+s_range+"&time=" + (new Date)+"&opDataUnclear=true",window,"dialogWidth:380px;dialogHeight:280px;center:yes;help:no;status:no;resizable:no");
}

/**ѡ����һ��������
  *Danny 20041205
  *@param flowtype ��������
  *@param level ��������
  *@return ts[0][1]�����˱��
  *        ts[0][2]����������
  */
function util_choose_checkman(flowtype,level)
{
  var qstr = webBasePath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.bift.flow.core.util.ChooseCheckList&scene=checkman&flowtype="+flowtype+"&level="+level+"&opDataUnclear=true";
  return window.showModalDialog(qstr,window,"dialogWidth:370px;dialogHeight:350px;center:yes;help:no;status:no;resizable:yes");
}

/**
*ѡ���¼�������
*���������major 0������ 1������ 2���������
*         area ��������
*return ts[0][1]�����˱��
*       ts[0][2]����������
*/
function util_choose_namelist(major,area)
{
  var qstr = webBasePath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.bift.flow.core.util.ChooseCheckList&scene=h&checkmajor="+major+"&area_code="+area+"&emp_code=0,1&opDataUnclear=true";
  return window.showModalDialog(qstr,window,"dialogWidth:370px;dialogHeight:350px;center:yes;help:no;status:no;resizable:yes");
}

/**ѡ��������Ա
  *���� 20041214
  *@param area_code ��������
  *@return ts[0][1]��Ա���
  *        ts[0][2]��Ա����
  *        ts[0][3]��������
  *        ts[0][4]������ɫ
*/
function util_choose_checkemployee(scene,area_code,checkmajor,emp_code)
{
  var qstr = webBasePath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.bift.flow.core.util.ChooseCheckList&scene="+scene+"&area_code="+area_code+"&checkmajor="+checkmajor+"&emp_code="+emp_code+"&opDataUnclear=true";
  return window.showModalDialog(qstr,window,"dialogWidth:370px;dialogHeight:350px;center:yes;help:no;status:no;resizable:yes");
}

// ���ʽ�ع�ѡ��������
function util_choose_open_approval(empFlag,busiType,oldsn)
{
  var ret=["�벻Ҫȡts[0]"];
  if (empFlag==null)
  	empFlag="";
  	
  if (busiType==null)
  	busiType="";
  	
  if (oldsn==null)
  	oldsn="";
  	
  var ts=window.showModalDialog(webBasePath+"/bift/util/util_Query.jsp?ta391032008="+ busiType + "&empFlag=" + empFlag + "&oldsn=" + oldsn + "&queryType=icbc.cmis.bift.sa.COpenChooseApprovalDAO&QueryPage=/bift/s/sa/bift_choose_open_approval_input.jsp &width=700&height=480&hasDetailLink=new"+"&opDataUnclear=true",window,"dialogWidth:800px;dialogHeight:570px;center:yes;help:no;status:no;resizable:yes");
  if (ts==null)
  	return ts;
  else 
    return ret.concat(ts);
}


// ��ȯ��Ʊ����ѡ��������
function util_choose_cashbill_approval(kemu,empFlag,oldsn)
{
  var ret=["�벻Ҫȡts[0]"];
  if (kemu==null)
  	kemu="";

  if (empFlag==null)
  	empFlag="";
  	
  if (oldsn==null)
  	oldsn="";
  	
  var ts=window.showModalDialog(webBasePath+"/bift/util/util_Query.jsp?kemu="+kemu+"&empFlag="+empFlag+"&oldsn="+oldsn+"&queryType=icbc.cmis.bift.ca.CCashBillChooseApprovalDAO&QueryPage=/bift/c/ca/bift_choose_cashbill_approval_input.jsp &width=700&height=480&hasDetailLink=new"+"&opDataUnclear=true",window,"dialogWidth:800px;dialogHeight:570px;center:yes;help:no;status:no;resizable:yes");
  if (ts==null)
  	return ts;
  else 
    return ret.concat(ts);
}


// �����г���ȯͶ������ѡ��������
function util_choose_cashbid_approval(empFlag)
{
  var ret=["�벻Ҫȡts[0]"];

  if (empFlag==null)
  	empFlag="";
  	
  var ts=window.showModalDialog(webBasePath+"/bift/util/util_Query.jsp?empFlag="+empFlag+"&queryType=icbc.cmis.bift.ca.CCashBidChooseApprovalDAO&QueryPage=/bift/c/ca/bift_choose_cashbid_approval_input.jsp &width=700&height=480&hasDetailLink=new"+"&opDataUnclear=true",window,"dialogWidth:800px;dialogHeight:570px;center:yes;help:no;status:no;resizable:yes");
  if (ts==null)
  	return ts;
  else 
    return ret.concat(ts);
}

// �����г���ƱͶ��ѡ��������
function util_choose_billbid_approval(empFlag)
{
  var ret=["�벻Ҫȡts[0]"];

  if (empFlag==null)
  	empFlag="";
  	
  var ts=window.showModalDialog(webBasePath+"/bift/util/util_Query.jsp?empFlag="+empFlag+"&queryType=icbc.cmis.bift.ca.CBillBidChooseApprovalDAO&QueryPage=/bift/c/ca/bift_choose_billbid_approval_input.jsp &width=700&height=480&hasDetailLink=new"+"&opDataUnclear=true",window,"dialogWidth:800px;dialogHeight:570px;center:yes;help:no;status:no;resizable:yes");
  if (ts==null)
  	return ts;
  else 
    return ret.concat(ts);
}

/** ���������ݿͻ�����ѡ��ͻ�
  * ���÷�������:var ts=util_choose_client_type()//ѡ��ȫ���ͻ�
  * ���÷�������:var ts=util_choose_client_type('01')//ѡ��������ҵ���пͻ�
  * by lexy 2004.12.07
*/
function util_choose_client_type(ta390050007)
{
  var ret=["�벻Ҫȡts[0]"];
  var para="";
  if (ta390050007==null)
  	ta390050007="";
  
  para=para+"&ta390050007="+ta390050007;
    
  var ts=window.showModalDialog(webBasePath+"/bift/util/util_Query.jsp?queryType=icbc.cmis.bift.util.CChooseClientDAO&QueryPage=/bift/util/bift_util_choose_client_input.jsp"+para+"&width=700&height=480&hasDetailLink=new"+"&opDataUnclear=true",window,"dialogWidth:800px;dialogHeight:570px;center:yes;help:no;status:no;resizable:yes");
  if (ts==null)
  	return ts;
  else 
    return ret.concat(ts);
}
