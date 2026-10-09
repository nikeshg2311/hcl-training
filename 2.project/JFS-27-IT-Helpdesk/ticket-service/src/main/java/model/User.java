
package model;

public class User extends BaseEntity {

    private Long userId;
    private String name;
    private String email;
    private String role;
    private String supportGroup;

    public User() {
        super();
    }

    public User(Long userId, String name, String email,
                String role, String supportGroup) {

        super();

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.supportGroup = supportGroup;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
        updateTimestamp();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateTimestamp();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        updateTimestamp();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        updateTimestamp();
    }

    public String getSupportGroup() {
        return supportGroup;
    }

    public void setSupportGroup(String supportGroup) {
        this.supportGroup = supportGroup;
        updateTimestamp();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", supportGroup='" + supportGroup + '\'' +
                '}';
    }
}