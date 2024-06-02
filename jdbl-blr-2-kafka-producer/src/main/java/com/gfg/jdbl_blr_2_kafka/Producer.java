package com.gfg.jdbl_blr_2_kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    KafkaTemplate<String , String> kafkaTemplate;
    public void produce() throws InterruptedException {
        int i = 1;
        while(true) {
            kafkaTemplate.send("test10",String.valueOf(i), "message "+i);
            Thread.sleep(500);
            i++;
        }


    }
}
