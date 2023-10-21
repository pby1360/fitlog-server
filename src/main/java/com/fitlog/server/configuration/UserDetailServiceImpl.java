package com.fitlog.server.configuration;

import com.fitlog.server.auth.entity.User;
import com.fitlog.server.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository repository;

    public UserDetailServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        CustomUserDetails detail = new CustomUserDetails(user.getId(), user.getEmail(), user.getName(), null);
        return detail;
    }
}
