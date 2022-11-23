package ru.kpfu.itis.shkalin.dto;

public class CommentFullDto extends AbstractDto {
    private Integer id;
    private Integer authorId;
    private String text;
    private String date; // TODO: DB date  -->  ????

    public CommentFullDto() {
    }

    public CommentFullDto(Integer id, Integer authorId, String text, String date) {
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
}
