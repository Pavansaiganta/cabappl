package com.cabapp.pro.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@DiscriminatorValue("driver")
@Data
public class Driver extends AbstractUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int driverId;
	private String licenseNo;
	private String status;
	private Boolean available;
	private String currentLocation;
	private Double avgRating;
//	@OneToMany(cascade=CascadeType.ALL,mappedBy = "driverId")
//	private List<Complaint> complaint;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cab_Id")
	private Cab cab;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Dri_Id")
	private List<TripBooking> tripbookings;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id")
	private List<Review> reviews;


	


	
}
