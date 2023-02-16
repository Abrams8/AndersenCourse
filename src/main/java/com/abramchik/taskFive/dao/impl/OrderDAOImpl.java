package com.abramchik.taskFive.dao.impl;

import com.abramchik.taskFive.dao.MyConnection;
import com.abramchik.taskFive.dao.OrderDAO;
import com.abramchik.taskFive.entity.Order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDAOImpl implements OrderDAO {

    private static final String MAKE_ORDER = "INSERT INTO shop.orders(processed, date, buckets_bucket_id, sum) VALUES(?,?,?,?);";
    private static final String CONFIRME_ORDER = "UPDATE shop.orders SET processed = true WHERE buckets_bucket_id = ?;";

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
    public boolean confirmeOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CONFIRME_ORDER);
            preparedStatement.setInt(1, order.getBucketId());
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
}
