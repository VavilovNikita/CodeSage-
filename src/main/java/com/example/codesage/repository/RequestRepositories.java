package com.example.codesage.repository;

import com.example.codesage.model.RequestedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepositories extends JpaRepository<RequestedData, Long> {
}
