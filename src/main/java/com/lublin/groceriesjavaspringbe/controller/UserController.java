package com.lublin.groceriesjavaspringbe.controller;

import com.lublin.groceriesjavaspringbe.MyResourceNotFoundException;
import com.lublin.groceriesjavaspringbe.model.Task;
import com.lublin.groceriesjavaspringbe.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") String id) {
        try {
            Task task = taskService.findById(id);
            return ResponseEntity.ok().body(task);
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "finById API not found", e);
        }

    }

    @GetMapping("/task")
    public ResponseEntity<List<Task>> list() {
        try {
            List<Task> list = taskService.findAll();
            return ResponseEntity.ok().body(list);
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "finById API not found", e);
        }
    }
/*
    @PostMapping("/task")
    public ResponseEntity<?> save(@RequestBody Task task) {
        try {
            Task result = taskService.save(new Task(task.getTaskId(), task.getTitle(), task.getIsDone(), task.getDate()));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(result.getTaskId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "create API not found", e);
        }
    }
*/
    @PutMapping("/task/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String taskId, @RequestBody Task task) {
        try {
            taskService.update(taskId, task);
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "update API not found", e);
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String taskId) {
        try {
            taskService.delete(taskId);
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "deleteById API not found", e);
        }
    }

    @DeleteMapping("/task")
    public ResponseEntity deleteAll() {
        try {
            taskService.deleteAll();
            return ResponseEntity.noContent().build();
        } catch (MyResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.METHOD_NOT_ALLOWED, "delete all not allowed", e);
        }
    }
}
