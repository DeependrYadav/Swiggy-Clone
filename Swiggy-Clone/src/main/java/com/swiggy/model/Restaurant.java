package com.swiggy.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer restaurantId;
	
	@NotBlank(message = "Name can't be empty")
	@NotNull(message = "Name can't be null")
	private String name;
	
	@NotBlank(message = "Address can't be empty")
	private String address;
	
	@JsonIgnore 
	@OneToMany(mappedBy = "restaurant")
	private List<Orders> orderList = new ArrayList<>();
	
	public Restaurant(@NotBlank(message = "Name can't be empty") @NotNull(message = "Name can't be null") String name,
			@NotBlank(message = "Address can't be empty") String address) {
		super();
		this.name = name;
		this.address = address;
	}
}
