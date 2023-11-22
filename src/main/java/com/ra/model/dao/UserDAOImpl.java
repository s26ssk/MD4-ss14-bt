package com.ra.model.dao;

import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO {

    private static final String FIND_ALL_QUERY = "SELECT * FROM user";
    private static final String CREATE_QUERY = "INSERT INTO user (name, email, password, phone, role) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user SET name=?, email=?, password=?, phone=?, role=? WHERE id=?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM user WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE id=?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Boolean create(User user) {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {

            setPreparedStatementValues(preparedStatement, user);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(User user, Integer id) {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            setPreparedStatementValues(preparedStatement, user);
            preparedStatement.setInt(6, id); // Set the ID for the WHERE clause
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findId(Integer id) {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createUserFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createUserFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("phone"),
                Role.valueOf(resultSet.getString("role"))
        );
    }

    private void setPreparedStatementValues(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getPhone());
        preparedStatement.setString(5, user.getRole().name());
    }
}
