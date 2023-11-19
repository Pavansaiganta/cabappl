package com.cabapp.pro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cabapp.pro.entity.Admin;
import com.cabapp.pro.entity.TripBooking;

@Service
public interface IAdminService {
	
	
	public Admin insertAdmin(Admin admin);
	public Admin updateAdmin(Admin admin);
	public List<TripBooking> getTripsCustomerwise();
	public List<TripBooking> getTripsDatewise();
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDate fromDate, LocalDate toDate);
    public Map<Integer, List<TripBooking>> getTripsCabwise();
    public TripBooking getTripBookingById(int userId);
}
