package com.git.kinomax094.repository;

import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldProductRepository extends JpaRepository<ProductSold, Integer> {
}
