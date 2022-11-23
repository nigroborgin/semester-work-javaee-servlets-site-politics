package ru.kpfu.itis.shkalin.service.сrud;

import ru.kpfu.itis.shkalin.dao.Dao;
import ru.kpfu.itis.shkalin.dao.UserDao;
import ru.kpfu.itis.shkalin.dto.UserFullDto;
import ru.kpfu.itis.shkalin.entity.Role;
import ru.kpfu.itis.shkalin.entity.User;
import ru.kpfu.itis.shkalin.service.converter.ConverterService;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;
import ru.kpfu.itis.shkalin.util.PasswordUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserCrudService implements CrudService<UserFullDto> {

    private final Dao<User> dao;
    private final ConverterService converter;

    public UserCrudService(UserDao dao, ConverterServiceImpl converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public void create(UserFullDto dto) {
        User user = (User) converter.getUpdateEntity(new User(), dto);
        String password = user.getPassword();
        dao.create(user);
    }

    @Override
    public UserFullDto get(int id) {
        User user = dao.get(id);
        return (UserFullDto) converter.getUpdateDto(user, new UserFullDto());
    }

    @Override
    public UserFullDto get(String name) {
        User user = dao.get(name);
        return (UserFullDto) converter.getUpdateDto(user, new UserFullDto());
    }

    @Override
    public List<UserFullDto> getAll() {
        return dao.getAll().stream()
                .map(user -> (UserFullDto) converter.getUpdateDto(user, new UserFullDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(UserFullDto dto) {
        User user = (User) converter.getUpdateEntity(new User(), dto);
        String password = user.getPassword();
        if (password != null) {
            user.setPassword(PasswordUtil.encrypt(password));
        }
        dao.update(user);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
