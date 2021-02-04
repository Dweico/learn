package com.yangya.v3.v1;

import com.alibaba.fastjson.JSON;

import javax.crypto.BadPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * @author abel lee
 * @create 2020-05-25 10:17
 **/
public class JsonTest {

/*  @Test
  public void testJson() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
    ApplymentInfo applymentInfo = new ApplymentInfo();
    applymentInfo.setBusinessCode("sssss");
    System.out.println(objectMapper.writeValueAsString(applymentInfo));
  }*/

  public static void applyTest() throws Exception {
    String path = "G:\\学习项目\\新建文件夹\\mall\\learn\\redis\\src\\main\\resources\\certdy\\apiclient_cert.pem";
    File file = new File(path);
    X509Certificate x509Certificate = PemUtil.loadCertificate(new FileInputStream(file));
    System.out.println(x509Certificate.getSerialNumber().toString(16));
  }

  public static void decodeStr() throws IOException, BadPaddingException {
    String str = "y92u0gp48pEIFjqgnpeZbroZvThlReoFCzwKbh0XcpNmj165Ji9FQ1PFkxJedCcpggWlPRY4PxKzSwVy8VxdmgRc3lrqHQUnlc2jZ/dg44i0o97aLQFfrQ/fvFFKqFSCUzckrXexwXSPQlniKc0Qkr1rrCYlBn12oYzbqoCS/ejqHvCX6FAEk8/G6gY37mZuXHe6Hft1CxOlRhaSgZm7CG0qkDbrv/RbV8Wuz3YNc72vhuYcl8Sa/6jAj/YDcg5v/dhtbNgDpusZzJJPgyk0q+96iuUtn5W0Ay43rcJyk6M3m3olvGghSKTNvFtrF23TuxjhMap458usyvvw2tiBrQ==";
    String path = "G:\\学习项目\\新建文件夹\\mall\\learn\\redis\\src\\main\\resources\\certdy\\apiclient_key.pem";
    File file = new File(path);
    PrivateKey privateKey = PemUtil.loadPrivateKey(new FileInputStream(file));
    System.out.println(privateKey.getEncoded());
    System.out.println(JSON.toJSONString(privateKey));
   /* String strDec = SpecFieldEncryptFormat.rsaDecryptOAEP(str, privateKey);
    System.out.println(strDec);*/
  }

  public static void main(String[] args) throws Exception {
    decodeStr();
  }


}
