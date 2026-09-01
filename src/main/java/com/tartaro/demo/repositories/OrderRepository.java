package com.tartaro.demo.repositories;

import com.tartaro.demo.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderItem,Long> {
}
