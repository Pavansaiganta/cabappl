package com.cabapp.pro.util;

import com.cabapp.pro.dto.CabCurrentBookingResponseDTO;
import com.cabapp.pro.dto.CabRequestDTO;
import com.cabapp.pro.dto.CabResponseDTO;
import com.cabapp.pro.entity.Cab;

public class CabDTOMapper {
	public Cab getCabFromCabDTO(CabRequestDTO dto)
	{
		Cab e = new Cab();
		e.setCabId(dto.getCabId());
		e.setCarType(dto.getCarType());
		e.setPerKmRate(dto.getPerKmRate());
		e.setCurrentLocation(dto.getCurrentLocation());
		e.setCabRegistrationNumber(dto.getCabRegistrationNumber());
	 
		 
		return e;
	 
		
	}
	public CabResponseDTO getCabDTOFromCab(Cab cab) {
		CabResponseDTO adto = new CabResponseDTO();
	 
		adto.setCabId(cab.getCabId()); 
		adto.setCarType(cab.getCarType());
		adto.setPerKmRate(cab.getPerKmRate());
		adto.setStatus(cab.getStatus());
		adto.setCurrentLocation(cab.getCurrentLocation());
		adto.setCabRegistrationNumber(cab.getCabRegistrationNumber());
		 
		return adto;
	}
	public CabCurrentBookingResponseDTO getCurrentCabBookingDTOFromCab(Cab cab) {
		CabCurrentBookingResponseDTO adto=new CabCurrentBookingResponseDTO();
		adto.setCabId(cab.getCabId());
		adto.setCarType(cab.getCarType());
		adto.setPerKmRate(cab.getPerKmRate());
		adto.setStatus(cab.getStatus());
		adto.setCurrentLocation(cab.getCurrentLocation());
		adto.setCabRegistrationNumber(cab.getCabRegistrationNumber());
		adto.setDriverName(cab.getDriver().getUsername());
		adto.setDriverLicenseNo(cab.getDriver().getLicenseNo());
		adto.setDriverStatus(cab.getDriver().getStatus());
		
		return adto;
	}

}
