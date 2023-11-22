package com.ra.model.service;

import com.ra.model.dao.UserDAO;
import com.ra.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public Boolean create(User user) {
        return userDAO.create(user);
    }

    @Override
    public Boolean update(User user, Integer integer) {
        return null;
    }

    @Override
    public User findId(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
