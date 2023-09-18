package com.swiggy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.swiggy.exception.SwiggyException;
import com.swiggy.model.Restaurant;
import com.swiggy.repository.RestaurantRepository;

import jakarta.validation.Valid;
@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantRepository rr;
	
	@Override
	public Restaurant addResturant(@Valid Restaurant res) {
		if (res == null)
			throw new SwiggyException("Customer is null");
		rr.save(res);
		return res;
	}

	@Override
	public List<Restaurant> getRestaurant(Integer page,Integer size,String sort,String order) {
		Sort sort1 = null;
		Pageable pagination = null;
		
		if(page == null && sort == null)return rr.findAll();
		
		else if(page == null && sort != null) {
			if(order.equalsIgnoreCase("asc"))sort1 = Sort.by(Sort.Direction.ASC,sort);
			else sort1 = Sort.by(Sort.Direction.DESC,sort);
			return rr.findAll(sort1);
		}
		else if(sort == null && page != null) {
			 pagination = PageRequest.of(page, size);
			 return rr.findAll(pagination).getContent();
		}
		else {
			if(order.equalsIgnoreCase("asc"))sort1 = Sort.by(Sort.Direction.ASC,sort);
			else sort1 = Sort.by(Sort.Direction.DESC,sort);
			pagination = PageRequest.of(page, size,sort1);
			return rr.findAll(pagination).getContent();
		}
	}

	@Override
	@Deprecated
	public List<Restaurant> getRestaurantPageWise(Integer pageNumber, Integer recordsperPage) {
		Pageable pageable = PageRequest.of(pageNumber, recordsperPage);
		return rr.findAll(pageable).getContent();
	}

	@Deprecated
	@Override
	public List<Restaurant> getRestaurantBySorting(String field, String direction) {
		Sort sort = direction.equalsIgnoreCase("ASC")?Sort.by(Sort.Direction.ASC, field):Sort.by(Sort.Direction.DESC, field);
		return rr.findAll(sort);
	}
}
