package com.tartaro.demo.services;

import com.tartaro.demo.entities.User;
import com.tartaro.demo.repositories.UserRepository;
import com.tartaro.demo.services.middlewares.DataBaseException;
import com.tartaro.demo.services.middlewares.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){return userRepository.findAll();}

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()->new ResourceNotFoundException(id));
    }

    public User insert(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            userRepository.delete(user);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public User update(User user, Long id) {
        try {
            User updatedUser = userRepository.getReferenceById(id);
            updateData(updatedUser, user);
            return userRepository.save(updatedUser);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public void updateData(User user, User updatedUser){
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setPhone(updatedUser.getPhone());
        
    }

}

