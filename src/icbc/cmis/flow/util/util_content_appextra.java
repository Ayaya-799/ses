package icbc.cmis.flow.util;

import java.util.*;
import java.sql.*;
import icbc.cmis.base.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;

/**
 * ���������޸����
 * 
 * @author zjfh-zhangyz
 * 
 * ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_content_appextra {
	public util_content_appextra() {

	}

	public String spellbtn(String entcode, String tradecode, String tradetype)
			throws Exception {
		String url = "";
		try {
			DummyOperation dummyop = new DummyOperation();
			util_content_appextraDao dao = new util_content_appextraDao(dummyop);
			url = dao.spellbtn(entcode, tradecode, tradetype);
		} catch (TranFailException ee) {
			throw ee;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.util",
					"util_content_appextra.spellbtn()", e.getMessage(), e
							.getMessage());
		}
		return url;
	}

}