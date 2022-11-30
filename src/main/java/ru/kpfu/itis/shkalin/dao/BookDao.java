package ru.kpfu.itis.shkalin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.shkalin.entity.Book;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements Dao<Book> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostDao.class);
    private final Connection connection;

    public BookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Book book) {

        String author = book.getAuthor();
        String title = book.getTitle();
        String description = book.getDescription();
        String fileURL = book.getFileURL();

        String sql = null;

        try {
            sql = "INSERT INTO book (author, title, description, file_url) VALUES (?, ?, ?, ?); " +
                    "COMMIT;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, author);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, fileURL);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("Failed execute insert query", e);
            LOGGER.warn("SQL: " + sql);
        }
    }

    @Override
    public Book get(int id) {
        String sql = null;
        try {
            Statement statement = connection.createStatement();
            sql = "SELECT * " +
                    "FROM book as b " +
                    "WHERE b.id = " + id;
//            String sql2 = "SELECT post_id " +
//                    "FROM post_book as pb " +
//                    "WHERE pb.book_id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);
//            ResultSet resultSet2 = statement.executeQuery(sql2);
//
//            ArrayList<Integer> postIdList = new ArrayList<>();
//
//            while (resultSet2.next()) {
//                postIdList.add(resultSet2.getInt("post_id"));
//            }

            if (resultSet.next()) {
//                if (resultSet2.next()) {
//                    resultSet2.getArray("post_id").getBaseType();
//                }
                return new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("author"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("file_url")
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

    @Override
    public Book get(String name) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        String sql = null;
        try {
            Statement statement = connection.createStatement();
            sql = "SELECT * FROM book";
            ResultSet resultSet = statement.executeQuery(sql);

            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("author"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("file_url")
                );
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            LOGGER.warn("Failed execute get query", e);
            LOGGER.warn("SQL: " + sql);
            return List.of();
        }
    }

    @Override
    public void update(Book book) {

        Integer id = book.getId();
        String author = book.getAuthor();
        String title = book.getTitle();
        String description = book.getDescription();
        String fileURL = book.getFileURL();
        String sql = null;
        PreparedStatement preparedStatement;

        try {
            sql = "UPDATE book SET (author, title, description, file_url) = (?, ?, ?, ?) " +
                    "WHERE id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, author);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, fileURL);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("SQL: " + sql);
            LOGGER.warn("Failed execute insert query", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = null;
        try {
            sql = "DELETE FROM book WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("SQL: " + sql);
            LOGGER.warn("Failed execute delete query", e);
        }
    }
}
