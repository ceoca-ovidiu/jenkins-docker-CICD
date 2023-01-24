package com.server.server;

import com.server.server.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    //    Command Line Runner is an interface. It is used to execute the code after the Spring Boot application started.
    CommandLineRunner run(AppUserService appUserService) {
        return args -> {

        };
    }

}
