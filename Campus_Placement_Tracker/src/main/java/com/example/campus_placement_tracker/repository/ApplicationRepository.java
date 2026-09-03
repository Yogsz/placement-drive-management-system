package com.example.campus_placement_tracker.repository;

import com.example.campus_placement_tracker.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {
    long countByPlacementDrive_DriveId(int driveId);
    boolean existsByStudent_IdAndPlacementDrive_DriveId(
            int studentId,
            int driveId
    );
}
