package com.shop.productservice.service;

import com.shop.productservice.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void addNewProduct(Product product);

    Product getProduct(int id);

    List<Product> getAllProducts();

    BigDecimal getProductPrice(int id);

}
