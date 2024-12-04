package com.aniview.aniview.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aniview.aniview.Service.AIChatService;

@RestController
@RequestMapping("/api/groq")
public class AIChatController {
    
    @Autowired  
    private AIChatService groqService;
    
    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> request) {
        try {
            String prompt = request.get("prompt");
            String response = groqService.processPrompt(prompt);
            
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("response", response);
            
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", "Error procesando la solicitud: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
        }
    }
}