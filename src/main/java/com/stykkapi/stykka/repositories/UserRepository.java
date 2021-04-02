package com.stykkapi.stykka.repositories;


import com.stykkapi.stykka.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
