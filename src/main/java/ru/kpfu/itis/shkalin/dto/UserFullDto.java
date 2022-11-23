package ru.kpfu.itis.shkalin.dto;

import ru.kpfu.itis.shkalin.entity.Role;

public class UserFullDto extends AbstractDto {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private String pictureURL;

    public UserFullDto() {
    }

    public UserFullDto(Integer id, String username, String password, String email, Role role, String pictureURL) {
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
}
