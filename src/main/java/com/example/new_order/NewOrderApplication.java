package com.example.new_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

//@service,@controller,@configuration,@restcontroller,@component
public class NewOrderApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NewOrderApplication.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(NewOrderApplication.class);
	}
}
