package com.abramchik.taskFive.service.impl;

import com.abramchik.taskFive.entity.Food;
import com.abramchik.taskFive.entity.NotFood;
import com.abramchik.taskFive.entity.Product;
import com.abramchik.taskFive.entity.currency.Currency;
import com.abramchik.taskFive.entity.currency.InternationalCode;
import com.abramchik.taskFive.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    List<Product> productList = new ArrayList<>();
    Currency usd = new Currency(InternationalCode.USD);
    Currency eur = new Currency(InternationalCode.EUR);
    Currency uah = new Currency(InternationalCode.UAH);

    public ProductServiceImpl() {
        addProducts();
    }

    @Override
    public List<Product> showCatalog() {
        return productList;
    }

    @Override
    public Product chooseProduct(int id) {
        return findProduct(id);
    }

    private Product findProduct(int id) {
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (id == product.getId()) {
                return product;
            }
        }
        return null;
    }

    private void addProducts() {
        productList.add(new Food(1, "Banana", BigDecimal.valueOf(22.65), false, uah));
        productList.add(new Food(2, "Orange", BigDecimal.valueOf(2.2), false, usd));
        productList.add(new Food(3, "Chips", BigDecimal.valueOf(1.5), true, eur));
        productList.add(new Food(4, "Coffee", BigDecimal.valueOf(33.0), false, uah));
        productList.add(new Food(9, "Milk", BigDecimal.valueOf(1.2), false, usd));


        productList.add(new NotFood(5, "TV", BigDecimal.valueOf(160), 24, usd));
        productList.add(new NotFood(6, "Phone", BigDecimal.valueOf(400), 12, eur));
        productList.add(new NotFood(7, "Vacuum cleaner", BigDecimal.valueOf(840.3), 12, uah));
        productList.add(new NotFood(8, "Printer", BigDecimal.valueOf(150), 6, usd));
    }

    @Override
    public String toString() {
        return "Catalog: " + productList;
    }
}
