package com.server.server.repo;

import com.server.server.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppUserRepository extends MongoRepository<AppUser, String> {
    AppUser findAppUserByUsername(String username);

    List<AppUser> findAppUsersByUserFirstName(String userFirstName);

    List<AppUser> findAppUsersByUserLastName(String userLastName);

    AppUser findAppUserById(String id);
}
