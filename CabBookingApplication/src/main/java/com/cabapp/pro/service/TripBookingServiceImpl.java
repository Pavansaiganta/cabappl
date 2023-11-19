package com.cabapp.pro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabapp.pro.entity.Customer;
import com.cabapp.pro.entity.Driver;
import com.cabapp.pro.entity.TripBooking;
import com.cabapp.pro.exception.DriverException;
import com.cabapp.pro.repository.ICustomerRepository;
import com.cabapp.pro.repository.IDriverRepository;
import com.cabapp.pro.repository.ITripBookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TripBookingServiceImpl implements ITripBookingService {
	 private static final Logger LOGGER = LoggerFactory.getLogger(TripBookingServiceImpl.class);
	
	@Autowired
	ITripBookingRepository tripBookingRepository;
	
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	IDriverRepository driverRepository;

	@Override
	public List<TripBooking> viewAllTripsCustomer(int customerId) {
		
		List<TripBooking> savedBookings=new ArrayList<>();
		List<TripBooking> finalList=new ArrayList<>();
		if(customerId!=0) {
			savedBookings=tripBookingRepository.findAll();
			finalList=savedBookings.stream().filter(e->e.getCustomerId()==customerId).collect(Collectors.toList());
			
			if(finalList!=null)
			
			return finalList;
		}///working but the jason need to be modified...
		
		
		return null;
	}

	@Override
	public TripBooking insertTripBooking( TripBooking tripBooking) {
		boolean anyBookingStatusIstrue=true;
		Customer customer = customerRepository.findById(tripBooking.getCustomerId()).get();// throw exception
																									// on driver not
																									// customer not
																									// available
																									// exception
		String fromLocation = tripBooking.getFromLocation();
		List<Driver> drivers = driverRepository.findByAvailableAndLocation(fromLocation);
		// if(drivers.size()==0)
		// {
		// ("Sorry No driver Available just now...");for exception
		// }
//		drivers.forEach((e)->System.out.println(e.getUserId()));
//		System.out.println(drivers.size());
		List<TripBooking> statusBooking= new ArrayList<>();
		statusBooking=tripBookingRepository.findTripBookingByCustomerIdAndStatus(customer.getCustomerId());
		 if(statusBooking.isEmpty()) {
			 anyBookingStatusIstrue=false; 
		 }

		// if(statusBooking.isStatus()!=true) {
		//List<TripBooking> custBookings = new ArrayList<>();
		//custBookings.addAll(customer.getTripBookings());
		//System.out.println("inside the custbooking"+custBookings.get(1).getBill());
		//if(custBookings!=null) {
		//for (TripBooking t : custBookings) {
			//if (t.isStatus()) {
				//anyBookingStatusIstrue = true;
				//break;
			//}
		//}
		//}
		if (anyBookingStatusIstrue != true) {
			if (drivers != null) {

				drivers.get(0).setAvailable(false);
				driverRepository.save(drivers.get(0));
				Integer km = (int) tripBooking.getDistanceInKm();
				Integer price = (int) drivers.get(0).getCab().getPerKmRate();// data loss due to datatype conversion.
				tripBooking.setBill(km * price);
				tripBooking.setDriver(drivers.get(0));
				tripBooking.setStatus(true);

				TripBooking tripBooked = tripBookingRepository.save(tripBooking);
				drivers.get(0).getTripbookings().add(tripBooked);
				driverRepository.save(drivers.get(0));
				if (customer.getCustomerId() == tripBooking.getCustomerId()) {
					customer.getTripBookings().add(tripBooked);
					customerRepository.save(customer);
					  LOGGER.info("Inserting new trip booking...");
					  tripBooked.setCustomer(customer);
				
					return tripBookingRepository.save(tripBooked);
				}
			 else {
			throw new DriverException(404,"Driver not found for this trip");
			}
		} else {
			throw new DriverException(409," user cannot book when already on another one");

			// exception for user cannot book when already on another one
			// }
			// return null;

		}
		}
		throw new DriverException(409," Customer already booked");
		 
	}

	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking) {//on doubt to use this method
	   
		
		if (tripBooking != null) {

			int id = (int) tripBooking.getCustomerId();

			TripBooking savedBooking = tripBookingRepository.findById(id).get();

			if (savedBooking != null) {
				if (savedBooking.getFromLocation() != null) {

					savedBooking.setFromLocation(tripBooking.getFromLocation());
				}
				if (savedBooking.getToLocation() != null) {
					savedBooking.setToLocation(tripBooking.getToLocation());
				}
				if (savedBooking.getFromDateTime() != null) {
					savedBooking.setFromDateTime(tripBooking.getFromDateTime());
				}
				if (savedBooking.getToDateTime() != null) {
					savedBooking.setDistanceInKm(tripBooking.getDistanceInKm());
				}

				tripBookingRepository.save(savedBooking);
				return savedBooking;
			}
		}

		return null;
	}

	@Override
	public TripBooking deleteTripBooking(int tripBookingId) {
		
		TripBooking tripBooking =tripBookingRepository.findById(tripBookingId).get();
		
		
		if(tripBooking != null) {
		tripBooking.setDriver(null);
		tripBookingRepository.delete(tripBooking);
		}
		
		
 	return null;
	}
//------------------------------------admin's api----------------------------
	@Override
	public List<TripBooking> getAllTrips(int customerId) {
		
		if(customerId!=0) {
			Optional<Customer> opt = customerRepository.findById(customerId);
			if(opt.isPresent()) {
				List<TripBooking> trips = tripBookingRepository.findAll();
				return trips;
			
			}
 
		return null;
	}
	return null;
	}
	
	
	
	

}
