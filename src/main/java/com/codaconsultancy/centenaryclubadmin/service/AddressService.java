package com.codaconsultancy.centenaryclubadmin.service;

import com.codaconsultancy.centenaryclubadmin.domain.Address;
import com.codaconsultancy.centenaryclubadmin.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends LifelineService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
}
