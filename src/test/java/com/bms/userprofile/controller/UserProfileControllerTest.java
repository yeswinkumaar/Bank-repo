package com.bms.userprofile.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;

import com.bms.userprofile.DAO.PasswordAuidRepository;
import com.bms.userprofile.DAO.UpdateUserAddressRepository;
import com.bms.userprofile.DAO.UpdateUserDetailsRepository;
import com.bms.userprofile.DAO.UserIDRepository;
import com.bms.userprofile.beans.UserProfileRequest;
import com.bms.userprofile.beans.UserProfileResponse;
import com.bms.userprofile.bo.UserProfileBO;
import com.bms.userprofile.bo.impl.UserProfileBOImpl;
import com.bms.userprofile.validation.UserProfileValidation;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserProfileController.class)
@AutoConfigureMockMvc(addFilters =false)
public class UserProfileControllerTest {

	@Mock
	UserProfileBO userProfileBo;
	
	@MockBean
	UserIDRepository UserIDRepository;
	
	@MockBean
	UpdateUserDetailsRepository updateUserDetailsRepository;
	
	@MockBean
	UpdateUserAddressRepository updateAddressRepository;
	
	@MockBean
	PasswordAuidRepository passwordAuidRepository;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	UserProfileController userProfileController;
	
	@Mock
	UserProfileResponse userProfileResponse;
	
	@Mock
	UserProfileValidation userProfileValidation;
	
	@Mock
	UserProfileRequest userProfileRequest;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void healthCheckTest() throws Exception {
		this.mockMvc.perform(get("/healthCheck"))
		.andExpect(status().isOk())
	.andExpect(( content().contentType(MediaType.APPLICATION_JSON)))
	.andExpect((jsonPath("$.httpStatus").value("OK")))
	.andExpect((jsonPath("$.response").value("Up-Running")));
	}
	
	@Test
	void checkUserNameAvailabilityTest() throws Exception 
	{
		
		UserProfileResponse userProfileResponse=new UserProfileResponse(HttpStatus.BAD_REQUEST,"validation failed");
		
		 when(userProfileBo.checkUserAvailability("mohan")).thenReturn(userProfileResponse);
		 
		 this.mockMvc.perform(get("/checkUserNameAvailability/{userName}","mohan"))
			.andExpect(status().isBadRequest())
		.andExpect(( content().contentType(MediaType.APPLICATION_JSON)))
		.andExpect((jsonPath("$.httpStatus").value("BAD_REQUEST")))
		.andExpect((jsonPath("$.response").value("user validation failed")));
	}
	
	@Test
	 void checkUserNameAvailabilityFailure_Test() throws Exception 
	{
		
		UserProfileResponse userProfileResponse=new UserProfileResponse(HttpStatus.BAD_REQUEST,"validation failed");
		
		 when(userProfileBo.checkUserAvailability("mohan")).thenReturn(userProfileResponse);
		 
		 this.mockMvc.perform(get("/checkUserNameAvailability/{userName}","mohan"))
			.andExpect(status().isBadRequest())
		.andExpect(( content().contentType(MediaType.APPLICATION_JSON)))
		.andExpect((jsonPath("$.httpStatus").value("BAD_REQUEST")))
		.andExpect((jsonPath("$.response").value("user validation failed")));
	}
	@Test
	 void checkUserNameAvailabilityException_Test() throws Exception 
	{
			 when(userProfileBo.checkUserAvailability("mohan")).thenThrow(Exception.class);		
	}
	
	@Test
	 void updateProfile_Test() throws Exception 
	{
		userProfileRequest=new UserProfileRequest((long) 1, "mohan123","ramvicky@456","ramvicky@456","ramvicky@456","rahul.kumar@gmail.com","kumar","19/08/1999","983749213","rkv street","chennai","TamilNadu","India","638142","Y","Y","Y");
		
		UserProfileResponse userProfileResponse=new UserProfileResponse(HttpStatus.BAD_GATEWAY,"Invalid  Username OR Customer ID");
		
		 when(userProfileBo.updateUserProfile(userProfileRequest)).thenReturn(userProfileResponse);
		 this.mockMvc.perform(put("/UpdateCustomerDetails/")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(objectMapper.writeValueAsString(userProfileRequest)))
			.andExpect(status().isBadRequest())
		.andExpect(( content().contentType(MediaType.APPLICATION_JSON)))
		.andExpect((jsonPath("$.httpStatus").value("BAD_REQUEST")))
		.andExpect((jsonPath("$.response").value("Invalid  Username OR Customer ID")));
	}
	
	@Test
	 void updateProfileException_Test() throws Exception 
	{
		userProfileRequest=new UserProfileRequest((long) 1, "mohan123","ramvicky@456","ramvicky@456","ramvicky@456","rahul.kumar@gmail.com","kumar","19/08/1999","983749213","rkv street","chennai","TamilNadu","India","638142","Y","Y","Y");
		
		UserProfileResponse userProfileResponse=new UserProfileResponse(HttpStatus.INTERNAL_SERVER_ERROR,"User Not Logged In");
		
		 when(userProfileBo.updateUserProfile(userProfileRequest)).thenThrow(Exception.class);
		 
		
	}
	
	
	
	
	
	
	

	

}
