package com.ra.model.service;

import com.ra.model.entity.User;

public interface UserService extends IGenericService<User, Integer>{
    User findByEmail(String email);
}
