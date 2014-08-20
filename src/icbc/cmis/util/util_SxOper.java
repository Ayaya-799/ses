/*
 * �������� 2004-12-1
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 * ��д ����  �޸����ſ��Ƴ���ĵ���
 */
package icbc.cmis.util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import icbc.cmis.base.*;
import icbc.cmis.operation.*;
import java.sql.*;
import icbc.cmis.second.pkg.*;

public class util_SxOper  extends CmisOperation{
   public util_SxOper(){}

   String replaypage="";
  String baseWebPath = (String) CmisConstance.getParameterSettings().get("webBasePath");
 public void execute() throws java.lang.Exception {

   String opAction = "sxop60";
    try{
      try{
		opAction = this.getStringAt("opAction");
         }catch(Exception e){ }
       if( opAction.equals("sxop60")){   //�������ſ���
		proc_sxctrl();
		this.setOperationDataToSession();
      }

         //setReplyPage(replaypage);
   }catch(TranFailException e){
                     throw e;
   }catch(Exception e){
                     throw new TranFailException("sxop099","util_sxOper",e.getMessage(),e.getMessage());
                    }
  }//end execute
  SqlTool sqlrun=null;

/*�ú��������������ҵ���롢֧�д�����Ҹ���ҵ�������Դ����ݺ�
*/
private void proc_sxctrl() throws TranFailException{
  
   String    sql = "";
   //SqlTool   sqlrun = null;
   CallableStatement stmt=null;
   try{
      PrintWriter out=new PrintWriter(System.out,true);
     this.setFieldValue("EnpCode",this.getStringAt("EnpCode"));
	 this.setFieldValue("Enp_type",this.getStringAt("Enp_type"));
	 this.setFieldValue("LoanAmount",this.getStringAt("LoanAmount"));
	 this.setFieldValue("CurrType",this.getStringAt("CurrType"));
	 this.setFieldValue("UniqueID1",this.getStringAt("UniqueID1"));
	 this.setFieldValue("UniqueID2",this.getStringAt("UniqueID2"));
	 this.setFieldValue("UniqueID3",this.getStringAt("UniqueID3"));
	 this.setFieldValue("UniqueID4",this.getStringAt("UniqueID4"));
	 
	 this.setFieldValue("LoanDate",this.getStringAt("LoanDate"));
	 this.setFieldValue("LoanType",this.getStringAt("LoanType"));
	 this.setFieldValue("FlowType",this.getStringAt("FlowType"));
     //���������ݿ������
	DBProcedureParamsDef def =
				new DBProcedureParamsDef("proc_sx");

			def.addInParam("EnpCode");
			def.addInParam("Enp_type");
			def.addInParam("LoanAmount");
			def.addInParam("CurrType");
			def.addInParam("UniqueID1");
			def.addInParam("UniqueID2");
			def.addInParam("UniqueID3");
			def.addInParam("UniqueID4");
			def.addInParam("LoanDate");
			def.addInParam("LoanType");
			def.addInParam("FlowType");
			def.addOutParam("out_flag");
			def.addOutParam("out_msg");

			DBProcedureAccessService procSrv =new DBProcedureAccessService(this);
			procSrv.executeProcedure(this.getOperationData(), def);
			procSrv = null;

			String out_flag = getStringAt("out_flag");
			String out_msg = getStringAt("out_msg");
			String xml = "DirectOutput<?xml version=\"1.0\" encoding=\"GBK\"?>";
     			xml+="<datas out_flag='"+out_flag+"' out_mesg='"+out_msg+"' >";
    		        xml +="</datas>";
	setReplyPage(xml);
	this.setOperationDataToSession();	
   }catch (TranFailException e){
     //if(sqlrun != null) sqlrun.closeconn();
     throw e;
   }catch (Exception e){
     //if(sqlrun != null) sqlrun.closeconn();
     throw new TranFailException("util_sxop001","util_SxOper.proc_sxctrl",e.getMessage(),e.getMessage());
   }
}

 
}