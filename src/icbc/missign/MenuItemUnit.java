package icbc.missign;
import java.util.*;
import icbc.cmis.base.CmisConstance;
import icbc.cmis.base.TranFailException;
/**
 * @author ZJFH-yanb
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
/**
 * @author ZJFH-yanb
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
/**
 * @author �ϲ�
 * �������� 2006-2-15
 * ����;�������˵�������
 */
public class MenuItemUnit implements Comparable, Cloneable {
  protected HashMap itemPicker = null; //�˵����ȡ��
  protected TreeSet children = new TreeSet(); //�ӽڵ㼯��
  protected String module = null; //���ڵ㹦�ܺ�
  protected int level; //���ڵ���
  protected String pmodule = null; //���ڵ㹦�ܺ�
  protected String funcName = null; //��������
  protected boolean subNode; //�Ƿ�ΪҶ�ڵ�
  protected String rolePrivilege = null; //��ɫ��λȨ��
  protected String majorCode = null; //רҵ��
  protected String appClass = null; //�м���Ȩ��
  protected int appOrder; //�������
  protected static String baseWebPath = (String)CmisConstance.getParameterSettings().get("webBasePath");
  protected String propertyFile = "icbc.missign.icbc_missign";

  /**
   * ���캯��
   * @param module  ģ����
   * @param funcName ģ��������
   * @param majorCode  רҵ��
   * @param rolePrivilege  ��ɫ��λȨ��
   * @param appClass  �м���Ȩ��
   * @param subNode  �Ƿ��ӽڵ�
   * @param appOrder  �˵�˳��
   */
  public MenuItemUnit(String module, String funcName, String majorCode, String rolePrivilege, String appClass, boolean subNode, int appOrder) {
    this.module = module;
    this.funcName = funcName;
    this.subNode = subNode;
    this.rolePrivilege = rolePrivilege;
    this.majorCode = majorCode;
    this.appClass = appClass;
    this.appOrder = appOrder;
  }

  /**
   * ���캯��
   * ��Ҫ���setXXXʹ��
   */
  public MenuItemUnit() {}

  /**
   * ����ģ����
   * @param module ģ����
   */
  public void setModule(String module) {
    this.module = module;
  }

  /**
   * ����ģ��������
   * @param funcName ģ��������
   */
  public void setFuncName(String funcName) {
    this.funcName = funcName;
  }

  /**
   * ���ò˵����ȡ��
   * @param itemPicker �˵����ȡ��
   */
  public void setItemPicker(HashMap itemPicker) {
    this.itemPicker = itemPicker;
  }

  /**
   * ��ò˵����ȡ��
   */
  public HashMap getItemPicker() {
    return itemPicker;
  }

  /**
   * ���ø��ڵ�
   * @param parent ���ڵ�
   */
  public void setParent(MenuItemUnit parent) {
    this.itemPicker = parent.itemPicker;
    this.level = parent.level + 1;
    this.pmodule = parent.module;
  }

  /**
   * �����Ƿ��ӽڵ�
   * @param subNode �Ƿ��ӽڵ�
   */
  public void setSubNode(boolean subNode) {
    this.subNode = subNode;
  }

  /**
   * ���ý�ɫ��λȨ��
   * @param rolePrivilege ��ɫ��λȨ��
   */
  public void setRolePrivilege(String rolePrivilege) {
    this.rolePrivilege = rolePrivilege;
  }

  /**
   * ����רҵ��
   * @param majorCode רҵ��
   */
  public void setMajorCode(String majorCode) {
    this.majorCode = majorCode;
  }

  /**
   * �����м���Ȩ��
   * @param appClass �м���Ȩ��
   */
  public void setAppClass(String appClass) {
    this.appClass = appClass;
  }

  /**
   * ���ù������
   * @param appOrder �������
   */
  public void setAppOrder(int appOrder) {
    this.appOrder = appOrder;
  }

  /**
   * �����ӽڵ㼯��
   * @param children �ӽڵ㼯��
   */
  public void setChildren(TreeSet children) {
    this.children = children;
  }

  /**
   * ��ȡ�ӽڵ㼯��
   * @return �ӽڵ㼯��
   */
  public TreeSet getChildren() {
    return this.children;
  }

  /**
   * ����һ���ӽڵ�
   * @param item �ӽڵ�
   */
  public void putMenuItem(MenuItemUnit item) {
    item.setParent(this);
    children.add(item);
    itemPicker.put(item.module, item);
  }

  /**
   * ��ȥָ���Ľڵ�
   * @param func �ڵ���
   */
  public void removeMenuItem(String func) {
    MenuItemUnit item = (MenuItemUnit)itemPicker.get(func);
    if (item == null)
      return;
    item.itemPicker.remove(func);
    MenuItemUnit parent = (MenuItemUnit)itemPicker.get(item.pmodule);
    if (parent == null)
      parent.children.remove(item);

  }

  public void updateRolePrivilegeAppClass(String func, Employee employee, boolean state) {
    char ori, tar;
    if (state) {
      ori = '0';
      tar = '1';
    }
    else {
      ori = '1';
      tar = '0';
    }
    MenuItemUnit item = this.getMenuItemUnit(func);
    if (item == null)
      return;
    if (item.appClass.charAt(Integer.parseInt(employee.getBankFlag())) == ori) {
      char[] c1 = item.appClass.toCharArray();
      c1[Integer.parseInt(employee.getBankFlag())] = tar;
      item.appClass = new String(c1);
    }
    if (item.rolePrivilege.charAt(Integer.parseInt(employee.getEmployeeClass()) - 1) == ori) {
      char[] c2 = item.rolePrivilege.toCharArray();
      c2[Integer.parseInt(employee.getEmployeeClass()) - 1] = tar;
      item.rolePrivilege = new String(c2);
    }

  }

  /**
   * ���ָ���Ľڵ����
   * @param module �ڵ�ģ����
   * @return �ڵ����
   */
  public MenuItemUnit getMenuItemUnit(String module) {
    return (MenuItemUnit)itemPicker.get(module);
  }

  /**
   * ��ÿհ���
   * @return �հ���
   */
  public String getSpacer() {
    return "<TABLE width=\"180\" cellspacing=\"1\" cellpadding=\"0\"><TR><td class=\"title1\" height=\"16\"></td></TR></TABLE>";
  }

  /**
   * ת���ɲ˵��ַ������
   * @param role ��ɫ
   * @return �˵��ַ���
   */
  public String toMenu(Role role) throws TranFailException {
    try {
      //�����ɫû�иò˵���Ȩ�ޣ��Ͳ�չʾ�ò˵�
      if (rolePrivilege.charAt(role.getRoleCode() - 1) == '0')
        return "";
      //����м���û�иò˵���Ȩ�ޣ��Ͳ�չʾ�ò˵�
      if (appClass.charAt(role.getBankFlag()) == '0')
        return "";
      StringBuffer menu = new StringBuffer();
      menu.append("<TR TITLE=\"");
      menu.append(funcName);
      menu.append("\" Class=\"Navigator");
      if (level > 0)
        menu.append("-Hidden");
      menu.append("\" ID=\"");
      menu.append(module);
      menu.append("\" AncestorID=\"");
      menu.append(pmodule);
      menu.append("\" Depth=\"");
      menu.append(level);
      if (!subNode) {
        menu.append("\" Expanded=\"no\" onclick=\"toggle('");
        menu.append(module);
        menu.append("')");
      }
      menu.append("\"><TD STYLE=\"cursor:hand\"  class=\"title\">");
      menu.append("<DIV noWrap=\"true\" STYLE=\"padding-left:");
      menu.append(level);
      menu.append("em;\"><img src=\"" + baseWebPath + "/images/");
      if (!subNode) {
        menu.append("bs.gif");
      }
      else {
        menu.append("dot.gif");
      }
      menu.append("\">");
      if (subNode) {
        menu.append("<A STYLE=\"cursor:hand\"");
        menu.append("href =\"javascript:goto('");
        menu.append(module + "')\"");
        menu.append(">");
        menu.append(funcName);
        menu.append("</A>");
      }
      else {
        menu.append(funcName);
      }
      menu.append("</DIV></TD></TR>");
      //����ӽڵ�Ĳ˵�
      for (Iterator iterator = children.iterator(); iterator.hasNext();) {
        MenuItemUnit child = (MenuItemUnit)iterator.next();
        menu.append(child.toMenu(role));
      }
      return menu.toString();
    }
    catch (TranFailException e) {
      throw e;
    }
    catch (Exception e) {
      throw new TranFailException("MenuItemUnit", "toMenu", this.module + "�ڵ�˵�����ʧ��" + e.getMessage(), this.module + "�ڵ�˵�����ʧ��" + e.getMessage());
    }
  }

  /**
   * ��дequals����
   */
  public boolean equals(Object o) {
    return o instanceof MenuItemUnit && this.module.equals(((MenuItemUnit)o).module);
  }

  /**
   * ��дhashCode����
   */
  public int hashCode() {
    return this.module.hashCode();
  }

  /**
   * ��дcompareTo����
   */
  public int compareTo(Object o) {
    MenuItemUnit item = (MenuItemUnit)o;
    int delta = this.appOrder - item.appOrder;
    //���appOrderһ���Ļ����ͱȽ�module�Ĵ�С
    if (delta != 0)
      return delta;
    else
      return this.module.compareTo(item.module);
  }

  /**
   * ��дclone����
   */
  public MenuItemUnit cloneTree(HashMap itp) throws CloneNotSupportedException {
    MenuItemUnit item = (MenuItemUnit)super.clone();
    item.children = new TreeSet();
    item.itemPicker = itp;
    item.itemPicker.put(item.module, item);

    for (Iterator iterator = children.iterator(); iterator.hasNext();) {
      MenuItemUnit thisChild = (MenuItemUnit)iterator.next();
      MenuItemUnit cloneChild = thisChild.cloneTree(item.itemPicker);
      item.children.add(cloneChild);
    }
    //item.children = (TreeSet)children.clone();
    //	item.appClass = new String(this.appClass);
    //	item.rolePrivilege = new String(this.rolePrivilege);
    return item;
  }

  /**
   * ��������
   * �������������ó�null��������������
   */
  public void destory() {
    //�����ӽڵ�
    for (Iterator iterator = children.iterator(); iterator.hasNext();) {
      MenuItemUnit child = (MenuItemUnit)iterator.next();
      child.destory();
    }
    itemPicker = null; //�˵����ȡ��
    children.clear(); //�ӽڵ㼯��
    children = null;
    module = null; //���ڵ㹦�ܺ�
    pmodule = null; //���ڵ㹦�ܺ�
    funcName = null; //��������
    rolePrivilege = null; //��ɫȨ��
    majorCode = null; //רҵ��
    appClass = null; //�м���Ȩ��
  }

}
