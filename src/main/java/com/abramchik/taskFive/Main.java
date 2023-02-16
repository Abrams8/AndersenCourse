package com.abramchik.taskFive;

import com.abramchik.taskFive.entity.Order;
import com.abramchik.taskFive.entity.Product;
import com.abramchik.taskFive.entity.User;
import com.abramchik.taskFive.entity.UserHistoryOfPurchasing;
import com.abramchik.taskFive.service.*;
import com.abramchik.taskFive.service.impl.*;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);
    private static final BucketService bucketService = new BucketServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();
    private static final WarehouseService warehouseService = new WarehouseServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();
    private static boolean isWork = true;

    public static void main(String[] args) {

        bucketService.readBucketFromFile();

        Order order = new Order();
        Scanner in = new Scanner(System.in);

        System.out.println("Write your name:");
        String name = in.next();
        System.out.println("Write your surname: ");
        String surname = in.next();
        User user = new User(name, surname);
        User currentUser = userService.getUserByNameAndSurname(user);
        if (currentUser == null) {
            userService.createNewUser(user);
            user = userService.getUserByNameAndSurname(user);
        } else {
            user = currentUser;
        }
        System.out.println(user);

        while (isWork) {

            System.out.println("1. Show product list");
            System.out.println("2. Show products in the bucket");
            System.out.println("3. Clear the bucket");
            System.out.println("4. Add products to the warehouse");
            System.out.println("5. Delete products from the warehouse");
            System.out.println("6. Make order");
            System.out.println("7. Confirme order");
            System.out.println("8. Call stored procedure");
            System.out.println("0. Exit");

            int number;
            switch (in.nextInt()) {
                case 1:
                    System.out.println(productService.showCatalog());
                    System.out.println("Choose product to add to cart or press 0 to go back: ");

                    while ((number = in.nextInt()) != 0) {
                        bucketService.addProductToTheBucket(number);
                        Product product = productService.chooseProduct(number);
                        bucketService.addProductToBucketDB(product, user);
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
                    if (number == 0) {
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
                    if (number == 0) {
                        break;
                    }
                    if (productService.chooseProduct(number) == null) {
                        log.error("Wrong product number!");
                        break;
                    }
                    System.out.println("Quantity: ");
                    warehouseService.deleteProductFromWarehouse(productService.chooseProduct(number), in.nextInt());
                    break;

                case 6:
                    List<Product> bucket = bucketService.showProductsInTheBucket();
                    if(bucket.size() == 0){
                        log.error("bucket is empty!");
                        break;
                    }
                    order.setBucketId(user.getId());
                    BigDecimal sum = BigDecimal.valueOf(0);
                    for (int i = 0; i < bucket.size(); i++) {
                        sum = sum.add(bucket.get(i).getPrice());
                    }
                    order.setSum(sum);
                    order.setDate(Date.valueOf(LocalDate.now().toString()));
                    orderService.makeOrder(order);
                    break;
                case 7:
                    if(order.getBucketId() != 0) {
                        orderService.confirmeOrder(order);
                    }else {
                        log.error("empty bucket!");
                    }
                    break;
                case 8:
                    List<UserHistoryOfPurchasing> list;
                    list = userService.callStoresProcedure(user.getId());
                    System.out.println(list);
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