// deleteRequisition.js

// ���ݴ������ҵ����,�µ�ҵ�����,ԭ�ȵ�ҵ����룬ҵ���ɾ��ҵ����ձ��е���ؼ�¼
function DeleteSubmit(sEnpCode,newsOpCode,oldsOpCode,sReqCode) {
     var objHTTP = new ActiveXObject("Microsoft.XMLHTTP")
      var parser=new ActiveXObject("microsoft.xmldom")
      parser.async="false"
      var act = basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.IA.IA_DelRequisitionOp&opDataUnclear=true&xmlOutput=true&opAction=40001&sEnpCode="+sEnpCode+"&newsOpCode="+newsOpCode+"&oldsOpCode="+oldsOpCode+"&sReqCode="+sReqCode;
      objHTTP.Open('GET',act,false);
      objHTTP.Send();
      //��ѯ������
      if(!parser.loadXML(objHTTP.responseText)) {
        alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
        return 0;
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
          alert("������룺" + errorCode + "\n����������" + errorMsg);
          return 0;
      }
      var returnInt=  rootElement.getAttribute("rertunInt");
      return  returnInt;
}
