package com.Scanpower.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScanpowerapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScanpowerapiApplication.class, args);
		
	}
	
	@Bean
	public FilterRegistrationBean<MyAuthFilter> filterRegistrationBean(){
		FilterRegistrationBean<MyAuthFilter> registrationBean = new FilterRegistrationBean<MyAuthFilter>();
		MyAuthFilter authFilter = new MyAuthFilter();
		registrationBean.setFilter(authFilter);

		//register all the protected resources here
		registrationBean.addUrlPatterns("/Scanpower/login");
		//similar to above line we can protect any other resources
		return registrationBean;
	}

}
