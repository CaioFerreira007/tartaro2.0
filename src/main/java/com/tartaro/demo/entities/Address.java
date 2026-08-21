package com.tartaro.demo.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_address")
public class Address implements Serializable {
@Serial
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String street;
private String city;
private String state;
private String zip_code;
private String point_reference;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(user, address.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
