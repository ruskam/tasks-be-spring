package com.lublin.groceriesjavaspringbe.dao;

import com.lublin.groceriesjavaspringbe.model.Task;
import com.lublin.groceriesjavaspringbe.model.User;

import java.util.List;

public interface IUserDao {

    User findById(String id);

    List<User> findAll();

    User save(User user);

    void update(String id, User user);

    void delete(String id);

    void deleteAll();

}
