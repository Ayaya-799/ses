/**
   *��ԱУ��
   * @create date 2004-4-24 15:34
   * @create by zheng ze zhou
   * @ע����js���������ǩ���˹���ģ��
   */
  /**
  * �޸����ڣ�2004-4-27 9:09
  * �޸�ԭ�������˲�ѯ�ж��������Ƿ���ǩ��Ȩ
  * �޸��ˣ�֣����
  * �޸����ݣ������˲�ѯ�ж��������Ƿ���ǩ��Ȩ
  */
  /**
  * �޸����ڣ�2004-7-21 15:51
  * �޸�ԭ�������ծ��Ŀ��������
  * �޸��ˣ�֣����
  * �޸����ݣ����������ǽ��ṩ�ĺ���
  */
  /**
  * �޸����ڣ�2005-2-21 15:58
  * �޸�ԭ���������������ڲ�¼Ȩ�޿���
  * �޸��ˣ�֣����
  * �޸����ݣ���¼Ȩ�޲�ѯ����
  */

 //var webBasePath = "/icbc/cmis";

	/*--
	���ܣ����ǰ̨����Ĺ�Ա����
	������obj ҳ���ϵ�һ������򣬴������ͬʱ��ʾ��Ա���������
	            obj2 ҳ���ϵ�һ�������hidden�򣩣�������򱣴��Ա����
	--*/
	function checkEmployeeCode(obj,obj2) {
	   //У���Ա��������Ϸ���
	   if (obj.readOnly == true) return false;
	   var  code = obj.value;
     if (code.length != 9)  {
	       alert("������9λ��Ա�ţ�");
		   return false;
	  }
	  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      parser.async="false";
      var act = basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.MA.MA_EmployeeCodeVerifyOp&xmlOutput=true&opDataUnclear=true&oper=checkEmployee&employeecode="+code+"&time="+new Date();
      //��ѯ
      objHTTP.Open('GET',act,false);
      objHTTP.Send();
      //��ѯ������
      if(!parser.loadXML(objHTTP.responseText)) {
        alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
        return false;
      }
      //���session��ʱ�ͽ���ʧ�ܣ���ʾ������Ϣ
      rootElement = parser.documentElement;
      if (rootElement.tagName == "error") {
          errorCode = "";
          errorMsg = "";
          for (i=0; i<rootElement.childNodes.length; i++) {
              if (rootElement.childNodes.item(i).tagName == "errorCode") {
                   errorCode = rootElement.childNodes.item(i).text;
              }
              else if (rootElement.childNodes.item(i).tagName == "errorDispMsg") {
                  errorMsg = rootElement.childNodes.item(i).text;
              }
          }
          alert(" ������룺" + errorCode + "\n����������" + errorMsg);
          return false;
      }
    if(rootElement.tagName=="datas")
    {
       var result=rootElement.getAttribute("employeename");
       if (result == '0') {
			  alert("�ù�Ա�����ڣ�");
			  obj.focus();
			  return false;
		   }
		   else {
			  if (!confirm("��ȷ����������Ĺ�Ա��:"+code+" "+result+"?")) {
          obj.value = "";
				  return false;
			  }
			  else {
			 	  obj.value = code+" "+result;
				  obj2.value = code;
			  }
		  }
    }
  }

	/*--
	���ܣ��ж��������Ƿ���ǩ��Ȩ
	������employeecode ����ù�Ա����
	�����true ��Ȩ false ��Ȩ
	--*/
	function isEmployeeSign(employeecode) {
	   //У���Ա��������Ϸ���
	   var  code = employeecode;
     if (code.length != 9)  {
	       alert("������9λ��Ա�ţ�");
		   return false;
	  }
	  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      parser.async="false";
      var act = basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.MA.MA_EmployeeCodeVerifyOp&xmlOutput=true&opDataUnclear=true&oper=isEmployeeSign&employeecode="+code+"&time="+new Date();
      //��ѯ
      objHTTP.Open('GET',act,false);
      objHTTP.Send();
      //��ѯ������
      if(!parser.loadXML(objHTTP.responseText)) {
        alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
        return false;
      }
      //���session��ʱ�ͽ���ʧ�ܣ���ʾ������Ϣ
      rootElement = parser.documentElement;
      if (rootElement.tagName == "error") {
          errorCode = "";
          errorMsg = "";
          for (i=0; i<rootElement.childNodes.length; i++) {
              if (rootElement.childNodes.item(i).tagName == "errorCode") {
                   errorCode = rootElement.childNodes.item(i).text;
              }
              else if (rootElement.childNodes.item(i).tagName == "errorDispMsg") {
                  errorMsg = rootElement.childNodes.item(i).text;
              }
          }
          alert(" ������룺" + errorCode + "\n����������" + errorMsg);
          return false;
      }
    if(rootElement.tagName=="datas")
    {
       var result=rootElement.getAttribute("employeename");
       if (result == '1') {
			  return true;
		   }
		   else {
			  return false;
		  }
    }
  }

	/*--
	���ܣ����ǰ̨����Ĺ�Ա����
	������obj ҳ���ϵ�һ������򣬴������ͬʱ��ʾ��Ա����
	��ע����һ��Ϊobj+"name"���������ʾ����
	--*/
	function checkEmployee(obj) {
	   //У���Ա��������Ϸ���
	   var  code = obj.value;
     if (code.length != 9)  {
	       alert("������9λ��Ա�ţ�");
		   obj.value = "";
		   return false;
	  }
	  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      parser.async="false";
      var act = basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.MA.MA_EmployeeCodeVerifyOp&xmlOutput=true&opDataUnclear=true&oper=checkEmployee&employeecode="+code+"&time="+new Date();
      //��ѯ
      objHTTP.Open('GET',act,false);
      objHTTP.Send();
      //��ѯ������
      if(!parser.loadXML(objHTTP.responseText)) {
        alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
        return false;
      }
      //���session��ʱ�ͽ���ʧ�ܣ���ʾ������Ϣ
      rootElement = parser.documentElement;
      if (rootElement.tagName == "error") {
          errorCode = "";
          errorMsg = "";
          for (i=0; i<rootElement.childNodes.length; i++) {
              if (rootElement.childNodes.item(i).tagName == "errorCode") {
                   errorCode = rootElement.childNodes.item(i).text;
              }
              else if (rootElement.childNodes.item(i).tagName == "errorDispMsg") {
                  errorMsg = rootElement.childNodes.item(i).text;
              }
          }
          alert(" ������룺" + errorCode + "\n����������" + errorMsg);
          return false;
      }
    if(rootElement.tagName=="datas")
    {
      var objName;
      var result=rootElement.getAttribute("employeename");
      if (result == '0') {
			  alert("�ù�Ա�����ڣ�");
			  obj.focus();
			  return false;
		  }
		  else {
			  if (!confirm("��ȷ����������Ĺ�Ա��:"+code+" "+result+"?")) {
				  objName = eval("form1."+obj.name+"name");
				  obj.value = "";
				  objName.value = "";
				  return false;
			  }
			  else {
			  	objName = eval("form1."+obj.name+"name");
				  objName.value = result;
			  }
		  }
    }
  }

/******************************************************
 *��������:����Ա����Ϸ��ķ���
 *����˵����var EmployeeCode ��Ա����
 *���ߣ�Zhouxj
 *�������ڣ�2004/07/02
 ���÷���������
 alert(checkEmployee2("120200206"));
 *****************************************************/
function checkEmployee2(EmployeeCode) {

	   var  code = EmployeeCode;
      if (code.length != 9)  {
		   return false;
	  }
	  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      parser.async="false";
      var act = basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.MA.MA_EmployeeCodeVerifyOp&xmlOutput=true&opDataUnclear=true&oper=checkEmployee&employeecode="+code+"&time="+new Date();
      //��ѯ
      objHTTP.Open('GET',act,false);
      objHTTP.Send();
      //��ѯ������
      if(!parser.loadXML(objHTTP.responseText)) {
        alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
        return false;
      }
      //���session��ʱ�ͽ���ʧ�ܣ���ʾ������Ϣ
      rootElement = parser.documentElement;
      if (rootElement.tagName == "error") {
          errorCode = "";
          errorMsg = "";
          for (i=0; i<rootElement.childNodes.length; i++) {
              if (rootElement.childNodes.item(i).tagName == "errorCode") {
                   errorCode = rootElement.childNodes.item(i).text;
              }
              else if (rootElement.childNodes.item(i).tagName == "errorDispMsg") {
                  errorMsg = rootElement.childNodes.item(i).text;
              }
          }
          alert(" ������룺" + errorCode + "\n����������" + errorMsg);
          return false;
      }
    if(rootElement.tagName=="datas")
    {
      var objName;
      var result=rootElement.getAttribute("employeename");
      if (result == '0') {
			  return false;
		  }
		  else {
			  return true;
		  }
    }
}

/******************************************************
 *��������:�ù�Ա��ȡ��Ա���Ƶķ���
 *����˵����var EmployeeCode ��Ա����
 * ���ع�Ա����
 *���ߣ�Zhouxj
 *�������ڣ�2004/07/02
  ���÷���������
alert(getEmployeeName("120200206"));
 *****************************************************/
function getEmployeeName(EmployeeCode) {

	   var  code = EmployeeCode;
     if (code.length != 9)  {
		   return "��Ա�ű�����9λ";
	  }
	  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      parser.async="false";
      var act = basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.MA.MA_EmployeeCodeVerifyOp&xmlOutput=true&opDataUnclear=true&oper=checkEmployee&employeecode="+code+"&time="+new Date();
      //��ѯ
      objHTTP.Open('GET',act,false);
      objHTTP.Send();
      //��ѯ������
      if(!parser.loadXML(objHTTP.responseText)) {
        alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
        return false;
      }
      //���session��ʱ�ͽ���ʧ�ܣ���ʾ������Ϣ
      rootElement = parser.documentElement;
      if (rootElement.tagName == "error") {
          errorCode = "";
          errorMsg = "";
          for (i=0; i<rootElement.childNodes.length; i++) {
              if (rootElement.childNodes.item(i).tagName == "errorCode") {
                   errorCode = rootElement.childNodes.item(i).text;
              }
              else if (rootElement.childNodes.item(i).tagName == "errorDispMsg") {
                  errorMsg = rootElement.childNodes.item(i).text;
              }
          }
          alert(" ������룺" + errorCode + "\n����������" + errorMsg);
          return false;
      }
    if(rootElement.tagName=="datas"){
		var objName;
		var result=rootElement.getAttribute("employeename");
			if (result == '0') {
				return "�ù�Ա�����ڣ�";
			}
			else {
				return result+"";
			}
	}
}

/******************************************************
 *��������:��¼Ȩ�޲�ѯ�ķ���
 *����˵����
 *    areacode �������� 
 *    customercode �ͻ�����
 *    bussinesstype ҵ������
 *    controltype ��������
 *    bussinesspk ҵ������
 * �����Ƿ�ɹ���־
 *���ߣ�zhengzezhou 
 *�������ڣ� 2005-2-21 16:03
  ���÷���������
 *****************************************************/
function patchCheck(areacode,customercode,bussinesstype,controltype,bussinesspk) {

	  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      parser.async="false";
      var tmp = "&areacode="+areacode+"&customercode="+customercode+"&bussinesstype="+bussinesstype+"&controltype="+controltype+"&bussinesspk="+bussinesspk;
      var act = basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.MA.MA_PatchGrantOp&xmlOutput=true&opDataUnclear=true&opAction=patchCheck"+tmp+"&time="+new Date();
      //��ѯ
      objHTTP.Open('GET',act,false);
      objHTTP.Send();
      //��ѯ������
      if(!parser.loadXML(objHTTP.responseText)) {
        alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
        return false;
      }
      //���session��ʱ�ͽ���ʧ�ܣ���ʾ������Ϣ
      rootElement = parser.documentElement;
      if (rootElement.tagName == "error") {
          errorCode = "";
          errorMsg = "";
          for (i=0; i<rootElement.childNodes.length; i++) {
              if (rootElement.childNodes.item(i).tagName == "errorCode") {
                   errorCode = rootElement.childNodes.item(i).text;
              }
              else if (rootElement.childNodes.item(i).tagName == "errorDispMsg") {
                  errorMsg = rootElement.childNodes.item(i).text;
              }
          }
          alert(" ������룺" + errorCode + "\n����������" + errorMsg);
          return false;
      }
    if(rootElement.tagName=="Content"){
		var objName;
		var result=rootElement.getAttribute("result");
			if (result == 'OK') {
				return true;
			}
			else {
				alert(result);
				return false;
			}
	  }
	  return true;
}