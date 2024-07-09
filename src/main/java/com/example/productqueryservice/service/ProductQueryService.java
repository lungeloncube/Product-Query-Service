package com.example.productqueryservice.service;

import com.example.productqueryservice.dto.ProductEvent;
import com.example.productqueryservice.entity.Product;
import com.example.productqueryservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepository repository;
    public List<Product> getProducts(){
        return repository.findAll();
    }

    @KafkaListener(topics="product-event-topic", groupId = "product-event-group")
    public void processProductEvent(ProductEvent productEvent){

        if(productEvent.getEventType().equals("CreateProduct")){
            repository.save(productEvent.getProduct());
        }

        if(productEvent.getEventType().equals("UpdateProduct")){
            Product existingProduct=repository.findById(productEvent.getProduct().getId()).get();
            existingProduct.setName(productEvent.getProduct().getName());
            existingProduct.setDescription(productEvent.getProduct().getDescription());
            existingProduct.setPrice(productEvent.getProduct().getPrice());
            repository.save(existingProduct);
        }

    }

}
