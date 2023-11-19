package com.cabapp.pro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabapp.pro.dto.DriverBasicResponseDTO;
import com.cabapp.pro.dto.DriverRequestSubmitDTO;
 
import com.cabapp.pro.dto.TripBookingResponseByBookingDTO;
import com.cabapp.pro.entity.Driver;
import com.cabapp.pro.entity.TripBooking;
import com.cabapp.pro.exception.DriverException;
import com.cabapp.pro.service.IComplaintService;
import com.cabapp.pro.service.IDriverService;
import com.cabapp.pro.util.DriverDTOMapper;
import com.cabapp.pro.util.TripBookingDTOMapper;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/driver")
@Validated
@Api
public class DriverController {
	
	@Autowired
	IDriverService driverService;
	
	
	@Autowired
	IComplaintService complaintService;
	
	@PostMapping("/register")
	public ResponseEntity<Object> saveDriverInfo(@Valid @RequestBody DriverRequestSubmitDTO dto) {
		
		
			DriverDTOMapper converter=new DriverDTOMapper();
			Driver driver= converter.getDriverFromDriverDTO(dto);
			
			return ResponseEntity.status(HttpStatus.OK).body(converter.getDriverBasicDTOFromDriver( driverService.InsertDriver(driver)));
		}
	
	
	@GetMapping("/get/booking/{userId}")//
	public ResponseEntity<TripBookingResponseByBookingDTO> getTripBookinginfo(@PathVariable int userId) {
		if(userId!=0) {
		TripBookingDTOMapper converter=new TripBookingDTOMapper();
		TripBooking trip=driverService.getTripBookingByCustomer(userId);
		TripBookingResponseByBookingDTO dto1=converter.getTripBookingDTOFromTripBookingByDriverId(trip);
		return new ResponseEntity<TripBookingResponseByBookingDTO>(dto1,HttpStatus.OK);
		}
		else {
		 throw new DriverException(400,"user id is null or emtpy");	
		}
	}
	
	@PutMapping("/update/{driverId}")
    public ResponseEntity<DriverBasicResponseDTO> updateDriver(@PathVariable int driverId, @RequestBody Driver driver) {
		DriverDTOMapper converter=new DriverDTOMapper();
        Driver updatedDriver = driverService.updateDriver(driver, driverId);
        DriverBasicResponseDTO dto= converter.getDriverBasicDTOFromDriver(updatedDriver);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PutMapping("/end/trip/{tripId}")
	public  ResponseEntity<Object> endTrip(@PathVariable int tripId) {
		return ResponseEntity.ok(driverService.tripEnd(tripId));
	}
	
	
	@GetMapping("/get/reviews")
	public  ResponseEntity<Object> getReviews(@PathVariable int driverId) {
		return ResponseEntity.ok(driverService.getReviews(driverId));
	}
	



//===================================================================ComplaintModule==============================================================================
       @GetMapping("/complaintOnDriver/{driverId}")
     public  ResponseEntity<Object> complaintOnDriver(@PathVariable int driverId) {
	return ResponseEntity.ok(complaintService.complaintOnDriver(driverId));
}
}