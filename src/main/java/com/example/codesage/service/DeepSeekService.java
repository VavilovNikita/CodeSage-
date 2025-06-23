package com.example.codesage.service;

import com.example.codesage.api.DeepSeekApi;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeepSeekService {
    private final DeepSeekApi deepSeekApi;

    public String chatCompletion(String messages) {
        return deepSeekApi.chatCompletion(createRequestWithContent(messages));

    }

    public JSONObject createRequestWithContent(String userContent) {
        JSONObject request = new JSONObject();
        try {
            request.put("model", "deepseek-chat");
            request.put("stream", false);

            JSONArray messages = new JSONArray();

            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", "You are a helpful assistant.");

            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", userContent);

            messages.put(systemMessage);
            messages.put(userMessage);

            request.put("messages", messages);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return request;
    }


}
