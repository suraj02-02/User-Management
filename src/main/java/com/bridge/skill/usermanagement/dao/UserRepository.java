package com.bridge.skill.usermanagement.dao;

import com.bridge.skill.usermanagement.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
