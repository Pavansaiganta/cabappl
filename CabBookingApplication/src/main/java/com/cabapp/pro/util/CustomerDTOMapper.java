package com.cabapp.pro.util;

 

import com.cabapp.pro.dto.CustomerRequestSubmitDTO;
import com.cabapp.pro.dto.CustomerResponseDTO;
import com.cabapp.pro.entity.Customer;

public class CustomerDTOMapper {
	
	public Customer getCustomerFromCustomerDTO(CustomerRequestSubmitDTO dto)
	{
		Customer e = new Customer();
		
		e.setUsername(dto.getUsername());
		e.setPassword(dto.getPassword());
		e.setAddress(dto.getAddress());
		e.setMobileNumber(dto.getMobileNumber());
		e.setEmail(dto.getEmail());
		e.setCustomerId(dto.getCustomerId());
	
		return e;
	 
		
	}
	public CustomerResponseDTO getCustomerDTOFromCustomer(Customer customer) {
		CustomerResponseDTO adto = new CustomerResponseDTO();
	 
		 
		adto.setUsername(customer.getUsername());
		adto.setPassword(customer.getPassword());
		adto.setAddress(customer.getAddress());
		adto.setMobileNumber(customer.getMobileNumber());
		adto.setEmail(customer.getEmail());
		adto.setCustomerId(customer.getCustomerId());
		return adto;
	}
 

	
	
	

}
