package com.familyvideo.repository.service;

import com.familyvideo.model.service.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressesRepository extends JpaRepository<Addresses, Integer> {
}
