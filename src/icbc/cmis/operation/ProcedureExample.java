package icbc.cmis.operation;

import java.util.*;
import icbc.cmis.base.*;
import icbc.cmis.second.pkg.DBProcedureAccessService;
import icbc.cmis.second.pkg.DBProcedureParamsDef;
import icbc.cmis.service.*;
/**
 * Insert the type's description here.
 * Creation date: (2002-1-10 16:33:34)
 * @author: Administrator
 *
 *	 �洢���̷����ඨ�塣ʹ�����ӣ�
 *	   ���ݿ��
 *	   CREATE TABLE MAG_MAJOR ( 
 *	    MAJOR_CODE  VARCHAR2 (3)  NOT NULL, 
 *	   MAJOR_NAME  VARCHAR2 (20)  NOT NULL
 *	   )
 *	  �洢���̣�
 *	 create or replace package dbtest as
 *	 	type rec_dbtest is record (
 *	 			out_id	  varchar2(50),
 *	 			out_value  varchar2(50)
 *	 			);	
 *	 	type rec_dbtest1 is record (
 *	 			out_id1	  varchar2(50),
 *	 			out_value1  varchar2(50)
 *	 		);	
 *	 	type ref_dbtest is ref cursor return rec_dbtest;
 *	 	type ref_dbtest1 is ref cursor return rec_dbtest1;		
 *	 	procedure dbtestproc (
 *	 			in_rowNum in varchar2,
 *	 			out_ProcSign	out varchar2,
 *	 			out_rowCount	out varchar2,
 *	 			ret_dbtest 	out ref_dbtest,
 *	 			ret_dbtest1 	out ref_dbtest1
 *	 			);
 *	 end dbtest;
 *	 /
 *	 create or replace package body dbtest as 
 *	 	procedure dbtestproc (
 *	 			in_rowNum in varchar2,
 *	 			out_ProcSign	out varchar2,
 *	 			out_rowCount	out varchar2,
 *	 			ret_dbtest 	out ref_dbtest,
 *	 			ret_dbtest1 	out ref_dbtest1) 
 *	 	as
 *	 	begin
 *	 		out_ProcSign:='0';
 *	 		select count(*) into out_rowCount from mag_major;
 *	 		
 *	 		open ret_dbtest for
 *	 			select major_code,major_name from   Mag_major 
 *	 			where rownum < in_rowNum;
 *	 		open ret_dbtest1 for
 *	 			select major_code,major_name from   Mag_major 
 *	 			where rownum < in_rowNum;
 *	 		commit;
 *	 	exception
 *	 		when NO_DATA_FOUND then
 *	 			  open ret_dbtest for
 *	 			  	   select major_code,major_name from   Mag_major 
 *	 				   where rownum < -1;
 *	 		open ret_dbtest1 for
 *	 			select major_code,major_name from   Mag_major 
 *	 			where rownum < -1;
 *	 			  out_ProcSign:='0';
 *	 		when others then
 *	 			out_ProcSign:='1';
 *	 	end;
 *	 end dbtest;
 *	 /
 *
 */
public class ProcedureExample extends CmisOperation {
/**
 * ProcedureExample constructor comment.
 */
public ProcedureExample() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (2002-1-10 16:33:34)
 *
 *
 *	 �洢���̷����ඨ�塣ʹ�����ӣ�
 *	   ���ݿ��
 *	   CREATE TABLE MAG_MAJOR ( 
 *	    MAJOR_CODE  VARCHAR2 (3)  NOT NULL, 
 *	   MAJOR_NAME  VARCHAR2 (20)  NOT NULL
 *	   )
 *
 *
 *	  �洢���̣�
 *	 create or replace package dbtest as
 *	 	type rec_dbtest is record (
 *	 			out_id	  varchar2(50),
 *	 			out_value  varchar2(50)
 *	 			);	
 *	 	type rec_dbtest1 is record (
 *	 			out_id1	  varchar2(50),
 *	 			out_value1  varchar2(50)
 *	 		);	
 *	 	type ref_dbtest is ref cursor return rec_dbtest;
 *	 	type ref_dbtest1 is ref cursor return rec_dbtest1;		
 *	 	procedure dbtestproc (
 *	 			in_rowNum in varchar2,
 *	 			out_ProcSign	out varchar2,
 *	 			out_rowCount	out varchar2,
 *	 			ret_dbtest 	out ref_dbtest,
 *	 			ret_dbtest1 	out ref_dbtest1
 *	 			);
 *	 end dbtest;
 *	 /
 *	 create or replace package body dbtest as 
 *	 	procedure dbtestproc (
 *	 			in_rowNum in varchar2,
 *	 			out_ProcSign	out varchar2,
 *	 			out_rowCount	out varchar2,
 *	 			ret_dbtest 	out ref_dbtest,
 *	 			ret_dbtest1 	out ref_dbtest1) 
 *	 	as
 *	 	begin
 *	 		out_ProcSign:='0';
 *	 		select count(*) into out_rowCount from mag_major;
 *	 		
 *	 		open ret_dbtest for
 *	 			select major_code,major_name from   Mag_major 
 *	 			where rownum < in_rowNum;
 *	 		open ret_dbtest1 for
 *	 			select major_code,major_name from   Mag_major 
 *	 			where rownum < in_rowNum;
 *	 		commit;
 *	 	exception
 *	 		when NO_DATA_FOUND then
 *	 			  open ret_dbtest for
 *	 			  	   select major_code,major_name from   Mag_major 
 *	 				   where rownum < -1;
 *	 		open ret_dbtest1 for
 *	 			select major_code,major_name from   Mag_major 
 *	 			where rownum < -1;
 *	 			  out_ProcSign:='0';
 *	 		when others then
 *	 			out_ProcSign:='1';
 *	 	end;
 *	 end dbtest;
 *	 /
 */
public void execute() throws Exception {

//Ϊ���ô洢����׼������Ҫ������
	
	setFieldValue("rowNum","10");//�洢�������볡�������ֵ
	DBProcedureParamsDef def = new DBProcedureParamsDef("dbtest.dbtestproc");
	//���볡����
	def.addInParam("rowNum");

	//���������
	def.addOutParam("returnFlag");//�洢���̷����룬��ʶ�洢��������״̬
	def.addOutParam("rowCount");

   //����洢���̽������cursor�����ز���
		//�������
		Vector vCursor1 = new Vector(2);
		vCursor1.add("id1");//����������ֶ�
		vCursor1.add("value1");//����������ֶ�
		def.addCursorOutParams(vCursor1,"iResultSet1");//ָ��������洢�ڵ�ǰ���׼����е�һ����iResultSet1������IndexedDataCollection��
		//�������
		Vector vCursor2 = new Vector(2);
		vCursor2.add("id2");//����������ֶ�
		vCursor2.add("value2");//����������ֶ�
		def.addCursorOutParams(vCursor2,"iResultSet2");//ָ��������洢�ڵ�ǰ���׼����е�һ����iResultSet��������IndexedDataCollection��
	
//ָ���洢�����û�������Ϣ,���ʹ��Ĭ���û�cmis3������Ҫ���²���
	
	def.setDBUserName((String)CmisConstance.getParameterSettings().get("dbUserName"));
	def.setDBUserPass((String)CmisConstance.getParameterSettings().get("dbUserPass"));
	 
//���ô洢����
	DBProcedureAccessService proceSrv = new DBProcedureAccessService(this);
	int returnCode = proceSrv.executeProcedure(this.getOperationData(),def);
	proceSrv = null;
		
//ȡ�洢���̽����Ϣ
	
	//����returnCodeΪ0��ʾ�洢���̵��óɹ�
	if(returnCode != 0){
		//����returnCode�Ĳ�ͬ���ʹ���洢���̷��ص���ϢreturnMessage��������Ĵ洢���̶����˵Ļ�����ҵ������
		setReplyPage("/icbc/cmis/testlogin.jsp");	//����������ҳ��
		return;
	}
	
	System.out.println(getStringAt("returnFlag"));
	System.out.println(getStringAt("rowCount"));
	
	//ȡ�������Ϣ
		//�������
		IndexedDataCollection iResult1 = getIndexedDataCollection("iResultSet1");
		
		for(int i = 0;i<iResult1.getSize();i++){	//����������е�ÿ����¼
			KeyedDataCollection aKColl = (KeyedDataCollection)iResult1.getElement(i);
			System.out.println((String)aKColl.getValueAt("id1"));
			System.out.println((String)aKColl.getValueAt("value1"));
		}
		
		//�������
		IndexedDataCollection iResult2 = getIndexedDataCollection("iResultSet2");
		
		for(int i = 0;i<iResult2.getSize();i++){	//����������е�ÿ����¼
			KeyedDataCollection aKColl = (KeyedDataCollection)iResult2.getElement(i);
			System.out.println((String)aKColl.getValueAt("id2"));
			System.out.println((String)aKColl.getValueAt("value2"));
		}
    setReplyPage("/icbc/cmis/testlogin.jsp");	
}
}
