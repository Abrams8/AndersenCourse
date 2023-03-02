package com.shop.ordersercice.dao.impl;

import com.shop.ordersercice.dao.MyConnection;
import com.shop.ordersercice.dao.OrderDAO;
import com.shop.ordersercice.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static final String MAKE_ORDER = "INSERT INTO shop.orders(processed, date, buckets_bucket_id, sum) VALUES(?,?,?,?);";
    private static final String CONFIRME_ORDER = "UPDATE shop.orders SET processed = true WHERE orders_id = ?;";
    private static final String GET_ALL_ORDERS = "SELECT * FROM orders;";

    @Override
    public boolean makeOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(MAKE_ORDER);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setDate(2, (Date)order.getDate());
            preparedStatement.setInt(3, order.getBucketId());
            preparedStatement.setBigDecimal(4, order.getSum());
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
    public boolean confirmeOrder(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CONFIRME_ORDER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
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
    public List<Order> getAllOrders(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Order order = null;
        List<Order> list = new ArrayList<>();
        try {
            connection = MyConnection.getRealConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ORDERS);
            while (resultSet.next()) {
                order = new Order(resultSet.getInt("buckets_bucket_id"), resultSet.getBigDecimal("sum"));
                order.setId(resultSet.getInt("orders_id"));
                order.setProcessed(resultSet.getBoolean("processed"));
                order.setDate(resultSet.getDate("date"));
                list.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
