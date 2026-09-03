package com.example.campus_placement_tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "placement_drives")
public class PlacementDriveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driveId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private CompanyEntity company;

    @NotBlank
    private String jobRole;

    @Min(0)
    private float salaryPackage;

    @NotBlank
    private String eligibility;

    @NotNull
    private LocalDate driveDate;

    @NotNull
    private LocalDate applyBefore;

    @NotBlank
    private String status;

    @Transient
    private int companyId;

    @Transient
    private String companyName;


    // Company ID

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


    // Company Name

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    // Drive ID

    public int getDriveId() {
        return driveId;
    }


    // Company

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }


    // Job Role

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }


    // Salary Package

    public float getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(float salaryPackage) {
        this.salaryPackage = salaryPackage;
    }


    // Eligibility

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }


    // Drive Date

    public LocalDate getDriveDate() {
        return driveDate;
    }

    public void setDriveDate(LocalDate driveDate) {
        this.driveDate = driveDate;
    }


    // Apply Before

    public LocalDate getApplyBefore() {
        return applyBefore;
    }

    public void setApplyBefore(LocalDate applyBefore) {
        this.applyBefore = applyBefore;
    }


    // Status

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}