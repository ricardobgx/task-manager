package com.taskmanager.profiles.api.profile.services;

import com.taskmanager.profiles.api.profile.dtos.CreateProfileInputDto;
import com.taskmanager.profiles.api.profile.models.Profile;
import com.taskmanager.profiles.api.profile.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindProfileByUsernameService {
    private final ProfileRepository profileRepository;
    private final CreateProfileService createProfileService;

    @Transactional
    public Profile execute(String username) {
        return this.profileRepository.findByUsername(username)
                .orElseGet(() -> this.createProfileService.execute(new CreateProfileInputDto(
                        username,
                        null,
                        null,
                        null
                )));
    }
}
