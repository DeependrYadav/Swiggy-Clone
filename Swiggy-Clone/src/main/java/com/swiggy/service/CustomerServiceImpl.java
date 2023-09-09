package com.swiggy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.swiggy.exception.SwiggyException;
import com.swiggy.model.Customer;
import com.swiggy.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository cr;
	
	@Override
	public Customer addCustomer(Customer cus) {
		if (cus == null)throw new SwiggyException("Customer is null");
		return cr.save(cus);
	}

	@Override
	public List<Customer> getCustomers() {
		return cr.findAll();
	}

	@Override
	public List<Customer> getCustomerByPageWise(Integer pageNumber, Integer recordsPerPage) {
		Pageable page = PageRequest.of(pageNumber, recordsPerPage);
		return cr.findAll(page).getContent();
	}

	@Override
	public List<Customer> getCustomerBySorting(String field, String direction) {
		Sort sort = null;
		if(direction.equalsIgnoreCase("ASC"))sort = Sort.by(Sort.Direction.ASC, field);
		else sort = Sort.by(Sort.Direction.DESC, field);
		return cr.findAll(sort);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return cr.findByEmail(email).orElseThrow( () -> new SwiggyException("No customer found with this email "+email));
	}
}
