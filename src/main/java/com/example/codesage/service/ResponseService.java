package com.example.codesage.service;

import com.example.codesage.model.Recommendation;
import com.example.codesage.repository.ResponseRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseService {
    private final ResponseRepositories responseRepositories;

    public List<Recommendation> findAll() {
        return responseRepositories.findAll();
    }
}
