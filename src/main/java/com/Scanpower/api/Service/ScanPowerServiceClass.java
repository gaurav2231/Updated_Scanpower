package com.Scanpower.api.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.Scanpower.api.dao.UserRepository;
import com.Scanpower.api.entity.User;

@Component
public class ScanPowerServiceClass {
	
  @Autowired
	private BCryptPasswordEncoder passwordEncoder;
	 
	@Autowired
	private UserRepository userRepository;
                      // for login
	
	public User userLogin(String password,String email,String marketplace){
		
	System.out.println("password:: "+password+" email:: "+email+" marketplace:: "+marketplace);
	
		User user = this.userRepository.getUserByUsername(email,password);
	
		  boolean isAlreadyExist = false;
	
    	if(user.getEmailid().equals(email)&&user.getPassword().equals(password))
			 {
			  isAlreadyExist = true;
			  System.out.println("email match successfully while login ");  
				
			 }
		 
		if(!isAlreadyExist) {
			System.out.println("INVALID CREDENTIAL!!");
		}
			  else
			  {
				System.out.println("USER LOGIN SUCCESSFULLY!!");
			  }
		return user;
	}
	
	
                         // for signup

	public List<User> saveUser(User user)
	{
   		List<User> userData = this.userRepository.findAll();
		 String userEmail = user.getEmailid();
            
		boolean isAlreadyExist = false;

		for(int i=0;i<userData.size();i++)
		{
			String databaseEmail = userData.get(i).getEmailid();
			if(databaseEmail.equals(userEmail))
			{
				isAlreadyExist = true;
			}
		}
		if(!isAlreadyExist) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			this.userRepository.save(user);
			System.out.println("USER REGISTERED SUCCESSFULLY!!");
		}
		else {
			System.out.println("USER ALREADY REGISTERED!!");
		}
		return userData;
	}
	
}
