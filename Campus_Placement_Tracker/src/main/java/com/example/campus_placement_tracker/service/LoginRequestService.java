package com.example.campus_placement_tracker.service;

import com.example.campus_placement_tracker.entity.LoginRequestEntity;
import com.example.campus_placement_tracker.entity.UserEntity;
import com.example.campus_placement_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginRequestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    public String login(LoginRequestEntity loginRequestEntity) {

        UserEntity user =
                userRepository
                        .findByEmail(loginRequestEntity.getEmail())
                        .orElse(null);

        if (user == null) {
            return "Login Failed";
        }


        if (!passwordEncoder.matches(
                loginRequestEntity.getPassword(),
                user.getPassword())) {

            return "Login Failed";
        }


        // Generate JWT token
        return jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );
    }
}