   /**
   *�ж��Ƿ������һ���־
   * @create date 2007-6-12
   * @create by zheng qi bin
   */
 

	/*--
	���ܣ��ж��Ƿ������һ���־
	���������
     �ͻ����룬����ʱ�䣬�������ͣ�
	--*/
	
	function Isdlpjflg(khcode,pjdate,pjtype) {
	   //У���Ա��������Ϸ���

     if (khcode ==null || khcode =="")  {
	       alert("�ͻ����벻��Ϊ�գ�");
		   return false;
	  }
	  if (pjdate ==null || pjdate =="")  {
	       alert("����ʱ�䲻��Ϊ�գ�");
		   return false;
	  }
	  if (pjtype ==null || pjtype =="")  {
	       alert("�������Ͳ���Ϊ�գ�");
		   return false;
	  }
	  
	  var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
      var parser=new ActiveXObject("microsoft.xmldom");
      parser.async="false";
      var act = basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.khpj.EE.KhpjflagOp&xmlOutput=true&opDataUnclear=true&oper=20001&khcode="+khcode+"&pjdate="+pjdate+"&pjtype="+pjtype+"&time="+new Date();
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
       var result=rootElement.getAttribute("ret_flag");
       
	   return result;
		 
		  
    }
  }
