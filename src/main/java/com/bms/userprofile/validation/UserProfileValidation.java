package com.bms.userprofile.validation;

import org.springframework.stereotype.Component;

import com.bms.userprofile.beans.UserProfileRequest;

@Component
public class UserProfileValidation extends BmsValidator {

	
	public  boolean validateUserAvailability(String userName)
	{
		
		if(isAlpha(userName,false,15))
		{
			return false;		
		}
		if(isNumeric(userName,false,15))
		{
			return false;
		}
		if(isAlphaNumeric(userName,false,15))
		{
			
			return true;
		}
		
		
		return false;
	}
	
	public  boolean isValidPassword(String password)
    {
        return password.matches( "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$");    
    }
	
	
	public  boolean validateIndicator(String indicatorvalue)
    {
        return indicatorvalue.equalsIgnoreCase("Y") || indicatorvalue.equalsIgnoreCase("N");    
    }
	public  String profileValidation(UserProfileRequest userProfileRequest)
	{
		
		if(!isAlpha(userProfileRequest.getLastName(), false, 8) && userProfileRequest.getLastName().length()>1)
		{
			return "Invalid LastName,Please check the name";
		}
		if(!isValidEmail(userProfileRequest.getEmail()) && userProfileRequest.getEmail().length()>1)
		{
			return "Invalid E-mail,Please check the E-mail";
		}
		if(!isValidDate(userProfileRequest.getDob()) && userProfileRequest.getDob().length()>1)
		{
			return "Invalid Date,Please check the Date Format";
		}
		if(!isNumeric(userProfileRequest.getContactNo(),false,11)  && userProfileRequest.getContactNo().length()>1)
		{
			return "Invalid ContactNo,Please check the ContactNo";
		}
		return "success";
	}
	
	public  String addressValidation(UserProfileRequest userProfileRequest)
	{
		
		
		if(!isAlpha(userProfileRequest.getCity(), false, 15) && userProfileRequest.getCity().length()>1)
		{
			return "Invalid City Name,Please check the Name";
		}
		if(!isNumeric(userProfileRequest.getPincode(), false,8) && userProfileRequest.getPincode().length()>1)
		{
			return "Invalid Pincode,Please check the Pincode";
		}
		if(!isAlpha(userProfileRequest.getCountry(), false,3) && userProfileRequest.getCountry().length()>1)
		{
			return "Invalid Country Name,Please check the name";
		}
		if(!isAlpha(userProfileRequest.getState(), false,12) && userProfileRequest.getState().length()>1)
		{
			return "Invalid State Name,Please check the name";
		}
		if(!isAlphaNumeric(userProfileRequest.getAddress(), false, 30) && userProfileRequest.getAddress().length()>1)
		{
			return "Invalid Address,Please check the Address";
		}
		return "success";
		
	}

}

