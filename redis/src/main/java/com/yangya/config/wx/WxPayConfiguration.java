package com.yangya.config.wx;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.Applyment4SubService;
import com.github.binarywang.wxpay.service.EcommerceService;
import com.github.binarywang.wxpay.service.MerchantMediaService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.Applyment4SubServiceImpl;
import com.github.binarywang.wxpay.service.impl.EcommerceServiceImpl;
import com.github.binarywang.wxpay.service.impl.MerchantMediaServiceImpl;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
@AllArgsConstructor
public class WxPayConfiguration {
  private WxPayProperties properties;


  @Bean(name = "wxPayService")
  public WxPayService wxPayService() {
    WxPayConfig payConfig = new WxPayConfig();
    payConfig.setAppId(StringUtils.trimToNull(this.properties.getAppId()));
    payConfig.setMchId(StringUtils.trimToNull(this.properties.getMchId()));
    payConfig.setMchKey(StringUtils.trimToNull(this.properties.getMchKey()));
    payConfig.setSubAppId(StringUtils.trimToNull(this.properties.getSubAppId()));
    payConfig.setSubMchId(StringUtils.trimToNull(this.properties.getSubMchId()));
    payConfig.setKeyPath(StringUtils.trimToNull(this.properties.getKeyPath()));
    payConfig.setPrivateKeyPath(StringUtils.trimToNull(this.properties.getPrivateKeyPath()));
    payConfig.setPrivateCertPath(StringUtils.trimToNull(this.properties.getPrivateCertPath()));
    payConfig.setApiV3Key(StringUtils.trimToNull(this.properties.getApiV3Key()));
    payConfig.setCertSerialNo(StringUtils.trimToNull(this.properties.getCertSerialNo()));

    // 可以指定是否使用沙箱环境
    payConfig.setUseSandboxEnv(false);

    WxPayService wxPayService = new WxPayServiceImpl();
    wxPayService.setConfig(payConfig);
    return wxPayService;
  }

/*  @Bean
  public MerchantMediaService merchantMediaService(){
    return new MerchantMediaServiceImpl(wxService());
  }

  @Bean
  public EcommerceService ecommerceService(){
    return new EcommerceServiceImpl(wxService());
  }

  @Bean
  public Applyment4SubService applyment4SubService(){
    return new Applyment4SubServiceImpl(wxService());
  }*/

}
