package QuantityMeasurementApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/success")
    public String success() {
        return "LOGIN SUCCESS 🔥";
    }
}