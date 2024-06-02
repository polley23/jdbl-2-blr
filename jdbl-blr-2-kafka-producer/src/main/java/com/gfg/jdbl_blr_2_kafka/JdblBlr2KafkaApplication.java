package com.gfg.jdbl_blr_2_kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class JdblBlr2KafkaApplication {

	public static void main(String[] args) {

		WebApplicationContext ioc = (WebApplicationContext) SpringApplication.run(JdblBlr2KafkaApplication.class, args);

		Producer producer = ioc.getBean(Producer.class);
		try {
			producer.produce();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

}
