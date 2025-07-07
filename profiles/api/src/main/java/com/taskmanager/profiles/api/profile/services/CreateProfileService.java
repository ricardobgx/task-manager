package com.taskmanager.profiles.api.profile.services;

import com.taskmanager.profiles.api.profile.dtos.CreateProfileInputDto;
import com.taskmanager.profiles.api.profile.mappers.ProfileMapper;
import com.taskmanager.profiles.api.profile.models.Profile;
import com.taskmanager.profiles.api.profile.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateProfileService {
    private final ProfileMapper profileMapper = Mappers.getMapper(ProfileMapper.class);

    private final ProfileRepository profileRepository;

    @Transactional
    public Profile execute(CreateProfileInputDto createProfileInputDto) {
        Profile profile = this.profileMapper.fromCreateUserInputDto(createProfileInputDto);

        return this.profileRepository.saveAndFlush(profile);
    }
}
