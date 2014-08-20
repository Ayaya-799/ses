package icbc.cmis.util;

import java.util.Hashtable;

import icbc.cmis.base.TranFailException;
import icbc.cmis.base.genMsg;
import icbc.cmis.tags.PropertyResourceReader;
import icbc.missign.Employee;

/**
 * ***********************************************************
 * 
 * <b>��������: </b>2007-5-31 8:46:30<br>
 * <b>����:  Util_MuiQueryDAO.java</b><br>
 * <b>������: Util_MuiQueryDAO.java</b><br>
 * <br>
 * <p>Copyright:(c)2007</p>
 * <p>Company: ICBC</p>
 * 
 * @author κ�鲨
 * 
 * @version 1.0
 * 
 * @since 
 * 
 * @see
 */
public abstract class Util_MuiQueryDAO extends util_QueryDAO {

  /**���Ա�־*/
  protected String langCode = null;
  /**�����Ķ���*/
	protected PropertyResourceReader propertyResourceReader = null;

  public void genParameters(Employee employee, Hashtable paras) throws TranFailException {
    langCode = "zh_CN";
    try {
      langCode = employee.getLangCode();
    }
    catch (Exception e) {}

    propertyResourceReader = new PropertyResourceReader(langCode, this.getMuiDef());

    super.genParameters(employee, paras);

  }

  /**
   *  
   * Description  : ����˽��properties��·��
   * CreationDate : 2007-5-31 8:47:20
   * @author     : 
   *   
   * @return
   */
  protected abstract String getMuiDef();

  /**
   *  
   * Description  : ȡ����properties���ı�
   * CreationDate : 2007-5-31 8:47:15
   * @author     : 
   *   
   * @param id
   * @return
   */
  protected String getMuiPubStr(String id) {
    return propertyResourceReader.getPublicStr(id);
  }

  /**
   *  
   * Description  : ȡ˽��properties���ı�
   * CreationDate : 2007-5-31 8:47:30
   * @author     : 
   *   
   * @param id
   * @return
   */
  protected String getMuiPriStr(String id) {
    return propertyResourceReader.getPrivateStr(id);
  }

  /**
   *  
   * Description  : ȡ������,�޲���
   * CreationDate : 2007-5-31 10:06:59
   * @author     : 
   *   
   * @param errCode
   * @return
   */
  protected String getMuiErrMsg(String errCode) {
    return genMsg.getErrMsgByLang(langCode, errCode);
  }

  /**
   *  
   * Description  : ȡ������,�в���
   * CreationDate : 2007-5-31 10:07:03
   * @author     : 
   *   
   * @param errCode
   * @param paras
   * @return
   */

  protected String getMuiErrMsg(String errCode, String paras) {
    return genMsg.getErrMsgByLang(langCode, errCode, paras);
  }

}