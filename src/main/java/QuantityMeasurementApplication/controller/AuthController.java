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
    public String manualLogin(@RequestBody User user) {

        // check user valid hai ya nahi
        service.login(user);  // assume ye validation kar raha hai

        // 🔥 TOKEN GENERATE
        return jwtUtil.generateToken(user.getEmail());
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody User user) {

        service.register(user);  // user DB me save

        // 🔥 TOKEN GENERATE after register
        return jwtUtil.generateToken(user.getEmail());
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
    public String success(org.springframework.security.core.Authentication authentication) {

        Object principal = authentication.getPrincipal();

        if (principal instanceof org.springframework.security.oauth2.core.oidc.user.OidcUser oidcUser) {
            return jwtUtil.generateToken(oidcUser.getEmail());
        }

        if (principal instanceof org.springframework.security.oauth2.core.user.OAuth2User oauthUser) {
            String email = (String) oauthUser.getAttributes().get("email");
            return jwtUtil.generateToken(email);
        }

        return "Login failed ❌";
    }
}