package com.shop.bucketservice.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bucket {

    int bucketId;
    int productId;

    public Bucket(int bucketId, int productId) {
        this.bucketId = bucketId;
        this.productId = productId;
    }

    public Bucket(int bucketId) {
        this.bucketId = bucketId;
    }
}
