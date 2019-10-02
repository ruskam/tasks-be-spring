package com.lublin.groceriesjavaspringbe.controller;

import com.lublin.groceriesjavaspringbe.MyResourceNotFoundException;
import com.lublin.groceriesjavaspringbe.model.Task;
import com.lublin.groceriesjavaspringbe.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") String id) {
        try {
            Task task = taskService.findById(id);
            return ResponseEntity.ok().body(task);
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "finById API not found", e);
        }

    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> listAllTasks() {
        try {
            List<Task> list = taskService.findAll();
            return ResponseEntity.ok().body(list);
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "findAll API not found", e);
        }
    }

    @GetMapping("/users/{userId}/tasks")
    public ResponseEntity<List<Task>> listAllTasksByUserId(@PathVariable String userId) {
        try {
            List<Task> list = taskService.findAllByUserId(userId);
            return ResponseEntity.ok().body(list);
        } catch (MyResourceNotFoundException e) {
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "findAllByUserId API not found", e);
        }
    }

    @PostMapping("/users/{userId}/tasks")
    public ResponseEntity<?> save(@RequestBody Task task, @PathVariable String userId) {
        try {
            task.setUserId(userId);
            Task result = taskService.save(task);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(result.getTaskId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "create API not found", e);
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String taskId, @RequestBody Task task) {
        try {
            taskService.update(taskId, task);
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "update API not found", e);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String taskId) {
        try {
            taskService.delete(taskId);
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "deleteById API not found", e);
        }
    }

    @DeleteMapping("/tasks")
    public ResponseEntity deleteAll() {
        try {
            taskService.deleteAll();
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.METHOD_NOT_ALLOWED, "delete all not allowed", e);
        }
    }

    @DeleteMapping("/users/{userId}/tasks")
    public ResponseEntity<?> deleteAllByUserId(@PathVariable String userId) {
        try {
            taskService.deleteAllByUserId(userId);
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "delete all tasks given userId not allowed");
        }
    }
}
