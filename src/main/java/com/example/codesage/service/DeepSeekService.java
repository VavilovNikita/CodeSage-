package com.example.codesage.service;

import com.example.codesage.api.DeepSeekApi;
import com.example.codesage.model.RecommendationResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeepSeekService {

    private final DeepSeekApi deepSeekApi;

    @Value("${deepseek.model}")
    private String model;

    @Value("${deepseek.stream}")
    private boolean stream;

    @Value("${deepseek.system.role}")
    private String systemRole;

    @Value("${deepseek.system.content}")
    private String systemContent;

    @Value("${deepseek.user.role}")
    private String userRole;

    @Value("${deepseek.system.example}")
    private String example;

    public String chatCompletion(String messages) {
        return deepSeekApi.chatCompletion(createRequestWithContent(messages)).toString();
    }

    public JSONObject createRequestWithContent(String userContent) {
        JSONObject request = new JSONObject();
        try {
            request.put("model", model);
            request.put("stream", stream);

            JSONArray messages = new JSONArray();

            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", systemRole);
            systemMessage.put("content", systemContent);

            JSONObject userMessage = new JSONObject();
            userMessage.put("role", userRole);
            userMessage.put("content", userContent + " " + example);

            messages.put(systemMessage);
            messages.put(userMessage);

            request.put("messages", messages);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return request;
    }
}
