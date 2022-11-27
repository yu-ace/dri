package com.example.car2.service.impl;

import com.example.car2.dao.IExamRecordsDao;
import com.example.car2.model.ExamRecords;
import com.example.car2.service.IExamRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExamRecordsService implements IExamRecordsService {

    @Autowired
    IExamRecordsDao examRecordsDao;

    @Override
    public Page<ExamRecords> getExamRecordsList(Pageable pageable) {
        return examRecordsDao.findAll(pageable);
    }

    @Override
    public Page<ExamRecords> getExamRecordsListById(int id, Pageable pageable) {
        return examRecordsDao.findByStudentId(id,pageable);
    }

    @Override
    public void newExamRecords(int id, int n, int grade) {
        ExamRecords examRecords = new ExamRecords();
        examRecords.setStudentId(id);
        examRecords.setSubjectId(n);
        examRecords.setGrade(grade);
        examRecords.setExamTime(new Date());
        examRecordsDao.save(examRecords);
    }

}
