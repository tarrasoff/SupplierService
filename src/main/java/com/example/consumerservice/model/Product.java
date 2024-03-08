package com.example.consumerservice.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class Product {

    private long id;

    private String name;

    private String description;

    private double price;

    private long categoryId;
}