package com.example.codesage.service;

import com.example.codesage.model.Recommendation;
import com.example.codesage.model.RequestedData;
import com.example.codesage.repository.RequestRepositories;
import com.example.codesage.repository.ResponseRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepositories requestRepositories;

    public Optional<RequestedData> findById(Long id) {
        return requestRepositories.findById(id);
    }
}
