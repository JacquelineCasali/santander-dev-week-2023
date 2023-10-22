package com.java.service;

import com.java.domain.model.User;

public interface UserService {
    User findById(Long id);
    User create(User userToCreate);
}
