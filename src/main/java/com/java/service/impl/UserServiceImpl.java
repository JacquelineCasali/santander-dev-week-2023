package com.java.service.impl;

import com.java.domain.model.User;
import com.java.domain.repository.UserRepository;
import com.java.service.UserService;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("Usuario Já existe");
        }

        return userRepository.save(userToCreate);
    }
}
