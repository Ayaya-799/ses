/*
 * �������� 2006-3-6
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */

package icbc.cmis.flow.util;

import icbc.cmis.base.TranFailException;
import icbc.cmis.service.CmisDao;

/**
 * Title:
 * Description: ǩ����ǩ�����
 * Copyright:    Copyright (c) 2005
 * Company:icbcsdc
 * @author��Ԭ��
 * @version 1.0
 */

public class util_content_subscribeDao extends CmisDao{
	public util_content_subscribeDao(icbc.cmis.operation.CmisOperation op) {
		super(op);
		}
	private String killnull(String strin) {
			if (strin == null) {
				return "";
			} else {
				return strin;
			}
		}
	/**
 * ȡ��ǩ����ǩ�����
 * @param tradecode   �����
 * @param entcode     �ͻ�����
 * @return
 * @throws Exception
 */
		
		public String getsubscribe(String tradecode,String entcode,String tradetype )
	throws TranFailException{
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		String sql = null;
		String subscribe = "";
		try{
		getConnection("cmis3");
		//��ѯǩ�����
		sql= 
              "select process021  from mprocess_new" 
		    +" where process001=? and process002=? "
			+"and process005=(select max(process005) from mprocess_new where process001=? and process002=? and process004=?  )";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,entcode);
			pstmt.setString(2,tradecode);
			pstmt.setString(3,entcode);
		    pstmt.setString(4,tradecode);
		    pstmt.setString(5,tradetype);
			rs = pstmt.executeQuery();
		    
		    if(rs.next()){
		    
		    subscribe = rs.getString("process021");
		}
		}
		catch (TranFailException e) {
					throw e;
				} catch (Exception e) {
					throw new TranFailException("icbc.cmis.flow.util", "util_content_subscribeDao.getsubscribe()", e.getMessage(), e.getMessage());
				} finally {
					if (rs != null)
						try {
							rs.close();
						} catch (Exception ex) {
						}
					if (pstmt != null)
						try {
							pstmt.close();
						} catch (Exception ex) {
						}
					closeConnection();
		}
	
				
				return subscribe;
	}
}