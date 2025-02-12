package com.bimbo.lo.data.repository;

import com.bimbo.lo.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByCode(String productCode);
}
