package com.example.campus_placement_tracker.service;

import com.example.campus_placement_tracker.entity.ApplicationEntity;
import com.example.campus_placement_tracker.entity.InterviewEntity;
import com.example.campus_placement_tracker.repository.ApplicationRepository;
import com.example.campus_placement_tracker.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public InterviewEntity createInterview(InterviewEntity interview) {

        ApplicationEntity application =
                applicationRepository
                        .findById(interview.getApplicationId())
                        .orElse(null);

        if (application == null) {
            return null;
        }

        interview.setApplication(application);

        return interviewRepository.save(interview);
    }


    public List<InterviewEntity> getAllInterviews() {

        List<InterviewEntity> interviews = interviewRepository.findAll();

        for(InterviewEntity interview : interviews){
            interview.setApplicationId(interview.getApplication().getId());
        }
        return interviews;
    }


    public InterviewEntity getInterviewById(int interviewId) {

        List<InterviewEntity> interviews = interviewRepository.findAll();

        for(InterviewEntity interview : interviews){
            if(interview.getInterviewId() == interviewId){
                interview.setApplicationId(interview.getApplication().getId());
                return interview;
            }

        }

        return null;
    }


    public InterviewEntity updateInterview(
            int interviewId,
            InterviewEntity interview) {

        InterviewEntity existingInterview =
                interviewRepository.findById(interviewId).orElse(null);
        if (existingInterview!=null) {

            ApplicationEntity application =
                    applicationRepository
                            .findById(interview.getApplicationId())
                            .orElse(null);

            if (application == null) {
                return null;
            }

            existingInterview.setApplication(application);

            existingInterview.setInterviewDate(
                    interview.getInterviewDate());

            existingInterview.setInterviewTime(
                    interview.getInterviewTime());

            existingInterview.setMode(
                    interview.getMode());

            existingInterview.setStatus(
                    interview.getStatus());

            return interviewRepository.save(existingInterview);
        }

        return null;
    }


    public boolean deleteInterview(int interviewId) {

        if (interviewRepository.existsById(interviewId)) {

            interviewRepository.deleteById(interviewId);

            return true;
        }

        return false;
    }
}