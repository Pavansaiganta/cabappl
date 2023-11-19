package com.cabapp.pro.dto;

import java.time.Instant;

public class ComplaintResponseDTO {
	
	private Integer complaintId;
    private Instant dateOnRegister;
    private String comment;
    private String status;
    private String email;
    private int customerId;
    private int driverId;
	public ComplaintResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComplaintResponseDTO(Integer complaintId, Instant dateOnRegister, String comment, String status,
			String email, int customerId, int driverId) {
		super();
		this.complaintId = complaintId;
		this.dateOnRegister = dateOnRegister;
		this.comment = comment;
		this.status = status;
		this.email = email;
		this.customerId = customerId;
		this.driverId = driverId;
	}
	public Integer getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
	public Instant getDateOnRegister() {
		return dateOnRegister;
	}
	public void setDateOnRegister(Instant dateOnRegister) {
		this.dateOnRegister = dateOnRegister;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

}
