package com.server.server.service;

import com.server.server.model.AppUser;
import com.server.server.repo.AppUserRepository;
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
public class AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    RoleRepository roleRepository;

    public AppUser createAppUser(AppUser appUser) {
        log.info("Saving AppUser {} to database", appUser.getUsername());
        return appUserRepository.save(appUser);
    }

    public void deleteAppUser(String id) {
        log.info("Deleting AppUser {} with ID {}", appUserRepository.findAppUserById(id), id);
        appUserRepository.delete(appUserRepository.findAppUserById(id));
    }

    public List<AppUser> getAllUsers() {
        log.info("Getting all AppUsers");
        return appUserRepository.findAll();
    }

    public AppUser getUserByUsername(String username) {
        log.info("Getting AppUser by username {}", username);
        return appUserRepository.findAppUserByUsername(username);
    }

    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to AppUser {}", roleName, username);
        appUserRepository.findAppUserByUsername(username).getRoles().add(roleRepository.findRoleByRoleName(roleName));
    }
}
