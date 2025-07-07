package com.taskmanager.profiles.api.profile.mappers;

import com.taskmanager.profiles.api.profile.dtos.CreateProfileInputDto;
import com.taskmanager.profiles.api.profile.models.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProfileMapper {
    Profile fromCreateUserInputDto(CreateProfileInputDto createProfileInputDto);
}
