package com.shop.bucketservice.service.impl;

import com.shop.bucketservice.dao.BucketDAO;
import com.shop.bucketservice.dao.impl.BucketDAOImpl;
import com.shop.bucketservice.entity.Bucket;
import com.shop.bucketservice.service.BucketService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BucketServiceImpl implements BucketService {

    BucketDAO bucketDAO = new BucketDAOImpl();

    @Override
    public List<Bucket> getAllBuckets() {
        return bucketDAO.getAllBuckets();
    }

    @Override
    public boolean addProductToTheBucket(Bucket bucket) {
        return bucketDAO.addProductToTheBucket(bucket);
    }

    @Override
    public boolean deleteProductFromTheBucket(Bucket bucket) {
        return bucketDAO.deleteProductFromTheBucket(bucket);
    }

    @Override
    public boolean clearBucket(int bucketId) {
        return bucketDAO.clearBucket(bucketId);
    }
}