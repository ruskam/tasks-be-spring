package com.lublin.groceriesjavaspringbe.service;

import com.lublin.groceriesjavaspringbe.model.Task;

import java.util.List;

public interface ITaskService {

    Task findById(String id);

    List<Task> findAll();

    List<Task> findAllByUserId(String userId);

    Task save(Task task);

    void update(String id, Task task);

    void delete(String id);

    void deleteAll();

}
