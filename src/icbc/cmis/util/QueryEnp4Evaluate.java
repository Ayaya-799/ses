package icbc.cmis.util;
import icbc.missign.Employee;
import java.sql.*;
import java.util.Hashtable;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class QueryEnp4Evaluate extends QueryNormalEnp {

  public QueryEnp4Evaluate() {

  }

  public String getWhere(Connection conn,Employee employee, Hashtable paras) {
     String where = "";
     String tt=null;

     String areaCode = employee.getMdbSID();
     String employeeCode = employee.getEmployeeCode();
     String employeeClass = (String)employee.getEmployeeClass();

     switch (Integer.parseInt(employeeClass)) {
        case 6://�쵼
         where ="ta200011010 not in ('9','11') and ta200011063='"+areaCode+"'  and ta200011059='1' and ta200011001 in ( select ma300004001 from ma300004 where ma300004002='"+employeeCode+"' )";
         break;
        case 7:
        case 8:
         where =" ta200011063='"+areaCode+"' and ta200011010 not in ('9','11')  and ta200011059='1' and  ta200011001 in (select ta200012001 from ta200012  where ta200012005='1' and ta200012006='"+ employeeCode +"' union all select ta200012001 from ta200012  where ta200012005='1' and ta200012003='"+employeeCode +"' and ta200012006 is null )";
         break;
        default ://�ڶ����Ա�����Բ�ѯ������������ҵ
         where ="ta200011010 not in ('9','11') and ta200011063='"+areaCode+"'  and ta200011059='1' ";
           break;
      }

			//�������̺�������ѯʹ��
		  String ifProcessCompleted = null;
			try{
				ifProcessCompleted = (String)paras.get("ifProcessCompleted");
			}catch (Exception e){}
			if (ifProcessCompleted!=null && ifProcessCompleted.equals("yes")){
					where += " and ta200011001 in (select TA300003001 from ta300003 where TA300003001=ta200011001 and TA300003016='05') ";
			}else if (ifProcessCompleted!=null && ifProcessCompleted.equals("no")){
					where += " and ta200011001 in (select TA300003001 from ta300003 where TA300003001=ta200011001 and TA300003016<>'05') ";
			}
      return where;
  }
}