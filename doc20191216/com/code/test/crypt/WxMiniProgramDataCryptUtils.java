package com.code.test.crypt;

import javax.xml.bind.DatatypeConverter;

/**
 * @author wanghongen
 * @date 8/22/18 2:49 PM
 */
public class WxMiniProgramDataCryptUtils {

  public static String decryptData(String encryptedData, String iv, String sessionKey) {
    if (iv.length() != 24 || sessionKey.length() != 24) {
      return null;
    }
    System.out.println("解密前==encryptedData==" + encryptedData);
    System.out.println("解密前==iv==" + iv);
    System.out.println("解密前==sessionKey==" + sessionKey);
    String data = AesCbc128.decrypt(
        DatatypeConverter.parseBase64Binary(sessionKey),
        DatatypeConverter.parseBase64Binary(iv),
        DatatypeConverter.parseBase64Binary(encryptedData)
    );

    System.out.println("解密后==data==" + data);
    data = AesCbc128.decode(data);
    System.out.println("解密后data==进行编码==" + encryptedData);
    return data;
  }

  public static void main(String[] args) {
    String encryptedData = "6mwXWXeAbtm6kjMFgyELq7EUZwsY+je7QftT9jtBZfxt9RaG9WyJ9Vdhyo917GZD40B5huNvIj38mhOpxeue/WrpEbfzxe81OUxMmTXMnkjABe4lsXjdCjvEj+QDOZysiqAuNlF9SMIE/3miBQ1tgBaQEcERBiuqAr1dwndniEWvxQ3hvJqqiyiB/Ot1k40p7RVrj7JLSbP3SqUZs3l68gGwdVT2n/hZSSf9opWjfH2kXuqFDWqsOVRQunOMaYYVmOME3Rd3acOHhz13auByoFhYAIVgQzjH1IJtdF3hxaKTEvCL8vInrVRP96vhQvIVqHeQeQ4U4RQdVCWWybalKN7uvzgjI5WLtQr/sssv+XdLIRvSfpsr0LvPEKeeA5HCO6n89YdBXih/suQUwwOIxc1fkIws9gw98PqwpdcUrCrn/JsyWuqv4s/PB6L0L/QIdQB1STLJLOgsAVh78IScFTCTpXZ6ddyegP2qh7mlE76YIcLP9nR8Y5ohkBCsFjk4TiShZ2l9KPZRrpH1nGKnNQ==";

    String iv = "q8ty67mhwoaKevXPsDrp2A==";
    String sessionKey = "4Sv9iB0KUwQrA+S2aTseiA==";

    //decryptData(encryptedData, iv, sessionKey);

    encryptedData="Ma1zDSagXh8KjA56biU6g+eQxn9qR+vDGi+WXPKraZHnl4SQtGN60YJcxywgS6X8J9Gr3G+r63YpSfhOH8YDlyIuKWHZ9loRp7fOAQWb4L5ynf5uSWWh5kESIy7obyoNAlXMNjUEfYWcyGYoTtMcKJv7eFngYlNi4Z7ERXbcGc8kVztxn4wJBD5hnHNFOxs+uFBr1cGMN1dQspo2SnYyaISVhPB8zhwj6CD1/Z/CsaYJabO8Epg/qa590HI5//jnevY0zvbnM1TKsIgmZbNmVtlL4DdVPQq5A5sVAOsYPVB49iIXssey4DU47ubbbpxZcJHMBhfICcNfvgzYNa7/W/8+yuOCrIApreOuc2rYJ1Qpt022PNr5+SG3kJq4vAjP/Ibd3nvZWPdbasabiz+S1a4DYHehdpGP862UWKcW9DR5t5iNDZ1l/Ict3DcEYtEFboMgFn2rIZCAzS0YhGzt7loOTzoohZ75BqEdY+byRVlD6vZ0s8s4+cOL/ExrdD/8smZ6g22mKG4gBL5kRgz5Tg==";

    encryptedData="QfddXE5CKis6xU4oY5TRDifdNpQN3zJLy0hyfuFyO2hyYmFnmsDOCWnMq3XewewvmAB8SaiFNVwGmg7nOf70OporDJcW7rDokaYy9uismNvH/zTQJuOvuO646CQ2du9DzAzL0BD9OZ27nIUJXEnnPDj7P26SU9vvlooU8+yoCNiW4qRqJZiuV4G64yufzx7KooY5E7+0CLWgS7nJ+0tK1sv1X6epXiTu8bBzdW29lv1tDDTcb/DqmGFRa/3UrFUbotXlJTNxxLvr1sQQ2XJkQytf3WRpZLfmPARyGw9nG+DQZ9TsCr1N9pK7h6j1uDXtBjM7JZkbbZHbJmnjCqn0F41jqIya6YACZpWKn0AV5Ww17FaWRLOlrQJbR3zeqcP9R4B8YGdi0Xf2pBFnzGTAJBotwjoRK2nElgD9EiO6XTOBWAB/O3/wtj8+1qN1dG7l9UHjmCZWbKHE8L6GoZLni2hGVcuNTlq1z4mFJVuzlXYPHlkZzDUk2j+JXlR6MOV5XuCUFEmsBdbaHOU7bzblNw==";
        iv="rImmgXES+WIwy2KTKMH7GA==";
    sessionKey = "8RkDobi7GDjfKSwy85rc4g==";

    decryptData(encryptedData, iv, sessionKey);
  }
}
