package com.example.campus_placement_tracker.controller;


import com.example.campus_placement_tracker.entity.LoginRequestEntity;
import com.example.campus_placement_tracker.service.LoginRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginRequestController {
    @Autowired
    private LoginRequestService loginRequestService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequestEntity loginRequest){
        return loginRequestService.login(loginRequest);
    }
}
