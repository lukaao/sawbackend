package lukaao.github.sawbackend.model;


import java.util.Date;

public class UserApp {

    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Boolean isActive = true; // Default to true
   private Date createdAt;
    private Date updatedAt;

    // Getters and Setters

    public UserApp(String id, String name, String email, String password, String role, Date createdAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
