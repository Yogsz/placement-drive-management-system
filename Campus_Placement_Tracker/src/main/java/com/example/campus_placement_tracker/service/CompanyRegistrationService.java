package com.example.campus_placement_tracker.service;

import com.example.campus_placement_tracker.entity.CompanyEntity;
import com.example.campus_placement_tracker.entity.CompanyRegistrationEntity;
import com.example.campus_placement_tracker.entity.UserEntity;
import com.example.campus_placement_tracker.repository.CompanyRepository;
import com.example.campus_placement_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyRegistrationService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserEntity registerCompany(
            CompanyRegistrationEntity registration) {

        // Check if email already exists
        if (userRepository
                .findByEmail(registration.getEmail())
                .isPresent()) {

            return null;
        }


        // Create Company
        CompanyEntity company = new CompanyEntity();

        company.setCompanyName(
                registration.getCompanyName());

        company.setCompanyEmail(
                registration.getEmail());

        company.setCompanyLocation(
                registration.getCompanyLocation());

        company.setContactPerson(
                registration.getContactPerson());

        company.setPhone(
                registration.getPhoneNo());

        company.setWebsite(
                registration.getWebsite());


        companyRepository.save(company);


        // Create User
        UserEntity user = new UserEntity();

        user.setEmail(
                registration.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        registration.getPassword()));

        user.setRole("COMPANY");

        // Connect User with Company
        user.setCompany(company);


        return userRepository.save(user);
    }
}