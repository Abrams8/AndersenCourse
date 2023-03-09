package com.abramchik.taskFive.service;

import com.abramchik.taskFive.entity.Product;

public interface WarehouseService {

    boolean addProductToWarehouse(Product product, int quantity);

    boolean deleteProductFromWarehouse(Product product, int quantity);

    void showProductsInTheWarehouse();

}
