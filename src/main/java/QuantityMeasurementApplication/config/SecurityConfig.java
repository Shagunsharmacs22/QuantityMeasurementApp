package QuantityMeasurementApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import QuantityMeasurementApplication.security.OAuthSuccessHandler;

@Configuration
public class SecurityConfig {

    @Autowired
    private OAuthSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/", 
                        "/login**", 
                        "/oauth2/**", 
                        "/login/oauth2/**",
                        "/success"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth -> oauth
                .successHandler(successHandler)
            );

        return http.build();
    }
}