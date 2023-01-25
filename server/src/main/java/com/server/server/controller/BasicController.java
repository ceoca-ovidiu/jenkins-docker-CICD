package com.server.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
@Slf4j
public class BasicController {

    @GetMapping("/welcome")
    public String getWelcome() {
        log.info("Welcome");
        return "Welcome to Spring Boot 3.0.1";
    }

}
