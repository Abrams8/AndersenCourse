package com.abramchik.shop;

import com.abramchik.taskFive.entity.NotFood;
import com.abramchik.taskFive.entity.Product;
import com.abramchik.taskFive.entity.currency.Currency;
import com.abramchik.taskFive.entity.currency.InternationalCode;
import com.abramchik.taskFive.service.BucketService;
import com.abramchik.taskFive.service.BucketServiceImpl;
import com.abramchik.taskFive.service.ProductService;
import com.abramchik.taskFive.service.ProductServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopTest {

    BucketService bucketService = new BucketServiceImpl();
    ProductService productService = new ProductServiceImpl();
    Product product = new Product();

    @Before
    public void initial() {
        product = new NotFood(8, "Printer", BigDecimal.valueOf(150.9), 6, new Currency(InternationalCode.USD));
    }

    @Test
    public void chooseProductTest() {
        Assert.assertEquals(product, productService.chooseProduct(8));
    }

    @Test
    public void deleteProductFromTheBucketTest() {
        bucketService.addProductToTheBucket(product.getId());
        bucketService.addProductToTheBucket(product.getId());
        Assert.assertEquals(2, bucketService.getBucketSize());
        bucketService.deleteProductFromTheBucket(product.getId());
        Assert.assertEquals(1, bucketService.getBucketSize());
        bucketService.deleteProductFromTheBucket(product.getId());
        Assert.assertEquals(0, bucketService.getBucketSize());
    }

    @Test
    public void addProductToTheBucketTest() {
        Assert.assertEquals(0, bucketService.getBucketSize());
        bucketService.addProductToTheBucket(3);
        bucketService.addProductToTheBucket(5);
        Assert.assertEquals(2, bucketService.getBucketSize());
        Assert.assertFalse(bucketService.addProductToTheBucket(555));
    }

    @Test
    public void clearBucketTest() {
        Assert.assertFalse(bucketService.clearBucket());
        bucketService.addProductToTheBucket(1);
        bucketService.addProductToTheBucket(2);
        bucketService.addProductToTheBucket(3);
        Assert.assertTrue(bucketService.clearBucket());
    }
}
