package com.cabapp.pro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.cabapp.pro.dto.*;
import com.cabapp.pro.entity.*;
import com.cabapp.pro.service.*;
import com.cabapp.pro.util.*;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@Validated
@Api
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService service;

    @Autowired
    private ITripBookingService tripService;

    @Autowired
    private IDriverService driverService;

    @Autowired
    private IComplaintService complaintService;

    public CustomerController() {
        logger.info("Inside Customer controller");
    }

    @GetMapping("/get")
    public String getStatement() {
        logger.debug("Returning a statement...");
        return "all the points to be noted.....";
    }
    /**
     * Saves a new customer.
     *
     * This endpoint receives a CustomerRequestSubmitDTO, converts it to a Customer entity,
     * and saves the customer using the service. Returns the saved customer information as a CustomerResponseDTO.
     *
     * @param dto The CustomerRequestSubmitDTO containing customer information.
     * @return CustomerResponseDTO representing the saved customer. Returns null if the provided DTO is null.
     */

    @PostMapping("/register/customer")
    public ResponseEntity<CustomerResponseDTO> saveCustomer(@Valid @RequestBody CustomerRequestSubmitDTO dto) {
        logger.debug("Inside saveCustomer method");
        if (dto != null) {
            CustomerDTOMapper dtoConverter = new CustomerDTOMapper();
            Customer customer = dtoConverter.getCustomerFromCustomerDTO(dto);
            Customer savedInfo = service.insertCustomer(customer);
            CustomerResponseDTO savedDto= dtoConverter.getCustomerDTOFromCustomer(savedInfo);
            return new ResponseEntity<CustomerResponseDTO>(savedDto, HttpStatus.CREATED);
        } else {
            logger.warn("Received null DTO for saving customer.");
            return null;
        }
    }


    /**
     * Retrieves a customer by ID.
     *
     * This endpoint fetches a customer by the provided customer ID and returns the result as a CustomerResponseDTO.
     *
     * @param cId The ID of the customer to retrieve.
     * @return CustomerResponseDTO representing the customer information.
     */
    
    @GetMapping("/customerby/{cId}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable int cId) {
        logger.debug("Inside getCustomerById method");
        CustomerDTOMapper dtoConverter = new CustomerDTOMapper();
        CustomerResponseDTO customerDto= dtoConverter.getCustomerDTOFromCustomer(service.viewCustomer(cId));
        return new ResponseEntity<CustomerResponseDTO>(customerDto, HttpStatus.OK);
    }

    
    /**
     * Retrieves all customers.
     *
     * This endpoint fetches all customers and returns the result as a list of CustomerResponseDTOs.
     *
     * @return ResponseEntity with a list of CustomerResponseDTO representing all customers.
     */
    @GetMapping("/allcustomers")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        logger.debug("Inside getAllCustomers method");
        CustomerDTOMapper dtoConverter = new CustomerDTOMapper();
        List<Customer> savedCustomers = service.viewCustomers();
        List<CustomerResponseDTO> finalList = new ArrayList<>();
        for (Customer cu : savedCustomers) {
            finalList.add(dtoConverter.getCustomerDTOFromCustomer(cu));
        }
        return new ResponseEntity<>(finalList, HttpStatus.OK);
    }

    
    /**
     * Validates a customer.
     *
     * This endpoint validates a customer by the provided username and password,
     * and returns the customer information if the validation is successful.
     *
     * @param userName The username of the customer.
     * @param password The password of the customer.
     * @return ResponseEntity with the validated customer information or HTTP status NOT_FOUND if validation fails.
     */
    @GetMapping("/validate")
    public ResponseEntity<Customer> validateCustomer(@RequestParam String userName, @RequestParam String password) {
        logger.debug("Inside validateCustomer method");
        Customer savedCustomer = service.validateCustomer(userName, password);
    
        if (savedCustomer != null) {
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    
    /**
     * Updates a customer by ID.
     *
     * This endpoint receives a Customer entity and updates the customer with the provided ID.
     * Returns the updated customer information as a CustomerResponseDTO.
     *
     * @param customer The Customer entity containing updated customer information.
     * @param id The ID of the customer to update.
     * @return CustomerResponseDTO representing the updated customer information.
     */
    @PutMapping("/update/{id}")
    public CustomerResponseDTO updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
        logger.debug("Inside updateCustomer method");
        CustomerDTOMapper dtoConverter = new CustomerDTOMapper();
        return dtoConverter.getCustomerDTOFromCustomer(service.updateCustomer(customer, id));
    }

    

    /**
     * Deletes a customer by ID.
     *
     * This endpoint deletes a customer by the provided customer ID and returns the deleted customer information as a CustomerResponseDTO.
     *
     * @param id The ID of the customer to delete.
     * @return CustomerResponseDTO representing the deleted customer information.
     */
    @DeleteMapping("/delete/{id}")
    public CustomerResponseDTO deleteCustomerById(@PathVariable int id) {
        logger.debug("Inside deleteCustomerById method");
        CustomerDTOMapper dtoConverter = new CustomerDTOMapper();
        CustomerResponseDTO deletedCustomer = dtoConverter.getCustomerDTOFromCustomer(service.deleteCustomer(id));
        return deletedCustomer;
    }

//============================================================tripbooking API's=================================================================================================================================================
    @GetMapping("/searchcab/{currentLocation}")
    public ResponseEntity<List<CabCurrentBookingResponseDTO>> searchCabBasedOnLocations(@PathVariable String currentLocation) {
        logger.debug("Inside searchCabBasedOnLocations method with current location: {}", currentLocation);
        List<Cab> allCabs = service.getCabByLocations(currentLocation);
        CabDTOMapper converter = new CabDTOMapper();
        List<CabCurrentBookingResponseDTO> cabs = new ArrayList<>();
        for (Cab b : allCabs) {
            cabs.add(converter.getCurrentCabBookingDTOFromCab(b));
        }
        return new ResponseEntity<List<CabCurrentBookingResponseDTO>>(cabs, HttpStatus.OK);
    }
    @PostMapping("/trip")
    public ResponseEntity<TripBookingResponseDTO> addTrip(@Valid @RequestBody TripBookingRequestSubmitDTO trip) {
        logger.debug("Inside addTrip method");
        TripBookingDTOMapper converter = new TripBookingDTOMapper();
        TripBooking tripBooking = converter.getTripBookingFromTripBookingDTO(trip);
        TripBookingResponseDTO finalbooking= converter.getTripBookingDTOFromTripBooking(tripService.insertTripBooking(tripBooking));
        return new ResponseEntity<TripBookingResponseDTO>(finalbooking,HttpStatus.OK);
    }
    @GetMapping("/trip/getBy/{userId}")
    public TripBookingResponseDTO getCurrentTripByUserId(@PathVariable int userId) {
        logger.debug("Inside getCurrentTripByUserId method for user ID: {}", userId);
        if (userId != 0) {
            TripBookingDTOMapper converter = new TripBookingDTOMapper();
            return converter.getTripBookingDTOFromTripBooking(service.getTripBookingByUserId(userId));
        }
        return null;
    }

    @GetMapping("/getalltrips")
    public ResponseEntity<List<TripBookingEndTripCustomerResponseDTO>> getAllTripBooking(@RequestParam int custId) {
        logger.debug("Inside getAllTripBooking method for customer ID: {}", custId);
        TripBookingDTOMapper converter=new TripBookingDTOMapper();
        List<TripBooking> savedCustomer = tripService.viewAllTripsCustomer(custId);
        List<TripBookingEndTripCustomerResponseDTO> finalList=new ArrayList<>();
        for(TripBooking t:savedCustomer) {
        	finalList.add(converter.getTripBookingEndTripCustomerDTOFromTripBooking(t));
        }
        if (finalList != null) {
            return new ResponseEntity<>(finalList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/booking")
    public TripBooking updateBooking(@RequestBody TripBooking tripBooking) {
        logger.debug("Inside updateBooking method");
        if (tripBooking != null) {
            return tripService.updateTripBooking(tripBooking);
        }
        return null;
    }

    @DeleteMapping("/{tripId}")
    public TripBooking cancelBooking(@PathVariable int tripId) {
        logger.debug("Inside cancelBooking method for trip ID: {}", tripId);
        if (tripId != 0) {
            return tripService.deleteTripBooking(tripId);
        }
        return null;
    }
	
	//========================================================complaint related code==========================================================================================================================
	
	@PostMapping("/complaint/save")
	    public ResponseEntity<Object> saveComplaint(@RequestBody Complaint complaint) {
		   logger.debug("Inside save complaint method");
		
	        try {
	            return ResponseEntity.status(HttpStatus.CREATED).body(complaintService.createComplaint(complaint));
	        } catch (Exception exception) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
	        }
	 
	    }
	 
	 
	    @GetMapping("/complaintByCustomer/{id}")
	    public ResponseEntity<Object> findComplaintsByUser(@PathVariable("id") int userId) {
	    	 logger.debug("Inside find complaint by user method");
				System.out.println("in complaint customer");

	        try {
	            return ResponseEntity.status(HttpStatus.OK).body(complaintService.complaintByCustomer(userId));
	        } catch(Exception exception) {
	            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
	 
	        }
	    }
	    
	   
	    
	    
	
	//-----------------------------------------------------------------------------------------------------
	
	// Add reviews to driver
	@PostMapping("/add/review")
	public String addReview(@RequestParam("driverId") int driverId,  @RequestBody Review review) {
		return driverService.addReview(driverId, review);
	}

		
	}
	
	
	
	

