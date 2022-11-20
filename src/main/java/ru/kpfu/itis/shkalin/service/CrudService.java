package ru.kpfu.itis.shkalin.service;

import ru.kpfu.itis.shkalin.dto.AbstractDto;

import java.util.List;

public interface CrudService {

    // CREATE
    void create(AbstractDto dto);

    // READ
    AbstractDto get(int id);
    List<AbstractDto> getAll();

    // UPDATE
    void update(AbstractDto dto);

    // DELETE
    void delete(int id);

}