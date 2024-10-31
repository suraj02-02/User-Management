package com.bridge.skill.usermanagement.dao;

import com.bridge.skill.usermanagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
