package com.yangya.config.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wxpay pay properties.
 *
 * @author Binary Wang
 */
@Data
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayProperties {
  /**
   * 设置微信公众号或者小程序等的appid
   */
  private String appId;

  /**
   * 微信支付商户号
   */
  private String mchId;

  /**
   * 微信支付商户密钥
   */
  private String mchKey;

  /**
   * 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
   */
  private String subAppId;

  /**
   * 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
   */
  private String subMchId;

  /**
   * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
   */
  private String keyPath;

  /**
   * apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String privateKeyPath;
  /**
   * apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String privateCertPath;

  /**
   * apiV3 秘钥值.
   */
  private String apiV3Key;

  /**
   * apiV3 证书序列号值
   */
  private String certSerialNo;

}
