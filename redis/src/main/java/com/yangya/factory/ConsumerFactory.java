package com.yangya.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ConsumerFactory {

    @Autowired
    Map<String, Consumer> consumerMap = new ConcurrentHashMap<>(100);

    public Consumer getConsumer(String service) throws Exception {
        Consumer consumer = consumerMap.get(service);
        if (consumer == null) {
            throw new RuntimeException("no consumer defined: " + service);
        }
        return consumer;
    }
}
