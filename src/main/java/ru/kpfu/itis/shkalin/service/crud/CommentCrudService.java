package ru.kpfu.itis.shkalin.service.crud;

import ru.kpfu.itis.shkalin.dao.CommentDao;
import ru.kpfu.itis.shkalin.dao.Dao;
import ru.kpfu.itis.shkalin.dto.CommentFullDto;
import ru.kpfu.itis.shkalin.entity.Comment;
import ru.kpfu.itis.shkalin.service.converter.ConverterService;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CommentCrudService implements CrudService<CommentFullDto>{

    private final Dao<Comment> dao;
    private final ConverterService converter;

    public CommentCrudService(CommentDao dao, ConverterServiceImpl converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public void create(CommentFullDto dto) {
        Comment comment = (Comment) converter.getUpdateEntity(new Comment(), dto);
        dao.create(comment);
    }

    @Override
    public CommentFullDto get(int id) {
        Comment comment = dao.get(id);
        return (CommentFullDto) converter.getUpdateDto(comment, new CommentFullDto());
    }

    @Override
    public CommentFullDto get(String name) {
        return null;
    }

    @Override
    public List<CommentFullDto> getAll() {
        return dao.getAll().stream()
                .map(comment -> (CommentFullDto) converter.getUpdateDto(comment, new CommentFullDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(CommentFullDto commentFullDto) {
        Comment comment = (Comment) converter.getUpdateEntity(new Comment(), commentFullDto);
        dao.update(comment);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
