package com.cabapp.pro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

 
import com.cabapp.pro.entity.TripBooking;



@Repository
public interface ITripBookingRepository extends JpaRepository<TripBooking, Integer> {
      
	public List<TripBooking> getTripBookingByCustomerId(int customerId);
	 
	@Query("from TripBooking t where t.customerId=:customerId AND t.status=true")
	public List<TripBooking> findTripBookingByCustomerIdAndStatus(int customerId);
	
	@Query("from TripBooking order by customerId")
	public List<TripBooking> findByCustomerIdAscs();
	
	@Query("from TripBooking  order by fromDateTime")
	public List<TripBooking> findByFromDateTimeAsce();
    
	@Query("from TripBooking where customerId=:customerId AND FromDateTime=:fromDate AND ToDateTime=:toDate")
	public List<TripBooking> findByCustomerIdOnFromDateTimeToToDateTime(int customerId, LocalDate fromDate, LocalDate toDate);
	
	@Query("from TripBooking Group by carType ORDER by carType")
	public List<TripBooking> findByCabWiseAscs();
}
	

