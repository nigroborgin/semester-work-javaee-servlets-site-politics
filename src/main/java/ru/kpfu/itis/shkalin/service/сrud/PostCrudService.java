package ru.kpfu.itis.shkalin.service.сrud;

import ru.kpfu.itis.shkalin.dao.Dao;
import ru.kpfu.itis.shkalin.dao.PostDao;
import ru.kpfu.itis.shkalin.dto.PostFullDto;
import ru.kpfu.itis.shkalin.entity.Post;
import ru.kpfu.itis.shkalin.service.converter.ConverterService;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class PostCrudService implements CrudService<PostFullDto>{

    private final Dao<Post> postDao;
    private final ConverterService converter;

    public PostCrudService(Dao<Post> postDao, ConverterService converter) {
        this.postDao = postDao;
        this.converter = converter;
    }

    @Override
    public void create(PostFullDto dto) {
        Post post = (Post) converter.getUpdateEntity(new Post(), dto);
        postDao.create(post);
    }

    @Override
    public PostFullDto get(int id) {
        Post post = postDao.get(id);
        return (PostFullDto) converter.getUpdateDto(post, new PostFullDto());
    }

    @Override
    public PostFullDto get(String name) {
        return null;
    }

    @Override
    public List<PostFullDto> getAll() {
        return postDao.getAll().stream()
                .map(post -> (PostFullDto) converter.getUpdateDto(post, new PostFullDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(PostFullDto postFullDto) {
        Post post = (Post) converter.getUpdateEntity(new Post(), postFullDto);
        postDao.update(post);
    }

    @Override
    public void delete(int id) {
        postDao.delete(id);
    }
}
