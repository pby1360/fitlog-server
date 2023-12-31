package com.fitlog.server.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class SignInResponse {
    private Long id;
    private String email;
    private String name;
    private Set<String> roles;
    private String token;
    private long expiredAt;
    private String image;

    // TODO: 2023-03-22 add User Information field 
    // private User user;


    @Override
    public String toString() {
        return "SignInResponse{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                ", token='" + token + '\'' +
                ", expiredAt=" + expiredAt +
                '}';
    }
}
