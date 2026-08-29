package com.example.campus_placement_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.campus_placement_tracker.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
