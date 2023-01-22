package com.server.server.controller;

import com.server.server.model.AppUser;
import com.server.server.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appuser")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @GetMapping("/getAllAppUsers")
    public ResponseEntity<List<AppUser>> getAllAppUsers() {
        return ResponseEntity.ok().body(appUserService.getAllUsers());
    }

    @PostMapping("/createAppUser")
    public ResponseEntity<AppUser> createAppUser(@RequestBody AppUser appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/appuser/createAppUser").toUriString());
        return ResponseEntity.created(uri).body(appUserService.createAppUser(appUser));
    }

    @DeleteMapping("/deleteAppUser")
    public ResponseEntity<?> deleteAppUser(@PathVariable String id) {
        appUserService.deleteAppUser(id);
        return ResponseEntity.ok().build();
    }

}
