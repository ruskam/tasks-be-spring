package com.lublin.groceriesjavaspringbe.dao;

import com.lublin.groceriesjavaspringbe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("userDao")
public class UserDao implements IUserDao {

    @Autowired
    MongoTemplate mongoTemplate;

    /* replaced with MongoTemplate
    @Autowired
    private SessionFactory sessionFactory;*/

    @Override
    public User findById(String id) {
        Query findByIdQuery = new Query();
        findByIdQuery.addCriteria(Criteria.where("userId").is(id));
        if (mongoTemplate.findOne(findByIdQuery, User.class) != null) {
            System.out.println("found");
        } else {
            System.out.println("not found");
        }
        return mongoTemplate.findOne(findByIdQuery, User.class);
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User save(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public void update(String id, User user) {
        Query updateQuery = new Query();
        updateQuery.addCriteria(Criteria.where("userId").is(id));

        Update newUpdate = new Update();
        newUpdate.set("name", user.getName());
        newUpdate.set("email", user.getEmail());
        newUpdate.set("password", user.getDate());

        mongoTemplate.updateMulti(updateQuery, newUpdate, User.class);
    }

    @Override
    public void delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(id));
        mongoTemplate.remove(query, User.class);
    }

    @Override
    public void deleteAll() {
        Query deleteAllQuery = new Query();
        deleteAllQuery.addCriteria(Criteria.where("userId").exists(true));
        mongoTemplate.findAllAndRemove(deleteAllQuery, User.class);
    }
}
