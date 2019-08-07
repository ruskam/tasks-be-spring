package com.lublin.groceriesjavaspringbe.service;

import com.lublin.groceriesjavaspringbe.dao.IUserDao;
import com.lublin.groceriesjavaspringbe.model.User;
import com.lublin.groceriesjavaspringbe.utility.Util;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserService implements IUserService{

    @Autowired
    IUserDao userDao;

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        ObjectId genId = ObjectId.get();
        user.setUserId(genId.toString());
        user.setDate(Util.getCorrectTime());
        return userDao.save(user);
    }

    @Override
    public void update(String id, User user) {
        user.setDate(Util.getCorrectTime());
        userDao.update(id, user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

}
