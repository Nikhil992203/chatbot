package com.ai.service;

import com.ai.model.Chatbot;
import com.ai.repository.ChatbotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class ChatbotService {

    private final ChatbotRepository memoryRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ChatbotService(ChatbotRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
        this.restTemplate = new RestTemplate();
    }

    public String chat(String question) {
        if (question.toLowerCase().contains("my name")) {
            Optional<Chatbot> memory = memoryRepository.findById("name");
            if (memory.isPresent()) {
                return "Your name is " + memory.get().getValue();
            } else {
                return "I don't know your name yet. Please tell me.";
            }
        }

        Chatbot memory = new Chatbot();
        memory.setKeyword("last_question");
        memory.setValue(question);
        memoryRepository.save(memory);

        // Prepare request for Ollama
        String url = "http://localhost:11434/api/generate";
        
        Map<String, Object> body = new HashMap<>();
        body.put("model", "mistral");  //
        body.put("prompt", question);
        body.put("stream", false);     //

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && responseBody.containsKey("response")) {
                return responseBody.get("response").toString().trim();
            } else {
                return "No response from Ollama.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calling Ollama: " + e.getMessage();
        }
    }

    public void rememberName(String name) {
        Chatbot memory = new Chatbot();
        memory.setKeyword("name");
        memory.setValue(name);
        memoryRepository.save(memory);
    }
}
