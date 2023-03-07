package com.shop.productservice.controller;

import com.shop.productservice.entity.Food;
import com.shop.productservice.entity.NotFood;
import com.shop.productservice.entity.Product;
import com.shop.productservice.service.ProductService;
import com.shop.productservice.service.impl.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    ProductService productService = new ProductServiceImpl();

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/get")
    public ResponseEntity<Product> getProduct(@RequestParam int id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping("/create/food")
    public ResponseEntity<String> addNewFood(@RequestBody Food product, @RequestHeader String role){
        if (role.equals("USER")){
            return ResponseEntity.status(404).body("error");
        }
        productService.addNewProduct(product);
        return ResponseEntity.status(200).body("done");
    }

    @PostMapping("/create/notfood")
    public ResponseEntity<String> addNewNotFood(@RequestBody NotFood product, @RequestHeader String role){
        if (role.equals("USER")){
            return ResponseEntity.status(404).body("error");
        }
        productService.addNewProduct(product);
        return ResponseEntity.status(200).body("done");
    }

    @GetMapping("/get/price")
    public ResponseEntity<BigDecimal> getProductPrice(@RequestParam int productId){
        return ResponseEntity.ok(productService.getProductPrice(productId));
    }
}
