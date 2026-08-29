package com.example.campus_placement_tracker.service;

import com.example.campus_placement_tracker.entity.CompanyEntity;
import com.example.campus_placement_tracker.entity.PlacementDriveEntity;
import com.example.campus_placement_tracker.repository.CompanyRepository;
import com.example.campus_placement_tracker.repository.PlacementDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementDriveService {

    @Autowired
    private PlacementDriveRepository placementDriveRepository;

    @Autowired
    private CompanyRepository companyRepository;


    // CREATE
    public PlacementDriveEntity createDrive(
            PlacementDriveEntity drive) {

        CompanyEntity company =
                companyRepository.findById(drive.getCompanyId())
                        .orElse(null);

        if (company == null) {
            return null;
        }

        drive.setCompany(company);

        return placementDriveRepository.save(drive);
    }


    // GET ALL
    public List<PlacementDriveEntity> getAllDrives() {

        List<PlacementDriveEntity> drives =
                placementDriveRepository.findAll();

        for (PlacementDriveEntity drive : drives) {

            if (drive.getCompany() != null) {

                drive.setCompanyId(
                        drive.getCompany().getCompanyId()
                );
            }
        }

        return drives;
    }


    // GET BY ID
    public PlacementDriveEntity getDriveById(int driveId) {

        PlacementDriveEntity drive =
                placementDriveRepository
                        .findById(driveId)
                        .orElse(null);

        if (drive != null && drive.getCompany() != null) {

            drive.setCompanyId(drive.getCompany().getCompanyId());
        }

        return drive;
    }


    // UPDATE
    public PlacementDriveEntity updateDrive(
            int driveId,
            PlacementDriveEntity drive) {

        PlacementDriveEntity existingDrive =
                placementDriveRepository
                        .findById(driveId)
                        .orElse(null);

        if (existingDrive != null) {

            CompanyEntity company =
                    companyRepository
                            .findById(drive.getCompanyId())
                            .orElse(null);

            if (company == null) {
                return null;
            }

            existingDrive.setCompany(company);

            existingDrive.setJobRole(
                    drive.getJobRole());

            existingDrive.setSalaryPackage(
                    drive.getSalaryPackage());

            existingDrive.setEligibility(
                    drive.getEligibility());

            existingDrive.setDriveDate(
                    drive.getDriveDate());

            existingDrive.setStatus(
                    drive.getStatus());

            return placementDriveRepository.save(existingDrive);
        }

        return null;
    }

    public boolean deleteDrive(int driveId) {

        if (placementDriveRepository.existsById(driveId)) {

            placementDriveRepository.deleteById(driveId);

            return true;
        }

        return false;
    }
}