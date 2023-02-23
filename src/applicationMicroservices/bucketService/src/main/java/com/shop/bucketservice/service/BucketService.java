package com.shop.bucketservice.service;

import com.shop.bucketservice.entity.Bucket;

import java.util.List;

public interface BucketService {

    List<Bucket> getAllBuckets();

    boolean addProductToTheBucket(Bucket bucket);

    boolean deleteProductFromTheBucket(Bucket bucket);

    boolean clearBucket(int bucketId);
}
