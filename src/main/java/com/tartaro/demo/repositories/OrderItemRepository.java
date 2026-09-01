package com.tartaro.demo.repositories;

import com.tartaro.demo.entities.OrderItem;
import com.tartaro.demo.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
