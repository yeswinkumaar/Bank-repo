package com.bms.userprofile.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="customer_detail")
public class CustomerDetail {

	@Id
	@Column(name = "customer_id ")
	@GenericGenerator(strategy = "native",name = "native")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	private Long customerId;

	@Column(name="first_name",columnDefinition = "TEXT")
	@NotNull(message = "")
	@Size(max=25)
	private String firstName;
	
	@Column(name="last_name",columnDefinition = "TEXT")
	@NotNull(message = "")
	@Size(max=25)
	private String lastName;

	@Column(name="email_id",length = 25)
	@NotNull(message = "")
	@Size(max=25)
	@Email(message = "Pattern is not correct!")
	private  String emailId;
	
	@Column(name="gender")
	@NotNull(message = "")
	private  String gender;
	
	@Column(name="dob")
	@NotNull(message = "")
	@Size(max=25)
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name="contact_no")
	@NotNull(message = "")
	@Min(value = 10,message = "")
	@Max(value = 10,message = "")
	private String contactNo;
	
	
	@Column(name="pan_no",updatable = false)
	@NotNull(message = "")
	@Size(max=10)
	private  String pan;

	@OneToOne
	private CustomerCredentials customerCredentials;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public CustomerCredentials getId() {
		return customerCredentials;
	}


	public void setId(CustomerCredentials customerCredentials) {
		this.customerCredentials = customerCredentials;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}
	public CustomerDetail() {}

	
	public CustomerDetail(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public CustomerDetail( CustomerCredentials customerCredentials,  String firstName,
			 String lastName,  String emailId, String gender,  Date dob,
			 String contactNo,  String pan) {
		super();
		this.customerCredentials = customerCredentials;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.gender = gender;
		this.dob = dob;
		this.contactNo = contactNo;
		this.pan = pan;
	}
}
