package com.cabapp.pro.dto;

public class CabResponseDTO {
	
	
	private int cabId;
	private String carType;
	private float perKmRate;
	private String status;
	private String currentLocation;
	private String cabRegistrationNumber;
	
	public CabResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CabResponseDTO(int cabId, String carType, float perKmRate, String status, String currentLocation,
			String cabRegistrationNumber) {
		super();
		this.cabId = cabId;
		this.carType = carType;
		this.perKmRate = perKmRate;
		this.status = status;
		this.currentLocation = currentLocation;
		this.cabRegistrationNumber = cabRegistrationNumber;
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
	 
	 
}