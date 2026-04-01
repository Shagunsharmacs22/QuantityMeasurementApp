package QuantityMeasurementApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import QuantityMeasurementApplication.security.JwtFilter;
import QuantityMeasurementApplication.security.OAuthSuccessHandler;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private OAuthSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {}) // ✅ correct syntax
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/oauth2/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth -> oauth
                .successHandler(successHandler) 
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");   // ✅ YEH ADD KARO
        config.addAllowedOrigin("http://127.0.0.1:4200");
        config.addAllowedOrigin("http://127.0.0.1:5500");
        config.addAllowedOrigin("http://localhost:5500");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}