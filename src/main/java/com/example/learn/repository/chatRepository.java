package com.example.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.learn.entity.Chat;

@Repository
public interface chatRepository extends JpaRepository<Chat, Long> {
    // Additional query methods can be defined here if needed
}
