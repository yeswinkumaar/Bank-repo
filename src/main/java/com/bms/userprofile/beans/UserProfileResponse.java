package com.bms.userprofile.beans;





import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class UserProfileResponse {
	
	private HttpStatus httpStatus;
	private String response;
	
		public UserProfileResponse( HttpStatus httpStatus, String response) {
		super();
		this.httpStatus = httpStatus;
		this.response = response;
	}
		
	public UserProfileResponse() {
		
		}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	

}

