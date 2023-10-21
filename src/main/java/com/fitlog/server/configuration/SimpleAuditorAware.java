package com.fitlog.server.configuration;

import com.fitlog.server.auth.entity.User;
import com.fitlog.server.auth.repository.UserRepository;
import com.fitlog.server.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SimpleAuditorAware implements AuditorAware<Long> {

    private UserRepository repository;

    public SimpleAuditorAware(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Long> getCurrentAuditor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByEmail(email);
        return Optional.ofNullable(user.getId());
    }
}
