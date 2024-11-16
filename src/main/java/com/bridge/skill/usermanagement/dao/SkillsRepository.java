package com.bridge.skill.usermanagement.dao;

import com.bridge.skill.usermanagement.dto.Skills;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillsRepository extends MongoRepository<Skills, String> {

}
