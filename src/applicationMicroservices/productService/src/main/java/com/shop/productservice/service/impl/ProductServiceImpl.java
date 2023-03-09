package com.shop.productservice.service.impl;

import com.shop.productservice.dao.ProductDAO;
import com.shop.productservice.dao.impl.ProductDAOImpl;
import com.shop.productservice.entity.Product;
import com.shop.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public void addNewProduct(Product product) {
        productDAO.addProduct(product);
    }

    @Override
    public Product getProduct(int id) {
        return productDAO.getProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public BigDecimal getProductPrice(int id) {
        return productDAO.getProductPrice(id);
    }
}


