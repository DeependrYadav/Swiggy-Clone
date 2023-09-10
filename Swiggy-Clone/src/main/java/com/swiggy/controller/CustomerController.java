package com.swiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.model.Customer;
import com.swiggy.repository.CustomerRepository;
import com.swiggy.service.CustomerService;

import jakarta.validation.Valid;
@RestController
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService cs;
	
	//End point: http://localhost:8088/
	
	@PostMapping(value = "/customers")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer cus){
		
		return new ResponseEntity<Customer>(cs.addCustomer(cus),HttpStatus.OK);
	}
	
	@GetMapping(value = "/customers")
	public ResponseEntity<List<Customer>> getCustomers(){
		return new ResponseEntity<List<Customer>>(cs.getCustomers(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/customers_by_page/{pageNumber}/{recordsPerPage}")
	public ResponseEntity<List<Customer>> getCustomerByPageWise(@PathVariable Integer pageNumber,@PathVariable Integer recordsPerPage){
		return new ResponseEntity<List<Customer>>(cs.getCustomerByPageWise(pageNumber,recordsPerPage),HttpStatus.OK);
	}

	@GetMapping(value = "/customers_by_sort/{field}/{direction}")
	public ResponseEntity<List<Customer>> getCustomerBySorting(@PathVariable String field,@PathVariable String direction){
		return new ResponseEntity<List<Customer>>(cs.getCustomerBySorting(field,direction),HttpStatus.OK);
	}
	
	@GetMapping(value = "/signIn")
	public ResponseEntity<String> getCustomerByEmail(Authentication auth){
		
//		System.out.println(auth);
		
		Customer cus = cs.getCustomerByEmail(auth.getName());
		
		return new ResponseEntity<String>(cus.getName()+" Logged In Successfully ",HttpStatus.ACCEPTED);
	}
}
