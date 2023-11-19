package com.cabapp.pro.util;
 
 
import com.cabapp.pro.dto.DriverBasicResponseDTO;
import com.cabapp.pro.dto.DriverRequestSubmitDTO;
import com.cabapp.pro.dto.DriverResponseDTO;
 
import com.cabapp.pro.entity.Driver;

public class DriverDTOMapper {
	public Driver getDriverFromDriverDTO(DriverRequestSubmitDTO dto)
	{
		Driver e = new Driver();
		
		e.setUsername(dto.getUsername());
		e.setPassword(dto.getPassword());
		e.setAddress(dto.getAddress());
		e.setDriverId(dto.getDriverId());
		e.setLicenseNo(dto.getLicenseNo());
		e.setMobileNumber(dto.getMobileNumber());
		e.setEmail(dto.getEmail());
		e.setCurrentLocation(dto.getCurrentLocation());
	 
	
		return e;
	 
		
	}
	public DriverResponseDTO getDriverDTOFromDriver(Driver driver) {
		DriverResponseDTO adto = new DriverResponseDTO();
	 
		 
		adto.setUsername(driver.getUsername());
		adto.setMobileNumber(driver.getMobileNumber());
		adto.setEmail(driver.getEmail());
		adto.setPassword(driver.getPassword());
		adto.setAddress(driver.getAddress());
		adto.setDriverId(driver.getDriverId());
		adto.setLicenseNo(driver.getLicenseNo());
		adto.setStatus(driver.getStatus());
		adto.setCabRegistrationNumber(driver.getCab().getCabRegistrationNumber());
		adto.setCarType(driver.getCab().getCarType());
		return adto;
	}
 
	public DriverBasicResponseDTO getDriverBasicDTOFromDriver(Driver driver) {
		DriverBasicResponseDTO adto = new DriverBasicResponseDTO();
	 
		 
		adto.setUsername(driver.getUsername());
		adto.setMobileNumber(driver.getMobileNumber());
		adto.setEmail(driver.getEmail());
		adto.setPassword(driver.getPassword());
		adto.setAddress(driver.getAddress());
		adto.setDriverId(driver.getDriverId());
		adto.setLicenseNo(driver.getLicenseNo());
		adto.setStatus(driver.getStatus());
		 
		return adto;
	}
}
