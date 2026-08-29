package com.example.campus_placement_tracker.controller;

import com.example.campus_placement_tracker.entity.InterviewEntity;
import com.example.campus_placement_tracker.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping
    public InterviewEntity createInterview(
            @Valid @RequestBody InterviewEntity interview) {

        return interviewService.createInterview(interview);
    }

    @GetMapping
    public List<InterviewEntity> getAllInterviews() {

        return interviewService.getAllInterviews();
    }

    @GetMapping("/{interviewId}")
    public InterviewEntity getInterviewById(
            @PathVariable int interviewId) {

        return interviewService.getInterviewById(interviewId);
    }

    @PutMapping("/{interviewId}")
    public InterviewEntity updateInterview(
            @PathVariable int interviewId,
            @Valid @RequestBody InterviewEntity interview) {

        return interviewService.updateInterview(
                interviewId,
                interview
        );
    }

    @DeleteMapping("/{interviewId}")
    public String deleteInterview(
            @PathVariable int interviewId) {

        boolean deleted =
                interviewService.deleteInterview(interviewId);

        if (deleted) {
            return "Interview deleted successfully";
        }

        return "Interview not found";
    }
}