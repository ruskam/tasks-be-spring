package com.lublin.groceriesjavaspringbe.dao;

import com.lublin.groceriesjavaspringbe.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("taskDao")
public class TaskDao implements ITaskDao {

    @Autowired
    MongoTemplate mongoTemplate;

    /* replaced with MongoTemplate
    @Autowired
    private SessionFactory sessionFactory;*/

    @Override
    public Task findById(String id) {
        Query findByIdQuery = new Query();
        findByIdQuery.addCriteria(Criteria.where("taskId").is(id));
        if (mongoTemplate.findOne(findByIdQuery, Task.class) != null) {
            System.out.println("found");
        } else {
            System.out.println("not found");
        }
        return mongoTemplate.findOne(findByIdQuery, Task.class);
    }

    @Override
    public List<Task> findAll() {
        return mongoTemplate.findAll(Task.class);
    }

    @Override
    public Task save(Task task) {
        return mongoTemplate.save(task);
    }

    @Override
    public void update(String id, Task task) {
        Query updateQuery = new Query();
        updateQuery.addCriteria(Criteria.where("taskId").is(id));

        Update newUpdate = new Update();
        newUpdate.set("title", task.getTitle());
        newUpdate.set("isDone", task.getIsDone());
        newUpdate.set("date", task.getDate());

        mongoTemplate.updateMulti(updateQuery, newUpdate, Task.class);
    }

    @Override
    public void delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("taskId").is(id));
        mongoTemplate.remove(query, Task.class);
    }

    @Override
    public void deleteAll() {
        Query deleteAllQuery = new Query();
        deleteAllQuery.addCriteria(Criteria.where("taskId").exists(true));
        mongoTemplate.findAllAndRemove(deleteAllQuery, Task.class);
    }

    @Override
    public List<Task> findAllByUserId(String userId) {
        Query findAllByUserId = new Query();
        findAllByUserId.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(findAllByUserId, Task.class);
    }

    @Override
    public void deleteAllByUserId(String userId) {
        Query deleteAllByUserId = new Query();
        deleteAllByUserId.addCriteria(Criteria.where("userId").is(userId));
        mongoTemplate.findAllAndRemove(deleteAllByUserId, Task.class);
    }
}
