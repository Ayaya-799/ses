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
 * @author
 * @version 1.0
 */

public class QueryAssurer extends QueryNormalEnp {

  public QueryAssurer() {
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
       //����ѡ������֧�У�����ѡ�ͻ�Ӧ�����û���ѡ�ĵ�����
       where += " and TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 = '" + area + "')";
     else if(bankflag.equals("3")||bankflag.equals("2"))
       //�����ѡ�����Ƕ����л�׼һ���У���ѡ�ͻ�Ӧ������ѡ�������¼���
       where += " and TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 in (select area_code from mag_area start with area_code = '" + area + "' connect by belong_bank = prior area_code))";
     //�����ѡ������һ���У���ѡ�ͻ�Ӧ������ѡ����������֧��
     else if(bankflag.equals("1"))
       where += " and TA200011001 in (select ta20001L001 from ta20001L where ta20001L002 in (select area_code from mag_area start with area_code = '" + area + "' connect by belong_bank = prior area_code))";

     //�����ѡ���������еģ�������������


     /*
       �����Ϊ�����������ͬѡ��֤�ˣ���Ҫ�����õȼ�ΪBBB������(��)��(������õȼ�Ϊδ��������ȡ
       �������õȼ�)�����ڿͻ����¼Ǳ����޴��µġ��ں����������޼�¼�ġ��ڿͻ��ӷ�ծ�����޼�¼��
     */
     if(!assuretype.equals("credit")){
        where += " and ta200011001 not in (select ta200016001 from ta200016 ) and ta200011001 not in (select ta200017001 from ta200017)";
        where += " and ta200011001 not in (select ta200018001 from ta200018) ";
        /*and ((ta200011040 <= '40' and ta200011040>'00') ";
        where += " or (ta200011040 = '00' and ta200011070 <='40' and ta200011070 >'00'))";*/
     }

      String hascreditrelation = "";

      if(paras.containsKey("hasCreditRelation"))
         hascreditrelation = (String)paras.get("hasCreditRelation");


      if(hascreditrelation.equals("yes"))
          where += " and ta200011059 in ('1','5') ";
      else
        //�ͻ�״̬Ϊ���������Ŵ���ϵ���������־����ɵ�
        where += " and ta200011059 in ('1','2','5')";

      where += " and ta200011083='1' and ta200011001 <> '"+enpcode+"' ";

      if(where.length()>0)
        where = where.substring(4);
      return where;
  }
}