package com.bms.userprofile.bo.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bms.userprofile.DAO.PasswordAuidRepository;
import com.bms.userprofile.DAO.UpdateUserAddressRepository;
import com.bms.userprofile.DAO.UpdateUserDetailsRepository;
import com.bms.userprofile.DAO.UserIDRepository;
import com.bms.userprofile.beans.CustomerCredentials;
import com.bms.userprofile.beans.UserProfileRequest;
import com.bms.userprofile.beans.UserProfileResponse;
import com.bms.userprofile.bo.UserProfileBO;

import com.bms.userprofile.validation.BmsValidator;
import com.bms.userprofile.validation.UserProfileValidation;
import com.bms.util.UserProfileUtil;

@ExtendWith(SpringExtension.class)

public class UserProfileBOImplTest {

	@InjectMocks
	private UserProfileBOImpl userProfileBO;
	
	
	
	@Mock
	private UserIDRepository userIDRepository;
	
	@Mock
	private UpdateUserAddressRepository updateUserAddressRepository;
	
	@Mock
	private UpdateUserDetailsRepository updateUserDetailsRepository;
	
	@Mock
	private PasswordAuidRepository PasswordAuidRepository;
	
	@Mock
	private UserProfileUtil userProfileUtil;
	
	@Mock
	private BmsValidator BmsValidator;
	
	@Mock
	private Optional<CustomerCredentials> customerCredentials;
	
	@Mock
	private UserProfileValidation userProfileValidation;

	@Mock
	private UserProfileRequest mockUserProfile;
	

	//Testing for UserName Availability
	@Test
	 void userNameAvailabilitySuccess_Test() throws Exception
	{
		String username="mohan123";

		 UserProfileResponse userProfileResponse=new UserProfileResponse();
		 userProfileResponse.setHttpStatus(HttpStatus.OK);
		 userProfileResponse.setResponse("Username is not available please choose another username");
		when(userIDRepository.countByUsername(username)).thenReturn((long) 1);
		when(userProfileValidation.validateUserAvailability("mohan123")).thenReturn(true);
		UserProfileResponse result=userProfileBO.checkUserAvailability(username);
		assertEquals(userProfileResponse.getResponse(), result.getResponse());
		
	}
	@Test
	 void userNameAvailabilityValidationFailure_Test() throws Exception
	{
		String username="mohan123";

		 UserProfileResponse userProfileResponse=new UserProfileResponse();
		 userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
		 userProfileResponse.setResponse("user validation failed");
		when(userProfileValidation.validateUserAvailability("mohan123")).thenReturn(false);
		UserProfileResponse result=userProfileBO.checkUserAvailability(username);
		assertEquals(userProfileResponse.getResponse(), result.getResponse());
		
	}
	
	
	@Test
	 void updateUserProfileInvalidCustomerId_Test() throws Exception
	{
		
		mockUserProfile=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","kumar","rahul.kumar@gmail.com","19/08/1999","983749213","rkv street","chennai","TamilNadu","India","638142","Y","Y","Y","Y");
		 UserProfileResponse userProfileResponse=new UserProfileResponse();
		 userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
		 userProfileResponse.setResponse("Invalid  Username OR Customer ID");
		
		UserProfileResponse result=userProfileBO.updateUserProfile(mockUserProfile);
		assertEquals(userProfileResponse.getResponse(), result.getResponse());
		
	}
	
	@Test
	 void updateUserProfileInvalidFlag_Test() throws Exception
	{
		
		mockUserProfile=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","kumar","rahul.kumar@gmail.com","19/08/1999","983749213","rkv street","chennai","TamilNadu","India","638142","Y","Y","Y");
		 UserProfileResponse userProfileResponse=new UserProfileResponse();
		 userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
		 userProfileResponse.setResponse("Invalid  Username OR Customer ID");
		//when(userProfileValidation.validateIndicator(mockUserProfile.getIsLoggedIn())).thenReturn(true);
		UserProfileResponse result=userProfileBO.updateUserProfile(mockUserProfile);
		assertEquals(userProfileResponse.getResponse(), result.getResponse());
		
	}
	
	@Test
	 void updateUserProfilePasswordFailure_Test() throws Exception
	{
		
		mockUserProfile=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","kumar","rahul.kumar@gmail.com","19/08/1999","983749213","rkv street","chennai","TamilNadu","India","638142","Y","Y","Y");
		 UserProfileResponse userProfileResponse=new UserProfileResponse();
		 userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
		 userProfileResponse.setResponse("Invalid  Username OR Customer ID");
		//when(userProfileValidation.validateIndicator(mockUserProfile.getIsLoggedIn())).thenReturn(true);
		
		when(BmsValidator.isAlphaNumeric(mockUserProfile.getUserName(), false, 8)).thenReturn(true);
		when(BmsValidator.isNumeric(mockUserProfile.getCustomerId().toString(), false, 1)).thenReturn(true);
		
		UserProfileResponse result=userProfileBO.updateUserProfile(mockUserProfile);
		assertEquals(userProfileResponse.getResponse(), result.getResponse());
		
	}
	
	@Test
	 void updateUserProfilePasswordSuccess_Test() throws Exception
	{
		
		mockUserProfile=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","kumar","rahul.kumar@gmail.com","19/08/1999","983749213","rkv street","chennai","TamilNadu","India","638142","Y","Y","Y");
		 UserProfileResponse userProfileResponse=new UserProfileResponse();
		 userProfileResponse.setHttpStatus(HttpStatus.OK);
		 userProfileResponse.setResponse("User Not Logged In");
		
		
		when(userProfileValidation.validateIndicator(mockUserProfile.getIsAddressChange())).thenReturn(true);
		when(userProfileValidation.validateIndicator(mockUserProfile.getIsProfileChange())).thenReturn(true);
		when(userProfileValidation.validateIndicator(mockUserProfile.getIsPasswordChange())).thenReturn(true);
		
		when(BmsValidator.isAlphaNumeric(mockUserProfile.getUserName(), false, 8)).thenReturn(true);
		when(BmsValidator.isNumeric(mockUserProfile.getCustomerId().toString(), false, 1)).thenReturn(true);
		
		
		when(userProfileValidation.isValidPassword(mockUserProfile.getNewPassword())).thenReturn(true);
		Optional<CustomerCredentials> customer=Optional.of(new CustomerCredentials("mohan123","yeeh@123.com"));
		
		when(userIDRepository.findByUsername(mockUserProfile.getUserName())).thenReturn(customer);
		
		UserProfileResponse result=userProfileBO.updateUserProfile(mockUserProfile);
		assertEquals(userProfileResponse.getResponse(), result.getResponse());
		
	}
	
	@Test
	 void updateUserProfileSamePasswordFailure_Test() throws Exception
	{
	
		mockUserProfile=new UserProfileRequest((long) 1, "vignesh123","ramvicky@456","ramvicky@456","ramvicky@456","kumar","rahul.kumar@gmail.com","19/08/1999","983749213","rkv street","chennai","TamilNadu","India","638142","Y","Y","Y");
		 UserProfileResponse userProfileResponse=new UserProfileResponse();
		 userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
		 userProfileResponse.setResponse("User Not Logged In");
		//when(userProfileValidation.validateIndicator(mockUserProfile.getIsLoggedIn())).thenReturn(true);
		
		when(userProfileValidation.validateIndicator(mockUserProfile.getIsAddressChange())).thenReturn(true);
		when(userProfileValidation.validateIndicator(mockUserProfile.getIsProfileChange())).thenReturn(true);
		when(userProfileValidation.validateIndicator(mockUserProfile.getIsPasswordChange())).thenReturn(true);
		
		when(BmsValidator.isAlphaNumeric(mockUserProfile.getUserName(), false, 8)).thenReturn(true);
		when(BmsValidator.isNumeric(mockUserProfile.getCustomerId().toString(), false, 1)).thenReturn(true);
		
		
		//when(userProfileValidation.isValidPassword(mockUserProfile.getPassWord())).thenReturn(true);
		Optional<CustomerCredentials> customer=Optional.of(new CustomerCredentials("mohan123","yeeh@123.com"));
		
		CustomerCredentials cust=customer.get();
		when(userIDRepository.findByUsername(mockUserProfile.getUserName())).thenReturn(customer);
		
		mockStatic(UserProfileUtil.class);
		when(UserProfileUtil.checkPassword(cust.getPassword(), mockUserProfile.getNewPassword())).thenReturn(true);
		
		UserProfileResponse result=userProfileBO.updateUserProfile(mockUserProfile);
		assertEquals(userProfileResponse.getResponse(), result.getResponse());
		
	}
	
}
