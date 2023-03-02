package com.shop.productservice.dao.impl;

import com.shop.productservice.dao.MyConnection;
import com.shop.productservice.dao.ProductDAO;
import com.shop.productservice.entity.Food;
import com.shop.productservice.entity.NotFood;
import com.shop.productservice.entity.Product;
import com.shop.productservice.entity.currency.Currency;
import com.shop.productservice.entity.currency.InternationalCode;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private static final String ADD_NEW_PRODUCT = "INSERT INTO shop.products(product_id, name, price, currency, exp_days, food_food_id, notfood_notfood_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM shop.products left join shop.foods ON food_food_id = food_id left join shop.notfoods ON notfood_notfood_id = notfood_id where product_id = ?;";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM shop.products left join shop.foods ON food_food_id = food_id left join shop.notfoods ON notfood_notfood_id = notfood_id;";
    private static final String GET_PRODUCT_PRICE = "SELECT price FROM products WHERE product_id = ?;";

    @Override
    public boolean addProduct(Product product) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result;
        try {
            connection = MyConnection.getRealConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_NEW_PRODUCT);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setBigDecimal(3, product.getPrice());
            System.out.println(product);
            System.out.println(product.getCurrency().getName());
            preparedStatement.setString(4, product.getCurrency().getCode().toString());
            if (product instanceof Food) {
                preparedStatement.setInt(5, product.getExpDays());
                Food food = (Food) product;
                if (food.isIncludesGMO()) {
                    preparedStatement.setInt(6, 1);
                } else {
                    preparedStatement.setInt(6, 2);
                }
                preparedStatement.setString(7, null);
            } else {
                NotFood notFood = (NotFood) product;
                switch (notFood.getWarrantyPeriod()) {
                    case 6:
                        preparedStatement.setInt(7, 1);
                        break;
                    case 12:
                        preparedStatement.setInt(7, 2);
                        break;
                    case 24:
                        preparedStatement.setInt(7, 3);
                        break;
                }
                preparedStatement.setString(5, null);
                preparedStatement.setString(6, null);
            }
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
    public Product getProduct(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;
        try {
            connection = MyConnection.getRealConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("exp_days") != 0) {
                    product = new Food();
                    product.setExpDays(resultSet.getInt("exp_days"));
                    ((Food) product).setIncludesGMO(resultSet.getBoolean("includesGMO"));
                } else {
                    product = new NotFood();
                    ((NotFood) product).setWarrantyPeriod(resultSet.getInt("warrantyPeriod"));

                }
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                switch (resultSet.getString("currency")) {
                    case "usd":
                        product.setCurrency(new Currency(InternationalCode.USD));
                        break;
                    case "uah":
                        product.setCurrency(new Currency(InternationalCode.UAH));
                        break;
                    case "eur":
                        product.setCurrency(new Currency(InternationalCode.EUR));
                        break;
                }
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
            return product;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Product product = null;
        List<Product> list = new ArrayList<>();
        try {
            connection = MyConnection.getRealConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_PRODUCTS);
            while (resultSet.next()) {
                if (resultSet.getInt("exp_days") != 0) {
                    product = new Food();
                    product.setExpDays(resultSet.getInt("exp_days"));
                    ((Food) product).setIncludesGMO(resultSet.getBoolean("includesGMO"));
                } else {
                    product = new NotFood();
                    ((NotFood) product).setWarrantyPeriod(resultSet.getInt("warrantyPeriod"));

                }
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                switch (resultSet.getString("currency")) {
                    case "usd":
                        product.setCurrency(new Currency(InternationalCode.USD));
                        break;
                    case "uah":
                        product.setCurrency(new Currency(InternationalCode.UAH));
                        break;
                    case "eur":
                        product.setCurrency(new Currency(InternationalCode.EUR));
                        break;
                }
                list.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public BigDecimal getProductPrice(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BigDecimal sum = null;
        try {
            connection = MyConnection.getRealConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCT_PRICE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sum = resultSet.getBigDecimal("price");
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
            return sum;
        }
    }
}