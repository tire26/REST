package com.example.repository;

import com.example.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Employer, Integer> {
}
