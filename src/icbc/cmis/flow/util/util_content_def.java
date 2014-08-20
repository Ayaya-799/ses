/*
 * �������� 2006-3-8
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */

package icbc.cmis.flow.util;

import java.util.*;
import icbc.cmis.operation.*;
import icbc.cmis.base.*;

/**
 * @author ֣�ڱ� ����-��ʾ���ֶ���Ϣ ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */

public class util_content_def {
	public util_content_def() {
	}

	/**
	 * ��ѯ���ڶ���
	 * 
	 * @param entcode
	 * @param tradecode
	 * @param flowtype
	 * @param empareacode
	 * @param busitype
	 * @return
	 * @throws Exception
	 */
	public ArrayList getflowdefconten(String entcode, String tradecode,
			String flowtype, String empareacode, String busitype)
			throws Exception {
		ArrayList alist = new ArrayList();
		try {
			DummyOperation dummyop = new DummyOperation();
			util_content_defDao dao = new util_content_defDao(dummyop);
			alist = dao.getflowdefconten(entcode, tradecode, flowtype,
					empareacode, busitype);
		} catch (TranFailException ee) {
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_def.getflowdefconten()", e.getMessage(), e
							.getMessage());
		}
		return alist;
	}

}