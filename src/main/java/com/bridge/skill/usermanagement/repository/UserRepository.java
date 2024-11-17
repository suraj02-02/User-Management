package com.bridge.skill.usermanagement.repository;

import com.bridge.skill.usermanagement.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByName(String username);
}
