// common_control.js

/****************************************************************************************************************
                              �����ǹ���У�麯��
*****************************************************************************************************************/
/******************************************************
 *����������ͳһ�˴�������ҵ����̨�ˣ������Ŀ��ƹ���
 *����˵����customercode = �ͻ���                                                    
 *����˵����areacode = ������                                    
 *����˵����tradecode   = ҵ��̨�ʻ�������еĺ�ͬ��
 *����˵����formercode     = ǰһҵ��� ��ҵ��Ʒ��Ϊչ��ʱ���ͽ�ݺţ���ҵ��Ʒ��Ϊ����֤�޸�ʱ��Ϊ����֤�ţ���ҵ��Ʒ��Ϊ�����޸�ʱ��Ϊ������                      
 *����˵����opflag       =   0������ 1����ʽ        
 *����˵����yewukind = ҵ������                
                    

 *���ߣ�huxiaozhong
 *�������ڣ�2005-11-29 
 *****************************************************/

var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
var parser=new ActiveXObject("microsoft.xmldom");
parser.async="false";


function control_check(customercode,areacode,tradecode,formercode,opflag,yewukind) {

   var act = basepath0606+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.Util_ControlOp&xmlOutput=true&opDataUnclear=true&opAction=20001&customercode="+customercode+"&areacode="+areacode+"&tradecode="+encode(tradecode)+"&formercode="+formercode+"&opflag="+opflag+"&yewukind="+yewukind+"&time="+new Date();
     

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
          var node = rootElement.childNodes.item(0);
          var result=node.getAttribute("result");
          if (result=="true")
          return true;
          else{
          alert(node.getAttribute("message"));
          return false;
          }
}