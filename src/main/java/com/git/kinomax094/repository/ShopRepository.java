package com.git.kinomax094.repository;

import com.git.kinomax094.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {



    // native
//    @Query(value = "select a.name from Shop a inner join  Product_Sold b ON a.id = b.shop_id where b.price > :price" , nativeQuery = true)
//    public List<String>  findShopWithProductSoldOverPrice(Double price);
//
//    @Query(value = "select a from Shop a inner join  ProductSold b ON a.id = b.shop where b.price > :price")
//    public List<Shop>  findShopWithProductSoldOverPrice(Double price);


    @Query(value = "select b from ProductSold a join  a.shop b  where a.price > :price")
    public List<Shop>  findShopWithProductSoldOverPrice(Double price);



}
