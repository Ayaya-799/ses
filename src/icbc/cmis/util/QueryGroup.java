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
 *
 * update date:2003-04-15
 * update content:��ȥ��ӡ��Ϣ system.out.print()
 * update by WuQQ
 *
 * update date:2002-08-22
 * update content:��ȥ����end
 * update by WuQQ
 *
 * update date: 2002-08-20
 * update content����ѯ�ͻ�������������ta20001l��ȡֵ
 * updated by WuQQ
 */

public class QueryGroup extends QueryNormalEnp {

	public QueryGroup() {
	}
	public String getWhere(Connection conn,Employee employee,Hashtable paras) {
		String where = "";
		//���� TA200173001 �ж��Ƿ�Ϊ���ſͻ�
		//��1���������ͻ�����60�������ſͻ�
		where = "ta200011001 in (select ta200173003 from ta200173 where  TA200173004='1' and TA200173001='60')";

	 String area = (String)paras.get("area");
	 String bankflag = (String)paras.get("bankFlag");
	 if(!bankflag.equals("0")) where += " and ";
	 if(bankflag.equals("4"))
		 //����ѡ������֧�У�����ѡ�ͻ�Ӧ�����û���ѡ�ĵ�����
		 where += " TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 = '" + area + "')";
		 //where += " TA200011063  =  '"+area+"' ";
	 else if(bankflag.equals("3")||bankflag.equals("2"))
		 //�����ѡ�����Ƕ����л�׼һ���У���ѡ�ͻ�Ӧ������ѡ�������¼���
		 where += " TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 in (select area_code from mag_area start with area_code = '" + area + "' connect by belong_bank = prior area_code))";
		 //where += " TA200011063 in (select area_code from mag_area where belong_bank='"+area+"')";
	 //�����ѡ������һ���У���ѡ�ͻ�Ӧ������ѡ����������֧��
	 else if(bankflag.equals("1"))
		 where += " TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 in (select area_code from mag_area start with area_code = '" + area + "' connect by belong_bank = prior area_code))";
		 //where += " TA200011063 in (select area_code from mag_area where belong_bank in (select area_code from mag_area where belong_bank ='"+area+"'))";
	 //�����ѡ���������еģ�������������

		//�ͻ�״̬Ϊ�����Ŵ���ϵ���������־����ɵ�
		where += " and ta200011059 = '1'";
		where += " and ta200011083='1'";

		//System.out.println(where);
		return where;
	}
}