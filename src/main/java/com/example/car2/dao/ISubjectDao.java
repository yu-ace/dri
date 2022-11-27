package com.example.car2.dao;

import com.example.car2.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubjectDao extends JpaRepository<Subject,Integer> {
}
