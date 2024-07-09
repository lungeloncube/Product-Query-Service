package com.example.productqueryservice.controller;
import com.example.productqueryservice.entity.Product;
import com.example.productqueryservice.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    private ProductQueryService service;

    @GetMapping
    public List<Product> getProducts(){
        return service.getProducts();
    }

    public void processProductEvent(){}
}
