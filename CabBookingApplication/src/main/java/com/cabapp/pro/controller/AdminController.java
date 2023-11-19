package com.cabapp.pro.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cabapp.pro.entity.*;
import com.cabapp.pro.exception.AdminException;
import com.cabapp.pro.service.*;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cabapp.pro.dto.AdminRequestDTO;
import com.cabapp.pro.dto.AdminResponseDTO;
import com.cabapp.pro.dto.CabCurrentBookingResponseDTO;
import com.cabapp.pro.dto.CabRequestDTO;
import com.cabapp.pro.dto.CabResponseDTO;
import com.cabapp.pro.dto.DriverBasicResponseDTO;
import com.cabapp.pro.dto.DriverResponseDTO;
import com.cabapp.pro.dto.TripBookingResponseByCurrentBookingDTO;
import com.cabapp.pro.dto.TripBookingResponseDTO;
import com.cabapp.pro.util.AdminDTOMapper;
import com.cabapp.pro.util.CabDTOMapper;
import com.cabapp.pro.util.DriverDTOMapper;
import com.cabapp.pro.util.TripBookingDTOMapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@Api
public class AdminController {
	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	IAdminService adminService;
	
	
	@Autowired
	IDriverService driverService;
	
	
	@Autowired
	ITripBookingService tripBooking;
	
	@Autowired
	ICabService cabService;

	@Autowired
	private IComplaintService complaintService;
	
	//==================================================AdminApi====================================================================================
	
	/**
     * Save Admin Endpoint.
     *
     * @param dto The AdminRequestDTO containing admin information.
     * @return ResponseEntity with AdminResponseDTO and HTTP status OK.
     * @throws AdminException If the request is null (HTTP status 400).
     */
	@PostMapping("/save") 
	public ResponseEntity<AdminResponseDTO> saveAdmin(@Valid @RequestBody AdminRequestDTO dto){
		AdminDTOMapper adminConverter=new AdminDTOMapper();

		if(dto!=null) {
			logger.info("recieved request to save admin " , dto);
			Admin admin=adminConverter.getAdminFromAdminDTO(dto);
			AdminResponseDTO dto1= adminConverter.getAdminDTOFromAdmin(adminService.insertAdmin(admin));
			
			return new ResponseEntity<AdminResponseDTO>(dto1, HttpStatus.OK);
			
		} else {
			throw new AdminException(400,"request is null ");
		}
		
	}
	  /**
     * Update Admin Endpoint.
     *
     * @param dto The AdminRequestDTO containing updated admin information.
     * @return ResponseEntity with AdminResponseDTO and HTTP status OK.
     * @throws AdminException If the request is null (HTTP status 400).
     */
	
	
	@PutMapping("/update")
	public ResponseEntity<AdminResponseDTO> updateAdmin(@Valid @RequestBody AdminRequestDTO dto){
		AdminDTOMapper adminConverter=new AdminDTOMapper();
		if(dto!=null) {
			logger.info("recieved request to update admin " , dto);

			Admin admin=adminConverter.getAdminFromAdminDTO(dto);
			AdminResponseDTO adminDTO= adminConverter.getAdminDTOFromAdmin(adminService.updateAdmin(admin));
			
			return new ResponseEntity<AdminResponseDTO>(adminDTO, HttpStatus.OK);
			
		}
		 else {
				throw new AdminException(400,"request is null ");
			}
		
	}
	 /**
     * Get All Trip Bookings Endpoint.
     *
     * @param cusId The customer ID for retrieving trip bookings.
     * @return ResponseEntity with a list of TripBookingResponseDTO and HTTP status OK.
     */
	@GetMapping("/getall/trips/{cusId}")
	public ResponseEntity<List<TripBookingResponseDTO>> getAllTripBookings(@PathVariable int cusId){
		if(cusId!=0) {
			logger.info("recieved request to get all trip booking for customer id " , cusId);

			List<TripBooking> totalTrips=tripBooking.getAllTrips(cusId);
			TripBookingDTOMapper tripDto=new TripBookingDTOMapper();
			List<TripBookingResponseDTO> finalList=new ArrayList<>();
		    for(TripBooking t:totalTrips) {
		    	finalList.add(tripDto.getTripBookingDTOFromTripBooking(t));
		    	
		    }
		
		    
		    return new ResponseEntity<List<TripBookingResponseDTO>>(finalList, HttpStatus.OK);
		}
		return null;
	}
	

	
//===============================================API's to access Driver=============================================================
	
	
	/**
     * Get New Registration Requests Endpoint.
     *
     * @param stat The status parameter to filter new registration requests.
     * @return ResponseEntity with a list of DriverResponseDTO and HTTP status OK.
     */
	@GetMapping("/get/newreq/dr/{stat}")//http://localhost:5677/admin/get/newreq/dr/Pending ....works fine
	public ResponseEntity<List<DriverBasicResponseDTO>> getNewRegisterationRequests(@PathVariable String stat) {
		if (stat != null) {
			logger.info("recieved request for registration  " , stat);
 
			DriverDTOMapper driverConverter = new DriverDTOMapper();
 
			List<Driver> groupedByStatus = driverService.getDriverByStatus(stat);
			List<DriverBasicResponseDTO> newDriversList=new ArrayList<>();
 
			for (Driver d : groupedByStatus) {
				 if(d!=null) {
					newDriversList.add(driverConverter.getDriverBasicDTOFromDriver(d));
				 }
				
				
			}
			return new ResponseEntity<List<DriverBasicResponseDTO>>(newDriversList, HttpStatus.OK);
 
		}
 
		return null;
	}
	 /**
     * Update Driver Registration Request Status Endpoint.
     *
     * @param driId   The driver ID to identify the driver.
     * @param stat    The current status of the registration request.
     * @param newStat The new status to update the registration request.
     * @return ResponseEntity with DriverResponseDTO and HTTP status OK.
     */
	@PutMapping("/update/status")//working fine but clarify on the status part......
	public ResponseEntity<DriverResponseDTO> updateDriverRegisterationRequesr(@RequestParam int driId){
		DriverDTOMapper driverConverter = new DriverDTOMapper();
		if(driId !=0) {
			logger.info("recieved request to update registration " , driId);
			Driver driver=driverService.updateDriverByStatus(driId);
			DriverResponseDTO finaldriver=driverConverter.getDriverDTOFromDriver(driver);
			return new ResponseEntity<DriverResponseDTO>(finaldriver, HttpStatus.OK);
		}
		return null;
		
		
		
	}
	@GetMapping("/active/alldrivres")
    public ResponseEntity<List<DriverResponseDTO>> getAllDrivers() {
		DriverDTOMapper driverConverter = new DriverDTOMapper();
        List<Driver> drivers = driverService.viewAllDrivers();
        List<DriverResponseDTO> allDriversList=new ArrayList<>();
 
		for (Driver d : drivers) {
			 if(d!=null) {
				 allDriversList.add(driverConverter.getDriverDTOFromDriver(d));
			 }
			
			
		}
        
        return new ResponseEntity<List<DriverResponseDTO>>(allDriversList, HttpStatus.OK);
    }
	
	//===============================================API's for cab=====================================================================
	
	 /**
     * Save Cab Endpoint.
     *
     * @param dto The CabRequestDTO containing cab information.
     * @return ResponseEntity with CabResponseDTO and HTTP status OK.
     */
	@PostMapping("/insert/cab")
	public ResponseEntity<CabResponseDTO> saveCab(@RequestBody CabRequestDTO dto){
		CabDTOMapper cabConverter=new CabDTOMapper();
		if(dto!=null) {
			logger.info("recieved request to save cab " , dto);

			Cab cab=cabService.insertCab(cabConverter.getCabFromCabDTO(dto));
		CabResponseDTO cabDto=cabConverter.getCabDTOFromCab(cab);
		return new ResponseEntity<CabResponseDTO>(cabDto, HttpStatus.OK);
		}
		return null;
	}
	
	
	/**
     * Update Cab Endpoint.
     *
     * @param dto The CabRequestDTO containing updated cab information.
     * @return ResponseEntity with CabResponseDTO and HTTP status OK.
     */
	@PutMapping("/update/cab")
	public ResponseEntity<CabResponseDTO> updateCab(@Valid @RequestBody CabRequestDTO dto){
		CabDTOMapper cabConverter=new CabDTOMapper();
		if(dto!=null) {
			logger.info("recieved request to update cab " , dto);

			Cab cab=cabService.updateCab(cabConverter.getCabFromCabDTO(dto));
		CabResponseDTO newCabDto=cabConverter.getCabDTOFromCab(cab);
		return new ResponseEntity<CabResponseDTO>(newCabDto, HttpStatus.OK);
		}
		return null;
	}
	
	
	/**
     * Delete Cab Endpoint.
     *
     * @param cabId The ID of the cab to be deleted.
     * @return ResponseEntity with a message and HTTP status OK.
     */
	@DeleteMapping("/delete/cab/{cabId}")
	public ResponseEntity<String> deleteCab(@PathVariable int cabId){
		logger.info("recieved request to delete cab " , cabId);

		String message=cabService.deleteCab(cabId);
		return new ResponseEntity<String>(message , HttpStatus.OK);
	}
	
	@GetMapping("/getactivecabs/OffType/{carType}")
	public ResponseEntity<List<CabCurrentBookingResponseDTO>> getActiveCabByType(@PathVariable String carType){
		CabDTOMapper cabConverter=new CabDTOMapper();
		if(carType!=null) {
			logger.info("recieved request to get cab by type " , carType);
 
			List<Cab> cab= cabService.viewCabsOfType(carType);
			List<CabCurrentBookingResponseDTO> finalList=new ArrayList<>();
			for(Cab b:cab) {
				finalList.add(cabConverter.getCurrentCabBookingDTOFromCab(b));
			}
			
			return new ResponseEntity<List<CabCurrentBookingResponseDTO>>(finalList, HttpStatus.OK);
		}
		return null;
	}
	
 
	@GetMapping("/getactivecabs/count/{carType}")
	public ResponseEntity<Integer> getActiveCabCountByCarType(@PathVariable String carType){
		
		if(carType!=null) {
			logger.info("recieved request to get cab count admin " , carType);
 
			Integer finalCount= cabService.countCabsOfType(carType);
			
			
			return new ResponseEntity<Integer>(finalCount, HttpStatus.OK);
		}
		return null;
	}
	@GetMapping("/getactivecabs/countby/{currentLocation}")
	public ResponseEntity<Integer> getActiveCabCountBycurrentLocation(@PathVariable String currentLocation){
		
		if(currentLocation!=null) {
			logger.info("recieved request to get cab count admin " , currentLocation);
 
			Integer finalCount= cabService.countCabsOfcurrentLocation(currentLocation);
			
			
			return new ResponseEntity<Integer>(finalCount, HttpStatus.OK);
		}
		return null;
	}
	@GetMapping("/getactivecabs/by/{currentLocation}")
	public ResponseEntity<List<CabCurrentBookingResponseDTO>> getActiveCabsBycurrentLocation(@PathVariable String currentLocation){
		CabDTOMapper cabConverter=new CabDTOMapper();
		if(currentLocation!=null) {
			logger.info("recieved request to get cab count admin " , currentLocation);
 
			List<Cab> cabs= cabService.getAllCabsBycurrentLocation(currentLocation);
			List<CabCurrentBookingResponseDTO> finalList=new ArrayList<>();
			for(Cab b:cabs) {
				finalList.add(cabConverter.getCurrentCabBookingDTOFromCab(b));
			}
			
			
			return new ResponseEntity<List<CabCurrentBookingResponseDTO>>(finalList, HttpStatus.OK);
		}
		return null;
	}
	//----------------------------------------unassigned cab---------------------------------
	@GetMapping("/getunassignedcabs/count/{currentLocation}")
	public ResponseEntity<Integer> getUnassignedCabCountBycurrentLocation(@PathVariable String currentLocation){
		
		if(currentLocation!=null) {
			logger.info("recieved request to get cab count admin " , currentLocation);
 
			Integer finalCount= cabService.countUnassignedCabsByCurrentLocation(currentLocation);
			
			
			return new ResponseEntity<Integer>(finalCount, HttpStatus.OK);
		}
		return null;
	}
	@GetMapping("/getunassignedcabs/by/{currentLocation}")
	public ResponseEntity<List<CabResponseDTO>> getUnassignedCabByCarType(@PathVariable String currentLocation){
		CabDTOMapper cabConverter=new CabDTOMapper();
		if(currentLocation!=null) {
			logger.info("recieved request to get cab count admin " , currentLocation);
 
			List<Cab> cabs=cabService.getUnassignedCabsBycurrentLocation(currentLocation);
			List<CabResponseDTO> finalListsOfCabs=new ArrayList<>();
			for(Cab b:cabs) {
				finalListsOfCabs.add(cabConverter.getCabDTOFromCab(b));
			}
			
			
			return new ResponseEntity<List<CabResponseDTO>>(finalListsOfCabs, HttpStatus.OK);
		}
		return null;
	}
	
	 /**
     * Get Cab by ID Endpoint.
     *
     * @param cId The ID of the cab to retrieve.
     * @return ResponseEntity with CabResponseDTO and HTTP status OK.
     */
	@GetMapping("/cabby/{cId}")
	public ResponseEntity<CabResponseDTO> getCabById(@PathVariable int cId)  {
		if(cId!=0) {
			CabDTOMapper converter=new CabDTOMapper();
			CabResponseDTO cab=converter.getCabDTOFromCab(cabService.viewCab(cId));
				 
		
		return new ResponseEntity<CabResponseDTO>(cab , HttpStatus.OK);
		}
		return null;
		
	}
		
	 /**
     * Get All Cabs Endpoint.
     *
     * @return ResponseEntity with a list of CabResponseDTO and HTTP status OK.
     */
	
	@GetMapping("/allcabs")
	public ResponseEntity<List<CabResponseDTO>> getAllCabs(){
		CabDTOMapper converter=new CabDTOMapper();
		System.out.println("inside controller getCabs()");
		List<Cab> temp = cabService.viewAllCab();
		List<CabResponseDTO> finalCabs=new ArrayList<>();
		for(Cab b: temp) {
			finalCabs.add(converter.getCabDTOFromCab(b));
		}
		
		return new ResponseEntity<List<CabResponseDTO>>(finalCabs, HttpStatus.OK);
	}
//===============================================getTripCabWise=======================================================================
	/**
     * Retrieves a list of trip bookings organized by customer order.
     *
     * This endpoint fetches trip bookings from the admin service, organizes them by customer order,
     * and returns the results as a list of TripBookingResponseDTOs.
     *
     * @return ResponseEntity with a list of TripBookingResponseDTO representing trips organized by customer order.
     *         Returns HTTP status OK if the operation is successful.
     */
	@GetMapping("/get/trips/bycustomerwise")
	public ResponseEntity<List<TripBookingResponseDTO>> getTripByCustomerOrder(){
		TripBookingDTOMapper tripConverter=new TripBookingDTOMapper();
		 
			List<TripBooking> trips= adminService.getTripsCustomerwise();
			List<TripBookingResponseDTO> finalList=new ArrayList<>();
			for(TripBooking b:trips) {
				finalList.add(tripConverter.getTripBookingDTOFromTripBooking(b));
			}
			
			return new ResponseEntity<List<TripBookingResponseDTO>>(finalList, HttpStatus.OK);
		}
	//==========================================================getTripByCabWise============================================================================
	/**
     * Retrieves a map of trip bookings organized by cab.
     *
     * This endpoint fetches trip bookings from the admin service, organizes them by cab,
     * and returns the results as a Map<Integer, List<TripBooking>> where the Integer represents the cab ID.
     *
     * @return ResponseEntity with a Map<Integer, List<TripBooking>> representing trips organized by cab.
     *         Returns HTTP status OK if the operation is successful.
     */
	@GetMapping("/get/trips/bycabwise")
	public ResponseEntity<Map<Integer, List<TripBooking>>> getTripByCabwise(){
		 
		 
		Map<Integer, List<TripBooking>> trips= adminService.getTripsCabwise();
			 
			return new ResponseEntity<Map<Integer, List<TripBooking>>>(trips, HttpStatus.OK);
		}
//=====================================================gattripsdatewise========================================================================================
	
//	@GetMapping("/get/trips/bydatewise")
//	public ResponseEntity<List<TripBooking>> getTripByDateOrder(){
//		//TripBookingDTOMapper tripConverter=new TripBookingDTOMapper();
//		 
//			List<TripBooking> trips= adminService.getTripsDatewise();
//			//List<TripBookingResponseDTO> finalList=new ArrayList<>();
//			//for(TripBooking b:trips) {
//			//	finalList.add(tripConverter.getTripBookingDTOFromTripBooking(b));
//			//}
//			
//			return new ResponseEntity<List<TripBooking>>(trips, HttpStatus.OK);
//		}
//	
	
	
	  /**
     * Retrieves trips organized by date.
     *
     * This endpoint fetches trip bookings from the admin service, organized by date,
     * and returns the results as a list of TripBooking entities.
     *
     * @return ResponseEntity with a list of TripBooking representing trips organized by date.
     *         Returns HTTP status OK if the operation is successful.
     */
    @GetMapping("/get/trips/bydatewise")
    public ResponseEntity<List<TripBooking>> getTripByDateOrder() {
        // Retrieve trips organized by date from the admin service
        List<TripBooking> trips = adminService.getTripsDatewise();

        // Return the list of TripBooking with HTTP status OK
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

	//========================================gettripbyfromtodate==================================================================================================

    /**
     * Retrieves trips within a specified date range for a customer.
     *
     * This endpoint fetches trip bookings from the admin service within the provided date range for a specific customer,
     * and returns the results as a list of TripBooking entities.
     *
     * @param custId The ID of the customer for whom trips are retrieved.
     * @param fromdat The start date of the date range.
     * @param todate The end date of the date range.
     * @return ResponseEntity with a list of TripBooking representing trips within the specified date range.
     *         Returns HTTP status OK if the operation is successful.
     */
    @GetMapping("/get/trips/bydate/range")
    public ResponseEntity<List<TripBooking>> getTripByDays(
            @RequestParam int custId,
            @RequestParam LocalDate fromdat,
            @RequestParam LocalDate todate) {
        // Retrieve trips within the specified date range for the given customer from the admin service
        List<TripBooking> trips = adminService.getAllTripsForDays(custId, fromdat, todate);

        // Return the list of TripBooking with HTTP status OK
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

	//-----------------------------------getcurrentBooking--------------------------------
    /**
     * Retrieves the current trip booking for a specific user.
     *
     * This endpoint fetches the current trip booking for a user based on the provided user ID.
     *
     * @param userId The ID of the user for whom the current trip booking is retrieved.
     * @return ResponseEntity with a TripBookingResponseByCurrentBookingDTO representing the current trip booking.
     *         Returns HTTP status OK if the operation is successful.
     */
    @GetMapping("/currenttrip/getBy/{userId}")
    public ResponseEntity<TripBookingResponseByCurrentBookingDTO> getTripBookingById(@PathVariable int userId) {
        if (userId != 0) {
            TripBookingDTOMapper converter = new TripBookingDTOMapper();

            // Retrieve the current trip booking for the given user from the admin service
            TripBookingResponseByCurrentBookingDTO dto = converter.getTripBookingDTOFromTripBookingByCurrentBooking(
                    adminService.getTripBookingById(userId));

            // Return the TripBookingResponseByCurrentBookingDTO with HTTP status OK
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return null;
    }

// =====================================Complaint related code ==========================================================================================================================

		    /**
		     * Retrieves complaints by status.
		     *
		     * This endpoint fetches complaints from the complaint service based on the specified status.
		     *
		     * @param status The status of complaints to retrieve.
		     * @return ResponseEntity with the result of complaints based on the provided status.
		     *         Returns HTTP status OK if the operation is successful, INTERNAL_SERVER_ERROR otherwise.
		     */
		    @GetMapping("/complaints/status/{status}")
		    public ResponseEntity<Object> findComplaintsByStatus(@PathVariable("status") String status) {
		        try {
		            return ResponseEntity.status(HttpStatus.OK).body(complaintService.complaintByStatus(status));
		        } catch (Exception exception) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
		        }
		    }
//===================================================================================================================================================================================================================
		    /**
		     * Retrieves all complaints.
		     *
		     * This endpoint fetches all complaints from the complaint service.
		     *
		     * @return ResponseEntity with the result of all complaints.
		     *         Returns HTTP status OK if the operation is successful, INTERNAL_SERVER_ERROR otherwise.
		     */
		    @GetMapping("/complaints/all")
		    public ResponseEntity<Object> getAllCompalints() {
		        try {
		            return ResponseEntity.status(HttpStatus.OK).body(complaintService.getAllComplaints());
		        } catch (Exception exception) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
		        }
		    }
//====================================================================================================================================================================================================================================================================================================   
		    

		    /**
		     * Updates a complaint.
		     *
		     * This endpoint updates a complaint based on the provided Complaint entity.
		     *
		     * @param complaint The Complaint entity containing updated complaint information.
		     * @return ResponseEntity indicating the success of the update operation.
		     *         Returns HTTP status NO_CONTENT if the operation is successful, INTERNAL_SERVER_ERROR otherwise.
		     */
		    @PutMapping("/complaints/update")
		    public ResponseEntity<Object> updateComplaint(@Valid @RequestBody Complaint complaint) {
		        try {
		            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(complaintService.updateComplaint(complaint));
		        } catch (Exception exception) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
		        }
		    }

//===========================================================Delete Review by id================================================================================================================
		

	
	
		    /**
		     * Deletes a review by ID.
		     *
		     * This endpoint deletes a review based on the provided review ID.
		     *
		     * @param reviewId The ID of the review to be deleted.
		     * @return ResponseEntity indicating the success of the delete operation.
		     *         Returns HTTP status NO_CONTENT if the operation is successful.
		     */
		    @DeleteMapping("/delete/review")
		    public ResponseEntity<Object> deleteReview(@RequestParam("id") Integer reviewId) {
		        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(driverService.deleteReview(reviewId));
		    }
		    
		    @GetMapping("/getdriver/ratings/{ratings}")
			public ResponseEntity<List<Driver>> getDriversByRatings(@PathVariable float ratings){
				List<Driver> drivers=driverService.getDriverByRating(ratings);
				if(drivers!=null) {
					return new ResponseEntity<>(drivers, HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
}
	
	


