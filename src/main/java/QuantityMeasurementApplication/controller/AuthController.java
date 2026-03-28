package QuantityMeasurementApplication.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import QuantityMeasurementApplication.entity.User;
import QuantityMeasurementApplication.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    // ✅ REGISTER (JSON API)
    @PostMapping("/register")
    public String register(@Valid @RequestBody User user) {
        service.register(user);
        return "User Registered ✅";
    }

    // ✅ MANUAL LOGIN (API)
    @PostMapping("/login")
    public String manualLogin(@RequestBody User user) {
        service.login(user);
        return "Login Successful ✅";
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

    // ✅ SUCCESS
    @GetMapping("/success")
    public String success() {
        return "Login Successful ✅";
    }
}