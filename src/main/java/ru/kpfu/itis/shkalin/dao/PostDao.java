package ru.kpfu.itis.shkalin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.shkalin.entity.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDao implements Dao<Post> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostDao.class);
    private final Connection connection;

    public PostDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Post post) {

        Integer userId = post.getUserId();
        String viewAuthor = post.getViewAuthor();
        String title = post.getTitle();
        String text = post.getText();
        LocalDateTime date = post.getDate();
        String type = post.getType();

        String sql = null;

        try {
            sql = "INSERT INTO post (user_id, title, date, author, text, type) VALUES (?, ?, ?, ?, ?, ?); " +
                    "COMMIT;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, title);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(date));
            preparedStatement.setString(4, viewAuthor);
            preparedStatement.setString(5, text);
            preparedStatement.setString(6, type);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("Failed execute insert query", e);
            LOGGER.warn("SQL: " + sql);
        }
    }

    @Override
    public Post get(int id) {
        String sql = null;
        try {
            Statement statement = connection.createStatement();
            sql = "SELECT * " +
                    "FROM post as p " +
                    "WHERE p.id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("author"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("date").toLocalDateTime(),
                        resultSet.getString("type")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOGGER.warn("Failed execute get query", e);
            LOGGER.warn("SQL: " + sql);
            return null;
        }
    }

    public List<Post> getByUserId(int id) {
        String sql = null;
        try {
            Statement statement = connection.createStatement();
            sql = "SELECT * " +
                    "FROM post as p " +
                    "WHERE p.user_id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);

            List<Post> posts = new ArrayList<>();

            while (resultSet.next()) {
                Post post = new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("author"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("date").toLocalDateTime(),
                        resultSet.getString("type")
                );
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            LOGGER.warn("SQL: " + sql);
            LOGGER.warn("Failed execute get query", e);
            return List.of();
        }
    }

    @Override
    public Post get(String name) {
        return null;
    }

    @Override
    public List<Post> getAll() {
        String sql = null;
        try {
            Statement statement = connection.createStatement();
            sql = "SELECT * FROM post";
            ResultSet resultSet = statement.executeQuery(sql);

            List<Post> posts = new ArrayList<>();

            while (resultSet.next()) {
                Post post = new Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("author"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("date").toLocalDateTime(),
                        resultSet.getString("type")
                );
                posts.add(post);
            }

            return posts;
        } catch (SQLException e) {
            LOGGER.warn("SQL: " + sql);
            LOGGER.warn("Failed execute get query", e);
            return List.of();
        }
    }

    @Override
    public void update(Post post) {

        Integer id = post.getId();
        String viewAuthor = post.getViewAuthor();
        String title = post.getTitle();
        String text = post.getText();
        LocalDateTime date = post.getDate();
        String sql = null;
        PreparedStatement preparedStatement;

        try {
            int counter = 0;
            if (date == null || viewAuthor == null) {
                sql = "UPDATE post " +
                        "SET (title, text) = (?, ?) " +
                        "WHERE id = ?;";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(++counter, title);
                preparedStatement.setString(++counter, text);
            } else {
                sql = "UPDATE post " +
                        "SET (date, author, title, text) = (?, ?, ?, ?) " +
                        "WHERE id = ?;";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setTimestamp(++counter, Timestamp.valueOf(date));
                preparedStatement.setString(++counter, viewAuthor);
                preparedStatement.setString(++counter, title);
                preparedStatement.setString(++counter, text);
            }
            preparedStatement.setInt(++counter, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("Failed execute update query", e);
            LOGGER.warn("SQL: " + sql);
        }
    }

    @Override
    public void delete(int id) {
        String sql = null;

        try {
            sql = "DELETE FROM post " +
                    "WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("Failed execute delete query", e);
            LOGGER.warn("SQL: " + sql);
        }
    }
}
