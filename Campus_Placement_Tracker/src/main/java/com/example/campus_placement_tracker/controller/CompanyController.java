package com.example.campus_placement_tracker.controller;

import com.example.campus_placement_tracker.entity.CompanyEntity;
import com.example.campus_placement_tracker.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @PostMapping
    public ResponseEntity<CompanyEntity> createCompany(
            @Valid @RequestBody CompanyEntity company) {

        return ResponseEntity.ok(
                companyService.createCompany(company)
        );
    }

    @GetMapping
    public ResponseEntity<List<CompanyEntity>> getAllCompanies() {

        return ResponseEntity.ok(
                companyService.getAllCompanies()
        );
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyEntity> getCompanyById(
            @PathVariable int companyId) {

        return companyService.getCompanyById(companyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/email/{companyEmail}")
    public ResponseEntity<CompanyEntity> getCompanyByEmail(
            @PathVariable String companyEmail) {

        return companyService.getCompanyByEmail(companyEmail)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyEntity> updateCompany(
            @PathVariable int companyId,
            @Valid @RequestBody CompanyEntity company) {

        CompanyEntity updatedCompany =
                companyService.updateCompany(
                        companyId,
                        company
                );

        if (updatedCompany != null) {
            return ResponseEntity.ok(updatedCompany);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(
            @PathVariable int companyId) {

        if (companyService.deleteCompany(companyId)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}