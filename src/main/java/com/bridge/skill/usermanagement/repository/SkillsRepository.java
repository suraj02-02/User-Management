package com.bridge.skill.usermanagement.repository;

import com.bridge.skill.usermanagement.entities.Skills;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends MongoRepository<Skills, String> {

    Skills findByUserId(String userId);

    void deleteByUserId(String id);
}
