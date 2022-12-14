package com.uni.platform.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "config")
@Validated
public class AppConfig {
    private final JwtConfig jwtConfig = new JwtConfig();

    public JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    @ConfigurationProperties(prefix = "jwt_config")
    @Data
    public class JwtConfig {
        private String secret;
        private String expirationMilliseconds;
    }
}
