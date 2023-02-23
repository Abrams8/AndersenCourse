package com.shop.bucketservice.controller;

import com.shop.bucketservice.entity.Bucket;
import com.shop.bucketservice.service.BucketService;
import com.shop.bucketservice.service.impl.BucketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buckets")
public class BucketController {

    @Autowired
    BucketService bucketService = new BucketServiceImpl();

    @GetMapping
    public List<Bucket> getAllBuckets(){
        return bucketService.getAllBuckets();
    }

    @PostMapping("/add")
    public boolean addProductToBucket(@RequestBody Bucket bucket){
        return bucketService.addProductToTheBucket(bucket);
    }

    @DeleteMapping("/delete")
    public boolean deleteProductFromBucket(@RequestBody Bucket bucket){
        return bucketService.deleteProductFromTheBucket(bucket);
    }

    @DeleteMapping("/clear")
    public boolean clearBucket(@RequestParam int bucketId){
        return bucketService.clearBucket(bucketId);
    }
}
