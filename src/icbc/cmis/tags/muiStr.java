package icbc.cmis.tags;

/**
 * Ϊ����ҳ��ʹ��resource��scriptlet��
 * @author zjfh-zhangyz
 * 2007-4-4 / 9:06:43
 *
 */

public class muiStr {
	private String sDef;
	private String sLang;
	/**
	 * ����resource���������
	 * @param def resource����
	 * @param lang ʹ�õ�����
	 */
	public muiStr(String def, String lang) {
		this.sDef = def;
		this.sLang = lang;
	}
	/**
	 * ����resource
	 * @param sItem
	 * @return
	 */
	public String getStr(String sItem) {
		return icbc.cmis.tags.MuiTagBase.getStr(sDef, sLang, sItem);
	}
}