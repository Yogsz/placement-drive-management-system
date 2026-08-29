package com.example.campus_placement_tracker.service;

import com.example.campus_placement_tracker.entity.CompanyEntity;
import com.example.campus_placement_tracker.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity createCompany(CompanyEntity company) {
        return companyRepository.save(company);
    }

    public List<CompanyEntity> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<CompanyEntity> getCompanyById(int companyId) {
        return companyRepository.findById(companyId);
    }

    public Optional<CompanyEntity> getCompanyByEmail(String companyEmail) {
        return companyRepository.findByCompanyEmail(companyEmail);
    }

    public CompanyEntity updateCompany(
            int companyId,
            CompanyEntity company) {

        Optional<CompanyEntity> existingCompany =
                companyRepository.findById(companyId);

        if (existingCompany.isPresent()) {

            CompanyEntity updatedCompany =
                    existingCompany.get();

            updatedCompany.setCompanyName(
                    company.getCompanyName());

            updatedCompany.setCompanyEmail(
                    company.getCompanyEmail());

            updatedCompany.setCompanyLocation(
                    company.getCompanyLocation());

            updatedCompany.setContactPerson(
                    company.getContactPerson());

            updatedCompany.setPhone(
                    company.getPhone());

            updatedCompany.setWebsite(
                    company.getWebsite());

            return companyRepository.save(updatedCompany);
        }

        return null;
    }

    public boolean deleteCompany(int companyId) {

        if (companyRepository.existsById(companyId)) {

            companyRepository.deleteById(companyId);
            return true;
        }

        return false;
    }
}