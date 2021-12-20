package com.kamicloud.liberator.laravel;

import com.kamicloud.liberator.Generator;
import com.kamicloud.liberator.generators.config.GeneratorProperties;
import com.kamicloud.liberator.laravel.config.LaravelProperties;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseGenerator extends Generator {
    @Autowired
    GeneratorProperties generatorProperties;

    @Autowired
    LaravelProperties laravelProperties;
}
