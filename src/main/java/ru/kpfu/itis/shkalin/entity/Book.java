package ru.kpfu.itis.shkalin.entity;

import java.util.Arrays;

public class Book extends AbstractEntity {
    private Integer id;
    private String author;
    private String title;
    private String description;
    private Integer[] postsId;
    private String fileURL;

    public Book() {}

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
