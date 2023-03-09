package com.abramchik.taskFive.service;

import com.abramchik.taskFive.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> showCatalog();

    Product chooseProduct(int id);

}
