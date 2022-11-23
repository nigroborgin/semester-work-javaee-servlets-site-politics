package ru.kpfu.itis.shkalin.entity;

import java.util.Objects;

public class User extends AbstractEntity {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private String pictureURL;
    // TODO: Posts ???

    public User() {
    }

    public User(Integer id, String username, String password, String email, Role role, String pictureURL) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.pictureURL = pictureURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(role, user.role) && Objects.equals(pictureURL, user.pictureURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, role, pictureURL);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", pictureURL='" + pictureURL + '\'' +
                '}';
    }
}
