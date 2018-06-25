package com.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

//@ComponentScan("{bo.impl, hello}")
@SpringBootApplication
@EnableJpaRepositories("com.chatbot.model.repository")
@EntityScan("com.chatbot.model")
public class HelloWorldConfiguration {


	public static void main(String[] args) {
		SpringApplication.run(HelloWorldConfiguration.class, args);
	}


}
