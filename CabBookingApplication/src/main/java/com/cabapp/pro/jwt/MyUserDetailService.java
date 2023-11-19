package com.cabapp.pro.jwt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cabapp.pro.entity.Admin;
import com.cabapp.pro.entity.Customer;
import com.cabapp.pro.entity.Driver;
import com.cabapp.pro.repository.IAdminRepository;
import com.cabapp.pro.repository.ICustomerRepository;
import com.cabapp.pro.repository.IDriverRepository;
import com.sun.tools.sjavac.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class MyUserDetailService implements UserDetailsService {

	
	@Autowired
	private IAdminRepository adminRepository;
	@Autowired
	private ICustomerRepository customerRepository;
	@Autowired
	private IDriverRepository driverRepository;
	 private String name; 
	    private String password; 
	    private List<GrantedAuthority> authorities; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = customerRepository.findByUsername(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					getCustomerAuthority(user));
		}
		Admin admin = adminRepository.findByUsername(username);
		if(admin !=null) {
			return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),
					getAdminAuthority(admin));
		}
		Driver driver = driverRepository.findByUsername(username);
		if(driver !=null) {
			return new org.springframework.security.core.userdetails.User(driver.getUsername(), driver.getPassword(),
					getDriverAuthority(driver));
		}
		throw new UsernameNotFoundException("User not found with username: " + username);

	}
	
	  private Set<SimpleGrantedAuthority> getCustomerAuthority(Customer user) {
	        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	            authorities.add(new SimpleGrantedAuthority("ROLE_" + "customer"));
	        return authorities;
	    }

	private Set<SimpleGrantedAuthority> getAdminAuthority(Admin user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + "admin"));
		return authorities;
	}

	private Set<SimpleGrantedAuthority> getDriverAuthority(Driver user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + "driver"));
		return authorities;
	}
}
