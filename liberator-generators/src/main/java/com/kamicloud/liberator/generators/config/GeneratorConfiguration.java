package com.kamicloud.liberator.generators.config;

import com.kamicloud.liberator.generators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class GeneratorConfiguration {
    @Bean
    public PostmanGenerator postmanGenerator() {
        return new PostmanGenerator();
    }

    @Bean
    public TestCaseGenerator testCaseGenerator() {
        return new TestCaseGenerator();
    }

    @Bean
    public OpenAPIGenerator openAPIGenerator() {
        return new OpenAPIGenerator();
    }
}
