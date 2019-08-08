package com.lublin.groceriesjavaspringbe.controller;

import com.lublin.groceriesjavaspringbe.MyResourceNotFoundException;
import com.lublin.groceriesjavaspringbe.model.User;
import com.lublin.groceriesjavaspringbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok().body(user);
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "finById API not found", e);
        }

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> list() {
        try {
            List<User> list = userService.findAll();
            return ResponseEntity.ok().body(list);
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "finById API not found", e);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody User user) {
        try {
            User result = userService.save(new User(user.getUserId(), user.getName(), user.getEmail(), user.getPassword(), user.getDate()));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(result.getUserId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "create API not found", e);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String userId, @RequestBody User user) {
        try {
            userService.update(userId, user);
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "update API not found", e);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "deleteById API not found", e);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity deleteAll() {
        try {
            userService.deleteAll();
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.METHOD_NOT_ALLOWED, "delete all not allowed", e);
        }
    }
}
