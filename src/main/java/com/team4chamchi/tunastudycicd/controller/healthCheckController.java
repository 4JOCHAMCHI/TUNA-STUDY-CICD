package com.team4chamchi.tunastudycicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheckController {

    @GetMapping("/")
    public String healthCheck() {
        return "health check ver 001";
    }
}
