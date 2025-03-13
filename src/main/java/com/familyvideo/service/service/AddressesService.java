package com.familyvideo.service.service;

import com.familyvideo.model.service.Addresses;
import com.familyvideo.repository.service.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressesService {

    private final AddressesRepository addressesRepository;

    @Autowired
    public AddressesService(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    public List<Addresses> getAllAddresses() {
        return addressesRepository.findAll();
    }

    public Optional<Addresses> getAddressById(Integer id) {
        return addressesRepository.findById(id);
    }

    public Addresses saveAddress(Addresses address) {
        return addressesRepository.save(address);
    }

    public void deleteAddress(Integer id) {
        addressesRepository.deleteById(id);
    }
}
