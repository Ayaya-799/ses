package icbc.cmis.util;

import icbc.missign.*;
import java.sql.*;
import java.util.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 *
 * update date:2002-08-22
 * update content:�ͻ������ ''
 * update by WQQ
 *
 * update date:2002-08-29
 * update content:modify sEnpList���ӿͻ���ѯ����ʧ��
 * update by WQQ
 *
 * update date:2002-08-08
 * update content:modify where  TA200173002 in ()(��Ϊ���ͻ�����������������)
 * update by WQQ
 */

public class QueryAffiliatedEnp extends QueryNormalEnp {

  public QueryAffiliatedEnp() {
  }
  public String getWhere(Connection conn,Employee employee,Hashtable paras) {
    String TA200173003 = (String)paras.get("TA200173003");
    String where = "";
		String sEnpList = "'"+TA200173003 +"'";
    try{
			icbc.cmis.FK.CommonSqlTool sqlTool = new icbc.cmis.FK.CommonSqlTool(new icbc.cmis.operation.DummyOperation());
			sEnpList =  sEnpList + sqlTool.getAffiliatedEnp(TA200173003);
    }catch (Exception e){}

		//���� TA200173002�Ƿ�һ�� �ж��Ƿ�Ϊ�����ͻ�
    //where = " ta200011001 in (select TA200173003 from TA200173 where TA200173002 in (select distinct TA200173002 from TA200173 where TA200173003='"+TA200173003+"' and TA200173001='60'))";
		where = " ta200011001 in ("+sEnpList+")";
    //System.out.println(where);
    return where;
  }
}