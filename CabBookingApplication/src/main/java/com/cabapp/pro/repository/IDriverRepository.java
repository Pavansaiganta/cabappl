package com.cabapp.pro.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cabapp.pro.entity.Driver;
import com.cabapp.pro.entity.TripBooking;



@Repository
public interface IDriverRepository extends JpaRepository<Driver, Integer> {

	
	
	@Query("from Driver d where d.available=true AND d.currentLocation=:fromLocation")
	public List<Driver> findByAvailableAndLocation(String fromLocation);
	@Query("from Driver d where d.available=true AND d.status='Pending' AND d.driverId =:driverId")
	public Driver getDriverByIdAndStatus(int driverId);
	public Driver findByDriverId(int driverId);

	public List<Driver> findByStatus(String status);


	public Driver findByUsername(String username);
	 
		public Driver getDriverByDriverId(int driverId);
		
		@Query("from Driver d where d.status='ok'")
		public List<Driver> findDriverByStatus();
		
		@Query("from Driver d where d.avgRating=:rating")
		public List<Driver> findByRating(float rating);
	 

}
