package com.taskmanager.bff.security.controllers;

import com.c4_soft.springaddons.security.oidc.starter.properties.SpringAddonsOidcProperties;
import com.taskmanager.bff.security.dtos.LoginOptionsDto;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("login-options")
public class LoginOptionsController {
    private final List<LoginOptionsDto> loginOptions;

    public LoginOptionsController(OAuth2ClientProperties clientProperties, SpringAddonsOidcProperties oidcProperties) {
        final var clientAuthority = oidcProperties.getClient()
                .getClientUri()
                .orElseThrow()
                .getAuthority();

        this.loginOptions = clientProperties.getRegistration()
                .entrySet()
                .stream()
                .filter(e -> "authorization_code".equals(e.getValue().getAuthorizationGrantType()))
                .map(e -> {
                    final var label = e.getValue().getProvider();
                    final var loginUri = "%s/oauth2/authorization/%s".formatted(
                            oidcProperties.getClient().getClientUri().orElseThrow(),
                            e.getKey());
                    final var providerId = clientProperties.getRegistration()
                            .get(e.getKey())
                            .getProvider();
                    final var providerIssuerAuthority = URI.create(clientProperties.getProvider()
                            .get(providerId)
                            .getIssuerUri())
                            .getAuthority();

                    return new LoginOptionsDto(label, loginUri, Objects.equals(clientAuthority, providerIssuerAuthority));
                })
                .toList();
    }

    @GetMapping
    public Mono<List<LoginOptionsDto>> getLoginOptions() {
        return Mono.just(this.loginOptions);
    }
}
