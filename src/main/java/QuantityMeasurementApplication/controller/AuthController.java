package QuantityMeasurementApplication.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import QuantityMeasurementApplication.entity.User;
import QuantityMeasurementApplication.security.JwtUtil;
import QuantityMeasurementApplication.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public java.util.Map<String, String> manualLogin(@RequestBody User user) {

        service.login(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return java.util.Map.of("token", token);
    }

    @PostMapping("/register")
    public java.util.Map<String, String> register(@Valid @RequestBody User user) {

        service.register(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return java.util.Map.of("token", token);
    }
   



    // ✅ GOOGLE LOGIN REDIRECT
    @GetMapping("/google")
    public void google(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

    // ❌ REMOVE THIS (conflict karega Spring Security se)
    /*
    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
    }
    */

    @GetMapping("/success")
    public void success(
            org.springframework.security.core.Authentication authentication,
            jakarta.servlet.http.HttpServletResponse response
    ) throws java.io.IOException {

        Object principal = authentication.getPrincipal();
        String email = null;

        if (principal instanceof org.springframework.security.oauth2.core.oidc.user.OidcUser oidcUser) {
            email = oidcUser.getEmail();
        } 
        else if (principal instanceof org.springframework.security.oauth2.core.user.OAuth2User oauthUser) {
            email = (String) oauthUser.getAttributes().get("email");
        }

        String token = jwtUtil.generateToken(email);

        // 🔥 IMPORTANT LINE
        response.sendRedirect("http://localhost:4200/auth?token=" + token);
    }
}