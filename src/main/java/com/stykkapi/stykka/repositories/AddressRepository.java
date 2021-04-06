package com.stykkapi.stykka.repositories;

import com.stykkapi.stykka.models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String>{
    Address save(Address newAddress);

    Address findAddressesByUserId(String userId);

    Address findAddressByStreetLine(String streetLine);
}
