package icbc.missign;

import icbc.cmis.base.TranFailException;
import icbc.cmis.operation.*;
import icbc.cmis.base.CmisConstance;
import icbc.cmis.base.SSICTool;
import java.util.Vector;
/**
 * �����û���¼����
 * @author Ҷ��ͦ
 * @version 1.0
 */

public class Login extends CmisOperation {

	public Login() {
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
			String baseWebPath = (String) CmisConstance.getParameterSettings().get("webBasePath");
			String enabledConfig = (String) CmisConstance.getParameterSettings().get("enableIDAndCryptoConfig");
            //boolean enabledConfig = false;
            boolean configedCrypto = true;
            String pwdCrypto = null;
			//ͳһ��֤���
			if (SSICTool.isSSICEnabled()) {
		      //��ȡͳһ��֤��½ʱ�õĹ�Ա��
              String accountCode = SSICTool.getSSICEmployeeCode(this);
              Vector branch = null;
              LoginDAO dao = new LoginDAO(this);
              //�ж��ⲿ��Ա���Ƿ���ڣ���ҪĿ����Ϊ������ʹ�õĵ�½��Ա������������Ƿ�ͣ�á��Ƿ����ڲ���Ա
              String outSideEmpCode = dao.isEmployeeExisted(accountCode);
              if(outSideEmpCode.equalsIgnoreCase("true")){            
              }
              else if(outSideEmpCode.equalsIgnoreCase("false"))
                throw new TranFailException("execute","Login.execute()","�ù�Ա����Ϊ�ڲ���Ա����δ�����ⲿ��Ա�������ܵ�¼��","�ù�Ա����Ϊ�ڲ���Ա����δ�����ⲿ��Ա�����ܵ�¼��");
              else if(outSideEmpCode.equalsIgnoreCase("stop")){
                //������ⲿ��Ա���ڲ���Ա�Ĺ�Ա�ű�ͣ�ã���ȡ��������������½�Ĺ�Ա
                branch = dao.getBranchDetail(accountCode);
                if(branch.size()==0)
                  throw new TranFailException("execute","Login.execute()","�ù�Ա�Ѿ���ͣ�ã����ܵ�¼��","�ù�Ա�Ѿ���ͣ�ã����ܵ�¼��");
              }
              else
                throw new TranFailException("execute","Login.execute()","�ù�ԱΪ�ڲ���Ա�����øù�Ա��Ӧ���ⲿ��Ա"+outSideEmpCode+"��¼ϵͳ��","�ù�ԱΪ�ڲ���Ա�����øù�Ա��Ӧ���ⲿ��Ա"+outSideEmpCode+"��¼ϵͳ��");
              
              if(branch == null){
                branch = dao.getBranchDetail(accountCode);
              }
              
              //ȡ���������еĵ�һ����Ա��Ϊ��½�õ��ڲ���Ա
              Employee employee = null;
              if(branch.size()>0){
                 String[] branch1 = (String[])branch.get(0);                
                 employee = dao.getEmployee(branch1[0]);
              }
              else
			    throw new TranFailException("execute","Login.execute()","�ù�Աδ�������κ�ҵ�񣬲��ܵ�¼��","�ù�Աδ�������κ�ҵ�񣬲��ܵ�¼��");
              
              //���ù�Ա�����Ϣ
              employee.setBranch(branch);
              employee.setOutsideEmpCode(accountCode);
              
              updateSessionData("Employee", employee);
              updateSessionData("AreaCode", employee.getMdbSID());
              updateSessionData("AreaName", employee.getAreaName());
              updateSessionData("BankFlag", employee.getBankFlag());
              updateSessionData("EmployeeCode", employee.getEmployeeCode());
              updateSessionData("EmployeeName", employee.getEmployeeName());
			  updateSessionData("ZhujiFlag", employee.getZhujiFlag());
			  updateSessionData("WorldFlag", employee.getWorldFlag());
			  updateSessionData("LangCode", employee.getLangCode());
			  updateSessionData("ZoneCode", employee.getZoneCode());
                //update by yanbo 20040429 for һ�˶����
                //��ȡ�ù�Ա���еĻ���
//                Vector branch = dao.getBranchDetail(accountCode);
//                employee.setBranch(branch);
                
                //updateSessionData("insideEmpDefault",branch);
              updateSessionData("Login", "YES");

              setReplyPage(baseWebPath+"/logined.jsp");
             
              
              return;
            }
            
            //��ͳһ��֤���
		    String accountCode = this.getStringAt("accountCode");
			
            String passwd = this.getStringAt("passwd");
            Vector branch = null;
			LoginDAO dao = new LoginDAO(this);
//		�ж��ⲿ��Ա���Ƿ���ڣ���ҪĿ����Ϊ������ʹ�õĵ�½��Ա������������Ƿ�ͣ�á��Ƿ����ڲ���Ա
            String outSideEmpCode = dao.isEmployeeExisted(accountCode);
			if(outSideEmpCode.equalsIgnoreCase("false"))
              throw new TranFailException("execute","Login.execute()","�ù�Ա����Ϊ�ڲ���Ա����δ�����ⲿ��Ա�������ܵ�¼��","�ù�Ա����Ϊ�ڲ���Ա����δ�����ⲿ��Ա�����ܵ�¼��");
            else if(outSideEmpCode.equalsIgnoreCase("stop")){
//		������ⲿ��Ա���ڲ���Ա�Ĺ�Ա�ű�ͣ�ã���ȡ��������������½�Ĺ�Ա
                branch = dao.getBranchDetail(accountCode);
                if(branch.size()==0)
                  throw new TranFailException("execute","Login.execute()","�ù�Ա�Ѿ���ͣ�ã����ܵ�¼��","�ù�Ա�Ѿ���ͣ�ã����ܵ�¼��");  
            }
            else if(!outSideEmpCode.equalsIgnoreCase("true"))
              throw new TranFailException("execute","Login.execute()","�ù�ԱΪ�ڲ���Ա�����øù�Ա��Ӧ���ⲿ��Ա"+outSideEmpCode+"��¼ϵͳ��","�ù�ԱΪ�ڲ���Ա�����øù�Ա��Ӧ���ⲿ��Ա"+outSideEmpCode+"��¼ϵͳ��");
            
            Employee employee = null;//dao.getEmployee(accountCode);
			
            //����ͳһ��֤����(Ŀ����Ϊ����ͳһ��֤����ʱ��ʹ��ͳһ��֤������У��)
//            if(enabledConfig.equals("true")){
//              pwdCrypto = SSICTool.genSSICpass(passwd);
//              configedCrypto = dao.configPwdCrypto(accountCode, pwdCrypto);
//              
//            }
//		ȡ���������еĵ�һ����Ա��Ϊ��½�õ��ڲ���Ա
            if(branch == null){
              branch = dao.getBranchDetail(accountCode);
            }
            if(branch.size()>0){
              String[] branch1 = (String[])branch.get(0);
              //if(!branch1[0].equals(accountCode)){
                employee = dao.getEmployee(branch1[0]);
				if(!employee.isValid(passwd)) {
					throw new TranFailException("cmisLogin001", "icbc.missign.Login", employee.getInvaildInfo(), employee.getInvaildInfo());			
				}
				employee.setBranch(branch);
            
				employee.setOutsideEmpCode(accountCode);
              //}
				//����ͳһ��֤����(Ŀ����Ϊ����ͳһ��֤����ʱ��ʹ��ͳһ��֤������У��)
				if(enabledConfig.equals("true")){
					pwdCrypto = SSICTool.genSSICpass(passwd);
					configedCrypto = dao.configPwdCrypto(employee.getEmployeeCode(), pwdCrypto);
              
				}
            }
			else
			  throw new TranFailException("execute","Login.execute()","�ù�Ա�Ѿ���ͣ�ã����ܵ�¼��","�ù�Ա�Ѿ���ͣ�ã����ܵ�¼��");
            
            
              
            //updateSessionData("insideEmpDefault",branch);
            
            //���ù�Ա��Ϣ
            updateSessionData("Employee",employee);
			updateSessionData("AreaCode", employee.getMdbSID());
			updateSessionData("AreaName", employee.getAreaName());
			updateSessionData("BankFlag", employee.getBankFlag());
			updateSessionData("EmployeeCode", employee.getEmployeeCode());
			updateSessionData("EmployeeName", employee.getEmployeeName());
			updateSessionData("ZhujiFlag", employee.getZhujiFlag());
			updateSessionData("WorldFlag", employee.getWorldFlag());
			updateSessionData("LangCode", employee.getLangCode());
			updateSessionData("ZoneCode", employee.getZoneCode());
//            Vector branch = dao.getBranchDetail(accountCode);
//            employee.setBranch(branch);
//            employee.setOutsideEmpCode(employee.getOutsideEmpCode());
            //updateSessionData("insideEmpDefault",branch);
			updateSessionData("Login", "YES");

			if(!configedCrypto){
              this.addSessionData("pwdCrypto",pwdCrypto);
              setReplyPage(baseWebPath+"/util/util_IDAndCryptoConfig.jsp");
            }
            else {
				setReplyPage(baseWebPath+"/logined.jsp");
			}
		}
		catch (Exception ex) {
			throw ex;
		}
	}
}