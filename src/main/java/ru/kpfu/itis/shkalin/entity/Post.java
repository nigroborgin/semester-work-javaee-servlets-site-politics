package ru.kpfu.itis.shkalin.entity;

public class Post extends AbstractEntity {
    private Integer id;
    private Integer authorId;
    private String title;
    private String text;
    private String date; // TODO: DB date  -->  ????

    public Post() {}

}
