//��ѯ��ҵ��ģ����С�͵�,���Ѿ����й�������Ȩ�����õ���ҵ
package icbc.cmis.util;

import icbc.missign.Employee;
import java.sql.*;
import java.util.Hashtable;

public class QueryWeightDoneEnp extends QueryNormalEnp {

  public QueryWeightDoneEnp() {
  }

  public String getWhere(Connection conn,Employee employee, Hashtable paras) {
//     String where = super.getWhere(conn,employee,paras);
     String area_code = employee.getMdbSID();
     String where = "ta200011063='"+area_code+"' and ta200011083='1' and ta200011001 in (select distinct ma300004001 from ma300004)";
// and ta200011016 not in ('1060','2030','2060','3030','4030','5013','5014','5024','6011','6012','6013','6014','6420','7011','7012','7013','8011','8012','8013')
    return where;
  }
}
