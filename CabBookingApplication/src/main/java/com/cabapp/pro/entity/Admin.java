package com.cabapp.pro.entity;

 
import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
 
 

@Entity
@DiscriminatorValue("admin")
@Data
public class Admin extends AbstractUser {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;


	

}
