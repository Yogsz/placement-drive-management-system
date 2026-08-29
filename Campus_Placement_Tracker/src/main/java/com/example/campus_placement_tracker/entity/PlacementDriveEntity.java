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

    @NotBlank
    private String status;

    @Transient
    private int companyId;


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getDriveId() {
        return driveId;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public float getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(float salaryPackage) {
        this.salaryPackage = salaryPackage;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public LocalDate getDriveDate() {
        return driveDate;
    }

    public void setDriveDate(LocalDate driveDate) {
        this.driveDate = driveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}