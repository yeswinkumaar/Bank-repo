package com.bms.userprofile.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;


public class UserProfileBeansTest {

	@Test
	void userProfileTestSuccess()
	{
		UserProfileRequest user=new UserProfileRequest();
		user.setUserName("vignesh123");
		user.setOldPassWord("vicky@456");
		user.setNewPassword("vicky@456");
		user.setEmail("rahul.kumar@gmail.com");
		user.setLastName("kumar");
		user.setDob("19/08/1999");
		user.setAddress("rkv street");
		user.setCity("chennai");
		user.setContactNo("902145456");
		user.setState("TamilNadu");
		user.setCountry("India");
		user.setPincode("638123");
		user.setIsProfileChange("Y");
		user.setIsAddressChange("Y");
		user.setIsPasswordChange("Y");
		assertEquals("vignesh123", user.getUserName());
		assertEquals("vicky@456", user.getOldPassWord());
		assertEquals("vicky@456", user.getNewPassword());
		assertEquals("rahul.kumar@gmail.com", user.getEmail());
		assertEquals("kumar", user.getLastName());
		assertEquals("19/08/1999", user.getDob());
		assertEquals("rkv street", user.getAddress());
		assertEquals("chennai", user.getCity());
		assertEquals("902145456", user.getContactNo());
		assertEquals("TamilNadu", user.getState());
		assertEquals("India", user.getCountry());
		assertEquals("638123", user.getPincode());
		assertEquals("Y", user.getIsProfileChange());
		assertEquals("Y", user.getIsPasswordChange());
		assertEquals("Y", user.getIsAddressChange());
		assertEquals("Y", user.getIsProfileChange());
		
		
		assertEquals("vignesh123", user.getUserName());
			
	}
	@Test
	 void userProfileTestFail()
	{
		UserProfileRequest user=new UserProfileRequest();
		user.setUserName("vignesh123");
		user.setOldPassWord("vicky@456");
		user.setNewPassword("vicky@456");
		user.setEmail("rahul.kumar@gmail.com");
		user.setLastName("kumar");
		user.setDob("19/08/1999");
		user.setAddress("rkv street");
		user.setCity("chennai");
		user.setContactNo("902145456");
		user.setState("TamilNadu");
		user.setCountry("India");
		user.setPincode("638123");
		user.setIsProfileChange("Y");
		user.setIsAddressChange("Y");
		
		user.setIsPasswordChange("Y");
		assertEquals("vignesh123", user.getUserName());
		assertEquals("vicky@456", user.getOldPassWord());
		assertEquals("vicky@456", user.getNewPassword());
		assertEquals("rahul.kumar@gmail.com", user.getEmail());
		assertEquals("kumar", user.getLastName());
		assertEquals("19/08/1999", user.getDob());
		assertEquals("rkv street", user.getAddress());
		assertEquals("chennai", user.getCity());
		assertEquals("902145456", user.getContactNo());
		assertEquals("TamilNadu", user.getState());
		assertEquals("India", user.getCountry());
		assertEquals("638123", user.getPincode());
		assertEquals("Y", user.getIsProfileChange());
		assertEquals("Y", user.getIsPasswordChange());
		assertEquals("Y", user.getIsAddressChange());
		assertEquals("Y", user.getIsProfileChange());
		
		
		assertEquals("vignesh123", user.getUserName());	
	}
	
	@Test
	void customerAddress_Test()
	{
		CustomerAddress customerAddress=new CustomerAddress();
		customerAddress.setAddress("ecr road");
		customerAddress.setAddressId((long) 1);
		customerAddress.setCountry("IND");
		customerAddress.setIsActive("y");
		customerAddress.setPinCode("287912");
		customerAddress.setState("tamilnadu");
		customerAddress.setCity("chennai");
		
		assertEquals("ecr road",customerAddress.getAddress());
		assertEquals("tamilnadu",customerAddress.getState());
		assertEquals("287912",customerAddress.getPinCode());
		assertEquals(1,customerAddress.getAddressId());
		assertEquals("IND",customerAddress.getCountry());
		assertEquals("y",customerAddress.getIsActive());
		assertEquals("chennai",customerAddress.getCity());
		
	}
	
	@Test
	void customerCredentials_Test()
	{
		CustomerCredentials customerCredentials=new CustomerCredentials("bdcd","bcsb","bbcb");
		CustomerCredentials customerCredential=new CustomerCredentials("bdcd","bcsb");
		customerCredentials.setUsername("MOHAN123");
		customerCredentials.setId((long) 1);
		customerCredentials.setLoggedInKey("hyugd");
		customerCredentials.setPassword("mohan@123");
		customerCredential.toString();
		assertEquals("MOHAN123",customerCredentials.getUsername());
		assertEquals(1,customerCredentials.getId());
		assertEquals("hyugd",customerCredentials.getLoggedInKey());
		assertEquals("mohan@123",customerCredentials.getPassword());
		
	}
	@Test
	void userProfileResponse_Test()
	{
		UserProfileResponse customerCredentials=new UserProfileResponse(HttpStatus.ACCEPTED,"updated");
		customerCredentials.setHttpStatus(HttpStatus.ACCEPTED);
		customerCredentials.setResponse("updated");
		assertEquals(HttpStatus.ACCEPTED,customerCredentials.getHttpStatus());
		assertEquals("updated",customerCredentials.getResponse());
		
	}
	@Test
	void customerdetail_Test()
	{
		CustomerDetail c=new CustomerDetail();
		c.setContactNo("883988989");
		c.setCustomerId((long) 1);
		c.setDob(new Date());
		c.setEmailId("sbabau@gamil.com");
		c.setFirstName("vignesh");
		c.setLastName("babu");
		c.setPan("dsgydhh");
		c.setGender("male");
		c.setId(new CustomerCredentials("xsjj","shduhh"));
		
		
		assertEquals(1, c.getCustomerId());
		assertEquals("883988989", c.getContactNo());
		assertNotNull(c.getDob());
		assertEquals("vignesh", c.getFirstName());
		assertEquals("sbabau@gamil.com", c.getEmailId());
		assertEquals("babu", c.getLastName());
		assertEquals("male", c.getGender());
		assertEquals("dsgydhh", c.getPan());
	}
	
	
}
