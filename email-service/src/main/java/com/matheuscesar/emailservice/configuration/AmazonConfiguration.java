package com.matheuscesar.emailservice.configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {

    @Bean
    public AmazonSimpleEmailService amazonSESConfig() {
        return AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
    }
}
