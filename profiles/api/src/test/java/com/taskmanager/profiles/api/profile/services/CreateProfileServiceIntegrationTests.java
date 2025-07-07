package com.taskmanager.profiles.api.profile.services;

import com.taskmanager.profiles.api.profile.dtos.CreateProfileInputDto;
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
public class CreateProfileServiceIntegrationTests implements CreateProfileServiceTests {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CreateProfileService createProfileService;

    @BeforeEach
    public void clearDatabase() {
        this.profileRepository.deleteAll();
    }

    @Test
    @Override
    public void givenCorrectInputInformation_WhenExecute_ThenReturnProfileCreated() {
        CreateProfileInputDto createProfileInputDto = new CreateProfileInputDto(
                "katzo@taskmanager.com",
                LocalDate.of(2000, 1, 1),
                "(00) 0000-0000",
                UUID.randomUUID()
        );

        Profile profile = this.createProfileService.execute(createProfileInputDto);

        assertThat(profile).isNotNull();
        assertThat(profile.getId()).isNotNull();
        assertThat(profile.getUsername()).isNotNull();
        assertThat(profile.getBirthDate()).isNotNull();
        assertThat(profile.getPhone()).isNotNull();
        assertThat(profile.getProfilePictureId()).isNotNull();
        assertThat(profile.getCreatedAt()).isNotNull();
    }
}
