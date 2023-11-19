package com.cabapp.pro.dto;

import java.time.LocalDateTime;

public class TripBookingResponseByCurrentBookingDTO {
	private int tripBookingId;
	private int customerId;
	private String fromLocation;
	private String toLocation;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private float distanceInKm;
	private float bill;
	private String customername;
	private String mobileNumber;
	private String drivername;
	private String driverMobileNumber;
	private String email;
	private String cabRegistrationNumber;
	private String carType;
	public TripBookingResponseByCurrentBookingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TripBookingResponseByCurrentBookingDTO(int tripBookingId, int customerId, String fromLocation,
			String toLocation, LocalDateTime fromDateTime, LocalDateTime toDateTime, float distanceInKm, float bill,
			String customername, String mobileNumber, String drivername, String driverMobileNumber, String email,
			String cabRegistrationNumber, String carType) {
		super();
		this.tripBookingId = tripBookingId;
		this.customerId = customerId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.distanceInKm = distanceInKm;
		this.bill = bill;
		this.customername = customername;
		this.mobileNumber = mobileNumber;
		this.drivername = drivername;
		this.driverMobileNumber = driverMobileNumber;
		this.email = email;
		this.cabRegistrationNumber = cabRegistrationNumber;
		this.carType = carType;
	}
	public int getTripBookingId() {
		return tripBookingId;
	}
	public void setTripBookingId(int tripBookingId) {
		this.tripBookingId = tripBookingId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}
	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}
	public LocalDateTime getToDateTime() {
		return toDateTime;
	}
	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}
	public float getDistanceInKm() {
		return distanceInKm;
	}
	public void setDistanceInKm(float distanceInKm) {
		this.distanceInKm = distanceInKm;
	}
	public float getBill() {
		return bill;
	}
	public void setBill(float bill) {
		this.bill = bill;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getDriverMobileNumber() {
		return driverMobileNumber;
	}
	public void setDriverMobileNumber(String driverMobileNumber) {
		this.driverMobileNumber = driverMobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCabRegistrationNumber() {
		return cabRegistrationNumber;
	}
	public void setCabRegistrationNumber(String cabRegistrationNumber) {
		this.cabRegistrationNumber = cabRegistrationNumber;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	

}
