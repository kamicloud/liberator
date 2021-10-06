package com.kamicloud.liberator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Objects;

@ConfigurationProperties(prefix = "liberator", ignoreUnknownFields = false)
@Component
public class LiberatorProperties {
    private LinkedList<String> generators;

    private String templatePath;

    public LinkedList<String> getGenerators() {
        return generators;
    }

    public void setGenerators(LinkedList<String> generators) {
        this.generators = generators;
    }

    public String getTemplatePath() {
        return Objects.requireNonNull(templatePath);
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}
