package icbc.missign;

/**
 * ���ڴ�Ź�Ա��Ϣ����missign�û���mag_employee��һ�¡�
 * �ṩisValid����ȷ�ϵ�ǰ�û��Ƿ���Ч�������Ƿ���ȷ��
 * @author Ҷ��ͦ
 * @version 1.0
 */
import icbc.cmis.base.*;
import icbc.cmis.util.Encode;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Employee implements java.io.Serializable {
  private String employeeCode = "";
  private String outsideEmpCode = "";
  private String employeeName = "";
  private String mdbSID = "";
  private String employeePasswd = "";
  private String employeeUseful = "";
  private String mdbFlag = "";
  private String mdbTime = "";
  private String employeeDeleteFlag = "";
  private String passwdTime = "";
  private String employeeEnableFlag = "";
  private String employeeNotesID = "";
  private String employeeEmail = "";
  private String invaildInfo = "";
  private String areaName = "";
  private String bankFlag = "";
  private String zhujiFlag = "";
  private String worldFlag = "";
  
  private String langCode = "";  //add by yanbo @20070412
  private String zoneCode = "";  //add by yanbo @20070417
  
  private String propertyFile = "icbc.missign.icbc_missign";
  
  private Vector majors;
  private Vector systems;
  private Vector branch = null;
  private String canScan;
  private Collection roles;
  private Role currentRole;
  //���һ��˽�б�������������й���Ա����5���ĸ���ҵ�����
  private HashSet managerMajors;
  

  public Employee() {
  }
  public String getWorldFlag() {
	return worldFlag;
  }
  public void setWorldFlag(String worldFlag) {
	this.worldFlag = worldFlag;
  }
  public String getZhujiFlag() {
	return zhujiFlag;
  }
  public void setZhujiFlag(String zhujiFlag) {
	this.zhujiFlag = zhujiFlag;
  }
  public String getEmployeeCode() {
    return employeeCode;
  }
  public void setEmployeeCode(String employeeCode) {
    this.employeeCode = employeeCode;
  }
  public String getOutsideEmpCode() {
    return outsideEmpCode;
  }
  public void setOutsideEmpCode(String outsideEmpCode) {
    this.outsideEmpCode = outsideEmpCode;
  }
  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }
  public String getEmployeeName() {
    return employeeName;
  }
  public void setMdbSID(String mdbSID) {
    this.mdbSID = mdbSID;
  }
  public String getMdbSID() {
    return mdbSID;
  }
  public void setEmployeePasswd(String employeePasswd) {
    this.employeePasswd = employeePasswd;
  }
  public String getEmployeePasswd() {
    return employeePasswd;
  }
  public void setEmployeeUseful(String employeeUseful) {
    this.employeeUseful = employeeUseful;
  }
  public String getEmployeeUseful() {
    return employeeUseful;
  }
  public void setMdbFlag(String mdbFlag) {
    this.mdbFlag = mdbFlag;
  }
  public String getMdbFlag() {
    return mdbFlag;
  }
  public void setMdbTime(String mdbTime) {
    this.mdbTime = mdbTime;
  }
  public String getMdbTime() {
    return mdbTime;
  }
  public void setEmployeeDeleteFlag(String employeeDeleteFlag) {
    this.employeeDeleteFlag = employeeDeleteFlag;
  }
  public String getEmployeeDeleteFlag() {
    return employeeDeleteFlag;
  }
  public void setPasswdTime(String passwdTime) {
    this.passwdTime = passwdTime;
  }
  public String getPasswdTime() {
    return passwdTime;
  }
  public void setEmployeeEnableFlag(String employeeEnableFlag) {
    this.employeeEnableFlag = employeeEnableFlag;
  }
  public String getEmployeeEnableFlag() {
    return employeeEnableFlag;
  }
  public void setEmployeeNotesID(String employeeNotesID) {
    this.employeeNotesID = employeeNotesID;
  }
  public String getEmployeeNotesID() {
    return employeeNotesID;
  }
  public void setEmployeeEmail(String employeeEmail) {
    this.employeeEmail = employeeEmail;
  }
  public String getEmployeeEmail() {
    return employeeEmail;
  }
  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }
  public String getAreaName() {
    return areaName;
  }
  public void setBankFlag(String bankFlag) {
    this.bankFlag = bankFlag;
  }
  public String getBankFlag() {
    return bankFlag;
  }
  public String getEmployeeClassName() {
    return currentRole.getRoleName();
  }
  public String getEmployeeMajor() {
    return currentRole.getMajorCode();
  }
  public String getEmployeeMajorName() {
    return currentRole.getMajorName();
  }
  public String getEmployeeSys() {
    return currentRole.getSysCode();
  }
  public String getEmployeeSysName() {
    return currentRole.getSysName();
  }

  public String getEmployeeClass() {
    return String.valueOf(currentRole.getRoleCode());
  }
  public void setMajors(Vector majors) {
    this.majors = majors;
  }
  public Vector getMajors() {
    return majors;
  }
  
  
  public void setManagerMajors(HashSet managerMajors) {
    this.managerMajors = managerMajors;
  }
  public HashSet getManagerMajors() {
    return managerMajors;
  }
  
  //add by yanbo @20070412  begin
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public String getLangCode() {
		return langCode;
	}
	
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public String getZoneCode() {
		return zoneCode;
	}
  //add by yanbo @20070412  end
  /**
   * �жϵ�ǰ�û��Ƿ���Ч���ж�������
   * <li>employeeCodeΪ�գ���Ч
   * <li>employeeNameΪ�գ���Ч
   * <li>employeePasswd�봫��Ŀ��һ�£������
   * <li>employeeEnableFlagΪ�ջ����0�����˺�û�м���
   * <li>employeeDeleteFlag����1�����˺�������
   * ���û���Ч����ȡ
   * @param passwd �û�����
   * @return ������û���Ч�ҿ�����ȷ���򷵻�true�����򣬷���false
   */
  public boolean isValid(String passwd) {
    if(employeeCode == null) {
      invaildInfo = "��Ա�Ų�û����ϵͳ�еǼǣ�";
      return false;
    }
    if(employeeName == null) {
      invaildInfo = "��Ա�Ų�û����ϵͳ�еǼǣ�";
      return false;
    }
    if(employeeEnableFlag == null || employeeEnableFlag.equals("0")) {
      invaildInfo = "�ù�Ա��û�м��";
      return false;
    }
    
    if(!Encode.checkPassword(employeeCode,passwd,employeePasswd)) {
      invaildInfo = "��������뷵����ҳ���ԡ�";
      return false;
    }
    return true;
  }
  /**
   * ȡ�û���Ч��ԭ��
   * @return ��Чԭ��
   */
  public String getInvaildInfo() {
    return invaildInfo;
  }
  public void setCanScan(String canScan) {
    this.canScan = canScan;
  }
  public String getCanScan() {
    return canScan;
  }
  public Collection getRoles() {
    return roles;
  }
  public void setRoles(Collection roles) {
    this.roles = roles;
  }

  public void setSystems(Vector systems) {
    this.systems = systems;
  }


  public Role getCurrentRole() {
    return currentRole;
  }

  /**
   * �����û���ǰ��ɫ
   * @param majorCode רҵ���
   * @param roleCode ��������
   */
  public void setCurrentRole(String majorCode,int roleCode) {
    //��ָ����רҵ��������Ч������ǰרҵ������
    for (Iterator i = roles.iterator(); i.hasNext(); ) {
      Role item = (Role)i.next();
      if(item.getMajorCode().equals(majorCode) && (roleCode == 0 || item.getRoleCode() == roleCode)) {
        this.currentRole = item;
        return;
      }
    }
  }

  /**
   * �����û���ǰ��ɫ(Ӧ�øı�ʱ��
   * @param majorCode רҵ���
   * @param roleCode ��������
   */
  public void setCurrentRoleSys(String sysCode) {
    //��ָ����רҵ��������Ч������ǰרҵ������
    for (Iterator i = roles.iterator(); i.hasNext(); ) {
      Role item = (Role)i.next();
      if(item.getSysCode().equals(sysCode)) {
        this.currentRole = item;
        return;
      }
    }
  }
  
  //add by yanbo 20040429 for ���½�л�Ӧ��
  /**
   * �����л�Ӧ�õĽű�
   * @return String
   */
  public String getSysEntryScript(boolean isSwitch) {
    StringBuffer script = new StringBuffer();
    //script.append(" ");
    boolean isEnabled = CmisConstance.getSwitchAppEnabled();
    script.append(" <script language='JavaScript'>");
    //script.append(" var sysCodeArray = new Array("+systems.size()+");");
    //script.append(" var sysURLArray = new Array("+systems.size()+");");
      
    for (int i = 0; i <systems.size(); i++) {
        
      String[] sysetm = (String[])systems.get(i);
      String url = CmisConstance.getAppEntry(sysetm[0]);
      if(!isSwitch){
        if(isEnabled && sysetm[0].equals(currentRole.getSysCode()) && url!=null){
          script.append("switchForm.action = '" + url +"&netType=0&accountCode=" + this.getEmployeeCode()+"&outAccountCode=" + this.getOutsideEmpCode()+"&isSwitch=false&selSysCode="+sysetm[0] +"'; ");
          script.append("switchForm.submit(); ");
          //script.append("parent.location.href='"+ url + "&accountCode=" + this.getEmployeeCode()+"';" );
          break;
        }
      }
      //script.append("sysCodeArray["+i+"]='"+sysetm[0]+"'; ");
      //if(isEnabled && url != null)
      //  script.append("sysURLArray["+i+"]='"+url+"'; ");
      //else
      //  script.append("sysURLArray["+i+"]='noSwitch'; ");
      
    }
    script.append(" </script> ");
    
    return script.toString();
  }
//end by yanbo 20040429
  
  /**
   * �ӹ�Ա��Ϣ����ȡ�ù�Ա���Բ���������רҵ���������б���ʾ
   * @return html
   */
  public String getMajorsHtml() {
    //isSwitch = false;
    //�ӹ�Ա��Ϣ����ȡ�ù�Ա���Բ���������רҵ���������б���ʾ
//      TableBean dict_sys = new TableBean();
      TableBean mag_sys_major = new TableBean();
      Hashtable hTable=(Hashtable)CmisConstance.getDictParam();
      mag_sys_major = (TableBean)hTable.get("mag_sys_major");
//      mag_sys_major = (TableBean)hTable.get("mag_sys_major");
    StringBuffer options = new StringBuffer();
    options.append("<b><font color=\"#FFFFFF\">&nbsp;"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, langCode, "S003")+"&nbsp;</font><select class='bselect' name=\"smajor\" style=\"width:110px\" onChange='changeMajor();'>");
    //if(!isSwitch){
      String sys_code = currentRole.getSysCode();
      for (int i = 0; i < majors.size(); i++) {
        String[] vmajor = (String[])majors.get(i);
        if(sys_code.equals(mag_sys_major.getNameByCode("sys_code","major_code",vmajor[0]))){
          if(vmajor[0].equals(currentRole.getMajorCode())) {
            options.append("<option value=\""+vmajor[0]+"\" selected>" + vmajor[1] + "</option>");
          } else {
            options.append("<option value=\""+vmajor[0]+"\">" + vmajor[1] + "</option>");
          }
        }
      }
//    }
//    else{
//      //String[] system = (String[])systems.get(0);
//      String sys_code = selectedSysCode;
//      boolean firstTime = true;
//      for (int i = 0; i < majors.size(); i++) {
//        String[] vmajor = (String[])majors.get(i);
//        if(sys_code.equals(mag_sys_major.getNameByCode("sys_code","major_code",vmajor[0]))){
//          if(firstTime) {
//            options.append("<option value=\""+vmajor[0]+"\" selected>" + vmajor[1] + "</option>");
//            firstTime = false;
//          } else {
//            options.append("<option value=\""+vmajor[0]+"\">" + vmajor[1] + "</option>");
//          }
//        }
//      }
//    }



//    for (Iterator i = roles.iterator(); i.hasNext(); ) {
//      Role item = (Role)i.next();
//        if(sys_code.equals(item.getSysCode())){
//          if(currentRole.getMajorCode().equals(item.getMajorCode())) {
//            options.append("<option value=\""+item.getMajorCode()+"\" selected>" + item.getMajorName() + "</option>");
//          } else {
//            options.append("<option value=\""+item.getMajorCode()+"\">" + item.getMajorName() + "</option>");
//          }
//        }
//    }





//        if(vmajor[0].equals(currentRole.getMajorCode())) {
//          options.append("<option value=\""+vmajor[0]+"\" selected>" + vmajor[1] + "</option>");
//        } else {
//          options.append("<option value=\""+vmajor[0]+"\">" + vmajor[1] + "</option>");
//        }



    options.append("</select></b><br>");
    return options.toString();
  }

//update by yanbo 20040429 for ���½�л�Ӧ��
/**
   * �ӹ�Ա��Ϣ����ȡ�ù�Ա���Բ���������רҵ���������б���ʾ
   * @return html
   */
  public String getSystemsHtml() {
    //�ӹ�Ա��Ϣ����ȡ�ù�Ա���Բ���������רҵ���������б���ʾ
    //isSwitch = false;
    StringBuffer options = new StringBuffer();
    options.append("<b><font color=\"#FFFFFF\">&nbsp;"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, langCode, "S002")+"&nbsp;</font><select class='bselect' name=\"ssys\" style=\"width:110px\" onChange=\"changeSys('"+this.getOutsideEmpCode()+"','"+this.getEmployeeCode()+"');\" onMouseWheel=\"event.returnValue = false;\">");
    //if(!isSwitch){
      for (int i = 0; i <systems.size(); i++) {
        String[] sysetm = (String[])systems.get(i);
        if(sysetm[0].equals(currentRole.getSysCode())) {
          options.append("<option value=\""+sysetm[0]+"\" selected>" + sysetm[1] + "</option>");
        } else {
          options.append("<option value=\""+sysetm[0]+"\">" + sysetm[1] + "</option>");
        }
      }
//    }
//    else{
//      for (int i = 0; i <systems.size(); i++) {
//        String[] sysetm = (String[])systems.get(i);
//        if(sysetm[0].equals(selectedSysCode)) {
//          options.append("<option value=\""+sysetm[0]+"\" selected>" + sysetm[1] + "</option>");
//        } else {
//          options.append("<option value=\""+sysetm[0]+"\">" + sysetm[1] + "</option>");
//        }
//      }
//    }
    options.append("</select></b><br>");
    return options.toString();
  }
//end by yanbo 20040429


  /**
   * ��ʾ�û���ǰרҵ�����в��������������б���ʾ
   * @return html
   */
  public String getMajorClassHtml() {
    //isSwitch = false;
    StringBuffer classes = new StringBuffer();
    boolean multiClasses = false;
    //if(!isSwitch){
      if(currentRole.getMajorCode() != null) {
        //��鵱ǰרҵ�Ƿ��ж����������
        int count = 0;
        for (Iterator i = roles.iterator(); i.hasNext(); ) {
          Role item = (Role)i.next();
          if(item.getMajorCode().equals(currentRole.getMajorCode())) {
            count++;
          }
        }
        //���ж��������������ʾ��������ѡ���б�
        if (count > 1) {
          classes.append("<b><font color=\"#FFFFFF\">&nbsp;"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, langCode, "S004")+"&nbsp;</font><select class='bselect' name=\"smajorClass\" style=\"width:110px\" onChange='changeMajorClass();'>");
          for (Iterator i = roles.iterator(); i.hasNext(); ) {
            Role item = (Role)i.next();
            if(item.getMajorCode().equals(currentRole.getMajorCode())) {
              if(item.getRoleCode() == currentRole.getRoleCode()) {
                classes.append("<option value=\"" + item.getRoleCode() + "\" selected>" + item.getRoleName() + "</option>");
              } else {
                classes.append("<option value=\"" + item.getRoleCode() + "\">" + item.getRoleName() + "</option>");
              }
            }
          }
          classes.append("</select></b>");
        }
      }
//    }
//    else{
//      TableBean mag_sys_major = new TableBean();
//      Hashtable hTable=(Hashtable)CmisConstance.getDictParam();
//      mag_sys_major = (TableBean)hTable.get("mag_sys_major");
//      //String[] system = (String[])systems.get(0);
//      String sys_code = selectedSysCode;
//      String major_code = null;
//      
//      for (int i = 0; i < majors.size(); i++) {
//        String[] vmajor = (String[])majors.get(i);
//        if(sys_code.equals(mag_sys_major.getNameByCode("sys_code","major_code",vmajor[0]))){
//          major_code = vmajor[0];
//          break;
//        }
//      }
//      
//      //��鵱ǰרҵ�Ƿ��ж����������
//        int count = 0;
//        boolean firstTime = true;
//        for (Iterator i = roles.iterator(); i.hasNext(); ) {
//          Role item = (Role)i.next();
//          if(item.getMajorCode().equals(major_code)) {
//            count++;
//          }
//        }
//        //���ж��������������ʾ��������ѡ���б�
//        if (count > 1) {
//          classes.append("<b><font color=\"#FFFFFF\">&nbsp;��λ&nbsp;</font><select class='bselect' name=\"smajorClass\" style=\"width:110px\" onChange='changeMajorClass();'>");
//          for (Iterator i = roles.iterator(); i.hasNext(); ) {
//            Role item = (Role)i.next();
//            if(item.getMajorCode().equals(major_code)) {
//              if(firstTime) {
//                classes.append("<option value=\"" + item.getRoleCode() + "\" selected>" + item.getRoleName() + "</option>");
//                firstTime = false;
//              } else {
//                classes.append("<option value=\"" + item.getRoleCode() + "\">" + item.getRoleName() + "</option>");
//              }
//            }
//          }
//          classes.append("</select></b>");
//        }
//    }
    return classes.toString();
  }
  
  
  //add by yanbo 20040430 for һ�˶����
  public void setBranch(Vector branch) {
    this.branch = branch;
  }
  public Vector getBranch() {
    return this.branch;
  }
  
  //�����ݿ�����Ϣ����ȡ���ⲿ��Ա���Բ����������ڲ���Ա���������б���ʾ
  public String getBranchHtml() {
    if(branch==null || branch.size()<=1)
      return "";
    //isSwitch = false;
    StringBuffer options = new StringBuffer();
//    options.append("<script language='javascript'>");
//    options.append("var employeeCodeBrn = new Array["+branch.size()+"]; ");
    
    options.append("<b><font color=\"#FFFFFF\">&nbsp;"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, langCode, "S001")+"&nbsp;</font><select class='bselect' name=\"sbrn\" style=\"width:110px\" onChange=\"changeBranch('"+this.getOutsideEmpCode()+"');\" onMouseWheel=\"event.returnValue = false;\">");
    //if(!isSwitch){
      for (int i = 0; i <branch.size(); i++) {
        String[] branchs = (String[])branch.get(i);
        if(branchs[0].equals(this.getEmployeeCode())) {
          options.append("<option value=\""+branchs[0]+"|"+branchs[3]+"|"+branchs[4]+"|"+branchs[5]+"\" selected>" + branchs[2]);
          //Ĭ�ϵĻ�����*
          if(branchs[0].equals(this.getOutsideEmpCode()))
            options.append("*");
          options.append("</option>");
        } else {
          options.append("<option value=\""+branchs[0]+"|"+branchs[3]+"|"+branchs[4]+"|"+branchs[5]+"\">" + branchs[2]);
          //Ĭ�ϵĻ�����*
          if(branchs[0].equals(this.getOutsideEmpCode()))
            options.append("*");
          options.append("</option>");
        }
      }
    //}
//    else{
//      for (int i = 0; i <branch.size(); i++) {
//        String[] sysetm = (String[])systems.get(i);
//        String[] branchs = (String[])branch.get(i);
//        if(branchs[1].equals(this.getEmployeeCode())) {
//          options.append("<option value=\""+branchs[1]+"\" selected>" + branchs[2] + "</option>");
//        } else {
//          options.append("<option value=\""+branchs[1]+"\">" + branchs[2] + "</option>");
//        }
//      }
//    }
    options.append("</select></b><br>");
    return options.toString();
  }
 //end yanbo 20040430
}
