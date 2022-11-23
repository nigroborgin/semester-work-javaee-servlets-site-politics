package ru.kpfu.itis.shkalin.service.сrud;

import ru.kpfu.itis.shkalin.dao.Dao;
import ru.kpfu.itis.shkalin.dao.RoleDao;
import ru.kpfu.itis.shkalin.dto.RoleFullDto;
import ru.kpfu.itis.shkalin.entity.Role;
import ru.kpfu.itis.shkalin.service.converter.ConverterService;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class RoleCrudService implements CrudService<RoleFullDto>{

    private final Dao<Role> dao;
    private final ConverterService converter;

    public RoleCrudService(RoleDao dao, ConverterServiceImpl converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public void create(RoleFullDto dto) {
        Role role = (Role) converter.getUpdateEntity(new Role(), dto);
        dao.create(role);
    }

    @Override
    public RoleFullDto get(int id) {
        Role role = dao.get(id);
        return (RoleFullDto) converter.getUpdateDto(role, new RoleFullDto());
    }

    @Override
    public RoleFullDto get(String name) {
        return null;
    }

    @Override
    public List<RoleFullDto> getAll() {
        return dao.getAll().stream()
                .map(role -> (RoleFullDto) converter.getUpdateDto(role, new RoleFullDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(RoleFullDto roleFullDto) {
        Role role = (Role) converter.getUpdateEntity(new Role(), roleFullDto);
        dao.update(role);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
