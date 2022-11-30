package ru.kpfu.itis.shkalin.dao;

import ru.kpfu.itis.shkalin.entity.AbstractEntity;

import java.util.List;

public interface Dao<T extends AbstractEntity> {

    // CREATE
    void create(T t);

    // READ
    T get(int id);

    T get(String name);

    List<T> getAll();

    // UPDATE
    void update(T t);

    // DELETE
    void delete(int id);

}
