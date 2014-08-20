package icbc.cmis.util;

import java.util.*;
import icbc.cmis.operation.*;
import icbc.cmis.base.*;

/**
 * <p>Title: F-CM2002�ʲ�����ϵͳ</p>
 * <p>Description: ��ѯ�ͻ���������õȼ���Ҫ��</p>
 * <p>create for �ʽ���������Ŀ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ICBC HZ </p>
 * @author zheng ze zhou
 * @version 1.0
 * modify by zheng ze zhou
 * modify date 2004-7-16
 * modify reason:Ϊ�˷����ܵĵ��ã�ȥ���˼̳�CmisOperation�ķ�ʽ
 */

public class QueryEnpCharacter  {

  public QueryEnpCharacter() {

  }
  /**
   * <p>��ѯ�ͻ���������õȼ�(���������õȼ�Ϊδ������ȡ�������õȼ�)</p>
   * <p>������õȼ� TA200011040���������õȼ� TA200011070 </p>
   * <p>д�ɾ�̬�������ڵ���</p>
   * <p>Example:
   * @param String enpcode �ͻ�����
   * @return String creditLevel ���õȼ�����
   * @throws TranFailException
   */
  public static String getCreditLevel(String enpcode) throws icbc.cmis.base.TranFailException{
    try {
      //define
      String retlevel = "?";
      Hashtable tmph;
      QueryEnpCharacterDAO dao;
      //values : ��ֵ dao���Ѿ������˲��ḳnull
      dao = new QueryEnpCharacterDAO(new icbc.cmis.operation.DummyOperation());
      tmph = dao.queryEnpRecord(enpcode);
      retlevel = (String)tmph.get("TA200011040"); //��ҵ������õȼ�
      if (retlevel!=null&&retlevel.equals("00")) { //δ����
        retlevel = (String)tmph.get("TA200011070"); //�������õȼ�
      }
      if (retlevel.equals("")||retlevel.equals("00")) { //�������õȼ�Ϊ�ջ�00ʱ������90 Date 2004-9-3
        retlevel = "90";
      }
      return retlevel;
    } catch (TranFailException e) {
      throw e;
    } catch (Exception e) {
      //handler your exception
      throw new TranFailException("QueryEnpCharacter001", "icbc.cmis.util.QueryEnpCharacter.getCreditLevel()",
                                   e.getMessage(),"�޷������ҵ���õȼ�");
    }
  }
}
