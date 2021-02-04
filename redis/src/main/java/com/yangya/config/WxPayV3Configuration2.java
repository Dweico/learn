//package com.yangya.config;
//
//import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
//import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
//import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
//import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
//import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
//import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
//import com.yangya.v3.KeyPairFactory;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.StandardCharsets;
//import java.security.KeyPair;
//import java.security.KeyStore;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.Collections;
//
///**
// * @author Binary Wang
// */
//@Configuration
//public class WxPayV3Configuration2 {
//
//  private String mchId = "1603632316"; // 商户号
//  private String mchSerialNo = "20892443DCFD03BC110751DC1E4F16ACE0DFC2EB"; // 商户证书序列号
//  //private String mchSerialNo = "43F2100478BD9980801A1F822331318A528946B3";
//
//  private String apiV3Key = "WSD57KkSGVlrKIXvRleA4zEsXYwS65WH";
//
//  @Bean
//  public CloseableHttpClient initApiV3HttpClient() throws WxPayException {
//    String privateKeyPath = this.getPrivateKeyPath();
//    String privateCertPath = this.getPrivateCertPath();
//    String certSerialNo = this.getCertSerialNo();
//    String apiV3Key = this.getApiV3Key();
//    if (StringUtils.isBlank(privateKeyPath)) {
//      throw new WxPayException("请确保privateKeyPath已设置");
//    }
//    if (StringUtils.isBlank(privateCertPath)) {
//      throw new WxPayException("请确保privateCertPath已设置");
//    }
//    if (StringUtils.isBlank(certSerialNo)) {
//      throw new WxPayException("请确保certSerialNo证书序列号已设置");
//    }
//    if (StringUtils.isBlank(apiV3Key)) {
//      throw new WxPayException("请确保apiV3Key值已设置");
//    }
//
//    InputStream keyInputStream = this.loadConfigInputStream(privateKeyPath);
//    InputStream certInputStream = this.loadConfigInputStream(privateCertPath);
//    try {
//      PrivateKey merchantPrivateKey = PemUtils.loadPrivateKey(keyInputStream);
//
//      AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
//              new WxPayCredentials(mchId, new PrivateKeySigner(certSerialNo, merchantPrivateKey)),
//              apiV3Key.getBytes(StandardCharsets.UTF_8), this.getCertAutoUpdateTime());
//
//      CloseableHttpClient httpClient = WxPayV3HttpClientBuilder.create()
//              .withMerchant(mchId, certSerialNo, merchantPrivateKey)
//              .withWechatpay(Collections.singletonList(PemUtils.loadCertificate(certInputStream)))
//              .withValidator(new WxPayValidator(verifier))
//              .build();
//      this.apiV3HttpClient = httpClient;
//      this.verifier=verifier;
//      this.privateKey = merchantPrivateKey;
//
//      return httpClient;
//    } catch (Exception e) {
//      throw new WxPayException("v3请求构造异常！", e);
//    }
//
//}
