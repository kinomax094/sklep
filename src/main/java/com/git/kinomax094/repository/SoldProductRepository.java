package com.git.kinomax094.repository;

import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SoldProductRepository extends JpaRepository<ProductSold, Integer> {



    @Query(value = "Select a From ProductSold a where a.price > :number")
    public List<ProductSold> findProductWitBigestPrice(Double number);

}
