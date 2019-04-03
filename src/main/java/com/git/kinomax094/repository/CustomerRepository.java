package com.git.kinomax094.repository;

import com.git.kinomax094.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "Select c FROM Customer c where c.name like :letter%")
    List<Customer> findByNameWhereNameStartWithLetter(String letter);


    public List<String> show(@Param(value = "shopName") String shopName,@Param(value = "customerName") String customerName);


}
