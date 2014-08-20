package icbc.cmis.util;
import icbc.missign.Employee;
import java.sql.*;
import java.util.Hashtable;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author     ���Ų���ͨ�ͻ���
 * @version 1.0
 */

public class MedalQueryAssurer extends QueryNormalEnp {

  public MedalQueryAssurer() {
  }

  public String getWhere(Connection conn,Employee employee, Hashtable paras) {
     String where = "";

     String area = (String)paras.get("assurearea");
     String assuretype = (String)paras.get("assuretype");
     String enpcode = (String)paras.get("enpcode");

     String bankflag = "4";
     if(paras.containsKey("bankFlag"))
       bankflag = (String)paras.get("bankFlag");

     if(bankflag.equals("4"))
       //����ѡ������֧�У�����ѡ��ҵӦ�����û���ѡ�ĵ�����
       where += " and TA200011063  = '" + area + "'";
     else if(bankflag.equals("3")||bankflag.equals("2"))
       //�����ѡ�����Ƕ����л�׼һ���У���ѡ��ҵӦ������ѡ�������¼���
       where += " and TA200011063 in (select area_code from mag_area start with area_code = '" + area + "' connect by belong_bank = prior area_code)";
     //�����ѡ������һ���У���ѡ��ҵӦ������ѡ����������֧��
     else if(bankflag.equals("1"))
       where += " and TA200011063 in (select area_code from mag_area start with area_code = '" + area + "' connect by belong_bank = prior area_code)";

     //�����ѡ���������еģ�������������


     /*
       �����Ϊ�����������ͬѡ��֤�ˣ���Ҫ�����õȼ�ΪBBB������(��)��(������õȼ�Ϊδ��������ȡ
       �������õȼ�)��������ҵ���¼Ǳ����޴��µġ��ں����������޼�¼�ġ�����ҵ�ӷ�ծ�����޼�¼��
     */
     if(!assuretype.equals("credit")){
        where += " and ta200011001 not in (select ta200016001 from ta200016 ) and ta200011001 not in (select ta200017001 from ta200017)";
        where += " and ta200011001 not in (select ta200018001 from ta200018) and ((ta200011040 <= '40' and ta200011040>'00') ";
        where += " or (ta200011040 = '00' and ta200011070 <='40' and ta200011070 >'00'))";
     }

      String hascreditrelation = "";

      if(paras.containsKey("hasCreditRelation"))
         hascreditrelation = (String)paras.get("hasCreditRelation");


      if(hascreditrelation.equals("yes"))
          where += " and ta200011059 = '1' ";
      else
        //��ҵ״̬Ϊ���������Ŵ���ϵ���������־����ɵ�
        where += " and ta200011059 in ('1','2')";

      where += " and ta200011083='1' and ta200011001 <> '"+enpcode+"' ";

      if(where.length()>0)
        where = where.substring(4);
      return where;
  }
}