package com.aegis.ultima.repository;

import com.aegis.ultima.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findProductById(String id);

    Product findProductByCode(String code);

}
