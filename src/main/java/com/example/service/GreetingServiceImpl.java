package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String greet() {
        return "Hello, Spring Boot!";
    }
}

