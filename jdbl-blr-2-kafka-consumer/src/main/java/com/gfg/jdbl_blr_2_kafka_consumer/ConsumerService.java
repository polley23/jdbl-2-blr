package com.gfg.jdbl_blr_2_kafka_consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {
    @KafkaListener(topics = "test10", groupId = "app", concurrency = "10")
    public void consume(String msg){
        log.info(msg);
    }
}
