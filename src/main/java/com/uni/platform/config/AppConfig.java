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
    private final AmazonS3Config amazonS3Config = new AmazonS3Config();

    public JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    @ConfigurationProperties(prefix = "jwt_config")
    @Data
    public class JwtConfig {
        private String secret;
        private String expirationMilliseconds;
    }
    
    public AmazonS3Config getAmazonS3Config() {
        return amazonS3Config;
    }
    
    @ConfigurationProperties(prefix = "amazon_s3_config")
    @Data
    public class AmazonS3Config {
        private String bucketName;
        private String accessKey;
        private String secretKey;
        private String region;
    }
}
