package com.example.cyber.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "products")
@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(sequenceName = "product_seq", name = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Long id;

    private String name;

    private String description;

}
