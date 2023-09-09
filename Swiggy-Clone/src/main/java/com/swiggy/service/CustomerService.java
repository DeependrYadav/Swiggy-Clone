package com.swiggy.service;

import java.util.List;

import com.swiggy.model.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer cus);
	
	public List<Customer> getCustomerByPageWise(Integer pageNumber, Integer recordsPerPage);
	
	public List<Customer> getCustomers();

	public List<Customer> getCustomerBySorting(String field, String direction);
	
	public Customer getCustomerByEmail(String email);
}
