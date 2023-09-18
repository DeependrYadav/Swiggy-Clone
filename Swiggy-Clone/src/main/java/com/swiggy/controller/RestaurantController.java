package com.swiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.model.Restaurant;
import com.swiggy.service.RestaurantService;

import jakarta.validation.Valid;
@RestController
@CrossOrigin("*")
public class RestaurantController {

	@Autowired
	private RestaurantService rs;
	
	@PostMapping(value = "/resturants")
	public ResponseEntity<Restaurant> addResturant(@Valid @RequestBody Restaurant res){
		return new ResponseEntity<Restaurant>(rs.addResturant(res),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/resturants")
	public ResponseEntity<List<Restaurant>> getRestaurant(
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "size",required = false) Integer size,
			@RequestParam(name = "sort",required = false) String sort,
			@RequestParam(name = "order",required = false) String order
			){
		return new ResponseEntity<List<Restaurant>>(rs.getRestaurant(page,size,sort,order),HttpStatus.OK);
	}
	
	@Deprecated
	@GetMapping(value = "/resturants_by_page/{pageNumber}/{recordsperPage}")
	public ResponseEntity<List<Restaurant>> getRestaurantPageWise(@PathVariable Integer pageNumber,@PathVariable Integer recordsperPage){
		return new ResponseEntity<List<Restaurant>>(rs.getRestaurantPageWise(pageNumber,recordsperPage),HttpStatus.OK);
	}
	
	@Deprecated
	@GetMapping(value = "/resturants_by_sorting/{field}/{direction}")
	public ResponseEntity<List<Restaurant>> getRestaurantBySorting(@PathVariable String field,@PathVariable String direction){
		return new ResponseEntity<List<Restaurant>>(rs.getRestaurantBySorting(field,direction),HttpStatus.OK);
	}
}
