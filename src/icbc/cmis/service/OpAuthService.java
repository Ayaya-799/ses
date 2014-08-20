/*
 * �������� 2004-11-19
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package icbc.cmis.service;

import icbc.cmis.util.DBTools;
import icbc.cmis.operation.DummyOperation;
import icbc.cmis.base.TranFailException;
import icbc.cmis.base.KeyedDataCollection;
import com.icbc.cte.base.KeyedCollection;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author ZJFH-yanb
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class OpAuthService {
  private String opName = null;
  private String opId = null;
  private KeyedDataCollection kdc = null;
  private KeyedCollection kc = null;
  private boolean isCtpOp = false;
  private String errMsg = null;

  public OpAuthService(String opName, KeyedDataCollection kdc) {
    this.opName = opName;
    this.kdc = kdc;
    this.isCtpOp = false;
  }

  public OpAuthService(String opName, KeyedCollection kc) {
    this.opName = opName;
    this.kc = kc;
    this.isCtpOp = true;
  }

  public boolean isOpAuthed() throws Exception {
    if (!isOpNameAuthed())
      return false;
    if (!isOpActionAuthed())
      return false;
    return true;
  }

  private boolean isOpNameAuthed() throws Exception {
    opId = getOpId();
    if (opId != null)
      return true;
    else {
      errMsg = "OP����[" + this.opName + "]û�еǼǣ�";
      return false;
    }
  }

  private String getOpId() throws TranFailException {
    String queryStr = "SELECT OPID FROM MAG_OP_NAME WHERE OPNAME=?";
    try {
      DBTools srv = new DBTools(new DummyOperation(), "missign");
      Vector inParam = new Vector(1);
      inParam.add(this.opName);

      String opId = srv.executeSQL(queryStr, inParam);
      if (opId == null || opId.equals(""))
        return null;
      else
        return opId;
    }
    catch (Exception e) {
      throw new TranFailException("icbc.cmis.service.OpAuthService", "OpAuthService.getOpId", "ȡoperation idʧ��", "ȡoperation idʧ��");
    }
  }

  private boolean isOpActionAuthed() throws TranFailException {
    Vector actionAndValue = getOpActionAndValue();
    String curActionValue = "";
    String actionName = "";
    if (actionAndValue.size() == 0){
	  errMsg = "OP��Ϊ["+this.opName +"]��OP Actionû�еǼ�";
      return false;
    }
    else {
      if (!this.isCtpOp) {
        //actionName = (String) ((Hashtable)actionAndValue.get(0)).get("OPACTNAME");
//        if (actionName.equalsIgnoreCase("NULL"))
//          return true;
		
		//KeyedDataCollection context = (KeyedDataCollection)kdc.getAttribute("operationData");
        for (int i = 0; i < actionAndValue.size(); i++) {
          actionName = (String) ((Hashtable)actionAndValue.get(i)).get("OPACTNAME");
		  String valueReg = (String) ((Hashtable)actionAndValue.get(i)).get("OPACTVAL");
		  if(valueReg.equals("NULL"))
		    return true;
          String actionValue = null;
          try{
			actionValue = (String)kdc.getValueAt(actionName);
          }catch(Exception e){}
          
          if (actionValue != null) {
			curActionValue = actionValue;
			//String valueReg = (String) ((Hashtable)actionAndValue.get(i)).get("OPACTVAL");
            if (valueReg.equals(actionValue))
              return true;
          }
        }
        errMsg = "OP��Ϊ["+this.opName +"]��[" + actionName + "]��context�е�ֵΪ[" + curActionValue+ "]���͵Ǽ�ֵ�޷�ƥ�䣡";
        return false;
      }
      else{
		errMsg = "Ŀǰ�ݲ�֧��CTP����µ�OP��֤��";
        return false;
      }
    }
  }

  private Vector getOpActionAndValue() throws TranFailException {
    String queryStr = "SELECT OPACTNAME,OPACTVAL FROM MAG_OP_ACTION WHERE OPID=? AND OPACTNAME IS NOT NULL";
    try {
      if (this.opId == null)
        throw new Exception();
      DBTools srv = new DBTools(new DummyOperation(), "missign");
      Vector inParam = new Vector(1);
      inParam.add(this.opId);

      Vector actionAndValue = srv.executeSQLResult(queryStr, inParam);
      return actionAndValue;
    }
    catch (Exception e) {
      throw new TranFailException("icbc.cmis.service.OpAuthService", "OpAuthService.getOpActionAndValue", "ȡoperation actionֵʧ��", "ȡoperation actionֵʧ��");
    }
  }

  public String getErrorMsg() {
    return this.errMsg;
  }

}
