package com.example.AuthServiceHospital.security;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt")
@Data
public class JwtConfig{
    private String uri;
    private String refresh;
    private String header;
    private String prefix;
    private int expiration;
    private String secret;
    private Boolean noexpiration;
}
