package com.taskmanager.profiles.api.profile.services;

import com.taskmanager.profiles.api.profile.models.Profile;
import com.taskmanager.profiles.api.profile.repositories.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class FindProfileByUsernameServiceIntegrationTests implements FindProfileByUsernameServiceTests {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private FindProfileByUsernameService findProfileByUsernameService;

    @BeforeEach
    public void clearDatabase() {
        this.profileRepository.deleteAll();
    }

    @Test
    @Override
    public void givenProfileDoesNotExist_WhenExecute_ThenReturnAnonymousProfile() {
        String username = "katzo@taskmanager.com";

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
                .username(username)
                .birthDate(LocalDate.of(2000, 1, 1))
                .phone("(00) 0000-0000")
                .profilePictureId(UUID.randomUUID())
                .build();

        this.profileRepository.saveAndFlush(existingProfile);

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
