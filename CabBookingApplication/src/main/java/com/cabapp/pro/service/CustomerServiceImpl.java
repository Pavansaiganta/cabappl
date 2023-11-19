package com.cabapp.pro.service;

import java.util.ArrayList;
import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabapp.pro.entity.Cab;
import com.cabapp.pro.entity.Customer;
import com.cabapp.pro.entity.TripBooking;
import com.cabapp.pro.repository.ICabRepository;
import com.cabapp.pro.repository.ICustomerRepository;



@Service
public class CustomerServiceImpl implements ICustomerService {
	
	
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	ICabRepository cabRepository;
	
	

	public CustomerServiceImpl() {
	 System.out.println("Inside the service");
	}



	@Override
	public Customer insertCustomer(Customer customer) {
		
		 Customer savedCustomer=customerRepository.save(customer);
	
	  return savedCustomer;
	}



	@Override
	public Customer viewCustomer(int customerId) {
		 Customer savedCustomer=customerRepository.getCustomerByCustomerId(customerId);
		 if(savedCustomer!=null) {
			 return savedCustomer;
		 }
		 else
		 return null;
	}



	@Override
	public List<Customer> viewCustomers() {
	 List<Customer> allCustomerList=customerRepository.findAll();
	 if(allCustomerList != null || allCustomerList.size() != 0)
	 {
		 return allCustomerList;
	 }
				else return null;
	 
	}



	@Override
	public Customer validateCustomer(String userName, String password) {
		 
		List<Customer> c1  =customerRepository.findAll();
		for(int i= 0; i < c1.size(); i++) {
			if(c1.get(i).getUsername().equals(userName) && c1.get(i).getPassword().equals(password))
			     return c1.get(i);
			}
		 return null;
	}



	@Override
	public Customer updateCustomer(Customer customer, int id) {   
		Customer c1=customerRepository.findById(id).get();
		
	       if(c1!=null) {
			
			c1.setAddress(customer.getAddress());
			c1.setEmail(customer.getEmail());
			c1.setMobileNumber(customer.getMobileNumber());
			c1.setPassword(customer.getPassword());
			c1.setUsername(customer.getUsername());
			 
			customerRepository.save(c1);
	       
			
			return c1;
	       }
	       else return null;//doubt on address entity
	       
		 
	}



	@Override
	public Customer deleteCustomer(int customerId) {
		 Customer customer=customerRepository.findById( customerId).get();
		 customerRepository.delete(customer);
		 return customer;//delete but doubt
		 
	}



	@Override
	public List<Cab> getCabByLocations(String currentLocation) {
		 if(currentLocation!=null) {
			 List<Cab> cabs=cabRepository.getCabByCurrentLocation(currentLocation);
			 if(cabs!=null) {
				 return cabs;
			 }
		 }
		return null;
	}



	@Override
	public TripBooking getTripBookingByUserId(int userId) {
	     Customer customer=customerRepository.findById(userId).get();
	     List<TripBooking> trip=new ArrayList<>();
	     trip.addAll(customer.getTripBookings());
	      for(TripBooking t:trip){
	    	  if(t.isStatus()) {
	    		  return t;
	    	  }
	      }
		
		
		return null;
	}



	 

}
