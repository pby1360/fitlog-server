package com.fitlog.server.auth.service;

import com.fitlog.server.auth.entity.User;

public interface UserService {

    boolean isExist(String email);
    User join(User user);
    User getUserByEmail(String email);
}
