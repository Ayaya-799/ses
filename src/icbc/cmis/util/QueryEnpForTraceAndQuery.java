package icbc.cmis.util;
import icbc.missign.Employee;
import icbc.cmis.util.QueryNormalEnp;
import java.sql.Connection;
import java.util.Hashtable;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author: WuQQ
 * @version 1.0
 * date: 2003-01-14
 *
 * updated by WuQQ,20030327
 * ����Ա�����ǵ�����С���Ա���ǲ���Ա����������Ĳ���ԱȨ���������С���Ա���ԣ����Բ�ѯ���������������Ŀ�����������Ǳ���������Ͻ����ҵ
 * ����ǲ���Ա����Ҫ��ta20001L001�����ж�,
 *
 * updated by WuQQ,20030313
 * ����Ա�����ǵ�����С���Ա���ǲ���Ա����������Ĳ���ԱȨ���������С���Ա���ԣ����Բ�ѯ���������������Ŀ����������������Ͻ����ҵ
 * Ӧ����Ҫ��20030313�޸�
 *
 * updated by WuQQ,20030221
 * ������ĵ������벻��֧��ʱ��Ӧ�ò�ѯ������Ͻ���пͻ�
 */

public class QueryEnpForTraceAndQuery extends QueryNormalEnp {

	public QueryEnpForTraceAndQuery() {

	}

	public String getWhere(Connection conn,Employee employee, Hashtable paras) {
		 String where = "";

		 String areaCode = employee.getMdbSID();
		 if(paras.containsKey("areaCode")){
				areaCode = (String)paras.get("areaCode");
			}

		 String employeeCode = employee.getEmployeeCode();
		 String employeeClass = (String)employee.getEmployeeClass();

		 //�Ƿ�����������ѧУ��ҽԺ��ҵ
		 where = " ta200011010 not in ('8','9','11')";
		 //where ="ta200011014 not in ('L8510','L8520','L8530','L8540','L8550','L8560','L8590','M8910','M8920','M8930','M8940','M8950','M8990') ";

		 //Ҫ�����Ŵ���ϵ
		 where += " and ta200011059='1' ";
		 //���������  --����
		 //where += " and ta200011083='1'";
		 switch (Integer.parseInt(employeeClass)) {
				//updated by WuQQ,20030327
				//����Ƿǲ���Ա,����������������ж�
				case 5://����Ա
				case 6://�쵼
				case 7://�ۺ�Ա
				 //Ҫ����Ͻ�ͻ�
				//updated by WuQQ,20030221
				//where += " and TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 = '" + areaCode + "')";
				where += " and TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 in (";
				where += " select area_code from mag_area start with area_code = '" + areaCode + "' connect by prior area_code =  belong_bank))";
				break;

				//����ԱҪ�ǹܻ����й�,�������޸�Ϊ��
				//����Ա�����ǵ�����С���Ա���ǲ���Ա����������Ĳ���ԱȨ���������С���Ա���ԣ����Բ�ѯ���������������Ŀ����������������Ͻ����ҵ
				//Ӧ����Ҫ��20030313�޸�
				case 8:
				 //where +=" and ta200011001 in (select ta200012001 from ta200012  where ta200012005='1' and ( ta200012006='"+ employeeCode +"' or ( ta200012003='"+employeeCode +"' and ta200012006 is null )))";
				 where += " and ta200011001 in "
									+" ("
									+"(select distinct TA350017001 from TA350017 where TA350017002 in (select  project_code from mag_group_350 where employee_code='"+employeeCode+"' ))"
									+" union"
									//updated by WuQQ,20030327
									//+" (select ta200012001 from ta200012  where ta200012005='1' and ( ta200012006='"+ employeeCode +"' or ( ta200012003='"+employeeCode +"' and ta200012006 is null )))"
									+" select ta200012001 from ta200012 where ta200012005='1' and  ta200012006='"+ employeeCode +"' "
									+" union"
									+" select ta200012001 from ta200012 where ta200012003='"+ employeeCode +"' and ta200012006 is null"
									+" )";
				 break;
				default :
					 break;
			}

			//�������̺�������ѯʹ��
			String ifProcessCompleted = null;
			if(paras.containsKey("ifProcessCompleted")){
							ifProcessCompleted = (String)paras.get("ifProcessCompleted");
			}
			//try{
							//ifProcessCompleted = (String)paras.get("ifProcessCompleted");
			//}catch (Exception e){}
			if (ifProcessCompleted!=null && ifProcessCompleted.equals("yes")){
											//99 �����������
											//where += " and ta200011001 in (select TA350012001 from TA350012 where TA350012031='99' ) ";
											where += " and ta200011001 in (select TA350012001 from TA350012) ";
			}else if (ifProcessCompleted!=null && ifProcessCompleted.equals("no")){
											//where += " and ta200011001 in (select TA300003001 from ta300003 where TA300003001=ta200011001 and TA300003016<>'05') ";
											where += " and ta200011001 in (select TA350012001 from TA350012 where TA350012031<>'99' ) ";
			}
			return where;
	}
}