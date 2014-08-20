/*
 * �������� 2004-11-9
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
public class Iterate2 {
  
  
  //��Կ����(����)
  private static final int keyLength = 1;
  
  //��Կ����
  byte[] key = null;
  
  /**
   * <b>��������: </b><br>
   * <p>���캯��</p>
   * @param key ��Կ
   */
  public Iterate2(byte[] key){
  	this.key = key;
  }
  
  /**
   * <b>��������: </b><br>
   * <p>����key</p>
   * @return byte[] ��Կ
   */
  public static byte[] genKey(){ 
  	byte[] key = new byte[Iterate2.keyLength];
  	for(int i=0;i<key.length;i++)
  	  key[i] = (byte)(Math.round((float)Math.random() * 256));
  	return key;
  }
  
  /**
   * <b>��������: </b><br>
   * <p>����</p>
   * @param text ����
   * @return byte[] ����
   * @throws Exception
   */
  public byte[] encode(byte[] text) throws Exception{
	testKey();
	byte[] crypto = new byte[text.length];
	for(int i=0;i<text.length;i++){
	  crypto[i] = (byte)((int)text[i] + (int)key[(i%key.length)]);
	  if(i<key.length)
	    crypto[i] = (byte)(crypto[i] ^ key[i]);
	}
	crypto = reverse(crypto);
	return crypto;
  }
  
  /**
   * <b>��������: </b><br>
   * <p>����</p>
   * @param crypto ����
   * @return byte[] ����
   * @throws Exception(int)crypto[i]
   */
  public byte[] decode(byte[] crypto) throws Exception{
	testKey();
	byte[] text = new byte[crypto.length];
	crypto = reverse(crypto);
	for(int i=0;i<crypto.length;i++){
	  if(i<key.length)
	    crypto[i] = (byte)(crypto[i] ^ key[i]);
	  text[i] = (byte)((int)crypto[i] - (int)key[(i%key.length)]);
	}
	return text;
  }
  
  /**
   * <b>��������: </b><br>
   * <p>������Կ�Ƿ�Ϊ�պͳ����Ƿ����</p>
   */
  private void testKey() throws Exception{
  	if(key == null || key.length<Iterate2.keyLength)
  	  throw new Exception("��ԿΪ�ջ��߳���̫��");
  }
  
  private byte[] reverse(byte[] src){
  	byte[] res = new byte[src.length];
  	for(int i=0;i<res.length;i++)
  	  res[i] = src[src.length-1-i];
  	return res;
  }
  
}
