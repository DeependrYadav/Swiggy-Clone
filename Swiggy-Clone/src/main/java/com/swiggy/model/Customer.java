package com.swiggy.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Customer {//inversion side

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	@NotBlank(message = "Name can't be empty")
	@NotNull(message = "Name can't be null")
	private String name;
	
	@NotNull(message = "password can't be null")
	@Size(min = 8,message = "Password size must be greater then or equals to 8")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	
	@Email(message = "Provide email in valid format")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "Address can't be empty")
	private String address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
	private List<Orders> orderList = new ArrayList<>();
	
	@NotBlank(message = "role can't be empty")
	@NotNull(message = "role can't be null")
	private String role;

	public Customer(@NotBlank(message = "Name can't be empty") @NotNull(message = "Name can't be null") String name,
			@NotNull(message = "password can't be null") @Size(min = 8, message = "Password size must be greater then or equals to 8") String password,
			@Email(message = "Provide email in valid format") String email,
			@NotBlank(message = "Address can't be empty") String address, List<Orders> orderList,
			@NotBlank(message = "role can't be empty") @NotNull(message = "role can't be null") String role) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.orderList = orderList;
		this.role = role;
	}

}
