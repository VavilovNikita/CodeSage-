package com.example.codesage.service;

import com.example.codesage.model.RecommendationResponse;
import com.example.codesage.repository.ResponseRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogConsumer {

    private final DeepSeekService deepSeekService;
    private final ResponseRepositories responseRepositories;

    @KafkaListener(topics = "logs", groupId = "log-group")
    public void consume(String logMessage) {
        RecommendationResponse response = deepSeekService.chatCompletion(logMessage);
        responseRepositories.saveAll(response.getRecommendations());
    }
}

