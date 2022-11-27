package com.example.car2.service;

import com.example.car2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
    void newStudent(String name);
    Page<Student> studentList(Pageable pageable);
    Page<Student> studentListByStatus(int status,Pageable pageable);
    void addGrade(int id,int n,int grade);
    Student getStudent(int id);
}
