package com.schooldevops.aws.mail.sdkv1.configs;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SesSDKV1Config {

    @Value("${aws.ses.profile}")
    private String profile;

    @Value("${aws.ses.region}")
    private String region;

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {

        ProfileCredentialsProvider profileCredentialsProvider = new ProfileCredentialsProvider(profile);
        return AmazonSimpleEmailServiceAsyncClientBuilder.standard()
                .withCredentials(profileCredentialsProvider)
                .withRegion(region)
                .build();
    }
}
