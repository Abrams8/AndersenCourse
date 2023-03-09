package com.abramchik.taskFive.service;

import com.abramchik.taskFive.entity.Product;
import com.abramchik.taskFive.entity.User;

import java.util.List;

public interface BucketService {

    List<Product> showProductsInTheBucket();

    boolean addProductToTheBucket(int id);

    boolean deleteProductFromTheBucket(int id);

    boolean clearBucket();

    int getBucketSize();

    void readBucketFromFile();

    void saveBucketToFile();

    boolean addProductToBucketDB(Product product, User user);

}
