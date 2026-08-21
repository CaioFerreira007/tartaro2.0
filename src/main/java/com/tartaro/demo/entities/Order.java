package com.tartaro.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tartaro.demo.enums.OrderStatus;
import com.tartaro.demo.enums.TypeOrder;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")

    private Instant moment_order = Instant.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_order")
    private Address address;

    private OrderStatus status;

    private TypeOrder type_order;


public Order() {

}

    public Order(User user, Instant moment_order, OrderStatus status, TypeOrder type_order) {
        this.user = user;
        this.moment_order = moment_order;
        this.status = status;
        this.type_order = type_order;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public Instant getMoment_order() {
        return moment_order;
    }

    public void setMoment_order(Instant moment_order) {
        this.moment_order = moment_order;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public TypeOrder getType_order() {
        return type_order;
    }

    public void setType_order(TypeOrder type_order) {
        this.type_order = type_order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "address=" + address +
                ", id=" + id +
                ", user=" + user +
                ", items=" + items +
                ", moment_order=" + moment_order +
                ", status=" + status +
                ", type_order=" + type_order +
                '}';
    }
}
