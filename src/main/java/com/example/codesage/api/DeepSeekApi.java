package com.example.codesage.api;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DeepSeekApi {
    private static final String BASE_URL = "https://api.deepseek.com";
    private final String apiKey;
    private final OkHttpClient client;

    public DeepSeekApi(@Value("${deepseek.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.client = new OkHttpClient();
    }

    public String chatCompletion(JSONObject requestBody) {
        try {
            return post("/chat/completions", requestBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String post(String endpoint, JSONObject jsonBody) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);

        Request request = new Request.Builder()
            .url(BASE_URL + endpoint)
            .addHeader("Authorization", "Bearer " + apiKey)
            .post(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API error: " + response.code() + " - " + response.message());
            }

            String responseBody = response.body().string();

            JSONObject jsonResponse = new JSONObject(responseBody);

            return jsonResponse
                .getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
        }
    }

}