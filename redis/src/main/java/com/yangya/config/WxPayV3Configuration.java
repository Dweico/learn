//package com.yangya.config;
//
//import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
//import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
//import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
//import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
//import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
//import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
//import com.yangya.v3.KeyPairFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.ByteArrayInputStream;
//import java.io.UnsupportedEncodingException;
//import java.security.KeyPair;
//import java.security.KeyStore;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//
///**
// * @author Binary Wang
// */
//@Configuration
//public class WxPayV3Configuration {
//
//  private String mchId = "1603632316"; // 商户号
//  //private String mchSerialNo = "20892443DCFD03BC110751DC1E4F16ACE0DFC2EB"; // 商户证书序列号
//  private String mchSerialNo = "43F2100478BD9980801A1F822331318A528946B3";
//
//  private String apiV3Key = "WSD57KkSGVlrKIXvRleA4zEsXYwS65WH";
//
//  private PublicKey publicKey;
//
//  private PrivateKey privateKey;
//
//  private AutoUpdateCertificatesVerifier verifier;
//  @Bean
//  public CloseableHttpClient wxHttpClient() throws Exception {
//    ClassPathResource resource = new ClassPathResource("apiclient_cert.p12");
//    char[] pem = mchId.toCharArray();
//      KeyStore store = KeyStore.getInstance("PKCS12");
//      store.load(resource.getInputStream(), pem);
//      X509Certificate certificate = (X509Certificate) store.getCertificate("Tenpay Certificate");
//      certificate.checkValidity();
//      // 证书的序列号 也有用
//      String serialNumber = certificate.getSerialNumber().toString(16).toUpperCase();
//      // 证书的 公钥
//      PublicKey publicKey = certificate.getPublicKey();
//      // 证书的私钥
//      PrivateKey privateKey = (PrivateKey) store.getKey("Tenpay Certificate", pem);
//
//    this.mchSerialNo= serialNumber;
//    this.publicKey = publicKey;
//    this.privateKey=privateKey;
//    ArrayList<X509Certificate> listCertificates = new ArrayList<>();
//    listCertificates.add(certificate);
//
//    return WechatPayHttpClientBuilder.create()
//            .withMerchant(mchId, serialNumber, privateKey)
//            .withWechatpay(listCertificates)
//            .build();
//  }
//
///*  @Bean
//  public CloseableHttpClient wxHttpClient () throws Exception {
//    KeyPairFactory keyPairFactory = new KeyPairFactory();
//    KeyPair tenpay_certificate = keyPairFactory.createPKCS12("apiclient_cert.p12", "Tenpay Certificate", "1603632316");
//// 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3秘钥）
//    AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
//            new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, tenpay_certificate.getPrivate())),apiV3Key.getBytes("utf-8"));
//
//    // 初始化httpClient
//    return WechatPayHttpClientBuilder.create()
//            .withMerchant(mchId, mchSerialNo, tenpay_certificate.getPrivate())
//            .withValidator(new WechatPay2Validator(verifier)).build();
//  }*/
//
//}
