package ru.kpfu.itis.shkalin.dto;

public class UserCreateDto extends AbstractDto {
    private String username;
    private String password;
    private String email;
    private String pictureURL;

    public UserCreateDto() {
    }

    public UserCreateDto(String username, String password, String email, String pictureURL) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.pictureURL = pictureURL;
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

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
