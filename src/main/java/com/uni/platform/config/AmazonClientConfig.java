package com.uni.platform.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AmazonClientConfig {
    private final AppConfig appConfig;

    public AmazonS3 getAmazonS3Client() {
        final BasicAWSCredentials basicAWSCredentials =
                new BasicAWSCredentials(appConfig.getAmazonS3Config().getAccessKey(),
                        appConfig.getAmazonS3Config().getSecretKey());

        return AmazonS3ClientBuilder.
                standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(appConfig.getAmazonS3Config().getRegion())
                .build();

    }
}
