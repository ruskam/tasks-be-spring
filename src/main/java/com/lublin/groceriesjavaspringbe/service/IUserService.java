package com.lublin.groceriesjavaspringbe.service;

import com.lublin.groceriesjavaspringbe.model.User;

import java.util.List;

public interface IUserService {

    User findById(String id);

    List<User> findAll();

    User save(User user);

    void update(String id, User user);

    void delete(String id);

    void deleteAll();

}
