package com.cabapp.pro.entity;

 
import java.util.Objects;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@MappedSuperclass
@ApiModel(description = "Details about Abstract user")
@Data
 public class AbstractUser {
	
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private int userId;
	
	private String username;
	private String password;
	private String address;
	private String mobileNumber;
	private String email;

	
}