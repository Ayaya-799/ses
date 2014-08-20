package icbc.cmis.tags;

import java.util.Locale;
import java.util.ResourceBundle;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * ר����Թ�����Դ����tag����
 * ֻ��һ����̬����Դ
 * @author zjfh-zhangyz
 * 2007-4-4 / 9:15:10
 *
 */

public class MuiTagPub extends TagSupport {
	private static String sPubdef = "icbc.cmis.icbc_cmis"; //������Դ�ļ���

	private String sItem;
	private String sMark = "";

	public MuiTagPub() {
		super();
	}

	public int doStartTag() throws JspTagException {
		try {
			//��session��ȡ�����Զ���
			String sLang = "";
			try {
				HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
				icbc.cmis.base.CMisSessionMgr sm = new icbc.cmis.base.CMisSessionMgr(request);
				sLang = (String)sm.getSessionData("LangCode");
				//sLang = (String)pageContext.getSession().getAttribute("LangCode");
			} catch (Exception eee) {
				sLang = "zh_CN";
			}
			String strOutBody = MuiTagBase.getStr(sPubdef, sLang, sItem);

			//���
			StringBuffer strbuf = new StringBuffer();
			JspWriter out = pageContext.getOut();
			out.print(strbuf.append(sMark).append(strOutBody).append(sMark).toString());
		} catch (IOException ioe) {
			Debug.println("Error Tag!");
		}
		return (SKIP_BODY);
	}

	public void setItem(String tItem) {
		this.sItem = tItem;
	}

	public void setMark(String tMark) {
		this.sMark = tMark;
	}

	public void setExp(String tExp) {

	}

}