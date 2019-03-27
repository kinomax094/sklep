package com.git.kinomax094.repository;

import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
