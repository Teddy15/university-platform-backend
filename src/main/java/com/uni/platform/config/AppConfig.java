package com.uni.platform.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "config")
@Validated
public class AppConfig {
    @Autowired
    private BuildProperties buildProperties;

    private final AmazonS3Config amazonS3Config = new AmazonS3Config();

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
