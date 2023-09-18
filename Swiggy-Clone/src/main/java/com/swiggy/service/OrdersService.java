package com.swiggy.service;

import java.util.List;

import com.swiggy.model.OrderStatus;
import com.swiggy.model.Orders;

import jakarta.validation.Valid;

public interface OrdersService {
	
	public Orders placeOrder(Integer customerId,Integer restaurantId,@Valid Orders order);
	public Orders assignDeliveryPartner(Integer orderId, Integer deliveryPartnerId);
	public Orders updateOrderStatus(Integer orderId, OrderStatus os);
	public List<Orders> orderHistory(Integer customerId);
	public List<Orders> getOrder(Integer page,Integer size,String sort,String order);
	
	@Deprecated
	public List<Orders> getOrdersPageWise(Integer pageNumber, Integer recordsPerPage);
	@Deprecated
	public List<Orders> getOrdersBySorting(String field, String direction);
	
}
