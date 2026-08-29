package com.example.campus_placement_tracker.repository;

import com.example.campus_placement_tracker.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository
        extends JpaRepository<CompanyEntity, Integer> {

    Optional<CompanyEntity> findByCompanyEmail(String companyEmail);
}