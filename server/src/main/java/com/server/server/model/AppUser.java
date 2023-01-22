package com.server.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class AppUser {
    @Id
    private String id;
    private String userFirstName;
    private String userLastName;
    private String username;
    private String password;
    private Collection<Role> roles = new ArrayList<>();
}
