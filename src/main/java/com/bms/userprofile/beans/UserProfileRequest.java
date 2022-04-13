package com.bms.userprofile.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserProfileRequest {

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getIsProfileChange() {
		return isProfileChange;
	}

	public void setIsProfileChange(String isProfileChange) {
		this.isProfileChange = isProfileChange;
	}

	public String getIsAddressChange() {
		return isAddressChange;
	}

	public void setIsAddressChange(String isAddressChange) {
		this.isAddressChange = isAddressChange;
	}

	public String getIsPasswordChange() {
		return isPasswordChange;
	}

	public void setIsPasswordChange(String isPasswordChange) {
		this.isPasswordChange = isPasswordChange;
	}

	public UserProfileRequest(@NotBlank @Size(min = 8, max = 15) Long customerId,
			@NotBlank @Size(min = 8, max = 15) String userName,
			@NotBlank @Size(max = 15) String oldPassWord,
			@NotBlank String newPassword, @NotBlank String confirmPassword, @NotBlank @Size(max = 15) String lastName,
			@NotBlank @Size(max = 15) String email, @NotBlank String dob, @NotBlank @Size(max = 10) String contactNo,
			@NotBlank String address, @NotBlank String city, @NotBlank String state, @NotBlank String country,
			@NotBlank @Size(max = 6) String pincode, @NotBlank @Size(max = 3) String isProfileChange,
			@NotBlank @Size(max = 3) String isAddressChange, @NotBlank @Size(max = 3) String isPasswordChange) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.oldPassWord = oldPassWord;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.contactNo = contactNo;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.isProfileChange = isProfileChange;
		this.isAddressChange = isAddressChange;
		this.isPasswordChange = isPasswordChange;
	}

	@NotBlank
	@Size(min = 8, max = 15)
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@NotBlank
	@Size(min = 8, max = 15)
	String userName;

	@NotBlank
	@Size(max = 15)
	String oldPassWord;

	@NotBlank
	String newPassword;

	@NotBlank
	String confirmPassword;

	public String getOldPassWord() {
		return oldPassWord;
	}

	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@NotBlank
	@Size(max = 15)
	String lastName;
	@NotBlank
	@Size(max = 15)
	String email;

	@NotBlank
	String dob;

	@NotBlank
	@Size(max = 10)
	String contactNo;

	@NotBlank
	String address;

	@NotBlank
	String city;

	@NotBlank
	String state;

	@NotBlank
	String country;

	@NotBlank
	@Size(max = 6)
	String pincode;

	@NotBlank
	@Size(max = 3)
	String isProfileChange;

	@NotBlank
	@Size(max = 3)
	String isAddressChange;

	public UserProfileRequest() {
		super();
	}

	@NotBlank
	@Size(max = 3)
	String isPasswordChange;

}
