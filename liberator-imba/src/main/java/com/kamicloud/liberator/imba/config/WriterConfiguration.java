package com.kamicloud.liberator.imba.config;

import com.kamicloud.liberator.generators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class WriterConfiguration {

    @Bean
    public PostmanGenerator postmanWriter() {
        return new PostmanGenerator();
    }

    @Bean
    public LaravelGenerator laravelWriter() {
        return new LaravelGenerator();
    }

    @Bean
    public JavaClientGenerator javaClientWriter() {
        return new JavaClientGenerator();
    }

    @Bean
    public TestCaseGenerator testCaseWriter() {
        return new TestCaseGenerator();
    }

    @Bean
    public DocGenerator docWriter() {
        return new DocGenerator();
    }

    @Bean
    public AutoTestGenerator autoTestWriter() {
        return new AutoTestGenerator();
    }

    @Bean
    public NodeJsClientGenerator nodeJsClientWriter() {
        return new NodeJsClientGenerator();
    }

    @Bean
    public OpenAPIGenerator openAPIWriter() {
        return new OpenAPIGenerator();
    }
}
