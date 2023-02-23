package com.shop.productservice.dao;

import com.shop.productservice.entity.Product;

import java.util.List;

public interface ProductDAO {

    boolean addProduct(Product product);

    Product getProduct(int id);

    List<Product> getAllProducts();
}
