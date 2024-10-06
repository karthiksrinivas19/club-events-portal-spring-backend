package com.club_events_portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/api/user/login").permitAll() // Allow login without authentication
                    .requestMatchers("/create_event").authenticated() // Require authentication for creating events
                    .anyRequest().permitAll() // Allow all other requests without authentication
            )
            .csrf().disable() // Disable CSRF for simplicity (not recommended for production)
            .exceptionHandling() // Optional: Handle access denied exceptions
            .accessDeniedPage("/access-denied"); // Redirect to access denied page or endpoint

        return http.build();
    }
}
