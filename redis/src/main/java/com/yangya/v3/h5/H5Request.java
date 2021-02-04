package com.yangya.v3.h5;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class H5Request {

    @JSONField(name = "time_expire")
    private Date timeExpire;
    private Amount amount;
    private String mchid;
    private String description;
    @JSONField(name = "notify_url")
    private String notifyUrl;
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "goods_tag")
    private String goodsTag;
    private String appid;
    private String attach;
    private Detail detail;
    @JSONField(name = "scene_info")
    private SceneInfo sceneInfo;

    @Data
    public static class Amount {

        private int total;
        private String currency;
    }


    @Data
    public static class GoodsDetail {
        @JSONField(name = "goods_name")
        private String goodsName;
        @JSONField(name = "wechatpay_goods_id")
        private String wechatpayGoodsId;
        private int quantity;
        @JSONField(name = "merchant_goods_id")
        private String merchantGoodsId;
        @JSONField(name = "unit_price")
        private long unitPrice;
    }

    @Data
    public static class Detail {
        @JSONField(name = "unit_price")
        private String invoice_id;
        @JSONField(name = "goods_detail")
        private List<GoodsDetail> goodsDetail;
        @JSONField(name = "cost_price")
        private long costPrice;

    }
    @Data
    public static class StoreInfo {

        private String address;
        @JSONField(name = "area_code")
        private String areaCode;
        private String name;
        private String id;

    }

    @Data
    public static class H5Info {
        @JSONField(name = "app_name")
        private String appName;
        @JSONField(name = "app_url")
        private String appUrl;
        @JSONField(name = "bundle_id")
        private String bundleId;
        @JSONField(name = "package_name")
        private String packageName;
        private String type;
    }

    @Data
    public static class SceneInfo {
        @JSONField(name = "device_id")
        private String deviceId;
        @JSONField(name = "store_info")
        private StoreInfo storeInfo;
        @JSONField(name = "h5_info")
        private H5Info h5Info;
        @JSONField(name = "payer_client_ip")
        private String payerClientIp;

    }

}
