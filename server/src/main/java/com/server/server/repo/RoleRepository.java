package com.server.server.repo;

import com.server.server.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findRoleById(String id);

    Role findRoleByRoleName(String roleName);
}
