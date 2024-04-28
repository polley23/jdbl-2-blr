package com.gfg.jdblbr2session7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class JdblBr2Session7Application {
	public static void main(String[] args) {
		WebApplicationContext webApplicationContext =
				(WebApplicationContext) SpringApplication.run(JdblBr2Session7Application.class, args);

//		for (Object obj : webApplicationContext.getBeansWithAnnotation(Deprecated.class).values()){
//			CarDriver carDriver = (CarDriver) obj;
//			carDriver.drive();
//		}
		CarDriver carDriver = webApplicationContext.getBean(CarDriver.class);
		carDriver.drive();
	}


	/**
	 *
	 * create table Car (
	 *  `id` bigint auto_increment,
	 *  `cname` varchar(1024),
	 *   primary key(`id`)
	 * )
	 *
	 *
	 *
	 *
	 *
	 */

}
