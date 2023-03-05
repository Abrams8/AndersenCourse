package com.shop.bucketservice.controller;

import com.shop.bucketservice.entity.Bucket;
import com.shop.bucketservice.service.BucketService;
import com.shop.bucketservice.service.impl.BucketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buckets")
public class BucketController {

    @Autowired
    BucketService bucketService = new BucketServiceImpl();

    @GetMapping
    public List<Bucket> getAllBuckets() {
        return bucketService.getAllBuckets();
    }

    @PostMapping("/add")
    public void addProductToBucket(@RequestBody Bucket bucket) {
        bucketService.addProductToTheBucket(bucket);
    }

    @DeleteMapping("/delete")
    public void deleteProductFromBucket(@RequestBody Bucket bucket) {
        bucketService.deleteProductFromTheBucket(bucket);
    }

    @DeleteMapping("/clear")
    public void clearBucket(@RequestParam int bucketId) {
        bucketService.clearBucket(bucketId);
    }

    @PostMapping("/makeOrder")
    public void makeOrder(@RequestParam int bucketId) {
        bucketService.makeOrder(bucketId);
    }
}
