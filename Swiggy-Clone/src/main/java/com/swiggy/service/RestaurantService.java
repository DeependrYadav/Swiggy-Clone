package com.swiggy.service;

import java.util.List;

import com.swiggy.model.Restaurant;

import jakarta.validation.Valid;

public interface RestaurantService {
	
	public Restaurant addResturant(@Valid Restaurant res);

	public List<Restaurant> getRestaurant(Integer page,Integer size,String sort,String order);

	public List<Restaurant> getRestaurantPageWise(Integer pageNumber, Integer recordsperPage);

	public List<Restaurant> getRestaurantBySorting(String field, String direction);
}
