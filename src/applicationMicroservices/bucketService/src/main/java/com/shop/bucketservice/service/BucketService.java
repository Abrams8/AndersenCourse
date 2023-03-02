package com.shop.bucketservice.service;

import com.shop.bucketservice.entity.Bucket;

import java.util.List;

public interface BucketService {

    List<Bucket> getAllBuckets();

    void addProductToTheBucket(Bucket bucket);

    void deleteProductFromTheBucket(Bucket bucket);

    void clearBucket(int bucketId);

    void makeOrder(int bucketId);
}
