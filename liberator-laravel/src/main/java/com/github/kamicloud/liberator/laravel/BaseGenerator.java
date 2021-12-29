package com.github.kamicloud.liberator.laravel;

import com.github.kamicloud.liberator.Generator;
import com.github.kamicloud.liberator.laravel.config.LaravelProperties;
import com.github.kamicloud.liberator.generators.config.GeneratorProperties;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseGenerator extends Generator {
    @Autowired
    GeneratorProperties generatorProperties;

    @Autowired
    LaravelProperties laravelProperties;
}
