package com.shop.userservice.service;

import com.shop.bucketservice.entity.Bucket;
import com.shop.userservice.entity.Users;
import com.shop.userservice.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaBucketConsumer {

    UserService userService = new UserServiceImpl();
    Logger log = Logger.getLogger(KafkaBucketConsumer.class);

    @KafkaListener(topics = "makeOrder", groupId = "users")
    public void userMadeOrder(Bucket bucket) {
        Users user = userService.getUserById(bucket.getBucketId());
        log.info("User " + user + " made order.");
    }

    @KafkaListener(topics = "addProduct", groupId = "users")
    public void userAddedProductToTheBucket(Bucket bucket) {
        Users user = userService.getUserById(bucket.getBucketId());
        log.info("User " + user + " has added product with id = " + bucket.getProductId());
    }

    @KafkaListener(topics = "delete", groupId = "users")
    public void userDeleteProductFromTheBucket(Bucket bucket) {
        Users user = userService.getUserById(bucket.getBucketId());
        log.info("User " + user + " has deleted product with id = " + bucket.getProductId());
    }

    @KafkaListener(topics = "clear", groupId = "users")
    public void userClearBucket(Bucket bucket) {
        Users user = userService.getUserById(bucket.getBucketId());
        log.info("User " + user + " has cleared bucket.");
    }


}
