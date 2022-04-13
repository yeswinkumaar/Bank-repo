package com.bms.userprofile.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bms.userprofile.beans.UserProfileRequest;
import com.bms.userprofile.beans.UserProfileResponse;
import com.bms.userprofile.bo.UserProfileBO;





@RestController
public class UserProfileController {

	private static Logger logger=LoggerFactory.getLogger(UserProfileController.class);
	
	@Autowired
	UserProfileBO userProfileBO;
	
	@GetMapping("/healthCheck")
	public UserProfileResponse healthCheck()
	
	{
		logger.info("Inside Health method");
		return new UserProfileResponse(HttpStatus.OK,"Up-Running");
	}
	
	/**
	 * @param userName
	 * @return
	 */
	@GetMapping(value="/checkUserNameAvailability/{userName}")
	public @ResponseBody ResponseEntity<UserProfileResponse> checkUserUsernameAvailability(@PathVariable String userName)
	{
		UserProfileResponse userProfileResponse =new UserProfileResponse();

		long start=System.currentTimeMillis();
		try
		{
			
			userProfileResponse= userProfileBO.checkUserAvailability(userName);
		}catch(Exception e)
		{
			
			logger.error("Exception occured while checkUserIdAvailability{}",e.getMessage());
			
		}
		long end=System.currentTimeMillis();
		logger.info("Method checkUserIdAvailability took {} milliseconds",end-start);
		return ResponseEntity
                .status(userProfileResponse.getHttpStatus())
                .body(userProfileResponse);
		
		
	}
	
	@PutMapping("/UpdateCustomerDetails/")
	public ResponseEntity<UserProfileResponse> updateUserProfile(@Valid @RequestBody UserProfileRequest userProfileRequest,BindingResult result) throws Exception
	{
		
		UserProfileResponse userProfileResponse =null;
		long start=System.currentTimeMillis();
		
	
				
		userProfileResponse=userProfileBO.updateUserProfile(userProfileRequest);	
		
		long end=System.currentTimeMillis();
		logger.info("Method checkUserIdAvailability took {} milliseconds",end-start);
		
		return  ResponseEntity
                .status(userProfileResponse.getHttpStatus())
                .body(userProfileResponse);
	}
	
	 

	}
	
	
