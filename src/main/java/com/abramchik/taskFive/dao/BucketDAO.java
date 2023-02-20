package com.abramchik.taskFive.dao;

import com.abramchik.taskFive.entity.Product;
import com.abramchik.taskFive.entity.User;

public interface BucketDAO {

    boolean addProductToBucket(Product product, User user);

}
