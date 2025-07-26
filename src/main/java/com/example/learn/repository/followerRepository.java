package com.example.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.learn.entity.Follower;

@Repository
public interface followerRepository extends JpaRepository<Follower, Long> {

}
