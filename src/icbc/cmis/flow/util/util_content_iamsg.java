/*
 * �������� 2006-3-6
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */

package icbc.cmis.flow.util;

import java.util.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;
import icbc.cmis.base.*;

/**
 * @author ����-��ʾ���������Ϣ ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 * 
 * �����������ѯֻ�������ķ�װ�࣬����OP ֻ��������ȡ��ҳ����ֱ��ȡ�õķ���
 *  
 */

public class util_content_iamsg {
	public util_content_iamsg() {
	}

	/**
	 * ��ѯ�������
	 * 
	 * @param entcode
	 * @param tradecode
	 * @returnflow_entcode,flow_tradecode,
	 * @throws Exception
	 */
	public ArrayList queryiamsg(String entcode, String tradecode,
			String empareacode, String employeecode, String ordercode,
			String flowtype, String tradetype) throws Exception {
		ArrayList alist = new ArrayList();
		try {
			DummyOperation dummyop = new DummyOperation();
			util_content_iamsgDao dao = new util_content_iamsgDao(dummyop);
			alist = dao.getiamsg(entcode, tradecode, empareacode, employeecode,
					ordercode, flowtype, tradetype);
		} catch (TranFailException ee) {
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_iamsg.queryass()", e.getMessage(), e
							.getMessage());
		}
		return alist;
	}

}