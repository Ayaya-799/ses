/**С��ҵ����
  author:��ֲ
  date:20041103
*/
var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
var parser=new ActiveXObject("microsoft.xmldom");
parser.async="false";
var basepath='/icbc/cmis';

/**
 function func_check_smallenp(enpcode)
 ���ܣ����ݴ�����ҵ����ж��Ƿ�С��ҵ
 ���������enpcode(��ҵ���)
 ���أ�true  ��С��ҵ
      false  alert(����С��ҵ) 
       ��    ���� 
*/
function func_check_smallenp(enpcode)
{
   var tmp = "enpcode="+enpcode+"&time="+(new Date);
   objHTTP.open('GET',basepath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_SmallApprove_toolOp&opDataUnclear=true&opAction=60001&'+tmp,false);
   objHTTP.send();
   xml = objHTTP.responseText;
     if(!parser.loadXML(xml)) {
        return;
     }
   var error = parser.getElementsByTagName("error");
    if(error.length > 0) {
      alert(error.item(0).text);
      return;
    }
    var out_flag = parser.documentElement.getAttribute("smallenp");
    
   if (out_flag == "1" ){
   	return true;
      }
   else
   	{
   	return false;
        } 

}
/**
 ���ܣ��жϱ��ս��Ƿ�Ϸ�
 ���������enpcode����ҵ��ţ�,applykind��ҵ���ţ��� bailrate����֤���������
          applyno������ţ��� obj������ر���Ȩ�ŵ�ҳ����󣩣�grant_seq1��ʹ�ù����ر���Ȩ�ţ�
 ���أ�
       true   ���ս�Ϸ�
              Υ�����Կ���ԭ�� confirm(�ͻ����õȼ�ΪXXX,���ս������Ҫ��[..])
              
       false  Υ�����Կ���ԭ�� alert(�ͻ����õȼ�ΪXXX,���ս�����������XX) 
       
        ��    ���� 
*/
function func_check_bailrate(enpcode,applykind,bailrate,applyno,obj,grant_seq1)
{
   var granttype = "";
   //01���жһ�Ʊ���ر���Ȩ������A1
   if(applykind == "01")
      granttype = "A1";
   else if(applykind == "02" || applykind == "03")  //02���������ౣ����03�������ౣ��
      granttype = "A2";
   else if(applykind == "04" || applykind == "05")  //04����������֤��05����������֤
      granttype = "A3";
   
   var tmp = "enpcode="+enpcode+"&applykind="+applykind+"&bailrate="+bailrate+"&time="+(new Date);
   objHTTP.open('GET',basepath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_SmallApprove_toolOp&opDataUnclear=true&opAction=60002&'+tmp,false);
   objHTTP.send();
   xml = objHTTP.responseText;
     if(!parser.loadXML(xml)) {
        return;
     }
   var error = parser.getElementsByTagName("error");
    if(error.length > 0) {
      alert(error.item(0).text);
      return;
    }	
    var out_flag = parser.documentElement.getAttribute("flag");
    var out_msg = parser.documentElement.getAttribute("msg");
    
    switch (out_flag)
    {
      case "0":
        return true;
        break;
      case "1":
        
        tmp = basepath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&xmlOutput=true&opDataUnclear=true&oper=isjianmian&enpcode="+enpcode+"&granttype="+granttype+"&applyno="+applyno+"&time=" + (new Date);
         objHTTP.Open('GET',tmp,false);

         objHTTP.Send();

         var xml = objHTTP.responseText;
         //alert(xml);

         if(!parser.loadXML(xml)) {
           alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
           return false;
         }
         error = parser.getElementsByTagName("error");
         if(error.length > 0) {
           alert(error.item(0).text);
           return false;
         }
         var count = parser.documentElement.getAttribute("count");
         count = count/1;
        if(count == 0){
          	alert(out_msg);
            return false;
        }
        else{
           var grant_seq = window.showModalDialog(basepath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=listtbsq&enpcode="+enpcode+"&sqtype="+granttype+"&jjh="+applyno+"&bili="+bailrate+"&grant_seq="+grant_seq1+"&time="+new Date(),window,"dialogWidth:570px;dialogHeight:350px;center:yes;help:no;status:no;resizable:no");
              if(grant_seq == null){
                alert(out_msg);
                return false;
              }
              else{
                obj.value = grant_seq;
                return true;
              }
        
        } 
        break;
      case "2":
        confirm(out_msg);
        return true;
        break;
      default:
        return false;
     }
 
}

/**
  ���ܣ��жϱ��ս��Ƿ�Ϸ�
 ���������enpcode ��ҵ���,applykindҵ���� opcurҵ����� opamountҵ���� 
           bailcur��֤����� bailamount��֤���applyno������ţ���
          obj������ر���Ȩ�ŵ�ҳ����󣩣�grant_seq1��ʹ�ù����ر���Ȩ�ţ�
 ���أ�
       true   ���ս�Ϸ�
              Υ�����Կ���ԭ�� confirm(�ͻ����õȼ�ΪXXX,���ս������Ҫ��[..])
              
       false  Υ�����Կ���ԭ�� alert(�ͻ����õȼ�ΪXXX,���ս�����������XX) 
       
        ��    ���� 
 */

function func_check_bailrate2(enpcode,applykind,opcur,opamount,bailcur,bailamount,applyno,obj,grant_seq1,obj1)
{
   var granttype = "";
   //01���жһ�Ʊ���ر���Ȩ������A1
   if(applykind == "01")
      granttype = "A1";
   else if(applykind == "02" || applykind == "03")  //02���������ౣ����03�������ౣ��
      granttype = "A2";
   else if(applykind == "04" || applykind == "05")  //04����������֤��05����������֤
      granttype = "A3";
   
   
   var tmp = "enpcode="+enpcode+"&applykind="+applykind+"&opcur="+opcur+"&opamount="+opamount+"&bailcur="+bailcur+"&bailamount="+bailamount+"&time="+(new Date);
   objHTTP.open('GET',basepath+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_SmallApprove_toolOp&opDataUnclear=true&opAction=60003&'+tmp,false);
   objHTTP.send();
   xml = objHTTP.responseText;
     if(!parser.loadXML(xml)) {
        return false;
     }
   var error = parser.getElementsByTagName("error");
    if(error.length > 0) {
      alert(error.item(0).text);
      return false;
    }	
    var out_flag = parser.documentElement.getAttribute("flag");
    var out_msg = parser.documentElement.getAttribute("msg");
    var out_bailrate = parser.documentElement.getAttribute("bailrate");
    obj1.value = out_bailrate;
    switch (out_flag)
    {
      case "0":
        return true;
        break;
      case "1":
        tmp = basepath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&xmlOutput=true&opDataUnclear=true&oper=isjianmian&enpcode="+enpcode+"&granttype="+granttype+"&applyno="+applyno+"&time=" + (new Date);
         objHTTP.Open('GET',tmp,false);

         objHTTP.Send();

         var xml = objHTTP.responseText;
         //alert(xml);

         if(!parser.loadXML(xml)) {
           alert(parser.parseError.reason + "\n" +parser.parseError.line + "\n" +parser.parseError.srcText + "\n");
           return false;
         }
         error = parser.getElementsByTagName("error");
         if(error.length > 0) {
           alert(error.item(0).text);
           return false;
         }
         var count = parser.documentElement.getAttribute("count");
         count = count/1;
        if(count == 0){
          	alert(out_msg);
            return false;
        }
        else{
           var grant_seq = window.showModalDialog(basepath+"/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=listtbsq&enpcode="+enpcode+"&sqtype="+granttype+"&jjh="+applyno+"&bili="+out_bailrate+"&grant_seq="+grant_seq1+"&time="+new Date(),window,"dialogWidth:570px;dialogHeight:350px;center:yes;help:no;status:no;resizable:no");
              if(grant_seq == null){
                alert(out_msg);
                return false;
              }
              else{
                obj.value = grant_seq;
                return true;
              }
        
        } 
      case "2":
        confirm(out_msg);
        return true;
        break;
      default:
        return false;
     }
 
}
