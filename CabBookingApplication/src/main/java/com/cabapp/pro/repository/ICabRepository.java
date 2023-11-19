package com.cabapp.pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cabapp.pro.entity.Cab;
import com.cabapp.pro.entity.TripBooking;


@Repository
public interface ICabRepository extends JpaRepository<Cab, Integer> {
	
	public List<Cab> getCabByCarType(String carType);

	//@Query("from TripBooking Group by carType ORDER by carType")
	//public List<TripBooking> findByCabWiseAscs();
    
	
	public List<Cab> getCabByCurrentLocation(String currentLocation);
	
	@Query("from Cab b where b.currentLocation=:currentLocation AND b.status='assigned'")
	public List<Cab> findByLocationAndStatus(String currentLocation);
    
	@Query("from Cab b where b.carType=:carType AND b.status='assigned'")
	public List<Cab> findByCarTypeAndStatus(String carType);
    
	
	@Query("from Cab b where b.currentLocation=:currentLocation AND b.status='assigned'")
	public List<Cab> findByCurrentLocationAndStatus(String currentLocation);
    
	
	@Query("from Cab b where b.currentLocation=:currentLocation AND b.status='unassigned'")
	public List<Cab> findByStatusAndLocation(String currentLocation);

}
