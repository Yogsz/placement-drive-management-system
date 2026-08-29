package com.example.campus_placement_tracker.service;

import com.example.campus_placement_tracker.entity.ApplicationEntity;
import com.example.campus_placement_tracker.entity.PlacementDriveEntity;
import com.example.campus_placement_tracker.entity.Student;
import com.example.campus_placement_tracker.repository.ApplicationRepository;
import com.example.campus_placement_tracker.repository.PlacementDriveRepository;
import com.example.campus_placement_tracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PlacementDriveRepository placementDriveRepository;


    public ApplicationEntity createApplication(ApplicationEntity application) {

        Student student =
                studentRepository.findById(application.getStudentId()).orElse(null);

        PlacementDriveEntity drive =
                placementDriveRepository.findById(application.getDriveId()).orElse(null);

        if (student == null || drive == null) {
            return null;
        }

        application.setStudent(student);
        application.setPlacementDrive(drive);

        return applicationRepository.save(application);
    }


    public List<ApplicationEntity> getAllApplications() {

        List<ApplicationEntity> applications = applicationRepository.findAll();

        for(ApplicationEntity application : applications){
            application.setStudentId(application.getStudent().getId());
            application.setDriveId(application.getPlacementDrive().getDriveId());
        }
        return applications;
    }

    public ApplicationEntity getApplicationById(int id) {
        List<ApplicationEntity> applications = applicationRepository.findAll();

        for(ApplicationEntity application : applications){
            if(id == application.getId()){
                application.setStudentId(application.getStudent().getId());
                application.setDriveId(application.getPlacementDrive().getDriveId());
                return application;
            }
        }
        return null;


    }


    public ApplicationEntity updateApplication(
            int id,
            ApplicationEntity application) {

        ApplicationEntity existingApplication =
                applicationRepository.findById(id).orElse(null);
        if (existingApplication!=null) {

            existingApplication.setStatus(application.getStatus());

            return applicationRepository.save(existingApplication);
        }

        return null;
    }


    public boolean deleteApplication(int id) {

        if (applicationRepository.existsById(id)) {

            applicationRepository.deleteById(id);
            return true;
        }

        return false;
    }
}