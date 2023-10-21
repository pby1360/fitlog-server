package com.fitlog.server;

import com.fitlog.server.auth.repository.UserRepository;
import com.fitlog.server.configuration.SimpleAuditorAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FitlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitlogApplication.class, args);
    }

    @Autowired
    private UserRepository repository;
    @Bean
    AuditorAware<Long> auditorAware () { return new SimpleAuditorAware(repository); }

}
