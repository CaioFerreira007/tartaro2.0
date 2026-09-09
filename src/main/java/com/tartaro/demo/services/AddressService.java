package com.tartaro.demo.services;

import com.tartaro.demo.entities.Address;
import com.tartaro.demo.entities.User;
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
    private final UserService userService;

    public AddressService(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Address findById(Long id){
        Optional<Address> address = addressRepository.findById(id);
        return address.orElseThrow(()-> new ResourceNotFoundException(id));
    }


    public Address insert(Address address){
        User user = userService.findById(address.getUser().getId());

        long addressCount = addressRepository.countByUser(address.getUser());
        if(addressCount >=5){
            throw new DataBaseException("Limíte máximo de 5 endereços atingidos para este usuário");
        }
        address.setUser(user);
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
        existing.setPointReference(newData.getPointReference());
        existing.setZipCode(newData.getZipCode());
    }
}
