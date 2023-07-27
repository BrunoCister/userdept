package com.project.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.userdept.entities.User;
import com.project.userdept.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    public ResponseEntity findAll() {
        List<User> result = userRepository.findAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        User result = userRepository.findById(id).get();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody User user) {
        User result = userRepository.save(user);
        if (result.getId() != 0) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(500).body("Erro interno.");
        
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity patch(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        User result = userRepository.findById(id).get();
        if (result.getId() == id) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().body("Usuario excluido");
        }
        return ResponseEntity.status(500).body("Erro interno.");
    }
}
