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

function event_key_press() {
  var i = -1;
  if(window.event.keyCode == 13) {
    ret = getIndex(window.event.srcElement);
    if(window.event.shiftKey) {
      i = preItem(ret);
    } else {
      i = nextItem(ret);
    }
    if(i>0) {
      document.all(i).focus();
    }
    window.event.returnValue = false;
  }else if(window.event.keyCode == 39 || window.event.keyCode == 34) {
    window.event.returnValue = false;
  }
}

//function event_contextmenu() {
//  return false;
//}

document.onkeypress = event_key_press;
//document.oncontextmenu = event_contextmenu;

function focusfirst()
{
	var i=nextItem(0);
	if (i>0)
		document.all(i).focus();
}


//�Ժ����Լ������ַ�����unicode����
//����%A0-%FF�ķ�˫�ֽ������ַ�
function encodex(s) {
	if(s == null ) return s;
	if(s.length == 0) return s;
	var ret = "";
	for(i=0;i<s.length;i++) {
		var c = s.substr(i,1);
		var ts = escape(c);
		var tstemp = ts.substr(1,1);
		if(tstemp<='F' && tstemp >='A'){
			ret = ret + "@@00" + ts.substr(1,2);
		} else if(ts.substr(0,2) == "%u") {
			ret = ret + ts.replace("%u","@@");
		} else {
			ret = ret + c;
		}
	}
	return ret;
}