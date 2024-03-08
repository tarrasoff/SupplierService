package com.example.consumerservice.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class Category {

    private long id;

    private String name;
}