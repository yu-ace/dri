package com.example.car2.dao;

import com.example.car2.model.ExamRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExamRecordsDao extends JpaRepository<ExamRecords,Integer> {
    Page<ExamRecords> findByStudentId(int id, Pageable pageable);
}
