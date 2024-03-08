package com.example.consumerservice.service;

import com.example.consumerservice.model.Category;
import com.example.consumerservice.model.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerServiceTest {
    private final RestTemplate restTemplate;
    @Value("${supplier.api.url}")
    private String url;

    @PostConstruct
    public void init() {
        Product product = Product.builder()
                .name("Product")
                .description("Description")
                .price(100)
                .categoryId(1)
                .build();

        Category category = Category.builder()
                .name("Category")
                .build();

        restTemplate.postForObject(url, product, Void.class);
        log.info("Create product: {}", product);

        restTemplate.postForObject(url, category, Void.class);
        log.info("Create category: {}", category);

        Product getProduct = restTemplate.getForObject(url + "/" + 1L, Product.class);
        log.info("New product: {}", getProduct);

        List<Product> products = restTemplate.getForObject(url + "/all", List.class);
        log.info("All products: {}", products);

        List<Category> categories = restTemplate.getForObject(url + "/all", List.class);
        log.info("All categories: {}", categories);

        Product newProduct = Product.builder()
                .name("Product")
                .description("NewDescription")
                .price(100)
                .categoryId(1)
                .build();

        restTemplate.put(url + "/" + 1L, newProduct, Void.class);
        log.info("Update product: {}", newProduct);

        restTemplate.delete(url + "/" + 1L, Void.class);
        log.info("Delete product: {}", newProduct);

        List<Product> filteredProducts = restTemplate.getForObject(url + "/filtered?price=100&categoryId=1", List.class);
        log.info("Filtered products: {}", filteredProducts);

        List<Product> filteredProductsByNameAndDescription = restTemplate.getForObject(url + "/filterByNameAndDescription?name=Product&description=NewDescription", List.class);
        log.info("Filtered products: {}", filteredProductsByNameAndDescription);

        restTemplate.getForObject(url + "/products?page=0&size=10", Void.class);
        log.info("Get all products with pagination");
    }
}