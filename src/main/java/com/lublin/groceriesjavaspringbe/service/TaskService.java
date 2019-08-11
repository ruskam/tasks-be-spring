package com.lublin.groceriesjavaspringbe.service;

import com.lublin.groceriesjavaspringbe.dao.ITaskDao;
import com.lublin.groceriesjavaspringbe.model.Task;
import com.lublin.groceriesjavaspringbe.utility.Util;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("taskService")
@Transactional(readOnly = true)
public class TaskService implements ITaskService{

    @Autowired
    ITaskDao taskDao;

    @Override
    public Task findById(String id) {
        return taskDao.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    // Save a task with user id
    @Override
    public Task save(Task task) {
        ObjectId genId = ObjectId.get();
        task.setTaskId(genId.toString());
        task.setDate(Util.getCorrectTime());
        return taskDao.save(task);
    }

    @Override
    public void update(String id, Task task) {
        task.setDate(Util.getCorrectTime());
        taskDao.update(id, task);
    }

    @Override
    public void delete(String id) {
        taskDao.delete(id);
    }

    @Override
    public void deleteAll() {
        taskDao.deleteAll();
    }

    @Override
    public List<Task> findAllByUserId(String userId) {
        return taskDao.findAllByUserId(userId);
    }

    @Override
    public void deleteAllByUserId(String userId) {
        taskDao.deleteAllByUserId(userId);
    }


}
