package com.swiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiggy.model.DeliveryPartner;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Integer> {

}
