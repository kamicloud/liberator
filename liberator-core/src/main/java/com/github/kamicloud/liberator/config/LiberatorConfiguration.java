package com.github.kamicloud.liberator.config;

import com.github.kamicloud.liberator.LiberatorManager;
import com.github.kamicloud.liberator.parsers.BuildParser;
import com.github.kamicloud.liberator.parsers.DocParser;
import com.github.kamicloud.liberator.parsers.ASTParser;
import com.github.kamicloud.liberator.stubs.core.OutputStub;
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
    public ASTParser astParser() {
        return new ASTParser();
    }

    @Bean
    public OutputStub outputStub() {
        if (outputStub == null) {
            outputStub = new OutputStub();
        }
        return outputStub;
    }
}
