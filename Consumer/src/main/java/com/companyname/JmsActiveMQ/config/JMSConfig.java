package com.companyname.JmsActiveMQ.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class JMSConfig {
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("simple-jms-queue");
	}
}