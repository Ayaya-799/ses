// sx_control.js

	/******************************************************
 	 *��������:	���ſ��ƴ���
 	 *����˵����customerid:�ͻ��ţ�contractid:��ˮ�Ż�����ҵ����
 	           operatetype:�������ͣ�producttype:��Ʒ����
 	           money:ʵ��ҵ���billtype:�������������
 	           begindate:ҵ����ʼ�գ�enddate:ҵ����ֹ��
 	           requestServletUrl:����Servlet��Url
 	           requiredHtmlParams:Ҫ���html����
 	 *���ߣ�hushou
 	 *�������ڣ�2004-08-11
 	 *****************************************************/
	function f_GrantControl(customerid,contractid,operatetype,producttype,money,billtype,begindate,enddate,requestServletUrl,requiredHtmlParams){
		if(contractid == ""){
			contractid ="1";
		}
		//ƴ��xml��
   		//var requestServletUrl = '<%=fullPath%><%=utb.getRequestServletUrl()%>';
		//var requiredHtmlParams = '<%= utb.getRequiredHtmlParams (null,"util_GrantControlOp")%>' ;
		
		var act0 =  requestServletUrl + "?xmlRequest=true";
		var act = act0 + '&' + requiredHtmlParams + '&customerid='+customerid+'&contractid='+contractid+'&operatetype='+operatetype+'&producttype='+producttype+'&money='+money+'&billtype='+billtype+'&begindate='+begindate+'&enddate='+enddate; 
		//����xml
		if (!DealXMLHTTP(act)) return false;
		//�����������ĵ÷����ֵ����Сֵ
		var nodes = parser.documentElement.childNodes;
		var node = nodes.item(0).childNodes;  
		
		var returnflag = node.item(0).text;  
		var returnmessage = node.item(1).text; 	
		
		if(returnflag == "1"){//1'���ſ���ͨ�����о�����Ϣ
			if(confirm(returnmessage)){
				return true;
			}else{
				return false;
			}
		}else if(returnflag == "-1"){//-1'���Ų�ͨ��
			alert(returnmessage);
			return false;
		}else if(returnflag == "-99"){//-99'���ݿ�����ԭ������ĳ���
			alert(returnmessage);
			return false;
		}else if(returnflag == "0"){//'0'���ſ���ͨ��
			return true;
		}
		
		return true;
	} 