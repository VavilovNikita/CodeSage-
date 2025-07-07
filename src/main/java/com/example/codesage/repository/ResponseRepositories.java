package com.example.codesage.repository;

import com.example.codesage.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepositories extends JpaRepository<Recommendation, Long> {
}
