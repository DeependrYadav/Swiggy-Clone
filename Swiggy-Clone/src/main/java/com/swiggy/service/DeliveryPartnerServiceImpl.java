package com.swiggy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.swiggy.exception.SwiggyException;
import com.swiggy.model.DeliveryPartner;
import com.swiggy.repository.DeliveryPartnerRepository;

import jakarta.validation.Valid;
@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService{

	@Autowired
	private DeliveryPartnerRepository dpr;
	
	@Override
	public DeliveryPartner addDeliveryPartner(@Valid DeliveryPartner deliveryPartner) {
		if (deliveryPartner == null)
			throw new SwiggyException("Customer is null");
		dpr.save(deliveryPartner);
		return deliveryPartner;
	}

	@Override
	public List<DeliveryPartner> getAllDeliveryPartner() {
		return dpr.findAll();
	}

	@Override
	public List<DeliveryPartner> getDeliveryPartnerPageWise(Integer pageNumber, Integer recordsPerPage) {
		Pageable page = PageRequest.of(pageNumber, recordsPerPage);
		return dpr.findAll(page).getContent();
	}

	@Override
	public List<DeliveryPartner> getDeliveryPartnerBySorting(String field, String direction) {
		Sort sort = direction.equalsIgnoreCase("ASC")?Sort.by(direction, field):Sort.by(direction, field);
		return dpr.findAll(sort);
	}
}
