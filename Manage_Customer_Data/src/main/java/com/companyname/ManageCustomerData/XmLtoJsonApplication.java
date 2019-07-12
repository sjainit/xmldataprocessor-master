package com.companyname.ManageCustomerData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;

@EnableMongoRepositories
@EnableJms
@SpringBootApplication
public class XmLtoJsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmLtoJsonApplication.class, args);

	}

}
