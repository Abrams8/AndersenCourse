package com.shop.userservice.dao.impl;

import com.shop.userservice.dao.MyConnection;
import com.shop.userservice.dao.UserDAO;
import com.shop.userservice.entity.User;
import com.shop.userservice.entity.UserHistoryOfPurchasing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String CREATE_NEW_USER = "INSERT INTO users(user_name, user_surname) VALUES (?,?);";
    private static final String GET_USER_BY_NAME_AND_SURNAME = "SELECT * FROM users WHERE user_name = ? and user_surname = ?;";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?;";
    private static final String STORED_PROCEDURE = "call TradesInfo(?);";
    private static final String GET_ALL_USERS = "SELECT * FROM users;";

    @Override
    public boolean createNewUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CREATE_NEW_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public User getUserByNameAndSurname(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User result = null;
        try {
            connection = MyConnection.getRealConnection();
            preparedStatement = connection.prepareStatement(GET_USER_BY_NAME_AND_SURNAME);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new User();
                result.setId(resultSet.getInt("user_id"));
                result.setName(resultSet.getString("user_name"));
                result.setSurname(resultSet.getString("user_surname"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return result;
        }
    }

    @Override
    public User getUserById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = MyConnection.getRealConnection();
            preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setSurname(resultSet.getString("user_surname"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return user;
        }
    }

    @Override
    public List<UserHistoryOfPurchasing> callStoredProcedureTradesInfoByUserId(int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<UserHistoryOfPurchasing> list = new ArrayList<>();
        try {
            connection = MyConnection.getRealConnection();
            preparedStatement = connection.prepareStatement(STORED_PROCEDURE);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserHistoryOfPurchasing userHistoryOfPurchasing = new UserHistoryOfPurchasing();
                userHistoryOfPurchasing.setOrderId(resultSet.getInt("orders_id"));
                userHistoryOfPurchasing.setTradeDate(resultSet.getDate("date"));
                userHistoryOfPurchasing.setOrderSum(resultSet.getBigDecimal("sum"));
                list.add(userHistoryOfPurchasing);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<User> list = new ArrayList<>();
        try {
            connection = MyConnection.getRealConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setSurname(resultSet.getString("user_surname"));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
