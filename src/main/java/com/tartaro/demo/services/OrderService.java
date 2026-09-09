package com.tartaro.demo.services;
import com.tartaro.demo.entities.*;
import com.tartaro.demo.repositories.OrderItemRepository;
import com.tartaro.demo.repositories.ProductRepository;
import com.tartaro.demo.enums.TypeOrder;
import com.tartaro.demo.repositories.OrderRepository;
import com.tartaro.demo.services.middlewares.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
    private final AddressService addressService;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository, UserService userService, AddressService addressService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.userService = userService;
        this.addressService = addressService;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(long id){
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Order insert(Order order){
        User user = userService.findById(order.getUser().getId());
        order.setUser(user);

        Payment payment = new Payment(Instant.now(),order);
        order.setPayment(payment);

        order.setMomentOrder(Instant.now());
        if(order.getTypeOrder() == TypeOrder.DELIVERED){
            if (order.getTypeOrder() == TypeOrder.DELIVERED && order.getAddress() == null) {
                throw new IllegalArgumentException("O endereço é obrigatório para pedidos de entrega.");
            }
                Address address = addressService.findById(order.getAddress().getId());
                order.setAddress(address);

        }else{
            order.setAddress(null);
        }

        order = orderRepository.save(order);
        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId()).get();

            item.setProduct(product);
            item.setOrder(order);

            item.setPrice(product.getPrice());
        }

        orderItemRepository.saveAll(order.getItems());
        return order;
    }
    

}
