package com.swiggy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.swiggy.exception.SwiggyException;
import com.swiggy.model.Customer;
import com.swiggy.model.DeliveryPartner;
import com.swiggy.model.OrderStatus;
import com.swiggy.model.Orders;
import com.swiggy.model.Restaurant;
import com.swiggy.repository.CustomerRepository;
import com.swiggy.repository.DeliveryPartnerRepository;
import com.swiggy.repository.OrdersRepository;
import com.swiggy.repository.RestaurantRepository;

import jakarta.validation.Valid;
@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersRepository or;

	@Autowired
	private CustomerRepository cr;

	@Autowired
	private RestaurantRepository rr;

	@Autowired
	private DeliveryPartnerRepository dpr;
	
	@Override
	public Orders placeOrder(Integer customerId,Integer restaurantId,@Valid Orders order) {
//		System.out.println("Inside");
		if (order == null)throw new SwiggyException("Order is null");

		Customer cus = cr.findById(customerId).orElseThrow(()->new SwiggyException("Customer ID is invalid"));
		Restaurant res = rr.findById(restaurantId).orElseThrow(()->new SwiggyException("Restaurant ID is invalid"));
//		System.out.println("Inside1");
				
		if(order.getItems().isEmpty())throw new SwiggyException("Order must have at least one item");
		
		order.setCustomer(cus); order.setRestaurant(res);
//		System.out.println("Inside2");
		
		cus.getOrderList().add(order); res.getOrderList().add(order);
//		System.out.println("Inside3");
		
		return or.save(order);
	}

	@Override
	public List<Orders> getOrder() {
		return or.findAll();
	}

	@Override
	public Orders assignDeliveryPartner(Integer orderId, Integer deliveryPartnerId) {
		
		Orders ord = or.findById(orderId).orElseThrow(()->new SwiggyException("Order ID is invalid"));
		
		if (ord.getOrderStatus() == OrderStatus.delivered) throw new SwiggyException("Order Status is completed");
		
		if (ord.getDeliveryPartner()!= null) throw new SwiggyException("Delivery Partner is already assigned");

		DeliveryPartner dp = dpr.findById(deliveryPartnerId).orElseThrow(()->new SwiggyException("Delivery Partner ID is invalid"));

		ord.setDeliveryPartner(dp);

		return or.save(ord);
	}
	
	@Override
	public Orders updateOrderStatus(Integer orderId, OrderStatus os) {
		
		Orders ord = or.findById(orderId).orElseThrow(()->new SwiggyException("Order ID is invalid"));
		
		if(ord.getOrderStatus().compareTo(os) <= 0)throw new SwiggyException("Invalid order status transitions");

		ord.setOrderStatus(os);
		
		return or.save(ord);
	}
	
}
