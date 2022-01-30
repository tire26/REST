package com.example.demo.repository;

import com.example.demo.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Employer, Integer> {
}
