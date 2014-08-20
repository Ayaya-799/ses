/*
 * �������� 2006-3-3
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */

package icbc.cmis.flow.util;

import java.util.*;
import icbc.cmis.operation.*;
import icbc.cmis.base.*;

/**
 * @author ��Զ�� ����-ѡ����һ����,��һ���� ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_content_choosenext {
	public util_content_choosenext() {

	}

	/**
	 * ȡ�û����б�
	 * 
	 * @param flowtype
	 * @param busi_type
	 * @param langcode
	 * @return
	 * @throws Exception
	 */
	public ArrayList getflowlist(String flowtype, String busi_type,
			String langcode) throws Exception {
		ArrayList alist = new ArrayList();
		try {
			DummyOperation dummyop = new DummyOperation();
			util_content_choosenextDao dao = new util_content_choosenextDao(
					dummyop);
			alist = dao.getflowlist(flowtype, busi_type, langcode);
		} catch (TranFailException ee) {
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_choosenext.getflowlist()", e.getMessage(), e
							.getMessage());
		}
		return alist;
	}

	/**
	 * ȡ�÷��͵���һ����
	 * 
	 * @param areacode
	 * @param busi_type
	 * @param entcode
	 * @return
	 * @throws Exception
	 */
	public ArrayList getnextarea(String areacode, String busi_type,
			String entcode) throws Exception {
		ArrayList alist = new ArrayList();
		try {
			DummyOperation dummyop = new DummyOperation();
			util_content_choosenextDao dao = new util_content_choosenextDao(
					dummyop);
			alist = dao.getnextarea(areacode, busi_type, entcode);
		} catch (TranFailException ee) {
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_choosenext.getflowlist()", e.getMessage(), e
							.getMessage());
		}
		return alist;
	}

}