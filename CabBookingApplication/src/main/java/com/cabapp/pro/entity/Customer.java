package com.cabapp.pro.entity;

 
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Entity
@DiscriminatorValue("customer")
@Data
public class Customer extends AbstractUser{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	
//	@OneToMany(cascade=CascadeType.ALL,mappedBy = "customerId")
//	private List<Complaint> complaint;
	
@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name="custId")
private List<TripBooking> tripBookings;//doubt on implementation part whether it should be here or in tripbooking entity




public Customer() {
	// TODO Auto-generated constructor stub
}

public Customer(int customerId, List<TripBooking> tripBookings) {
	super();
	this.customerId = customerId;
	this.tripBookings = tripBookings;
}




public int getCustomerId() {
	return customerId;
}

public void setCustomerId(int customerId) {
	this.customerId = customerId;
}

public List<TripBooking> getTripBookings() {
	return tripBookings;
}

public void setTripBookings(List<TripBooking> tripBookings) {
	this.tripBookings = tripBookings;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(customerId, tripBookings);
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	Customer other = (Customer) obj;
	return customerId == other.customerId && Objects.equals(tripBookings, other.tripBookings);
}

@Override
public String toString() {
	return "Customer [customerId=" + customerId + ", tripBookings=" + tripBookings + "]";
}



 




 


	
	

}
