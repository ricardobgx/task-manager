package com.taskmanager.profiles.api.profile.services;

import com.taskmanager.profiles.api.profile.dtos.CreateProfileInputDto;
import com.taskmanager.profiles.api.profile.models.Profile;
import com.taskmanager.profiles.api.profile.repositories.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FindProfileByUsernameServiceUnitTests implements FindProfileByUsernameServiceTests {
    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private CreateProfileService createProfileService;

    @InjectMocks
    private FindProfileByUsernameService findProfileByUsernameService;

    @Test
    @Override
    public void givenProfileDoesNotExist_WhenExecute_ThenReturnAnonymousProfile() {
        String username = "katzo@taskmanager.com";

        CreateProfileInputDto CreateProfileInputDto = new CreateProfileInputDto(
                username,
                null,
                null,
                null
        );

        Profile profileCreated = Profile.builder()
                .id(UUID.randomUUID())
                .username(CreateProfileInputDto.username())
                .birthDate(CreateProfileInputDto.birthDate())
                .phone(CreateProfileInputDto.phone())
                .profilePictureId(CreateProfileInputDto.profilePictureId())
                .createdAt(Instant.now())
                .build();

        when(this.profileRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(this.createProfileService.execute(CreateProfileInputDto)).thenReturn(profileCreated);

        Profile profile = this.findProfileByUsernameService.execute(username);

        assertThat(profile).isNotNull();
        assertThat(profile.getId()).isNotNull();
        assertThat(profile.getUsername()).isNotNull();
        assertThat(profile.getBirthDate()).isNull();
        assertThat(profile.getPhone()).isNull();
        assertThat(profile.getProfilePictureId()).isNull();
        assertThat(profile.getCreatedAt()).isNotNull();
    }

    @Test
    @Override
    public void givenProfileExists_WhenExecute_ThenReturnExistingProfile() {
        String username = "katzo@taskmanager.com";

        Profile existingProfile = Profile.builder()
                .id(UUID.randomUUID())
                .username(username)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phone("(00) 0000-0000")
                .profilePictureId(UUID.randomUUID())
                .createdAt(Instant.now().minus(7, ChronoUnit.DAYS))
                .build();

        when(this.profileRepository.findByUsername(username)).thenReturn(Optional.of(existingProfile));

        Profile profile = this.findProfileByUsernameService.execute(username);

        assertThat(profile).isNotNull();
        assertThat(profile.getId()).isNotNull();
        assertThat(profile.getUsername()).isNotNull();
        assertThat(profile.getBirthDate()).isNotNull();
        assertThat(profile.getPhone()).isNotNull();
        assertThat(profile.getProfilePictureId()).isNotNull();
        assertThat(profile.getCreatedAt()).isNotNull();
    }
}
