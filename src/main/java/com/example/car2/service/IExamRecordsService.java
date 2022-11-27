package com.example.car2.service;

import com.example.car2.model.ExamRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IExamRecordsService {
    Page<ExamRecords> getExamRecordsList(Pageable pageable);
    Page<ExamRecords> getExamRecordsListById(int id,Pageable pageable);
    void newExamRecords(int id,int n,int grade);
}
