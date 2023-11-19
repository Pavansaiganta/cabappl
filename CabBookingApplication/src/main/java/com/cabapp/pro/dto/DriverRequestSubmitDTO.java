package com.cabapp.pro.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class DriverRequestSubmitDTO {
	
	@NotBlank(message = "Username cannot be blank")
    private String username;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    
    @NotNull(message="Address cannot be null")
    private String address;

    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Email(message = "Email should be valid")
    private String email;
	private int driverId;
	private String licenseNo; 
	@NotNull(message="Current location cannot be null")
	private String currentLocation;
	 
	 
	public DriverRequestSubmitDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DriverRequestSubmitDTO(@NotBlank(message = "Username cannot be blank") String username,
			@Size(min = 6, message = "Password must be at least 6 characters long") String password,
			@NotNull(message = "Address cannot be null") String address,
			@Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits") String mobileNumber,
			@Email(message = "Email should be valid") String email, int driverId, String licenseNo,
			@NotNull(message = "Current location cannot be null") String currentLocation) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.driverId = driverId;
		this.licenseNo = licenseNo;
		this.currentLocation = currentLocation;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getDriverId() {
		return driverId;
	}


	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}


	public String getLicenseNo() {
		return licenseNo;
	}


	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}


	public String getCurrentLocation() {
		return currentLocation;
	}


	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	 
	 
	
}