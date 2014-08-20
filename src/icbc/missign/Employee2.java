package icbc.missign;

/**
 * ���ڴ�Ź�Ա��Ϣ����missign�û���mag_employee��һ�¡�
 * �ṩisValid����ȷ�ϵ�ǰ�û��Ƿ���Ч�������Ƿ���ȷ��
 * @author Ҷ��ͦ
 * @version 1.0
 */
import icbc.cmis.util.Encode;

public class Employee2 implements java.io.Serializable {
  private String employeeCode = "";
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
  private String employeeMajor = "";
  private String employeeMajorName = "";
  private String employeeClass = "";
  private String employeeClassName = "";
  private java.util.Vector majors;
  private String canScan;

  public Employee2() {
  }
  public String getEmployeeCode() {
    return employeeCode;
  }
  public void setEmployeeCode(String employeeCode) {
    this.employeeCode = employeeCode;
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
  public void setEmployeeClassName(String employeeClassName) {
    this.employeeClassName = employeeClassName;
  }
  public String getEmployeeClassName() {
    return employeeClassName;
  }
  public void setEmployeeMajor(String employeeMajor) {
    this.employeeMajor = employeeMajor;
  }
  public String getEmployeeMajor() {
    return employeeMajor;
  }
  public void setEmployeeMajorName(String employeeMajorName) {
    this.employeeMajorName = employeeMajorName;
  }
  public String getEmployeeMajorName() {
    return employeeMajorName;
  }
  public void setEmployeeClass(String employeeClass) {
    this.employeeClass = employeeClass;
  }
  public String getEmployeeClass() {
    return employeeClass;
  }
  public void setMajors(java.util.Vector majors) {
    this.majors = majors;
  }
  public java.util.Vector getMajors() {
    return majors;
  }
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
      invaildInfo = "�˺Ų�û����ϵͳ�еǼǣ�";
      return false;
    }
    if(employeeName == null) {
      invaildInfo = "�˺Ų�û����ϵͳ�еǼǣ�";
      return false;
    }
    if(employeeEnableFlag == null || employeeEnableFlag.equals("0")) {
      invaildInfo = "���˺�û�м��";
      return false;
    }
    if(employeeDeleteFlag.equals("1")) {
      invaildInfo = "���˺������ϣ�";
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

}