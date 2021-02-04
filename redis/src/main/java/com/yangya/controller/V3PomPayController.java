package com.yangya.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.rwocj.wx.service.WxPayV3Service;
import com.yangya.common.CommonResult;
import com.yangya.v3.KeyPairFactory;
import com.yangya.v3.h5.H5Request;
import com.yangya.v3.utils.SignatureProvider;
import com.yangya.v3.utils.WechatMetaBean;
import com.yangya.v3.v2.WxPayV3Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.Base64Utils;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by macro on 2019/9/1.
 */
@Api(tags = "v3pay", description = "v3pay")
@Slf4j
@RestController
@RequestMapping("/v3pompay")
public class V3PomPayController {

    private static Logger LOGGER = LoggerFactory.getLogger(V3PomPayController.class);

    @Autowired
    private WxPayV3Service wxPayV3Service;

/*    @Autowired
    private CloseableHttpClient httpClient;*/

    @ApiOperation("verify")
    @GetMapping("/verify")
    public CommonResult verify() throws Exception {
        String url = "https://api.mch.weixin.qq.com/v3/certificates";
        /*ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("mchid", wxProperties.getPay().getMchId());
        post(url, objectNode);*/
        String s = wxPayV3Service.get("https://api.mch.weixin.qq.com/v3/certificates");
        return CommonResult.success(s, "操作成功");
    }

    @ApiOperation("verify2")
    @GetMapping("/verify2")
    public CommonResult verify2() throws Exception {
        String path="G:\\学习项目\\新建文件夹\\mall\\learn\\redis\\src\\main\\resources\\certdy\\apiclient_key.pem";
        String s = WxPayV3Util.V3Get("v3/certificates", "1603632316", "20892443DCFD03BC110751DC1E4F16ACE0DFC2EB", path);
        return CommonResult.success(s, "操作成功");
    }

}
