package icbc.cmis.util;

import icbc.cmis.base.TranFailException;
import java.util.*;
import java.text.DecimalFormat;
import icbc.cmis.base.CmisConstance;
import java.io.UnsupportedEncodingException;

/**
 * <p>Title: DSR��������ʱ�Ĺ��õ�У�鹤��</p>
 * <p>Description: �����Ĺ����У�</p>
 * <p>1 �����ڡ����ʡ�������������ǰ�ĸ�ʽ��</p>
 * <p>2 ͨѶ��У���ֶεļ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ICBC HZ</p>
 * @author zheng ze zhou
 * @version 1.0
 * modify zhengzezhou 20060802 ������17λ�˺ŵ�У��λ����
 */

public class DsrCommCheckTools {
	/**
	 * ��ȫϵ���㷨���õı���
	 */
	private String v_ACCNO1;

	private String v_ACCNO2;

	private String v_AMOUNT1;

	private String v_AMOUNT2;

	private String v_AMOUNT3;

	private String v_TRXSQNB;

	private String v_SAFEPARA;

	/**
	 * Ĭ��ϵͳ��Ա��Կ
	 */
	private static final String constTsfKey = "12345678";

	/**
	 * �����㷨������������
	 */
	private static final char[] constArray = { '3', '7', '0', '5', '6', '8',
			'9', '3' };

	public DsrCommCheckTools() {
	}

	/**
	 * ��ʼ��������
	 * 1 ����ʺ� accno1 17λ
	 * 2 �����ʺ� accno2 17λ
	 * 3 ������ amount1 17λ
	 * 4 ������ˮ�� tradesqno 8λ
	 */
	public DsrCommCheckTools(String accno1, String accno2, double amount1,
			String tradesqno) {
		this.v_ACCNO1 = accno1;
		this.v_ACCNO2 = accno2;
		this.v_AMOUNT1 = DsrCommCheckTools.fmtElementContent(fmtAmount(String
				.valueOf(amount1)), 17, '0', 'L');
		this.v_AMOUNT2 = "00000000000000000";
		this.v_AMOUNT3 = "00000000000000000";
		this.v_TRXSQNB = DsrCommCheckTools.fmtElementContent(tradesqno, 8, '0',
				'L');
	}

	/**
	 * ��ʼ��������
	 * 1 ����ʺ� accno1 17λ
	 * 2 ������ˮ�� tradesqno 8λ
	 */
	public DsrCommCheckTools(String accno1, String tradesqno) {
		this.v_ACCNO1 = accno1;
		this.v_ACCNO2 = "00000000000000000";
		this.v_AMOUNT1 = "00000000000000000";
		this.v_AMOUNT2 = "00000000000000000";
		this.v_AMOUNT3 = "00000000000000000";
		this.v_TRXSQNB = DsrCommCheckTools.fmtElementContent(tradesqno, 8, '0',
				'L');
	}

	/**
	 * ��ʼ��������
	 * 1 accno1 17λ
	 * 2 accno2 17λ
	 * 3 amount1 17λ
	 * 4 amount2 17λ
	 * 5 amount3 17λ
	 * 4 tradesqno 8λ
	 */
	public DsrCommCheckTools(String accno1, String accno2, double amount1,
			double amount2, double amount3, String tradesqno) {
		this.v_ACCNO1 = accno1;
		this.v_ACCNO2 = accno2;
		this.v_AMOUNT1 = DsrCommCheckTools.fmtElementContent(fmtAmount(String
				.valueOf(amount1)), 17, '0', 'L');
		this.v_AMOUNT2 = DsrCommCheckTools.fmtElementContent(fmtAmount(String
				.valueOf(amount2)), 17, '0', 'L');
		this.v_AMOUNT3 = DsrCommCheckTools.fmtElementContent(fmtAmount(String
				.valueOf(amount3)), 17, '0', 'L');
		this.v_TRXSQNB = DsrCommCheckTools.fmtElementContent(tradesqno, 8, '0',
				'L');
	}

	/**
	 * ��ʼ������
	 */
	public void init(Vector v_para) {
	}

	/**
	 * ��ȫϵ��У����㷨
	 */
	public final String checkSafePara() throws Exception, TranFailException {
		try {
			/* ACCNO_NUM = ACCNO1_NUM @ ACCNO2_NUM */
			char[] accno1_bit = v_ACCNO1.substring(0, 17).toCharArray();
			char[] accno2_bit = v_ACCNO2.substring(0, 17).toCharArray();
			for (int i = 0; i < 17; i++) {
				int tmp1 = convertCharToInt(accno1_bit[i])
						+ convertCharToInt(accno2_bit[i]);
				accno1_bit[i] = getAddResultBit(tmp1);
			}
			String v_ACCNO_NUM = String.valueOf(accno1_bit);
			/* AMOUNT_NUM = AMOUNT1_NUM + AMOUNT2_NUM + AMOUNT3_NUM */
			char[] amount1_bit = v_AMOUNT1.substring(0, 17).toCharArray();
			char[] amount2_bit = v_AMOUNT2.substring(0, 17).toCharArray();
			char[] amount3_bit = v_AMOUNT3.substring(0, 17).toCharArray();
			for (int i = 0; i < 17; i++) {
				int tmp1 = convertCharToInt(amount1_bit[i])
						+ convertCharToInt(amount2_bit[i])
						+ convertCharToInt(amount3_bit[i]);
				amount1_bit[i] = getAddResultBit(tmp1);
			}
			String v_AMOUNT_NUM = String.valueOf(amount1_bit);
			/* K = KEY @ Z1 @ Z2 @ Y1 @ Y2 @ Y3 @ 37056893 */
			char[] key_bit = constTsfKey.substring(0, 8).toCharArray();
			char[] z1_bit = v_ACCNO_NUM.substring(0, 8).toCharArray();
			char[] mid_bit = v_ACCNO_NUM.substring(8, 9).toCharArray();
			char[] z2_bit = v_ACCNO_NUM.substring(9, 17).toCharArray();
			char[] y1_bit = v_AMOUNT_NUM.substring(1, 9).toCharArray();
			char[] y2_bit = v_AMOUNT_NUM.substring(9, 17).toCharArray();
			char[] y3_bit = v_TRXSQNB.substring(3, 8).toCharArray();

			char[] kk_bit = new char[8];
			int tmpkk;
			tmpkk = convertCharToInt(key_bit[0]) + convertCharToInt(z1_bit[0])
					+ convertCharToInt(z2_bit[0]) + convertCharToInt(y1_bit[0])
					+ convertCharToInt(y2_bit[0])
					+ convertCharToInt(constArray[0]);
			kk_bit[0] = getAddResultBit(tmpkk);
			tmpkk = convertCharToInt(key_bit[1]) + convertCharToInt(z1_bit[1])
					+ convertCharToInt(z2_bit[1]) + convertCharToInt(y1_bit[1])
					+ convertCharToInt(y2_bit[1])
					+ convertCharToInt(constArray[1]);
			kk_bit[1] = getAddResultBit(tmpkk);
			tmpkk = convertCharToInt(key_bit[2]) + convertCharToInt(z1_bit[2])
					+ convertCharToInt(z2_bit[2]) + convertCharToInt(y1_bit[2])
					+ convertCharToInt(y2_bit[2]) + convertCharToInt(y3_bit[0])
					+ convertCharToInt(constArray[2]);
			kk_bit[2] = getAddResultBit(tmpkk);
			tmpkk = convertCharToInt(key_bit[3]) + convertCharToInt(z1_bit[3])
					+ convertCharToInt(z2_bit[3]) + convertCharToInt(y1_bit[3])
					+ convertCharToInt(y2_bit[3]) + convertCharToInt(y3_bit[1])
					+ convertCharToInt(constArray[3]);
			kk_bit[3] = getAddResultBit(tmpkk);
			tmpkk = convertCharToInt(key_bit[4]) + convertCharToInt(z1_bit[4])
					+ convertCharToInt(z2_bit[4]) + convertCharToInt(y1_bit[4])
					+ convertCharToInt(y2_bit[4]) + convertCharToInt(y3_bit[2])
					+ convertCharToInt(constArray[4]);
			kk_bit[4] = getAddResultBit(tmpkk);
			tmpkk = convertCharToInt(key_bit[5]) + convertCharToInt(z1_bit[5])
					+ convertCharToInt(z2_bit[5]) + convertCharToInt(y1_bit[5])
					+ convertCharToInt(y2_bit[5]) + convertCharToInt(y3_bit[3])
					+ convertCharToInt(constArray[5]);
			kk_bit[5] = getAddResultBit(tmpkk);
			tmpkk = convertCharToInt(key_bit[6]) + convertCharToInt(z1_bit[6])
					+ convertCharToInt(z2_bit[6]) + convertCharToInt(y1_bit[6])
					+ convertCharToInt(y2_bit[6]) + convertCharToInt(y3_bit[4])
					+ convertCharToInt(constArray[6]);
			kk_bit[6] = getAddResultBit(tmpkk);
			tmpkk = convertCharToInt(key_bit[7]) + convertCharToInt(z1_bit[7])
					+ convertCharToInt(z2_bit[7]) + convertCharToInt(y1_bit[7])
					+ convertCharToInt(y2_bit[7])
					+ convertCharToInt(mid_bit[0])
					+ convertCharToInt(constArray[7]);
			kk_bit[7] = getAddResultBit(tmpkk);
			/* ��ɢ�任��K -> H */
			char[] hh_bit = new char[8];
			int tmphh;
			tmphh = convertCharToInt(kk_bit[7]) + convertCharToInt(kk_bit[0])
					+ convertCharToInt(kk_bit[1]);
			hh_bit[0] = getAddResultBit(tmphh);
			tmphh = convertCharToInt(kk_bit[0]) + convertCharToInt(kk_bit[1])
					+ convertCharToInt(kk_bit[2]);
			hh_bit[1] = getAddResultBit(tmphh);
			tmphh = convertCharToInt(kk_bit[1]) + convertCharToInt(kk_bit[2])
					+ convertCharToInt(kk_bit[3]);
			hh_bit[2] = getAddResultBit(tmphh);
			tmphh = convertCharToInt(kk_bit[2]) + convertCharToInt(kk_bit[3])
					+ convertCharToInt(kk_bit[4]);
			hh_bit[3] = getAddResultBit(tmphh);
			tmphh = convertCharToInt(kk_bit[3]) + convertCharToInt(kk_bit[4])
					+ convertCharToInt(kk_bit[5]);
			hh_bit[4] = getAddResultBit(tmphh);
			tmphh = convertCharToInt(kk_bit[4]) + convertCharToInt(kk_bit[5])
					+ convertCharToInt(kk_bit[6]);
			hh_bit[5] = getAddResultBit(tmphh);
			tmphh = convertCharToInt(kk_bit[5]) + convertCharToInt(kk_bit[6])
					+ convertCharToInt(kk_bit[7]);
			hh_bit[6] = getAddResultBit(tmphh);
			tmphh = convertCharToInt(kk_bit[6]) + convertCharToInt(kk_bit[7])
					+ convertCharToInt(kk_bit[0]);
			hh_bit[7] = getAddResultBit(tmphh);
			return String.valueOf(hh_bit);
		} catch (Exception ex) {
			throw new TranFailException("DsrCommCheckErr001",
					"icbc.cmis.util.DsrCommCheckTools", "���㰲ȫ��ʱ����", ex
							.toString());
		}
	}

	private static int convertCharToInt(char convertChar) {
		return Integer.parseInt(String.valueOf(convertChar));
	}

	private static char getAddResultBit(int numbit) {
		String tmpS = String.valueOf(numbit);
		return tmpS.toCharArray()[tmpS.length() - 1];
	}

	/**
	 * <p>�����ݰ������ݽ��и�ʽ��</p>
	 * @param String OriginalString Ԫ����
	 * @param int TotalLength �ֶζ���
	 * @param char AttachedChar Ҫ���ϵ��ַ�����ո����
	 * @param char RorL �󲹻��Ҳ� 'R' �Ҳ��� 'L' ��
	 * @return String ���볤�Ⱥ���ַ���
	 */
	public static String fmtElementContent(String OriginalString,
			int TotalLength, char AttachedChar, char RorL) {
		if (OriginalString == null) {
			OriginalString = "";
		}
		int LofS = DsrCommCheckTools.getBytesLength(OriginalString); //ԭ�ַ�������
		String tempS = OriginalString;
		if (LofS >= TotalLength) { //���ԭ�ַ���������󳤶�
			try {
				return OriginalString.substring(0, TotalLength); //��ȡ�ֶζ���
			} catch (Exception ex) {
				return OriginalString;
			}
		} else {
			for (int i = 0; i < TotalLength - LofS; i++) {
				if (RorL == 'R') {
					tempS = tempS.concat(String.valueOf(AttachedChar));
				} else {
					tempS = String.valueOf(AttachedChar).concat(tempS);
				}
			}
			return tempS;
		}
	}

	/**
	 * <b>��������: </b>�˺������ڼ�����и�ʽ�����ַ�ת�����ֽڵ��ֽ���<br>
	 * <p>ע��������������	</p>
	 * @see
	 * @param String ԴString
	 * @return int �ֽ���
	 */
	public static int getBytesLength(String srcStr) {
		if (srcStr == null) {
			return 0;
		}
		String encoding = (String) CmisConstance.getParameterSettings().get(
				"dsrEndoding");
		if (encoding == null || encoding.trim().length() == 0) {
			encoding = "GBK";
		}
		try {
			return (srcStr.getBytes(encoding)).length;
		} catch (UnsupportedEncodingException ex) {
			return srcStr.length();
		}
	}

	/**
	 * <p>�Խ�����ݽ��и�ʽ��</p>
	 * <p>����Լ����������CM2002���ݿ��еĽ�����ݴ�С�������λ���������������������־����ܴ�С���㣻</p>
	 * <p>���Խ������Ҫ��100�����Է�Ϊ��λ����������</p>
	 * @param String ����ǰ�Ľ������
	 * @return String ������Ľ������
	 */
	public static String fmtAmount(String OriginalAmount) {
		if (OriginalAmount == null || OriginalAmount.equals("")) {
			OriginalAmount = "0";
		}
		java.text.DecimalFormat decimalFmt = new java.text.DecimalFormat();
		decimalFmt.applyPattern("#0.00#");
		double tmpD = 0;
		try {
			tmpD = Double.parseDouble(OriginalAmount);
		} catch (NumberFormatException ex) {
		}
		tmpD = tmpD * 100; //�Խ�����ݳ���100���Ա�������С��
		String tmpS = decimalFmt.format(tmpD);
		return tmpS.substring(0, tmpS.indexOf("."));
	}

	/**
	 * ע���˷����Ѿ����ڣ������fmtRateAll 2005-6-1
	 * <p>��CM2002�е����ʣ�һ�����������ʣ����и�ʽ�����Ա����������� </p>
	 * <p>CM2002���ݿ��б�������ʴ�С���㣬����������ʱ���ܴ�С���㣬���� </p>
	 * <p>����Լ���������������ȳ���1000000������8λ��ǰ��0���ٸ��ݿ��ز���ѡ����ǰλ��9��0 </p>
	 * <p>ע���˺����ܹ�������������ʵķ�Χ [0,100)  </p>
	 * @param String ����ǰ����������
	 * @param char �Ƿ�ǰ��9 - 'Y'��'9' ��9��'N'��'0' ��0
	 * @return String ���������������(С�������ܲ�ֹ��λ)
	 * @deprecated 2005-6-1
	 */
	public static String fmtRate(String OriginalRate, char Pitch9orNot) {
		if (OriginalRate == null || OriginalRate.equals("")) {
			OriginalRate = "0";
		}
		java.text.DecimalFormat decimalFmt = new java.text.DecimalFormat();
		decimalFmt.applyPattern("#0.00####");
		double tmpD = Double.parseDouble(OriginalRate);
		tmpD = tmpD * 1000000; //���������ݳ���1000000
		String tmpS = decimalFmt.format(tmpD);
		tmpS = tmpS.substring(0, tmpS.indexOf(".")); //���������ݽ��й���
		int LofS = tmpS.length(); //�������ݹ�����ĳ���
		if (Pitch9orNot == 'Y' || Pitch9orNot == '9') {
			for (int i = 0; i < 8 - LofS; i++) { //Ҫ��9�����
				tmpS = "0" + tmpS;
			}
			tmpS = "9" + tmpS;
		} else {
			for (int i = 0; i < 9 - LofS; i++) { //ȫ��0�����
				tmpS = "0" + tmpS;
			}
		}
		return tmpS;
	}

	/**
	 * ע���˷����Ѿ����ڣ������fmtRateAll 2005-6-1
	 * <p>��CM2002�е����ʣ�һ�����������ʣ����и�ʽ�����Ա����������� For ����ֱ�Ӽ��� 2004-10-8 </p>
	 * <p>CM2002���ݿ��б�������ʴ�С���㣬����������ʱ���ܴ�С���㣬���� </p>
	 * <p>����Լ���������������ȳ���100000000������9λǰ��0�������������ԭ�����������ϸ�д�� </p>
	 * <p>ע���˺�����������ָ�����ٷֺŵ����� ����ٷ�֮ʮһ���˴���ڲ�������"0.11" </p>
	 * @param String ����ǰ����������(С�������ܲ�ֹ��λ)
	 * @return String ���������������
	 * @deprecated 2005-6-1
	 */
	public static String fmtRateNew(String OriginalRate) {
		if (OriginalRate == null || OriginalRate.equals("")) {
			OriginalRate = "0";
		}
		java.text.DecimalFormat decimalFmt = new java.text.DecimalFormat();
		decimalFmt.applyPattern("#0.00####");
		double tmpD = Double.parseDouble(OriginalRate);
		tmpD = tmpD * 100000000; //���������ݳ���һ������
		String tmpS = decimalFmt.format(tmpD);
		tmpS = tmpS.substring(0, tmpS.indexOf(".")); //���������ݽ��й���
		int LofS = tmpS.length(); //�������ݹ�����ĳ���

		for (int i = 0; i < 9 - LofS; i++) { //����ǰ��0
			tmpS = "0" + tmpS;
		}
		return tmpS;
	}

	/**
	 * <p>˵�������ʼ����ʸ����ʸ�ʽ������</p>
	 * <p>��;��̨�������ʼ����ʸ����ʵĸ�ʽ��������ͬ��Ϊ������������ȷ��ʶ������������ʱ����Ҫ���øú���</p>
	 * <p>     �����͵����ʼ����ʸ������ֶν��и�ʽ��</p>
	 * <p>����ʱ������������ǰ</p>
	 * <p>��Ҫ���õ��ֶΣ�̨������������أ��ֿ��ܴ���С����ģ����������ֶΣ�</p>
	 * <p>������չ�ڣ����ʸ�����</p>
	 * <p>����ִ�����ʻ���������</p>
	 * <p>�������ʸ�����</p>
	 * @param String OriginalRate ����ǰ��̨�����ݸ�ʽ
	 * @param String FormatType 0 ����ֵ���������Σ� 1 ����ֵ������������ 2 ����
	 * @return String ��������������ݸ�ʽ
	 * @example:
	 * 1 ���Ҫ�����Ը������μ�����������ʸ����ʣ���OriginalRate��̨�ʸ�ʽ�����ʣ�FormatType��0
	 *   ���ã�DsrCommCheckTools.fmtRateAll("10","0")
	 * 2 ���Ҫ��������ֵ����OriginalRate��̨�ʸ�ʽ���ʣ�FormatType��2
	 *   ���ã�DsrCommCheckTools.fmtRateAll("5.58","2")
	 *
	 * ����ʵ����JUnit����ͨ����
	 *     assertEquals("-999123456",DsrCommCheckTools.fmtRateAll("-99.123456","1"));
	 *     assertEquals("999123456",DsrCommCheckTools.fmtRateAll("99.123456","1"));
	 *     assertEquals("-900123456",DsrCommCheckTools.fmtRateAll("-0.123456","1"));
	 *     assertEquals("900123456",DsrCommCheckTools.fmtRateAll("0.123456","1"));
	 *     assertEquals("-901123456",DsrCommCheckTools.fmtRateAll("-1.123456","1"));
	 *     assertEquals("901123456",DsrCommCheckTools.fmtRateAll("1.123456","1"));
	 *
	 *     assertEquals("-099123456",DsrCommCheckTools.fmtRateAll("-99.123456","2"));
	 *     assertEquals("099123456",DsrCommCheckTools.fmtRateAll("99.123456","2"));
	 *     assertEquals("899123456",DsrCommCheckTools.fmtRateAll("899.123456","2"));
	 *     assertEquals("001123456",DsrCommCheckTools.fmtRateAll("1.123456","2"));
	 *     assertEquals("-001123456",DsrCommCheckTools.fmtRateAll("-1.123456","2"));
	 *     assertEquals("000123456",DsrCommCheckTools.fmtRateAll("0.123456","2"));
	 *
	 *     assertEquals("-099123456",DsrCommCheckTools.fmtRateAll("-99.123456","0"));
	 *     assertEquals("099123456",DsrCommCheckTools.fmtRateAll("99.123456","0"));
	 *     assertEquals("899123456",DsrCommCheckTools.fmtRateAll("899.123456","0"));
	 *     assertEquals("001123456",DsrCommCheckTools.fmtRateAll("1.123456","0"));
	 *     assertEquals("-001123456",DsrCommCheckTools.fmtRateAll("-1.123456","0"));
	 *     assertEquals("000123456",DsrCommCheckTools.fmtRateAll("0.123456","0"));
	 *
	 */
	public static String fmtRateAll(String OriginalRate, String FormatType) {
		//����У��
		if (OriginalRate == null || OriginalRate.equals("")) {
			OriginalRate = "0";
		}
		//���ַ���ת��Ϊ��ֵ��
		double tmpD;
		try {
			tmpD = Double.parseDouble(OriginalRate);
		} catch (NumberFormatException ex) {
			tmpD = 0;
		}
		//���������ݳ���һ������
		tmpD = tmpD * 1000000;
		//����Ǹ������� ���ټ��ϻ��ȥ��9��
		if (FormatType != null && FormatType.equals("1")) {
			if (tmpD >= 0) {
				tmpD = tmpD + 900000000;
			} else {
				tmpD = tmpD - 900000000;
			}
		}
		//��ʽ�����
		String fmtPattern = "000000000";
		return fmtDecimal(tmpD, fmtPattern);
	}

	public static String fmtDecimal(double originalDouble, String pattern) {
		java.text.DecimalFormat decimalFmt = new java.text.DecimalFormat();
		decimalFmt.applyPattern(pattern);
		String retString = decimalFmt.format(originalDouble);
		return retString;
	}

	/**
	 * ע���˷����Ѿ����ڣ������fmtRateAll 2005-6-1
	 * <p> ��ʽ�������ĸ������Σ� </p>
	 * <p> ̨��ҳ��������Ϊ��(-100,900) �� </p>
	 * ����ֵ�߼���<br>
	 * ��������ֵ*1000000 ����Ĳ��ֲ�0 <br>
	 * @param String ʵ�ʸ�������
	 * @return String ����������������
	 * @author zhangjian
	 * @deprecated 2005-6-1
	 */
	public static String fmtFloatBaddish(String realdot) {
		double tmpd = 0;
		double retd = 0;
		java.text.DecimalFormat decimalFmt = new java.text.DecimalFormat();
		decimalFmt.applyPattern("#0.000000#");

		try {
			if (realdot == null) {
				realdot = "0";
			}
			tmpd = Double.parseDouble(realdot);
		} catch (NumberFormatException ex) {
		}
		tmpd = tmpd * 1000000;
		String tmpS = decimalFmt.format(tmpd);

		if (tmpd < 0) {
			tmpS = tmpS.substring(1, tmpS.indexOf(".")); //���������ݽ��й���
		} else {
			tmpS = tmpS.substring(0, tmpS.indexOf(".")); //���������ݽ��й���
		}

		if (tmpS.length() < 9) {
			int j = 9 - tmpS.length();

			for (int i = 0; i < j; i++) { //����9λǰ��0
				tmpS = "0" + tmpS;
			}
		}
		if (tmpd < 0) { //����Ǹ���,����ǰ�油��һ��"-"
			tmpS = "-" + tmpS;
		}

		return tmpS;
	}

	/**
	 * ע���˷����Ѿ����ڣ������fmtRateAll 2005-6-1
	 * <p> �����ĸ�������ֻ�ܵ������ʵ����λ������10�����λ�����ݿ���ѡ�����100000000,����1000000)�� </p>
	 * <p> ��ΧΪ��-0.0010 �� 0.0010��Ҳ���Ǿ���ֵ������0.0010 �� </p>
	 * ����ֵ�߼���<br>
	 * IF ʵ�ʸ������� > 0 THEN <br>
	 *   ���������������� = ʵ�ʸ������� * 100,000,000(1,000,000) + 900,000,000 <br>
	 * ELSE <br>
	 *   ���������������� = ʵ�ʸ������� * 100,000,000(1,000,000) - 900,000,000 <br>
	 * END <br>
	 * @param String ʵ�ʸ�������
	 * @param char ����������λ�� 'B'�ٷ�λ�� 'W'���λ��
	 * @return String ����������������
	 * @author yangguanrun
	 * @deprecated 2005-6-1
	 */
	public static String fmtFloatDot(String realdot, char format) {
		double tmpd = 0;
		double retd = 0;
		int yinzi = 0;
		yinzi = format == 'B' ? 1000000 : 100000000;
		try {
			if (realdot == null) {
				realdot = "0";
			}
			tmpd = Double.parseDouble(realdot);
		} catch (NumberFormatException ex) {
		}
		if (tmpd > 0) {
			retd = tmpd * yinzi + 900000000;
		} else if (tmpd == 0) {
		} else {
			retd = tmpd * yinzi - 900000000;
		}
		java.text.DecimalFormat decimalFmt = new java.text.DecimalFormat();
		decimalFmt.applyPattern("000000000");
		return String.valueOf(decimalFmt.format(retd));
	}

	/**
	 * <p>���ڸ�ʽת���������ӡ�yyyymmdd������yyyy-mm-dd��</p>
	 * <p>˵������������ʽ�������ڸ�ʽΪ��yyyymmdd����������׼��֤�����������ڸ�ʽΪ��yyyy-mm-dd��</p>
	 * <p>�����Ǳ��浽���ݿ⻹���������������������ʽ��</p>
	 * @param ���ڣ���ʽ��yyyymmdd��
	 * @return ���ڣ���ʽ��yyyy-mm-dd��
	 */
	public static String formatDate(String date) {
		if (date == null || date.length() < 8) {
			date = "00000000";
		}
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		return year.trim() + "-" + month.trim() + "-" + day.trim();
	}

	/**
	 * ���ڸ�ʽת���������ӡ�yyyymmdd������yyyy-mm-dd����yyyy.mm.dd�����ָ��������趨 <p>
	 * ˵������������ʽ�������ڸ�ʽΪ��yyyymmdd����������׼��֤�������ڸ�ʽΪ��yyyy-mm-dd����
	 * �������ձ�־�����ֶ��ǡ�yyyy.mm.dd����ʽ��<p>
	 * @param ���ڣ���ʽ��yyyymmdd��
	 * @return ���ڣ���ʽ��yyyy.mm.dd����yyyy-mm-dd��
	 */
	public static String formatDate(String date, char splitChar) {
		if (date == null || date.length() < 8) {
			date = "00000000";
		}
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		return year.trim() + splitChar + month.trim() + splitChar + day.trim();
	}

	/**
	 * ���ڸ�ʽ��ת���������ӡ�yyyy-mm-dd������yyyymmdd��
	 * ˵�����������´������ڸ�ʽΪ��yyyy-mm-dd�������֤���д洢�ĸ�ʽ�ǡ�yyyymmdd��
	 * @param ���ڣ���ʽ��yyyy-mm-dd��
	 * @return ���ڣ���ʽ��yyyymmdd��
	 */
	public static String trimDate(String hostDate) {
		if (hostDate == null || hostDate.length() != 10) {
			hostDate = "9999-12-31";
		}
		String year = hostDate.substring(0, 4);
		String month = hostDate.substring(5, 7);
		String day = hostDate.substring(8, 10);
		return year + month + day;
	}

	/**
	 * ʱ���ʽ��ת���������ӡ�HH:mm:ss������HHmmss��
	 * ˵�����������´���ʱ���ʽΪ��HH:mm:ss�������֤���д洢�ĸ�ʽ�ǡ�HHmmss��
	 * @param ʱ�䣬��ʽ��HH:mm:ss��
	 * @return ʱ�䣬��ʽ��HHmmss��
	 */
	public static String trimTime(String hostTime) {
		if (hostTime == null || hostTime.length() != 8) {
			hostTime = "99:99:99";
		}
		String hour = hostTime.substring(0, 2);
		String minute = hostTime.substring(3, 5);
		String second = hostTime.substring(6, 8);
		return hour + minute + second;
	}

	/**
	 * ���ݴ����˺ŵ�ǰ17λ����У����
	 * ˵����̨�����ݿⱣ����˺�Ϊ19λ�������17λ�˺ţ���Ҫ׷��У��λ�󱣴�
	 * @param �˺ţ�δ��У��λǰ���˺�
	 * @return У���룬2λ
	 * ���ԣ�12020202101540874 ���� 13
	 *      02000260100000040 ���� 84
	 */
	public static String accountVerifyCode(String AccNo17) {
		String retCode = "00";
		try {
			//�˺Ų���Ϊ��
			if (AccNo17 == null) {
				return "";
			}
			//С��17λ���˺Ų�У��
			if (AccNo17.length() < 17)
				return "";
			//����17λ���˺Ų�׷��У��λ
			if (AccNo17.length() > 17)
				return "";
			int num_so;
			int num_ob;
			num_so = Integer.parseInt(AccNo17.substring(0, 1)) * 11
					+ Integer.parseInt(AccNo17.substring(1, 2)) * 13
					+ Integer.parseInt(AccNo17.substring(2, 3)) * 17
					+ Integer.parseInt(AccNo17.substring(3, 4)) * 19
					+ Integer.parseInt(AccNo17.substring(4, 5)) * 23
					+ Integer.parseInt(AccNo17.substring(5, 6)) * 29
					+ Integer.parseInt(AccNo17.substring(6, 7)) * 31
					+ Integer.parseInt(AccNo17.substring(7, 8)) * 37
					+ Integer.parseInt(AccNo17.substring(8, 9)) * 41
					+ Integer.parseInt(AccNo17.substring(9, 10)) * 43
					+ Integer.parseInt(AccNo17.substring(10, 11)) * 47
					+ Integer.parseInt(AccNo17.substring(11, 12)) * 53
					+ Integer.parseInt(AccNo17.substring(12, 13)) * 59
					+ Integer.parseInt(AccNo17.substring(13, 14)) * 61
					+ Integer.parseInt(AccNo17.substring(14, 15)) * 67
					+ Integer.parseInt(AccNo17.substring(15, 16)) * 71
					+ Integer.parseInt(AccNo17.substring(16, 17)) * 73;
			num_ob = 97 - num_so % 97;
			retCode = DsrCommCheckTools.fmtElementContent(String
					.valueOf(num_ob), 2, '0', 'L');
		} catch (Exception ex) {			
		}
		return retCode;
	}

	public static void main(String[] args) throws Exception {
		DsrCommCheckTools dsrCommCheckTools1 = new DsrCommCheckTools(
				"02000260100150069", "02000260090150819", 1, "00000500");
		System.out.println(dsrCommCheckTools1.checkSafePara());
		System.out.println(DsrCommCheckTools.accountVerifyCode("02000260100000040"));
	}
}