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
	public List<DeliveryPartner> getAllDeliveryPartner(Integer page,Integer size,String sort,String order) {
		Sort sort1 = null;
		Pageable pagination = null;
		
		if(page == null && sort == null)return dpr.findAll();
		
		else if(page == null && sort != null) {
			if(order.equalsIgnoreCase("asc"))sort1 = Sort.by(Sort.Direction.ASC,sort);
			else sort1 = Sort.by(Sort.Direction.DESC,sort);
			return dpr.findAll(sort1);
		}
		else if(sort == null && page != null) {
			 pagination = PageRequest.of(page, size);
			 return dpr.findAll(pagination).getContent();
		}
		else {
			if(order.equalsIgnoreCase("asc"))sort1 = Sort.by(Sort.Direction.ASC,sort);
			else sort1 = Sort.by(Sort.Direction.DESC,sort);
			pagination = PageRequest.of(page, size,sort1);
			return dpr.findAll(pagination).getContent();
		}
	}

	@Override
	@Deprecated
	public List<DeliveryPartner> getDeliveryPartnerPageWise(Integer pageNumber, Integer recordsPerPage) {
		Pageable page = PageRequest.of(pageNumber, recordsPerPage);
		return dpr.findAll(page).getContent();
	}

	@Override
	@Deprecated
	public List<DeliveryPartner> getDeliveryPartnerBySorting(String field, String direction) {
		Sort sort = direction.equalsIgnoreCase("ASC")?Sort.by(direction, field):Sort.by(direction, field);
		return dpr.findAll(sort);
	}
}
