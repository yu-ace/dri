package com.example.car2.dao;

import com.example.car2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentDao extends JpaRepository<Student,Integer> {
    Student findById(int id);
    Page<Student> findStudentByStatus(int status, Pageable pageable);
}
