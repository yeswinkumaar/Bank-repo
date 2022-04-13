package com.bms.userprofile.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="customer_address")

public class CustomerAddress {

	@Id
	@Column(name="address_id")
	@GenericGenerator(strategy = "native",name = "native")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	private Long addressId;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private CustomerDetail customerDetail;

	@Column(name="address",length = 100)
	@Size(max=100)
	private String address;

	@Column(name="city",length = 20)
	@Size(max=20)
	private String city;

	@Column(name="state",length = 20)
	@Size(max=20)
	private String state;

	@Column(name="pin_code",length = 6)
	@Size(max=6)
	private String pinCode;
	
	@Column(name="country",length = 5)
	@Size(max=5)
	private String country;
	
	@Column(name="is_active")
	private String isActive;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public CustomerDetail getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
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

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public CustomerAddress( @Size(max = 100) String address, @Size(max = 20) String city,
			@Size(max = 20) String state, @Size(max = 6) String pinCode, @Size(max = 2) String country,
			String isActive) {
		super();
		
		this.address = address;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.country = country;
		this.isActive = isActive;
	}

	public CustomerAddress() {}

	public CustomerAddress(CustomerDetail customerDetail,String address,  String city,
			String state, String pinCode,  String country,
			String isActive) {
		super();
		this.customerDetail = customerDetail;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.country = country;
		this.isActive = isActive;
	}
}
