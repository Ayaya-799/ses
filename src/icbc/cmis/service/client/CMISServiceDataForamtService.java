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

import icbc.cmis.base.XMLReader;

/**
 * 
 * <b>��������: </b> 2004-10-21<br>
 * <b>����: </b><br>
 * <b>������: </b><br>
 * ���ݻ���ʽ���񣬸÷�����Ҫ�������ݸ�ʽ�����ļ��ĳ�ʼ���������ʼ�����֮��<br>
 * �����ݽ��и�ʽ���ͷ���ʽ��ʱ�Ͳ���Ҫ�ٴζ�ȡ��ʽ�����ļ���<br>
 * <p>Copyright: Copyright (c)2004</p>
 * <p>Company: </p>
 * 
 * @author Administrator
 * 
 * @version 
 * 
 * @since 
 * 
 * @see 
 * 
 */

public class CMISServiceDataForamtService {
	
	private static XMLReader xmlReader=null;
	/**��ʼ����־��true �Ѿ���ʼ����false ��δ��ʼ��*/
	private static boolean isInitialized=false;
		
	private CMISServiceDataForamtService(){
	}
	
	
	/**
	 * <b>��������: </b><br>
	 * <p>�Է�����г�ʼ�� </p>
	 * @param xmlFileName
	 * @throws Exception
	 *  
	 */
	public static void initFormatServiece(String xmlFileName)throws Exception{
		getXMLReader().loadXMLFile(xmlFileName);
		isInitialized=true;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>��ȡXMLReader,ͨ����Ҫ����ɳ�ʼ�����ú��ڽ��е��� </p>
	 * @return
	 *  
	 */
	public static XMLReader getXMLReader(){
		if(xmlReader==null)
			xmlReader=new XMLReader();
		return xmlReader;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�жϷ����Ƿ��Ѿ�����ʼ�� </p>
	 * @return
	 *  
	 */
	public static boolean isInitialized(){
		return isInitialized;
	}
}
