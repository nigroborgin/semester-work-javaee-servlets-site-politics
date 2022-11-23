package ru.kpfu.itis.shkalin.dto;

public class PostCreateDto extends AbstractDto{
    private Integer userId;
    private String viewAuthor;
    private String title;
    private String text;
    private String date;
    private String type;

    public PostCreateDto() {
    }

    public PostCreateDto(Integer userId, String viewAuthor, String title, String text, String date, String type) {
        this.userId = userId;
        this.viewAuthor = viewAuthor;
        this.title = title;
        this.text = text;
        this.date = date;
        this.type = type;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
