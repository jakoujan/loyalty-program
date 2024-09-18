package com.bimbo.lo.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(name = "product_name", unique = true, nullable = false)
    private String name;
    @Column(name = "reward_point", unique = true, nullable = false)
    private Integer points;
}
