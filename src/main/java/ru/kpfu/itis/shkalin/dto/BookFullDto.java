package ru.kpfu.itis.shkalin.dto;

public class BookFullDto extends AbstractDto {
    private Integer id;
    private String author;
    private String title;
    private String description;
    private Integer[] postsId;
    private String fileURL;

    public BookFullDto() {
    }

    public BookFullDto(Integer id, String author, String title, String description, Integer[] postsId, String fileURL) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.postsId = postsId;
        this.fileURL = fileURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer[] getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer[] postsId) {
        this.postsId = postsId;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}
