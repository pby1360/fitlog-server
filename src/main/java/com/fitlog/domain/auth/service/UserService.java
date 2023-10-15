package com.fitlog.domain.auth.service;

import com.fitlog.domain.auth.entity.User;

public interface UserService {

    boolean isExist(String email);
    User join(User user);
    User getUserByEmail(String email);
}
