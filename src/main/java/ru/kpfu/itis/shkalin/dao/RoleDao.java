package ru.kpfu.itis.shkalin.dao;

import ru.kpfu.itis.shkalin.entity.Role;

import java.sql.Connection;
import java.util.List;

public class RoleDao implements Dao<Role> {

    private Connection connection;

    public RoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Role role) {

    }

    @Override
    public Role get(int id) {
        return null;
    }

    @Override
    public Role get(String name) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public void update(Role role) {

    }

    @Override
    public void delete(int id) {

    }
}
