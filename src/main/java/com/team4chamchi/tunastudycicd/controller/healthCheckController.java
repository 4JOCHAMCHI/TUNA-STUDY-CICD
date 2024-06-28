package com.team4chamchi.tunastudycicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheckController {

    @GetMapping("/health")
    public String healthCheck() {
        return "new branch push";
    }
}
