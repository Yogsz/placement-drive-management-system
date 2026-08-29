package com.example.campus_placement_tracker.service;

import com.example.campus_placement_tracker.entity.Student;
import com.example.campus_placement_tracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentrepository;

    public List<Student> getAllStudent(){
        return studentrepository.findAll();
    }

    public Student getStudentById(int id){
        return studentrepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student){
        return studentrepository.save(student);
    }

    public Student updateStudent(int id, Student updateStudent){
        Student existingStudent = studentrepository.findById(id).orElse(null);
        if(existingStudent!=null){
            existingStudent.setName(updateStudent.getName());
            existingStudent.setEmail(updateStudent.getEmail());
            existingStudent.setDepartment(updateStudent.getDepartment());
            existingStudent.setAcademicYear(updateStudent.getAcademicYear());
            existingStudent.setPhoneNo(updateStudent.getPhoneNo());

            return studentrepository.save(existingStudent);
        }
        return null;
    }

    public void deleteById(int id){
        studentrepository.deleteById(id);
    }
}