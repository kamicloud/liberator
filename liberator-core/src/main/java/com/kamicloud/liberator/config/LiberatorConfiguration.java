package com.kamicloud.liberator.config;

import com.kamicloud.liberator.LiberatorManager;
import com.kamicloud.liberator.parsers.DocParser;
import com.kamicloud.liberator.parsers.BuildParser;
import com.kamicloud.liberator.stubs.core.OutputStub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LiberatorConfiguration {
    private OutputStub outputStub;

    @Bean
    public LiberatorManager liberatorManager() {
        return new LiberatorManager();
    }

    @Bean
    public BuildParser parser() {
        return new BuildParser();
    }

    @Bean
    public DocParser docParser() {
        return new DocParser();
    }

    @Bean
    public OutputStub outputStub() {
        if (outputStub == null) {
            outputStub = new OutputStub();
        }
        return outputStub;
    }
}
