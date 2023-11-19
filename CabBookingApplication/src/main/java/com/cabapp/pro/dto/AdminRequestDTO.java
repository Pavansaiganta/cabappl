package com.cabapp.pro.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class AdminRequestDTO {
	@NotNull(message = "username cannot be null")
	private String username;
	@NotNull(message = "password cannot be null")
	private String password;
	private String address;
	private String mobileNumber;
	private String email;
	private int adminId;
	public AdminRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminRequestDTO(String username, String password, String address, String mobileNumber, String email,
			int adminId) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.adminId = adminId;
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
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	

}
