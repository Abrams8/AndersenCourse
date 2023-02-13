package com.abramchik.taskFive.service;

import com.abramchik.taskFive.entity.ExpireDate;
import com.abramchik.taskFive.entity.Food;
import com.abramchik.taskFive.entity.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class WarehouseServiceImpl implements WarehouseService {

    final static int DEFAULT_EXPIRE_DATE = 15;
    final static int DEFAULT_ADDED_QUANTITY = 50;
    Logger log = Logger.getLogger(WarehouseServiceImpl.class);
    Map<Product, Integer> map = new LinkedHashMap<>();
    ProductService productService = new ProductServiceImpl();

    public WarehouseServiceImpl() {
        List<Product> productList = productService.showCatalog();
        for (Product product : productList) {
            setExpireDate(product, DEFAULT_EXPIRE_DATE);
            map.put(product, DEFAULT_ADDED_QUANTITY);
        }
    }

    @Override
    public boolean addProductToWarehouse(Product product, int quantity) {
        if (product instanceof Food) {
            setExpireDate(product, DEFAULT_EXPIRE_DATE);
        }
        if (map.containsKey(product)) {
            int currentQuantity = map.get(product);
            map.put(product, (currentQuantity + quantity));
        } else {
            map.put(product, quantity);
        }
        System.out.println(map.toString());
        return true;
    }

    private Product setExpireDate(Product product, int days) {
        Field[] fields = product.getClass().getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExpireDate.class)) {
                product.setExpDays(days);
            }
        }
        return product;
    }

    @Override
    public boolean deleteProductFromWarehouse(Product product, int quantity) {
        if (map.containsKey(product) == false) {
            log.error("The product is not in warehouse!");
            return false;
        }
        int currentQuantity = map.get(product);
        if (currentQuantity < quantity) {
            log.error("The quantity of products in stock is less than desired!");
            return false;
        } else {
            int newQuantity = currentQuantity - quantity;
            map.put(product, newQuantity);
            System.out.println(map.toString());
            return true;
        }
    }

    @Override
    public void showProductsInTheWarehouse() {
        Set<Map.Entry<Product, Integer>> set = map.entrySet();
        for (Map.Entry<Product, Integer> prod : set) {
            System.out.println(prod.getKey().toString());
            if (prod.getKey() instanceof Food) {
                System.out.println(" expDays: " + prod.getKey().getExpDays());
            }
            System.out.println(" quantity: " + prod.getValue().toString());
        }
    }
}
