package icbc.cmis.util;
import icbc.cmis.operation.*;
import icbc.cmis.base.*;
import icbc.cmis.util.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import icbc.cmis.second.pkg.*;

public class ChooseEnp8 extends CmisOperation {
  public ChooseEnp8() {
	super();
  }
  public void execute() throws Exception {
	try {
	  String opAction = this.getStringAt("opAction");
	  if (opAction.equals("20001")) { //20001Ϊ��ѯ
		query();
	  }
	}catch (Exception e) {
	  this.setOperationDataToSession();
	  throw e;
	}
  }
		 public void query() throws TranFailException {
					  try {
						  String customercode = this.getStringAt("customercode"); //�ͻ�����
						  String AreaCode = (String)this.getSessionData("AreaCode"); // ��������
						  String EmployeeCode = (String)this.getSessionData("EmployeeCode"); //��Ա����
						  ChooseEnp8DAO dao = new ChooseEnp8DAO(this);
						  Hashtable ht_detail = new Hashtable();
						 
						  String v_return = dao.query(customercode,EmployeeCode,AreaCode);
						  
						  String xmlPack =
							  "DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?>";

//						  String glkh ="";
//					  if (v_return.size() == 0) { 
//							 glkh = "0";  //�Ƿ��в�ѯȨ��
//						  } else {
//							 glkh="1";
//					   }
//						  xmlPack += "<Content result='" + v_return + "'  ></Content>";
						  xmlPack += "<Content result='" +icbc.cmis.util.Func_XMLfiltrate.validXml(v_return)+ "'  ></Content>";
						  setReplyPage(xmlPack);
//						  setReplyPage(icbc.cmis.util.Func_XMLfiltrate.validXml(xmlPack));
						  setOperationDataToSession();
					  } catch (TranFailException ex) {
						  throw ex;
					  } catch (Exception e) {
//						  throw new TranFailException("ChooseEnp8",
//						  //������룬ʹ���߿�
//						  "ChooseEnp8.query()", //����λ��,�����߿�
//						  "��ѯʧ�ܣ�" + e.toString() //�������ݣ������߿�
//						  );
							throw new MuiTranFailException("099995", "ChooseEnp8.query()",(String)this.getSessionData("LangCode"));
					  }
				  }
  }
 