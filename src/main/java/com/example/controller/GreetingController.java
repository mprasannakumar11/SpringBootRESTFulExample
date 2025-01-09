package com.example.controller;

import com.example.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    //Field Injection
    @Autowired
    //private GreetingService greetingService;

    //or

    // use final if Constructor based Injection
    private GreetingService greetingService;

    //Setter based Injection
    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    //or

    //Constructor based Injection
    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greet")
    public String greet() {
        return greetingService.greet();
    }
    /*When you run the Spring Boot application, accessing the /greet endpoint (e.g., http://localhost:8080/greet)
    will return the message "Hello, Spring Boot!".*/
}

