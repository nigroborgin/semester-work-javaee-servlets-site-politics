package ru.kpfu.itis.shkalin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.shkalin.entity.Account;
import ru.kpfu.itis.shkalin.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao implements Dao<Account> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDao.class);
    private final Connection connection;

    public AccountDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Account account) {

        String acUsername = account.getUsername();
        String acPassword = account.getPassword();
        String acEmail = account.getEmail();
        String acPicture = account.getPictureURL();
        String sql = null;

        try {
            sql = "INSERT INTO account (username, password, email, picture_url) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, acUsername);
            preparedStatement.setString(2, acPassword);
            preparedStatement.setString(3, acEmail);
            preparedStatement.setString(4, acPicture);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("SQL: " + sql);
            LOGGER.warn("Failed execute insert query", e);
        }
    }

    @Override
    public Account get(int id) {

        String sql = null;
        try {
            Statement statement = connection.createStatement();
            sql = "SELECT acc.id as acc_id, acc.username, acc.password, acc.email, acc.picture_url, r.id as r_id, r.name " +
                    "FROM account as acc " +
                    "    LEFT JOIN role_of_user as r " +
                    "        ON acc.role_id = r.id " +
                    "WHERE acc.id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt("acc_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        new Role(resultSet.getInt("r_id"), resultSet.getString("name")),
                        resultSet.getString("picture_url")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOGGER.warn("SQL: " + sql);
            LOGGER.warn("Failed execute get query", e);
            return null;
        }
    }

    @Override
    public Account get(String name) {
        String sql = null;
        try {
            Statement statement = connection.createStatement();
            sql = "SELECT acc.id as acc_id, acc.username, acc.password, acc.email, acc.picture_url, r.id as r_id, r.name " +
                    "FROM account as acc " +
                    "   LEFT JOIN role_of_user as r " +
                    "       ON acc.role_id = r.id " +
                    "WHERE acc.username = '" + name + "';";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt("acc_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        new Role(resultSet.getInt("r_id"), resultSet.getString("name")),
                        resultSet.getString("picture_url")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOGGER.warn("SQL: " + sql);
            LOGGER.warn("Failed execute get query", e);
            return null;
        }
    }

    @Override
    public List<Account> getAll() {
        String sql;
        try {
            Statement statement = connection.createStatement();
            sql = "SELECT acc.id as acc_id, acc.username, acc.password, acc.email, acc.picture_url, r.id as r_id, r.name " +
                    "FROM account as acc " +
                    "   LEFT JOIN role_of_user as r " +
                    "       ON acc.role_id = r.id;";
            ResultSet resultSet = statement.executeQuery(sql);

            List<Account> users = new ArrayList<>();

            while (resultSet.next()) {
                Account user = new Account(
                        resultSet.getInt("acc_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        new Role(resultSet.getInt("r_id"), resultSet.getString("name")),
                        resultSet.getString("picture_url")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOGGER.warn("Failed execute get all query", e);
            return List.of();
        }
    }

    @Override
    public void update(Account account) {
        String sql1 = null;
        String sql2 = null;

        try {
            sql1 = "SELECT id FROM role_of_user WHERE name = '" + account.getRole().getName() + "';";
            ResultSet resultSet = connection.createStatement().executeQuery(sql1);
            int role_id = 0;
            if (resultSet.next()) {
                role_id = resultSet.getInt("id");
            }
            account.getRole().setId(role_id);

            sql1 = generateUpdateQuery(account);
            connection.createStatement().executeQuery(sql1);

            LOGGER.warn("SQL1: " + sql1);
            LOGGER.warn("SQL2: " + sql2);
        } catch (SQLException e) {

            LOGGER.warn("Failed execute update query", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = null;
        try {
            sql = "DELETE FROM account WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("SQL: " + sql);
            LOGGER.warn("Failed execute delete query", e);
        }
    }


    private String generateUpdateQuery(Account account) throws SQLException {
        String firstPart = "UPDATE account SET ";
        String lastPart = " WHERE id = " + account.getId() + ";";

        StringBuilder columnNames = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");

        if (account.getRole() != null && account.getRole().getId() != 0) {
            columnNames.append("role_id, ");
            values.append(account.getRole().getId()).append(", ");
        }
        if (account.getUsername() != null) {
            columnNames.append("username, ");
            values.append("'").append(account.getUsername()).append("'").append(", ");
        }
        if (account.getEmail() != null) {
            columnNames.append("email, ");
            values.append("'").append(account.getEmail()).append("'").append(", ");
        }
        if (account.getPassword() != null) {
            columnNames.append("password, ");
            values.append("'").append(account.getPassword()).append("'").append(", ");
        }

        if (account.getPictureURL() != null) {
            columnNames.append("picture_url, ");
            values.append("'").append(account.getPictureURL()).append("'").append(", ");
        }
        columnNames.delete(columnNames.length() - 2, columnNames.length() - 1);
        values.delete(values.length() - 2, values.length() - 1);
        columnNames.append(")");
        values.append(")");

        return firstPart + columnNames + " = " + values + lastPart;
    }
}
