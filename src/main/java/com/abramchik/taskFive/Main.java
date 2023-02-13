package com.abramchik.taskFive;

import com.abramchik.taskFive.service.*;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);
    private static final BucketService bucketService = new BucketServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();
    private static final WarehouseService warehouseService = new WarehouseServiceImpl();
    private static boolean isWork = true;

    public static void main(String[] args) {

        bucketService.readBucketFromFile();

        while (isWork) {

            System.out.println("1. Show product list");
            System.out.println("2. Show products in the bucket");
            System.out.println("3. Clear the bucket");
            System.out.println("4. Add products to the warehouse");
            System.out.println("5. Delete products from the warehouse");
            System.out.println("0. Exit");

            Scanner in = new Scanner(System.in);

            int number;
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

                case 4:
                    System.out.println(productService.showCatalog());
                    System.out.println("Choose product to add to warehouse or press 0 to go back: ");
                    number = in.nextInt();
                    if (number == 0){
                        break;
                    }
                    if (productService.chooseProduct(number) == null) {
                        log.error("Wrong product number!");
                        break;
                    }
                    System.out.println("Quantity: ");
                    warehouseService.addProductToWarehouse(productService.chooseProduct(number), in.nextInt());
                    break;

                case 5:
                    warehouseService.showProductsInTheWarehouse();
                    System.out.println("Choose product to delete from warehouse or press 0 to go back: ");

                    number = in.nextInt();
                    if (number == 0){
                        break;
                    }
                    if (productService.chooseProduct(number) == null) {
                        log.error("Wrong product number!");
                        break;
                    }
                    System.out.println("Quantity: ");
                    warehouseService.deleteProductFromWarehouse(productService.chooseProduct(number), in.nextInt());
                    break;
                case 0:
                    bucketService.saveBucketToFile();
                    isWork = false;
                    break;

                default:
                    log.info("Wrong number!");
            }
        }
    }
}