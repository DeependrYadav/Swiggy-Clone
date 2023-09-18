package com.swiggy.service;

import java.util.List;

import com.swiggy.model.DeliveryPartner;

import jakarta.validation.Valid;

public interface DeliveryPartnerService {
	
	public DeliveryPartner addDeliveryPartner(@Valid DeliveryPartner deliveryPartner);
	public List<DeliveryPartner> getAllDeliveryPartner(Integer page,Integer size,String sort,String order);
	@Deprecated
	public List<DeliveryPartner> getDeliveryPartnerPageWise(Integer pageNumber, Integer recordsPerPage);
	@Deprecated
	public List<DeliveryPartner> getDeliveryPartnerBySorting(String field, String direction);
}
