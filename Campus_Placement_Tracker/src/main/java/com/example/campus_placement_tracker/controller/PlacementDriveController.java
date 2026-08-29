package com.example.campus_placement_tracker.controller;

import com.example.campus_placement_tracker.entity.PlacementDriveEntity;
import com.example.campus_placement_tracker.service.PlacementDriveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class PlacementDriveController {

    @Autowired
    private PlacementDriveService placementDriveService;

    @PostMapping
    public PlacementDriveEntity createDrive(
            @Valid @RequestBody PlacementDriveEntity drive) {

        return placementDriveService.createDrive(drive);
    }

    @GetMapping
    public List<PlacementDriveEntity> getAllDrives() {

        return placementDriveService.getAllDrives();
    }

    @GetMapping("/{driveId}")
    public PlacementDriveEntity getDriveById(
            @PathVariable int driveId) {

        return placementDriveService.getDriveById(driveId);
    }


    @PutMapping("/{driveId}")
    public PlacementDriveEntity updateDrive(
            @PathVariable int driveId,
            @Valid @RequestBody PlacementDriveEntity drive) {

        return placementDriveService.updateDrive(
                driveId,
                drive
        );
    }


    @DeleteMapping("/{driveId}")
    public String deleteDrive(
            @PathVariable int driveId) {

        boolean deleted =
                placementDriveService.deleteDrive(driveId);

        if (deleted) {
            return "Placement drive deleted successfully";
        }

        return "Placement drive not found";
    }
}