package com.cabapp.pro.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(description = "Details about Cab Bean")
public class Cab {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cabId;
	private String carType;
	private float perKmRate;
	private String cabRegistrationNumber;
	private String currentLocation;
	private String status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "driv_Id")
	private Driver driver;
	
	// should be bi directional with driver
	// remove locations and add only current location
	
	public Cab() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
	
 
	 
 
	public Cab(int cabId, String carType, float perKmRate, String cabRegistrationNumber, String currentLocation,
			String status, Driver driver) {
		super();
		this.cabId = cabId;
		this.carType = carType;
		this.perKmRate = perKmRate;
		this.cabRegistrationNumber = cabRegistrationNumber;
		this.currentLocation = currentLocation;
		this.status = status;
		this.driver = driver;
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






	public String getCabRegistrationNumber() {
		return cabRegistrationNumber;
	}






	public void setCabRegistrationNumber(String cabRegistrationNumber) {
		this.cabRegistrationNumber = cabRegistrationNumber;
	}






	public String getCurrentLocation() {
		return currentLocation;
	}






	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}






	public String getStatus() {
		return status;
	}






	public void setStatus(String status) {
		this.status = status;
	}






	public Driver getDriver() {
		return driver;
	}






	public void setDriver(Driver driver) {
		this.driver = driver;
	}






	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + ", carType=" + carType + ", perKmRate=" + perKmRate + ", cabRegistrationNumber="
				+ cabRegistrationNumber + ", currentLocation=" + currentLocation + ", status=" + status + ", driver="
				+ driver + "]";
	}






	@Override
	public int hashCode() {
		return (int)((cabId+perKmRate+cabRegistrationNumber.length())/3+3);
	}
	






	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cab other = (Cab) obj;
		return cabId == other.cabId && Objects.equals(cabRegistrationNumber, other.cabRegistrationNumber)
				&& Objects.equals(carType, other.carType) && Objects.equals(currentLocation, other.currentLocation)
				&& Objects.equals(driver, other.driver)
				&& Float.floatToIntBits(perKmRate) == Float.floatToIntBits(other.perKmRate)
				&& Objects.equals(status, other.status);
	}






	 
 
	 
	
	
 
	
	

}
