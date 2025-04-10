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

    // Use your OpenRouter API key
    @Value("${openrouter.api.key}")
    private String openRouterApiKey;
    
    @Autowired
    public ChatbotService(ChatbotRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
        this.restTemplate = new RestTemplate();
    }

    public synchronized String chat(String question) {
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

        String url = "https://openrouter.ai/api/v1/chat/completions";

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "mistralai/mistral-7b-instruct");
        payload.put("messages", List.of(Map.of("role", "user", "content", question)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openRouterApiKey); // OpenRouter auth

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && responseBody.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return message.get("content").toString().trim();
                }
            }
            return "No response from OpenRouter.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calling OpenRouter: " + e.getMessage();
        }
    }

    public void rememberName(String name) {
        Chatbot memory = new Chatbot();
        memory.setKeyword("name");
        memory.setValue(name);
        memoryRepository.save(memory);
    
    

    }

}
