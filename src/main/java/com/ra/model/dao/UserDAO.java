package com.ra.model.dao;

import com.ra.model.entity.User;

public interface UserDAO extends IGenericDAO<User, Integer> {
    User findByEmail(String email);
}
