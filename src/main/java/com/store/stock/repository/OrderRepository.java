package com.store.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.stock.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	Optional<Order> findByOtpValue(Integer otpValue);
}
