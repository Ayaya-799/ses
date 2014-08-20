package icbc.cmis.flow.BA;

import java.sql.*;
import java.util.*;

import oracle.jdbc.*;
import icbc.cmis.service.CmisDao;
import icbc.cmis.base.*;

/**
 * �����Ĵ������б��ѯ
 * 
 * @author zjfh-zhangyz
 * 
 * ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */

public class BA_approvelistDao extends CmisDao {

	public static String sLangCode = "zh_CN";

	public BA_approvelistDao(icbc.cmis.operation.CmisOperation op) {
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
	 * ��ѯ�����˴����б�
	 * 
	 * @param employeecode
	 *            ��Ա��
	 * @param empareacode
	 *            ��Ա��������
	 * @param ordercode
	 *            ��ǰ���ڴ���
	 * @param runproc
	 *            �Ƿ���ô洢���̲�ѯδ������ҵ��0��1��
	 * @return ��ѯ����б�
	 * @throws TranFailException
	 */
	public ArrayList querylist_me(String employeecode, String empareacode,
			String ordercode, String runproc, String busitype)
			throws TranFailException {
		ArrayList vret = new ArrayList();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		String sql = null;
		try {
			this.getConnection("cmis3");
            ArrayList alist = new ArrayList();
            sql = "select def01,def02 from mag_approve_def where def_type =? order by to_number(def_order)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, busitype);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	HashMap hmap = new HashMap();
            	hmap.put("runsql", rs.getString(1));
            	hmap.put("runsqlbundlestr", rs.getString(2));
            	alist.add(hmap);
            }
            rs.close();
            if (alist.size()!=0) {
				//��ѯ���̱�
				sql = "select rownum rnum,process001,ta200011003 process001_name,process002,"
						+ "process003,kind_name process003_name, "
						+ "process004,pa002 process004_name, "
						+ "process005,process009 "
						+ "from mprocess_new, ta200011, imag_kind, ipa200021 "
						+ "where ta200011001 = process001 and kind_code = process003 and pa001 = process004 "
						+ "and process004 in(select distinct pa001 from ipa200021 where pa004 = ? ) "
						+ "and process006 = ? and process007 = ? and process008 = ? and ( process009 = '0' or process009 = '1' or process009 = '2') and imag_kind.LANG_CODE=? and ipa200021.LANG_CODE=? "
						;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, busitype);
				pstmt.setString(2, ordercode);
				pstmt.setString(3, empareacode);
				pstmt.setString(4, employeecode);
				pstmt.setString(5, this.sLangCode);
				pstmt.setString(6, this.sLangCode);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					HashMap hmap = new HashMap();
					hmap.put("rnum", killnull(rs.getString("rnum")));
					hmap.put("process001", killnull(rs.getString("process001"))); //�ͻ���
					hmap.put("process001_name", killnull(rs
							.getString("process001_name"))); //�ͻ�����
					hmap.put("process002", killnull(rs.getString("process002"))); //�����
					hmap.put("process003", killnull(rs.getString("process003"))); //�����������
					hmap.put("process003_name", killnull(rs
							.getString("process003_name"))); //��������
					hmap.put("process004", killnull(rs.getString("process004"))); //�����������
					hmap.put("process004_name", killnull(rs
							.getString("process004_name"))); //��������
					hmap.put("process005", killnull(rs.getString("process005"))); //���
					hmap.put("process009", killnull(rs.getString("process009"))); //����״̬
					vret.add(hmap);
				}
            } else {
            	HashMap hmapvar = new HashMap();
            	hmapvar.put("busitype", busitype);
            	hmapvar.put("ordercode", ordercode);
            	hmapvar.put("empareacode", empareacode);
            	hmapvar.put("langcode", this.sLangCode);
            	hmapvar.put("employeecode", employeecode);
            	for(int i=0;i<alist.size();i++){
            		HashMap hmapsql = (HashMap)alist.get(i);
            		String runsql = (String)hmapsql.get("runsql");
            		String runsqlbundlestr = (String)hmapsql.get("runsqlbundlestr");
            		if (runsql.equals("")) {
            			continue;
            		}
            		pstmt = conn.prepareStatement(runsql);
            		int j=1;
            		StringTokenizer token = new StringTokenizer(runsqlbundlestr,"|");
            		while (token.hasMoreTokens()) {
            			pstmt.setString(j++, (String)hmapvar.get(token.nextToken()));
            		}
            		rs = pstmt.executeQuery();
            		while (rs.next()) {
    					HashMap<String,String> hmap = new HashMap<String,String>();
    					hmap.put("rnum", killnull(rs.getString("rnum")));
    					hmap.put("process001", killnull(rs.getString("process001"))); //�ͻ���
    					hmap.put("process001_name", killnull(rs
    							.getString("process001_name"))); //�ͻ�����
    					hmap.put("process002", killnull(rs.getString("process002"))); //�����
    					hmap.put("process003", killnull(rs.getString("process003"))); //�����������
    					hmap.put("process003_name", killnull(rs
    							.getString("process003_name"))); //��������
    					hmap.put("process004", killnull(rs.getString("process004"))); //�����������
    					hmap.put("process004_name", killnull(rs
    							.getString("process004_name"))); //��������
    					hmap.put("process005", killnull(rs.getString("process005"))); //���
    					hmap.put("process009", killnull(rs.getString("process009"))); //����״̬
    					vret.add(hmap);
            		}
            		rs.close();
            	}
            	
            }
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approvelistDao.querylist_me()", e.getMessage(), e
							.getMessage());
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
		return vret;
	}

	/**
	 * ��ѯ�����д���������б�
	 * 
	 * @param empareacode
	 *            ��Ա��������
	 * @param employeecode
	 *            ��Ա����
	 * @param ordercode
	 *            ��ǰ���ڴ���
	 * @return
	 * @throws TranFailException
	 */
	public ArrayList querylist_us(String empareacode, String employeecode,
			String ordercode, String runproc, String busitype)
			throws TranFailException {
		ArrayList vret = new ArrayList();
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement pstmt = null;
		java.sql.CallableStatement stmt_call = null;
		String sql = null;
		try {
			this.getConnection("cmis3");
            ArrayList alist = new ArrayList();
            sql = "select def03,def04 from mag_approve_def where def_type =? order by to_number(def_order)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, busitype);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	HashMap hmap = new HashMap();
            	hmap.put("runsql", rs.getString(1));
            	hmap.put("runsqlbundlestr", rs.getString(2));
            	alist.add(hmap);
            }
            rs.close();

            if (alist.size()==0) {
				//��ѯ���̱�
				sql = "select rownum rnum,process001,ta200011003 process001_name,process002,"
						+ "process003,kind_name process003_name, "
						+ "process004,pa002 process004_name, "
						+ "process005,process009 "
						+ "from mprocess_new, ta200011, imag_kind, ipa200021 "
						+ "where ta200011001 = process001 and kind_code = process003 and pa001 = process004 "
						+ "and process004 in(select distinct pa001 from ipa200021 where pa004 = ? ) "
						+ "and process006 = ? and process007 = ? and (process008 is null or process008='') and process009 = '0' and imag_kind.LANG_CODE=? and ipa200021.LANG_CODE=? "
						;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, busitype);
				pstmt.setString(2, ordercode);
				pstmt.setString(3, empareacode);
				pstmt.setString(4, this.sLangCode);
				pstmt.setString(5, this.sLangCode);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					HashMap hmap = new HashMap();
					hmap.put("rnum", killnull(rs.getString("rnum")));
					hmap.put("process001", killnull(rs.getString("process001"))); //�ͻ���
					hmap.put("process001_name", killnull(rs
							.getString("process001_name"))); //�ͻ�����
					hmap.put("process002", killnull(rs.getString("process002"))); //�����
					hmap.put("process003", killnull(rs.getString("process003"))); //�����������
					hmap.put("process003_name", killnull(rs
							.getString("process003_name"))); //��������
					hmap.put("process004", killnull(rs.getString("process004"))); //�����������
					hmap.put("process004_name", killnull(rs
							.getString("process004_name"))); //��������
					hmap.put("process005", killnull(rs.getString("process005"))); //���
					hmap.put("process009", killnull(rs.getString("process009"))); //����״̬
					vret.add(hmap);
				}
            }else {
            	HashMap hmapvar = new HashMap();
            	hmapvar.put("busitype", busitype);
            	hmapvar.put("ordercode", ordercode);
            	hmapvar.put("empareacode", empareacode);
            	hmapvar.put("langcode", this.sLangCode);
            	hmapvar.put("employeecode", employeecode);
            	for(int i=0;i<alist.size();i++){
            		HashMap hmapsql = (HashMap)alist.get(i);
            		String runsql = (String)hmapsql.get("runsql");
            		String runsqlbundlestr = (String)hmapsql.get("runsqlbundlestr");
            		if (runsql.equals("")) {
            			continue;
            		}
            		pstmt = conn.prepareStatement(runsql);
            		int j=1;
            		StringTokenizer token = new StringTokenizer(runsqlbundlestr,"|");
            		while (token.hasMoreTokens()) {
            			pstmt.setString(j++, (String)hmapvar.get(token.nextToken()));
            		}
            		rs = pstmt.executeQuery();
            		while (rs.next()) {
    					HashMap<String,String> hmap = new HashMap<String,String>();
    					hmap.put("rnum", killnull(rs.getString("rnum")));
    					hmap.put("process001", killnull(rs.getString("process001"))); //�ͻ���
    					hmap.put("process001_name", killnull(rs
    							.getString("process001_name"))); //�ͻ�����
    					hmap.put("process002", killnull(rs.getString("process002"))); //�����
    					hmap.put("process003", killnull(rs.getString("process003"))); //�����������
    					hmap.put("process003_name", killnull(rs
    							.getString("process003_name"))); //��������
    					hmap.put("process004", killnull(rs.getString("process004"))); //�����������
    					hmap.put("process004_name", killnull(rs
    							.getString("process004_name"))); //��������
    					hmap.put("process005", killnull(rs.getString("process005"))); //���
    					hmap.put("process009", killnull(rs.getString("process009"))); //����״̬
    					vret.add(hmap);
            		}
            		rs.close();
            	}
            }
			//���ô洢���̲�ѯ
			if (runproc.equals("1")) {
				//��ѯ�ӿ�
				sql = "select interface001 from mag_approve_interface "
						+ "where tradecode in(select distinct pa001 from ipa200021 where pa004 = ? ) "
						+ "and interface001 is not null";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, busitype);
				rs = pstmt.executeQuery();

				HashMap interfacemap = new HashMap();
				int icount = 1;
				while (rs.next()) {
					interfacemap.put(icount++ + "", rs
							.getString("interface001"));
				}
				int isize = interfacemap.size();
				int countbegin = vret.size() + 1;

				//��һ���ýӿ�
				for (int i = 1; i <= isize; i++) {
					stmt_call = conn.prepareCall("{call "
							+ (String) interfacemap.get(i + "")
							+ "(?,?,?,?,?)}");
					String temo = (String) interfacemap.get(i + "");
					stmt_call.setString(1, empareacode); //������
					stmt_call.setString(2, employeecode); //��Ա��
					stmt_call.registerOutParameter(3, OracleTypes.CURSOR); //�����α�
					stmt_call.registerOutParameter(4, OracleTypes.VARCHAR); //���ر�־,0��ȷ����1ʧ��
					stmt_call.registerOutParameter(5, OracleTypes.VARCHAR); //������Ϣ
					stmt_call.execute();
					rs = (ResultSet) stmt_call.getObject(3);
					String str_flag = stmt_call.getString(4);
					String str_msg = stmt_call.getString(5);
					while (rs.next()) {
						HashMap hmap = new HashMap();
						hmap.put("rnum", countbegin++ + "");
						hmap.put("process001", killnull(rs.getString("a1"))); //�ͻ���
						hmap.put("process001_name",
								killnull(rs.getString("a2"))); //�ͻ�����
						hmap.put("process002", killnull(rs.getString("a3"))); //�����
						hmap.put("process003", killnull(rs.getString("a4"))); //�����������
						hmap.put("process003_name",
								killnull(rs.getString("a5"))); //��������
						hmap.put("process004", killnull(rs.getString("a6"))); //�����������
						hmap.put("process004_name",
								killnull(rs.getString("a7"))); //��������
						hmap.put("process005", "1"); //���
						hmap.put("process009", "-1"); //����״̬��Ĭ����Ϊδ��������
						vret.add(hmap);
					}
					rs.close();
				}
			}
		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			throw new TranFailException("icbc.cmis.flow.BA",
					"BA_approvelistDao.querylist_us()", e.getMessage(), e
							.getMessage());
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
			if (stmt_call != null)
				try {
					stmt_call.close();
				} catch (Exception ex) {
				}
			closeConnection();
		}
		return vret;
	}

	/**
	 * ������״̬��飽��д�������У��
	 * 
	 * @param i_cuscode
	 *            �ͻ���
	 * @param i_yewucode
	 *            ҵ���
	 * @param i_areacode
	 *            ��ǰ����
	 * @param i_employee
	 *            ��ǰ������
	 * @param i_nowphase
	 *            ��ǰ����
	 * @param i_yewuflow
	 *            ��ǰ��������
	 * @return
	 * @throws TranFailException
	 */
	public HashMap checkapply(String i_cuscode, String i_yewucode,
			String i_areacode, String i_employee, String i_nowphase,
			String i_yewuflow) throws TranFailException {
		HashMap hmap = new HashMap();
		java.sql.CallableStatement stmt_call = null;
		String sql = null;
		try {
			this.getConnection("cmis3");
			//����
			stmt_call = conn
					.prepareCall("{call pack_ctrl_public.proc_ctrl_nowrite"
							+ "(?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt_call.setString(1, i_cuscode);
			stmt_call.setString(2, i_yewucode);
			stmt_call.setString(3, i_areacode);
			stmt_call.setString(4, i_employee);
			stmt_call.setString(5, i_nowphase);
			stmt_call.setString(6, i_yewuflow);
			stmt_call.registerOutParameter(7, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(8, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(10, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(11, OracleTypes.VARCHAR);
			stmt_call.registerOutParameter(12, OracleTypes.VARCHAR);
			stmt_call.execute();
			String o_opinion = stmt_call.getString(7); //�������0������1����2��ʾ-1�������
			String o_firmctl = stmt_call.getString(8); //���Կ���˵��
			String o_softctl = stmt_call.getString(9); //��ʾ��Ϣ˵��
			String o_firmold = stmt_call.getString(10); //���Կ���˵��
			String o_softold = stmt_call.getString(11); //��ʾ��Ϣ˵��
			String o_stoparea = stmt_call.getString(12); //ͣ��������

			//hmap.put("o_opinion", o_opinion);

			stmt_call = conn.prepareCall("{call pack_explaincode.proc_analyze"
					+ "(?,?,?,?,?,?,?,?)}");
			stmt_call.setString(1, o_firmctl);
			stmt_call.setString(2, o_softctl);
			stmt_call.setString(3, o_firmold);
			stmt_call.setString(4, o_softold);
			stmt_call.registerOutParameter(5, Types.VARCHAR);
			stmt_call.registerOutParameter(6, Types.VARCHAR);
			stmt_call.registerOutParameter(7, OracleTypes.CURSOR);
			stmt_call.registerOutParameter(8, OracleTypes.CURSOR);
			stmt_call.execute();
			String o_flag = stmt_call.getString(5); //���ر�־-1��������0�ɹ�
			String o_msg = stmt_call.getString(6);
			java.sql.ResultSet o_ret1 = (ResultSet) stmt_call.getObject(7);
			java.sql.ResultSet o_ret2 = (ResultSet) stmt_call.getObject(8);
			StringBuffer sbf_msg = new StringBuffer();
			if (o_flag.equals("0")) {
				while (o_ret1.next()) {
					sbf_msg.append(o_ret1.getString(1));
					sbf_msg.append(";");
				}
				while (o_ret2.next()) {
					sbf_msg.append(o_ret2.getString(1));
					sbf_msg.append(";");
				}
				hmap.put("o_opinion", o_opinion);
				hmap.put("o_message", sbf_msg.toString());
			} else {
				hmap.put("o_opinion", "-1");
				hmap.put("o_message", o_msg);
			}
			o_ret1.close();
			o_ret2.close();

		} catch (TranFailException e) {
			throw e;
		} catch (Exception e) {
			hmap.put("o_opinion", "-1");
			hmap.put("o_message", e.getMessage());
		} finally {
			if (stmt_call != null)
				try {
					stmt_call.close();
				} catch (Exception ex) {
				}
			closeConnection();
		}
		return hmap;
	}

}