package icbc.cmis.operation;

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
		clearSession();
		setReplyPage("/icbc/cmis/LogoutSuccess.jsp");
	} catch (Exception e) {
		System.out.println("ERR-LogoutOp.execut():\n"+e.toString());
		System.out.println("ERR-LogoutOp.execut()-printTrace:");
		e.printStackTrace();
		setReplyPage("/icbc/cmis/LogoutSuccess.jsp");
	}
}
}