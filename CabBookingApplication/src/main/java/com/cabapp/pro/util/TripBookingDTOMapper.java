package com.cabapp.pro.util;

 
 
import java.time.LocalDateTime;

import com.cabapp.pro.dto.TripBookingEndTripCustomerResponseDTO;
import com.cabapp.pro.dto.TripBookingRequestSubmitDTO;
import com.cabapp.pro.dto.TripBookingResponseByBookingDTO;
import com.cabapp.pro.dto.TripBookingResponseByCurrentBookingDTO;
import com.cabapp.pro.dto.TripBookingResponseDTO;
import com.cabapp.pro.entity.TripBooking;

public class TripBookingDTOMapper {
	
	public TripBooking getTripBookingFromTripBookingDTO(TripBookingRequestSubmitDTO dto)
	{
		TripBooking e = new TripBooking();
		
		e.setCustomerId(dto.getCustomerId());
		e.setFromLocation(dto.getFromLocation());
		e.setToLocation(dto.getToLocation());
		e.setFromDateTime(LocalDateTime.now());
		e.setToDateTime(LocalDateTime.now());
		e.setDistanceInKm(dto.getDistanceInKm());
		  
		
 
		return e;
 
	}
	public TripBookingResponseDTO getTripBookingDTOFromTripBooking(TripBooking tripBooking)
	{
		TripBookingResponseDTO e = new TripBookingResponseDTO();
		e.setTripBookingId(tripBooking.getTripBookingId());
		e.setCustomerId(tripBooking.getCustomerId());
		e.setFromLocation(tripBooking.getFromLocation());
		e.setToLocation(tripBooking.getToLocation());
		e.setFromDateTime(LocalDateTime.now());
		e.setToDateTime(LocalDateTime.now());
		e.setDistanceInKm(tripBooking.getDistanceInKm());
		e.setStatus(tripBooking.isStatus());
		e.setBill(tripBooking.getBill());
		e.setDrivername(tripBooking.getDriver().getUsername());
		e.setMobileNumber(tripBooking.getDriver().getMobileNumber());
		e.setEmail(tripBooking.getDriver().getEmail());
		e.setCabRegistrationNumber(tripBooking.getDriver().getCab().getCabRegistrationNumber());
		e.setCarType(tripBooking.getDriver().getCab().getCarType());
		
 
		return e;
	 
		
	}
	public TripBookingResponseByBookingDTO getTripBookingDTOFromTripBookingByDriverId(TripBooking tripBooking) {
	TripBookingResponseByBookingDTO e = new TripBookingResponseByBookingDTO();
	e.setTripBookingId(tripBooking.getTripBookingId());
	e.setCustomerId(tripBooking.getCustomerId());
	e.setFromLocation(tripBooking.getFromLocation());
	e.setToLocation(tripBooking.getToLocation());
	e.setFromDateTime(LocalDateTime.now());
	e.setToDateTime(LocalDateTime.now());
	e.setDistanceInKm(tripBooking.getDistanceInKm());
	 
	e.setBill(tripBooking.getBill());
	 
	e.setCustomername(tripBooking.getCustomer().getUsername());
	e.setMobileNumber(tripBooking.getCustomer().getMobileNumber());
 
	 return e;
	}
	
	 public TripBookingResponseByCurrentBookingDTO getTripBookingDTOFromTripBookingByCurrentBooking(TripBooking tripBooking) {
		 TripBookingResponseByCurrentBookingDTO e = new TripBookingResponseByCurrentBookingDTO();
			e.setTripBookingId(tripBooking.getTripBookingId());
			e.setCustomerId(tripBooking.getCustomerId());
			e.setFromLocation(tripBooking.getFromLocation());
			e.setToLocation(tripBooking.getToLocation());
			e.setFromDateTime(LocalDateTime.now());
			e.setToDateTime(LocalDateTime.now());
			e.setDistanceInKm(tripBooking.getDistanceInKm());
			 
			e.setBill(tripBooking.getBill());
			 
			e.setCustomername(tripBooking.getCustomer().getUsername());
			e.setMobileNumber(tripBooking.getCustomer().getMobileNumber());
			e.setDrivername(tripBooking.getDriver().getUsername());
			e.setMobileNumber(tripBooking.getDriver().getMobileNumber());
			e.setEmail(tripBooking.getDriver().getEmail());
			e.setCabRegistrationNumber(tripBooking.getDriver().getCab().getCabRegistrationNumber());
			e.setCarType(tripBooking.getDriver().getCab().getCarType());
		 
			 return e;
	 }
			 public TripBookingEndTripCustomerResponseDTO getTripBookingEndTripCustomerDTOFromTripBooking(TripBooking tripBooking) {
				 TripBookingEndTripCustomerResponseDTO e=new TripBookingEndTripCustomerResponseDTO();
				 e.setTripBookingId(tripBooking.getTripBookingId());
					e.setCustomerId(tripBooking.getCustomerId());
					e.setFromLocation(tripBooking.getFromLocation());
					e.setToLocation(tripBooking.getToLocation());
					e.setFromDateTime(LocalDateTime.now());
					e.setToDateTime(LocalDateTime.now());
					e.setDistanceInKm(tripBooking.getDistanceInKm());
					e.setBill(tripBooking.getBill());
					e.setDrivername(tripBooking.getDriver().getUsername());
					e.setMobileNumber(tripBooking.getDriver().getMobileNumber());
					e.setEmail(tripBooking.getDriver().getEmail());
					e.setCabRegistrationNumber(tripBooking.getDriver().getCab().getCabRegistrationNumber());
					e.setCarType(tripBooking.getDriver().getCab().getCarType());
					
					
					 return e;
					
			 

}
}
