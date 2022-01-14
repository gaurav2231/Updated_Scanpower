package com.Scanpower.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Scanpower.api.Service.ScanPowerServiceClass;
import com.Scanpower.api.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.Scanpower.api.JWTConfig;

@RestController
@RequestMapping("/Scanpower")
public class HomeController {

	@Autowired
	private ScanPowerServiceClass service;

	@Autowired
	private User user;

	@PostMapping("/login")
	public String login(@RequestBody String data)
	{
		
		JSONObject em=new JSONObject(data);

		String email=em.getString("emailid");
	    String password =em.getString("password");
		String marketplace=em.getString("marketplace");
      User user1 = service.userLogin(password,email,marketplace);
		
		
		return email;

	}  


	@PostMapping("/signup")
	public Map<String, Object> registerUser(@RequestBody User user)
	{
		List<User> user1=this.service.saveUser(user);
		Map<String, Object> data = new HashMap<>();
		data.put("user", user1);
		data.put("jwtToken", getJWTToken(user1.get(0)));
		return data;
	}
	
	private String getJWTToken(User user) {
		//timeStamp will be used for setting token expiry time and token issue date
		long timeStamp = System.currentTimeMillis();

		return Jwts.builder().signWith(SignatureAlgorithm.HS256, JWTConfig.API_SECRET_KEY)
				.setIssuedAt(new Date(timeStamp))
				.setExpiration(new Date(Long.sum(timeStamp, JWTConfig.TOKEN_VALIDITY)))
				.claim("email", user.getEmailid())
				.claim("password",user.getPassword())
				.compact();
	}
}
