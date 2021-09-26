package com.kamicloud.liberator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@ConfigurationProperties(prefix = "liberator", ignoreUnknownFields = false)
@Component
public class LiberatorProperties {
    private LinkedList<String> generators;

    public LinkedList<String> getGenerators() {
        return generators;
    }

    public void setGenerators(LinkedList<String> generators) {
        this.generators = generators;
    }
}
