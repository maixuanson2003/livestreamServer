package com.example.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.learn.entity.TypeStream;

@Repository
public interface typeStreamRepository extends JpaRepository<TypeStream, Long> {

}
