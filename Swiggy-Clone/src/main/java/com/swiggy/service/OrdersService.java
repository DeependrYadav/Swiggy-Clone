package com.swiggy.service;

import java.util.List;

import com.swiggy.model.OrderStatus;
import com.swiggy.model.Orders;

import jakarta.validation.Valid;

public interface OrdersService {
	
	public Orders placeOrder(Integer customerId,Integer restaurantId,@Valid Orders order);

	public List<Orders> getOrder();
	
}
