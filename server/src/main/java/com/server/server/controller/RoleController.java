package com.server.server.controller;

import com.server.server.model.Role;
import com.server.server.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
// This controller is responsible for the endpoints that manipulates the roles from the database
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/createRole")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        // The uri is created to send the response as "created"
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/createRole").toUriString());
        return ResponseEntity.created(uri).body(roleService.createRole(role));
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }
}
