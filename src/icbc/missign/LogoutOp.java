package icbc.missign;

import icbc.cmis.operation.CmisOperation;
/**
 * �˴���������˵����
 * �������ڣ�(2002-1-2 13:41:00)
 * @author��Administrator
 */
public class LogoutOp extends CmisOperation {
/**
 * LogoutOp ������ע�⡣
 */
public LogoutOp() {
	super();
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2002-1-2 13:41:24)
 */
public void execute() throws icbc.cmis.base.TranFailException{
	try {
    try{
        this.setFieldValue("logoutMark","true");
        this.setFieldValue("EmployeeCode",getSessionData("EmployeeCode"));
        this.setFieldValue("EmployeeName",getSessionData("EmployeeName"));
        this.setFieldValue("AreaCode",getSessionData("AreaCode"));
      }catch(Exception es){}
		clearSession();
		setReplyPage("/LogoutSuccess.jsp");
	} catch (Exception e) {
		System.out.println("ERR-LogoutOp.execut():\n"+e.toString());
		System.out.println("ERR-LogoutOp.execut()-printTrace:");
		e.printStackTrace();
		setReplyPage("/LogoutSuccess.jsp");
	}
}
}