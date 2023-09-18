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

import com.swiggy.model.DeliveryPartner;
import com.swiggy.service.DeliveryPartnerService;

import jakarta.validation.Valid;
@RestController
@CrossOrigin("*")
public class DeliveryPartnerController {

	@Autowired
	private DeliveryPartnerService dps;

	@PostMapping(value = "/deliveryPartners")
	public ResponseEntity<DeliveryPartner> addResturant(@Valid @RequestBody DeliveryPartner deliveryPartner){
		return new ResponseEntity<DeliveryPartner>(dps.addDeliveryPartner(deliveryPartner),HttpStatus.OK);
	}
	
	@GetMapping(value = "/deliveryPartners")
	public ResponseEntity<List<DeliveryPartner>> getAllDeliveryPartner(
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "size",required = false) Integer size,
			@RequestParam(name = "sort",required = false) String sort,
			@RequestParam(name = "order",required = false) String order
			){
		return new ResponseEntity<List<DeliveryPartner>>(dps.getAllDeliveryPartner(page,size,sort,order),HttpStatus.OK);
	}
	
	@Deprecated
	@GetMapping(value = "/deliveryPartners_by_page/{pageNumber}/{recordsPerPage}")
	public ResponseEntity<List<DeliveryPartner>> getDeliveryPartnerPageWise(@PathVariable Integer pageNumber, @PathVariable Integer recordsPerPage){
		return new ResponseEntity<List<DeliveryPartner>>(dps.getDeliveryPartnerPageWise(pageNumber,recordsPerPage),HttpStatus.OK);
	}
	@Deprecated
	@GetMapping(value = "/deliveryPartners_by_sorting/{field}/{direction}")
	public ResponseEntity<List<DeliveryPartner>> getDeliveryPartnerBySorting(@PathVariable String field, @PathVariable String direction){
		return new ResponseEntity<List<DeliveryPartner>>(dps.getDeliveryPartnerBySorting(field,direction),HttpStatus.OK);
	}
}
