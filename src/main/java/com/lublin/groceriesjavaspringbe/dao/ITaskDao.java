package com.lublin.groceriesjavaspringbe.dao;

import com.lublin.groceriesjavaspringbe.model.Task;

import java.util.List;

public interface ITaskDao {

    Task findById(String id);

    List<Task> findAll();

    Task save(Task task);

    void update(String id, Task task);

    void delete(String id);

    void deleteAll();

    List findAllByUserId(String userId);

    void deleteAllByUserId(String userId);

}
