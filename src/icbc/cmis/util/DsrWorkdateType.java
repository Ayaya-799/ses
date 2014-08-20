package icbc.cmis.util;

import icbc.cmis.base.CmisConstance;

/**
 * @author xgl
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DsrWorkdateType extends DsrDataElementType {
	/**
	 * Constructor for DsrWorkdateType.
	 */
	public DsrWorkdateType() {
		super();
	}
	public String format(String src) {
		return CmisConstance.getWorkDate(getFormat());
	}
	public void setFormat(String fmt)throws Exception{
		if(fmt == null || fmt.trim().length() == 0){
			throw new Exception("�������ļ�������["+getDataName()+"]����[format]δ����");
		}
		super.setFormat(fmt);
	}

}
