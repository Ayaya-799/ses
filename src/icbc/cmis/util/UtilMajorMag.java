package icbc.cmis.util;

import icbc.cmis.operation.*;
import icbc.cmis.base.*;
import java.util.*;
import java.sql.*;
import icbc.missign.*;
import icbc.cmis.util.Decode;

/**
 * Title: ҵ���ܲü�
 * Description: ������ҵ���ܵ���ɾ��
 * Modify History:
 *   20020730  cmis��bmis��vmis������ñ�ģ�飬������������application�������ֲ�ͬ��Ӧ�ã�
 *   20030423  ������ÿ�ε���ɾ�Ĳ�����������setDictUpdatetMark �����������̼߳�ʱ�����޸ĵĲ˵����ݣ�
 *
 *	 20060228 1�������Ƿ���Ч���޸Ĺ��ܡ�
				a.����ģ�����ó���Чʱ����Ҫͬʱ���¸��е���ͬ���ܱ�ŵ���Ч�ԡ�
				b.����ģ�����ó���Чʱ��������е�ģ��ά��ԭ״��
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  ����ΰ �����2002-04-27
 * 			 �Ĵ��� �޸���2006-02-28
 * @version 1.0
 */

public class UtilMajorMag extends CmisOperation {

	public UtilMajorMag() {
		super();
	}

	public void execute() throws java.lang.Exception {
		/**@todo: implement this icbc.cmis.operation.CmisOperation abstract method*/
		try {
			String action = getStringAt("opAction");

			if (action.equals("displayMajorFunc")) {
				displayMajorFunc();
			}
			else if (action.equals("enterSelectPage")) { //��������ѡ��ҳ��
				enterSelectPage();
			}
			else if (action.equals("querySelectModule")) { //��ѯ���û�ѡ���ģ��
				querySelectModule();
			}
			else if (action.equals("MovePageSelectFunc")) { //ѡ��ҳ�淭ҳ
				xmlizeSelectFunc();
			}
			else if (action.equals("enterAddSetPage")) { //��������ģ��Ȩ������ҳ��
				enterAddSetPage();
			}
			else if (action.equals("addFunc")) { //����ģ��
				addFunc();
			}
			else if (action.equals("deleteFunc")) { //ɾ��ģ��
				deleteFunc();
			}
			else if (action.equals("enterUpdatePage")) { //�����޸�ҳ��
				enterUpdatePage();
			}
			else if (action.equals("updateFunc")) {
				updateFunc();
			}
		}
		catch (TranFailException te) {
			this.setOperationDataToSession();
			throw te;
		}
		catch (Exception e) {
			this.setOperationDataToSession();
			throw new TranFailException("100534", "UtilMajorMag.execute()", e.getMessage(), genMsg.getErrMsgByLang((String)getSessionData("LangCode"), "100534"));//ҵ���ܲü�ģ�����
		}
	} 

	/**
	 * ����ҵ����룬��ѯ��ҵ���µ�����ģ�顢��ģ���Ȩ��
	 * @throws Exception
	 */
	private void displayMajorFunc() throws Exception {
		try {
			String major_code = getStringAt("major_code");
			String sAppName = getStringAt("application"); //����Ӧ�ã�cmis��bmis��vmis��������
		String area_code = "00000000"; //����
			String menu = "";
			if (major_code != null && !major_code.equals("")) {
				MenuDAO3 dao3 = new MenuDAO3(this);
				menu = dao3.getMenuXML(major_code, area_code);
			}
			try {
				//this.removeDataField("empClassVector");
				this.removeDataField("str_menu");
			}
			catch (Exception e) {}
			//this.addDataField("empClassVector",empClassVector);
			this.addDataField("str_menu", menu);
			this.setOperationDataToSession();
			setReplyPage("/M/MA/MA_MajorFuncMag.jsp");
			//display from yxt
		}
		catch (TranFailException te) {
			throw te;
		}
		catch (Exception e) {
			throw new TranFailException("100535", "UtilMajorMag.displayMajorFunc()", e.getMessage(), genMsg.getErrMsgByLang((String)getSessionData("LangCode"), "100535"));
		}
	}

	/**
	 * �����û�ѡ������ҳ��
	 * @throws Exception
	 */
	private void enterSelectPage() throws Exception {
		this.setOperationDataToSession();
		this.setReplyPage("/M/MA/MA_MajorFuncSelectList.jsp");
	}

	/**
	 * ѡ���ͬһ��εĲ��һ�û��ѡ�ϵĹ���ģ�飬���û�ѡ������
	 * @throws Exception
	 */
	private void querySelectModule() throws Exception {
		SqlTool tool = new SqlTool(this);
		try {
			String sMajorCode = this.getStringAt("major_code");
			String sModuleId = this.getStringAt("ModuleId");
			String sPModuleId = this.getStringAt("ParentModuleId");
			//String sQryModName = Decode.decode(this.getStringAt("ModName").trim());//��������Ѿ��ڿ��������
			String sQryModName = this.getStringAt("ModName").trim();
			String sQryModId = this.getStringAt("ModCode").trim();
		String area_code = "00000000"; //����

			String application = (String)CmisConstance.getParameterSettings().get("application");
			StringTokenizer token = new StringTokenizer(application, "|");

			//��ѯ��רҵ�£�״̬�����������ѯ������ģ�����������ڵ㣬�ֵܽڵ㶼��ѡ��			 
			Vector vValue = new Vector();
			vValue.add(sMajorCode);
			vValue.add(sPModuleId);
			vValue.add(sPModuleId);
			String sql =
				"select func_code, func_name, func_sub_node from mag_function where func_code not in (select app_module_code from mag_application_new,mag_area where app_major_code = ? and (app_module_code = ? or app_pmodule_code = ?) and mag_area.area_code = '"
					+ area_code
					+ "' and mag_application_new.area_code = mag_area.area_code";
			sql += " and application in (";
			String subSql = "";
			while (token.hasMoreTokens()) {
				subSql += ",'" + token.nextToken() + "'";
			}
			sql += subSql.substring(1) + ")) and func_status = '0'";

			if (sQryModName.length() > 0) {
				//              sql += " and func_name like '%" + sQryModName + "%'";
				vValue.add("%" + sQryModName + "%");
				sql += " and func_name like ? ";
			}
			if (sQryModId.length() > 0) {
				//              sql += " and func_code like '%" + sQryModId + "%'";
				vValue.add("%" + sQryModId + "%");
				sql += " and func_code like ? ";
			}

			tool = new SqlTool(this);
			tool.getConn("missign");
			ResultSet rs = tool.executeQuery(sql, vValue);
			Vector funcVector = new Vector();
			while (rs.next()) {
				String sCode = rs.getString(1);
				String sName = rs.getString(2);
				String sHasChild = rs.getString(3);
				String sValue = sCode + "###" + sName + "@@@" + sHasChild;
				funcVector.add(sValue);
			}
			this.setFieldValue("AvailFuncVector", funcVector);

			this.setOperationDataToSession();
			this.xmlizeSelectFunc();
		}
		catch (TranFailException te) {
			throw te;
		}
		catch (Exception e) {
			throw new TranFailException("100536", "UtilMajorMag.enterSelectPage()", e.getMessage(), genMsg.getErrMsgByLang((String)getSessionData("LangCode"), "100536"));//��ѯ��ѡ��������ҵ����ģ�����
		}
		finally {
			try {
				tool.closeconn();
			}
			catch (Exception e) {}
		}
	}

	/**
	 * �Ѵ�ѡ��Ĺ���ģ����xml��ʽ���ظ�ҳ��
	 * @throws Exception
	 */
	private void xmlizeSelectFunc() throws Exception {
		try {
			java.util.Vector aVector = (java.util.Vector)this.getObjectAt("AvailFuncVector");
			int beginPos = Integer.parseInt(getStringAt("beginPos"));
			int page_size = 15;
			int currPos = beginPos + page_size;
			if (currPos > aVector.size())
				currPos = aVector.size();
			int pages = aVector.size() % page_size == 0 ? aVector.size() / page_size : aVector.size() / page_size + 1;
			int itemamount = aVector.size();

			String xmlPack = "<?xml version=\"1.0\" encoding=\"GB2312\"?>";
			xmlPack += "<Content pages=\"" + pages + "\" itemamount=\"" + itemamount + "\">";
			for (int i = beginPos; i < currPos; i++) {
				String sValue = (String)aVector.get(i);
				int idx1 = sValue.indexOf("###");
				int idx2 = sValue.indexOf("@@@");
				String sCode = sValue.substring(0, idx1);
				String sName = sValue.substring(idx1 + 3, idx2);
				sName = validXml(sName);
				String sHasChild = sValue.substring(idx2 + 3);
				xmlPack += "<CodeName code=\"" + sCode + "\" name=\"" + sName + "\" haschild=\"" + sHasChild + "\"/>";
			}
			xmlPack += "</Content>";

			this.setOperationDataToSession();
			this.setReplyPage("DirectOutput" + xmlPack);
		}
		catch (TranFailException te) {
			throw te;
		}
		catch (Exception e) {
			throw new TranFailException("100537", "UtilMajorMag.xmlizeSelectFunc()", e.getMessage(), genMsg.getErrMsgByLang((String)getSessionData("LangCode"), "100537"));//��ѡ�������ģ���б����ɷ�ҳ����ʧ��
		}
	}

	/**
	 * �û�ѡ�����ĳ��ģ��󣬽���Ȩ�ޡ��м�������ҳ��
	 * @throws Exception
	 */
	private void enterAddSetPage() throws Exception {
		
		String sNewModuleId = this.getStringAt("NewModuleId");
		String sMajorCode = this.getStringAt("major_code");
		String webBasePath =(String) CmisConstance.getParameterSettings().get("webBasePath");
		MenuDAO3 dao = new MenuDAO3(this);
		boolean isExist = dao.isExistMoudle(sMajorCode,sNewModuleId);
		this.setOperationDataToSession();
		if(isExist){
			this.setFieldValue("infoTitle", genMsg.getErrMsgByLang((String)getSessionData("LangCode"), "099996"));
			this.setFieldValue(
				"infoReturn","javascript:history.back();");
			this.setFieldValue("infoMsg", genMsg.getErrMsgByLang((String)getSessionData("LangCode"), "100545"));//�ù���ģ�����ڵ�ǰҵ��˵�ģ�嵱�д��ڣ������ظ�������
			this.setReplyPage(webBasePath + "/util/util_info.jsp");	
		}else{
			this.setReplyPage("/M/MA/MA_MajorFuncAddSet.jsp");
		}	
	}

	/**
	 * �������ҳ��
	 * @throws Exception
	 */
	private void enterUpdatePage() throws Exception {
		
		this.setOperationDataToSession();
		this.setReplyPage("/M/MA/MA_MajorFuncModify.jsp");

	}

	/**
	 * �ڱ�mag_application_new������һ����¼��ͬʱ����Ȩ���֡��м����޸��丸ģ���Ȩ�޺��м���
	 * @throws Exception
	 */
	private void addFunc() throws Exception {
		try {
			String sMajorCode = this.getStringAt("major_code");
			String sModuleId = this.getStringAt("ModuleId");
			String sPModuleId = this.getStringAt("ParentModuleId");
			String sNewModuleId = this.getStringAt("NewModuleId");
			String sModuleHasChild = this.getStringAt("ModuleHasChild");
			String sSiblingChild = this.getStringAt("SiblingChildRadio");
			String sAppName = this.getStringAt("application");
		String sEnaFlag = this.getStringAt("Enable");
		String area_code = "00000000"; //����
	  
			int PrivCount = Integer.parseInt(this.getStringAt("PrivilegeCount"));
			String sPrivilege = "", sBankClass = "", sEnableFlag = "";
			for (int i = 0; i < PrivCount; i++) {
				String sName = "ChkboxPriv" + (i + 1);
				String sPriv = "";
				try {
					sPriv = this.getStringAt(sName);
					sPrivilege += sPriv;
					this.removeDataField(sName); //��ֹ��һ�ν��뺯��ʱ��session����Ȼ������ε�ֵ
				}
				catch (TranFailException e) {
					sPrivilege += "0";
				}
			}
			StringBuffer sBuff = new StringBuffer(40);
			sBuff.append(sPrivilege);
			for(int i = 0;i<40 - PrivCount;i++){
				sBuff.append("0");
			}
			sPrivilege = sBuff.toString();
			for (int i = 0; i < 5; i++) {
				String sName = "ChkboxBank" + (i + 1);
				String sBank = "";
				try {
					sBank = this.getStringAt(sName);
					sBankClass += sBank;
					this.removeDataField(sName);
				}
				catch (TranFailException e) {
					sBankClass += "0";
				}
			}

			String sName = "ChkboxEnable";
			String sEnable = "";
			try {
				sEnable = this.getStringAt(sName);
				sEnableFlag += sEnable;
				this.removeDataField(sName);
			}
			catch (TranFailException e) {
				sEnableFlag = "0";
			}
			MenuDAO3 dao = new MenuDAO3(this);
			dao.addNewFunc(sMajorCode, sPModuleId, sModuleId, sNewModuleId, sPrivilege, sBankClass, sModuleHasChild, sSiblingChild, sAppName, sEnableFlag, area_code);
			this.setDictUpdatetMark("UTILMAJORMAGICBCECC["+sMajorCode +"$" + area_code +"]", "3", "cmis3");
			this.setOperationDataToSession();
			this.displayMajorFunc();
		}
		catch (TranFailException te) {
			throw te;
		}
		catch (Exception e) {
			throw new TranFailException("100538", "UtilMajorMag.addFunc()", e.getMessage(), genMsg.getErrMsgByLang((String)getSessionData("LangCode"), "100538"));//����ģ��ʧ��
		}
	}

	/**
	 * ɾ��ģ�鼰����ģ��
	 * @throws Exception
	 */
	private void deleteFunc() throws Exception {
		try {
			String sMajorCode = this.getStringAt("major_code");
			String sModuleId = this.getStringAt("ModuleId");
			String sPModuleId = this.getStringAt("ParentModuleId");
		String area_code = "00000000"; //����
	  
			MenuDAO3 dao = new MenuDAO3(this);
			dao.deleteFunc(sMajorCode, sPModuleId, sModuleId);
			this.setDictUpdatetMark("UTILMAJORMAGICBCECC["+ sMajorCode +"$" + area_code +"]", "3", "cmis3");
			this.setOperationDataToSession();
			this.displayMajorFunc();
		}
		catch (TranFailException te) {
			throw te;
		}
		catch (Exception e) {
			throw new TranFailException("100539", "UtilMajorMag.deleteFunc()", e.getMessage(), genMsg.getErrMsgByLang((String)getSessionData("LangCode"), "100539"));//ɾ��ģ��ʧ��
		}
	}

	/**
	 * ����һ������ģ���Ȩ���֣�ͬʱ�������ĸ���ģ���Ȩ����
	 * @throws Exception
	 */
	private void updateFunc() throws Exception {
		try {
			String sMajorCode = this.getStringAt("major_code");
			String sModuleId = this.getStringAt("ModuleId");
			String sPModuleId = this.getStringAt("ParentModuleId");
			String sPrePriv = this.getStringAt("PrePrivilege");
			String sPreBank = this.getStringAt("PreBankClass");
			String sPreAppOrder = this.getStringAt("PreAppOrder");
			String sAppOrder = this.getStringAt("AppOrder");
			String sEnaFlag = this.getStringAt("Enable");
			String area_code = "00000000"; //����
			int PrivCount = Integer.parseInt(this.getStringAt("PrivilegeCount"));
			String sPrivilege = "", sBankClass = "", sEnableFlag = "";
			for (int i = 0; i < PrivCount; i++) {
				String sName = "ChkboxPriv" + (i + 1);
				String sPriv = "";
				try {
					sPriv = this.getStringAt(sName);
					sPrivilege += sPriv;
					this.removeDataField(sName);
				}
				catch (TranFailException e) {
					sPrivilege += "0";
				}
			}
			StringBuffer sBuff = new StringBuffer(40);
			sBuff.append(sPrivilege);
				for(int i = 0;i<40 - PrivCount;i++){
					sBuff.append("0");
			}
			sPrivilege = sBuff.toString();
			for (int i = 0; i < 5; i++) {
				String sName = "ChkboxBank" + (i + 1);
				String sBank = "";
				try {
					sBank = this.getStringAt(sName);
					sBankClass += sBank;
					this.removeDataField(sName);
				}
				catch (TranFailException e) {
					sBankClass += "0";
				}
			}
			String sName = "ChkboxEnable";
			String sEnable = "";
			try {
				sEnable = this.getStringAt(sName);
				sEnableFlag += sEnable;
				this.removeDataField(sName);
			}
			catch (TranFailException e) {
				sEnableFlag = "0";
			}
			if (!sPrivilege.equals(sPrePriv) || !sBankClass.equals(sPreBank) || !sEnableFlag.equals(sEnaFlag) || !sAppOrder.equals(sPreAppOrder)) { //ģ���Ȩ�޺��м������仯
				MenuDAO3 dao = new MenuDAO3(this);
				dao.updateFunc(sMajorCode, sPModuleId, sModuleId, sPrivilege, sBankClass, sAppOrder, sEnableFlag, area_code);
				this.setDictUpdatetMark("UTILMAJORMAGICBCECC["+sMajorCode +"$" + area_code +"]", "3", "cmis3");
			}
			this.displayMajorFunc();
		}
		catch (TranFailException te) {
			throw te;
		}
		catch (Exception e) {
			throw new TranFailException("100527", "UtilMajorMag.updateFunc()", e.getMessage(), genMsg.getErrMsgByLang((String)getSessionData("LangCode"),"100527"));
		}
	}
	/**
	 *  
	 * Description  : ����XML����������ַ�	 
	 * @return
	 */

	public static String validXml(String xml) {
		String ret = xml;

		final String[] FROM = { "&", "<", ">", "'", "\"" };
		final String[] TO = { "&amp;", "&lt;", "&gt;", "��", "&quot;" };

		for (int i = 0; i < FROM.length; i++) {
			ret = replaceAll(ret, FROM[i], TO[i]);
		}
		return ret;
	}

	/**
	 *  
	 * Description  : �滻 (ע��:���ִ�Сд)
	 * CreationDate : 2007-6-20 15:05:15
	 * @param src
	 * @param replaceEx
	 * @param relacement
	 * @return
	 */
	public static String replaceAll(String src, String replaceEx, String relacement) {
		String ret = "";

		int srcLen = src.length();
		int repLen = replaceEx.length();
		int start = 0;
		int i;
		for (i = 0; i < srcLen - repLen + 1;) {
			if (src.substring(i, i + repLen).equals(replaceEx)) {
				ret += src.substring(start, i) + relacement;

				i += repLen;
				start = i;
			}
			else {
				i++;
			}
		}
		if (i > start) {
			ret += src.substring(start, i);
		}
		return ret;
	}		

}