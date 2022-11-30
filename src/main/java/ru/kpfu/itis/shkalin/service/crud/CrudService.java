package ru.kpfu.itis.shkalin.service.crud;

import ru.kpfu.itis.shkalin.dto.AbstractDto;

import java.util.List;

public interface CrudService<T extends AbstractDto> {

    // CREATE
    void create(T dto);

    // READ
    T get(int id);
    T get(String name);
    List<T> getAll();

    // UPDATE
    void update(T t);

    // DELETE
    void delete(int id);

}
