package com.kamicloud.liberator.generators;

import com.kamicloud.liberator.Generator;
import com.kamicloud.liberator.generators.config.GeneratorProperties;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseGenerator extends Generator {
    @Autowired
    GeneratorProperties generatorProperties;
}
