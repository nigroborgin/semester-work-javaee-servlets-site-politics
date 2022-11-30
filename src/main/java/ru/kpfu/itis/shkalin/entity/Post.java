package ru.kpfu.itis.shkalin.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post extends AbstractEntity {
    private Integer id;
    private Integer userId;
    private String viewAuthor;
    private String title;
    private String text;
    private LocalDateTime date;
    private String type;

    public Post() {}

    public Post(Integer id, Integer userId, String viewAuthor, String title, String text, LocalDateTime date, String type) {
        this.id = id;
        this.userId = userId;
        this.viewAuthor = viewAuthor;
        this.title = title;
        this.text = text;
        this.date = date;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getViewAuthor() {
        return viewAuthor;
    }

    public void setViewAuthor(String viewAuthor) {
        this.viewAuthor = viewAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(userId, post.userId) && Objects.equals(viewAuthor, post.viewAuthor) && Objects.equals(title, post.title) && Objects.equals(text, post.text) && Objects.equals(date, post.date) && Objects.equals(type, post.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, viewAuthor, title, text, date, type);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", viewAuthor='" + viewAuthor + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
