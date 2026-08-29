package com.example.campus_placement_tracker.repository;

import com.example.campus_placement_tracker.entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository
        extends JpaRepository<InterviewEntity, Integer> {
}