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
private String zip_code;
private String point_reference;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

@OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
private Set<Order> orders = new HashSet<>();
    public Address() {
    }

    public Address(String street, String city, String state, String point_reference, String zip_code, User user) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.point_reference = point_reference;
        this.zip_code = zip_code;
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoint_reference() {
        return point_reference;
    }

    public void setPoint_reference(String point_reference) {
        this.point_reference = point_reference;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    @JsonIgnore
    public Set<Order> getOrders() {
        return orders;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
