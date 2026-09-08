package com.example.campus_placement_tracker.controller;

import com.example.campus_placement_tracker.entity.ApplicationEntity;
import com.example.campus_placement_tracker.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<?> createApplication(
            @Valid @RequestBody ApplicationEntity application) {

        ApplicationEntity result =
                applicationService.createApplication(application);

        if (result == null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("You have already applied for this company.");
        }

        return ResponseEntity.ok(result);
    }
    @GetMapping
    public List<ApplicationEntity> getAllApplications() {

        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationEntity getApplicationById(
            @PathVariable int id) {

        return applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}")
    public ApplicationEntity updateApplication(
            @PathVariable int id,
            @Valid @RequestBody ApplicationEntity application) {

        return applicationService.updateApplication(
                id,
                application
        );
    }


    @DeleteMapping("/{id}")
    public String deleteApplication(
            @PathVariable int id) {

        boolean deleted =
                applicationService.deleteApplication(id);

        if (deleted) {
            return "Application deleted successfully";
        }

        return "Application not found";
    }

    @GetMapping("/drive/{driveId}/count")
    public long getApplicationCount(
            @PathVariable int driveId) {

        return applicationService
                .getApplicationCount(driveId);
    }
}