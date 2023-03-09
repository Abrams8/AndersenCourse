package com.shop.bucketservice.controller;

import com.shop.bucketservice.entity.Bucket;
import com.shop.bucketservice.service.BucketService;
import com.shop.bucketservice.service.impl.BucketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buckets")
public class BucketController {

    private static final String USER = "USER";

    @Autowired
    BucketService bucketService = new BucketServiceImpl();

    @GetMapping
    public ResponseEntity<List<Bucket>> getAllBuckets(@RequestHeader String role) {
        if(USER.equals(role)){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(bucketService.getAllBuckets());
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProductToBucket(@RequestBody Bucket bucket, @RequestHeader String role) {
        if(USER.equals(role)){
            bucketService.addProductToTheBucket(bucket);
            return ResponseEntity.ok("Product has been added to bucket");
        } else {
            return ResponseEntity.status(404).body("error: Product hasn't been added to bucket");
        }
    }

    @DeleteMapping("/product")
    public ResponseEntity<String> deleteProductFromBucket(@RequestBody Bucket bucket, @RequestHeader String role) {
        if(USER.equals(role)){
            bucketService.deleteProductFromTheBucket(bucket);
            return ResponseEntity.ok("Product has been removed from the bucket");
        } else {
            return ResponseEntity.status(404).body("error: Product hasn't been removed from the bucket");
        }
    }

    @DeleteMapping("/bucket")
    public ResponseEntity<String> clearBucket(@RequestParam int bucketId, @RequestHeader String role) {
        if(USER.equals(role)){
            bucketService.clearBucket(bucketId);
            return ResponseEntity.ok("Bucket has been emptied");
        } else {
            return ResponseEntity.status(404).body("error: Bucket hasn't been emptied");
        }
    }

    @PostMapping("/order")
    public ResponseEntity<String> makeOrder(@RequestParam int bucketId, @RequestHeader String role) {
        if(USER.equals(role)){
            bucketService.makeOrder(bucketId);
            return ResponseEntity.ok("Order has been placed");
        } else {
            return ResponseEntity.status(404).body("error: Order hasn't been placed");
        }
    }
}
