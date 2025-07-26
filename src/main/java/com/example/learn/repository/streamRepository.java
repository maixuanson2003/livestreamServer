package com.example.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.learn.entity.StreamSessions;

@Repository
public interface streamRepository extends JpaRepository<StreamSessions, Long> {

}
