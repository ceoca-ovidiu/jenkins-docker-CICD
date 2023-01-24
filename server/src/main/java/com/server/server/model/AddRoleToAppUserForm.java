package com.server.server.model;

import lombok.Data;

@Data
public class AddRoleToAppUserForm {
    private String username;
    private String roleName;
}
