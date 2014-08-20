////////////////////////////////////////////////////////////////////////////
//Copyright (C) 2004 ICBC
//
//ALL RIGHTS RESERVED BY ICBC CORPORATION, THIS PROGRAM
//MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH IT WAS  
//FURNISHED BY ICBC CORPORATION, NO PART OF THIS PROGRAM
//MAY BE REPRODUCED OR DISCLOSED TO OTHERS, IN ANY FORM
//WITHOUT THE PRIOR WRITTEN PERMISSION OF ICBC CORPORATION.
//USE OF COPYRIGHT NOTICE DOES NOT EVIDENCE PUBLICATION
//OF THE PROGRAM
//
//			ICBC CONFIDENTIAL AND PROPRIETARY
//
////////////////////////////////////////////////////////////////////////////
//

package icbc.cmis.service.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <b>��������: </b> 2004-9-21<br>
 * <b>����: </b>CM2002�������������<br>
 * <b>������: </b><br>
 * ���������ṩ������ϵͳ����ϵͳ��ͨ��������CM2002�Ľ��ס�<br>
 * <p>Copyright: Copyright (c)2004</p>
 * <p>Company: </p>
 * 
 * @author YangGuangRun
 * 
 * @version 
 * 
 * @since 
 * 
 * @see 
 * 
 */

public class CmisServiceHttpReqProxy {
	
	/**������IP��������*/
	String host=null;
	
	/**�������˿ں�*/
	String hostPort=null;
	
	/**ϵͳ��ʶID*/
	String systemTypeID=null;
	
	/**��Ա(�û�)��ʶ*/
	String authId=null;
	
	/**�û�У�鴮�����룩*/
	String verifyStr=null;
	
	/**CM2002�������,ͨ��ΪServlet URL,
	 * Ʃ��"/icbc/cmis/servlet/CmisServiceReqServlet"*/
	String responsor=null;
	
	/**�������**/
	String serialNo=null;
	
	
	/**
	 * <b>���캯��</b><br>
	 * 
	 */
	public CmisServiceHttpReqProxy(String serialNo,String host,String responsor,String systemTypeID,String authId,String verifyStr ) {
		
		this(serialNo,host,"80",responsor,systemTypeID,authId,verifyStr);
	}
	
	/**
	 * <b>���캯��</b><br>
	 * 
	 */
	public CmisServiceHttpReqProxy(String serialNo,String host,String hostPort,String responsor,String systemTypeID,String authId,String verifyStr ) {
		setReqSerialNo(serialNo);
		setHost(host);
		setHostPort(hostPort);
		setResponsor(responsor);
		this.systemTypeID=systemTypeID;
		setAuthId(authId);
		setVerifyStr(verifyStr);
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>ֱ��ת��HTTP�ͻ��˵�HTTP���� </p>
	 * @param req
	 * @return
	 *  
	 */
	public String requestOperation(HttpServletRequest req){
		HashMap param=new HashMap();
		
		String opName="";
		for(Enumeration enumerator=req.getParameterNames();enumerator.hasMoreElements();){
			String parmName=(String)enumerator.nextElement();
			String[] parmValue=req.getParameterValues(parmName);
			if(parmName.equals("TransNo"))
				opName=parmValue[0];
			else
				param.put(parmName,parmValue);
		}
		
		return requestOperation(opName,param);
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>��CM2002�������󣬲�����CM2002���صĽ��XML��</p>
	 * @param opName
	 * @param param
	 *  
	 */
	public String requestOperation(String opName,HashMap param){
		if(!getResponsor().startsWith("/"))
			setResponsor("/"+getResponsor());
		String serviceUrl = "http://"+getHost()+":"+getHostPort()+getResponsor()+"?TransNo="+
							opName+"&SerialNo="+serialNo+"&SysID="+systemTypeID+"&AuthId="+authId+"&VerifyStr="+verifyStr;
		
		String urlParm="";
		for(Iterator iterator=param.keySet().iterator();iterator.hasNext();){
			String paramName=(String)iterator.next();
			String[] paramValue=(String[])param.get(paramName);
			if(paramValue.length==1)
				urlParm=urlParm+"&"+paramName+"="+paramValue[0];
			else
				for(int i=0;i<paramValue.length;i++){
					urlParm=urlParm+"&"+paramName+"="+paramValue[i];
				}
		}
		serviceUrl=serviceUrl+urlParm;
		
		System.out.println(serviceUrl);
		
		
		StringBuffer strRet = new StringBuffer();
		try {
			URL url = new URL(serviceUrl); 
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			String line;
			
			BufferedReader in =
				new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			for (strRet.append("");
				(line = in.readLine()) != null;
				strRet.append(line));
			in.close();
		} catch (IOException ex) {
	
		}
		System.out.println(strRet.toString());
		return  strRet.toString();
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public String getHost() {
		return host;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public String getHostPort() {
		return hostPort;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public String getResponsor() {
		return responsor;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param string
	 *  
	 */
	public void setHost(String string) {
		host = string;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param string
	 *  
	 */
	public void setHostPort(String string) {
		hostPort = string;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param string
	 *  
	 */
	public void setResponsor(String string) {
		responsor = string;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param string
	 *  
	 */
	public void setSystemTypeID(String string) {
		systemTypeID = string;
	}
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public String getAuthId() {
		return authId;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	public String getVerifyStr() {
		return verifyStr;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param string
	 *  
	 */
	public void setAuthId(String string) {
		authId = string;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param string
	 *  
	 */
	public void setVerifyStr(String string) {
		verifyStr = string;
	}
	
	public void setReqSerialNo(String reqSerialNo){
		this.serialNo=reqSerialNo;
	}
	
	public String getReqSerialNo(){
		return serialNo;
	}
	
}
