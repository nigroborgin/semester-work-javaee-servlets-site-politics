package ru.kpfu.itis.shkalin.entity;

import java.util.Objects;

public class Comment extends AbstractEntity {
    private Integer id;
    private Integer authorId;
    private String text;
    private String date; // TODO: DB date  -->  ????

    public Comment() {}

    public Comment(Integer id, Integer authorId, String text, String date) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(authorId, comment.authorId) && Objects.equals(text, comment.text) && Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, text, date);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
