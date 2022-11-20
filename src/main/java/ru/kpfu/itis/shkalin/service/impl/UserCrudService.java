package ru.kpfu.itis.shkalin.service.impl;

import ru.kpfu.itis.shkalin.dao.Dao;
import ru.kpfu.itis.shkalin.dao.impl.UserDaoImpl;
import ru.kpfu.itis.shkalin.dto.AbstractDto;
import ru.kpfu.itis.shkalin.dto.UserReadDto;
import ru.kpfu.itis.shkalin.entity.User;
import ru.kpfu.itis.shkalin.service.CrudService;
import ru.kpfu.itis.shkalin.service.EntityDtoConverterService;

import java.util.List;
import java.util.stream.Collectors;

public class UserCrudService implements CrudService {

    private final Dao<User> userDao = new UserDaoImpl();
    private final EntityDtoConverterService converter = new UserEntityDtoConverterService();

    @Override
    public void create(AbstractDto userCreateDto) {
        User user = (User) converter.convert(userCreateDto);
        userDao.create(user);
    }

    @Override
    public AbstractDto get(int id) {
        User user = userDao.get(id);
        return converter.convert(user);
    }

    @Override
    public List<AbstractDto> getAll() {
        return userDao.getAll().stream()
                .map(user -> new UserReadDto())
                .collect(Collectors.toList());
    }

    @Override
    public void update(AbstractDto dto) {

    }

    @Override
    public void delete(int id) {

    }
}
