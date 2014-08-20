/*
 * �������� 2006-3-2
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package icbc.cmis.flow.util;
import oracle.jdbc.driver.*;
import icbc.cmis.service.CmisDao;
import icbc.cmis.operation.*;
import java.util.*;
import java.io.IOException;
import java.sql.*;
import icbc.cmis.base.TranFailException;
import icbc.cmis.util.DBTools;
/**
 * Title:
 * Description: ��ѯ�������̱��о�������
 * Copyright:    Copyright (c) 2005
 * Company:icbcsdc
 * @author��֣�ڱ�
 * @version 1.0
 */
public class util_showcontent_flowDAO extends CmisDao{
	public util_showcontent_flowDAO(CmisOperation op) {
		super(op);
		}
	
	/**
	 * ��������: 
	 * @param formflag ��ѯ���־  1,���˵�� 2,������������������������ 3.��������
	 * @param  entcode   //�ͻ�����
	 * @param tradecode //ҵ�������
	 * @param flowtype  //��������
	 * @param xh  //���
	 * @param step  //����(��Ҫ���������Ƿ��ǵ�����)		 
	 * @return
	 * @throws
	 */	
	 public Vector getcontenttxt(String formflag, String entcode, String tradecode, String flowtype, String xh, String step)
		 throws TranFailException{
		 String queryStr="";
		 if (formflag.equals("1"))
		 {
			queryStr=
			" select process012"
					 +  " from mprocess_new   "
					 +  " WHERE process001=?  AND Tprocess002=?  AND Tprocess003=? AND Tprocess005=? ";
		 }
		 if (formflag.equals("3"))
		{
		 queryStr=
		 " select process019"
					+  " from mprocess_new   "
					+  " WHERE process001=?  AND Tprocess002=?  AND Tprocess003=? AND Tprocess005=? ";
		}
		if (formflag.equals("2") || step.equals("���黷��") )
		 {
			queryStr=
			" select process013,process014,process015,process016,process020"
					 +  " from mprocess_new   "
					 +  " WHERE process001=?  AND Tprocess002=?  AND Tprocess003=? AND Tprocess005=? ";
		 }
		 if (formflag.equals("2") || !step.equals("���黷��"))
		{
		 queryStr=
		 " select process020"
					+  " from mprocess_new   "
					+  " WHERE process001=?  AND Tprocess002=?  AND Tprocess003=? AND Tprocess005=? ";
		}
		 try{
			 DBTools srv = new DBTools(this.getOperation());
			 Vector param = new Vector(4);
			 param.add(entcode);
			 param.add(tradecode);
			 param.add(flowtype);
			 param.add(xh);
			 Vector v_result = srv.executeSQLResult(queryStr,param);
			 return v_result;       
		 }
		 catch(TranFailException te){
			 throw te;
		 }
		 catch(Exception e){
			 throw new TranFailException("util_showcontent_flow","util_showcontent_flowDAO.getcontenttxt",e.getMessage());
		 }
	 }
	 
/**
	 * ��������: ��ѯ�������˵��
	 * @param formflag ��ѯ���־  1,���˵�� 2,������������������������ 
	 * @param  entcode   //�ͻ�����
	 * @param tradecode //ҵ�������
	 * @param xh  //���
	 * @param step  //����(��Ҫ���������Ƿ��ǵ�����)		 
	 * @return
	 * @throws
	 */	
	 public Vector getadvicetxt(String formflag, String entcode, String tradecode, String xh, String step)
		 throws TranFailException{
		 String queryStr="";
		 if (formflag.equals("1"))
		 {
			queryStr=
			" select process012"
					 +  " from mprocess_new   "
					 +  " WHERE process001=?  AND Tprocess002=?  AND Tprocess005=? AND Tprocess006=? ";
		 }
		if (formflag.equals("2") || step.equals("���黷��") )
		 {
			queryStr=
			" select process013,process014,process015,process016,process020"
					 +  " from mprocess_new   "
					 +  " WHERE process001=?  AND Tprocess002=?  AND Tprocess005=? AND Tprocess006=? ";
		 }
		 if (formflag.equals("2") || !step.equals("���黷��"))
		{
		 queryStr=
		 " select process020"
					+  " from mprocess_new   "
					+  " WHERE process001=?  AND Tprocess002=?  AND Tprocess005=? AND Tprocess006=? ";
		}
		 try{
			 DBTools srv = new DBTools(this.getOperation());
			 Vector param = new Vector(4);
			 param.add(entcode);
			 param.add(tradecode);
			 param.add(xh);
			 param.add(step);
			 Vector v_result = srv.executeSQLResult(queryStr,param);
			 return v_result;       
		 }
		 catch(TranFailException te){
			 throw te;
		 }
		 catch(Exception e){
			 throw new TranFailException("util_showcontent_flow","util_showcontent_flowDAO.getcontenttxt",e.getMessage());
		 }
	 }
}
