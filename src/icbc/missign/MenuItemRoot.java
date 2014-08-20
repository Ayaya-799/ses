package icbc.missign;
import java.util.*;
import icbc.cmis.base.CmisConstance;
import icbc.cmis.base.TranFailException;
import icbc.cmis.base.SSICTool;

/**
 * @author �ϲ�
 * �������� 2006-2-15
 * ����;�������˵�������(���ڵ�)
 */
public class MenuItemRoot extends MenuItemUnit {
  /**
   * ���캯��
   *
   */
  public MenuItemRoot() {
    super();
    super.module = "00000";
    super.itemPicker = new HashMap();
    super.level = -1;
    super.subNode = false;
    super.appOrder = 0;
    super.appClass = "11111";
    super.rolePrivilege = "111111111111111111111111111111111111111"; //�����ݿ��е��ֶγ��ȱ���һ��
    super.itemPicker.put(super.module, this);
  }

  /**
   * ת���ɲ˵��ַ������
   * @param role ��ɫ
   * @return �˵��ַ���
   */
  public String toMenu(Role role) throws TranFailException {
    try {
      StringBuffer menu = new StringBuffer();
      menu.append("<TABLE width=\"180\" cellspacing=\"1\" cellpadding=\"0\">");
      //����ӽڵ�Ĳ˵�
      for (Iterator iterator = children.iterator(); iterator.hasNext();) {
        MenuItemUnit child = (MenuItemUnit)iterator.next();
        menu.append(child.toMenu(role));
      }
      //����̶��Ĳ˵� �޸�����
      menu.append("<TR TITLE=\""+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S009")+"\" Class=\"Navigator\" ID=\"01008\" AncestorID=\"00000\" Depth=\"0\">");
      menu.append("<TD STYLE=\"cursor:hand\"  class=\"title\">");
      menu.append("<DIV noWrap=\"true\" STYLE=\"padding-left:0em;\">");
      menu.append("<img src=\"" + baseWebPath + "/images/dot.gif\">");
      menu.append("<a href =\"javascript:goto('01008')\">"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S009")+"</A>");
      menu.append("</DIV>");
      menu.append("</TD>");
      //menu.append("</TR>");
      menu.append("</TR>");
      //Ĭ��ҵ������
      menu.append("<TR TITLE=\""+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S005")+"\" Class=\"Navigator\" ID=\"90102\" AncestorID=\"00000\" Depth=\"0\">");
      menu.append("<TD STYLE=\"cursor:hand\"  class=\"title\">");
      menu.append("<DIV noWrap=\"true\" STYLE=\"padding-left:0em;\">");
      menu.append("<img src=\"" + baseWebPath + "/images/dot.gif\">");
      menu.append("<a href =\"javascript:goto('90102')\">"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S005")+"</A>");
      menu.append("</DIV>");
      menu.append("</TD>");
      menu.append("</TR>");
      
      //Ĭ����������
	  menu.append("<TR TITLE=\""+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S011")+"\" Class=\"Navigator\" ID=\"90103\" AncestorID=\"00000\" Depth=\"0\">");
	  menu.append("<TD STYLE=\"cursor:hand\"  class=\"title\">");
	  menu.append("<DIV noWrap=\"true\" STYLE=\"padding-left:0em;\">");
	  menu.append("<img src=\"" + baseWebPath + "/images/dot.gif\">");
	  menu.append("<a href =\"javascript:goto('90103')\">"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S011")+"</A>");
	  menu.append("</DIV>");
	  menu.append("</TD>");
	  menu.append("</TR>");
      
	  //�л�����
	  menu.append("<TR TITLE=\""+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S010")+"\" Class=\"Navigator\" ID=\"x\" AncestorID=\"00000\" Depth=\"0\">");
	  menu.append("<TD STYLE=\"cursor:hand\"  class=\"title\">");
	  menu.append("<DIV noWrap=\"true\" STYLE=\"padding-left:0em;\">");
	  menu.append("<img src=\"" + baseWebPath + "/images/dot.gif\">");
	  menu.append("<a href =\"javascript:changeLang(\'"+role.getLangCode()+"\')\">"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S010")+"</A>");
	  menu.append("</DIV>");
	  menu.append("</TD>");
	  menu.append("</TR>");
      
      //ͳһ��֤��ǩ��
      //�˳���ϵͳ
      if (SSICTool.isSSICEnabled()) {
        menu.append("<TR TITLE=\""+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S006")+"\" Class=\"Navigator\" ID=\"241\" AncestorID=\"0\" Depth=\"0\">");
        menu.append("<TD STYLE=\"cursor:hand\"  class=\"title\">");
        menu.append("<DIV noWrap=\"true\" STYLE=\"padding-left:0em;\">");
        menu.append("<img src=\"" + baseWebPath + "/images/dot.gif\">");
        menu.append("<a href =\"icbc.cmis.servlets.CmisReqServlet?operationName=LogoutOp\" target=\"_top\")\">"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S006")+"</a>");
        menu.append("</DIV>");
        menu.append("</TD>");
        menu.append("</TR>");
        //��Աǩ��
        menu.append("<TR TITLE=\""+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S007")+"\" Class=\"Navigator\" ID=\"242\" AncestorID=\"0\" Depth=\"0\">");
        menu.append("<TD STYLE=\"cursor:hand\"  class=\"title\">");
        menu.append("<DIV noWrap=\"true\" STYLE=\"padding-left:0em;\">");
        menu.append("<img src=\"" + baseWebPath + "/images/dot.gif\">");
        menu.append("<a href =\"icbc.cmis.servlets.CmisReqServlet?operationName=LogoutOp&scene=quitSSIC\" target=\"_top\")\">"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S007")+"</A>");
        menu.append("</DIV>");
        menu.append("</TD>");
        menu.append("</TR>");
      }
      //��ͳһ��֤��ǩ��
      else {
        menu.append("<TR TITLE=\""+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S006")+"\" Class=\"Navigator\" ID=\"241\" AncestorID=\"0\" Depth=\"0\">");
        menu.append("<TD STYLE=\"cursor:hand\"  class=\"title\">");
        menu.append("<DIV noWrap=\"true\" STYLE=\"padding-left:0em;\">");
        menu.append("<img src=\"" + baseWebPath + "/images/dot.gif\">");
        menu.append("<a href =\"icbc.cmis.servlets.CmisReqServlet?operationName=LogoutOp\" target=\"_top\")\">"+icbc.cmis.tags.MuiTagBase.getStr(propertyFile, role.getLangCode(), "S006")+"</A>");
        menu.append("</DIV>");
        menu.append("</TD>");
        menu.append("</TR>");
      }
      menu.append("</TABLE>");

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
   * ��дclone����
   */
  public MenuItemUnit cloneTree() throws CloneNotSupportedException {
    MenuItemRoot root = new MenuItemRoot();
    root.children = new TreeSet();
	root.itemPicker.put(root.module, root);
    for (Iterator iterator = children.iterator(); iterator.hasNext();) {
      MenuItemUnit thisChild = (MenuItemUnit)iterator.next();
	  MenuItemUnit cloneChild = thisChild.cloneTree(root.itemPicker);
//	  cloneChild.itemPicker = root.itemPicker;
//	  cloneChild.itemPicker.put(cloneChild.module,cloneChild);
	  root.children.add(cloneChild);
    }
    //(TreeSet)children.clone();
    //MenuItemRoot root = (MenuItemRoot)super.clone();
    //root.itemPicker = (HashMap)itemPicker.clone();
    return root;
  }

  /**
   * ��������
   * �������������ó�null��������������
   */
  public void destory() {
    for (Iterator iterator = children.iterator(); iterator.hasNext();) {
      MenuItemUnit child = (MenuItemUnit)iterator.next();
      child.destory();
    }
    itemPicker.clear();
    itemPicker = null;
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