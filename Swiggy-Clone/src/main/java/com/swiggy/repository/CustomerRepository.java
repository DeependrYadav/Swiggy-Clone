package com.swiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiggy.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


}
