package com.tartaro.demo.services;

import com.tartaro.demo.entities.Address;
import com.tartaro.demo.repositories.AddressRepository;
import com.tartaro.demo.services.middlewares.DataBaseException;
import com.tartaro.demo.services.middlewares.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {


    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Address findById(Long id){
        Optional<Address> address = addressRepository.findById(id);
        return address.orElseThrow(()-> new ResourceNotFoundException(id));
    }


    public Address insert(Address address){
        return addressRepository.save(address);
    }


    public void delete(Long id){
        try{
            Address address = addressRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
            addressRepository.delete(address);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }


    public Address update(Address newData, Long id) {
        try {
            Address existing = addressRepository.getReferenceById(id);
            updateData(existing, newData);
            return addressRepository.save(existing);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(Address existing, Address newData) {
        existing.setCity(newData.getCity());
        existing.setStreet(newData.getStreet());
        existing.setState(newData.getState());
        existing.setPoint_reference(newData.getPoint_reference());
        existing.setZip_code(newData.getZip_code());
    }
}
