package com.bridge.skill.usermanagement.repository;

import com.bridge.skill.usermanagement.entities.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends MongoRepository<Experience, String> {

    Experience findByUserId(String userId);
    void deleteByUserId(String id);
}
