package icbc.missign;

import icbc.cmis.operation.CmisOperation;
import icbc.cmis.base.*;
import java.util.Vector;
/**
 * �˴���������˵����
 * �������ڣ�(2002-1-2 13:41:00)
 * @author��Administrator
 */
public class SwitchLogout extends CmisOperation {
/**
 * LogoutOp ������ע�⡣
 */
public SwitchLogout() {
	super();
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2002-1-2 13:41:24)
 */
public void execute() throws icbc.cmis.base.TranFailException{
	String empCode = null;
	//String sysCode = this.getStringAt("sysCode");
	String forwardURL = null;
	String forwardApp = null;
    String app = null;
    String accountCode = null;
    String xmlPack = null;
    try{
      try{
        app=this.getStringAt("app");
      }
      catch(Exception e){
        app = null;
      }
      try{
        forwardApp=this.getStringAt("toApp");
      }
      catch(Exception e){
        forwardApp = null;
      }
      try{
        accountCode=this.getStringAt("accountCode");
      }
      catch(Exception e){
        accountCode = null;
      }
      
      //��Ӧ��
      if(app==null){
        if(CmisConstance.getSwitchAppEnabled()){
            forwardURL=CmisConstance.getAppEntry(forwardApp);
            if(forwardURL==null){
              xmlPack ="DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><url>noSwitch</url>";
            }
            else{
              try{        
                this.setFieldValue("logoutMark","true");
                empCode = (String)getSessionData("EmployeeCode");
                this.setFieldValue("EmployeeCode",getSessionData("EmployeeCode"));
                this.setFieldValue("EmployeeName",getSessionData("EmployeeName"));
                this.setFieldValue("AreaCode",getSessionData("AreaCode"));
              }catch(Exception es){}
              clearSession();
              xmlPack ="DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><url>"+forwardURL+"</url>";
            }
          }
          //��������Ӧ��
          else{
            xmlPack ="DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><url>noSwitch</url>";
          }  
      }
      //�л���
      else{
        //��ͬӦ��
        if(!forwardApp.equals(app)){
          //������Ӧ��
          if(CmisConstance.getSwitchAppEnabled()){
            forwardURL=CmisConstance.getAppEntry(forwardApp);
            if(forwardURL==null){
              LoginDAO dao = new LoginDAO(this);
              Employee oldEmp = (Employee)this.getSessionData("Employee");
              Vector branch = oldEmp.getBranch();
              Employee employee = dao.getEmployee(accountCode);
              employee.setBranch(branch);
              employee.setOutsideEmpCode(oldEmp.getOutsideEmpCode());
              updateSessionData("Employee",employee);
              updateSessionData("AreaCode", employee.getMdbSID());
              updateSessionData("AreaName", employee.getAreaName());
              updateSessionData("BankFlag", employee.getBankFlag());
              updateSessionData("EmployeeCode", employee.getEmployeeCode());
              updateSessionData("EmployeeName", employee.getEmployeeName());
              xmlPack ="DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><url>noSwitch</url>";
            }
            else{
              try{        
                this.setFieldValue("logoutMark","true");
                empCode = (String)getSessionData("EmployeeCode");
                this.setFieldValue("EmployeeCode",getSessionData("EmployeeCode"));
                this.setFieldValue("EmployeeName",getSessionData("EmployeeName"));
                this.setFieldValue("AreaCode",getSessionData("AreaCode"));
              }catch(Exception es){}
              clearSession();
              xmlPack ="DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><url>"+forwardURL+"</url>";
            }
          }
          //��������Ӧ��
          else{
            LoginDAO dao = new LoginDAO(this);
            Employee oldEmp = (Employee)this.getSessionData("Employee");
            Vector branch = oldEmp.getBranch();
            Employee employee = dao.getEmployee(accountCode);
            employee.setBranch(branch);
            employee.setOutsideEmpCode(oldEmp.getOutsideEmpCode());
            updateSessionData("Employee",employee);
            updateSessionData("AreaCode", employee.getMdbSID());
            updateSessionData("AreaName", employee.getAreaName());
            updateSessionData("BankFlag", employee.getBankFlag());
            updateSessionData("EmployeeCode", employee.getEmployeeCode());
            updateSessionData("EmployeeName", employee.getEmployeeName());
            xmlPack ="DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><url>noSwitch</url>";
          }  
        }
        //ͬӦ��
        else{
          LoginDAO dao = new LoginDAO(this);
          Employee oldEmp = (Employee)this.getSessionData("Employee");
          Vector branch = oldEmp.getBranch();
          Employee employee = dao.getEmployee(accountCode);
          employee.setBranch(branch);
          employee.setOutsideEmpCode(oldEmp.getOutsideEmpCode());
          updateSessionData("Employee",employee);
          updateSessionData("AreaCode", employee.getMdbSID());
          updateSessionData("AreaName", employee.getAreaName());
          updateSessionData("BankFlag", employee.getBankFlag());
          updateSessionData("EmployeeCode", employee.getEmployeeCode());
          updateSessionData("EmployeeName", employee.getEmployeeName());
          xmlPack ="DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><url>noSwitch</url>";
        }
      }
       
      
      setReplyPage(xmlPack);
 
	} catch (Exception e) {
		System.out.println("ERR-SwitchApp.execut():\n"+e.toString());
		System.out.println("ERR-SwitchApp.execut()-printTrace:");
		e.printStackTrace();
		setReplyPage("/LogoutSuccess.jsp");
	}
}
}