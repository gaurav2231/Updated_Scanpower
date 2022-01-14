package com.Scanpower.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyConfig{


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder b=new BCryptPasswordEncoder();
        
		
		
		return new BCryptPasswordEncoder();
	}
}
	