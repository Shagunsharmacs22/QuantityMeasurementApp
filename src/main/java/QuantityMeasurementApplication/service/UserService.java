package QuantityMeasurementApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QuantityMeasurementApplication.entity.User;
import QuantityMeasurementApplication.repository.UserRepository;
import QuantityMeasurementApplication.security.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private JwtUtil jwtUtil;

    public User register(User user) {

        // 🔥 Important validation
        if (user.getProvider().equalsIgnoreCase("LOCAL")) {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                throw new RuntimeException("Password required for LOCAL user");
            }
        }

        return repo.save(user);
    }

    public String login(User user) {

        User dbUser = repo.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dbUser.getProvider().equals("LOCAL")) {
            if (!dbUser.getPassword().equals(user.getPassword())) {
                throw new RuntimeException("Invalid password");
            }
        }

        // 🔥 BAS YE LINE IMPORTANT
        return jwtUtil.generateToken(dbUser.getEmail());
    }
}