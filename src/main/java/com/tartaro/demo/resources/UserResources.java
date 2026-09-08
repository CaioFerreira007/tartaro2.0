package com.tartaro.demo.resources;

import com.tartaro.demo.entities.User;
import com.tartaro.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    private final UserService userService;

    public UserResources(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user) {
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") Long id, @RequestBody User user) {
        user = userService.update( user,id);
        return ResponseEntity.ok().body(user);
    }
}
