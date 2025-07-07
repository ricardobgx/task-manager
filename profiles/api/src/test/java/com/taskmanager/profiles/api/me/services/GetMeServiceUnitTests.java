package com.taskmanager.profiles.api.me.services;

import com.taskmanager.profiles.api.me.dtos.UserDetailsDto;
import com.taskmanager.profiles.api.profile.models.Profile;
import com.taskmanager.profiles.api.profile.services.FindProfileByUsernameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetMeServiceUnitTests implements GetMeServiceTests {
    @Mock
    private FindProfileByUsernameService findProfileByUsernameService;

    @InjectMocks
    private GetMeService getMeService;

    @Test
    @Override
    public void givenJwtAuthenticationToken_WhenExecute_ThenReturnUserDetails() {
        String email = "katzo@taskmanager.com";

        Map<String, Object> tokenHeaders = new HashMap<>();

        tokenHeaders.put("alg", "HS256");
        tokenHeaders.put("typ", "JWT");

        Map<String, Object> tokenClaims = new HashMap<>();

        tokenClaims.put("sub", email);
        tokenClaims.put("name", "Katzo");
        tokenClaims.put("email", email);
        tokenClaims.put("scope", "openid email");

        Jwt token = new Jwt(
                "test-token",
                Instant.now(),
                Instant.now().plus(7, ChronoUnit.DAYS),
                tokenHeaders,
                tokenClaims
        );

        JwtAuthenticationToken tokenAuthentication = new JwtAuthenticationToken(token, List.of(new SimpleGrantedAuthority("USER")));

        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .username(email)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        when(this.findProfileByUsernameService.execute(email)).thenReturn(profile);

        UserDetailsDto userDetailsDto = this.getMeService.execute(tokenAuthentication);

        assertThat(userDetailsDto).isNotNull();
        assertThat(userDetailsDto.name()).isNotNull();
        assertThat(userDetailsDto.email()).isEqualTo(email);
        assertThat(userDetailsDto.birthDate()).isNull();
        assertThat(userDetailsDto.phone()).isNull();
        assertThat(userDetailsDto.profilePictureUrl()).isNotNull();
        assertThat(userDetailsDto.roles()).isNotNull();
        assertThat(userDetailsDto.exp()).isNotNull();
    }
}
