package com.taskmanager.profiles.api.me.services;

import com.taskmanager.profiles.api.exception.InvalidAuthenticationTypeException;
import com.taskmanager.profiles.api.me.dtos.UserDetailsDto;
import com.taskmanager.profiles.api.profile.models.Profile;
import com.taskmanager.profiles.api.profile.services.FindProfileByUsernameService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetMeService {
    private final FindProfileByUsernameService findProfileByUsernameService;

    public UserDetailsDto execute(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            String username = authentication.getName();

            String name = (String) jwtAuthenticationToken.getTokenAttributes()
                    .getOrDefault(StandardClaimNames.NAME, "");

            String email = (String) jwtAuthenticationToken.getTokenAttributes()
                    .getOrDefault(StandardClaimNames.EMAIL, "");

            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            long exp = Optional.ofNullable(jwtAuthenticationToken.getTokenAttributes()
                    .get(JwtClaimNames.EXP)).map(expClaim -> {
                if (expClaim instanceof Long lexp) {
                    return lexp;
                }

                if (expClaim instanceof Instant iexp) {
                    return iexp.getEpochSecond();
                }

                if (expClaim instanceof Date dexp) {
                    return dexp.toInstant().getEpochSecond();
                }

                return Long.MAX_VALUE;
            }).orElse(Long.MAX_VALUE);

            Profile profile = this.findProfileByUsernameService.execute(username);

            return new UserDetailsDto(
                    name,
                    email,
                    profile.getBirthDate(),
                    profile.getPhone(),
                    "",
                    roles,
                    exp
            );
        }

        throw new InvalidAuthenticationTypeException("You are not authenticated using JWT token");
    }
}
