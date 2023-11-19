package com.cabapp.pro.dto;

public class CabCurrentBookingResponseDTO {
	private int cabId;
	private String carType;
	private float perKmRate;
	private String status;
	private String currentLocation;
	private String cabRegistrationNumber;
	private String driverName;
	private String driverLicenseNo;
	private String driverStatus;
	public CabCurrentBookingResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CabCurrentBookingResponseDTO(int cabId, String carType, float perKmRate, String status,
			String currentLocation, String cabRegistrationNumber, String driverName, String driverLicenseNo,
			String driverStatus) {
		super();
		this.cabId = cabId;
		this.carType = carType;
		this.perKmRate = perKmRate;
		this.status = status;
		this.currentLocation = currentLocation;
		this.cabRegistrationNumber = cabRegistrationNumber;
		this.driverName = driverName;
		this.driverLicenseNo = driverLicenseNo;
		this.driverStatus = driverStatus;
	}
	public int getCabId() {
		return cabId;
	}
	public void setCabId(int cabId) {
		this.cabId = cabId;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public float getPerKmRate() {
		return perKmRate;
	}
	public void setPerKmRate(float perKmRate) {
		this.perKmRate = perKmRate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getCabRegistrationNumber() {
		return cabRegistrationNumber;
	}
	public void setCabRegistrationNumber(String cabRegistrationNumber) {
		this.cabRegistrationNumber = cabRegistrationNumber;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverLicenseNo() {
		return driverLicenseNo;
	}
	public void setDriverLicenseNo(String driverLicenseNo) {
		this.driverLicenseNo = driverLicenseNo;
	}
	public String getDriverStatus() {
		return driverStatus;
	}
	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}
	
	
}