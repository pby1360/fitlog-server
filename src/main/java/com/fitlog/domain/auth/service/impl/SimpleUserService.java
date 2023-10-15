package com.fitlog.domain.auth.service.impl;

import com.fitlog.domain.auth.entity.User;
import com.fitlog.domain.auth.repository.UserRepository;
import com.fitlog.domain.auth.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService implements UserService {

    private UserRepository repository;

    public SimpleUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isExist(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User join(User user) {
        return repository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
