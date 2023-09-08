package com.swiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiggy.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
