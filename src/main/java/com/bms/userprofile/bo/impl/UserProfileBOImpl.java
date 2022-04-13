package com.bms.userprofile.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.PostConstruct;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.stereotype.Service;

import com.bms.userprofile.DAO.PasswordAuidRepository;
import com.bms.userprofile.DAO.UpdateUserAddressRepository;
import com.bms.userprofile.DAO.UpdateUserDetailsRepository;
import com.bms.userprofile.DAO.UserIDRepository;
import com.bms.userprofile.beans.CustomerAddress;
import com.bms.userprofile.beans.CustomerCredentials;
import com.bms.userprofile.beans.CustomerDetail;
import com.bms.userprofile.beans.PasswordAudit;
import com.bms.userprofile.beans.UserProfileRequest;
import com.bms.userprofile.beans.UserProfileResponse;
import com.bms.userprofile.bo.UserProfileBO;
import com.bms.userprofile.validation.BmsValidator;
import com.bms.userprofile.validation.UserProfileValidation;
import com.bms.util.UserProfileUtil;

@Service
public class UserProfileBOImpl implements UserProfileBO {
	
	
	private static final Logger logger=LoggerFactory.getLogger(UserProfileBOImpl.class);

	@Autowired
	private UserIDRepository userIdRespository;

	
	@Autowired
	private UpdateUserAddressRepository updateUserAddressRepository;
	
	@Autowired
	private UpdateUserDetailsRepository updateUserDetailsRepository;
	
	@Autowired
	private PasswordAuidRepository passwordAuidRepository;
	
	@Autowired
	private UserProfileValidation userProfileValidation;
	
	@Autowired
	private UserProfileUtil userProfileUtil;
	
	@Autowired
	private BmsValidator bmsValidator;
	
	@PostConstruct
	void postConstruct()
	{
		ArrayList<CustomerCredentials> customerCredentials=new ArrayList<>();
		ArrayList <CustomerAddress> customerAddress=new ArrayList<>();
		ArrayList <CustomerDetail> customerDetails=new ArrayList<>();
		
		
		Date date=new Date();
		
		
		CustomerCredentials customer=new CustomerCredentials("mohan123",UserProfileUtil.bcryptPasswordEncoder("yesH@123"),"2a$12$2RMDph0A/Lus5j.W8a7XM.KmYmnQDFK");
		CustomerCredentials customer1=new CustomerCredentials("vignesh","vignesh@gmail.com");
		CustomerCredentials customer2=new CustomerCredentials("Siddharth","yeeh@123.com");
		
		CustomerDetail customerDetail=new CustomerDetail(customer,"Mohan","babu","babuM@gmail.com","male",date,"9876543210","78623BNC23");
		CustomerDetail customerDetail1=new CustomerDetail(customer1,"Vigesh","ramasamy","vignesh@gmail.com","male",date,"8876543210","79623BNC23");
		CustomerDetail customerDetail2=new CustomerDetail(customer2,"Siddharth","Arulravi","siddharth@gmail.com","male",date,"9976543210","78623BNC23");
		
		CustomerAddress customerAddress1=new CustomerAddress(customerDetail,"rkv park","Chennai","TamilNadu","614589","IN","Y");
		CustomerAddress customerAddress2=new CustomerAddress(customerDetail1,"longly road,shevapet","salem","Rajasthan","311589","IN","Y");
		CustomerAddress customerAddress3=new CustomerAddress(customerDetail2,"EastCoast road","Erode","TamilNadu","311589","IN","Y");
		
		customerCredentials.add(customer);
		customerCredentials.add(customer1);
		customerCredentials.add(customer2);
		
		customerDetails.add(customerDetail);
		customerDetails.add(customerDetail1);
		customerDetails.add(customerDetail2);
		
		customerAddress.add(customerAddress1);
		customerAddress.add(customerAddress2);
		customerAddress.add(customerAddress3);
		
		
		userIdRespository.saveAll(customerCredentials);
		updateUserDetailsRepository.saveAll(customerDetails);
		updateUserAddressRepository.saveAll(customerAddress);
		
		
	}
	
	@Override
	public UserProfileResponse checkUserAvailability(String userName) throws Exception {
		
		UserProfileResponse userProfileResponse = new UserProfileResponse();
		userProfileResponse.setHttpStatus(HttpStatus.OK);
		userProfileResponse.setResponse("Username is  available");
		try
		
		{
			logger.info("inside Bo impl CheckuserAvailability");
			if(!userProfileValidation.validateUserAvailability(userName))
			{
				userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
				userProfileResponse.setResponse("user validation failed");
				
				return userProfileResponse;
			}
			
			if(userIdRespository.countByUsername(userName)>0)
			{
				
				userProfileResponse.setHttpStatus(HttpStatus.OK);
				userProfileResponse.setResponse("Username is not available please choose another username");
				
			}
		
		}catch(Exception e)
		{
		
			logger.error("Error");
		}
		logger.info("End Bo impl CheckuserAvailability");
		return userProfileResponse;
	}
	


	
	public UserProfileResponse updateUserProfile(UserProfileRequest userProfileRequest) throws  ParseException
	{
		UserProfileResponse userProfileResponse = new UserProfileResponse();
		
		String updateStatus="";
		logger.info("inside Bo impl updateUserProfile");
		Optional<CustomerCredentials> customercredentials=userIdRespository.findByUsername(userProfileRequest.getUserName());
		if(customercredentials==null)
		{
			userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			userProfileResponse.setResponse("Invalid  Username OR Customer ID");
			return userProfileResponse;
		}
		String key=customercredentials.get().getLoggedInKey();
		if(key!=null)
		{
			if(userProfileValidation.validateIndicator(userProfileRequest.getIsAddressChange()) && userProfileValidation.validateIndicator(userProfileRequest.getIsPasswordChange()) && userProfileValidation.validateIndicator(userProfileRequest.getIsProfileChange()))
			{
				if(!bmsValidator.isAlphaNumeric(userProfileRequest.getUserName(),false,15) || !bmsValidator.isNumeric(String.valueOf(userProfileRequest.getCustomerId()),false,8))
				{
					logger.info("inside BMS");
					userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
					userProfileResponse.setResponse("Invalid  Username OR Customer ID");
					return userProfileResponse;
				}else if(userProfileRequest.getIsAddressChange().equals("N") && userProfileRequest.getIsPasswordChange().equals("N") && userProfileRequest.getIsProfileChange().equals("N")) {
					userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
					userProfileResponse.setResponse("Change the isChange Flags to Y");
					return userProfileResponse;
				}else
				{
					if(userProfileRequest.getIsPasswordChange().equals("Y"))
					{
						return updateUserPassword(userProfileRequest);
						
						
					}
					if(userProfileRequest.getIsProfileChange().equals("Y"))
					{
						Optional<CustomerDetail> customerDetails=updateUserDetailsRepository.findById(userProfileRequest.getCustomerId());
						if(customerDetails==null)
						{
							userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
							userProfileResponse.setResponse("Please Enter Correct Customer ID");
							return userProfileResponse;
						}
						CustomerDetail customerDetail=customerDetails.get();
						String err=userProfileValidation.profileValidation(userProfileRequest);
						if(err.trim().length()>8)
						{
							userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
							userProfileResponse.setResponse(err);
							return userProfileResponse;
						}
						if(userProfileRequest.getLastName().trim().length() > 0)
						{
							
								customerDetail.setLastName(userProfileRequest.getLastName());
						}
						if(userProfileRequest.getEmail().trim().length() > 0)
						{
							
								customerDetail.setEmailId(userProfileRequest.getEmail());
						}
						if(userProfileRequest.getDob().trim().length() > 0)
						{
							
								SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
								Date date=formatter.parse(userProfileRequest.getDob());
								customerDetail.setDob(date);
						}
						if(userProfileRequest.getContactNo().trim().length() > 0)
						{
							
								customerDetail.setContactNo(userProfileRequest.getContactNo());
						}
						updateUserDetailsRepository.save(customerDetail);
						userProfileResponse.setHttpStatus(HttpStatus.OK);
						updateStatus+="Customer Details Updated";
					}
					if(userProfileRequest.getIsAddressChange().equals("Y"))
					{
						
						String isActive="Y";
						 String err=userProfileValidation.addressValidation(userProfileRequest);
						Optional<CustomerDetail> customerDetail=updateUserDetailsRepository.findById(userProfileRequest.getCustomerId());
						if(customerDetail==null)
						{
							userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
							userProfileResponse.setResponse("Please Enter Correct Customer ID");
							return userProfileResponse;
						}
						Optional<CustomerAddress> address=updateUserAddressRepository.findByCustomerDetailAndIsActiveContaining(customerDetail.get(),isActive);
						if(address==null)
						{
							userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
							userProfileResponse.setResponse("Please check IsActive or Not");
							return userProfileResponse;
						}
						CustomerAddress customerAddress=address.get();
						if(err.length()>8)
						{
							userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
							userProfileResponse.setResponse(err);
							return userProfileResponse;
						}
						if(userProfileRequest.getAddress().trim().length() > 0)
						{
							customerAddress.setAddress(userProfileRequest.getAddress());
						}
						if(userProfileRequest.getCity().trim().length() > 0)
						{	
								customerAddress.setCity(userProfileRequest.getCity());
						}
						if(userProfileRequest.getPincode().trim().length() > 0)
						{
							customerAddress.setPinCode(userProfileRequest.getPincode());
						}
						if(userProfileRequest.getCountry().trim().length() > 0)
						{
							customerAddress.setCountry(userProfileRequest.getCountry());
						}
						if(userProfileRequest.getState().trim().length() > 0)
						{
							customerAddress.setState(userProfileRequest.getState());
						}
						
						updateUserAddressRepository.save(customerAddress);
						updateStatus+="Customer Address Details Updated";
						userProfileResponse.setHttpStatus(HttpStatus.OK);
						
					}
					userProfileResponse.setResponse(updateStatus);
				}
			}else
			{
				userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
				userProfileResponse.setResponse("Invalid FLAG KEYS");
				return userProfileResponse;
			}
		
	}else
	{
		userProfileResponse.setHttpStatus(HttpStatus.BAD_GATEWAY);
		userProfileResponse.setResponse("User Not Logged In");
		return userProfileResponse;
	}
	return userProfileResponse;
	}

	
	public UserProfileResponse updateUserPassword(UserProfileRequest userProfileRequest)
	{
		
	    UserProfileResponse userProfileResponse=new UserProfileResponse();
		
	    if(userProfileRequest.getNewPassword().equals(userProfileRequest.getConfirmPassword()))
	    {
	    if(!userProfileValidation.isValidPassword(userProfileRequest.getNewPassword()))
		{
			userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			userProfileResponse.setResponse("Invalid Password,Please check the password");
			return userProfileResponse;
		
		}
		else {
		Date date=new Date();
		Optional<CustomerCredentials> customercredentials=userIdRespository.findByUsername(userProfileRequest.getUserName());
		if(customercredentials==null)
		{
			userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			userProfileResponse.setResponse("Please enter correct Username");
			return userProfileResponse;
		}
		CustomerCredentials customerCredential=customercredentials.get();	
		
		
		if(!UserProfileUtil.checkPassword(customerCredential.getPassword(),userProfileRequest.getOldPassWord()))
		{
			userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			userProfileResponse.setResponse("Old Password are not Matched");
			return userProfileResponse;

		}else if(UserProfileUtil.checkPassword(customerCredential.getPassword(),userProfileRequest.getNewPassword()))
		{
			userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			userProfileResponse.setResponse("Old Password and New Password are same");
			return userProfileResponse;
		}
		else
		{
			PasswordAudit passwordAudit=new PasswordAudit(customerCredential,customerCredential.getPassword(),date,customerCredential.getUsername());
			passwordAuidRepository.save(passwordAudit);
			customerCredential.setPassword(UserProfileUtil.bcryptPasswordEncoder(userProfileRequest.getNewPassword()));
			userIdRespository.save(customerCredential);
			userProfileResponse.setHttpStatus(HttpStatus.OK);
			userProfileResponse.setResponse("Customer Passowrd Updated");
		}
		
		}
	}else
	{
		userProfileResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
		userProfileResponse.setResponse("New Password and confirm Password not match");
		return userProfileResponse;
	}
	  return userProfileResponse; 
	
	}
}
 