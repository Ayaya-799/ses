package icbc.cmis.util;

/*************************************************************
 * 
 * <b>��������: </b> 2004-7-16<br>
 * <b>����: </b>��¼�ʹ�ӡ�쳣��<br>
 * <b>������: </b><br>
 * <br>��5�������¼��Ϣ
 * <p>Copyright: Copyright (c)2004</p>
 * <p>Company: </p>
 * 
 * @author zhouxj
 * 
 * @version 1.03
 * 
 * @since 
 * 
 * @see 
 * 
 *************************************************************/

public class util_logTools {

	private static int level = 1; //�����Ϣ��¼����,1-5�ֱ�Ϊdebug,info,warn,error,fatal
	private static boolean jspinfoFlag=true;	//�Ƿ���jspҳ�����
	/**
	 * <b>��������: </b><br>
	 * <p> </p>
	 * @param i
	 * @param msg
	 *  
	 */

	private static void msgout(int i, String msg) {
		
		if (i >= level) {
			System.out.println(msg);
		}
	}
	/**
	 * <b>��������: </b><br>
	 * <p> ���ݼ�������쳣�����Ϣ</p>
	 * @param i
	 * @param msg
	 *  
	 */
	private static void msgout(int i, Exception e) {
		
		if (i >= level) {
			e.printStackTrace();
		}
	}
	/**
	 * <b>��������: </b><br>
	 * <p>���������Ϣ����Ϣ����1 </p>
	 */
	public static void debug(Exception e) {
		msgout(1, e);
	}
	/**
	 * <b>��������: </b><br>
	 * <p> ���һ����Ϣ����Ϣ����2</p>
	 *  
	 */
	public static void info(Exception e) {

		msgout(2, e);
	}
	/**
	 * <b>��������: </b><br>
	 * <p> ���������Ϣ����Ϣ����3</p>
	 *  
	 */
	public static void warn(Exception e) {

		msgout(3, e);
	}
	/**
	 * <b>��������: </b><br>
	 * <p> ���������Ϣ����Ϣ����4</p>
	 *  
	 */
	public static void error(Exception e) {

		msgout(4, e);
	}
	/**
	 * <b>��������: </b><br>
	 * <p> ������ش�����Ϣ����Ϣ����5</p>
	 *  
	 */
	public static void fatal(Exception e) {

		msgout(5, e);
	}

	/**
	 * <b>��������: </b><br>
	 * <p>���������Ϣ����Ϣ����1 </p>
	 */
	public static void debug(String msg) {
		msgout(1, msg);
	}
	/**
	 * <b>��������: </b><br>
	 * <p> ���һ����Ϣ����Ϣ����2</p>
	 *  
	 */
	public static void info(String msg) {

		msgout(2, msg);
	}
	/**
	 * <b>��������: </b><br>
	 * <p> ���������Ϣ����Ϣ����3</p>
	 *  
	 */
	public static void warn(String msg) {

		msgout(3, msg);
	}
	/**
	 * <b>��������: </b><br>
	 * <p> ���������Ϣ����Ϣ����4</p>
	 *  
	 */
	public static void error(String msg) {

		msgout(4, msg);
	}
	/**
	 * <b>��������: </b><br>
	 * <p> ������ش�����Ϣ����Ϣ����5</p>
	 *  
	 */
	public static void fatal(String msg) {

		msgout(5, msg);
	}

	/**
	 * <b>��������: </b><br>
	 * <p>ȡ�ü�¼��Ϣ���� </p>
	 * @return
	 *  
	 */
	public static int getLevel() {
		return level;
	}

	/**
	 * <b>��������: </b><br>
	 * <p> ���ü�¼��Ϣ�ļ���</p>
	 * @param i
	 *  
	 */
	private static void setLevel(int i) {
		level = i;		//ע�ⲻ�����ٳ���������ı������Ϣ�ļ��𣬷����Ӱ����������������ʽ��
	}
	
	/**
	 * <b>��������: </b><br>
	 * <p>��JSPҳ���������Ϣ </p>
	 * @param str
	 * @return ������Ϣ
	 *  
	 */
	public static String jspinfo(String str) {

		if (jspinfoFlag == true) {
			return str;
		}
		return "";
	}
}
