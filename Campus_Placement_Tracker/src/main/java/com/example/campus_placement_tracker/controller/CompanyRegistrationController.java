package com.example.campus_placement_tracker.controller;

import com.example.campus_placement_tracker.entity.CompanyRegistrationEntity;
import com.example.campus_placement_tracker.entity.UserEntity;
import com.example.campus_placement_tracker.service.CompanyRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class CompanyRegistrationController {

    @Autowired
    private CompanyRegistrationService companyRegistrationService;

    @PostMapping("/register/company")
    public UserEntity registerCompany(
            @Valid @RequestBody CompanyRegistrationEntity registration) {

        return companyRegistrationService.registerCompany(registration);
    }
}