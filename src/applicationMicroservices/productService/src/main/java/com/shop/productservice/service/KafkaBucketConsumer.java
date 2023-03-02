package com.shop.productservice.service;

import com.shop.bucketservice.entity.Bucket;
import com.shop.productservice.entity.Product;
import com.shop.productservice.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaBucketConsumer {

    ProductService productService = new ProductServiceImpl();
    Logger log = Logger.getLogger(KafkaBucketConsumer.class);

    @KafkaListener(topics = "addProduct", groupId = "products")
    public void userAddedProductToTheBucket(Bucket bucket) {
        Product product = productService.getProduct(bucket.getProductId());
        log.info("User with id " + bucket.getBucketId() + " has added product: " + product);
    }

    @KafkaListener(topics = "delete", groupId = "products")
    public void userDeleteProductFromTheBucket(Bucket bucket) {
        Product product = productService.getProduct(bucket.getProductId());
        log.info("User with id " + bucket.getBucketId() + " has deleted product: " + product);
    }
}
