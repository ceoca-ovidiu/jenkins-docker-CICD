package com.server.server.controller;

import com.server.server.model.AddRoleToAppUserForm;
import com.server.server.model.AppUser;
import com.server.server.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appuser")
// This controller is responsible for the endpoints that manipulates the AppUsers from the database
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @GetMapping("/getAllAppUsers")
    // Only the user with ROLE_ADMIN rights can access this method
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<AppUser>> getAllAppUsers() {
        return ResponseEntity.ok().body(appUserService.getAllUsers());
    }

    @PostMapping("/createAppUser")
    // Only the user with ROLE_ADMIN rights can access this method
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AppUser> createAppUser(@RequestBody AppUser appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/appuser/createAppUser").toUriString());
        return ResponseEntity.created(uri).body(appUserService.createAppUser(appUser));
    }

    @DeleteMapping("/deleteAppUser")
    // Only the user with ROLE_ADMIN rights can access this method
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteAppUser(@PathVariable String id) {
        appUserService.deleteAppUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addRoleToAppUser")
    // Only the user with ROLE_ADMIN rights can access this method
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    // Created a new class named AddRoleToAppUserForm to send only one object as parameter from RequestBody
    public ResponseEntity<?> addRoleToAppUser(@RequestBody AddRoleToAppUserForm addRoleToAppUserForm) {
        appUserService.addRoleToUser(addRoleToAppUserForm.getUsername(), addRoleToAppUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }

}
