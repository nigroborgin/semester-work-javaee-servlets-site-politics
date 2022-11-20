package ru.kpfu.itis.shkalin.entity;

public class Comment extends AbstractEntity {
    private Integer id;
    private Integer authorId;
    private String text;
    private String date; // TODO: DB date  -->  ????

    public Comment() {}

}
