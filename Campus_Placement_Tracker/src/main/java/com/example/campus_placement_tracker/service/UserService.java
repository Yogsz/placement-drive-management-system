package com.example.campus_placement_tracker.service;

import com.example.campus_placement_tracker.entity.CompanyEntity;
import com.example.campus_placement_tracker.entity.Student;
import com.example.campus_placement_tracker.entity.UserEntity;
import com.example.campus_placement_tracker.repository.CompanyRepository;
import com.example.campus_placement_tracker.repository.StudentRepository;
import com.example.campus_placement_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // CREATE / REGISTER
    public UserEntity createUser(UserEntity user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        }

        // STUDENT
        if (user.getRole().equalsIgnoreCase("STUDENT")) {

            Student student =
                    studentRepository.findById(user.getStudentId())
                            .orElse(null);

            if (student == null) {
                return null;
            }

            user.setStudent(student);
        }


        // COMPANY
        if (user.getRole().equalsIgnoreCase("COMPANY")) {

            CompanyEntity company =
                    companyRepository.findById(user.getCompanyId())
                            .orElse(null);

            if (company == null) {
                return null;
            }

            user.setCompany(company);
        }


        // Hash password
        String hashedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }


    // GET ALL USERS
    public List<UserEntity> getAllUsers() {

        List<UserEntity> users =
                userRepository.findAll();

        for (UserEntity user : users) {

            if (user.getStudent() != null) {

                user.setStudentId(
                        user.getStudent().getId()
                );
            }

            if (user.getCompany() != null) {

                user.setCompanyId(
                        user.getCompany().getCompanyId()
                );
            }
        }

        return users;
    }


    // GET USER BY ID
    public UserEntity getUserById(int userId) {

        UserEntity user =
                userRepository.findById(userId)
                        .orElse(null);

        if (user != null) {

            if (user.getStudent() != null) {

                user.setStudentId(
                        user.getStudent().getId()
                );
            }

            if (user.getCompany() != null) {

                user.setCompanyId(
                        user.getCompany().getCompanyId()
                );
            }
        }

        return user;
    }


    // GET USER BY EMAIL
    public UserEntity getUserByEmail(String email) {

        UserEntity user =
                userRepository.findByEmail(email)
                        .orElse(null);

        if (user != null) {

            if (user.getStudent() != null) {

                user.setStudentId(
                        user.getStudent().getId()
                );
            }

            if (user.getCompany() != null) {

                user.setCompanyId(
                        user.getCompany().getCompanyId()
                );
            }
        }

        return user;
    }


    public UserEntity updateUser(int userId, UserEntity user) {

        UserEntity existingUser =
                userRepository.findById(userId)
                        .orElse(null);

        if (existingUser != null) {

            existingUser.setEmail(user.getEmail());

            existingUser.setPassword(
                    passwordEncoder.encode(
                            user.getPassword()
                    )
            );

            existingUser.setRole(user.getRole());


            // Update Student relationship
            if (user.getRole().equalsIgnoreCase("STUDENT")) {

                Student student =
                        studentRepository
                                .findById(user.getStudentId())
                                .orElse(null);

                if (student == null) {
                    return null;
                }

                existingUser.setStudent(student);
                existingUser.setCompany(null);
            }


            // Update Company relationship
            if (user.getRole().equalsIgnoreCase("COMPANY")) {

                CompanyEntity company =
                        companyRepository
                                .findById(user.getCompanyId())
                                .orElse(null);

                if (company == null) {
                    return null;
                }

                existingUser.setCompany(company);
                existingUser.setStudent(null);
            }


            return userRepository.save(existingUser);
        }

        return null;
    }


    // DELETE
    public boolean deleteUser(int userId) {

        if (userRepository.existsById(userId)) {

            userRepository.deleteById(userId);

            return true;
        }

        return false;
    }
}