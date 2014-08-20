package icbc.cmis.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import icbc.cmis.base.*;
import icbc.cmis.service.GenAppIDService;
import icbc.cmis.service.channel.CM2002Service;
import icbc.missign.GetMenu;

/**
 * Insert the type's description here.
 * Creation date: (2001-12-24 13:26:23)
 * @author: Administrator
 */
public class CmisStartupServlet extends HttpServlet {
  String userName = null;
  String userPass = null;
  String cteFilePath = null;
  private boolean monitorType = false;
  /**
   * CmisStartupServlet constructor comment.
   */
  public CmisStartupServlet() {
    super();
  }
  
  /**
   * service method comment.
   */
  public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, java.io.IOException {
    try {
      //		String password1 = (String) req.getParameter("password");
      //		String userName1 = (String) req.getParameter("userName");
      //
      ////	if (userName1 == null || !userName.trim().equals(userName))
      ////		{
      ////			tipMsg(rep,"ERROR in Server: �Ƿ��û�!");
      ////			return;
      ////		}
      ////
      ////		if (password1 == null || !password1.trim().equals(userPass))
      ////		{
      ////			tipMsg(rep,"ERROR in Server: �Ƿ��û�!");
      ////			return ;
      ////		}
      ////		cteFilePath = "d:\\ncmis3\\cmis3\\defaultroot\\icbccmis.xml";
      //		//cteFilePath = "d:\\wsad\\icbccmis_2\\Web Content\\icbccmis.xml";
      //		//cteFilePath = "E:\\Workbench\\icbccmis\\config\\icbccmis.xml";
      //		CmisConstance.systemReset();
      //
      //
      //			System.out.println("begin initCTEFile:"+cteFilePath);
      //			CmisConstance.initCTEFile(cteFilePath);
      //			System.out.println("end initCTEFile:"+cteFilePath);
      //					
      //			icbc.cmis.base.Trace.reset();
      //	
      ////			System.out.println("begin initializePassVerifyTable");		
      ////			CmisConstance.initializePassVerifyTable();
      ////			System.out.println("end initializePassVerifyTable");	
      //						
      //			System.out.println("begin setWorkDate");				
      //			CmisConstance.setWorkDate();
      //			System.out.println("end setWorkDate");	
      //			
      //			System.out.println("begin begin gen appid");							
      //			try{
      //				GenAppIDService gappid= new GenAppIDService();	
      //				CmisConstance.appServerID = gappid.getAppId();
      //			}
      //			catch(Exception e){
      //				
      //			}
      //			if(CmisConstance.appServerID == null) CmisConstance.appServerID = "";
      //			System.out.println("end gen appid:"+CmisConstance.appServerID);	
      //			
      //			System.out.println("SSIC initialize BEGIN.");						
      //										SSICTool.initializeSSIC();
      //									System.out.println("SSIC initialize complete.");			
      //								
      //			System.out.println("begin initializeErrorMessageTable");								
      //			CmisConstance.initializeErrorMessageTable();
      //			System.out.println("end initializeErrorMessageTable");										
      //			
      //			System.out.println("begin initializationFmt");
      //			CmisConstance.initializationFmt();
      //			System.out.println("end initializationFmt");		
      //	
      //			System.out.println("begin initializeLimitedOpList()");
      //			CmisConstance.initializeLimitedOpList();
      //			System.out.println("end initializeLimitedOpList()");
      //			
      //			System.out.println("begin initialDictTables()");		
      //			new icbc.cmis.service.GeneralSQLService().initialDictTables();
      //			System.out.println("end initialDictTables()");		
      //			
      //			System.out.println("begin startCmisSystemInfoThread()");						
      //			CmisConstance.startCmisSystemInfoThread();
      //			System.out.println("end startCmisSystemInfoThread()");		
      //							
      //			/////////////////////////////////////////////////////////////////
      //			/*
      //			 * �޸�ԭ������CM2002��CTP�������ʹ��������ܵĳ�ʼ��ͬʱ���У�����
      //			 * 			 ��CTP�ĳ�ʼ����Ƕ����CM2002������档
      //			 * �޸�ʱ�䣺2004-07-01
      //			 * �޸��ˣ������
      //			 * 
      //			 * �޸�ԭ���������ò��������Ƿ����CTP�ĳ�ʼ����
      //			 * �޸�ʱ�䣺2004-08-18
      //			 * �޸��ˣ������
      //			 */
      //			if(((String)CmisConstance.getParameterSettings().get("CTPEnabled")).equals("true")){
      //				CTPInitService CTPIniter=new CTPInitService();
      //				CTPIniter.doPostRefresh(req,rep,false);	
      //			}
      //			/////////////////////////////////////////////////////////////////
      //			
      //		CmisConstance.isServerStarted = true;
      //		System.out.println(CmisConstance.getDictParam().get("vma350002_350"));
      //		tipMsg(rep,"Ӧ�÷�����["+java.net.InetAddress.getLocalHost().getHostAddress()+" "+CmisConstance.appServerID+"]��ʼ�����! ��ǰ��������Ϊ:"+CmisConstance.getWorkDate("yyyy��MM��dd��HHʱmm��ss��")+",ϵͳ����Ϊ��"+CmisConstance.getSystemData("yyyy��MM��dd��HHʱmm��ss��"));
      //		icbc.cmis.base.Trace.trace("",0,0,"","Ӧ�÷�����["+java.net.InetAddress.getLocalHost().getHostAddress()+" "+CmisConstance.appServerID+"]��ʼ�����! ��ǰ��������Ϊ:"+CmisConstance.getWorkDate("yyyy��MM��dd��HHʱmm��ss��")+",ϵͳ����Ϊ��"+CmisConstance.getSystemData("yyyy��MM��dd��HHʱmm��ss��"));
    }
    catch (Exception e) {
      tipMsg(rep, "ERROR: exception is:\n" + e.getMessage());
    }
  }
  /**
   * service method comment.
   */
  public void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, java.io.IOException {
    try {
      //		icbc.cmis.base.Trace.trace("",0,0,"","CmisStartupServlet.doPost() begin");
      //		String password1 = (String) req.getParameter("password");
      //		String userName1 = (String) req.getParameter("userName");
      //
      ////	if (userName1 == null || !userName.trim().equals(userName))
      ////		{
      ////			tipMsg1(rep,"ERROR in Server: �Ƿ��û�!");
      ////			return;
      ////		}
      ////
      ////		if (password1 == null || !password1.trim().equals(userPass))
      ////		{
      ////			tipMsg1(rep,"ERROR in Server: �Ƿ��û�!");
      ////			return ;
      ////		}
      //		boolean isStart = true;
      //		String statusCheck = (String)req.getParameter("statusCheck");
      //		if(statusCheck != null && statusCheck.trim().equals("true")){
      //			if(CmisConstance.isServerStarted){
      //
      //				tipMsg1(rep,"�����Ѿ�����");
      //				return ;
      //			}else{
      //				isStart = false;
      //			}
      //		}
      //		CmisConstance.systemReset();
      //
      //			System.out.println("begin initCTEFile:"+cteFilePath);
      //			CmisConstance.initCTEFile(cteFilePath);
      //			System.out.println("end initCTEFile:"+cteFilePath);
      //					
      //			icbc.cmis.base.Trace.reset();
      //	
      ////			System.out.println("begin initializePassVerifyTable");		
      ////			CmisConstance.initializePassVerifyTable();
      ////			System.out.println("end initializePassVerifyTable");	
      //						
      //			System.out.println("begin setWorkDate");				
      //			CmisConstance.setWorkDate();
      //			System.out.println("end setWorkDate");	
      //			
      //			System.out.println("begin begin gen appid");							
      //			try{
      //				GenAppIDService gappid= new GenAppIDService();	
      //				CmisConstance.appServerID = gappid.getAppId();
      //			}
      //			catch(Exception e){
      //				
      //			}
      //			if(CmisConstance.appServerID == null) CmisConstance.appServerID = "";
      //			System.out.println("end gen appid:"+CmisConstance.appServerID);				
      //					
      //			
      //			
      //			System.out.println("SSIC initialize BEGIN.");						
      //										SSICTool.initializeSSIC();
      //									System.out.println("SSIC initialize complete.");			
      //								
      //			System.out.println("begin initializeErrorMessageTable");								
      //			CmisConstance.initializeErrorMessageTable();
      //			System.out.println("end initializeErrorMessageTable");										
      //			
      //			System.out.println("begin initializationFmt");
      //			CmisConstance.initializationFmt();
      //			System.out.println("end initializationFmt");		
      //	
      //			System.out.println("begin initializeLimitedOpList()");
      //			CmisConstance.initializeLimitedOpList();
      //			System.out.println("end initializeLimitedOpList()");
      //			
      //			System.out.println("begin initialDictTables()");		
      //			new icbc.cmis.service.GeneralSQLService().initialDictTables();
      //			System.out.println("end initialDictTables()");		
      //			
      //			System.out.println("begin startCmisSystemInfoThread()");						
      //			CmisConstance.startCmisSystemInfoThread();
      //			System.out.println("end startCmisSystemInfoThread()");	
      //								
      //			////////////////////////////////////////////////////////////////////////
      //			/*
      //			 * �޸�ԭ������CM2002��CTP�������ʹ��������ܵĳ�ʼ��ͬʱ���У�����
      //			 * 			 ��CTP�ĳ�ʼ����Ƕ����CM2002������档
      //			 * �޸�ʱ�䣺2004-07-01
      //			 * �޸��ˣ������
      //			 * 
      //			 * �޸�ԭ���������ò��������Ƿ����CTP�ĳ�ʼ����
      //			 * �޸�ʱ�䣺2004-08-18
      //			 * �޸��ˣ������
      //			 */
      //			if(((String)CmisConstance.getParameterSettings().get("CTPEnabled")).equals("true")){
      //				CTPInitService CTPIniter=new CTPInitService();
      //				CTPIniter.doPostRefresh(req,rep,false);	
      //			}
      //			////////////////////////////////////////////////////////////////////////
      //		
      //		
      //		CmisConstance.isServerStarted = true;
      //		tipMsg1(rep,"Ӧ�÷�����["+java.net.InetAddress.getLocalHost().getHostAddress()+" "+CmisConstance.appServerID+"]��ʼ�����! ��������Ϊ:"+CmisConstance.getWorkDate("yyyy��MM��dd��HHʱmm��ss��")+",ϵͳ����Ϊ��"+CmisConstance.getSystemData("yyyy��MM��dd��HHʱmm��ss��"));
      //		icbc.cmis.base.Trace.trace("",0,0,"","Ӧ�÷�����["+java.net.InetAddress.getLocalHost().getHostAddress()+" "+CmisConstance.appServerID+"]��ʼ�����! ��ǰ��������Ϊ:"+CmisConstance.getWorkDate("yyyy��MM��dd��HHʱmm��ss��")+",ϵͳ����Ϊ��"+CmisConstance.getSystemData("yyyy��MM��dd��HHʱmm��ss��"));
    }
    catch (Exception e) {
      tipMsg1(rep, "ERROR: exception is:\n" + e.getMessage());
    }
  }
  /**
  	 @roseuid 3A51E04500FA
  	 */
  public void init(ServletConfig sc) {
    try {
      super.init(sc);
      String initStart = getInitParameter("initStart");
      cteFilePath = sc.getInitParameter("CTEFilePath");
      userName = sc.getInitParameter("userName");
      userPass = sc.getInitParameter("userPass");
      CmisConstance.appServerID = sc.getInitParameter("JVMID");
      if (CmisConstance.appServerID == null)
        CmisConstance.appServerID = "";
      CmisConstance.appServerID = CmisConstance.appServerID.trim();
      CmisConstance.appCode = sc.getInitParameter("appCode");
      if (CmisConstance.appCode == null)
        CmisConstance.appCode = "";
      CmisConstance.appCode = CmisConstance.appCode.trim();

      if (initStart != null && initStart.equals("true")) {
        CmisConstance.systemReset();

        System.out.println("begin clear menu");
        GetMenu.menus.clear();
        System.out.println("end clear menu");

        System.out.println("begin initCTEFile:" + cteFilePath);
        CmisConstance.initCTEFile(cteFilePath);
        System.out.println("end initCTEFile:" + cteFilePath);

        icbc.cmis.base.Trace.reset();

        //			System.out.println("begin initializePassVerifyTable");		
        //			CmisConstance.initializePassVerifyTable();
        //			System.out.println("end initializePassVerifyTable");	

		/*
		* �޸�ԭ��Ӣ�İ����,
		* �޸�ʱ�䣺2007-04-17
		* �޸��ˣ�chenj
		* ��ʼ��ϵͳĿǰ��ʱ��������Դ����
		*/		
		System.out.println("begin initZoneLang");
		//CmisConstance.initZoneLang();
		System.out.println("end initZoneLang");
		//�޸Ľ���


        System.out.println("begin setWorkDate");
        CmisConstance.setWorkDate();
        System.out.println("end setWorkDate");

        System.out.println("begin begin gen appid");
        try {
          GenAppIDService gappid = new GenAppIDService();
          CmisConstance.appServerID = gappid.getAppId();
        }
        catch (Exception e) {}
        if (CmisConstance.appServerID == null)
          CmisConstance.appServerID = "";
        System.out.println("end gen appid:" + CmisConstance.appServerID);

        System.out.println("SSIC initialize BEGIN.");
        //SSICTool.initializeSSIC();
        System.out.println("SSIC initialize complete.");

        System.out.println("begin initializeErrorMessageTable");
        //CmisConstance.initializeErrorMessageTable();
        System.out.println("end initializeErrorMessageTable");

        //add by chenj 20060301
        System.out.println("begin initializeMsgList");
        //genMsg.initializeMsgList();
        System.out.println("end initializeMsgList");
        //add by chenj 20060301 end

        System.out.println("begin initializationFmt");
        //CmisConstance.initializationFmt();
        System.out.println("end initializationFmt");

        System.out.println("begin initializeLimitedOpList()");
        //CmisConstance.initializeLimitedOpList();
        System.out.println("end initializeLimitedOpList()");

        //		add by ZAP	20060607
       /* CmisConstance.initControledList();
        CmisConstance.initOpdataList();*/
        
        //end zap 20060607
/*
        System.out.println("begin initializeControledFunc()");
        CmisConstance.initializeControledFunc();
        System.out.println("end initializeControledFunc()");

        System.out.println("begin initialDictTables()");
        new icbc.cmis.service.GeneralSQLService().initialDictTables();
        System.out.println("end initialDictTables()");

        System.out.println("begin startCmisSystemInfoThread()");
        CmisConstance.startCmisSystemInfoThread();
        System.out.println("end startCmisSystemInfoThread()");*/
		/*
		* �޸�ԭ��ipͨ����������ʵʱ����
		* �޸�ʱ�䣺2006-08-18
		* �޸��ˣ�������
		* 
		*/
		System.out.println("��ʼ����ipͨ������ʵʱ��������");
		/*try{
				if (((String)CmisConstance.getParameterSettings().get("IPCGWEnabled")).equals("true")) {
	                         System.out.println("��������ipͨ������ͨѶ����");
					IPCGWClientService.startService();
				} 
				System.out.println("ipͨ������ʵʱ���������������");
		}catch(Exception e){
			System.out.println("ipͨ������ʵʱ������������ʧ��");
		}*/
        //////////////////////////////////////////////////////////////////////
        /*
         * �޸�ԭ������CM2002��CTP�������ʹ��������ܵĳ�ʼ��ͬʱ���У�����
         * 			 ��CTP�ĳ�ʼ����Ƕ����CM2002������档
         * �޸�ʱ�䣺2004-07-01
         * �޸��ˣ������
         * 
         * �޸�ԭ���������ò��������Ƿ����CTP�ĳ�ʼ����
         * �޸�ʱ�䣺2004-08-18
         * �޸��ˣ������
         */
        /*if (((String)CmisConstance.getParameterSettings().get("CTPEnabled")).equals("true")) {

          CTPInitService CTPIniter = new CTPInitService();
          CTPIniter.init(sc, false);
        }*/
        ///////////////////////////////////////////////////////////////////////

        CmisConstance.isServerStarted = true;
        ///////////////////////////////////////////////////////////////////////////
        /*
         * 	�޸�ԭ���ṩCM2002����ӿڷ��񣬹��ڴ�����CM2002����ӿڷ���
         * 	�޸�ʱ�䣻2004-10-23
         * 	�޸���:YangGuangRun
         */
        if (CM2002Service.isEnabled()) {
          System.out.println("begin CM2002Service()");
          CM2002Service.resetService();
          System.out.println("end CM2002Service()");
        }

        log(
          "AppServer["
            + java.net.InetAddress.getLocalHost().getHostAddress()
            + " "
            + CmisConstance.appServerID
            + "]initialization ok! current work data:"
            + CmisConstance.getWorkDate("yyyy��MM��dd��HHʱmm��ss��")
            + ",current system data��"
            + CmisConstance.getSystemData("yyyy��MM��dd��HHʱmm��ss��"));
      }
    }
    catch (Exception e) {
      log("icbc.cmis.servlets.CmisStartupServlet Initialization: Exception in init: " + e);
    }
    log("icbc.cmis.servlets.CmisStartupServlet Initialization:End of init");
  }
  /**
   * Insert the method's description here.
   * Creation date: (2002-1-3 19:51:52)
   * @param o javax.servlet.ServletOutputStream
   */
  private void tipMsg(HttpServletResponse rep, String message) throws java.io.IOException {
    java.io.PrintWriter o = (java.io.PrintWriter)rep.getWriter();
    o.println("<HEAD><H1><B>ICBCCmisStartupServlet</B></H1><HR></HEAD>");
    o.println("<BODY>");
    o.write(message);
    o.println("<BR><HR>");
    o.println("</BODY></HTML>");
    o.close();
  }
  /**
   * Insert the method's description here.
   * Creation date: (2002-1-3 19:51:52)
   * @param o javax.servlet.ServletOutputStream
   */
  private void tipMsg1(HttpServletResponse rep, String message) throws java.io.IOException {
    java.io.DataOutputStream out = new java.io.DataOutputStream(rep.getOutputStream());
    byte[] messageBytes = message.getBytes("UTF8");
    out.writeInt(messageBytes.length);
    out.write(messageBytes, 0, messageBytes.length);

    out.close();
  }
}
