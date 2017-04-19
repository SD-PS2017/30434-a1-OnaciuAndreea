package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Activities;
import com.example.model.User;

public interface ActivityRepository extends JpaRepository<Activities, Long> {
    List<Activities> findByUser(User u);
}
