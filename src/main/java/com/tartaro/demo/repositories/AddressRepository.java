package com.tartaro.demo.repositories;

import com.tartaro.demo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address,Long> {
}
