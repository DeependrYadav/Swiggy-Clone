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
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class DeliveryPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deliveryPartnerId;
	
	@NotBlank(message = "Name can't be empty")
	@NotNull(message = "Name can't be null")
	private String name;
	
	@Pattern(regexp = "^[6-9][0-9]{9}",message = "Provide valid mobile number")
	private String phoneNumber;
	
	@JsonIgnore
	@OneToMany(mappedBy = "deliveryPartner")
	private List<Orders> orderList = new ArrayList<>();
	
	public DeliveryPartner(
			@NotBlank(message = "Name can't be empty") @NotNull(message = "Name can't be null") String name,
			@Pattern(regexp = "^[6-9][0-9]{9}", message = "Provide valid mobile number") String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
}
