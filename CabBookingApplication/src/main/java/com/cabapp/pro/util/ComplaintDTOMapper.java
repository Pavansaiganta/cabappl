package com.cabapp.pro.util;


import com.cabapp.pro.dto.ComplaintRequestDTO;
import com.cabapp.pro.dto.ComplaintResponseDTO;


import com.cabapp.pro.entity.Complaint;


public class ComplaintDTOMapper {
	
	
	
	public Complaint getComplaintFromComplaintDTO(ComplaintRequestDTO dto)
	{
		Complaint c = new Complaint();
		

	    c.setComplaintId(dto.getComplaintId());
	    c.setDateOnRegister(dto.getDateOnRegister());
	    c.setComment(dto.getComment());
	    c.setStatus(dto.getStatus());
	    c.setEmail(dto.getEmail());
	    c.setCustomerId(dto.getCustomerId());
	    c.setDriverId(dto.getDriverId());
	    
	    return c;
		
		
	 
		
	}
	public ComplaintResponseDTO getComplaintDTOFromComplaint(Complaint complaint) {
		ComplaintResponseDTO cr = new ComplaintResponseDTO();
	 
		
		
		
		cr.setComplaintId(complaint.getComplaintId());
	    cr.setDateOnRegister(complaint.getDateOnRegister());
	    cr.setComment(complaint.getComment());
	    cr.setStatus(complaint.getStatus());
	    cr.setEmail(complaint.getEmail());
	    cr.setCustomerId(complaint.getCustomerId());
	    cr.setDriverId(complaint.getDriverId());
	    
	    return cr;
		
	
	}

}
