package icbc.cmis.util;

public class Decode {

  public Decode() {
  }

  /**
   * �� @@NNNN ����ת��Ϊ����
   * @param src
   * @return
   */
  public static String decode(String src) {
    //Ŀ�괮
    String des = "";
    //Դ������
    int len = src.length();
    //@@��ʼλ��ָ��
    int i = 0;
    //��һ�ο�ʼ����λ��ָ��
    int k = 0;
    while(true) {
      //����@@��λ�ã��������Ǻ��ֵ���ʼλ��
      i = src.indexOf("@@",k);
      //���Ҳ���@@���ʾû�к���
      if(i == -1) {
        //��ָ��δ���ַ�����β������ʣ�µ��ַ������Ƶ�Ŀ�괮
        if(k < len) des += src.substring(k,len);
        //���
        break;
      }
      //���ƷǺ��ֵ�Ŀ�괮
      des += src.substring(k,i);
      //��@@��û���㹻��λ�������ʾ��ǰ@@���Ǻ��ֵ���ʼ����
      if(i + 6 > len) {
        //��ʣ�µ��ַ������Ƶ�Ŀ�괮
        des += src.substring(k,len);
        break;
      }
      //ȡ@@�����λunicode����
      String ts = src.substring(i + 2,i + 6);
      try {
        //Ŀ�괮��һ������
        char tc = (char)Integer.parseInt(ts,16);
        des += tc;
        k = i + 6;
      }
      catch (Exception ex) {
        //ת���������ʾ@@���Ǻ��ֱ���
        des += "@";
        k = i + 1;
      }
    }
    return des;
  }

}