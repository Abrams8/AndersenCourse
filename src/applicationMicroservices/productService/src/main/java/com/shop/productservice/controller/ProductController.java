package com.shop.productservice.controller;

import com.shop.productservice.entity.Food;
import com.shop.productservice.entity.NotFood;
import com.shop.productservice.entity.Product;
import com.shop.productservice.service.ProductService;
import com.shop.productservice.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService = new ProductServiceImpl();

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/get")
    public Product getProduct(@RequestParam int id){
        return productService.getProduct(id);
    }

    @PostMapping("/create/food")
    public void addNewFood(@RequestBody Food product){
        productService.addNewProduct(product);
    }

    @PostMapping("/create/notfood")
    public void addNewNotFood(@RequestBody NotFood product){
        productService.addNewProduct(product);
    }

    @GetMapping("/get/price")
    public BigDecimal getProductPrice(@RequestParam int productId){
        return productService.getProductPrice(productId);
    }
}
