package com.tartaro.demo.resources;

import com.tartaro.demo.entities.Address;
import com.tartaro.demo.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressResources {
    private final AddressService addressService;

    public AddressResources(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        List<Address> addresses = addressService.findAll();
            return ResponseEntity.ok().body(addresses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable(value = "id") Long id) {
        Address address = addressService.findById(id);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping
    public ResponseEntity<Address> insert(@RequestBody Address address) {
        Address address1 = addressService.insert(address);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address1.getId()).toUri();
        return ResponseEntity.ok().body(address1);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Address> delete(@PathVariable(value = "id") Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Address> update(@PathVariable(value = "id") Long id, @RequestBody Address address) {
        Address address1 = addressService.update(address, id);
        return ResponseEntity.ok().body(address1);
    }
}
