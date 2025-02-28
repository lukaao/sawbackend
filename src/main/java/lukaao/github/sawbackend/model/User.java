package lukaao.github.sawbackend.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.UUID;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Boolean isActive = true; // Default to true
//    private OffsetDateTime createdAt;
//    private OffsetDateTime updatedAt;

    // Getters and Setters

    public User(String id, String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
