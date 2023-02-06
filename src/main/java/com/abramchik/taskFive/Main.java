package com.abramchik.taskFive;

import com.abramchik.taskFive.service.BucketService;
import com.abramchik.taskFive.service.BucketServiceImpl;
import com.abramchik.taskFive.service.ProductService;
import com.abramchik.taskFive.service.ProductServiceImpl;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);
    private static final BucketService bucketService = new BucketServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();
    private static boolean isWork = true;
    private static int number;

    public static void main(String[] args) {
        while (isWork) {

            System.out.println("1. Show product list");
            System.out.println("2. Show products in the bucket");
            System.out.println("3. Clear the bucket");
            System.out.println("0. Exit");

            Scanner in = new Scanner(System.in);

            switch (in.nextInt()) {
                case 1:
                    System.out.println(productService.showCatalog());
                    System.out.println("Choose product to add to cart or press 0 to go back: ");

                    while ((number = in.nextInt()) != 0) {
                        bucketService.addProductToTheBucket(number);
                    }
                    break;

                case 2:
                    System.out.println(bucketService.showProductsInTheBucket());
                    System.out.println("Choose product to delete from cart or press 0 to go back: ");

                    while ((number = in.nextInt()) != 0) {
                        bucketService.deleteProductFromTheBucket(number);
                    }
                    break;

                case 3:
                    bucketService.clearBucket();
                    break;

                case 0:
                    isWork = false;
                    break;

                default:
                    log.info("Wrong number!");
            }
        }
    }
}