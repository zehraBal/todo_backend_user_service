
package com.bal.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/users/save",
                                "/api/users/username/**",  // password-hash endpointi için
                                "/api/users/**"            // diğer user endpointleri için
                        ).permitAll()
                        .anyRequest().authenticated()  // Diğer endpointler için auth gerekli
                );
        return http.build();
    }
}