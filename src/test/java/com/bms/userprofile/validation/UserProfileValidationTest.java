package com.bms.userprofile.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bms.userprofile.beans.UserProfileRequest;
@ExtendWith(SpringExtension.class)

public class UserProfileValidationTest {

	
	
	
	@InjectMocks
	private UserProfileValidation  userProfileValidation;
	
	@Test
	 void addressValidationSuccessTest()
	{
		UserProfileRequest userProfileRequest;
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","rahul.kumar@gmail.com","kumar","19/08/1999","983749213","10 rkv road","chennai","TamilNadu","IND","638142","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.addressValidation(userProfileRequest);
		assertEquals("success", result);
	}
	
	@Test
	 void addressValidationfailedAddressTest()
	{
		UserProfileRequest userProfileRequest;
		
		
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","rahul.kumar@gmail.com","kumar","19/08/1999","983749213","10,rkv road","chennai","TamilNadu","IND","638142","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.addressValidation(userProfileRequest);
		assertEquals("Invalid Address,Please check the Address", result);
	}
	
	@Test
	 void addressValidationFailedCityTest()
	{
		UserProfileRequest userProfileRequest;
		
		
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","rahul.kumar@gmail.com","kumar","19/08/1999","983749213","10 rkv road","c!@#nnai","TamilNadu","IND","638142","Y","Y","Y");
		
		String result=userProfileValidation.addressValidation(userProfileRequest);
		assertEquals("Invalid City Name,Please check the Name", result);
	}
	@Test
	 void addressValidationfailedStateTest()
	{
		UserProfileRequest userProfileRequest;
		
		
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","rahul.kumar@gmail.com","kumar","19/08/1999","983749213","10 rkv road","chennai","Tam@#adu","IND","638142","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.addressValidation(userProfileRequest);
		assertEquals("Invalid State Name,Please check the name", result);
	}
	@Test
	void addressValidationfailedCountryTest()
	{
		UserProfileRequest userProfileRequest;
		
		
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","rahul.kumar@gmail.com","kumar","19/08/1999","983749213","10 rkv road","chennai","TamilNadu","IN@","638142","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.addressValidation(userProfileRequest);
		assertEquals("Invalid Country Name,Please check the name", result);
	}
	@Test
	 void addressValidationfailedPincodeTest()
	{
		UserProfileRequest userProfileRequest;
		
		
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","rahul.kumar@gmail.com","kumar","19/08/1999","983749213","10 rkv road","chennai","TamilNadu","IN@","638143#","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.addressValidation(userProfileRequest);
		assertEquals("Invalid Pincode,Please check the Pincode", result);
	}
	
	@Test
	 void profileValidationSuccessTest()
	{
		UserProfileRequest userProfileRequest;
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","kumar","rahul.kumar@gmail.com","19/08/1999","983749213","10 rkv road","chennai","TamilNadu","IND","638142","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.profileValidation(userProfileRequest);
		assertEquals("success", result);
	}
	@Test
	 void profileValidationFailureTest()
	{
		UserProfileRequest userProfileRequest;
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","rahul.kumar@gmail.com","kumar","19/08/1999","983749213","10 rkv road","chennai","TamilNadu","IND","638142","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.profileValidation(userProfileRequest);
		assertEquals("Invalid LastName,Please check the name", result);
	}
	@Test
	void profileValidationMailFailureTest()
	{
		UserProfileRequest userProfileRequest;
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","kumar","rahul.kumargmail.com","19/08/1999","983749213","10 rkv road","chennai","TamilNadu","IND","638142","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.profileValidation(userProfileRequest);
		assertEquals("Invalid E-mail,Please check the E-mail", result);
	}
	@Test
	 void profileValidationDateFailureTest()
	{
		UserProfileRequest userProfileRequest;
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","kumar","rahul.kumar@gmail.com","19/08/199","983749213","10 rkv road","chennai","TamilNadu","IND","638142","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.profileValidation(userProfileRequest);
		assertEquals("Invalid Date,Please check the Date Format", result);
	}
	@Test
	 void profileValidationContactFailureTest()
	{
		UserProfileRequest userProfileRequest;
		userProfileRequest=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","kumar","rahul.kumar@gmail.com","19/08/1999","983@749213","10 rkv road","chennai","TamilNadu","IND","638142","Y","Y","Y");
		System.out.println(userProfileRequest.toString());
		
		String result=userProfileValidation.profileValidation(userProfileRequest);
		assertEquals("Invalid ContactNo,Please check the ContactNo", result);
	}
	@Test
	 void validationIndicatorFailureTest()
	{
		assertEquals(false,userProfileValidation.validateIndicator("no"));
	}
	@Test
	 void validationIndicatorSuccessTest()
	{
		assertEquals(true,userProfileValidation.validateIndicator("Y"));
	}
	@Test
	 void userAvailabilitySuccessTest()
	{
		assertEquals(true,userProfileValidation.validateUserAvailability("Mohan123"));
	}
	@Test
	void userAvailabilityFailureTest()
	{
		assertEquals(false,userProfileValidation.validateUserAvailability("Mohan123@"));
	}
	@Test
	 void userAvailabilityAlphaFailureTest()
	{
		assertEquals(false,userProfileValidation.validateUserAvailability("Mohan"));
	}
	@Test
	 void userAvailabilityNumbericFailureTest()
	{
		assertEquals(false,userProfileValidation.validateUserAvailability("123"));
	}
	@Test
	 void passwordValidationSuccessTest()
	{
		assertEquals(true,userProfileValidation.isValidPassword("Mohan123@"));
	}
	@Test
	void passwordValidationFailureTest()
	{
		assertEquals(false,userProfileValidation.isValidPassword("Mohan123"));
	}
}