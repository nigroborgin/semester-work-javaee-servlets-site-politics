package ru.kpfu.itis.shkalin.dto;

public class PostViewDto extends AbstractDto{
    private Integer id;
    private String viewAuthor;
    private String title;
    private String text;
    private String date;

    public PostViewDto() {
    }

    public PostViewDto(Integer id, String viewAuthor, String title, String text, String date) {
        this.id = id;
        this.viewAuthor = viewAuthor;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
