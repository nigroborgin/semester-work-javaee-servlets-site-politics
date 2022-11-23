package ru.kpfu.itis.shkalin.entity;

import java.util.Arrays;
import java.util.Objects;

public class Book extends AbstractEntity {
    private Integer id;
    private String author;
    private String title;
    private String description;
    private Integer[] postsId;
    private String fileURL;

    public Book() {}

    public Book(Integer id, String author, String title, String description, Integer[] postsId, String fileURL) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(description, book.description) && Arrays.equals(postsId, book.postsId) && Objects.equals(fileURL, book.fileURL);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, author, title, description, fileURL);
        result = 31 * result + Arrays.hashCode(postsId);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", postsId=" + Arrays.toString(postsId) +
                ", fileURL='" + fileURL + '\'' +
                '}';
    }
}
