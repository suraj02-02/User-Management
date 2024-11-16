package com.bridge.skill.usermanagement.repository;

import com.bridge.skill.usermanagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
