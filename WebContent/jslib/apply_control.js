/**
   *�������
   * @param qycode     �ͻ�����
   * @param areacode   ��������
   * @param dkfs       ������ʽ
   * @param ywpz       ҵ��Ʒ��
   * @param sqje       ������
   * @param currtype   ����
   * @param sqtype     �ر���Ȩ����
   * @param checktype  �粻������Կ����������Ƿ���Ҫ˫���ٹ�
   *                   ����׼��֤ʱ�������ݺţ���Ҫ˫���ٹ� ���벻��˫���ٹ�Ϊ0.
   * @return boolean
   * @
   * --	 -1 ��ҵ�˳���־Ϊ'ȫ��'(����)
   * --	 -2 ��ҵ��Ʒ��Ϊͣ�� (����)
   * --	 -3 ����Υ��
   * --	 -4 �����ϸ��Կ���
   * --  -5 ͣ�쵫�����ر���Ȩ
   * --  -6 �����в��ܰ����ҵ��Ʒ�� 
   * --  -7 û��ʵʱ����
   * --   0 ��ȷ
   * --   99 ���ݿ����ʧ��
   */
  /**
  * �޸��� 2003��7��19
  * �޸�ԭ�򣺿ͻ������ҵ�������ͣ��ҵ��ֻҪ���ر���ȨҲ�ɰ���
  * �޸��ˣ����M
  * �޸����ݣ��ڵ���������ƴ洢����ʱ������������������ֺ��ر���Ȩ����
  */
/**
 * �޸��� 2003��7��24
 * �޸�ԭ��ԭ������ƴ洢���̶�����Ŀ����������ֲ���֧�֡����ڷ������ʽ���������ͣ��ҵ�����Ҳ�������⡣
 * �޸��ˣ� ���M
 * �޸����ݣ�������Ŀ�����������µ�������ƴ洢����Proc_AppCtrl_project�����ڷ������ʽ�����������Proc_AppCtrl_contract
 * 
 */
 
 /**
 * �޸��� 2003��10��20
 * �޸�ԭ��ͣ��ҵ���������������������ƴ洢���̲����䶯
 * �޸��ˣ����M
 * �޸����ݣ�����������ƴ洢���̲�������������֤������֤�޸ġ�����Ѻ�㡢����Ѻ������������ƣ�
 *           ���ڸ��Կ��Ʋ��֣�ó�����ʲ�����ʱ������ 
 * 
 */
 
 /* �޸��� 20050608
  * �޸�ԭ�����ӹ�����ҵ�������ơ�
  * �޸��ˣ����M
  * �޸����ݣ����ڹ����а���ҵ��ʱ�����Ƿ���Ȩ���жϡ�
 */

function func_apply_control(enpcode,areacode,jtdkfs,ywpz1,ywpz2,sqje,currtype,checktype,sqtype,dkfs,dkxs){
   var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
   var parser=new ActiveXObject("microsoft.xmldom");
   parser.async="false";

   var tmp = "enpcode="+enpcode+"&areacode="+areacode+"&currtype="+currtype+"&dkfs="+dkfs+"&ywpz1="+ywpz1+"&ywpz2="+ywpz2+"&sqje="+sqje+"&sqtype="+sqtype+"&jtdkfs="+jtdkfs+"&dkxs="+dkxs+"&time="+(new Date);

   objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=appctrl&'+tmp,false);
   objHTTP.send();
    xml = objHTTP.responseText;
     if(!parser.loadXML(xml)) {
        return;
     }
    error = parser.getElementsByTagName("error");
    if(error.length > 0) {
      alert(error.item(0).text);
      return;
    }
    var out_flag = parser.documentElement.getAttribute("result");
    var out_message = parser.documentElement.getAttribute("message");
    if(out_flag!="0"){
       while (out_message.indexOf('*')!=-1) {
          out_message = out_message.replace("*","\r\n");
       }
    }
    
    if(out_flag == "0"){
      return true;
    }
    else if(out_flag == "-1"||out_flag == "-8"){
      alert(out_message);
      return false;
    }
    else if(out_flag == "-2"){
      if(checktype == "0" || checktype == "1"){
        alert(out_message);
        return false;
      }
      else{
        var tmp = "enpcode="+enpcode+"&due="+checktype+"&tbyw="+ywpz1+"&currtype="+currtype+"&time="+(new Date);

        objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=checkusespecial&'+tmp,false);
        objHTTP.send();
        xml = objHTTP.responseText;
        if(!parser.loadXML(xml)) {
          return;
        }
        error = parser.getElementsByTagName("error");
        if(error.length > 0) {
          alert(error.item(0).text);
          return;
        }
        var return_count = parser.documentElement.getAttribute("return");
        return_count = return_count/1;
        if(return_count==0){
          alert(out_message);
          return false;
        }
        else{
          return true;
        }
      }
    }
    else if(out_flag == "-3"){
      if(confirm(out_message))
        return true;
      else
        return false;
    }
    else if(out_flag == "-4"){
      if(checktype != "0"){
        if(confirm(out_message)){
          var ts = window.showModalDialog(basepath0606+"/util/util_Authorize.jsp?module=��ҵ"+enpcode+"�Ĵ��������Կ�������"+"&time=" + (new Date),'��������Կ�������',"dialogWidth:295px;dialogHeight:230px;center:yes;help:no;status:no;resizable:no");
          if(ts != null) {
            return true;
          }
          return false;
        }
        else
          return false;
      }
      else{
        if(confirm(out_message))
          return true;
        else
          return false;
      }
    }
    else if(out_flag == "99"){
      alert(out_message);
      return false;
    }
    else if(out_flag == "-5"){
      if(checktype == "0"|| checktype == "1"){
        return true;
      }
      else{
        var tmp = "enpcode="+enpcode+"&due="+checktype+"&tbyw="+ywpz1+"&currtype="+currtype+"&time="+(new Date);

        objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=checkusespecial&'+tmp,false);
        objHTTP.send();
        xml = objHTTP.responseText;
        if(!parser.loadXML(xml)) {
          return;
        }
        error = parser.getElementsByTagName("error");
        if(error.length > 0) {
          alert(error.item(0).text);
          return;
        }
        var return_count = parser.documentElement.getAttribute("return");
        return_count = return_count/1;
        if(return_count==0){
          alert(out_message);
          return false;
        }
        else{
          return true;
        }
      }
    }
    else if(out_flag == "-6"){
      if(checktype == "0"){
        alert(out_message);
        return false;
      }
    }
    else if(out_flag == "-7"){
      alert(out_message);
      return false;
    }
    return true;
}

function func_apply_control_project(enpcode,areacode,dkfs,ywpz1,ywpz2,sqje,currtype,checktype,sqtype,jtdkfs,dkxs){
   var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
   var parser=new ActiveXObject("microsoft.xmldom");
   parser.async="false";

   var tmp = "enpcode="+enpcode+"&areacode="+areacode+"&currtype="+currtype+"&dkfs="+dkfs+"&ywpz1="+ywpz1+"&ywpz2="+ywpz2+"&sqje="+sqje+"&sqtype="+sqtype+"&jtdkfs="+jtdkfs+"&dkxs="+dkxs+"&time="+(new Date);

   objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=appctrlproject&'+tmp,false);
   objHTTP.send();
    xml = objHTTP.responseText;
     if(!parser.loadXML(xml)) {
        return;
     }
    error = parser.getElementsByTagName("error");
    if(error.length > 0) {
      alert(error.item(0).text);
      return;
    }
    var out_flag = parser.documentElement.getAttribute("result");
    var out_message = parser.documentElement.getAttribute("message");
    if(out_flag!="0"){
       while (out_message.indexOf('*')!=-1) {
          out_message = out_message.replace("*","\r\n");
       }
    }
    if(out_flag == "0"){
      return true;
    }
    else if(out_flag == "-1"||out_flag == "-8"){
      alert(out_message);
      return false;
    }
    else if(out_flag == "-2"){
      if(checktype == "0"){
        alert(out_message);
        return false;
      }
      else{
        var tmp = "enpcode="+enpcode+"&due="+checktype+"&tbyw="+ywpz1+"&currtype="+currtype+"&time="+(new Date);

        objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=checkusespecial&'+tmp,false);
        objHTTP.send();
        xml = objHTTP.responseText;
        if(!parser.loadXML(xml)) {
          return;
        }
        error = parser.getElementsByTagName("error");
        if(error.length > 0) {
          alert(error.item(0).text);
          return;
        }
        var return_count = parser.documentElement.getAttribute("return");
        return_count = return_count/1;
        if(return_count==0){
          alert(out_message);
          return false;
        }
        else{
          return true;
        }
      }
    }
    else if(out_flag == "-3"){
      if(confirm(out_message))
        return true;
      else
        return false;
    }
    else if(out_flag == "-4"){
      if(checktype != "0"){
        if(confirm(out_message)){
          var ts = window.showModalDialog("/icbc/cmis/util/util_Authorize.jsp?module=��ҵ"+enpcode+"�Ĵ��������Կ�������"+"&time=" + (new Date),'��������Կ�������',"dialogWidth:295px;dialogHeight:230px;center:yes;help:no;status:no;resizable:no");
          if(ts != null) {
            return true;
          }
          return false;
        }
        else
          return false;
      }
      else{
        if(confirm(out_message))
          return true;
        else
          return false;
      }
    }
    else if(out_flag == "99"){
      alert(out_message);
      return false;
    }
    else if(out_flag == "-5"){
      if(checktype == "0"){
        return true;
      }
      else{
        var tmp = "enpcode="+enpcode+"&due="+checktype+"&tbyw="+ywpz1+"&currtype="+currtype+"&time="+(new Date);

        objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=checkusespecial&'+tmp,false);
        objHTTP.send();
        xml = objHTTP.responseText;
        if(!parser.loadXML(xml)) {
          return;
        }
        error = parser.getElementsByTagName("error");
        if(error.length > 0) {
          alert(error.item(0).text);
          return;
        }
        var return_count = parser.documentElement.getAttribute("return");
        return_count = return_count/1;
        if(return_count==0){
          alert(out_message);
          return false;
        }
        else{
          return true;
        }
      }
    }
    else if(out_flag == "-6"){
      alert(out_message);
      return false;
    }
    else if(out_flag == "-7"){
      alert(out_message);
      return false;
    }
    return true;
}


function func_apply_control_contract(enpcode,areacode,dkfs,ywpz1,ywpz2,sqje,currtype,checktype,sqtype,spno,jtdkfs,dkxs){
   var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
   var parser=new ActiveXObject("microsoft.xmldom");
   parser.async="false";

   var tmp = "enpcode="+enpcode+"&areacode="+areacode+"&currtype="+currtype+"&dkfs="+dkfs+"&ywpz1="+ywpz1+"&ywpz2="+ywpz2+"&sqje="+sqje+"&sqtype="+sqtype+"&spno="+spno+"&jtdkfs="+jtdkfs+"&dkxs="+dkxs+"&time="+(new Date);

   objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=appctrlcontract&'+tmp,false);
   objHTTP.send();
    xml = objHTTP.responseText;
     if(!parser.loadXML(xml)) {
        return;
     }
    error = parser.getElementsByTagName("error");
    if(error.length > 0) {
      alert(error.item(0).text);
      return;
    }
    var out_flag = parser.documentElement.getAttribute("result");
    var out_message = parser.documentElement.getAttribute("message");
    if(out_flag!="0"){
       while (out_message.indexOf('*')!=-1) {
          out_message = out_message.replace("*","\r\n");
       }
    }
    if(out_flag == "0"){
      return true;
    }
    else if(out_flag == "-1"||out_flag == "-8"){
      alert(out_message);
      return false;
    }
    else if(out_flag == "-2"){
      if(checktype == "0"){
        alert(out_message);
        return false;
      }
      else{
        var tmp = "enpcode="+enpcode+"&due="+checktype+"&tbyw="+ywpz1+"&currtype="+currtype+"&time="+(new Date);

        objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=checkusespecial&'+tmp,false);
        objHTTP.send();
        xml = objHTTP.responseText;
        if(!parser.loadXML(xml)) {
          return;
        }
        error = parser.getElementsByTagName("error");
        if(error.length > 0) {
          alert(error.item(0).text);
          return;
        }
        var return_count = parser.documentElement.getAttribute("return");
        return_count = return_count/1;
        if(return_count==0){
          alert(out_message);
          return false;
        }
        else{
          return true;
        }
      }
    }
    else if(out_flag == "-3"){
      if(confirm(out_message))
        return true;
      else
        return false;
    }
    else if(out_flag == "-4"){
      if(checktype != "0"){
        if(confirm(out_message)){
          var ts = window.showModalDialog("/icbc/cmis/util/util_Authorize.jsp?module=��ҵ"+enpcode+"�Ĵ��������Կ�������"+"&time=" + (new Date),'��������Կ�������',"dialogWidth:295px;dialogHeight:230px;center:yes;help:no;status:no;resizable:no");
          if(ts != null) {
            return true;
          }
          return false;
        }
        else
          return false;
      }
      else{
        if(confirm(out_message))
          return true;
        else
          return false;
      }
    }
    else if(out_flag == "99"){
      alert(out_message);
      return false;
    }
    else if(out_flag == "-5"){
      if(checktype == "0"){
        return true;
      }
      else{
        var tmp = "enpcode="+enpcode+"&due="+checktype+"&tbyw="+ywpz1+"&currtype="+currtype+"&time="+(new Date);

        objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=checkusespecial&'+tmp,false);
        objHTTP.send();
        xml = objHTTP.responseText;
        if(!parser.loadXML(xml)) {
          return;
        }
        error = parser.getElementsByTagName("error");
        if(error.length > 0) {
          alert(error.item(0).text);
          return;
        }
        var return_count = parser.documentElement.getAttribute("return");
        return_count = return_count/1;
        if(return_count==0){
          alert(out_message);
          return false;
        }
        else{
          return true;
        }
      }
    }
    else if(out_flag == "-6"){
      alert(out_message);
      return false;
    }
    else if(out_flag == "-7"){
      alert(out_message);
      return false;
    }
    return true;
}

/**
 * ��ѯ����ҵ�������Ƿ����δɨ��ı�ɨӰ������
 * @param enpcode �ͻ�����
 * @param applycode  �������
 * @param opcode   ҵ������
 * @param assuretype ������ʽ����λ����һλ��ʾ���ã��ڶ�λ��ʾ��֤
 * ����λ��ʾ��Ѻ������λ��ʾ��Ѻ
 *
 * �������0�����ʾ������δɨ��ı�ɨӰ������
 * ����1�����ʾ����δɨ��ı�ɨӰ������
 */
function imagecontrol(enpcode,applycode,opcode,assuretype){
   var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
   var parser=new ActiveXObject("microsoft.xmldom");
   parser.async="false";

   var tmp = "enpcode="+enpcode+"&applycode="+encode(applycode)+"&assuretype="+assuretype+"&opcode="+opcode+"&time="+(new Date);

   objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=queryimage&'+tmp,false);
   objHTTP.send();
    xml = objHTTP.responseText;
     if(!parser.loadXML(xml)) {
        return;
     }
    error = parser.getElementsByTagName("error");
    if(error.length > 0) {
      alert(error.item(0).text);
      return;
    }
    var out_flag = parser.documentElement.getAttribute("result");

    if(out_flag == "0"){
      return true;
    }
    else{
      alert("����ҵ��������뻹��δɨ���Ӱ�����ϣ���ɨ�衣");
      return false;
    }
}

/**
 * ��ѯ����ҵ��ҵ���Ƿ����δɨ��ı�ɨӰ������
 * @param enpcode �ͻ�����
 * @param applycode  ҵ�����
 * @param opcode   ҵ������
 *
 *
 * �������0�����ʾ������δɨ��ı�ɨӰ������
 * ����1�����ʾ����δɨ��ı�ɨӰ������
 */
function imageaftercontrol(enpcode,applycode,opcode){
   var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
   var parser=new ActiveXObject("microsoft.xmldom");
   parser.async="false";

   var tmp = "enpcode="+enpcode+"&applycode="+encode(applycode)+"&opcode="+opcode+"&time="+(new Date);

   objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.FE.FE_utilOp&opDataUnclear=true&oper=queryimage1&'+tmp,false);
   objHTTP.send();
    xml = objHTTP.responseText;
     if(!parser.loadXML(xml)) {
        return;
     }
    error = parser.getElementsByTagName("error");
    if(error.length > 0) {
      alert(error.item(0).text);
      return;
    }
    var out_flag = parser.documentElement.getAttribute("result");

    if(out_flag == "0"){
      return true;
    }
    else{
      alert("����ҵ�����ҵ����δɨ���Ӱ�����ϣ���ɨ�衣");
      return false;
    }
}



