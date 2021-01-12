package com.yangya.factory;

import com.alibaba.fastjson.JSONObject;

public interface Consumer {

    void handleMessage(JSONObject data);
}
