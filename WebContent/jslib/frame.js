var fatodays = new Date();
var fayears = fatodays.getYear(); 
fayears=(fayears<1900?(1900+fayears):fayears); 
var famonths= fatodays.getMonth()+1;
var fadates=fatodays.getDate();
if(famonths<10) famonths="0"+famonths;
if(fadates<10) fadates="0"+fadates;
var currdays=fayears+""+famonths+""+fadates;
function qotherpage(posflag,topn){
   var postmp = posflag;
   if(topn=="topre"){
       postmp=parseInt(postmp)-15;
   }else if(topn=="last"){
       postmp=(parseInt(postmp)-1)*15;
   }
   document.form1.doflag.value="";
   document.form1.beginPos.value=postmp;
   document.form1.submit();
}
function radioselect(strid1,strid2)
{
  document.form1.selectid.value=strid1;
  document.form1.selpoint.value=strid2;
  return true;
}
function tabsubmit(arg1){
   document.form1.tabflag.value=arg1;
   document.form1.doflag.value="";
   document.form1.submit();
}
function worksign(arg1){
   if(arg1!="add"&&arg1!="addsign"&&arg1!="downinfo"&&document.form1.selectid.value==""){
      alert("��ѡ��Ҫ�����ļ�¼!");
      return false;
   }else if(arg1=="delete"){
      var ret=confirm("ȷ��ɾ���ü�¼?");
      if (!ret) return false;
      else{
         document.form1.doflag.value=arg1;
         document.form1.submit();
      }
   }else{
      document.form1.doflag.value=arg1;
      document.form1.submit();
   }
}
function mylinkto(strid)
{
  document.form2.companyid.value=strid;
  document.form2.submit();
}

function checkaccount(arg1){
  if (isNaN(arg1)) {
    alert("�ʺ�Ϊ�գ������룡");
    return false;
  }
  //С��17λ���ʺ���Ϊ���ʺţ���У��
  if (arg1.length < 17) return true;
  //���ʺű���Ϊ19λ
  if (arg1.length < 19){
    alert("������19λ�ʺ�!");
    return false;
  }
   var num_so;
   var num_ob;
   num_so=arg1.substr(0,1)*11+arg1.substr(1,1)*13+arg1.substr(2,1)*17+arg1.substr(3,1)*19+
     arg1.substr(4,1)*23+arg1.substr(5,1)*29+arg1.substr(6,1)*31+arg1.substr(7,1)*37+
     arg1.substr(8,1)*41+arg1.substr(9,1)*43+arg1.substr(10,1)*47+arg1.substr(11,1)*53+
     arg1.substr(12,1)*59+arg1.substr(13,1)*61+arg1.substr(14,1)*67+arg1.substr(15,1)*71+
     arg1.substr(16,1)*73;
  num_ob=97-num_so%97;
  if (arg1.substr(17,2)==num_ob) return true;
  else{
   if(num_ob < 10) {
        num_ob = "0" + num_ob;
   }
   alert("�ʺŴ��󣬸����ʺŵ�ǰ17λУ����ӦΪ��" + num_ob);
   return false;
  }
   return;
}
function The_date()
{
	var Today = new Date();
	var year = Today.getYear(); 
year=(year<1900?(1900+year):year); 
	var month= Today.getMonth();
	var date  = Today.getDate();
	
	document.write(year+"��");
	document.write(month+1);
	document.write("��"+date+"�� ");
}
function faprint(tabflag,winName,features){
   //alert("�ù������ڿ���!");
   var now=new Date();
   var shi=now.getHours();
   var fen=now.getMinutes();
   var miao=now.getSeconds();
   var fdate=shi+":"+fen+":"+miao;
   window.showModalDialog("../F/FA/FA_Print.jsp?fdate="+fdate+"&tabflag="+tabflag,"","dialogWidth:780px;dialogHeight:580px;center:yes;help:no;status:no;resizable:no");
   //printwin=window.open("/icbc/cmis/F/FA/FA_Print.jsp?tabflag="+tabflag,'',features);
   return;
}
function startprint(){
     var allpage = document.all.tags("table");                
     for(i = 1; i <= allpage.length; i++){                       
             var page = allpage[i - 1];                          
             if (page.getAttribute("id") == "but") {
                     page.style.display = 'none';                 
             }                                                  
     }
   var conret=confirm("��׼���ô�ӡ������ȷ����ʼ��ӡ!");
   if (conret){
     window.print();
   }
   window.close();
}

//��rul����unicode����
function encodeURL(s) {
  if(s == null ) return s;
  if(s.length == 0) return s;
  k = s.indexOf('?');
  if(k==-1) return s;

  //���ƣ���ǰ��url
  var len = s.length;
  if(k + 1 < len)  k = k + 1;
  var url = s.substring(0,k);

  //����url����
  //����&=��
  var paras = "";
  var ts = '&' + s.substring(k,len);
  len = ts.length;
  k = 0;
  while(k < len) {
    //����һ������
    //����&����һ��&��ȷ��һ������
    p1 = ts.indexOf('&',k);
    p2 = ts.indexOf('&',k + 1);
    if(p2 == -1) p2 = len;
    k = p2;
    para = ts.substring(p1,p2);
    //����=������������ͱ���
    p3 = para.indexOf('=');
    if(p3 == -1) {
      paras = paras + para;
    } else {
      p3 = p3 + 1;
      key = para.substring(0,p3);
      //�Ա������б���
      value = encode2(para.substring(p3,para.length));
      //��ӵ�Ŀ�괮
      paras = paras + key + value;
    }
  }
  //ȥ���󲹵�&
  paras = paras.substring(1);
  //���ؽ��
  return url + paras;
}

//ֻ�Ժ��ֽ���unicode����
function encode(s) {
  if(s == null ) return s;
  if(s.length == 0) return s;

  var ret = "";
  for(i=0;i<s.length;i++) {
    var c = s.substr(i,1);
    var ts = escape(c);
    if(ts.substring(0,2) == "%u") {
      ret = ret + ts.replace("%u","@@");
    } else {
      ret = ret + c;
    }
  }
  return ret;
}
//���ַ�������unicode����
function encode2(s) {
  if(s == null ) return s;
  if(s.length == 0) return s;

  s = escape(s);
  while(true) {
    if(s.indexOf('%u') == -1) return s;
    s = s.replace('%u','@@');
  }
}

//��ָ����form���ݽ���unicode������ύ
function formSubmit2(form) {
  method = form.method;
  action = encodeURL(form.action);
  content = encode(form.innerHTML);
  tform = "<form name='SubmitFormQ4WXk7' method='"+ method +"' action='"+ action +"' style='display:none'>"
    + content +"</form>";
  tbody = document.body.innerHTML + tform;
  document.body.innerHTML = tbody;
  SubmitFormQ4WXk7.submit();
}

//��ָ����form���ݽ���unicode������ύ
//ʹ�øú���������ҳ�������һ���ɼ���form:
//<form name="SubmitForm" style="display:none" />
function formSubmit(form) {
  SubmitForm.method = form.method;
  SubmitForm.action = encodeURL(form.action);
  SubmitForm.innerHTML = encode(form.innerHTML);
  SubmitForm.submit();
}

function getIndex(obj) {
	for(i=0;i<document.all.length;i++) {
		if(document.all(i) == obj) return i;
	}
}
function nextItem(k) {
	for(i=k+1;i<document.all.length;i++) {
		var tag = document.all(i).tagName;
		var type = document.all(i).type;
		if((tag == "INPUT" || tag == "SELECT" || tag == "TEXTAREA") && type != "hidden" && canFocus(document.all(i))) {
			return i;
		}
	}
	for(i=0;i<=k;i++) {
		var tag = document.all(i).tagName;
		var type = document.all(i).type;
		if((tag == "INPUT" || tag == "SELECT" || tag == "TEXTAREA") && type != "hidden" && canFocus(document.all(i))) {
			return i;
		}
	}
        return -1;
}

function preItem(k) {
	for(i=k - 1;i>=0;i--) {
		var tag = document.all(i).tagName;
		var type = document.all(i).type;
		if((tag == "INPUT" || tag == "SELECT" || tag == "TEXTAREA") && type != "hidden" && canFocus(document.all(i))) {
			return i;
		}
	}
	for(i=document.all.length - 1;i>=k;i--) {
		var tag = document.all(i).tagName;
		var type = document.all(i).type;
		if((tag == "INPUT" || tag == "SELECT" || tag == "TEXTAREA") && type != "hidden" && canFocus(document.all(i))) {
			return i;
		}
	}
        return -1;
}

function canFocus(obj) {
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



function event_key_press() {
	if(window.event.keyCode == 13) {
		ret = getIndex(window.event.srcElement);
		if(window.event.shiftKey) {
			document.all(preItem(ret)).focus();
		} else {
			document.all(nextItem(ret)).focus();
		}
    window.event.returnValue = false;
	}
}

document.onkeypress = event_key_press;


function EnpCheck(){
  if (form3.companyid.value==""){
    alert("����ѡ����ҵ");
    return;
  }else{
    form3.submit();
    return;
  }
}

function ChooseEnp() {
  var ts = window.showModalDialog("../util/util_ChooseEnp.jsp?queryType=QueryAllEnp&ask=true&time=" + (new Date),window,"dialogWidth:630px;dialogHeight:360px;center:yes;help:no;status:no;resizable:no");
  if(ts != null) {
    //alert(ts[0]);  //��ҵ����
    //alert(ts[1]);  //��ҵ����
    //alert(ts[2]);  //����ִ���
    //alert(ts[3]);  //����֤��
    form3.companyid.value = ts[0];
    //form3.companyname.value = ts[1];
    form3.submit();
    return;
  } 
  return ;
}
function NewEnp() {
  var ts = window.showModalDialog("../util/util_ChooseEnp.jsp?queryType=QueryNewEnp&ask=true&time=" + (new Date),window,"dialogWidth:630px;dialogHeight:360px;center:yes;help:no;status:no;resizable:no");
  if(ts != null) {
    //alert(ts[0]);  //��ҵ����
    //alert(ts[1]);  //��ҵ����
    //alert(ts[2]);  //����ִ���
    //alert(ts[3]);  //����֤��
    form3.companyid.value = ts[0];
    form3.submit();
    //form3.companyname.value = ts[1];
    return;
  } else {
    alert('û��ѡ����ҵ');
    return;
  }
}

function ChooseEnp_sars() {
  var ts = window.showModalDialog("../util/util_ChooseEnp.jsp?queryType=QueryAllEnp&ask=true&time=" + (new Date),window,"dialogWidth:630px;dialogHeight:360px;center:yes;help:no;status:no;resizable:no");
  if(ts != null) {
    form3.companyid.value = ts[0];
    document.all.btnID.style.display="none";
    form3.submit();
    return true;
  } 
  return false;
}
function NewEnp_sars() {
  var ts = window.showModalDialog("../util/util_ChooseEnp.jsp?queryType=QueryNewEnp&ask=true&time=" + (new Date),window,"dialogWidth:630px;dialogHeight:360px;center:yes;help:no;status:no;resizable:no");
  if(ts != null) {
    form3.companyid.value = ts[0];
    document.all.btnID.style.display="none";
    form3.submit();
    return;
  } else {
    alert('û��ѡ����ҵ');
    return;
  }
}

function check_credit_card_no ( in_creditcardno )                        //�����
{
  var weightvalue = new Array(14);
  var checkvalue  = new Array(14);
  var totalvalue = 0;
  var c = 0;
  var sn;
  var j = 0;

  //����У��
  if (in_creditcardno == null || in_creditcardno == "" || in_creditcardno.length != 16){
    alert("�����(" + in_creditcardno + ")����ȷ.����ԭ��:�Ƿ��ĳ���");
    return false;
  }

  //��ʼ�������λУ�����
  weightvalue[0] = 1;
  weightvalue[1] = 3;
  weightvalue[2] = 5;
  weightvalue[3] = 7;
  weightvalue[4] = 11;
  weightvalue[5] = 2;
  weightvalue[6] = 13;
  weightvalue[7] = 1;
  weightvalue[8] = 1;
  weightvalue[9] = 17;
  weightvalue[10] = 19;
  weightvalue[11] = 97;
  weightvalue[12] = 23;
  weightvalue[13] = 29;

  //����ǰ14λ���ַ�У�鼰ȡ����У�����ƥ���λֵ
  while ( j < 14 ) {
    sn = in_creditcardno.charCodeAt(j);

    if ( sn >= 65 && sn <= 90 )
      checkvalue[j] = (sn - 65) + 10;
    else if ( sn >= 48 && sn <= 57 )
      checkvalue[j] = sn - 48;
    else{
      alert("�����(" + in_creditcardno + ")����ȷ.����ԭ��:�����а����Ƿ��ַ�" + in_creditcardno.charCodeAt(j) );
          return false;
    }

    //�ۼ�У������ֵ
	totalvalue = totalvalue + weightvalue[j] * checkvalue[j];
    j = j + 1;
  }

  //�õ�У������ֵ
  c = totalvalue % 97 + 1;

  //���У������ֵ�ͱ����У��λ�Ƿ�ƥ��
  if ( ((in_creditcardno.charCodeAt(14) - 48) * 10 + in_creditcardno.charCodeAt(15) - 48 ) != c ){
    alert("�����(" + in_creditcardno + ")����ȷ.����ԭ��:У��λֵ����ȷ");
    return false;
  }

  return true;
}
