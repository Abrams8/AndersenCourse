package com.abramchik.taskFive.service;

import com.abramchik.taskFive.entity.Food;
import com.abramchik.taskFive.entity.NotFood;
import com.abramchik.taskFive.entity.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService{

    List<Product> productList = new ArrayList<>();

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

    private Product findProduct(int id){
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (id == product.getId()){
                return product;
            }
        } return null;
    }

    private void addProducts(){
        productList.add(new Food(1, "Banana", 22.65, false));
        productList.add(new Food(2, "Orange", 25.6, false));
        productList.add(new Food(3, "Chips", 10.2, true));
        productList.add(new Food(4, "Coffee", 33.0, false));

        productList.add(new NotFood(5, "TV", 160.0, 24));
        productList.add(new NotFood(6, "Phone", 400.0, 12));
        productList.add(new NotFood(7, "Vacuum cleaner", 200.99, 12));
        productList.add(new NotFood(8, "Printer", 150.9, 6));
    }

    @Override
    public String toString() {
        return "Catalog: " + productList;
    }
}
