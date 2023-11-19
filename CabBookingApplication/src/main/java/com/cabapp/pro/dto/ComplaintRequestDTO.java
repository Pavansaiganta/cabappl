package com.cabapp.pro.dto;

import com.cabapp.pro.entity.Customer;
import com.cabapp.pro.entity.Driver;
import lombok.Data;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.OneToOne;

@Data
public class ComplaintRequestDTO {
	
	private Integer complaintId;
    private Instant dateOnRegister;
    private String comment;
    private String status;
    private String email;
    private int customerId;
    private int driverId;
   
}
