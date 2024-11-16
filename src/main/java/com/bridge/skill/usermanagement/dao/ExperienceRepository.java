package com.bridge.skill.usermanagement.dao;

import com.bridge.skill.usermanagement.dto.Experience;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExperienceRepository extends MongoRepository<Experience, String> {

}
