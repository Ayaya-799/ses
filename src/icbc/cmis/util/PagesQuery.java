package icbc.cmis.util;

import java.sql.*;
import icbc.missign.*;
import java.util.*;
import icbc.cmis.util.Decode;

public class PagesQuery extends util_QueryDAO {

  public PagesQuery() {
  }

  /**
   * ���û���ҳ��
   * @return int����ҳ��
   */
  public int getBufferPages() {
    return 5;
  }

  /**
   * ����ÿҳ��ʾ����
   * @return intÿҳ��ʾ����
   */
  public int getPageLines() {
    return 20;
  }

  /**
   * ���ز�ѯ����ֶθ���
   * @return int �ֶθ���
   */
  public int getFieldNumber() {
    return 4;
  }

  /**
   * ���ز�ѯ����������ֶ�����, ""��ʾ����ʾ
   * @return String[]
   */
  public String[] getFieldNames() {
    String[] ret = {"���","�ͻ�����","�ͻ�ȫ��",""};
    return ret;
  }

  /**
   * ���ظ��ֶ���ʾ��ȣ�-1��ʾ��ָ�����, 0��ʾ����ʾ
   * @return ���ֶ���ʾ��� �� null
   */
  public int[] getFieldsWidth() {
//    int[] ret = {-1,-1,-1,-1};
    int[] ret = {50,100,400,0};
    return ret;
  }

  /**
   * ָ��������ʾȷ�ϵ��ֶΣ�1��ʾ��ʾ���õ����ֶ�
   * @return ��ʾȷ�ϵ��ֶ�
   */
  public int[] getAskFields(){
    int[] ret = {0,1,0,1};
    return ret;
  }

  /**
   * ���ö��뷽ʽ��0����� 1�Ҷ���
   * @return ���뷽ʽ
   */
  public int[] getFieldsAlign() {
    int[] ret = {0,1,0,0};
    return ret;
  }

  /**
   * ������ѯ���������SQL���
   * �������� select count(*) from ��ʼ
   * �����ô�������ݿ����ӡ���Ա���Զ��������֯SQL���
   * @param conn  ��ǰ���õ����ݿ�����
   * @param employee ��ǰ��Ա
   * @param paras �û������Զ������
   * @return SQL���
   * @throws Exception
   */
  public String getCountSQL(Connection conn, Employee employee, Hashtable paras) throws Exception {
    return "select count(*) from TA200011 where TA200011004 like ?";
  }

  /**
   * ������ѯSQL��䣬
   * ��佨��������½ṹ֮һ��<br>
   * SELECT * FROM ( SELECT ROWNUM rnum,a.* FROM ( YOUR_QUERY_GOES_HERE -- including the order by ) a WHERE ROWNUM &lt;= ?) WHERE rnum &gt;= ?
   * select * from (select RANK() OVER (ORDER BY ����) rnk,���� FROM ����) WHERE rnk &lt;= ? AND rnk &gt;= ? <br>
   * �����ô�������ݿ����ӡ���Ա���Զ��������֯SQL���
   * @param conn  ��ǰ���õ����ݿ�����
   * @param employee ��ǰ��Ա
   * @param paras �û������Զ������
   * @throws Exception
   * @return SQL���
   */
  public String getQuerySQL(Connection conn, Employee employee, Hashtable paras) throws Exception {
    return "SELECT * FROM ( SELECT ROWNUM rnum,a.* FROM (select TA200011001,TA200011003,TA200011004 FROM TA200011 where TA200011004 like ?) a WHERE ROWNUM <= ?) WHERE rnum >= ?";
//    return "select * from (select RANK() OVER (ORDER BY TA200011001) rnk,TA200011001,TA200011003,TA200011004 FROM TA200011 where TA200011004 like ?) WHERE rnk <= ? AND rnk >= ?";
  }

  /**
   * �󶨲�ѯ������ע����SQL�����
   * @param notCountQuery ������ѯʱΪtrue,���ݲ�ѯʱΪfalse
   * @param pstmt PreparedStatement
   * @param paras �Զ��������
   * @param begin ��ʼ˳���
   * @param end ��ֹ˳���
   * @throws Exception
   */
  public void setParameters(boolean notCountQuery,PreparedStatement pstmt,Hashtable paras,int begin, int end)  throws Exception {
    String ts = "%"+Decode.decode((String)paras.get("cname"))+"%";
    pstmt.setString(1,ts);
    if(notCountQuery) {
      pstmt.setInt(2,end);
      pstmt.setInt(3,begin);
    }
  }
}