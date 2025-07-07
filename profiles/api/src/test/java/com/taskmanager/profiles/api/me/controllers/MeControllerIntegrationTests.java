package com.taskmanager.profiles.api.me.controllers;

import com.c4_soft.springaddons.security.oauth2.test.annotations.WithJwt;
import com.taskmanager.profiles.api.profile.repositories.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class MeControllerIntegrationTests implements MeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileRepository profileRepository;

    @BeforeEach
    public void clearDatabase() {
        this.profileRepository.deleteAll();
    }

    @WithJwt("jwt-mock.json")
    @Test
    @Override
    public void givenUserIsAuthenticated_WhenGetAuthenticatedUserInfo_ThenReturnStatus201AndUserInfo() {
        try {
            this.mockMvc.perform(get("/me")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isNotEmpty())
                    .andExpect(jsonPath("$.name").hasJsonPath())
                    .andExpect(jsonPath("$.email").hasJsonPath())
                    .andExpect(jsonPath("$.birthDate").hasJsonPath())
                    .andExpect(jsonPath("$.phone").hasJsonPath())
                    .andExpect(jsonPath("$.profilePictureUrl").hasJsonPath())
                    .andExpect(jsonPath("$.roles").hasJsonPath())
                    .andExpect(jsonPath("$.roles").isArray())
                    .andExpect(jsonPath("$.exp").hasJsonPath());
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Test
    @Override
    public void givenUserIsNotAuthenticated_WhenGetAuthenticatedUserInfo_ThenReturnStatus403() {
        try {
            this.mockMvc.perform(get("/me")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized());
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
