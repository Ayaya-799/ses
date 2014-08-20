/*
 * �������� 2006-7-10
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
 * Title:
 * Description: ��ѯ��һ�����б�,��һ��������б�
 * Company:icbcsdc
 * @author��Ԭ��
 * @version 1.0
 */
public class util_content_choosenextDao1 extends CmisDao {

	public util_content_choosenextDao1(CmisOperation op) {
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
	 * ��������: ��ѯ��һ�����б�
	 * @param  flowtype   //��������	 
	 * @return
	 * @throws
	 */
	public ArrayList getflowlist(String flowtype, String busi_type,
		String lang_code) throws TranFailException {
	ArrayList alist = new ArrayList();
	java.sql.ResultSet rs = null;
	java.sql.PreparedStatement pstmt = null;
	String sql = null;
	try {
		getConnection("cmis3");
		sql = " select role_code,role_name"
				+ " from imag_role_major_new "
				+ " where lang_code = ? "
				+ "and  role_type= (select kind_type from imag_kind where kind_code=? and lang_code'"+lang_code+"') "
				+ "and role_flag2='1' order by role_code ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, lang_code);
		pstmt.setString(2, flowtype);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			HashMap hmap = new HashMap();
			hmap.put("role_code", killnull(rs.getString("role_code")) + "|"
					+ killnull(rs.getString("role_name")));
			hmap.put("role_name", killnull(rs.getString("role_name")));
			alist.add(hmap);
		}
	} catch (TranFailException te) {
		throw te;
	} catch (Exception e) {
		throw new TranFailException("util_content_choosenextDao",
				"util_content_choosenextDao.getflowlist", e.getMessage());
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
	return alist;
}

	/**
	 * ȡ���Է��͵ĵ���
	 * @param areacode ������
	 * @param busi_type ҵ�����ʣ�ǰ������
	 * @param entcode �ͻ�����
	 * @param tradecode ҵ���
	 * @param tradetype ��������
	 * @return
	 * @throws TranFailException
	 */
	public ArrayList getnextarea(String areacode, String busi_type, String entcode,String tradecode,String tradetype) throws TranFailException {
		ArrayList alist = new ArrayList();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		java.sql.CallableStatement stmt_call = null;
		String sql = null;
		
		try {
			getConnection("cmis3");

			//ȡ������
			sql ="select area_code,area_name from mag_area where  area_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, areacode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap hmap = new HashMap();
				hmap.put("area_code", killnull(rs.getString("area_code")) + "|" + killnull(rs.getString("area_name")));
				hmap.put("area_name", killnull(rs.getString("area_name")));
				alist.add(hmap);
			}
			//ȡҵ��������
			if(tradetype.equals("qt01"))  //����
			sql = "select area_code,area_name from mag_area where area_code=(select TA200251047 from TA200251 where TA200251001=? and TA200251002=?)";
		    else if(tradetype.equals("qt02")) //�ж�
			sql = "select area_code,area_name from mag_area where area_code=(select TA200321032 from ta200321 where ta200321001=? and TA200321002=?)";
			else if(tradetype.equals("qt03"))  //����Ѻ��
			sql = "select area_code,area_name from mag_area where area_code=(select TA270030017 from TA270030 where TA270030001=? and TA270030028=?)";
			else if(tradetype.equals("qt04"))  //����Ѻ��
			sql = "select area_code,area_name from mag_area where area_code=(select TA270025002 from TA270025 where TA270025001=? and TA270025003=?)";
			else if(tradetype.equals("qt05")) //���б���
			sql = "select area_code,area_name from mag_area where area_code=(select TA250011999 from Ta250011 where Ta250011001=? and TA250011002=?)";
			else if(tradetype.equals("qt06")) //����֤���½��ڴ���
			sql = "select area_code,area_name from mag_area where area_code=(select ta270051002 from ta270051 where ta270051001=? and ta270051004=? and ta270051007 = '8065')";
			else if(tradetype.equals("qt07")) //�����޸�
			sql = "select area_code,area_name from mag_area where area_code=(select ta250091002 from TA250091 where ta250091001=? and ta250091004=?)";
			else if(tradetype.equals("qt08")) //����֤
			sql = "select area_code,area_name from mag_area where area_code=(select TA270021002 from TA270021 where TA270021001=? and TA270021003=?)";
			else if(tradetype.equals("qt09")) //����֤�޸�
			sql = "select area_code,area_name from mag_area where area_code=(select TA270022002 from TA270022 where TA270022001=? and TA270022005=?)";
			else if(tradetype.equals("qt10")) //չ��
			sql = "select area_code,area_name from mag_area where area_code=(select ta200231040 from TA200081 where ta200081001=? and TA200081003=?)";
			else if(tradetype.equals("qt21")) //����͸֧
			sql = "select area_code,area_name from mag_area where area_code=(select ta270051002 from ta270051 where ta270051001=? and ta270051004=?)";
			else if(tradetype.equals("qt25")) //������֤�µĽ��ڴ���
			sql = "select area_code,area_name from mag_area where area_code=(select ta270051002 from ta270051 where ta270051001=? and ta270051004=? and ta270051007 <> '8065')";
			else if(tradetype.equals("qt26")) //���ڴ���չ��
			sql = "select area_code,area_name from mag_area where area_code=(select ta270053002 from TA270053 where ta270053001=? and ta270053004=?)";
			else if(tradetype.equals("qt32")) //���ڱ���δ���ʵǼ�
			sql = "select area_code,area_name from mag_area where area_code=(select ta270062002 from TA270062 where ta270062001=? and ta270062004=?)";
			else if(tradetype.equals("qt33")) //���ڱ����޸�
			sql = "select area_code,area_name from mag_area where area_code=(select ta270061002 from TA270061 where ta270061001=? and ta270061004=?)";
			else if(tradetype.equals("qt51")) //���ڱ�������
			sql = "select area_code,area_name from mag_area where area_code=(select ta270064002 from TA270064 where ta270064001=? and ta270064004=?)";
			else if(tradetype.equals("qt53")) //���ڱ���Ӧ���ʿ�����
			sql = "select area_code,area_name from mag_area where area_code=(select ta270067002 from TA270067 where ta270067001=? and ta270067004=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, entcode);
			pstmt.setString(2, tradecode);
			rs = pstmt.executeQuery();
			while (rs.next())
			{				
				String area_code = rs.getString(1);
				String area_name = rs.getString(2);
				boolean match = false;
				for(int i=0;i<alist.size();i++)
				{
					HashMap map = (HashMap)alist.get(i);
					String mapname = (String)map.get("area_name");
					if (mapname!=null && mapname.equals(area_name))
					{
						match =true;
						break;
					}
				}
				if (match==false) {
					HashMap hmap = new HashMap();
					hmap.put("area_code", killnull(rs.getString("area_code")) + "|" + killnull(rs.getString("area_name")));
					hmap.put("area_name", killnull(rs.getString("area_name")));
					alist.add(hmap);
				}
			}
			//ȡҵ���׼��
			stmt_call = conn.prepareCall("{call pack_flow_premiss.proc_para_gethz(?,?,?,?)}");
			stmt_call.setString(1,entcode );
			stmt_call.setString(2,tradecode);
			stmt_call.setString(3,tradetype);
			stmt_call.registerOutParameter(4, OracleTypes.VARCHAR); 
			stmt_call.execute();
			String area_hz = stmt_call.getString(4);
			sql = "select area_code,area_name from mag_area where area_code='"+area_hz+"'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				String area_code = rs.getString(1);
					String area_name = rs.getString(2);
					boolean match = false;
					for(int i=0;i<alist.size();i++)
					{
						HashMap map = (HashMap)alist.get(i);
						String mapname = (String)map.get("area_name");
						if (mapname!=null && mapname.equals(area_name))
						{
							match =true;
							break;
						}
					}
					if (match==false) {
						HashMap hmap = new HashMap();
						hmap.put("area_code", killnull(rs.getString("area_code")) + "|" + killnull(rs.getString("area_name")));
						hmap.put("area_name", killnull(rs.getString("area_name")));
						alist.add(hmap);
					}
			}
			
		} catch (TranFailException te) {
			throw te;
		} catch (Exception e) {
			throw new TranFailException("util_content_choosenextDao", "util_content_choosenextDao.getnextarea", e.getMessage());
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
		return alist;
	}
}
