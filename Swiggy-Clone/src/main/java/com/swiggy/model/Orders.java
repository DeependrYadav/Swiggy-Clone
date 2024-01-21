package com.swiggy.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Orders {// owning side
//	Order: a unique orderId, customerId, restaurantId, deliveryPartnerId (nullable), items (a list of menu items), and orderStatus.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Restaurant restaurant;
		
	@ManyToOne(cascade = CascadeType.ALL)
	private DeliveryPartner deliveryPartner;
	
	@ElementCollection
	private List<String> items;
	
	@Enumerated
	private OrderStatus orderStatus;

	public Orders(@NotNull(message = "Provide order Id") Customer customer,
			@NotNull(message = "Provide resturant Id") Restaurant restaurant, DeliveryPartner deliveryPartner,
			List<String> items, OrderStatus orderStatus) {
		super();
		this.customer = customer;
		this.restaurant = restaurant;
		this.deliveryPartner = deliveryPartner;
		this.items = items;
		this.orderStatus = orderStatus;
	}
}
