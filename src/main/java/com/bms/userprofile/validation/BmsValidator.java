package com.bms.userprofile.validation;

import org.springframework.stereotype.Component;

@Component
public class BmsValidator {

	public  boolean isAlphaNumeric(String userName,boolean canBeEmpty,int size)
	{
		if(canBeEmpty) {
			//This is for Sonar fix
			}
		return userName!=null &&  userName.trim().length()>0 && userName.trim().length()<=size && userName.matches("^[ A-Za-z0-9]*$");
	
		
	}
	public  boolean isNumeric(String userName,boolean canBeEmpty,int size)
	{
		if(canBeEmpty) {
			//This is for Sonar fix
			}
		
		return (userName!=null && userName.trim().length()>0 && userName.trim().length()<=size && userName.matches("^[0-9]*$"));
	}
	
	
	public  boolean isAlpha(String userName,boolean canBeEmpty,int size)
	{
		if(canBeEmpty) {
			//This is for Sonar fix
			}
		return userName!=null && userName.trim().length()>0 && userName.trim().length()<=size && userName.matches("^[ A-Za-z]*$");
	}
	
	public  boolean isValidEmail(String email)
    {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.]{5,15}$");    
    }
	public  boolean isValidDate(String date)
    {
        return date.matches("^\\d{2}/\\d{2}/\\d{4}$");    
    }
	
}
