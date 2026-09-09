package com.tartaro.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_address")
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String pointReference;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

    public Address() {
    }

    public Address(String street, String city, String state, String pointReference, String zipCode, User user) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.pointReference = pointReference;
        this.zipCode = zipCode;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public String getPointReference() { return pointReference; }
    public void setPointReference(String pointReference) { this.pointReference = pointReference; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @JsonIgnore
    public Set<Order> getOrders() { return orders; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }
    @Override
    public int hashCode() { return Objects.hashCode(id); }
}