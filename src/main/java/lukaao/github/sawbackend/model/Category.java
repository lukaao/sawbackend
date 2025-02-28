package lukaao.github.sawbackend.model;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public class Category {

    private String id;

    @NotEmpty(message = "The 'name' field is required.")
    @Size(min = 3, max = 50, message = "The 'name' must be between 3 and 50 characters.")
    private String name;

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
