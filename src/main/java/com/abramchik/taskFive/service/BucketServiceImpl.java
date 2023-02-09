package com.abramchik.taskFive.service;

import com.abramchik.taskFive.entity.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BucketServiceImpl implements BucketService {

    static final Logger log = Logger.getLogger(BucketServiceImpl.class);

    List<Product> bucketList = new ArrayList<>();
    ProductService productService = new ProductServiceImpl();
    int bucketSize = 0;

    @Override
    public List<Product> showProductsInTheBucket() {
        return bucketList;
    }

    @Override
    public boolean deleteProductFromTheBucket(int id) {
        if (bucketSize == 0) {
            log.info("Cart is empty!");
            return false;
        }
        Product product = findProductInTheBucket(id);
        if (product == null) {
            log.info("Product with ID = " + id + " isn't in the cart!");
            return false;
        } else {
            bucketList.remove(product);
            bucketSize--;
            log.info("Product with ID = " + id + " has been deleted from the cart!");
            return true;
        }
    }

    private Product findProductInTheBucket(int id) {
        for (Product product : bucketList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean addProductToTheBucket(int id) {
        Product product = productService.chooseProduct(id);
        if (product == null) {
            log.info("Wrong product number!");
            return false;
        } else {
            product.setPrice(product.getPrice()
                    .multiply(BigDecimal.valueOf(product.getCurrency().getCourse()))
                    .multiply(BigDecimal.valueOf(product.getCurrency().getMultiplier())));
            product.getCurrency().setUAHCurrency();
            bucketList.add(product);
            bucketSize++;
            log.info(product.getName() + " has been added to the bucket!");
            return true;
        }
    }

    @Override
    public boolean clearBucket() {
        if (bucketSize == 0) {
            log.info("Cart is empty!");
            return false;
        } else {
            bucketList.clear();
            bucketSize = 0;
            log.info("Successful cleaning! Cart is empty!");
            return true;
        }
    }

    @Override
    public int getBucketSize() {
        return bucketSize;
    }

    @Override
    public String toString() {
        return "Products in the bucket: \n" + bucketList;
    }

    @Override
    public void saveBucketToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\bucket.txt"));
            oos.writeObject(bucketList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readBucketFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\bucket.txt"));
            this.bucketList = (List<Product>) ois.readObject();
            this.bucketSize = bucketList.size();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.info("Bucket not found ");
        }
    }
}
