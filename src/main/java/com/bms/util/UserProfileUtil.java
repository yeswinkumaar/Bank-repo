package com.bms.util;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UserProfileUtil {

	public static  String bcryptPasswordEncoder(String password) 
	{
		
		return  BCrypt.hashpw(password, BCrypt.gensalt(12));

		
	}
	
	public static boolean checkPassword(String dbpass,String password)
	{
		boolean flag=false;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		flag= encoder.matches(password, dbpass);
		
		return flag;
	}
}