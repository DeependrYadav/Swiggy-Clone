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
	public List<Customer> getCustomerByPageWise(Integer pageNumber, Integer recordsPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomers() {
		return cr.findAll();
	}

	@Override
	public List<Customer> getCustomerBySorting(String field, String direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}


}
