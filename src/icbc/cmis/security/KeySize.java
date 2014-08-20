/*
 * �������� 2004-11-22
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package icbc.cmis.security;

/**
 * @author ZJFH-yanb
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class KeySize {
  private final int size;
  
  private KeySize(int size){
  	this.size = size;
  }
  
  public static final KeySize Bits128 = new KeySize(128);
  public static final KeySize Bits192 = new KeySize(192);
  public static final KeySize Bits256 = new KeySize(256);
}
