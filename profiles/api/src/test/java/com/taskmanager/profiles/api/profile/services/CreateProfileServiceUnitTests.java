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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateProfileServiceUnitTests implements CreateProfileServiceTests {
    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private CreateProfileService createProfileService;

    @Test
    @Override
    public void givenCorrectInputInformation_WhenExecute_ThenReturnProfileCreated() {
        CreateProfileInputDto createProfileInputDto = new CreateProfileInputDto(
                "katzo@taskmanager.com",
                LocalDate.of(2000, 1, 1),
                "(00) 0000-0000",
                UUID.randomUUID()
        );

        Profile profileToCreate = Profile.builder()
                .username(createProfileInputDto.username())
                .birthDate(createProfileInputDto.birthDate())
                .phone(createProfileInputDto.phone())
                .profilePictureId(createProfileInputDto.profilePictureId())
                .build();

        Profile profileCreated = profileToCreate.toBuilder()
                .id(UUID.randomUUID())
                .createdAt(Instant.now())
                .build();

        when(this.profileRepository.saveAndFlush(profileToCreate)).thenReturn(profileCreated);

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
