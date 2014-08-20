/*
 * �������� 2006-3-2
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */

package icbc.cmis.flow.util;

import oracle.jdbc.driver.*;
import icbc.cmis.service.CmisDao;
import icbc.cmis.operation.*;
import java.util.*;
import java.io.IOException;
import java.sql.*;
import icbc.cmis.base.TranFailException;
import icbc.cmis.util.DBTools;

/**
 * Title: Description: ��ѯ�������̱��о������� Copyright: Copyright (c) 2005
 * Company:icbcsdc
 * 
 * @author��֣�ڱ�
 * @version 1.0
 */
public class util_content_msg3Dao extends CmisDao {
	public util_content_msg3Dao(CmisOperation op) {
		super(op);
	}

	/**
	 * ��������: ��ѯ�������˵��
	 * 
	 * @param entcode
	 *            //�ͻ�����
	 * @param tradecode
	 *            //ҵ�������
	 * @param xh
	 *            //���
	 * @param step
	 *            //����(��Ҫ���������Ƿ��ǵ�����)
	 * @return
	 * @throws
	 */
	public Vector getadvicetxt(String entcode, String tradecode, String xh,
			String step) throws TranFailException {
		String queryStr = "";

		queryStr = " select process021"
				+ " from mprocess_new   "
				+ " WHERE process001=?  AND process002=?  AND process005=? AND process006=? ";

		try {
			DBTools srv = new DBTools(this.getOperation());
			Vector param = new Vector(4);
			param.add(entcode);
			param.add(tradecode);
			param.add(xh);
			param.add(step);
			Vector v_result = srv.executeSQLResult(queryStr, param);
			return v_result;
		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_msg3Dao",
					"util_content_msg3Dao.getadvicetxt", e.getMessage());
		}
	}
}