package com.github.kamicloud.liberator.laravel.config;

import com.github.kamicloud.liberator.laravel.AutoTestGenerator;
import com.github.kamicloud.liberator.laravel.DocGenerator;
import com.github.kamicloud.liberator.laravel.LaravelGenerator;
import com.github.kamicloud.liberator.laravel.NodeJsClientGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LaravelConfiguration {
    @Bean
    public LaravelGenerator laravelGenerator() {
        return new LaravelGenerator();
    }

    @Bean
    public DocGenerator docGenerator() {
        return new DocGenerator();
    }

    @Bean
    public AutoTestGenerator autoTestGenerator() {
        return new AutoTestGenerator();
    }

    @Bean
    public NodeJsClientGenerator nodeJsClientGenerator() {
        return new NodeJsClientGenerator();
    }
}
