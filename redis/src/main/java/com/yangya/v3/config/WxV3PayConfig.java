package com.yangya.v3.config;

import com.yangya.v3.utils.KeyPairFactory;
import com.yangya.v3.utils.SignatureProvider;
import com.yangya.v3.utils.WechatMetaBean;
import com.yangya.v3.utils.WechatPayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxV3PayConfig {

    /**
     * 微信支付V3签名工具.
     *
     * @return the signature provider
     */
    @Bean
    SignatureProvider signatureProvider() {
        KeyPairFactory keyPairFactory = new KeyPairFactory();
        WechatMetaBean wechatMetaBean = keyPairFactory.createPKCS12("apiclient_cert.p12", "Tenpay Certificate", "1603632316");
        return new SignatureProvider(wechatMetaBean);
    }

    @Bean
    WechatPayClient wechatPayClient() {
        return new WechatPayClient(signatureProvider());
    }
}
