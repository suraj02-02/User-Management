package com.bridge.skill.usermanagement.mapper;


import com.bridge.skill.usermanagement.entities.Experience;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring" )
@Component
public interface ExperienceMapper {

    Experience toUserExperienceInfoResponse(final Experience experience);


}
