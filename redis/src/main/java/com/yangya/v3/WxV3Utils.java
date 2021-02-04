///*
//package com.yangya.v3;
//import okhttp3.HttpUrl;
//import java.security.Signature;
//import java.util.Base64;
//
//public class WxV3Utils {
//
//
//
//    // Authorization:
//// GET - getToken("GET", httpurl, "")
//// POST - getToken("POST", httpurl, json)
//    String schema = "WECHATPAY2-SHA256-RSA2048";
//    HttpUrl httpurl = HttpUrl.parse(url);
//
//    String getToken(String method, HttpUrl url, String body) {
//        String nonceStr = "your nonce string";
//        long timestamp = System.currentTimeMillis() / 1000;
//        String message = buildMessage(method, url, timestamp, nonceStr, body);
//        String signature = sign(message.getBytes("utf-8"));
//
//        return "mchid=\"" + yourMerchantId + "\","
//                + "nonce_str=\"" + nonceStr + "\","
//                + "timestamp=\"" + timestamp + "\","
//                + "serial_no=\"" + yourCertificateSerialNo + "\","
//                + "signature=\"" + signature + "\"";
//    }
//
//    String sign(byte[] message) {
//        Signature sign = Signature.getInstance("SHA256withRSA");
//        sign.initSign(yourPrivateKey);
//        sign.update(message);
//
//        return Base64.getEncoder().encodeToString(sign.sign());
//    }
//
//    String buildMessage(String method, HttpUrl url, long timestamp, String nonceStr, String body) {
//        String canonicalUrl = url.encodedPath();
//        if (url.encodedQuery() != null) {
//            canonicalUrl += "?" + url.encodedQuery();
//        }
//
//        return method + "\n"
//                + canonicalUrl + "\n"
//                + timestamp + "\n"
//                + nonceStr + "\n"
//                + body + "\n";
//    }
//
//}
//
//
//*/
