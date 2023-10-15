package com.fitlog.domain.auth.controller;

import com.fitlog.domain.auth.dto.SignInResponse;
import com.fitlog.domain.auth.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger log = LoggerFactory.getLogger(getClass());

    private LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/google-login")
    public ResponseEntity loginWithGoogle (@RequestParam String code) throws URISyntaxException {
        log.info(":: loginWithGoogle");
        log.info(":: code ? {}", code);

        try {
            SignInResponse response = loginService.login(code);
            return ResponseEntity.ok(response);

        } catch (IllegalAccessException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}