package com.server.server.service;

import com.server.server.model.Role;
import com.server.server.repo.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role createRole(Role role) {
        log.info("Creating role with name {}", role.getRoleName());
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        log.info("Getting all roles");
        return roleRepository.findAll();
    }
}
