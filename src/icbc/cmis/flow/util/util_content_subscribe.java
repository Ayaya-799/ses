/*
 * �������� 2006-3-6
 * 
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
 
package icbc.cmis.flow.util;

import icbc.cmis.base.TranFailException;
import icbc.cmis.operation.DummyOperation;

import java.util.ArrayList;






/**
 * @author 
 * @author Ԭ��
 * ����-��ʾǩ����ǩ�����
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 * 
 * �����������ѯǩ����ǩ������ķ�װ�࣬����OP
 * 
 * 
 */

public class util_content_subscribe {
	public util_content_subscribe() {
	}
	
	public String querysubscribe(String entcode, String tradecode,String empareacode,String employeecode,String ordercode,String flowtype,String tradetype) throws Exception {
		String  msg = "";
				try {
					DummyOperation dummyop = new DummyOperation();
					util_content_subscribeDao dao = new util_content_subscribeDao(dummyop);
					msg = dao.getsubscribe( tradecode,entcode,tradetype);
				}
	
	 catch (TranFailException ee) {
				throw ee;
			} catch (Exception e) {
				throw new TranFailException("icbc.cmis.flow.util", "util_content_subscribe.querysubscribe()", e.getMessage(), e.getMessage());
			}
				return msg;
	}
}
