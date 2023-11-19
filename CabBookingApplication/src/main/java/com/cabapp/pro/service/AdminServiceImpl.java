package com.cabapp.pro.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cabapp.pro.exception.AdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabapp.pro.entity.Admin;
import com.cabapp.pro.entity.Customer;
import com.cabapp.pro.entity.TripBooking;
import com.cabapp.pro.repository.IAdminRepository;
import com.cabapp.pro.repository.ICabRepository;
import com.cabapp.pro.repository.ICustomerRepository;
import com.cabapp.pro.repository.ITripBookingRepository;

@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	IAdminRepository adminRepo;
	
	@Autowired
	ITripBookingRepository tripRepo;
	
	@Autowired
	ICabRepository cabRepository;
	
	
	@Autowired
	ICustomerRepository customerRepository;
	

	@Override
	public Admin insertAdmin(Admin admin) {
		
		if(admin !=null)
			return adminRepo.save(admin);
		
		 
		return null;
	}


	@Override
	public Admin updateAdmin(Admin admin) {

		if (admin != null) {
			Admin info = adminRepo.findById(admin.getAdminId()).get();
			if (info != null) {
				if (admin.getAddress() != null) {
					info.setAddress(admin.getAddress());
				}
				else {
					throw new AdminException(404,"Admin info not found");
				}
				if (admin.getEmail() != null) {
					info.setEmail(admin.getEmail());
				}
				if (admin.getMobileNumber() != null) {
					info.setMobileNumber(admin.getMobileNumber());
				}
				if (admin.getPassword() != null) {
					info.setPassword(admin.getPassword());
				}
				if (admin.getUsername() != null) {
					info.setUsername(admin.getUsername());
				} 
				if(admin.getAdminId() != 0) {
					info.setAdminId(admin.getAdminId());
				}
				return adminRepo.save(info);
			}

		}
		return null;
	}


	@Override
	public List<TripBooking> getTripsCustomerwise() {
		 
		List<TripBooking> list = tripRepo.findByCustomerIdAscs();
		if(list!=null) {
			return list;
		} else {
				throw new AdminException(404,"trip info not found");
			}
	}


	@Override
	public List<TripBooking> getTripsDatewise() {
		List<TripBooking> list = tripRepo.findByFromDateTimeAsce();
		if(list.size() > 0) {
			return list;
		} else{

				throw new AdminException(404,"trip info not found");
			}

	}


	@Override
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDate fromDate, LocalDate toDate) {
		List<TripBooking> list = tripRepo.findByCustomerIdOnFromDateTimeToToDateTime(customerId, fromDate, toDate);
		if(list.size() > 0)
			return list;
		else {
			throw new AdminException(404,"trip details not found on these days");
		}
	}


	@Override
	public Map<Integer, List<TripBooking>> getTripsCabwise() {
		//List<TripBooking> triplists=tripRepo.findAll();
		//List<TripBooking> finalLists=triplists.stream().
		//return tripRepo.findByCabWiseAscs();
		 
		List<TripBooking> allTrips=tripRepo.findAll();
		Map<Integer, List<TripBooking>> cabWiseTrips=new HashMap<>();
		if(allTrips!=null){
			
			
			for(TripBooking t:allTrips) {
				if( cabWiseTrips.containsKey(t.getDriver().getCab().getCabId())) {
					List<TripBooking> update=cabWiseTrips.get(t.getDriver().getCab().getCabId());
					update.add(t);
					cabWiseTrips.put(t.getDriver().getCab().getCabId(), update);
					
				}
				else {
					List<TripBooking> valueMap=new ArrayList<>();
					valueMap.add(t);
					cabWiseTrips.put(t.getDriver().getCab().getCabId(), valueMap);
				}
			}
			return cabWiseTrips;
		}
		else {
			throw new AdminException(404,"trip details not found on these cabs");
		}
	}

		@Override
		public TripBooking getTripBookingById(int userId) {
			 if(userId!=0) {
				 Customer customer=customerRepository.findById(userId).get();
				 List<TripBooking> trips=new ArrayList<>();
				 trips.addAll(customer.getTripBookings());
				 for(TripBooking t:trips) {
					 if(t.isStatus()) {
						 return t;
					 }
				 }
			 }
			return null;
		}
	}
	


