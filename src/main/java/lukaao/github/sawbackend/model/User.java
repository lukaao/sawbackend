package lukaao.github.sawbackend.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public class User {

    private String id;

    @NotEmpty(message = "The 'name' field is required.")
    @Size(min = 3, max = 100, message = "The 'name' must be between 3 and 100 characters.")
    private String name;

    @NotEmpty(message = "The 'email' field is required.")
    @Email(message = "The 'email' must be a valid email address.")
    private String email;

    @NotEmpty(message = "The 'password' field is required.")
    @Size(min = 8, message = "The 'password' must have at least 8 characters.")
    private String password;

    @NotEmpty(message = "The 'role' field is required.")
    private String role = "user"; // Default to "user"

    private Boolean isActive = true; // Default to true

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    // Getters and Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
