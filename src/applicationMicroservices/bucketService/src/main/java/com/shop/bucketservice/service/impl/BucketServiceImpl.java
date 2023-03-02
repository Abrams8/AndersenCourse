package com.shop.bucketservice.service.impl;

import com.shop.bucketservice.dao.BucketDAO;
import com.shop.bucketservice.dao.impl.BucketDAOImpl;
import com.shop.bucketservice.entity.Bucket;
import com.shop.bucketservice.service.BucketService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BucketServiceImpl implements BucketService {

    BucketDAO bucketDAO = new BucketDAOImpl();

    @Autowired
    KafkaTemplate<String, Bucket> kafkaTemplate;

    @Override
    public List<Bucket> getAllBuckets() {
        return bucketDAO.getAllBuckets();
    }

    @Override
    public void addProductToTheBucket(Bucket bucket) {
        kafkaTemplate.send("addProduct", bucket);
    }

    @Override
    public void deleteProductFromTheBucket(Bucket bucket) {
        kafkaTemplate.send("delete", bucket);
    }

    @Override
    public void clearBucket(int bucketId) {
        Bucket bucket = new Bucket();
        bucket.setBucketId(bucketId);
        kafkaTemplate.send("clear", bucket);
    }

    @Override
    public void makeOrder(int bucketId){
        Bucket bucket = new Bucket();
        bucket.setBucketId(bucketId);
        kafkaTemplate.send("makeOrder", bucket);
    }
}