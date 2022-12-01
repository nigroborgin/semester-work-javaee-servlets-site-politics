package ru.kpfu.itis.shkalin.service.crud;

import ru.kpfu.itis.shkalin.dao.Dao;
import ru.kpfu.itis.shkalin.dao.PostDao;
import ru.kpfu.itis.shkalin.dto.PostFullDto;
import ru.kpfu.itis.shkalin.entity.Post;
import ru.kpfu.itis.shkalin.service.converter.ConverterService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostCrudService implements CrudService<PostFullDto>{

    private final Dao<Post> dao;
    private final ConverterService converter;

    public PostCrudService(Dao<Post> dao, ConverterService converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public void create(PostFullDto dto) {
        Post post = (Post) converter.getUpdateEntity(new Post(), dto);
        dao.create(post);
    }

    @Override
    public PostFullDto get(int id) {
        Post post = dao.get(id);
        if (post != null) {
            return (PostFullDto) converter.getUpdateDto(post, new PostFullDto());
        } else{
            return null;
        }
    }

    public List<PostFullDto> getByUserId(int id) {
        PostDao postDao = (PostDao) dao;
        return postDao.getByUserId(id).stream()
                .map(post -> (PostFullDto) converter.getUpdateDto(post, new PostFullDto()))
                .collect(Collectors.toList());
    }

    @Override
    public PostFullDto get(String name) {
        Post post = dao.get(name);
        if (post != null) {
            return (PostFullDto) converter.getUpdateDto(post, new PostFullDto());
        } else{
            return null;
        }
    }

    @Override
    public List<PostFullDto> getAll() {
        return dao.getAll().stream()
                .map(post -> (PostFullDto) converter.getUpdateDto(post, new PostFullDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(PostFullDto postFullDto) {
        Post post = (Post) converter.getUpdateEntity(new Post(), postFullDto);
        dao.update(post);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    public String dateForView(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int day = localDateTime.getDayOfMonth();
        int month = localDateTime.getMonthValue();
        int year = localDateTime.getYear();
//        if (hour < 10) {
//            sb.append("0").append(hour);
//        } else {
//            sb.append(hour);
//        }
//        sb.append(":");
//        if (minute < 10) {
//            sb.append("0").append(minute);
//        } else {
//            sb.append(minute);
//        }
        sb.append("  ");
        if (day < 10) {
            sb.append("0").append(day);
        } else {
            sb.append(day);
        }
        sb.append(".");
        if (month < 10) {
            sb.append("0").append(month);
        } else {
            sb.append(month);
        }
        sb.append(".").append(year);

        return sb.toString();
    }
}
