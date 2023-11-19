package com.cabapp.pro.util;

import com.cabapp.pro.dto.AdminRequestDTO;
import com.cabapp.pro.dto.AdminResponseDTO;
import com.cabapp.pro.entity.Admin;

public class AdminDTOMapper {
	public Admin getAdminFromAdminDTO(AdminRequestDTO dto)
	{
		Admin e = new Admin();
		
		e.setUsername(dto.getUsername());
		e.setPassword(dto.getPassword());
		e.setAddress(dto.getAddress());
		e.setMobileNumber(dto.getMobileNumber());
		e.setEmail(dto.getEmail());
		e.setAdminId(dto.getAdminId());
	
		return e;
	 
		
	}
	public AdminResponseDTO getAdminDTOFromAdmin(Admin admin) {
		AdminResponseDTO adto = new AdminResponseDTO();
	 
		adto.setUsername(admin.getUsername());
		adto.setPassword(admin.getPassword());
		adto.setAddress(admin.getAddress());
		adto.setMobileNumber(admin.getMobileNumber());
		adto.setEmail(admin.getEmail());
		adto.setAdminId(admin.getAdminId());
		return adto;
	}
 

}
