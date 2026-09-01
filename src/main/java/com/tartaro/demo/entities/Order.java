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

    private Instant momentOrder = Instant.now();

    @ManyToOne
    @JoinColumn(name = "address_order")
    private Address address;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Enumerated(EnumType.STRING)
    private TypeOrder typeOrder;


    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
public Order() {

}

    public Order(User user, Instant momentOrder, OrderStatus status, TypeOrder typeOrder) {
        this.user = user;
        this.momentOrder = momentOrder;
        this.status = status;
        this.typeOrder = typeOrder;
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

    public Instant getMomentOrder() {
        return momentOrder;
    }

    public void setMomentOrder(Instant momentOrder) {
        this.momentOrder = momentOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public TypeOrder getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(TypeOrder typeOrder) {
        this.typeOrder = typeOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public Double getTotal() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
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
                ", moment_order=" + momentOrder +
                ", status=" + status +
                ", type_order=" + typeOrder +
                '}';
    }
}
