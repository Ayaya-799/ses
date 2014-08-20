/*
 * �������� 2006-3-1
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package icbc.cmis.flow.util;
import java.util.*;
import icbc.cmis.operation.*;
import icbc.cmis.second.pkg.*;
import icbc.cmis.base.*;  
/**
 * @author ֣�ڱ�
 * ����-��ʾ���ֶ���Ϣ
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class util_showcontent_flowOp extends icbc.cmis.operation.CmisOperation {
	public util_showcontent_flowOp() {
			super();
	}
	private String webBasePath =
				(String) CmisConstance.getParameterSettings().get("webBasePath");
	util_content_flowDao dao = null;
	public void execute() throws Exception, icbc.cmis.base.TranFailException {
		String action = getStringAt("opAction");
		try {
			//��ѯ��������
			if (action.equals("20001")) {
				queryshowcontent();
				};

		}

		catch (Exception ex) {
			setOperationDataToSession();
			throw new TranFailException("dzypjBB0001", "EBAlreadyApprove.execute()", ex.getMessage(), "����������Ϣ����");
		}
		
	}
	
/**
	* <b>��������: </b><br>
	* <p>��ѯ��������</p>
	* @throws TranFailException
	*  
	*/
	private void queryshowcontent() throws TranFailException {
		
		
		String formflag =getStringAt("formflag");  //��ѯ���־  1,���˵�� 2,������������������������ 3.��������
		String entcode =getStringAt("entcode");   //�ͻ�����
		String tradecode =getStringAt("tradecode");//ҵ�������
		String flowtype =getStringAt("flowtype");  //��������
		String xh =getStringAt("xh");  //���
		String step =getStringAt("step"); //����(��Ҫ���������Ƿ��ǵ�����)

		 

		dao = new util_content_flowDao(this);
		String returnURL = "";
		Vector contentlist = new Vector();

		try {
			contentlist = dao.getcontenttxt(formflag, entcode, tradecode,flowtype,xh,step);
			this.setFieldValue("contentlist", contentlist);

			if (formflag.equals("2")  || (step.equals("������")))
			 { 
			 setReplyPage("/icbc/cmis/flow/util/util_showcontentforsearcher_flow.jsp");
			 }
			 else
			 {
			 this.setFieldValue("formflag", formflag);
			 setReplyPage("/icbc/cmis/flow/util/util_showcontent_flow.jsp");
			 }
			this.setOperationDataToSession();
		}
		catch (TranFailException e) {
			throw e;
		}
		catch (Exception e) {
			throw new TranFailException("util_showcontent_flowOp", "EBAlreadyApprove.queryshowcontent", e.getMessage());
		}
	}
	

}
