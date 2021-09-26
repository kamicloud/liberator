package com.kamicloud.liberator.config;

import com.kamicloud.liberator.LiberatorManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LiberatorConfiguration {
    @Bean
    public LiberatorManager liberatorManager() {
        return new LiberatorManager();
    }
}
