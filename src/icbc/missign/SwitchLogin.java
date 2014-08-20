package icbc.missign;

import java.net.*;
import icbc.cmis.operation.*;
import icbc.cmis.base.CmisConstance;
/**
 * �����û���¼�л�����
 * @author Yanbo
 * @version 1.0
 */

public class SwitchLogin extends CmisOperation {

	public SwitchLogin() {
	}
	/**
	 * �����û���¼����
	 * �û���login.jsp�������Ա�š����
	 * ��ѯָ����Ա�ŵ��û���Ϣ���жϸ��û��Ƿ���Ч��
	 * ��Ч����ת��ָ����Ӧ�ó�����ҳ�棻
	 * ��Ч����ת����¼ʧ��ҳ�档
	 *
	 * login.jsp�����ṩ������Ϣ��
	 * <li>��Ա��accountCode
	 * <li>��Ա����passwd
	 * @throws Exception
	 */
	public void execute() throws java.lang.Exception {
		try {
			boolean authorized = false;
			String referringHost = (String)this.getSessionData("ReferringHost");
			String localHost = java.net.InetAddress.getLocalHost().getHostAddress();
			String baseWebPath = (String) CmisConstance.getParameterSettings().get("webBasePath");

			String accountCode = this.getStringAt("accountCode");
			String isSwitch = this.getStringAt("isSwitch");
            String selSysCode = this.getStringAt("selSysCode");
			String outAccountCode = this.getStringAt("outAccountCode");
            
            //ȡ�õ���Ȩ�����õ�IP��ַ
            java.util.Enumeration e = CmisConstance.getAppEntryURLs();
    		
		    for(;e.hasMoreElements();){
			  String authorizedURL = (String)e.nextElement();
		
			//String fromAppEntry = this.getStringAt("FromAppCode");
			//String authorizedURL = (String) CmisConstance.getParameterSettings().get(fromAppEntry);
			  String authorizedHost = new URL(authorizedURL).getHost();
			  //�Ƚ��Ƿ�õ���Ȩ
              if(authorizedHost.equals(referringHost) || referringHost.equals(localHost)){
			  	 authorized = true;
			  	 break;
			  }
		    }
			if(authorized){
			  LoginDAO dao = new LoginDAO(this);
			  Employee employee = dao.getEmployee(accountCode);
			  employee.setBranch(dao.getBranchDetail(outAccountCode));
			  updateSessionData("Employee",employee);
			  updateSessionData("AreaCode", employee.getMdbSID());
			  updateSessionData("AreaName", employee.getAreaName());
			  updateSessionData("BankFlag", employee.getBankFlag());
			  updateSessionData("EmployeeCode", employee.getEmployeeCode());
			  updateSessionData("EmployeeName", employee.getEmployeeName());
			  updateSessionData("ZhujiFlag", employee.getZhujiFlag());
			  updateSessionData("WorldFlag", employee.getWorldFlag());
              updateSessionData("isSwitch", isSwitch);
              updateSessionData("selSysCode", selSysCode);
			  updateSessionData("Login", "YES");
			  updateSessionData("LangCode", employee.getLangCode());
			  updateSessionData("ZoneCode", employee.getZoneCode());

			
			  setReplyPage(baseWebPath+"/logined.jsp");
			
			}
			else{
			  setReplyPage(baseWebPath+"/login.jsp");
			}
		}
		catch (Exception ex) {
			throw ex;
		}
	}
}