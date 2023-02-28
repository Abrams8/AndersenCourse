package com.shop.userservice.dao.impl;

import com.shop.userservice.dao.MyConnection;
import com.shop.userservice.dao.UserDAO;
import com.shop.userservice.entity.UserHistoryOfPurchasing;
import com.shop.userservice.entity.Users;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final String CREATE_NEW_USER = "INSERT INTO users(user_name, password) VALUES (?,?);";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?;";
    private static final String STORED_PROCEDURE = "call TradesInfo(?);";
    private static final String GET_ALL_USERS = "SELECT * FROM users;";
    private static final String FIND_USER_BY_USER_NAME = "SELECT * FROM shop.users WHERE user_name = ?;";

    @Override
    public boolean createNewUser(Users user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CREATE_NEW_USER);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
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
    public Users getUserById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Users user = null;
        try {
            connection = MyConnection.getRealConnection();
            preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
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
    public List<Users> getAllUsers() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Users> list = new ArrayList<>();
        try {
            connection = MyConnection.getRealConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()){
                Users user = new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Users findUserByUserName(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Users user = null;
        try {
            connection = MyConnection.getRealConnection();
            preparedStatement = connection.prepareStatement(FIND_USER_BY_USER_NAME);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = findUserByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
