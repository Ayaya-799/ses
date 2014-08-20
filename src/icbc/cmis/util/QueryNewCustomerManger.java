package icbc.cmis.util;

import icbc.missign.Employee;
import java.sql.*;
import java.util.Hashtable;

/**
 * ���ܿͻ������еĲ�ѯ��������ѯ�ͻ���
 * ��Ҫʵ�֣��ͻ�״̬(5ȫ��)��������ɱ�־��������ѯ(2ȫ��)
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: icbc</p>
 * @author yxt
 * @version 1.1
 */

public class QueryNewCustomerManger extends QueryNormalAllEnp {

  public QueryNewCustomerManger() {
  }

  /**
   * ���ز�ѯ����
   * <p>modify: 12/9/2002 �ͻ�״̬���ӱ����Ͽͻ�4,ȫ����Ϊ5</p>
   * @param conn ���ݿ�����
   * @param employee ��ǰ��Ա
   * @param paras ��ѯ����
   * @return where�Ӿ�
   */
  public String getWhere(Connection conn, Employee employee, Hashtable paras) {
      String where = "and " + super.getWhere(conn,employee,paras);

      /*//���Ҵ���DA200011078
      String TA200011078 = (String)paras.get("TA200011078");
      if(TA200011078 != null && TA200011078.length() > 0) {
        where += (" and TA200011078 = '" + TA200011078 + "'");
      }
      //һ��ͻ����ص�ͻ�DA200011079
      String TA200011079 = (String)paras.get("TA200011079");
      if(TA200011079 != null && TA200011079.length() > 0) {
        where += (" and TA200011079 = " + TA200011079);
      }
      //������ҵDA200011080
      String TA200011080 = (String)paras.get("TA200011080");
      if(TA200011080 != null && TA200011080.length() > 0) {
        where += (" and TA200011080 = " + TA200011080);
      }*/
      //�ͻ�״̬ 1�����Ŵ���ϵ 2���� 3����(��ҵ��) 4���� 5����(�ѽ���)
      String TA200011059 = (String)paras.get("TA200011059");
      if(TA200011059 != null && TA200011059.length() > 0 && !TA200011059.equals("6")) {
        where += (" and TA200011059 = '" + TA200011059 + "'");
      }
      //��ɱ�־ 0 δ��� 1 ���
      String TA200011083 = (String)paras.get("TA200011083");
      if(TA200011083 != null && TA200011083.length() > 0 && !TA200011083.equals("2")) {
        where += (" and TA200011083 = " + TA200011083);
      }

    if(where.length() > 4 ) where = where.substring(4);
    return where;
  }
}