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

    @Autowired
    BucketService bucketService = new BucketServiceImpl();

    @GetMapping
    public ResponseEntity<List<Bucket>> getAllBuckets(@RequestHeader String role) {
        if(role.equals("USER")){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(bucketService.getAllBuckets());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductToBucket(@RequestBody Bucket bucket, @RequestHeader String role) {
        if(role.equals("USER")){
            bucketService.addProductToTheBucket(bucket);
            return ResponseEntity.ok("done");
        } else {
            return ResponseEntity.status(404).body("error");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProductFromBucket(@RequestBody Bucket bucket, @RequestHeader String role) {
        if(role.equals("USER")){
            bucketService.deleteProductFromTheBucket(bucket);
            return ResponseEntity.ok("done");
        } else {
            return ResponseEntity.status(404).body("error");
        }
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearBucket(@RequestParam int bucketId, @RequestHeader String role) {
        if(role.equals("USER")){
            bucketService.clearBucket(bucketId);
            return ResponseEntity.ok("done");
        } else {
            return ResponseEntity.status(404).body("error");
        }
    }

    @PostMapping("/makeOrder")
    public ResponseEntity<String> makeOrder(@RequestParam int bucketId, @RequestHeader String role) {
        if(role.equals("USER")){
            bucketService.makeOrder(bucketId);
            return ResponseEntity.ok("done");
        } else {
            return ResponseEntity.status(404).body("error");
        }
    }
}
