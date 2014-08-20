package icbc.cmis.tags;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import icbc.cmis.base.KeyedDataCollection;

/**
 * ***********************************************************
 * 
 * <b>��������: </b>2007-5-15 15:31:11<br>
 * <b>����:  PropertyResourceReader.java</b><br>
 * <b>������: PropertyResourceReader.java</b><br>
 * <br>
 * <p>Copyright:(c)2007</p>
 * <p>Company: ICBC</p>
 * 
 * @author κ�鲨
 * 
 * @version 1.0
 * 
 * @since 
 * 
 * @see
 */

public class PropertyResourceReader {
  private String langCode = null;
  private String privateDef = null;
private static Hashtable hTag = new Hashtable();
	
  private String publicDef = "icbc.cmis.icbc_cmis";

  /**
   * ��ֻʹ�ù�������ʱ,���Բ�����˽�������ļ���
   * @param pageContext
   */
  public PropertyResourceReader(PageContext pageContext) {
    this.langCode = getLangCode(pageContext);
  }

  /**
   * ��Ҫʹ��˽������ʱ,һ��Ҫ����˽�������ļ���
   * @param pageContext
   * @param privateDef
   */
  public PropertyResourceReader(PageContext pageContext, String privateDef) {
    this.langCode = getLangCode(pageContext);
    this.privateDef = privateDef;
  }

  /**
   * ֱ�Ӵ���langCode,��ֻʹ�ù�������ʱ,���Բ�����˽�������ļ���
   * 20070530
   * @param langCode
   * @param privateDef
   */
  public PropertyResourceReader(String langCode) {
    this.langCode = langCode;
  }

  /**
   * ֱ�Ӵ���langCode,��Ҫʹ��˽������ʱ,һ��Ҫ����˽�������ļ���
   * 20070530
   * @param langCode
   * @param privateDef
   */
  public PropertyResourceReader(String langCode, String privateDef) {
    this.langCode = langCode;
    this.privateDef = privateDef;
  }

  /**
   * 
   * Description  :ȡ���������ı�,���ı������������$  
   * CreationDate : 2007-5-15 15:32:04
   * @author     : 
   *   
   * @param id
   * @return
   */
  public String getPublicStr(String id) {
    return this.getString(true, id, null);
  }

  /**
   *  
   * Description  : ȡ���������ı�,���ı����������$ 
   * CreationDate : 2007-5-15 15:33:01
   * @author     : 
   *   
   * @param id
   * @param para
   * @return
   */
  public String getPublicStr(String id, String para) {
    return this.getString(true, id, para);
  }

  public String getPublicStr(String id, int para) {
    return this.getPublicStr(id,""+para);
  }

  /**
   *  
   * Description  : ȡ˽�������ı�,���ı������������$ 
   * CreationDate : 2007-5-15 15:33:01
   * @author     : 
   *   
   * @param id
   * @param para
   * @return
   */
  public String getPrivateStr(String id) {
    return this.getString(false, id, null);
  }

  /** 
   * Description  : ȡ˽�������ı�,���ı����������$ 
   * CreationDate : 2007-5-15 15:33:57
   * @author     : 
   *   
   * @param id
   * @param para
   * @return
   */
  public String getPrivateStr(String id, String para) {
    return this.getString(false, id, para);
  }

  public String getPrivateStr(String id, int para) {
    return this.getPrivateStr(id, "" + para);
  }

  /**
   *  
   * Description  : �ڴ˷����е���MuiTagBase.getStr
   * CreationDate : 2007-5-15 15:34:31
   * @author     : 
   *   
   * @param isPublic
   * @param id
   * @param para
   * @return
   */
  private String getString(boolean isPublic, String id, String para) {
    String ret = "";

    String muiDef;

    if (isPublic) {
      muiDef = publicDef;
    }
    else {
      muiDef = privateDef;
    }

    ret = MuiTagBase.getStr(muiDef, langCode, id);

    if (null != para && !"".equals(para.trim()) && null != ret && !"".equals(ret)) {
      StringTokenizer paraToken = new StringTokenizer(para, "|");

      while (paraToken.hasMoreTokens() && ret.indexOf("$") > -1) {
        String aPara = paraToken.nextToken();
        ret = replaceFirst(ret, "$", aPara);
      }

    }

    return ret;
  }

  /**
   *  
   * Description  : ����pageContextȡsession,ȡLangCode
   * CreationDate : 2007-5-15 15:35:50
   * @author     : 
   *   
   * @param pageContext
   * @return
   */
  private String getLangCode(PageContext pageContext) {
    String ret;
    try {
      HttpSession session = ((HttpServletRequest)pageContext.getRequest()).getSession(false);
      KeyedDataCollection k = (KeyedDataCollection)session.getAttribute("sessionKCData");
      ret = (String)k.getValueAt("LangCode");
    }
    catch (Exception eee) {
      ret = "zh_CN";
    }

    return ret;
  }

  /**
   *  
   * Description  : �ַ������
   * CreationDate : 2007-5-15 15:36:42
   * @author     : 
   *   
   * @param in
   * @param regex
   * @param replacement
   * @return
   */
  private String replaceFirst(String in, String regex, String replacement) {
    String ret = in;

    int begin, end;
    begin = in.indexOf(regex);
    if (begin > -1) {
      end = begin + regex.length();

      ret = in.substring(0, begin) + replacement + in.substring(begin + 1);
    }

    return ret;
  }
  
  public String getStr(String chinese)
  {
  		if (this.langCode.equals("zh_CN")) return chinese;
  		String id="" ;
		String file = this.privateDef + "_zh_CN";//��Ϊ��ResourceBundle��keyֵ
		ResourceBundle bundle = (ResourceBundle)hTag.get(file);
		//�����û������������һ��
		if (bundle == null) {
			Locale local = MuiTagBase.getDefaultLocale("zh_CN");
			bundle = ResourceBundle.getBundle(this.privateDef, local);
			hTag.put(file, bundle);
		}
		Enumeration enumkeys = bundle.getKeys();
		String key = "";
		for (; enumkeys.hasMoreElements(); key = (String)enumkeys.nextElement()) {
			if (key != null && !"".equals(key)) {
				String valueOfKey = bundle.getString(key);
				if (valueOfKey.equalsIgnoreCase(chinese))
				{
					id = key;
					break;
				}
			}
		}
		if ("".equals(id))
		{
			file = this.publicDef + "_zh_CN" ;//��Ϊ��ResourceBundle��keyֵ
			bundle = (ResourceBundle)hTag.get(file);
			//�����û������������һ��
			if (bundle == null) {
				Locale local = MuiTagBase.getDefaultLocale("zh_CN");
				bundle = ResourceBundle.getBundle(this.publicDef, local);
				hTag.put(file, bundle);
			}
			enumkeys = bundle.getKeys();
			key = "";
			for (; enumkeys.hasMoreElements(); key = (String)enumkeys.nextElement()) {
				if (key != null && !"".equals(key)) {
					String valueOfKey = bundle.getString(key);
					if (valueOfKey.equalsIgnoreCase(chinese))
					{
						id = key;
						break;
					}
				}
			}
		}
		System.out.println(id);
  	if (id.startsWith("C"))
  		return this.getPrivateStr(id);
  	else if (id.startsWith("P"))
  		return this.getPublicStr(id);
  	else
  		return chinese;
  		
  }

}