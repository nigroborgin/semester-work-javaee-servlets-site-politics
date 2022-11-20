package ru.kpfu.itis.shkalin.dao;

import java.util.List;

public interface Dao<T> {

    // CREATE
    void create(T t);

    // READ
    T get(int id);
    List<T> getAll();

    // UPDATE
    void update(T t);

    // DELETE
    void delete(int id);

}
