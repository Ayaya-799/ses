package icbc.cmis.operation;

/**
 * �˴���������˵����
 * �������ڣ�(2002-1-2 13:41:00)
 * @author��Administrator
 */
public class ExampleLogoutOp extends CmisOperation {
/**
 * LogoutOp ������ע�⡣
 */
public ExampleLogoutOp() {
	super();
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2002-1-2 13:41:24)
 */
public void execute() throws icbc.cmis.base.TranFailException{
	try {
		try{
			this.setFieldValue("EmployeeCode",getSessionData("EmployeeCode"));
			this.setFieldValue("EmployeeName",getSessionData("EmployeeName"));
			this.setFieldValue("AreaCode",getSessionData("AreaCode"));
		}catch(Exception es){}
		clearSession();
		setReplyPage("/icbc/cmis/examplelogout.jsp");
	} catch (Exception e) {
		setReplyPage("/icbc/cmis/examplelogout.jsp");
	}
}
}
