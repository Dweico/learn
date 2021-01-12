package com.yangya.factory.service;

import com.alibaba.fastjson.JSONObject;
import com.yangya.factory.Consumer;
import org.springframework.stereotype.Service;

@Service("four")
public class FourImpl implements Consumer {

    @Override
    public void handleMessage(JSONObject data) {
        System.out.println("four");
    }
}
