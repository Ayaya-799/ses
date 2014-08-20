package icbc.cmis.util;

/**
 * �˴���������˵����
 * �������ڣ�(2002-6-24 19:45:34)
 * @author��Administrator
 * errorCode xdtzUTIL300-xdtzUTIL303
 */
import icbc.cmis.base.*;
import java.sql.*;
import icbc.cmis.operation.*;
import icbc.missign.*;
import java.util.*;
public class IDAndCryptoConfig extends CmisOperation
{
    private SqlTool tool= null;
/**
 * PasswordChangeOp ������ע�⡣
 */
public IDAndCryptoConfig()
{
    super();

}
/**
 * Insert the method's description here.
 * Creation date: (2002-1-3 19:45:34)
 */
public void execute() throws Exception
{
    try{
        
        String id= getStringAt("id_no");
        String idType= getStringAt("id_type");
        String account= (String) getSessionData("EmployeeCode");
        String crypto = (String) this.getSessionData("pwdCrypto");
        //String area_code= (String) getSessionData("AreaCode");
        IDAndCryptoConfigDAO dao = new IDAndCryptoConfigDAO(this);
        dao.updateIDAndCrypto(account,id,idType,crypto);

//        this.setFieldValue("okTitle","�����ɹ���");
//        this.setFieldValue("okMsg","����֤�����ͺ�֤������ɹ���");
//        this.setFieldValue("okReturn",(String)CmisConstance.getParameterSettings().get("webBasePath") + "/login.jsp");
        this.setReplyPage("DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><success />");
    }
    catch (TranFailException e)
        {
//        setErrorCode(e.getErrorCode());
//        setErrorDispMsg(e.getDisplayMessage());
//        setErrorLocation(e.getErrorLocation());
//        setErrorMessage(e.getErrorMsg());
        String msg = "DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><error>";
        msg += "����λ�ã� " + e.getErrorLocation() + "\n�������ݣ�" + e.getErrorMsg();
        msg += "</error>";
        setReplyPage(msg);
        setOperationDataToSession();
    }
    catch (Exception e)
        {
//        setErrorCode("xdtz22126");
//        setErrorDispMsg("����ƽ̨���󣺽��״���ʧ��");
//        setErrorLocation("IDAndTypeConfig.execute()");
//        setErrorMessage(e.getMessage());
        String msg = "DirectOutput<?xml version=\"1.0\" encoding=\"GB2312\"?><error>";
        msg += "IDAndTypeConfig.execute()" + "  " + e.getMessage();
        msg += "</error>";
        setReplyPage(msg);
        setOperationDataToSession();
    }
}

}
