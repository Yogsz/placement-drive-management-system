package com.example.campus_placement_tracker.controller;

import com.example.campus_placement_tracker.entity.ApplicationEntity;
import com.example.campus_placement_tracker.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ApplicationEntity createApplication(
            @Valid @RequestBody ApplicationEntity application) {

        return applicationService.createApplication(application);
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
}