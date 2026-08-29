package com.example.campus_placement_tracker.controller;

import com.example.campus_placement_tracker.entity.StudentRegistrationEntity;
import com.example.campus_placement_tracker.entity.UserEntity;
import com.example.campus_placement_tracker.service.StudentRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class StudentRegistrationController {

    @Autowired
    private StudentRegistrationService studentRegistrationService;

    @PostMapping("/register/student")
    public UserEntity registerStudent(
            @Valid @RequestBody StudentRegistrationEntity registration) {

        return studentRegistrationService.registerStudent(registration);
    }
}