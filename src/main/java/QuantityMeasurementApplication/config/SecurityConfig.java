package QuantityMeasurementApplication.config;

import org.springframework.context.annotation.*;
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
                        "/auth/**",
                        "/login",
                        "/oauth2/**",
                        "/login/oauth2/**"
                ).permitAll()
                .anyRequest().authenticated()
            )

            // ✅ Manual login (default Spring page)
            .formLogin(form -> form
                .defaultSuccessUrl("/auth/success", true)
            )

            // ✅ Google login
            .oauth2Login(oauth -> oauth
                .defaultSuccessUrl("/auth/success", true)
            );

        return http.build();
    }
}