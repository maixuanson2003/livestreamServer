package com.example.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.learn.entity.StreamSessions;
import com.example.learn.entity.User;

@Repository
public interface streamRepository extends JpaRepository<StreamSessions, Long> {
    @Query("SELECT s FROM StreamSessions s WHERE s.streamKey = ?1")
    public StreamSessions findByStreamKey(String streamKey);
}
