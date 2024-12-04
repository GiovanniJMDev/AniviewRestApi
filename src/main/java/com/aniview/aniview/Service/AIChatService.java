package com.aniview.aniview.Service;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class AIChatService {
    private static final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";
    
    @Value("${spring.groq.api-key}")
    private String apiKey;
    
    private final OkHttpClient client = new OkHttpClient();
    
    public String processPrompt(String prompt) throws IOException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", "llama3-groq-70b-8192-tool-use-preview");
        
        JSONArray messages = new JSONArray();
        
        // Agregar mensaje del sistema
        JSONObject systemMessage = new JSONObject();
        systemMessage.put("role", "system");
        systemMessage.put("content", "Eres Aniview_AI, un asistente especializado en anime. Debes responder siempre en español de manera amigable y entusiasta, compartiendo tu conocimiento sobre anime y cultura japonesa.");
        messages.put(systemMessage);
        
        // Agregar mensaje del usuario
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.put(userMessage);
        
        jsonBody.put("messages", messages);
        
        Request request = new Request.Builder()
            .url(GROQ_API_URL)
            .addHeader("Authorization", "Bearer " + apiKey)
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(jsonBody.toString(), MediaType.parse("application/json")))
            .build();
            
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
        }
    }
}