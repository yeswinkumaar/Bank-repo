package com.bms.userprofile.bo;



import com.bms.userprofile.beans.UserProfileRequest;
import com.bms.userprofile.beans.UserProfileResponse;


public interface UserProfileBO {

	
	public  UserProfileResponse checkUserAvailability(String userName) throws NullPointerException,Exception;
	
	public UserProfileResponse updateUserProfile(UserProfileRequest userProfileRequest) throws NullPointerException,Exception;
}
