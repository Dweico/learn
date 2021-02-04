package com.yangya.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.service.WxPayService;
import com.yangya.common.CommonResult;
import com.yangya.model.PmsBrand;
import com.yangya.v3.KeyPairFactory;
import com.yangya.v3.h5.H5Request;
import com.yangya.v3.utils.SignatureProvider;
import com.yangya.v3.utils.WechatMetaBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.Base64Utils;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by macro on 2019/9/1.
 */
@Api(tags = "v3pay", description = "v3pay")
@Slf4j
@RestController
@RequestMapping("/v3pay")
public class V3PayController {

    private static Logger LOGGER = LoggerFactory.getLogger(V3PayController.class);

    @Autowired
    private SignatureProvider signatureProvider;

    @Autowired
    private WxPayService wxPayService;

/*    @Autowired
    private CloseableHttpClient httpClient;*/

    @ApiOperation("verify")
    @GetMapping("/verify")
    public CommonResult verify() throws Exception {
        WechatMetaBean wechatMetaBean = signatureProvider.getWechatMetaBean();
        Signature signer = Signature.getInstance("SHA256withRSA");
        // 私钥加签
        signer.initSign(wechatMetaBean.getKeyPair().getPrivate());

        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println("appid = wx55a75ae9fd5d3b78");
        System.out.println("timestamp = " + timestamp);
        IdGenerator ID_GENERATOR = new AlternativeJdkIdGenerator();
        String nonceStr = ID_GENERATOR.generateId()
                .toString()
                .replaceAll("-", "");
        System.out.println("nonceStr = " + nonceStr);
        String prepay_id = "wx201410272009395522657a690389285100";
        System.out.println("prepay_id = " + prepay_id);
        String signatureStr = Stream.of("wx55a75ae9fd5d3b78", String.valueOf(timestamp), nonceStr, prepay_id)
                .collect(Collectors.joining("\n", "", "\n"));

        signer.update(signatureStr.getBytes(StandardCharsets.UTF_8));
        String encode = Base64Utils.encodeToString(signer.sign());
        // 公钥 验证签名
        signer.initVerify(wechatMetaBean.getKeyPair().getPublic());
        signer.update(signatureStr.getBytes(StandardCharsets.UTF_8));
        boolean verify = signer.verify(Base64Utils.decode(encode.getBytes(StandardCharsets.UTF_8)));
        return CommonResult.success(verify, "操作成功");
    }

    @ApiOperation("HttpUtil使用：Http请求工具类")
    @GetMapping("/httpUtil")
    public CommonResult httpUtil() {
        KeyPairFactory keyPairFactory = new KeyPairFactory();
        KeyPair pkcs12 = keyPairFactory.createPKCS12("apiclient_cert.p12", "Tenpay Certificate", "1603632316");
        PrivateKey aPrivate = pkcs12.getPrivate();
        PublicKey aPublic = pkcs12.getPublic();
        return CommonResult.success(null, "操作成功");
    }

    @ApiOperation("qqq")
    @GetMapping("/qqq")
    public CommonResult qqq() {
        String payBaseUrl = wxPayService.getPayBaseUrl();
        return CommonResult.success(payBaseUrl, "操作成功");
    }

    @ApiOperation("H5Pay")
    @PostMapping("/H5Pay")
    public CommonResult H5Pay(@RequestBody H5Request request) {
        log.error("h5request={}",JSON.toJSONString(request));
        return CommonResult.success(request, "操作成功");
    }

    /*@ApiOperation("post")
    @GetMapping("/post")
    public CommonResult post(String requestStr) throws IOException {
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi");
        StringEntity stringEntity = new StringEntity(requestStr, ContentType.create("application/json", "utf-8"));
        httpPost.setEntity(stringEntity);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");
        String serialNumber = "43F2100478BD9980801A1F822331318A528946B3";
        //String serialNumber = wxV3Service.getConfig().getVerifier().getValidCertificate().getSerialNumber().toString(16).toUpperCase();
        httpPost.addHeader("Wechatpay-Serial", serialNumber);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        String responseString = "{}";
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        }
        log.error("responseString={}",responseString);
        return CommonResult.success(responseString, "操作成功");
    }

    @ApiOperation("certificates")
    @GetMapping("/certificates")
    public CommonResult certificates() throws Exception {

        URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/certificates");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        String responseString = "{}";
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        }
        log.error("responseString={}",responseString);
        return CommonResult.success(responseString, "操作成功");
    }

    @ApiOperation("h5")
    @GetMapping("/h5")
    public CommonResult h5(String requestStr) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(requestStr);
        jsonObject.put("notify_url","http://you.xiudo.cn/pay/wx");
        jsonObject.put("notify_url","http://you.xiudo.cn/pay/wx");
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/combine-transactions/h5");
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(jsonObject), ContentType.create("application/json", "utf-8"));
        httpPost.setEntity(stringEntity);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");
        String serialNumber = "20892443DCFD03BC110751DC1E4F16ACE0DFC2EB";
        //String serialNumber = wxV3Service.getConfig().getVerifier().getValidCertificate().getSerialNumber().toString(16).toUpperCase();
        httpPost.addHeader("Wechatpay-Serial", serialNumber);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        String responseString = "{}";
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        }
        log.error("responseString={}",responseString);
        return CommonResult.success(responseString, "操作成功");
    }*/


}
