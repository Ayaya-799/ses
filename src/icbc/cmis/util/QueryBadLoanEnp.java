package icbc.cmis.util;

import icbc.missign.Employee;
import java.sql.*;
import java.util.Hashtable;
import icbc.cmis.util.*;

/*************************************************************
 *
 * <b>��������: </b> 2004-11-08<br>
 * <b>����: </b><br>
 * <b>������:��ѯ�ͻ�����ͨ��ģʽ�Ի���������� </b><br>
 * <br>
 * <p>Copyright: Copyright (c)2004</p>
 * <p>Company: </p>
 *
 * @author ��С�
 *
 * @version 1.0.0
 *
 * @since
 *
 * @see
 *
 *************************************************************/

public class QueryBadLoanEnp extends QueryNormalEnp {//��ѯ�Ѿ���ɲ�������ͻ���������Ŀͻ�


	  public QueryBadLoanEnp() {
	  }

	  public String getWhere(Connection conn,Employee employee, Hashtable paras) {

		 String where = " ta200011011 in ( select distinct ta390001001 from ta390001 ) ";

		return where;
	  }

}