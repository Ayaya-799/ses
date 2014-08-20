package icbc.cmis.util;

import icbc.missign.Employee;
import java.sql.*;
import java.util.Hashtable;

/**
 * <p>Title: </p>
 * <p>Description:���ݴ���ĵ������м���ѡ����ҵ����������ĺ���Ȩ����ҵ </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author ����ΰ д�� 20020709
 * @version 1.0
 */

public class QueryAllEnpByArea extends QueryNormalEnp {

  public QueryAllEnpByArea() {
  }

  public String getWhere(Connection conn,Employee employee,Hashtable paras) {
      String where = "";
      //�����м����where
      int eclass = Integer.valueOf(employee.getEmployeeClass()).intValue();
      String ecode = employee.getEmployeeCode();
//      int bankFlag = Integer.valueOf(employee.getBankFlag()).intValue();
//      String area = employee.getMdbSID();
      int bankFlag = Integer.valueOf((String)paras.get("bankFlag")).intValue();
      String area = (String)paras.get("areacode");
      switch (bankFlag) {
        case 0:
          break;
        case 4:
          where += (" and TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 = '" + area + "')");
          //���ݹ�Ա��where
//          if(eclass == 8) {
//            where += (" and TA200011001 in (select ta200012001 from ta200012 where ta200012003 = '" + ecode + "' or ta200012006 = '" + ecode + "')");
//          }
          break;
        default:
          where += (" and TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 in (select area_code from mag_area start with area_code = '" + area + "' connect by belong_bank = prior area_code))");
          break;
      }
    if(where.length() > 4 ) where = where.substring(4);
    return where;
  }
}