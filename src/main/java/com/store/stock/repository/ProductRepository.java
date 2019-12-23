package com.store.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.stock.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
