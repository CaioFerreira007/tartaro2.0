package com.tartaro.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tartaro.demo.enums.StatusOrder;
import com.tartaro.demo.enums.TypeOrder;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")

    private Instant moment_order = Instant.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_order")
    private Address address;

    private StatusOrder status;

    private TypeOrder type_order;


public Order() {

}




}
