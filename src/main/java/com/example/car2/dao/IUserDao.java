package com.example.car2.dao;

import com.example.car2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User,Integer> {
    User findByName(String name);
    User findById(int id);
    Page<User> findByIsDelete(int isDelete, Pageable pageable);
}
