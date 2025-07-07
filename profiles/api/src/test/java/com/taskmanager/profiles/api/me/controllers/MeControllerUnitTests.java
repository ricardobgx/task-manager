package com.taskmanager.profiles.api.me.controllers;

import com.c4_soft.springaddons.security.oauth2.test.annotations.WithJwt;
import com.taskmanager.profiles.api.me.dtos.UserDetailsDto;
import com.taskmanager.profiles.api.me.services.GetMeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MeController.class)
@ActiveProfiles("test")
public class MeControllerUnitTests implements MeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetMeService getMeService;

    @WithJwt("jwt-mock.json")
    @Test
    @Override
    public void givenUserIsAuthenticated_WhenGetAuthenticatedUserInfo_ThenReturnStatus201AndUserInfo() {
        try {
            UserDetailsDto userDetailsDto = new UserDetailsDto(
                    "Katzo",
                    "katzo@taskmanager.com",
                    LocalDate.of(2000, 1, 1),
                    "(00) 0000-0000",
                    "",
                    List.of("user"),
                    Instant.now().plus(7, ChronoUnit.DAYS).getEpochSecond()
            );

            when(this.getMeService.execute(any())).thenReturn(userDetailsDto);

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
