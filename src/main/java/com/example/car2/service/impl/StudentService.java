package com.example.car2.service.impl;

import com.example.car2.dao.IStudentDao;
import com.example.car2.model.Student;
import com.example.car2.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentService implements IStudentService {

    @Autowired
    IStudentDao studentDao;

    @Override
    public void newStudent(String name) {
        Student student = new Student();
        student.setName(name);
        student.setJoinTime(new Date());
        studentDao.save(student);
    }

    @Override
    public Page<Student> studentList(Pageable pageable) {
        return studentDao.findAll(pageable);
    }

    @Override
    public Page<Student> studentListByStatus(int status, Pageable pageable) {
        return studentDao.findStudentByStatus(status,pageable);
    }

    @Override
    public void addGrade(int id, int n, int grade) {
        Student student = studentDao.findById(id);
        switch(n){
            case 1:
                student.setClass1Grade(grade);
                break;
            case 2:
                student.setClass2Grade(grade);
                break;
            case 3:
                student.setClass3Grade(grade);
                break;
            case 4:
                student.setClass4Grade(grade);
                break;
        }
        if(student.getClass1Grade() >= 90 && student.getClass2Grade() >= 90
                && student.getClass3Grade() >= 90 && student.getClass4Grade() >= 90){
            student.setStatus(1);
        }
        studentDao.save(student);
    }

    @Override
    public Student getStudent(int id) {
        return studentDao.findById(id);
    }
}
