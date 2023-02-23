package com.shop.bucketservice.dao;

import com.shop.bucketservice.entity.Bucket;

import java.util.List;

public interface BucketDAO {

    List<Bucket> getAllBuckets();

    boolean addProductToTheBucket(Bucket bucket);

    boolean deleteProductFromTheBucket(Bucket bucket);

    boolean clearBucket(int bucketId);
}
