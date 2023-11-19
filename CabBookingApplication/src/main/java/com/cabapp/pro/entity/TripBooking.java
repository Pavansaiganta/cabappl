package com.cabapp.pro.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
public class TripBooking {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tripBookingId;
	private int customerId; 
	private String fromLocation;
	private String toLocation;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private boolean status;
	private float distanceInKm;
	private float bill;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Driver driver;
    
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	public TripBooking() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TripBooking(int customerId, String fromLocation, String toLocation, LocalDateTime fromDateTime,
			LocalDateTime toDateTime, boolean status, float distanceInKm, float bill, Driver driver) {
		super();
		this.customerId = customerId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.status = status;
		this.distanceInKm = distanceInKm;
		this.bill = bill;
		this.driver = driver;
	}
	


	public TripBooking(int tripBookingId, int customerId, String fromLocation, String toLocation,
			LocalDateTime fromDateTime, LocalDateTime toDateTime, boolean status, float distanceInKm, float bill,
			Driver driver, Customer customer) {
		super();
		this.tripBookingId = tripBookingId;
		this.customerId = customerId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.status = status;
		this.distanceInKm = distanceInKm;
		this.bill = bill;
		this.driver = driver;
		this.customer = customer;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
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


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
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


	public Driver getDriver() {
		return driver;
	}


	public void setDriver(Driver driver) {
		this.driver = driver;
	}


	@Override
	public String toString() {
		return "TripBooking [tripBookingId=" + tripBookingId + ", customerId=" + customerId + ", fromLocation="
				+ fromLocation + ", toLocation=" + toLocation + ", fromDateTime=" + fromDateTime + ", toDateTime="
				+ toDateTime + ", status=" + status + ", distanceInKm=" + distanceInKm + ", bill=" + bill + ", driver="
				+ driver + ", customer=" + customer + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(bill, customer, customerId, distanceInKm, driver, fromDateTime, fromLocation, status,
				toDateTime, toLocation, tripBookingId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripBooking other = (TripBooking) obj;
		return Float.floatToIntBits(bill) == Float.floatToIntBits(other.bill)
				&& Objects.equals(customer, other.customer) && customerId == other.customerId
				&& Float.floatToIntBits(distanceInKm) == Float.floatToIntBits(other.distanceInKm)
				&& Objects.equals(driver, other.driver) && Objects.equals(fromDateTime, other.fromDateTime)
				&& Objects.equals(fromLocation, other.fromLocation) && status == other.status
				&& Objects.equals(toDateTime, other.toDateTime) && Objects.equals(toLocation, other.toLocation)
				&& tripBookingId == other.tripBookingId;
	}
	

}
