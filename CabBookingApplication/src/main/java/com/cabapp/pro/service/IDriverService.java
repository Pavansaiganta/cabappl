package com.cabapp.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cabapp.pro.entity.Driver;
import com.cabapp.pro.entity.Review;
import com.cabapp.pro.entity.TripBooking;

@Service

public interface IDriverService {
	
	
	public Driver InsertDriver(Driver driver);
	public List<Driver> getDriverByStatus(String status);
	public Driver updateDriverByStatus(int driverId);
	public TripBooking getTripBookingByCustomer(int userId);
	public TripBooking tripEnd(int tripId);
	public String addReview(int driverId,Review review);
	public List<Review> getReviews(int driverId);
	public String deleteReview(int reviewId);
	public List<Driver> viewAllDrivers();
	public List<Driver> getDriverByRating(float rating);
	public Driver updateDriver(Driver driver, int driverId);
	

}
