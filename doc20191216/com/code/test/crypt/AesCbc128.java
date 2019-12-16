package com.code.test.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author wanghongen
 * @date 8/22/18 2:50 PM
 */
public class AesCbc128 {
  public static String decrypt(byte[] key, byte[] initVector, byte[] encrypted) {
    try {
      IvParameterSpec iv = new IvParameterSpec(initVector);
      SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

      byte[] original = cipher.doFinal(encrypted);

      return new String(original);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }

  /**
   * 十六进制转换字符串
   */
  private static byte[] hexStr2Bytes(String hexStr) {
    String str = "0123456789ABCDEF";
    char[] hexs = hexStr.toCharArray();
    byte[] bytes = new byte[hexStr.length() / 2];
    int n;
    for (int i = 0; i < bytes.length; i++) {
      n = str.indexOf(hexs[2 * i]) * 16;
      n += str.indexOf(hexs[2 * i + 1]);
      bytes[i] = (byte) (n & 0xff);
    }
    return bytes;
  }

  /**
   * 对解密后的明文进行补位删除
   * @param data 解密后的明文
   * @return 删除填充补位后的明文
   */
  public static String decode(String data) {
    int pad = data.charAt(data.length() -1);
    if(pad < 1 || pad > 32) {
      pad = 0;
    }
    return data.substring(0, data.length() - pad);
  }
}
