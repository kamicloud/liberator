package com.github.kamicloud.liberator.config;

import com.github.kamicloud.liberator.utils.StringUtil;
import com.github.kamicloud.liberator.utils.UrlUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfiguration {
    @Bean
    public UrlUtil urlUtil() {
        return new UrlUtil();
    }

    @Bean
    public StringUtil stringUtil() {
        return new StringUtil();
    }
}
