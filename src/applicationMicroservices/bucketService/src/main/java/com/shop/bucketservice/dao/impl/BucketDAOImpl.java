package com.shop.bucketservice.dao.impl;

import com.shop.bucketservice.dao.BucketDAO;
import com.shop.bucketservice.dao.MyConnection;
import com.shop.bucketservice.entity.Bucket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDAOImpl implements BucketDAO {

    private static final String ADD_PRODUCT_TO_BUCKET = "INSERT INTO shop.buckets(bucket_id, product_id) VALUES(?, ?);";
    private static final String GET_ALL_BUCKETS = "SELECT * FROM buckets;";
    private static final String DELETE_PRODUCT_FROM_ORDER = "DELETE FROM shop.buckets where bucket_id = ? and product_id = ?;";
    private static final String CLEAR_BUCKET = "DELETE FROM shop.buckets WHERE bucket_id = ?;";

    @Override
    public List<Bucket> getAllBuckets() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Bucket bucket = null;
        List<Bucket> list = new ArrayList<>();
        try {
            connection = MyConnection.getRealConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_BUCKETS);
            while (resultSet.next()) {
                bucket = new Bucket();
                bucket.setBucketId(resultSet.getInt("bucket_id"));
                bucket.setProductId(resultSet.getInt("product_id"));
                list.add(bucket);
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

    @Override
    public boolean addProductToTheBucket(Bucket bucket) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_PRODUCT_TO_BUCKET);
            preparedStatement.setInt(1, bucket.getBucketId());
            preparedStatement.setInt(2, bucket.getProductId());
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

    @Override
    public boolean deleteProductFromTheBucket(Bucket bucket) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_PRODUCT_FROM_ORDER);
            preparedStatement.setInt(1, bucket.getBucketId());
            preparedStatement.setInt(2, bucket.getProductId());
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

    @Override
    public boolean clearBucket(int bucketId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CLEAR_BUCKET);
            preparedStatement.setInt(1, bucketId);
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
