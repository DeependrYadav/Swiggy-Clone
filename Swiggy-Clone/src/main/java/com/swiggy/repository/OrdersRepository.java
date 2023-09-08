package com.swiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiggy.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

}
