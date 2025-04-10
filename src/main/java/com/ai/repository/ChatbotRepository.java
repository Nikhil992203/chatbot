package com.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.model.Chatbot;

public interface ChatbotRepository extends JpaRepository<Chatbot, String> {

}
