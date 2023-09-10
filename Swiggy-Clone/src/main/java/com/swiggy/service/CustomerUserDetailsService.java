package com.swiggy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swiggy.model.Customer;
import com.swiggy.repository.CustomerRepository;
@Service
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Customer> opt = customerRepository.findByEmail(username);
		
		if(opt.isPresent()) {
			Customer cus = opt.get();

			System.out.println(cus.getEmail()+" "+cus.getRole());
			
			List<GrantedAuthority> authorities= new ArrayList<>();
			
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(cus.getRole());
			authorities.add(sga);
			
			return new User(cus.getEmail(),cus.getPassword(),authorities);
		}else
			throw new BadCredentialsException("User Details not found with this username: "+username);
		
	}
	
//	public void newMethod(User user) {
//		System.out.println(user.getUsername());
//		System.out.println(user.getAuthorities());
//	}

}
