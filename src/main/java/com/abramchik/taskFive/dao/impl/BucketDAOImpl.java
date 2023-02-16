package com.abramchik.taskFive.dao.impl;

import com.abramchik.taskFive.dao.BucketDAO;
import com.abramchik.taskFive.dao.MyConnection;
import com.abramchik.taskFive.entity.Product;
import com.abramchik.taskFive.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BucketDAOImpl implements BucketDAO {

    private static final String ADD_PRODUCT_TO_BUCKET = "INSERT INTO shop.buckets(bucket_id, product_id) VALUES(?, ?);";

    @Override
    public boolean addProductToBucket(Product product, User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_PRODUCT_TO_BUCKET);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, product.getId());
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
        } return result;
    }
}
