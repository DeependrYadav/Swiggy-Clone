package com.swiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.model.OrderStatus;
import com.swiggy.model.Orders;
import com.swiggy.service.OrdersService;

import jakarta.validation.Valid;
@RestController
@CrossOrigin("*")
public class OrdersController {

	@Autowired
	private OrdersService os;
	
	@PostMapping(value = "/orders/{cusId}/{resId}")
	public ResponseEntity<Orders> placeOrder(@PathVariable Integer cusId,@PathVariable Integer resId,@Valid @RequestBody Orders order){
		return new ResponseEntity<Orders>(os.placeOrder(cusId,resId,order),HttpStatus.OK);
	}

	@GetMapping(value = "/orders")
	public ResponseEntity<List<Orders>> getOrders(
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "size",required = false) Integer size,
			@RequestParam(name = "sort",required = false) String sort,
			@RequestParam(name = "order",required = false) String order
			){
		return new ResponseEntity<List<Orders>>(os.getOrder(page,size,sort,order),HttpStatus.OK);
	}
	
	@Deprecated
	@GetMapping(value = "/orders_by_page/{pageNumber}/{recordsPerPage}")
	public ResponseEntity<List<Orders>> getOrdersPageWise(@PathVariable Integer pageNumber, @PathVariable Integer recordsPerPage){
		return new ResponseEntity<List<Orders>>(os.getOrdersPageWise(pageNumber,recordsPerPage),HttpStatus.OK);
	}
	
	@Deprecated
	@GetMapping(value = "/orders_by_sort/{field}/{direction}")
	public ResponseEntity<List<Orders>> getOrdersBySort(@PathVariable String field, @PathVariable String direction){
		return new ResponseEntity<List<Orders>>(os.getOrdersBySorting(field,direction),HttpStatus.OK);
	}
	
	@PutMapping(value ="/orders/{orderId}/{deliveryPartnerId}")
	public ResponseEntity<Orders> assignDeliveryPartner(@PathVariable Integer orderId,@PathVariable Integer deliveryPartnerId){
		return new ResponseEntity<Orders>(os.assignDeliveryPartner(orderId,deliveryPartnerId),HttpStatus.OK);
	}

	@PatchMapping(value ="/orders/{orderId}/{os}")
	public ResponseEntity<Orders> updateOrderStatus(@PathVariable Integer orderId,@PathVariable("os") OrderStatus orderStatus){
		return new ResponseEntity<Orders>(os.updateOrderStatus(orderId,orderStatus),HttpStatus.OK);
	}

	@GetMapping(value = "customers/{customerId}")
	public ResponseEntity<List<Orders>> orderHistory(@PathVariable Integer customerId){
		return new ResponseEntity<List<Orders>>(os.orderHistory(customerId),HttpStatus.OK);
	}
	
}
