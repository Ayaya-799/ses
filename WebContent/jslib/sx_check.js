    function sx_check(enp_code,enp_type,loan_date,curr_type,loan_amount,uniqueID1,uniqueID2,uniqueID3,uniqueID4,v_loantype,v_flowtype){
     /*   enp_code   ��ҵ����
          enp_type  ��ҵ����
         loan_date   �ſ�����
         curr_type   ����
         loan_amount ���
         uniqueid1   Ψһ����1  ����|�ͻ���|ҵ����|���ҵ����|ҵ�������|
         uniqueid2   Ψһ����2  Ʒ��|ҵ��ʼʱ��|ҵ����ֹʱ��|ҵ����������|�Ƿ���Ŀ���ʱ��|������ʽ|����ҵ������������Ҽƽ��|01|
         uniqueid3   Ψһ����3  ������ҼƵ����ҵ���ܶ�|����ҵ���Ӧ�ķ�����|������Ҫ���Ƶķ�����|-ҵ���Ӧ�ļ�Ȩ����ϵ��|������|������|
         uniqueid4   Ψһ����4  ���ʽ|������ʽ|
         v_loantype  da340362020ҵ������
         v_flowtype  --���̱��   0 ���� 1���� 2 ׼��֤ 3����

        �Ƿ���Կ��� �ж������� v_flowtype=0 and isProve=1  ���Կ���
        �Ƿ����Կ��� �ж�������  v_flowtype=1 ����ʱ��˫���ٹ� ����
                                 v_flowtype=0 and isProve=2  ���Կ���
         ���ز�����false --Ϊ���������  true --���ſ���ͨ��
         
         update by wz 20051226  ���ڱ�����encode()����

      */
       var objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
        var parser=new ActiveXObject("microsoft.xmldom");
        parser.async="false";
        
        var tmp = "&EnpCode="+encode(enp_code)+"&LoanDate="+encode(loan_date)+"&CurrType="+encode(curr_type)+"&LoanAmount="+encode(loan_amount)+"&UniqueID1="+encode(uniqueID1)+"&UniqueID2="+encode(uniqueID2)+"&UniqueID3="+encode(uniqueID3)+"&UniqueID4="+encode(uniqueID4)+ "&LoanType="+encode(v_loantype)+"&time="+(new Date)+"&FlowType="+encode(v_flowtype) + "&Enp_type=" + encode(enp_type);
         objHTTP.open('GET',basepath0606+'/servlet/icbc.cmis.servlets.CmisReqServlet?operationName=icbc.cmis.util.util_SxOper&opDataUnclear=true&opAction=sxop60'+tmp,false);
         objHTTP.send();
          var xml = objHTTP.responseText;
           if(!parser.loadXML(xml)) {
              
              return false;
           }
          error = parser.getElementsByTagName("error");
          if(error.length > 0) {
            alert(error.item(0).text);
            return false;
          }
          var out_flag = parser.documentElement.getAttribute("out_flag");
          var out_mesg = parser.documentElement.getAttribute("out_mesg");
          if(out_flag == "1"){  //������
            while (out_mesg.indexOf('*')!=-1) {
             var alert_info = out_mesg.substring(0,out_mesg.indexOf('*'));
             if (!confirm(alert_info)) return false;
             out_mesg = out_mesg.substring(out_mesg.indexOf('*')+1);
            }
            if (v_flowtype=="2")
               return false;//׼��֤ʱ���Կ���
            
          }else if(out_flag == "2"){ //�쳣
            alert(out_mesg);
            return false;
          }else if(out_flag == "3"){ //����ʾ��Ϣ
             while (out_mesg.indexOf('*')!=-1) {
             var alert_info = out_mesg.substring(0,out_mesg.indexOf('*'));
             alert(alert_info);
             out_mesg = out_mesg.substring(out_mesg.indexOf('*')+1);
            }
            return true;
          }
          return true;
     }

   function sx_auth(enp_code) {
     var ts = window.showModalDialog(basepath0606+"/util/util_Authorize.jsp?module=icbc.cmis.FG.BankDraftMgrOp&time=" + (new Date),"��ҵ"+enp_code+"����������","dialogWidth:295px;dialogHeight:230px;center:yes;help:no;status:no;resizable:no");
     if(ts != null) {
       return true;
     }
     return false;
   }
