package com.example.campus_placement_tracker.service;

import com.example.campus_placement_tracker.entity.Student;
import com.example.campus_placement_tracker.entity.StudentRegistrationEntity;
import com.example.campus_placement_tracker.entity.UserEntity;
import com.example.campus_placement_tracker.repository.StudentRepository;
import com.example.campus_placement_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentRegistrationService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserEntity registerStudent(StudentRegistrationEntity registration) {

        // Check if email already exists
        if (userRepository
                .findByEmail(registration.getEmail())
                .isPresent()) {

            return null;
        }


        // Create Student
        Student student = new Student();

        student.setName(registration.getName());
        student.setEmail(registration.getEmail());
        student.setPhoneNo(registration.getPhoneNo());
        student.setDepartment(registration.getDepartment());
        student.setAcademicYear(registration.getAcademicYear());

        studentRepository.save(student);


        // Create User
        UserEntity user = new UserEntity();

        user.setEmail(registration.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        registration.getPassword()
                )
        );

        user.setRole("STUDENT");

        // Connect User with Student
        user.setStudent(student);


        return userRepository.save(user);
    }
}