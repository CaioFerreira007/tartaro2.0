package com.tartaro.demo.services;

import com.tartaro.demo.entities.Order;
import com.tartaro.demo.entities.OrderItem;
import com.tartaro.demo.repositories.OrderRepository;
import com.tartaro.demo.services.middlewares.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(long id){
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Order insert(Order order){
        return orderRepository.save(order);
    }
    

}
