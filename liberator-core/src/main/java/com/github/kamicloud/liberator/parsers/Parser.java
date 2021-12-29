package com.github.kamicloud.liberator.parsers;

import com.github.kamicloud.liberator.config.LiberatorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public abstract class Parser {
    @Autowired
    LiberatorProperties liberatorProperties;

    @Autowired
    Environment env;

    public abstract String getName();

    public abstract void parse();
}
