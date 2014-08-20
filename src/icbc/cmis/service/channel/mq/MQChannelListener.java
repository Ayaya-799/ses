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

package icbc.cmis.service.channel.mq;

import icbc.cmis.base.TranFailException;
import icbc.cmis.service.MQConnectionPoolService;
import icbc.cmis.service.MQConnectionService;
import icbc.cmis.service.channel.CM2002Service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;

/**
 * 
 * <b>��������: </b> 2004-10-21<br>
 * <b>����: </b><br>
 * <b>������: </b><br>
 * MQ�����ػ���������<br>
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

public class MQChannelListener extends Thread {
	
	/**�������Ķ���*/
	private String channelName=null;
	
	private MQConnectionService listenedChannel=null;
	
	/**MQ���ӻ���ط���*/
	private MQConnectionPoolService mqConnPool=null;
	
	/**��־�����Ƿ���ֹ*/
	boolean stoped=false;
	
	private int channelHandlers=0;
	
	public MQChannelListener(String channelName,MQConnectionPoolService mqConnPoolService){
		this.channelName=channelName;
		this.mqConnPool=mqConnPoolService;
		this.setName(channelName+"_Listener");
	}
	
	/** 
	 * <b>��������: </b><br>
	 * <p>	</p>
	 * @see java.lang.Runnable#run()
	 * 
	 * 
	 */
	public void run() {
		super.run();
		boolean start=true;
		try{
			init();
		}catch ( TranFailException e){
			listenedChannel=null;
			start=false;
			icbc.cmis.base.Trace.trace(
						"",
						0,
						0,
						"",
						"MQChannel "+channelName+"��MQChannelListener.run() init failed!");
		}
		icbc.cmis.base.Trace.trace(
						"",
						0,
						0,
						"",
						"MQChannel "+channelName+"��MQChannelListener.run() begin!");
		while(start){
			if(!isStopped() && listenedChannel!=null){
				if(channelHandlers < CM2002Service.getMaxMQServieceThread()){
					MQMessage message=null;
					try {
						message = listenedChannel.receive();
					} catch (MQException e1) {
						/*
						 * �����쳣����ͨ��������,�����������й�����
						 * MQ��������ֹͣ��
						 */
						if(e1.reasonCode==2009){
							if(listenedChannel!=null)
								mqConnPool.releaseMQConnection(listenedChannel);
								listenedChannel=null;
								try{
									init();
								}catch ( TranFailException e){
									listenedChannel=null;
									start=false;
								}
						}
					}
					if(message!=null){
						int messageLen;
						try {
							messageLen = message.getMessageLength();
						} catch (IOException e) {
							messageLen=0;
						}
						if(messageLen>0){
							Thread mqDriver=new Thread(new MQChannelDriver(message,channelName,this));
							mqDriver.setName(channelName+"_Handler");
							mqDriver.start();
							synchronized(this){
								channelHandlers++;
							}
						}
					}
				}
			}else{
				if(listenedChannel!=null)
					mqConnPool.releaseMQConnection(listenedChannel);
				break;
			}
		}
		icbc.cmis.base.Trace.trace(
							"",
							0,
							0,
							"",
							"MQChannel "+channelName+"��MQChannelListener.run() stopped!");	
		stoped=true;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>�ػ������ʼ�����������ڳ�ʼ���ػ��ɵļ���MQ���� </p>
	 * 
	 *  
	 */
	private void init() throws TranFailException{
		listenedChannel=mqConnPool.getMQConnection(channelName);
	}
	
	public synchronized void subChannelHandlers(){
		channelHandlers--;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * 
	 *  
	 */
	public void stopListener(){
		stoped=true;
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @return
	 *  
	 */
	private boolean isStopped(){
		return stoped;
	}
}
