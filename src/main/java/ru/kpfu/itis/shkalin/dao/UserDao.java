package ru.kpfu.itis.shkalin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.shkalin.entity.Role;
import ru.kpfu.itis.shkalin.entity.User;
import ru.kpfu.itis.shkalin.util.PostgresConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.List;

public class UserDao implements Dao<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
//    private final Connection connection = PostgresConnectionUtil.getConnection();

    @Override
    public void create(User user) {
        System.out.println("USER " + user.getUsername() + " CREATE!!!");
        System.out.println("password: " + user.getPassword());
    }

    @Override
    public User get(int id) {
        if (id == 1) {
            return new User(1, "RTF", "ssss", "essss@rtf.ru", new Role(), "ddd");
        } else if (id == 2) {
            return new User(2, "d", "d", "dua@dialectic.ru", new Role(), "sss");
        }
        return null;
    }

    @Override
    public User get(String name) {
        if (name.equals("RTF")) {
            return new User(1, "RTF", "ssss", "essss@rtf.ru", new Role(), "ddd");
        } else if (name.equals("d")) {
            return new User(2, "d", "d", "dua@dialectic.ru", new Role(), "sss");
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
